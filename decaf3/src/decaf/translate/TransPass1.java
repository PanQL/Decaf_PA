package decaf.translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import decaf.tree.Tree;
import decaf.tree.Tree.Assign;
import decaf.tree.Tree.VarIdent;
import decaf.backend.OffsetCounter;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.tac.Temp;

public class TransPass1 extends Tree.Visitor {
	private Translater tr;

	private int objectSize;

	private List<Variable> vars;

	public TransPass1(Translater tr) {
		this.tr = tr;
		vars = new ArrayList<Variable>();
	}

	@Override
	public void visitTopLevel(Tree.TopLevel program) {
		for (Tree.ClassDef cd : program.classes) {
			cd.accept(this);
		}
		for (Tree.ClassDef cd : program.classes) {
			tr.createVTable(cd.symbol);		//创建虚函数表
			tr.genNewForClass(cd.symbol);	//生成构造函数
		}
		for (Tree.ClassDef cd : program.classes) {
			if (cd.parent != null) {
				cd.symbol.getVtable().parent = cd.symbol.getParent()
						.getVtable();
			}
		}
	}

	@Override
	public void visitClassDef(Tree.ClassDef classDef) {
		//设定当前类及其超类中的变量、函数的num分配
		classDef.symbol.resolveFieldOrder();
		objectSize = 0;		//统计需要为变量分配的空间大小，初始化
		vars.clear();	//变量集合的清空，统计开始前的准备
		for (Tree f : classDef.fields) {
			f.accept(this);
		}
		Collections.sort(vars, Symbol.ORDER_COMPARATOR);
		OffsetCounter oc = OffsetCounter.VARFIELD_OFFSET_COUNTER;	//kind类型为VarFieled
		Class c = classDef.symbol.getParent();
		if (c != null) {
			oc.set(c.getSize());
		} else {
			oc.reset();
		}
		for (Variable v : vars) {
			v.setOffset(oc.next(OffsetCounter.WORD_SIZE));
		}
	}

	//对方法声明进行处理，根据对应的Function符号表项
	@Override
	public void visitMethodDef(Tree.MethodDef funcDef) {
		Function func = funcDef.symbol;
		if (!func.isStatik()) {
			func.setOffset(2 * OffsetCounter.POINTER_SIZE + func.getOrder()
					* OffsetCounter.POINTER_SIZE);
		}
		tr.createFuncty(func);	//为func设置Functy
		OffsetCounter oc = OffsetCounter.PARAMETER_OFFSET_COUNTER;
		oc.reset();
		int order;
		if (!func.isStatik()) {		//if the function is not static, count variables in it
			Variable v = (Variable) func.getAssociatedScope().lookup("this");	//search a symbol named this in scope of this function
			v.setOrder(0);
			Temp t = Temp.createTempI4();	//create temp t for v.
			t.sym = v;
			t.isParam = true;
			v.setTemp(t);
			v.setOffset(oc.next(OffsetCounter.POINTER_SIZE));
			order = 1;
		} else {	//then, it is static, set order 0
			order = 0;
		}
		for (Tree.VarDef vd : funcDef.formals) {
			vd.symbol.setOrder(order++);	//set order for variable symbol in this function.
			Temp t = Temp.createTempI4();
			t.sym = vd.symbol;
			t.isParam = true;
			vd.symbol.setTemp(t);
			vd.symbol.setOffset(oc.next(vd.symbol.getTemp().size));
		}
	}

	//对变量声明进行处理，体现在重新计算objectSize
	@Override
	public void visitVarDef(Tree.VarDef varDef) {
		vars.add(varDef.symbol);
		objectSize += OffsetCounter.WORD_SIZE;
	}

	@Override
	public void visitVarIdent(VarIdent that) {
		vars.add(that.symbol);
		objectSize += OffsetCounter.WORD_SIZE;
	}

	@Override
	public void visitAssign(Assign that) {
		that.left.accept(this);
	}

}
