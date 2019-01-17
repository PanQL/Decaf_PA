package decaf.dataflow;

import java.io.PrintWriter;
import java.util.*;

import decaf.machdesc.Asm;
import decaf.machdesc.Register;
import decaf.tac.Label;
import decaf.tac.Tac;
import decaf.tac.Temp;

public class BasicBlock {
    public int bbNum;   //块标号

    public enum EndKind {   //最后一个语句的类型
        BY_BRANCH, BY_BEQZ, BY_BNEZ, BY_RETURN
    }

    public EndKind endKind;

    public int endId; // last TAC's id for this basic block

    public int inDegree;    //

    public Tac tacList;

    public Label label;     //基本块开头的标号

    public Temp var;

    public Register varReg;

    public int[] next;  //后继基本块的标号数组，如果有，最多有两个后继基本块

    public boolean cancelled;

    public boolean mark;

    public Set<Temp> def;   //进入块之后直接被重新定值的变量

    public Set<Temp> liveUse;   //在(可能存在的定值)前被引用的变量,也就是进入块之后先被引用而不是被定值。

    public Set<Temp> liveIn;    //在块的入口是活跃的变量

    public Set<Temp> liveOut;   //在块的出口是活跃的变量。等于所有后继基本块的活跃变量的并集

    public Set<Temp> saves;

    private List<Asm> asms;

    /**
     * DUChain.
     *
     * 表中的每一项 `Pair(p, A) -> ds` 表示 变量 `A` 在定值点 `p` 的 DU 链为 `ds`.
     * 这里 `p` 和 `ds` 中的每一项均指的定值点或引用点对应的那一条 TAC 的 `id`.
     */
    private Map<Pair, Set<Integer>> DUChain;

    public Map<Temp, Set<Integer>> useIn;

    public Map<Temp, Set<Integer>> useOut;
    
    // public Set<Temp> useThrough;

    public Set<Temp> useDef;

    public BasicBlock() {
        //初始化各个集合
        def = new TreeSet<Temp>(Temp.ID_COMPARATOR);
        liveUse = new TreeSet<Temp>(Temp.ID_COMPARATOR);
        liveIn = new TreeSet<Temp>(Temp.ID_COMPARATOR);
        liveOut = new TreeSet<Temp>(Temp.ID_COMPARATOR);
        next = new int[2];
        asms = new ArrayList<Asm>();

        DUChain = new TreeMap<Pair, Set<Integer>>(Pair.COMPARATOR);

        useIn = new TreeMap<Temp, Set<Integer>>();
        useOut = new TreeMap<Temp, Set<Integer>>();
        // useThrough = new TreeSet<Temp>(Temp.ID_COMPARATOR);
        useDef = new TreeSet<Temp>(Temp.ID_COMPARATOR);
    }

    public void allocateTacIds() {  //为各个tac语句分配号码
        for (Tac tac = tacList; tac != null; tac = tac.next) {
            tac.id = IDAllocator.apply();
        }
        endId = IDAllocator.apply();
    }

    public void computeDefAndLiveUse() {
        for (Tac tac = tacList; tac != null; tac = tac.next) {
            switch (tac.opc) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                case LAND:
                case LOR:
                case GTR:
                case GEQ:
                case EQU:
                case NEQ:
                case LEQ:
                case LES:
                /* use op1 and op2, def op0 */
                    if (tac.op1.lastVisitedBB != bbNum) {   //如果op1上次引用是在当前基本块之外

                        useIn.put(tac.op1, new TreeSet<Integer>());
                        useIn.get(tac.op1).add(tac.id);

                        liveUse.add(tac.op1);
                        tac.op1.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op1)){
                        useIn.get(tac.op1).add(tac.id);
                    }

                    if (tac.op2.lastVisitedBB != bbNum) {   //如果op2上次引用是在当前基本块之外

                        useIn.put(tac.op2, new TreeSet<Integer>());
                        useIn.get(tac.op2).add(tac.id);

                        liveUse.add(tac.op2);
                        tac.op2.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op2)){
                        useIn.get(tac.op2).add(tac.id);
                    }

                    if (tac.op0.lastVisitedBB != bbNum) {   //如果op0在基本块内还未被引用过
                        def.add(tac.op0);
                        tac.op0.lastVisitedBB = bbNum;
                    }

                    if(!useDef.contains(tac.op0)){  //将被定值的temp加入到useDef中
                        useDef.add(tac.op0);
                    }

