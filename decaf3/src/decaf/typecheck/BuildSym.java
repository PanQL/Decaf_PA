package decaf.typecheck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import decaf.Driver;
import decaf.tree.Tree;
import decaf.error.BadArrElementError;
import decaf.error.BadInheritanceError;
import decaf.error.BadOverrideError;
import decaf.error.BadVarTypeError;
import decaf.error.ClassNotFoundError;
import decaf.error.DecafError;
import decaf.error.DeclConflictError;
import decaf.error.NoMainClassError;
import decaf.error.OverridingVarError;
import decaf.error.*;
import decaf.scope.ClassScope;
import decaf.scope.GlobalScope;
import decaf.scope.LocalScope;
import decaf.scope.ScopeStack;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Symbol;
import decaf.symbol.Variable;
import decaf.type.BaseType;
import decaf.type.FuncType;
import decaf.type.ClassType;
import decaf.type.ArrayType;
import decaf.type.Type;

public class BuildSym extends Tree.Visitor {

	private ScopeStack table;

	public static Set<String> sealedClasses;

	private void issueError(DecafError error) {
		Driver.getDriver().issueError(error);
	}

	public BuildSym(ScopeStack table) {
		this.table = table;
	}

	public static void buildSymbol(Tree.TopLevel tree) {
		new BuildSym(Driver.getDriver().getTable()).visitTopLevel(tree);
	}

	// root
	@Override
	public void visitTopLevel(Tree.TopLevel program) {
		sealedClasses = new HashSet<String>();

		program.globalScope = new GlobalScope();
		table.open(program.globalScope);
		for (Tree.ClassDef cd : program.classes) {
			Class c = new Class(cd.name, cd.parent, cd.getLocation());
			Class earlier = table.lookupClass(cd.name);
			if (earlier != null) {
				issueError(new DeclConflictError(cd.getLocation(), cd.name,
						earlier.getLocation()));
			} else {
				table.declare(c);
			}
			if(cd.isSealed){
				sealedClasses.add(cd.name);
			}
			cd.symbol = c;
		}

		for (Tree.ClassDef cd : program.classes) {
			Class c = cd.symbol;
			if (cd.parent != null && c.getParent() == null) {
				issueError(new ClassNotFoundError(cd.getLocation(), cd.parent));
				c.dettachParent();
			}
			if (calcOrder(c) <= calcOrder(c.getParent())) {
				issueError(new BadInheritanceError(cd.getLocation()));
				c.dettachParent();
			}
			if(sealedClasses.contains(cd.parent)){
				issueError(new BadSealedInherError(cd.getLocation()));
			}
		}

		for (Tree.ClassDef cd : program.classes) {
			cd.symbol.createType();
		}

		for (Tree.ClassDef cd : program.classes) {
			cd.accept(this);
			if (Driver.getDriver().getOption().getMainClassName().equals(
					cd.name)) {
				program.main = cd.symbol;
			}
		}

		for (Tree.ClassDef cd : program.classes) {
			checkOverride(cd.symbol);
		}

		if (!isMainClass(program.main)) {
			issueError(new NoMainClassError(Driver.getDriver().getOption()
					.getMainClassName()));
		}
		table.close();
	}

	// visiting declarations
	@Override
	public void visitClassDef(Tree.ClassDef classDef) {
		table.open(classDef.symbol.getAssociatedScope());
		for (Tree f : classDef.fields) {
			f.accept(this);
		}
		table.close();
	}

	@Override
	public void visitVarDef(Tree.VarDef varDef) {
		varDef.type.accept(this);
		if (varDef.type.type.equal(BaseType.VOID)) {
			issueError(new BadVarTypeError(varDef.getLocation(), varDef.name));
			// for argList
			varDef.symbol = new Variable(".error", BaseType.ERROR, varDef
					.getLocation());
			return;
		}
		Variable v = new Variable(varDef.name, varDef.type.type, 
				varDef.getLocation());
		Symbol sym = table.lookup(varDef.name, true);
		if (sym != null) {
			if (table.getCurrentScope().equals(sym.getScope())) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else if ((sym.getScope().isFormalScope() || sym.getScope()
					.isLocalScope())) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else {
				table.declare(v);
			}
		} else {
			table.declare(v);
		}
		varDef.symbol = v;
	}

