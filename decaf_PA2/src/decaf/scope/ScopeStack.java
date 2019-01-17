package decaf.scope;

import java.util.ListIterator;
import java.util.Stack;

import decaf.Location;
import decaf.scope.Scope.Kind;
import decaf.symbol.Class;
import decaf.symbol.Symbol;

public class ScopeStack {
	private Stack<Scope> scopeStack = new Stack<Scope>();
	
	private GlobalScope globalScope;

	//through代表是否搜索整个栈，name代表查找的符号的名字
	public Symbol lookup(String name, boolean through) {
		if (through) {	//through为真，搜索整个栈，返回找到的第一个名字为name的symbol,找不到则返回空 
			ListIterator<Scope> iter = scopeStack.listIterator(scopeStack
					.size());
			while (iter.hasPrevious()) {
				Symbol symbol = iter.previous().lookup(name);
				if (symbol != null) {
					return symbol;
				}
			}
			return null;
		} else {	//through为假，返回搜索栈顶的scope的结果
			return scopeStack.peek().lookup(name);
		}
	}

	//搜索在当前位置之前的名字为name的symbol
	public Symbol lookupBeforeLocation(String name, Location loc) {
		ListIterator<Scope> iter = scopeStack.listIterator(scopeStack.size());
		while (iter.hasPrevious()) {
			Scope scope = iter.previous();
			Symbol symbol = scope.lookup(name);
			if (symbol != null) {
				if (scope.isLocalScope()
						&& symbol.getLocation().compareTo(loc) > 0) {
					continue;
				}
				return symbol;
			}
		}
		return null;
	}

	public void declare(Symbol symbol) {
		scopeStack.peek().declare(symbol);
	}

	public void open(Scope scope) {
		switch (scope.getKind()) {
		case GLOBAL:
			globalScope = (GlobalScope)scope;
			break;
		case CLASS:
			ClassScope cs = ((ClassScope) scope).getParentScope();
			if (cs != null) {
				open(cs);
			}
			break;
		}
		scopeStack.push(scope);
	}

	public void close() {
		Scope scope = scopeStack.pop();
		if (scope.isClassScope()) {
			for (int n = scopeStack.size() - 1; n > 0; n--) {
				scopeStack.pop();
			}
		}
	}

	public Scope lookForScope(Kind kind) {
		ListIterator<Scope> iter = scopeStack.listIterator(scopeStack.size());
		while (iter.hasPrevious()) {
			Scope scope = iter.previous();
			if (scope.getKind() == kind) {
				return scope;
			}
		}
		return null;
	}

	public Scope getCurrentScope() {
		return scopeStack.peek();
	}

	public Class lookupClass(String name) {
		return (Class) globalScope.lookup(name);
	}
}
