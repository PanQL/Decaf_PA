package decaf.frontend;

import decaf.Driver;
import decaf.error.MsgError;
import decaf.tree.Tree;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Parser extends Table {
    /**
     * Lexer.
     */
    private Lexer lexer;

    /**
     * Set lexer.
     *
     * @param lexer the lexer.
     */
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    /**
     * Lookahead token (can be eof/eos).
     */
    public int lookahead = eof;

    /**
     * Undefined token (when lexer fails).
     */
    private static final int undefined_token = -2;

    /**
     * Lexer will write this when a token is parsed.
     */
    public SemValue val = new SemValue();

    /**
     * Helper function.
     * Create a `MsgError` with `msg` and append it to driver.
     *
     * @param msg the message string.
     */
    private void issueError(String msg) {
        Driver.getDriver().issueError(new MsgError(lexer.getLocation(), msg));
    }

    /**
     * Error handler.
     */
    private void error() {
        issueError("syntax error");
    }

    /**
     * Lexer caller.
     *
     * @return the token parsed by the lexer. If `undefined_token` is returned, then lexer failed.
     */
    private int lex() {
        int token = undefined_token;
        try {
            token = lexer.yylex();
        } catch (Exception e) {
            issueError("lexer error: " + e.getMessage());
        }
        return token;
    }

    /**
     * Parse function for each non-terminal with error recovery.
     * 对每个非终结符带有错误恢复的解析函数
     * NOTE: the current implementation is buggy and may throw NullPointerException.
     * 当前这个版本有bug并且可能抛出空指针异常
     * TODO: find a correct implementation for error recovery!
     * 为错误恢复找到办法
     * TODO: You are free to change the method body as you wish, but not the interface!
     * 可以自由改变类的方法，但请不要改变接口
     * @param symbol the non-terminal to be parsed.
     * @return the parsed value of `symbol` if parsing succeeded, otherwise `null`.
     */
    private SemValue parse(int symbol, Set<Integer> follow) {

        Set<Integer> begin = beginSet(symbol);
        Set<Integer> end = followSet(symbol);
        Set<Integer> followCopy = new HashSet<>(follow);
        followCopy.addAll(end);

        if (!begin.contains(lookahead)) {
            System.out.print(name(symbol) + "\t");
            System.out.print(name(lookahead) + "\t");
            error();
            while (!begin.contains(lookahead) && !followCopy.contains(lookahead) ){
                System.out.print(name(symbol) + "\t");
                System.out.print(name(lookahead) + "\t");
                lookahead = lex();
            }
            if (!begin.contains(lookahead) && followCopy.contains(lookahead)){
                return null;
            }
        } 

        Map.Entry<Integer, List<Integer>> result = query(symbol, lookahead); // get production by lookahead symbol
        if(result == null){
            return null;
        }
        int actionId = result.getKey(); // get user-defined action

        List<Integer> right = result.getValue(); // right-hand side of production
        int length = right.size();
        SemValue[] params = new SemValue[length + 1];

        boolean isError = false;   //错误检测、记录

        for (int i = 0; i < length; i++) { // parse right-hand side symbols one by one
            int term = right.get(i);
            //根据是否非终结符来决定是否继续递归向下
            params[i + 1] = isNonTerminal(term)
                    ? parse(term, followCopy) // for non terminals: recursively parse it
                    : matchToken(term) // for terminals: match token
                    ;

            if(params[i + 1] == null)
                isError = true;
        }

        if(isError)
            return null;
        
        System.out.println();

        params[0] = new SemValue(); // initialize return value
        act(actionId, params); // do user-defined action
        return params[0];
    }

    /**
     * Match if the lookahead token is the same as the expected token.
     *
     * @param expected the expected token.
     * @return same as `parse`.
     */
    private SemValue matchToken(int expected) {
        SemValue self = val;
        if (lookahead != expected) {
            System.out.print(name(expected) + "\t");
            System.out.print(name(lookahead) + "\t");
            error();    //当预期的非终结符和得到的符号无法匹配，先报错，然后把该语法节点置为空
            return null;
        }

        lookahead = lex();//如果读入的符号被接受，那么再读入一个到lookahead
        return self;
    }

    /**
     * Explicit interface of the parser. Call it in `Driver` class!
     *
     * @return the program AST if successfully parsed, otherwise `null`.
     */
    public Tree.TopLevel parseFile() {
        lookahead = lex();
        SemValue r = parse(start, new HashSet<>());
        return r == null ? null : r.prog;
    }

    /**
     * Helper function. (For debugging)
     * Pretty print a symbol set.
     *
     * @param set symbol set.
     */
    private void printSymbolSet(Set<Integer> set) {
        StringBuilder buf = new StringBuilder();
        buf.append("{ ");
        for (Integer i : set) {
            buf.append(name(i));
            buf.append(" ");
        }
        buf.append("}");
        System.out.print(buf.toString());
    }

    /**
     * Helper function. (For debugging)
     * Pretty print a symbol list.
     *
     * @param list symbol list.
     */
    private void printSymbolList(List<Integer> list) {
        StringBuilder buf = new StringBuilder();
        buf.append(" ");
        for (Integer i : list) {
            buf.append(name(i));
            buf.append(" ");
        }
        System.out.print(buf.toString());
    }

    /**
     * Diagnose function. (For debugging)
     * Implement this by yourself on demand.
     */
    public void diagnose() {

    }

}