	@Override
	public void visitMethodDef(Tree.MethodDef funcDef) {
		funcDef.returnType.accept(this);
		Function f = new Function(funcDef.statik, funcDef.name,
				funcDef.returnType.type, funcDef.body, funcDef.getLocation());
		funcDef.symbol = f;
		Symbol sym = table.lookup(funcDef.name, false);
		if (sym != null) {
			issueError(new DeclConflictError(funcDef.getLocation(),
					funcDef.name, sym.getLocation()));
		} else {
			table.declare(f);
		}
		table.open(f.getAssociatedScope());
		for (Tree.VarDef d : funcDef.formals) {
			d.accept(this);
			f.appendParam(d.symbol);
		}
		funcDef.body.accept(this);
		table.close();
	}

	// visiting types
	@Override
	public void visitTypeIdent(Tree.TypeIdent type) {
		switch (type.typeTag) {
		case Tree.VOID:
			type.type = BaseType.VOID;
			break;
		case Tree.INT:
			type.type = BaseType.INT;
			break;
		case Tree.BOOL:
			type.type = BaseType.BOOL;
			break;
		default:
			type.type = BaseType.STRING;
		}
	}

	@Override
	public void visitTypeClass(Tree.TypeClass typeClass) {
		Class c = table.lookupClass(typeClass.name);
		if (c == null) {
			issueError(new ClassNotFoundError(typeClass.getLocation(),
					typeClass.name));
			typeClass.type = BaseType.ERROR;
		} else {
			typeClass.type = c.getType();
		}
	}

	@Override
	public void visitTypeArray(Tree.TypeArray typeArray) {
		typeArray.elementType.accept(this);
		if (typeArray.elementType.type.equal(BaseType.ERROR)) {
			typeArray.type = BaseType.ERROR;
		} else if (typeArray.elementType.type.equal(BaseType.VOID)) {
			issueError(new BadArrElementError(typeArray.getLocation()));
			typeArray.type = BaseType.ERROR;
		} else {
			typeArray.type = new decaf.type.ArrayType(
					typeArray.elementType.type);
		}
	}

	// for VarDecl in LocalScope
	@Override
	public void visitBlock(Tree.Block block) {
		block.associatedScope = new LocalScope(block);
		table.open(block.associatedScope);
		for (Tree s : block.block) {
			s.accept(this);
		}
		table.close();
	}

	@Override
	public void visitIfSubStmt(Tree.IfSubStmt that) {
		if(that.trueBranch.tag == Tree.BLOCK){
			Tree.Block block = (Tree.Block)(that.trueBranch);
			block.associatedScope = new LocalScope(block);
			table.open(block.associatedScope);
			for(Tree s : block.block){
				s.accept(this);
			}
			table.close();
		}
	}

	@Override
	public void visitIfBranches(Tree.GuardedStmt that) {
		for(Tree.Expr e: that.branches){
			e.accept(this);
		}
	}

	@Override
	public void visitForLoop(Tree.ForLoop forLoop) {
		if (forLoop.loopBody != null) {
			forLoop.loopBody.accept(this);
		}
	}

	@Override
	public void visitIf(Tree.If ifStmt) {
		if (ifStmt.trueBranch != null) {
			ifStmt.trueBranch.accept(this);
		}
		if (ifStmt.falseBranch != null) {
			ifStmt.falseBranch.accept(this);
		}
	}

	@Override
	public void visitWhileLoop(Tree.WhileLoop whileLoop) {
		if (whileLoop.loopBody != null) {
			whileLoop.loopBody.accept(this);
		}
	}


