package decaf.symbol;

import java.util.Iterator;

import decaf.Driver;
import decaf.Location;
import decaf.backend.OffsetCounter;
import decaf.scope.ClassScope;
import decaf.scope.GlobalScope;
import decaf.tac.Label;
import decaf.tac.VTable;
import decaf.type.ClassType;

public class Class extends Symbol {

	private String parentName;

	private ClassScope associatedScope;

	private int order;

	private boolean check;

	private int numNonStaticFunc;

	private int numVar;

	private int size;

	private VTable vtable;

	private Label newFuncLabel;

	public Label getNewFuncLabel() {
		return newFuncLabel;
	}

	public void setNewFuncLabel(Label newFuncLabel) {
		this.newFuncLabel = newFuncLabel;
	}

	public VTable getVtable() {
		return vtable;
	}

	public void setVtable(VTable vtable) {
		this.vtable = vtable;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumNonStaticFunc() {
		return numNonStaticFunc;
	}

	public void setNumNonStaticFunc(int numNonStaticFunc) {
		this.numNonStaticFunc = numNonStaticFunc;
	}

	public int getNumVar() {
		return numVar;
	}

	public void setNumVar(int numVar) {
		this.numVar = numVar;
	}

	public Class(String name, String parentName, Location location) {
		this.name = name;
		this.parentName = parentName;
		this.location = location;
		this.order = -1;
		this.check = false;
		this.numNonStaticFunc = -1;
		this.numVar = -1;
		this.associatedScope = new ClassScope(this);
	}

	public void createType() {
		Class p = getParent();
		if (p == null) {
			type = new ClassType(this, null);
		} else {
			if (p.getType() == null) {
				p.createType();
			}
			type = new ClassType(this, (ClassType) p.getType());
		}
	}

	@Override
	public ClassType getType() {
		if (type == null) {
			createType();
		}
		return (ClassType) type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(location + " -> class " + name);
		if (parentName != null) {
			sb.append(" : " + parentName);
		}
		return sb.toString();
	}

	public ClassScope getAssociatedScope() {
		return associatedScope;
	}

	public Class getParent() {
		return Driver.getDriver().getTable().lookupClass(parentName);
	}

	@Override
	public boolean isClass() {
		return true;
	}

	@Override
	public GlobalScope getScope() {
		return (GlobalScope) definedIn;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void dettachParent() {
		parentName = null;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void resolveFieldOrder() {
		if (numNonStaticFunc >= 0 && numVar >= 0) {		//已经排列过顺序了，直接返回
			return;
		}
		//查看是否有超类，如果有的话，先处理超类中的field排列关系
		if (parentName != null) {	 
			Class parent = getParent();
			parent.resolveFieldOrder();	
			numNonStaticFunc = parent.numNonStaticFunc;		//如果有超类，直接在超类的基础上进行num的分配
			numVar = parent.numVar;
			size = parent.size;
		} else {
			//如果没有超类，则从0开始
			numNonStaticFunc = 0;
			numVar = 0;
			size = OffsetCounter.POINTER_SIZE;
		}

		//获取超类的符号表
		ClassScope ps = associatedScope.getParentScope();
		//遍历当前类的符号
		Iterator<Symbol> iter = associatedScope.iterator();
		while (iter.hasNext()) {
			Symbol sym = iter.next();
			if (sym.isVariable()) {		//设定变量的顺序，同时确定当前类的表的大小
				sym.setOrder(numVar++);
				size += OffsetCounter.WORD_SIZE;
			} else if (!((Function) sym).isStatik()) {	//如果当前这个函数表项不是静态的
				if (ps == null) {
					sym.setOrder(numNonStaticFunc++);
				} else {
					Symbol s = ps.lookupVisible(sym.name);
					if (s == null) {	//如果这个函数在超类中不存在，则分配新的num
						sym.setOrder(numNonStaticFunc++);
					} else {	//否则，这个函数是继承来的，可以直接设置在超类中设置好的order
						sym.setOrder(s.getOrder());
					}
				}
			}
		}
	}

	@Override
	public boolean isFunction() {
		return false;
	}

	@Override
	public boolean isVariable() {
		return false;
	}
}
