package decaf.backend;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import decaf.Driver;
import decaf.dataflow.BasicBlock;
import decaf.machdesc.Register;
import decaf.tac.Tac;
import decaf.tac.Temp;


class InferenceGraph {
	public Set<Temp> nodes = new HashSet<>();	//节点集合
	public Map<Temp, Set<Temp>> neighbours = new HashMap<>();	//任一节点的邻居节点集合
	public Map<Temp, Integer> nodeDeg = new HashMap<>();	//任一节点的度
	public BasicBlock bb;	//基本块
	public Register[] regs;		//寄存器堆
	public Register fp;		//栈顶寄存器
	public Set<Temp> liveUseLoad = new HashSet<>();		//为什么有这个？？？
	public Set<Temp> tacLive = new HashSet<>();


	private void clear() {
		nodes.clear();
		neighbours.clear();
		nodeDeg.clear();
		liveUseLoad.clear();
	}


	//为各个temp分配寄存器
	public void alloc(BasicBlock bb, Register[] regs, Register fp) {
		this.regs = regs;
		this.bb = bb;
		this.fp = fp;
		while (true) {
			clear();
			makeGraph();
			if (color())
				break;
			// For simplicity, omit handling for spilling.
		}
	}


	private void addNode(Temp node) {	//在图中添加点
		if (nodes.contains(node)) return;
		if (node.reg != null && node.reg.equals(fp)) return;
		nodes.add(node);
		neighbours.put(node, new HashSet<Temp>());
		nodeDeg.put(node, 0);
	}


	private void removeNode(Temp n) {	//从图中删除点
		nodes.remove(n);
		for (Temp m : neighbours.get(n))
			if (nodes.contains(m))
				nodeDeg.put(m, nodeDeg.get(m) - 1);
	}


	private void addEdge(Temp a, Temp b) {	//向图中添加边
		neighbours.get(a).add(b);
		neighbours.get(b).add(a);
		nodeDeg.put(a, nodeDeg.get(a) + 1);
		nodeDeg.put(b, nodeDeg.get(b) + 1);
	}


	private boolean color() {	//上色
		if (nodes.isEmpty())
			return true;

		// Try to find a node with less than K neighbours
		Temp n = null;
		for (Temp t : nodes) {
			if (nodeDeg.get(t) < regs.length) {		//如果当前节点的度小于寄存器的总个数
				n = t;
				break;
			}
		}

		if (n != null) {
			// We've found such a node.
			removeNode(n);
			boolean subColor = color();
			n.reg = chooseAvailableRegister(n);
			return subColor;
		} else {	//所有节点的度都大于等于当前的寄存器总个数，需要spill进内存。不支持，当前的图不符合要求。
			throw new IllegalArgumentException(
					"Coloring with spilling is not yet supported");
		}
	}


	Register chooseAvailableRegister(Temp n) {	//返回一个当前可分配的寄存器
		Set<Register> usedRegs = new HashSet<>();
		for (Temp m : neighbours.get(n)) {	//将所有已经被n的邻居占用的寄存器加入到usedRegs
			if (m.reg == null) continue;
			usedRegs.add(m.reg);
		}
		for (Register r : regs)		//找到一个n的邻居没有占用过的寄存器，用来作为n的寄存器。
			if (!usedRegs.contains(r))
				return r;
		return null;
	}


	void makeGraph() {
		// First identify all nodes. 
		// Each value is a node.
		makeNodes();
		// Then build inference edges:
		// It's your job to decide what values should be linked.
		makeEdges();
	}


	void makeNodes() {
		for (Tac tac = bb.tacList; tac != null; tac = tac.next) {
			switch (tac.opc) {
				case ADD: case SUB: case MUL: case DIV: case MOD:
				case LAND: case LOR: case GTR: case GEQ: case EQU:
				case NEQ: case LEQ: case LES:
					addNode(tac.op0); addNode(tac.op1); addNode(tac.op2);
					break;

				case NEG: case LNOT: case ASSIGN:
					addNode(tac.op0); addNode(tac.op1);
					break;

				case LOAD_VTBL: case LOAD_IMM4: case LOAD_STR_CONST:
					addNode(tac.op0);
					break;

				case INDIRECT_CALL:
					addNode(tac.op1);
				case DIRECT_CALL:
					// tac.op0 is used to hold the return value.
					// If we are calling a function with void type, then tac.op0 is null.
					if (tac.op0 != null) addNode(tac.op0);
					break;

				case PARM:
					addNode(tac.op0);
					break;

				case LOAD:
				case STORE:
					addNode(tac.op0); addNode(tac.op1);
					break;

				case BRANCH: case BEQZ: case BNEZ: case RETURN:
					throw new IllegalArgumentException();
			}
		}
	}


	// With your definition of inference graphs, build the edges.
	//my work
	void makeEdges() {
		tacLive.clear();
		for (Tac tac = bb.tacList; tac != null; tac = tac.next) {
			switch (tac.opc) {
				case ADD: case SUB: case MUL: case DIV: case MOD:	
				case LAND: case LOR: case GTR: case GEQ: case EQU:
				case NEQ: case LEQ: case LES:	//三元运算，Def op0, use op1 op2
					checkNewDefine(tac, tac.op0);
					checkLiving(tac, tac.op1);
					checkLiving(tac, tac.op2);
					break;
				case NEG: case LNOT: case ASSIGN:	//二元运算，Def op0, use op1
					checkNewDefine(tac, tac.op0);
					checkLiving(tac, tac.op1);
					break;
				case LOAD_VTBL: case LOAD_IMM4: case LOAD_STR_CONST:	//加载常量到某寄存器，这时，对于之后还需要使用的变量，不能与之重复
					checkNewDefine(tac, tac.op0);
					break;
				case INDIRECT_CALL:
					if(tac.op0 != null){
						checkNewDefine(tac, tac.op0);
					}
					checkLiving(tac, tac.op1);
					break;
				case DIRECT_CALL:
					if(tac.op0 != null){
						checkNewDefine(tac, tac.op0);
					}
					break;
				case PARM:
					checkLiving(tac, tac.op0);
					break;
				case LOAD:	//op0 is value , op1 is base address
					checkNewDefine(tac, tac.op0);
					checkLiving(tac, tac.op1);
					break;
				case STORE:	//op0 is value , op1 is base address
					checkLiving(tac, tac.op0);
					checkLiving(tac, tac.op1);
					break;
				case BRANCH: case BEQZ: case BNEZ: case RETURN:
					throw new IllegalArgumentException();
			}
		}
	}

	//my work
	private void checkNewDefine(Tac tac, Temp t){
		for(Temp temp : tacLive){	//对于在集合中的每一个变量，当前定值的变量不能与之共用一个寄存器
			addEdge(t, temp);
		}
		if(tac.liveOut.contains(t)){	//如果当前定值的变量之后还活跃，则加入集合中 
			tacLive.add(t);
		}else if(tacLive.contains(t)){	//如果之后不活跃了，直接从集合中删除他
			tacLive.remove(t);
		}
	}

	//my work
	private void checkLiving(Tac tac, Temp t){
		if(!tac.liveOut.contains(t)){		//对于当前引用的变量，如果在接下来不再活跃，则从集合中去掉
			if(tacLive.contains(t)){
				tacLive.remove(t);
			}
		}
	}
}

