package decaf.dataflow;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;

import decaf.tac.Functy;
import decaf.tac.Tac;
import decaf.tac.Tac.Kind;
import decaf.tac.Temp;

public class FlowGraph implements Iterable<BasicBlock> {

    private Functy functy;  //当前对应的函数表项

    private List<BasicBlock> bbs;   //函数中的基本块集合

    public FlowGraph(Functy func) {
        this.functy = func;
        deleteMemo(func);
        bbs = new ArrayList<BasicBlock>();
        markBasicBlocks(func.head); //标记基本块
        gatherBasicBlocks(func.head);   //聚集基本块
        simplify(); //化简
        for (BasicBlock bb : bbs) {
            bb.allocateTacIds();
        }
        analyzeLiveness();  //分析活跃变量
        for (BasicBlock bb : bbs) {
            bb.analyzeLiveness();   //在每个块中分析活跃变量
        }
        analyzeDUChain();
        for (BasicBlock bb : bbs){
            bb.analyzeDUlink();
        }
    }

    private void deleteMemo(Functy func) {
        //清除开头的注释
        while (func.head != null && func.head.opc == Tac.Kind.MEMO) {
            func.head = func.head.next;
        }
        //清除代码中夹杂的注释
        for (Tac t = func.head; t != null; t = t.next) {
            if (t.opc == Tac.Kind.MEMO) {
                if (t.prev != null) {
                    t.prev.next = t.next;
                }
                if (t.next != null) {
                    t.next.prev = t.prev;
                }
            }
        }

    }

    private void markBasicBlocks(Tac t) {
        int index = -1;
        boolean atStart = false;

        for (; t != null; t = t.next) {
            t.bbNum = index;
            switch (t.opc) {
                //如果当前这条tac语句的操作类型是RETURE、BRANCH、BEQZ、BNEZ，那么它是一个基本块的结尾语句
                case RETURN:
                case BRANCH:
                case BEQZ:
                case BNEZ:
                    index++;
                    atStart = true;
                    break;
                case MARK:  //如果当前这条tac语句是一个label，那么它可能是一个基本块的开头
                    if (!t.label.target) {  //如果当前标签不是某个跳转语句的目标，则删除该标签
                        if (t.prev != null) {   //如果它的前一个语句是非空，直接删除当前标签
                            t.prev.next = t.next;
                        } else {    //否则，这一标签在函数头部。删除函数头部的标签
                            functy.head = t.next;
                        }
                        if (t.next != null) {   //消除后继tac语句中指向这一标签的链接
                            t.next.prev = t.prev;
                        }
                    } else {    //否则，该标签是某次跳转的目标
                        if (!atStart) { //如果该tac块之前不是处于最开始的位置，则置atStart为真
                            index++;    //基本块标号增加
                            t.bbNum = index;    //设置tac语句的基本块标号
                            atStart = true;     //下一条语句作为基本块的开头语句
                        }
                    }   //注意这里会将几个连续的label看作只有一个，但是原有的跳转关系是不变的。
                    break;
                default:
                    atStart = false;
                    break;
            }
        }

    }

    private void gatherBasicBlocks(Tac start) {
        BasicBlock current = null;
        Tac nextStart = null;
        Tac end = null;

        while (start != null && start.bbNum < 0) {  //首先忽略首基本块
            start = start.next;
        }

        for (; start != null; start = nextStart) {
            int bbNum = start.bbNum;
            while (start != null && start.opc == Tac.Kind.MARK) {
                start = start.next;
            }

            if (start == null) {    //只有label，没有具体的语句,tarList为空
                current = new BasicBlock();
                current.bbNum = bbNum;
                current.tacList = null;
                current.endKind = BasicBlock.EndKind.BY_RETURN;
                nextStart = null;
            } else { 
                start.prev = null;  //切断基本块的语句的前向指针,
                end = start;
                while (end.next != null && end.next.bbNum == start.bbNum) { 
                    //遇到同一基本块的号码则end指针后移
                    end = end.next;
                }
                nextStart = end.next;   //设置end指针的后一条语句为下一个基本块的开头
                current = new BasicBlock();
                current.bbNum = bbNum;
                current.tacList = start;
                switch (end.opc) {  //判断当前基本块的结束语句类型
                    case RETURN:
                        current.endKind = BasicBlock.EndKind.BY_RETURN;
                        current.var = end.op0;
                        current.next[0] = current.next[1] = -1; // Special case.
                        end = end.prev;
                        break;
                    case BRANCH:
                        current.endKind = BasicBlock.EndKind.BY_BRANCH;
                        current.next[0] = current.next[1] = end.label.where.bbNum;  //跳转的下一个基本块号码
                        end = end.prev;
                        break;
                    case BEQZ:
                    case BNEZ:
                        current.endKind = end.opc == Kind.BEQZ ? BasicBlock.EndKind.BY_BEQZ
                                : BasicBlock.EndKind.BY_BNEZ;
                        current.var = end.op0;
                        current.next[0] = end.label.where.bbNum;
                        current.next[1] = nextStart.bbNum;
                        end = end.prev;
                        break;
                    default:
                        if (nextStart == null) {
                            current.endKind = BasicBlock.EndKind.BY_RETURN;
                        } else {
                            current.endKind = BasicBlock.EndKind.BY_BRANCH;
                            current.next[0] = current.next[1] = nextStart.bbNum;
                        }
                }
                if (end == null) {
                    current.tacList = null;
                } else {
                    end.next = null;    //切断基本块最后一个语句的后向指针
                }
            }
            bbs.add(current);
        }
    }

    @Override
    public Iterator<BasicBlock> iterator() {
        return bbs.iterator();
    }