	@Override
	public void visitVarIdent(Tree.VarIdent that) {

		Variable v = new Variable(that.name, BaseType.VAR, 
				that.getLocation());
		Symbol sym = table.lookup(that.name, true);
		//变量重复定义的问题
		if (sym != null) {
			if (table.getCurrentScope().equals(sym.getScope())) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
				that.type = BaseType.ERROR;
			} else if (sym.getScope().isFormalScope() && table.getCurrentScope().isLocalScope()) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
				that.type = BaseType.ERROR;
			} else {
				table.declare(v);
			}
		} else {
			table.declare(v);
		}
		that.type = BaseType.VAR;
		that.symbol = v;
	}

	@Override
	public void visitAssign(Tree.Assign that) {
		that.left.accept(this);
		that.expr.accept(this);
	}

	@Override
	public void visitForeachStmt(Tree.ForeachStmt that) {
		that.associatedScope = new LocalScope(that.block);
		table.open(that.associatedScope);
		that.boundVariable.accept(this);
		that.arr.accept(this);
		if(that.judge != null){
			that.judge.accept(this);
		}
		that.block.accept(this);
		table.close();

	}

	@Override
	public void visitBoundVariable(Tree.BoundVariable that) {
		that.type.accept(this);
		if (that.type.type.equal(BaseType.VOID)) {
			issueError(new BadVarTypeError(that.getLocation(), that.name));
			// for argList
			that.symbol = new Variable(".error", BaseType.ERROR,that
					.getLocation());
			return;
		}
		Variable v = new Variable(that.name, that.type.type, 
				that.getLocation());
		Symbol sym = table.lookup(that.name, true);
		//变量重复定义的问题
		if (sym != null) {
			if (table.getCurrentScope().equals(sym.getScope())) {	//当前作用域内已经定义了这个变量
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else if (sym.getScope().isFormalScope() && table.getCurrentScope().isLocalScope()) {
				issueError(new DeclConflictError(v.getLocation(), v.getName(),
						sym.getLocation()));
			} else {
				table.declare(v);
			}
		} else {
			table.declare(v);
		}
		that.symbol = v;
	}

	@Override
	public void visitTypeVar(Tree.TypeVar that) {
		that.type = BaseType.VAR;
	}

	@Override
	public void visitForeachBlock(Tree.ForeachBlock that) {
		for(Tree t:that.block){
			t.accept(this);
		}
	}

	private int calcOrder(Class c) {
		if (c == null) {
			return -1;
		}
		if (c.getOrder() < 0) {
			c.setOrder(0);
			c.setOrder(calcOrder(c.getParent()) + 1);
		}
		return c.getOrder();
	}

	private void checkOverride(Class c) {
		if (c.isCheck()) {
			return;
		}
		Class parent = c.getParent();
		if (parent == null) {
			return;
		}
		checkOverride(parent);

		ClassScope parentScope = parent.getAssociatedScope();
		ClassScope subScope = c.getAssociatedScope();
		table.open(parentScope);
		Iterator<Symbol> iter = subScope.iterator();
		while (iter.hasNext()) {
			Symbol suspect = iter.next();
			Symbol sym = table.lookup(suspect.getName(), true);
			if (sym != null && !sym.isClass()) {
				if ((suspect.isVariable() && sym.isFunction())
						|| (suspect.isFunction() && sym.isVariable())) {
					issueError(new DeclConflictError(suspect.getLocation(),
							suspect.getName(), sym.getLocation()));
					iter.remove();
				} else if (suspect.isFunction()) {
					if (((Function) suspect).isStatik()
							|| ((Function) sym).isStatik()) {
						issueError(new DeclConflictError(suspect.getLocation(),
								suspect.getName(), sym.getLocation()));
						iter.remove();
					} else if (!suspect.getType().compatible(sym.getType())) {
						issueError(new BadOverrideError(suspect.getLocation(),
								suspect.getName(),
								((ClassScope) sym.getScope()).getOwner()
										.getName()));
						iter.remove();
					}
				} else if (suspect.isVariable()) {
					issueError(new OverridingVarError(suspect.getLocation(),
							suspect.getName()));
					iter.remove();
				}
			}
		}
		table.close();
		c.setCheck(true);
	}

	private boolean isMainClass(Class c) {
		if (c == null) {
			return false;
		}
		table.open(c.getAssociatedScope());
		Symbol main = table.lookup(Driver.getDriver().getOption()
				.getMainFuncName(), false);
		if (main == null || !main.isFunction()) {
			return false;
		}
		((Function) main).setMain(true);
		FuncType type = (FuncType) main.getType();
		return type.getReturnType().equal(BaseType.VOID)
				&& type.numOfParams() == 0 && ((Function) main).isStatik();
	}
}