                    break;
                case NEG:
                case LNOT:
                case ASSIGN:
                case INDIRECT_CALL:
                case LOAD:
				/* use op1, def op0 */
                    if (tac.op1.lastVisitedBB != bbNum) {

                        useIn.put(tac.op1, new TreeSet<Integer>());
                        useIn.get(tac.op1).add(tac.id);

                        liveUse.add(tac.op1);
                        tac.op1.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op1)){
                        useIn.get(tac.op1).add(tac.id);
                    }
                    if (tac.op0 != null && tac.op0.lastVisitedBB != bbNum) {  // in INDIRECT_CALL with return type VOID,
                        // tac.op0 is null
                        def.add(tac.op0);
                        tac.op0.lastVisitedBB = bbNum;
                    }
                    
                    if(tac.op0 != null){
                        useDef.add(tac.op0);
                    }

                    break;
                case LOAD_VTBL:
                case DIRECT_CALL:
                case RETURN:
                case LOAD_STR_CONST:
                case LOAD_IMM4:
				/* def op0 */
                    if (tac.op0 != null && tac.op0.lastVisitedBB != bbNum) {  // in DIRECT_CALL with return type VOID,
                        // tac.op0 is null
                        def.add(tac.op0);
                        tac.op0.lastVisitedBB = bbNum;
                    }
                    
                    if(tac.op0 != null){
                        useDef.add(tac.op0);
                    }

                    break;
                case STORE:
				/* use op0 and op1*/
                    if (tac.op0.lastVisitedBB != bbNum) {

                        useIn.put(tac.op0, new TreeSet<Integer>());
                        useIn.get(tac.op0).add(tac.id);
                        // useThrough.add(tac.op0);

                        liveUse.add(tac.op0);
                        tac.op0.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op0)){
                        useIn.get(tac.op0).add(tac.id);
                    }

                    if (tac.op1.lastVisitedBB != bbNum) {

                        useIn.put(tac.op1, new TreeSet<Integer>());
                        useIn.get(tac.op1).add(tac.id);
                        // useThrough.add(tac.op1);

                        liveUse.add(tac.op1);
                        tac.op1.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op1)){
                        useIn.get(tac.op1).add(tac.id);
                    }
                    break;
                case PARM:
				/* use op0 */
                    if (tac.op0.lastVisitedBB != bbNum) {

                        useIn.put(tac.op0, new TreeSet<Integer>());
                        useIn.get(tac.op0).add(tac.id);
                        // useThrough.add(tac.op0);

                        liveUse.add(tac.op0);
                        tac.op0.lastVisitedBB = bbNum;
                    }else if(!useDef.contains(tac.op0)){
                        useIn.get(tac.op0).add(tac.id);
                    }
                    break;
                default:
				/* BRANCH MEMO MARK PARM*/
                    break;
            }
        }
        if (var != null ) {
            if(var.lastVisitedBB != bbNum){
                liveUse.add(var);
                var.lastVisitedBB = bbNum;

                if(!useIn.containsKey(var)){
                    useIn.put(var, new TreeSet<Integer>());
                }
                useIn.get(var).add(endId);
            }else if(this.endKind == EndKind.BY_RETURN){
                if(!useIn.containsKey(var)){
                    useIn.put(var, new TreeSet<Integer>());
                }
                useIn.get(var).add(endId);
            }
        }
        liveIn.addAll(liveUse);
    }

    public void analyzeLiveness() {
        if (tacList == null)
            return;
        Tac tac = tacList;
        for (; tac.next != null; tac = tac.next) ;  //得到最后一个tac语句

        tac.liveOut = new HashSet<Temp>(liveOut);
        if (var != null)
            tac.liveOut.add(var);
        for (; tac != tacList; tac = tac.prev) {
            tac.prev.liveOut = new HashSet<Temp>(tac.liveOut);
            switch (tac.opc) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                case LAND:
                case LOR:
                case GTR:
                case GEQ:
                case EQU:
                case NEQ:
                case LEQ:
                case LES:
				/* use op1 and op2, def op0 */
                    tac.prev.liveOut.remove(tac.op0);
                    tac.prev.liveOut.add(tac.op1);
                    tac.prev.liveOut.add(tac.op2);
                    break;
                case NEG:
                case LNOT:
                case ASSIGN:
                case INDIRECT_CALL:
                case LOAD:
				/* use op1, def op0 */
                    tac.prev.liveOut.remove(tac.op0);
                    tac.prev.liveOut.add(tac.op1);
                    break;
                case LOAD_VTBL:
                    //why ?
                    // System.out.println("VTBL");
                    // break;
                case DIRECT_CALL:
                case RETURN:
                case LOAD_STR_CONST:
                case LOAD_IMM4:
				/* def op0 */
                    tac.prev.liveOut.remove(tac.op0);
                    break;
                case STORE:
				/* use op0 and op1*/
                    tac.prev.liveOut.add(tac.op0);
                    tac.prev.liveOut.add(tac.op1);
                    break;
                case BEQZ:
                case BNEZ:
                case PARM:
				/* use op0 */
                    tac.prev.liveOut.add(tac.op0);
                    break;
                default:
				/* BRANCH MEMO MARK PARM*/
                    break;
            }
        }
    }

    public void analyzeDUlink(){
        if (tacList == null)
            return;
        Tac tac = tacList;
        for (; tac.next != null; tac = tac.next) ;  //得到最后一个tac语句

        if(var != null){
            switch (endKind) {
                case BY_RETURN :
                    if(useOut.containsKey(var)){
                        useOut.get(var).add(endId);
                    }else{
                        useOut.put(var, new TreeSet<Integer>());
                        useOut.get(var).add(endId);
                    }
                    break;
                case BY_BEQZ :
                    if(useOut.containsKey(var)){
                        useOut.get(var).add(endId);
                    }else{
                        useOut.put(var, new TreeSet<Integer>());
                        useOut.get(var).add(endId);
                    }
                    break;
                case BY_BNEZ :
                    if(useOut.containsKey(var)){
                        useOut.get(var).add(endId);
                    }else{
                        useOut.put(var, new TreeSet<Integer>());
                        useOut.get(var).add(endId);
                    }
                    break;
                default:
                    break;
            }
        }

        for (; tac != null; tac = tac.prev) {
            switch (tac.opc) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                case LAND:
                case LOR:
                case GTR:
                case GEQ:
                case EQU:
                case NEQ:
                case LEQ:
                case LES:
                /* use op1 and op2, def op0 */

                    Pair pair = new Pair(tac.id, tac.op0);
                    DUChain.put(pair, new TreeSet<Integer>());
                    if(useOut.containsKey(tac.op0)){
                        DUChain.get(pair).addAll(useOut.get(tac.op0));
                        useOut.get(tac.op0).clear();
                    }

                    if(!useOut.containsKey(tac.op1)){
                        useOut.put(tac.op1, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op1).add(tac.id);

                    if(!useOut.containsKey(tac.op2)){
                        useOut.put(tac.op2, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op2).add(tac.id);
                    break;
                case NEG:
                case LNOT:
                case ASSIGN:
                case INDIRECT_CALL:
                case LOAD:
				/* use op1, def op0 */
                    if(!useOut.containsKey(tac.op1)){
                        useOut.put(tac.op1, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op1).add(tac.id);

                    if(tac.op0 != null){
                        Pair pair1 = new Pair(tac.id, tac.op0);
                        DUChain.put(pair1, new TreeSet<Integer>());
                        if(useOut.containsKey(tac.op0)){
                            DUChain.get(pair1).addAll(useOut.get(tac.op0));
                            useOut.get(tac.op0).clear();
                        }
                    }
                    break;
                case LOAD_VTBL:
                case DIRECT_CALL:
                case RETURN:
                case LOAD_STR_CONST:
                case LOAD_IMM4:
                /* def op0 */
                    if(tac.op0 != null){
                        Pair pair2 = new Pair(tac.id, tac.op0);
                        DUChain.put(pair2, new TreeSet<Integer>());
                        if(useOut.containsKey(tac.op0)){
                            DUChain.get(pair2).addAll(useOut.get(tac.op0));
                            useOut.get(tac.op0).clear();
                        }
                    }
                    break;
                case STORE:
				/* use op0 and op1*/
                    if(!useOut.containsKey(tac.op0)){
                        useOut.put(tac.op0, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op0).add(tac.id);

                    if(!useOut.containsKey(tac.op1)){
                        useOut.put(tac.op1, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op1).add(tac.id);
                    break;
                case BEQZ:
                case BNEZ:
                case PARM:
				/* use op0 */
                    if(!useOut.containsKey(tac.op0)){
                        useOut.put(tac.op0, new TreeSet<Integer>());
                    }
                    useOut.get(tac.op0).add(tac.id);
                    break;
                default:
				/* BRANCH MEMO MARK PARM*/
                    break;
            }
        }
    }

    public void printTo(PrintWriter pw) {
        pw.println("BASIC BLOCK " + bbNum + " : ");
        for (Tac t = tacList; t != null; t = t.next) {
            pw.println("    " + t);
        }
        switch (endKind) {
            case BY_BRANCH:
                pw.println("END BY BRANCH, goto " + next[0]);
                break;
            case BY_BEQZ:
                pw.println("END BY BEQZ, if " + var.name + " = ");
                pw.println("    0 : goto " + next[0] + "; 1 : goto " + next[1]);
                break;
            case BY_BNEZ:
                pw.println("END BY BGTZ, if " + var.name + " = ");
                pw.println("    1 : goto " + next[0] + "; 0 : goto " + next[1]);
                break;
            case BY_RETURN:
                if (var != null) {
                    pw.println("END BY RETURN, result = " + var.name);
                } else {
                    pw.println("END BY RETURN, void result");
                }
                break;
        }
    }

    public void printLivenessTo(PrintWriter pw) {
        pw.println("BASIC BLOCK " + bbNum + " : ");
        pw.println("  Def     = " + toString(def));
        pw.println("  liveUse = " + toString(liveUse));
        pw.println("  liveIn  = " + toString(liveIn));
        pw.println("  liveOut = " + toString(liveOut));

        for (Tac t = tacList; t != null; t = t.next) {
            pw.println("    " + t + " " + toString(t.liveOut));
        }

        switch (endKind) {
            case BY_BRANCH:
                pw.println("END BY BRANCH, goto " + next[0]);
                break;
            case BY_BEQZ:
                pw.println("END BY BEQZ, if " + var.name + " = ");
                pw.println("    0 : goto " + next[0] + "; 1 : goto " + next[1]);
                break;
            case BY_BNEZ:
                pw.println("END BY BGTZ, if " + var.name + " = ");
                pw.println("    1 : goto " + next[0] + "; 0 : goto " + next[1]);
                break;
            case BY_RETURN:
                if (var != null) {
                    pw.println("END BY RETURN, result = " + var.name);
                } else {
                    pw.println("END BY RETURN, void result");
                }
                break;
        }
    }

    public void printDUChainTo(PrintWriter pw) {
        pw.println("BASIC BLOCK " + bbNum + " : ");

        for (Tac t = tacList; t != null; t = t.next) {
            pw.print(t.id + "\t" + t);

            Pair pair = null;
            switch (t.opc) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                case MOD:
                case LAND:
                case LOR:
                case GTR:
                case GEQ:
                case EQU:
                case NEQ:
                case LEQ:
                case LES:
                case NEG:
                case LNOT:
                case ASSIGN:
                case INDIRECT_CALL:
                case LOAD:
                case LOAD_VTBL:
                case DIRECT_CALL:
                case RETURN:
                case LOAD_STR_CONST:
                case LOAD_IMM4:
                    if (t.op0 != null) {
                        pair = new Pair(t.id, t.op0);
                    }
                    break;
                case STORE:
                case BEQZ:
                case BNEZ:
                case PARM:
                    break;
                default:
				/* BRANCH MEMO MARK PARM */
                    break;
            }

            if (pair == null) {
                pw.println();
            } else {
                pw.print(" [ ");
                if (pair != null) {
                    Set<Integer> locations = DUChain.get(pair);
                    if (locations != null) {
                        for (Integer loc : locations) {
                            pw.print(loc + " ");
                        }
                    }
                }
                pw.println("]");
            }
        }

        pw.print(endId + "\t");
        switch (endKind) {
            case BY_BRANCH:
                pw.println("END BY BRANCH, goto " + next[0]);
                break;
            case BY_BEQZ:
                pw.println("END BY BEQZ, if " + var.name + " = ");
                pw.println("\t    0 : goto " + next[0] + "; 1 : goto " + next[1]);
                break;
            case BY_BNEZ:
                pw.println("END BY BGTZ, if " + var.name + " = ");
                pw.println("\t    1 : goto " + next[0] + "; 0 : goto " + next[1]);
                break;
            case BY_RETURN:
                if (var != null) {
                    pw.println("END BY RETURN, result = " + var.name);
                } else {
                    pw.println("END BY RETURN, void result");
                }
                break;
        }
    }

    public String toString(Set<Temp> set) {
        StringBuilder sb = new StringBuilder("[ ");
        for (Temp t : set) {
            sb.append(t.name + " ");
        }
        sb.append(']');
        return sb.toString();
    }

    public void insertBefore(Tac insert, Tac base) {
        if (base == tacList) {
            tacList = insert;
        } else {
            base.prev.next = insert;
        }
        insert.prev = base.prev;
        base.prev = insert;
        insert.next = base;
    }

    public void insertAfter(Tac insert, Tac base) {
        if (tacList == null) {
            tacList = insert;
            insert.next = null;
            return;
        }
        if (base.next != null) {
            base.next.prev = insert;
        }
        insert.prev = base;
        insert.next = base.next;
        base.next = insert;
    }

    public void appendAsm(Asm asm) {
        asms.add(asm);
    }

    public List<Asm> getAsms() {
        return asms;
    }
}