    public BasicBlock getBlock(int i) {
        return bbs.get(i);
    }

    public int size() {
        return bbs.size();
    }

    public void analyzeLiveness() {
        for (BasicBlock bb : bbs) {
            bb.computeDefAndLiveUse();
        }
        boolean changed = true;
        do {
            changed = false;
            for (BasicBlock bb : bbs) {
                for (int i = 0; i < 2; i++) {
                    if (bb.next[i] >= 0) { // Not RETURN
                        bb.liveOut.addAll(bbs.get(bb.next[i]).liveIn);
                    }
                }
                bb.liveOut.removeAll(bb.def);
                if (bb.liveIn.addAll(bb.liveOut))
                    changed = true;
                for (int i = 0; i < 2; i++) {
                    if (bb.next[i] >= 0) { // Not RETURN
                        bb.liveOut.addAll(bbs.get(bb.next[i]).liveIn);
                    }
                }
            }
        } while (changed);
    }

    public void analyzeDUChain(){

        boolean changed = true;
        do {
            changed = false;
            for (BasicBlock bb : bbs) {
                for (int i = 0; i < 2; i++) {
                    if (bb.next[i] < 0)continue;
                    for(Temp temp : bbs.get(bb.next[i]).useIn.keySet()){
                        if (bb.useDef.contains(temp))continue;
                        if(bb.useIn.containsKey(temp)){
                            if(bb.useIn.get(temp).addAll(bbs.get(bb.next[i]).useIn.get(temp))){
                                changed = true;
                            }
                        }else{
                            changed = true;
                            bb.useIn.put(temp, new TreeSet<Integer>());
                            bb.useIn.get(temp).addAll(bbs.get(bb.next[i]).useIn.get(temp));
                        }
                    }
                }
            }
        } while (changed);

        for (BasicBlock bb : bbs){
            if(bb.next[0] >= 0){
                for(Temp temp : bbs.get(bb.next[0]).useIn.keySet()){
                    bb.useOut.put(temp, new TreeSet<Integer>());
                    bb.useOut.get(temp).addAll(bbs.get(bb.next[0]).useIn.get(temp));
                }
            }
            if(bb.next[1] >= 0){
                for(Temp temp : bbs.get(bb.next[1]).useIn.keySet()){
                    if(bb.useOut.containsKey(temp)){
                        bb.useOut.get(temp).addAll(bbs.get(bb.next[1]).useIn.get(temp));
                    }else{
                        bb.useOut.put(temp, new TreeSet<Integer>());
                        bb.useOut.get(temp).addAll(bbs.get(bb.next[1]).useIn.get(temp));
                    }
                }
            }
        }
    }

    public void simplify() {
        getBlock(0).inDegree = 1;
        for (BasicBlock bb : bbs) {
            switch (bb.endKind) {
                case BY_BEQZ:
                case BY_BNEZ:
                    getBlock(bb.next[1]).inDegree++;
                case BY_BRANCH:
                    getBlock(bb.next[0]).inDegree++;
                    break;
            }
        }
        for (BasicBlock bb : bbs) {
            if (bb.inDegree <= 0
                    || (bb.endKind == BasicBlock.EndKind.BY_BRANCH && bb.tacList == null)) {
                bb.cancelled = true;
            }
        }
        for (BasicBlock bb : bbs) {
            if (bb.cancelled || bb.endKind == BasicBlock.EndKind.BY_RETURN) {
                continue;
            }
            BasicBlock trace = getBlock(bb.next[0]);
            while (trace.cancelled) {
                trace = getBlock(trace.next[0]);
            }
            bb.next[0] = trace.bbNum;

            if (bb.endKind == BasicBlock.EndKind.BY_BEQZ
                    || bb.endKind == BasicBlock.EndKind.BY_BNEZ) {
                trace = getBlock(bb.next[1]);
                while (trace.cancelled) {
                    trace = getBlock(trace.next[0]);
                }
                bb.next[1] = trace.bbNum;

                if (bb.next[0] == bb.next[1]) {
                    bb.endKind = BasicBlock.EndKind.BY_BRANCH;
                }
            } else {
                bb.next[1] = bb.next[0];
            }
        }

        Map<Integer, Integer> newBBNum = new HashMap<Integer, Integer>();
        int sz = 0;
        int i = 0;
        for (BasicBlock bb : bbs) {
            if (!bb.cancelled) {
                newBBNum.put(i, sz);
                if (i > sz) {
                    bbs.set(sz, getBlock(i));
                }
                sz++;
            }
            i++;
        }
        bbs = bbs.subList(0, sz);
        for (BasicBlock bb : bbs) {
            bb.bbNum = newBBNum.get(bb.bbNum);
            if (bb.endKind != BasicBlock.EndKind.BY_RETURN) {
                bb.next[0] = newBBNum.get(bb.next[0]);
                bb.next[1] = newBBNum.get(bb.next[1]);
            }
        }
    }

    public void printTo(PrintWriter pw) {
        pw.println("FUNCTION " + functy.label.name + " : ");
        for (BasicBlock bb : bbs) {
            bb.printTo(pw);
        }
    }

    public void printLivenessTo(PrintWriter pw) {
        pw.println("FUNCTION " + functy.label.name + " : ");
        for (BasicBlock bb : bbs) {
            bb.printLivenessTo(pw);
        }
    }

    public void printDUChainTo(PrintWriter pw) {
        pw.println("FUNCTION " + functy.label.name + " : ");
        for (BasicBlock bb : bbs) {
            bb.printDUChainTo(pw);
        }
    }

    public Functy getFuncty() {
        return functy;
    }
}
