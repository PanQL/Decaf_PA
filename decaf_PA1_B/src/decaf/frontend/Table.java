/* This is auto-generated source by LL1-Parser-Gen.
 * Specification file: D:\Study\decaf_PA1_B\src\decaf\frontend\Parser.spec
 * Options: unstrict mode
 * Generated at: Sun Nov 04 21:39:23 CST 2018
 * Please do NOT modify it!
 *
 * Project repository: https://github.com/paulzfm/LL1-Parser-Gen
 * Version: special version for decaf-PA1-B
 * Author: Zhu Fengmin (Paul)
 */

package decaf.frontend;

import decaf.Location;
import decaf.tree.Tree;
import decaf.tree.Tree.*;
import java.util.*;

public class Table
 {
    public static final int eof = -1;
    public static final int eos = 0;
    
    /* tokens and symbols */
    public static final int VOID = 257; //# line 13
    public static final int BOOL = 258; //# line 13
    public static final int INT = 259; //# line 13
    public static final int STRING = 260; //# line 13
    public static final int CLASS = 261; //# line 13
    public static final int NULL = 262; //# line 14
    public static final int EXTENDS = 263; //# line 14
    public static final int THIS = 264; //# line 14
    public static final int WHILE = 265; //# line 14
    public static final int FOR = 266; //# line 14
    public static final int IF = 267; //# line 15
    public static final int ELSE = 268; //# line 15
    public static final int RETURN = 269; //# line 15
    public static final int BREAK = 270; //# line 15
    public static final int NEW = 271; //# line 15
    public static final int PRINT = 272; //# line 16
    public static final int READ_INTEGER = 273; //# line 16
    public static final int READ_LINE = 274; //# line 16
    public static final int LITERAL = 275; //# line 17
    public static final int IDENTIFIER = 276; //# line 18
    public static final int AND = 277; //# line 18
    public static final int OR = 278; //# line 18
    public static final int STATIC = 279; //# line 18
    public static final int INSTANCEOF = 280; //# line 18
    public static final int LESS_EQUAL = 281; //# line 19
    public static final int GREATER_EQUAL = 282; //# line 19
    public static final int EQUAL = 283; //# line 19
    public static final int NOT_EQUAL = 284; //# line 19
    public static final int SCOPY = 285; //# line 23
    public static final int GUARD = 286; //# line 23
    public static final int SEALED = 287; //# line 23
    public static final int MOMO = 288; //# line 23
    public static final int IN = 289; //# line 23
    public static final int PLUSPLUS = 290; //# line 23
    public static final int VAR = 291; //# line 24
    public static final int FOREACH = 292; //# line 24
    public static final int DEFAULT = 293; //# line 24
    public static final int LeftBra = 294; //# line 24
    public static final int RightBra = 295; //# line 24
    
    public static final int VariableDef = 296;
    public static final int ExprT5 = 297;
    public static final int Oper8 = 298;
    public static final int BoundVariable = 299;
    public static final int Oper3 = 300;
    public static final int Oper6 = 301;
    public static final int VariableList = 302;
    public static final int Formals = 303;
    public static final int Oper7 = 304;
    public static final int IfSubStmt = 305;
    public static final int Expr8 = 306;
    public static final int AfterSimpleTypeExpr = 307;
    public static final int Constants = 308;
    public static final int Expr2 = 309;
    public static final int Oper2 = 310;
    public static final int IfHelper = 311;
    public static final int ExprT10 = 312;
    public static final int Expr6 = 313;
    public static final int IfBranchList = 314;
    public static final int Expr11 = 315;
    public static final int BreakStmt = 316;
    public static final int ExprT2 = 317;
    public static final int Judge = 318;
    public static final int StmtList = 319;
    public static final int Constant = 320;
    public static final int SubVariableList = 321;
    public static final int PrintStmt = 322;
    public static final int IfBranches = 323;
    public static final int ForStmt = 324;
    public static final int Expr9 = 325;
    public static final int Expr1 = 326;
    public static final int ForeachStmt = 327;
    public static final int Oper1 = 328;
    public static final int ElseClause = 329;
    public static final int FieldList = 330;
    public static final int SubExprList = 331;
    public static final int ArrConstList = 332;
    public static final int Expr10 = 333;
    public static final int AfterParenExpr = 334;
    public static final int ClassDef = 335;
    public static final int ReturnStmt = 336;
    public static final int ExprList = 337;
    public static final int StmtBlock = 338;
    public static final int FunctionField = 339;
    public static final int AfterIdentExpr = 340;
    public static final int Oper9 = 341;
    public static final int Program = 342;
    public static final int Expr = 343;
    public static final int Type = 344;
    public static final int Expr5 = 345;
    public static final int AfterNewExpr = 346;
    public static final int Assignment = 347;
    public static final int ExtendsClause = 348;
    public static final int Oper5 = 349;
    public static final int ArrayType = 350;
    public static final int Expr3 = 351;
    public static final int Actuals = 352;
    public static final int Variable = 353;
    public static final int ExprT3 = 354;
    public static final int Stmt = 355;
    public static final int WhileJudge = 356;
    public static final int SimpleStmt = 357;
    public static final int ExprT7 = 358;
    public static final int SimpleType = 359;
    public static final int WhileStmt = 360;
    public static final int ExprT1 = 361;
    public static final int Expr4 = 362;
    public static final int Range = 363;
    public static final int ExprT4 = 364;
    public static final int ReturnExpr = 365;
    public static final int IfStmt = 366;
    public static final int OCStmt = 367;
    public static final int ExprT6 = 368;
    public static final int ExprT8 = 369;
    public static final int Expr7 = 370;
    public static final int ClassList = 371;
    public static final int Oper4 = 372;
    public static final int Field = 373;
    
    /* start symbol */
    public final int start = Program;
    
    /**
      * Judge if a symbol (within valid range) is non-terminal.
      *
      * @param symbol the symbol to be judged.
      * @return true if and only if the symbol is non-terminal.
      */
        
    public boolean isNonTerminal(int symbol) {
        return symbol >= 296;
    }
    
    private final String[] allSymbols = {
        "VOID", "BOOL", "INT", "STRING", "CLASS",
        "NULL", "EXTENDS", "THIS", "WHILE", "FOR",
        "IF", "ELSE", "RETURN", "BREAK", "NEW",
        "PRINT", "READ_INTEGER", "READ_LINE", "LITERAL", "IDENTIFIER",
        "AND", "OR", "STATIC", "INSTANCEOF", "LESS_EQUAL",
        "GREATER_EQUAL", "EQUAL", "NOT_EQUAL", "SCOPY", "GUARD",
        "SEALED", "MOMO", "IN", "PLUSPLUS", "VAR",
        "FOREACH", "DEFAULT", "LeftBra", "RightBra", "VariableDef",
        "ExprT5", "Oper8", "BoundVariable", "Oper3", "Oper6",
        "VariableList", "Formals", "Oper7", "IfSubStmt", "Expr8",
        "AfterSimpleTypeExpr", "Constants", "Expr2", "Oper2", "IfHelper",
        "ExprT10", "Expr6", "IfBranchList", "Expr11", "BreakStmt",
        "ExprT2", "Judge", "StmtList", "Constant", "SubVariableList",
        "PrintStmt", "IfBranches", "ForStmt", "Expr9", "Expr1",
        "ForeachStmt", "Oper1", "ElseClause", "FieldList", "SubExprList",
        "ArrConstList", "Expr10", "AfterParenExpr", "ClassDef", "ReturnStmt",
        "ExprList", "StmtBlock", "FunctionField", "AfterIdentExpr", "Oper9",
        "Program", "Expr", "Type", "Expr5", "AfterNewExpr",
        "Assignment", "ExtendsClause", "Oper5", "ArrayType", "Expr3",
        "Actuals", "Variable", "ExprT3", "Stmt", "WhileJudge",
        "SimpleStmt", "ExprT7", "SimpleType", "WhileStmt", "ExprT1",
        "Expr4", "Range", "ExprT4", "ReturnExpr", "IfStmt",
        "OCStmt", "ExprT6", "ExprT8", "Expr7", "ClassList",
        "Oper4", "Field",
    };
    
    /**
      * Debugging function (pretty print).
      * Get string presentation of some token or symbol.
      *
      * @param symbol either terminal or non-terminal.
      * @return its string presentation.
      */
        
    public String name(int symbol) {
        if (symbol == eof) return "<eof>";
        if (symbol == eos) return "<eos>";
        if (symbol > 0 && symbol <= 256) return "'" + (char) symbol + "'";
        return allSymbols[symbol - 257];
    }
    
    /* begin lookahead symbols */
    private ArrayList<Set<Integer>> begin = new ArrayList<Set<Integer>>();
    private final Integer[][] beginRaw = {
        {VOID, CLASS, INT, STRING, BOOL},
        {MOMO, Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf('*'), Integer.valueOf('/'), Integer.valueOf('%')},
        {VAR, VOID, CLASS, INT, STRING, BOOL},
        {EQUAL, NOT_EQUAL},
        {LESS_EQUAL, GREATER_EQUAL, Integer.valueOf('<'), Integer.valueOf('>')},
        {VOID, CLASS, INT, STRING, BOOL},
        {VOID, CLASS, INT, STRING, BOOL, Integer.valueOf(')')},
        {Integer.valueOf('+'), Integer.valueOf('-')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf(']'), Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {LITERAL, NULL, Integer.valueOf('['), Integer.valueOf(']')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {AND},
        {Integer.valueOf('('), Integer.valueOf('{')},
        {Integer.valueOf('['), Integer.valueOf('.'), DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, Integer.valueOf('}')},
        {LITERAL, NULL, Integer.valueOf('['), READ_INTEGER, READ_LINE, THIS, NEW, INSTANCEOF, Integer.valueOf('('), IDENTIFIER},
        {BREAK},
        {AND, Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, IF, RightBra, Integer.valueOf(';')},
        {IF, RightBra},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{'), Integer.valueOf('}')},
        {LITERAL, NULL, Integer.valueOf('[')},
        {Integer.valueOf(','), Integer.valueOf(')')},
        {PRINT},
        {GUARD, Integer.valueOf('}')},
        {FOR},
        {Integer.valueOf('-'), Integer.valueOf('!'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {FOREACH},
        {OR},
        {ELSE, PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {VOID, CLASS, INT, STRING, STATIC, BOOL, Integer.valueOf('}')},
        {Integer.valueOf(','), Integer.valueOf(')')},
        {Integer.valueOf(','), Integer.valueOf(']')},
        {READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, CLASS},
        {CLASS, SEALED},
        {RETURN},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('{')},
        {Integer.valueOf('('), Integer.valueOf(';')},
        {Integer.valueOf('('), DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf('-'), Integer.valueOf('!')},
        {CLASS, SEALED},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, LeftBra},
        {VOID, CLASS, INT, STRING, BOOL},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {IDENTIFIER, VOID, CLASS, INT, STRING, BOOL},
        {Integer.valueOf('='), Integer.valueOf(';'), Integer.valueOf(')')},
        {EXTENDS, Integer.valueOf('{')},
        {MOMO},
        {Integer.valueOf('['), IDENTIFIER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, Integer.valueOf(')')},
        {VOID, CLASS, INT, STRING, BOOL},
        {EQUAL, NOT_EQUAL, Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {VOID, CLASS, INT, STRING, BOOL, Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, VAR, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), READ_INTEGER, IF, WHILE, FOR, RETURN, PRINT, BREAK, Integer.valueOf('{'), SCOPY, FOREACH},
        {WHILE, Integer.valueOf(')')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, VAR, Integer.valueOf(';'), Integer.valueOf(')')},
        {Integer.valueOf('+'), Integer.valueOf('-'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {INT, VOID, BOOL, STRING, CLASS},
        {WHILE},
        {OR, Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf(':'), Integer.valueOf(']')},
        {PLUSPLUS, Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, LeftBra, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER, Integer.valueOf(';')},
        {IF},
        {SCOPY},
        {LESS_EQUAL, GREATER_EQUAL, Integer.valueOf('<'), Integer.valueOf('>'), Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), MOMO},
        {Integer.valueOf('*'), Integer.valueOf('/'), Integer.valueOf('%'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {CLASS, SEALED, eof, eos},
        {PLUSPLUS},
        {STATIC, VOID, CLASS, INT, STRING, BOOL},
    };
    
    /**
      * Get begin lookahead tokens for `symbol`.
      *
      * @param symbol the non-terminal.
      * @return its begin lookahead tokens.
      */
        
    public Set<Integer> beginSet(int symbol) {
        return begin.get(symbol - 296);
    }
    
    /* follow set */
    private ArrayList<Set<Integer>> follow = new ArrayList<Set<Integer>>();
    private final Integer[][] followRaw = {
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {IN},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {Integer.valueOf(')')},
        {Integer.valueOf(')')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {GUARD, Integer.valueOf('}')},
        {LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(']')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), MOMO},
        {Integer.valueOf('}')},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(';')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, IF, RightBra, Integer.valueOf(';')},
        {RightBra},
        {Integer.valueOf('}')},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(')')},
        {Integer.valueOf(';')},
        {Integer.valueOf('}')},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), IF, RightBra, Integer.valueOf(';')},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf('}')},
        {Integer.valueOf(')')},
        {Integer.valueOf(']')},
        {Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {CLASS, SEALED, eof, eos},
        {Integer.valueOf(';')},
        {Integer.valueOf(')')},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, STATIC, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {VOID, CLASS, INT, Integer.valueOf('}'), STRING, STATIC, BOOL},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {eof, eos},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), IF, RightBra, Integer.valueOf(';')},
        {IDENTIFIER},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {DEFAULT, Integer.valueOf('/'), LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('.'), Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, Integer.valueOf('*'), IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('['), Integer.valueOf('>'), Integer.valueOf('%'), MOMO},
        {Integer.valueOf(';'), Integer.valueOf(')')},
        {Integer.valueOf('{')},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {IDENTIFIER},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf(')')},
        {Integer.valueOf(';'), Integer.valueOf(','), Integer.valueOf(')')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf(')')},
        {Integer.valueOf(';'), Integer.valueOf(')')},
        {LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {Integer.valueOf('['), IDENTIFIER},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), Integer.valueOf(')'), Integer.valueOf(','), WHILE, Integer.valueOf('='), IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf(']')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';')},
        {Integer.valueOf(';')},
        {PRINT, VOID, FOR, Integer.valueOf('!'), FOREACH, Integer.valueOf('-'), CLASS, READ_LINE, WHILE, RETURN, NULL, INT, SCOPY, Integer.valueOf('}'), GUARD, IDENTIFIER, NEW, IF, VAR, THIS, INSTANCEOF, STRING, LITERAL, LeftBra, ELSE, Integer.valueOf('('), Integer.valueOf(';'), Integer.valueOf('['), BOOL, BREAK, READ_INTEGER, Integer.valueOf('{')},
        {Integer.valueOf(';')},
        {Integer.valueOf(']'), FOR, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), MOMO},
        {LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf('-'), Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, Integer.valueOf('+'), AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {LESS_EQUAL, Integer.valueOf(']'), FOR, GREATER_EQUAL, Integer.valueOf(':'), EQUAL, Integer.valueOf(')'), NOT_EQUAL, Integer.valueOf(','), WHILE, PLUSPLUS, Integer.valueOf('='), OR, AND, IF, RightBra, Integer.valueOf(';'), Integer.valueOf('<'), Integer.valueOf('>'), MOMO},
        {eof, eos},
        {Integer.valueOf('!'), Integer.valueOf('-'), READ_LINE, NULL, IDENTIFIER, NEW, THIS, INSTANCEOF, LITERAL, Integer.valueOf('('), Integer.valueOf('['), READ_INTEGER},
        {VOID, CLASS, INT, Integer.valueOf('}'), STRING, STATIC, BOOL},
    };
    
    /**
      * Get follow set for `symbol`.
      *
      * @param symbol the non-terminal.
      * @return its follow set.
      */
        
    public Set<Integer> followSet(int symbol) {
        return follow.get(symbol - 296);
    }
    
    public Table() {
        for (int i = 0; i < 78; i++) {
            begin.add(new HashSet<>(Arrays.asList(beginRaw[i])));
            follow.add(new HashSet<>(Arrays.asList(followRaw[i])));
        }
    }
    
    /**
      * Predictive table `M` query function.
      * `query(A, a)` will return the corresponding term `M(A, a)`, i.e., the target production
      * for non-terminal `A` when the lookahead token is `a`.
      *
      * @param nonTerminal   the non-terminal.
      * @param lookahead     the lookahead symbol.
      * @return a pair `<id, right>` where `right` is the right-hand side of the target
      * production `nonTerminal -> right`, and `id` is the corresponding action id. To execute
      * such action, call `act(id, params)`.
      * If the corresponding term is undefined in the table, `null` will be returned.
      */
        
    public Map.Entry<Integer, List<Integer>> query(int nonTerminal, int lookahead) {
        switch (nonTerminal) {
            //# line 50
            case VariableDef: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(0, Arrays.asList(Variable, Integer.valueOf(';')));
                    default: return null;
                }
            }
            //# line 560
            case ExprT5: {
                switch (lookahead) {
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(1, Arrays.asList(Oper5, Expr6, ExprT5));
                    case ']':
                    case FOR:
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                        return new AbstractMap.SimpleEntry<>(2, Arrays.asList());
                    default: return null;
                }
            }
            //# line 386
            case Oper8: {
                switch (lookahead) {
                    case '*':
                        return new AbstractMap.SimpleEntry<>(3, Arrays.asList(Integer.valueOf('*')));
                    case '/':
                        return new AbstractMap.SimpleEntry<>(4, Arrays.asList(Integer.valueOf('/')));
                    case '%':
                        return new AbstractMap.SimpleEntry<>(5, Arrays.asList(Integer.valueOf('%')));
                    default: return null;
                }
            }
            //# line 267
            case BoundVariable: {
                switch (lookahead) {
                    case VAR:
                        return new AbstractMap.SimpleEntry<>(6, Arrays.asList(VAR, IDENTIFIER));
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(7, Arrays.asList(Type, IDENTIFIER));
                    default: return null;
                }
            }
            //# line 326
            case Oper3: {
                switch (lookahead) {
                    case EQUAL:
                        return new AbstractMap.SimpleEntry<>(8, Arrays.asList(EQUAL));
                    case NOT_EQUAL:
                        return new AbstractMap.SimpleEntry<>(9, Arrays.asList(NOT_EQUAL));
                    default: return null;
                }
            }
            //# line 352
            case Oper6: {
                switch (lookahead) {
                    case LESS_EQUAL:
                        return new AbstractMap.SimpleEntry<>(10, Arrays.asList(LESS_EQUAL));
                    case GREATER_EQUAL:
                        return new AbstractMap.SimpleEntry<>(11, Arrays.asList(GREATER_EQUAL));
                    case '<':
                        return new AbstractMap.SimpleEntry<>(12, Arrays.asList(Integer.valueOf('<')));
                    case '>':
                        return new AbstractMap.SimpleEntry<>(13, Arrays.asList(Integer.valueOf('>')));
                    default: return null;
                }
            }
            //# line 170
            case VariableList: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(14, Arrays.asList(Variable, SubVariableList));
                    default: return null;
                }
            }
            //# line 160
            case Formals: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(15, Arrays.asList(VariableList));
                    case ')':
                        return new AbstractMap.SimpleEntry<>(16, Arrays.asList());
                    default: return null;
                }
            }
            //# line 374
            case Oper7: {
                switch (lookahead) {
                    case '+':
                        return new AbstractMap.SimpleEntry<>(17, Arrays.asList(Integer.valueOf('+')));
                    case '-':
                        return new AbstractMap.SimpleEntry<>(18, Arrays.asList(Integer.valueOf('-')));
                    default: return null;
                }
            }
            //# line 963
            case IfSubStmt: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(19, Arrays.asList(Expr1, Integer.valueOf(':'), Stmt));
                    default: return null;
                }
            }
            //# line 631
            case Expr8: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(20, Arrays.asList(Expr9, ExprT8));
                    default: return null;
                }
            }
            //# line 812
            case AfterSimpleTypeExpr: {
                switch (lookahead) {
                    case ']':
                        return new AbstractMap.SimpleEntry<>(21, Arrays.asList(Integer.valueOf(']'), Integer.valueOf('['), AfterSimpleTypeExpr));
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(22, Arrays.asList(Expr, Integer.valueOf(']')));
                    default: return null;
                }
            }
            //# line 969
            case Constants: {
                switch (lookahead) {
                    case LITERAL:
                    case NULL:
                    case '[':
                        return new AbstractMap.SimpleEntry<>(23, Arrays.asList(Constant, ArrConstList));
                    case ']':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 466
            case Expr2: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(25, Arrays.asList(Expr3, ExprT2));
                    default: return null;
                }
            }
            //# line 319
            case Oper2: {
                switch (lookahead) {
                    case AND:
                        return new AbstractMap.SimpleEntry<>(26, Arrays.asList(AND));
                    default: return null;
                }
            }
            //# line 905
            case IfHelper: {
                switch (lookahead) {
                    case '(':
                        return new AbstractMap.SimpleEntry<>(27, Arrays.asList(Integer.valueOf('('), Expr, Integer.valueOf(')'), Stmt, ElseClause));
                    case '{':
                        return new AbstractMap.SimpleEntry<>(28, Arrays.asList(Integer.valueOf('{'), IfBranchList, Integer.valueOf('}')));
                    default: return null;
                }
            }
            //# line 698
            case ExprT10: {
                switch (lookahead) {
                    case '[':
                        return new AbstractMap.SimpleEntry<>(29, Arrays.asList(Integer.valueOf('['), Expr, Range, Integer.valueOf(']'), ExprT10));
                    case '.':
                        return new AbstractMap.SimpleEntry<>(30, Arrays.asList(Integer.valueOf('.'), IDENTIFIER, AfterIdentExpr, ExprT10));
                    case DEFAULT:
                        return new AbstractMap.SimpleEntry<>(31, Arrays.asList(DEFAULT, Expr11));
                    case '/':
                    case LESS_EQUAL:
                    case ']':
                    case FOR:
                    case GREATER_EQUAL:
                    case '-':
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case '+':
                    case AND:
                    case '*':
                    case IF:
                    case RightBra:
                    case ';':
                    case '<':
                    case '>':
                    case '%':
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 573
            case Expr6: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(33, Arrays.asList(Expr7, ExprT6));
                    default: return null;
                }
            }
            //# line 941
            case IfBranchList: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(34, Arrays.asList(IfSubStmt, IfBranches));
                    case '}':
                        return new AbstractMap.SimpleEntry<>(35, Arrays.asList());
                    default: return null;
                }
            }
            //# line 756
            case Expr11: {
                switch (lookahead) {
                    case LITERAL:
                    case NULL:
                    case '[':
                        return new AbstractMap.SimpleEntry<>(36, Arrays.asList(Constant));
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(37, Arrays.asList(READ_INTEGER, Integer.valueOf('('), Integer.valueOf(')')));
                    case READ_LINE:
                        return new AbstractMap.SimpleEntry<>(38, Arrays.asList(READ_LINE, Integer.valueOf('('), Integer.valueOf(')')));
                    case THIS:
                        return new AbstractMap.SimpleEntry<>(39, Arrays.asList(THIS));
                    case NEW:
                        return new AbstractMap.SimpleEntry<>(40, Arrays.asList(NEW, AfterNewExpr));
                    case INSTANCEOF:
                        return new AbstractMap.SimpleEntry<>(41, Arrays.asList(INSTANCEOF, Integer.valueOf('('), Expr, Integer.valueOf(','), IDENTIFIER, Integer.valueOf(')')));
                    case '(':
                        return new AbstractMap.SimpleEntry<>(42, Arrays.asList(Integer.valueOf('('), AfterParenExpr));
                    case IDENTIFIER:
                        return new AbstractMap.SimpleEntry<>(43, Arrays.asList(IDENTIFIER, AfterIdentExpr));
                    default: return null;
                }
            }
            //# line 893
            case BreakStmt: {
                switch (lookahead) {
                    case BREAK:
                        return new AbstractMap.SimpleEntry<>(44, Arrays.asList(BREAK));
                    default: return null;
                }
            }
            //# line 478
            case ExprT2: {
                switch (lookahead) {
                    case AND:
                        return new AbstractMap.SimpleEntry<>(45, Arrays.asList(Oper2, Expr3, ExprT2));
                    case ']':
                    case FOR:
                    case ':':
                    case ')':
                    case ',':
                    case WHILE:
                    case '=':
                    case OR:
                    case IF:
                    case RightBra:
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 427
            case Judge: {
                switch (lookahead) {
                    case IF:
                        return new AbstractMap.SimpleEntry<>(47, Arrays.asList(IF, Expr));
                    case RightBra:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 197
            case StmtList: {
                switch (lookahead) {
                    case PRINT:
                    case VOID:
                    case FOR:
                    case '!':
                    case FOREACH:
                    case '-':
                    case CLASS:
                    case READ_LINE:
                    case WHILE:
                    case RETURN:
                    case NULL:
                    case INT:
                    case SCOPY:
                    case IDENTIFIER:
                    case NEW:
                    case IF:
                    case VAR:
                    case THIS:
                    case INSTANCEOF:
                    case STRING:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case ';':
                    case '[':
                    case BOOL:
                    case BREAK:
                    case READ_INTEGER:
                    case '{':
                        return new AbstractMap.SimpleEntry<>(49, Arrays.asList(Stmt, StmtList));
                    case '}':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 836
            case Constant: {
                switch (lookahead) {
                    case LITERAL:
                        return new AbstractMap.SimpleEntry<>(51, Arrays.asList(LITERAL));
                    case NULL:
                        return new AbstractMap.SimpleEntry<>(52, Arrays.asList(NULL));
                    case '[':
                        return new AbstractMap.SimpleEntry<>(53, Arrays.asList(Integer.valueOf('['), Constants, Integer.valueOf(']')));
                    default: return null;
                }
            }
            //# line 180
            case SubVariableList: {
                switch (lookahead) {
                    case ',':
                        return new AbstractMap.SimpleEntry<>(54, Arrays.asList(Integer.valueOf(','), Variable, SubVariableList));
                    case ')':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 935
            case PrintStmt: {
                switch (lookahead) {
                    case PRINT:
                        return new AbstractMap.SimpleEntry<>(56, Arrays.asList(PRINT, Integer.valueOf('('), ExprList, Integer.valueOf(')')));
                    default: return null;
                }
            }
            //# line 951
            case IfBranches: {
                switch (lookahead) {
                    case GUARD:
                        return new AbstractMap.SimpleEntry<>(57, Arrays.asList(GUARD, IfSubStmt, IfBranches));
                    case '}':
                        return new AbstractMap.SimpleEntry<>(58, Arrays.asList());
                    default: return null;
                }
            }
            //# line 887
            case ForStmt: {
                switch (lookahead) {
                    case FOR:
                        return new AbstractMap.SimpleEntry<>(59, Arrays.asList(FOR, Integer.valueOf('('), SimpleStmt, Integer.valueOf(';'), Expr, Integer.valueOf(';'), SimpleStmt, Integer.valueOf(')'), Stmt));
                    default: return null;
                }
            }
            //# line 660
            case Expr9: {
                switch (lookahead) {
                    case '-':
                    case '!':
                        return new AbstractMap.SimpleEntry<>(60, Arrays.asList(Oper9, Expr10));
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(61, Arrays.asList(Expr10));
                    default: return null;
                }
            }
            //# line 436
            case Expr1: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(62, Arrays.asList(Expr2, ExprT1));
                    default: return null;
                }
            }
            //# line 261
            case ForeachStmt: {
                switch (lookahead) {
                    case FOREACH:
                        return new AbstractMap.SimpleEntry<>(63, Arrays.asList(FOREACH, Integer.valueOf('('), BoundVariable, IN, Expr, WhileJudge, Integer.valueOf(')'), Stmt));
                    default: return null;
                }
            }
            //# line 312
            case Oper1: {
                switch (lookahead) {
                    case OR:
                        return new AbstractMap.SimpleEntry<>(64, Arrays.asList(OR));
                    default: return null;
                }
            }
            //# line 915
            case ElseClause: {
                switch (lookahead) {
                    case ELSE:
                        return new AbstractMap.SimpleEntry<>(65, Arrays.asList(ELSE, Stmt));
                    case PRINT:
                    case VOID:
                    case FOR:
                    case '!':
                    case FOREACH:
                    case '-':
                    case CLASS:
                    case READ_LINE:
                    case WHILE:
                    case RETURN:
                    case NULL:
                    case INT:
                    case SCOPY:
                    case '}':
                    case GUARD:
                    case IDENTIFIER:
                    case NEW:
                    case IF:
                    case VAR:
                    case THIS:
                    case INSTANCEOF:
                    case STRING:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case ';':
                    case '[':
                    case BOOL:
                    case BREAK:
                    case READ_INTEGER:
                    case '{':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 120
            case FieldList: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case STATIC:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(67, Arrays.asList(Field, FieldList));
                    case '}':
                        return new AbstractMap.SimpleEntry<>(68, Arrays.asList());
                    default: return null;
                }
            }
            //# line 868
            case SubExprList: {
                switch (lookahead) {
                    case ',':
                        return new AbstractMap.SimpleEntry<>(69, Arrays.asList(Integer.valueOf(','), Expr, SubExprList));
                    case ')':
                        return new AbstractMap.SimpleEntry<>(70, Arrays.asList());
                    default: return null;
                }
            }
            //# line 978
            case ArrConstList: {
                switch (lookahead) {
                    case ',':
                        return new AbstractMap.SimpleEntry<>(71, Arrays.asList(Integer.valueOf(','), Constant, ArrConstList));
                    case ']':
                        return new AbstractMap.SimpleEntry<>(72, Arrays.asList());
                    default: return null;
                }
            }
            //# line 670
            case Expr10: {
                switch (lookahead) {
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(73, Arrays.asList(Expr11, ExprT10));
                    default: return null;
                }
            }
            //# line 824
            case AfterParenExpr: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(74, Arrays.asList(Expr, Integer.valueOf(')')));
                    case CLASS:
                        return new AbstractMap.SimpleEntry<>(75, Arrays.asList(CLASS, IDENTIFIER, Integer.valueOf(')'), Expr11));
                    default: return null;
                }
            }
            //# line 103
            case ClassDef: {
                switch (lookahead) {
                    case CLASS:
                        return new AbstractMap.SimpleEntry<>(76, Arrays.asList(CLASS, IDENTIFIER, ExtendsClause, Integer.valueOf('{'), FieldList, Integer.valueOf('}')));
                    case SEALED:
                        return new AbstractMap.SimpleEntry<>(77, Arrays.asList(SEALED, CLASS, IDENTIFIER, ExtendsClause, Integer.valueOf('{'), FieldList, Integer.valueOf('}')));
                    default: return null;
                }
            }
            //# line 922
            case ReturnStmt: {
                switch (lookahead) {
                    case RETURN:
                        return new AbstractMap.SimpleEntry<>(78, Arrays.asList(RETURN, ReturnExpr));
                    default: return null;
                }
            }
            //# line 860
            case ExprList: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(79, Arrays.asList(Expr, SubExprList));
                    default: return null;
                }
            }
            //# line 191
            case StmtBlock: {
                switch (lookahead) {
                    case '{':
                        return new AbstractMap.SimpleEntry<>(80, Arrays.asList(Integer.valueOf('{'), StmtList, Integer.valueOf('}')));
                    default: return null;
                }
            }
            //# line 152
            case FunctionField: {
                switch (lookahead) {
                    case '(':
                        return new AbstractMap.SimpleEntry<>(81, Arrays.asList(Integer.valueOf('('), Formals, Integer.valueOf(')'), StmtBlock));
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList(Integer.valueOf(';')));
                    default: return null;
                }
            }
            //# line 749
            case AfterIdentExpr: {
                switch (lookahead) {
                    case '(':
                        return new AbstractMap.SimpleEntry<>(83, Arrays.asList(Integer.valueOf('('), Actuals, Integer.valueOf(')')));
                    case DEFAULT:
                    case '/':
                    case LESS_EQUAL:
                    case ']':
                    case FOR:
                    case GREATER_EQUAL:
                    case '.':
                    case '-':
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case '+':
                    case AND:
                    case '*':
                    case IF:
                    case RightBra:
                    case ';':
                    case '<':
                    case '[':
                    case '>':
                    case '%':
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 403
            case Oper9: {
                switch (lookahead) {
                    case '-':
                        return new AbstractMap.SimpleEntry<>(85, Arrays.asList(Integer.valueOf('-')));
                    case '!':
                        return new AbstractMap.SimpleEntry<>(86, Arrays.asList(Integer.valueOf('!')));
                    default: return null;
                }
            }
            //# line 28
            case Program: {
                switch (lookahead) {
                    case CLASS:
                    case SEALED:
                        return new AbstractMap.SimpleEntry<>(87, Arrays.asList(ClassDef, ClassList));
                    default: return null;
                }
            }
            //# line 417
            case Expr: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(88, Arrays.asList(Expr1));
                    case LeftBra:
                        return new AbstractMap.SimpleEntry<>(89, Arrays.asList(LeftBra, Expr, FOR, IDENTIFIER, IN, Expr, Judge, RightBra));
                    default: return null;
                }
            }
            //# line 84
            case Type: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(90, Arrays.asList(SimpleType, ArrayType));
                    default: return null;
                }
            }
            //# line 546
            case Expr5: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(91, Arrays.asList(Expr6, ExprT5));
                    default: return null;
                }
            }
            //# line 798
            case AfterNewExpr: {
                switch (lookahead) {
                    case IDENTIFIER:
                        return new AbstractMap.SimpleEntry<>(92, Arrays.asList(IDENTIFIER, Integer.valueOf('('), Integer.valueOf(')')));
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(93, Arrays.asList(SimpleType, Integer.valueOf('['), AfterSimpleTypeExpr));
                    default: return null;
                }
            }
            //# line 302
            case Assignment: {
                switch (lookahead) {
                    case '=':
                        return new AbstractMap.SimpleEntry<>(94, Arrays.asList(Integer.valueOf('='), Expr));
                    case ';':
                    case ')':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 113
            case ExtendsClause: {
                switch (lookahead) {
                    case EXTENDS:
                        return new AbstractMap.SimpleEntry<>(96, Arrays.asList(EXTENDS, IDENTIFIER));
                    case '{':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 345
            case Oper5: {
                switch (lookahead) {
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(98, Arrays.asList(MOMO));
                    default: return null;
                }
            }
            //# line 93
            case ArrayType: {
                switch (lookahead) {
                    case '[':
                        return new AbstractMap.SimpleEntry<>(99, Arrays.asList(Integer.valueOf('['), Integer.valueOf(']'), ArrayType));
                    case IDENTIFIER:
                        return new AbstractMap.SimpleEntry<>(100, Arrays.asList());
                    default: return null;
                }
            }
            //# line 496
            case Expr3: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(101, Arrays.asList(Expr4, ExprT3));
                    default: return null;
                }
            }
            //# line 850
            case Actuals: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(102, Arrays.asList(ExprList));
                    case ')':
                        return new AbstractMap.SimpleEntry<>(103, Arrays.asList());
                    default: return null;
                }
            }
            //# line 56
            case Variable: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(104, Arrays.asList(Type, IDENTIFIER));
                    default: return null;
                }
            }
            //# line 508
            case ExprT3: {
                switch (lookahead) {
                    case EQUAL:
                    case NOT_EQUAL:
                        return new AbstractMap.SimpleEntry<>(105, Arrays.asList(Oper3, Expr4, ExprT3));
                    case ']':
                    case FOR:
                    case ':':
                    case ')':
                    case ',':
                    case WHILE:
                    case '=':
                    case OR:
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 205
            case Stmt: {
                switch (lookahead) {
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(107, Arrays.asList(VariableDef));
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case VAR:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case ';':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(108, Arrays.asList(SimpleStmt, Integer.valueOf(';')));
                    case IF:
                        return new AbstractMap.SimpleEntry<>(109, Arrays.asList(IfStmt));
                    case WHILE:
                        return new AbstractMap.SimpleEntry<>(110, Arrays.asList(WhileStmt));
                    case FOR:
                        return new AbstractMap.SimpleEntry<>(111, Arrays.asList(ForStmt));
                    case RETURN:
                        return new AbstractMap.SimpleEntry<>(112, Arrays.asList(ReturnStmt, Integer.valueOf(';')));
                    case PRINT:
                        return new AbstractMap.SimpleEntry<>(113, Arrays.asList(PrintStmt, Integer.valueOf(';')));
                    case BREAK:
                        return new AbstractMap.SimpleEntry<>(114, Arrays.asList(BreakStmt, Integer.valueOf(';')));
                    case '{':
                        return new AbstractMap.SimpleEntry<>(115, Arrays.asList(StmtBlock));
                    case SCOPY:
                        return new AbstractMap.SimpleEntry<>(116, Arrays.asList(OCStmt, Integer.valueOf(';')));
                    case FOREACH:
                        return new AbstractMap.SimpleEntry<>(117, Arrays.asList(ForeachStmt));
                    default: return null;
                }
            }
            //# line 279
            case WhileJudge: {
                switch (lookahead) {
                    case WHILE:
                        return new AbstractMap.SimpleEntry<>(118, Arrays.asList(WHILE, Expr));
                    case ')':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 286
            case SimpleStmt: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(120, Arrays.asList(Expr, Assignment));
                    case VAR:
                        return new AbstractMap.SimpleEntry<>(121, Arrays.asList(VAR, IDENTIFIER, Integer.valueOf('='), Expr));
                    case ';':
                    case ')':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 614
            case ExprT7: {
                switch (lookahead) {
                    case '+':
                    case '-':
                        return new AbstractMap.SimpleEntry<>(123, Arrays.asList(Oper7, Expr8, ExprT7));
                    case LESS_EQUAL:
                    case ']':
                    case FOR:
                    case GREATER_EQUAL:
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                    case '<':
                    case '>':
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 62
            case SimpleType: {
                switch (lookahead) {
                    case INT:
                        return new AbstractMap.SimpleEntry<>(125, Arrays.asList(INT));
                    case VOID:
                        return new AbstractMap.SimpleEntry<>(126, Arrays.asList(VOID));
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(127, Arrays.asList(BOOL));
                    case STRING:
                        return new AbstractMap.SimpleEntry<>(128, Arrays.asList(STRING));
                    case CLASS:
                        return new AbstractMap.SimpleEntry<>(129, Arrays.asList(CLASS, IDENTIFIER));
                    default: return null;
                }
            }
            //# line 881
            case WhileStmt: {
                switch (lookahead) {
                    case WHILE:
                        return new AbstractMap.SimpleEntry<>(130, Arrays.asList(WHILE, Integer.valueOf('('), Expr, Integer.valueOf(')'), Stmt));
                    default: return null;
                }
            }
            //# line 448
            case ExprT1: {
                switch (lookahead) {
                    case OR:
                        return new AbstractMap.SimpleEntry<>(131, Arrays.asList(Oper1, Expr2, ExprT1));
                    case ']':
                    case FOR:
                    case ':':
                    case ')':
                    case ',':
                    case WHILE:
                    case '=':
                    case IF:
                    case RightBra:
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 525
            case Expr4: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(133, Arrays.asList(Expr5, ExprT4));
                    default: return null;
                }
            }
            //# line 741
            case Range: {
                switch (lookahead) {
                    case ':':
                        return new AbstractMap.SimpleEntry<>(134, Arrays.asList(Integer.valueOf(':'), Expr));
                    case ']':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 535
            case ExprT4: {
                switch (lookahead) {
                    case PLUSPLUS:
                        return new AbstractMap.SimpleEntry<>(136, Arrays.asList(Oper4, Expr5, ExprT4));
                    case ']':
                    case FOR:
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case '=':
                    case OR:
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 928
            case ReturnExpr: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case LeftBra:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(138, Arrays.asList(Expr));
                    case ';':
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 899
            case IfStmt: {
                switch (lookahead) {
                    case IF:
                        return new AbstractMap.SimpleEntry<>(140, Arrays.asList(IF, IfHelper));
                    default: return null;
                }
            }
            //# line 255
            case OCStmt: {
                switch (lookahead) {
                    case SCOPY:
                        return new AbstractMap.SimpleEntry<>(141, Arrays.asList(SCOPY, Integer.valueOf('('), IDENTIFIER, Integer.valueOf(','), Expr, Integer.valueOf(')')));
                    default: return null;
                }
            }
            //# line 585
            case ExprT6: {
                switch (lookahead) {
                    case LESS_EQUAL:
                    case GREATER_EQUAL:
                    case '<':
                    case '>':
                        return new AbstractMap.SimpleEntry<>(142, Arrays.asList(Oper6, Expr7, ExprT6));
                    case ']':
                    case FOR:
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 643
            case ExprT8: {
                switch (lookahead) {
                    case '*':
                    case '/':
                    case '%':
                        return new AbstractMap.SimpleEntry<>(144, Arrays.asList(Oper8, Expr9, ExprT8));
                    case LESS_EQUAL:
                    case ']':
                    case FOR:
                    case GREATER_EQUAL:
                    case '-':
                    case ':':
                    case EQUAL:
                    case ')':
                    case NOT_EQUAL:
                    case ',':
                    case WHILE:
                    case PLUSPLUS:
                    case '=':
                    case OR:
                    case '+':
                    case AND:
                    case IF:
                    case RightBra:
                    case ';':
                    case '<':
                    case '>':
                    case MOMO:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 602
            case Expr7: {
                switch (lookahead) {
                    case '!':
                    case '-':
                    case READ_LINE:
                    case NULL:
                    case IDENTIFIER:
                    case NEW:
                    case THIS:
                    case INSTANCEOF:
                    case LITERAL:
                    case '(':
                    case '[':
                    case READ_INTEGER:
                        return new AbstractMap.SimpleEntry<>(146, Arrays.asList(Expr8, ExprT7));
                    default: return null;
                }
            }
            //# line 39
            case ClassList: {
                switch (lookahead) {
                    case CLASS:
                    case SEALED:
                        return new AbstractMap.SimpleEntry<>(147, Arrays.asList(ClassDef, ClassList));
                    case eof:
                    case eos:
                        return new AbstractMap.SimpleEntry<>(148, Arrays.asList());
                    default: return null;
                }
            }
            //# line 338
            case Oper4: {
                switch (lookahead) {
                    case PLUSPLUS:
                        return new AbstractMap.SimpleEntry<>(149, Arrays.asList(PLUSPLUS));
                    default: return null;
                }
            }
            //# line 136
            case Field: {
                switch (lookahead) {
                    case STATIC:
                        return new AbstractMap.SimpleEntry<>(150, Arrays.asList(STATIC, Type, IDENTIFIER, Integer.valueOf('('), Formals, Integer.valueOf(')'), StmtBlock));
                    case VOID:
                    case CLASS:
                    case INT:
                    case STRING:
                    case BOOL:
                        return new AbstractMap.SimpleEntry<>(151, Arrays.asList(Type, IDENTIFIER, FunctionField));
                    default: return null;
                }
            }
            default: return null;
        }
    }
    
    /**
      * Execute some user-defined semantic action on the specification file.
      * Note that `$$ = params[0], $1 = params[1], ...`. Nothing will be returned, so please
      * do not forget to store the parsed AST result in `params[0]`.
      *
      * @param id      the action id.
      * @param params  parameter array.
      */
        
    public void act(int id, SemValue[] params) {
        switch (id) {
            case 0: {
                //# line 51
                params[0].vdef = params[1].vdef;
                return;
            }
            case 1: {
                //# line 561
                params[0].elist = new ArrayList<Expr>();
                params[0].elist.add(params[2].expr);
                params[0].elist.addAll(params[3].elist);
                params[0].loc = params[1].loc;
                return;
            }
            case 2: {
                //# line 568
                params[0].elist = new ArrayList<Expr>();
                return;
            }
            case 3: {
                //# line 387
                params[0].counter = Tree.MUL;
                params[0].loc = params[1].loc;
                return;
            }
            case 4: {
                //# line 392
                params[0].counter = Tree.DIV;
                params[0].loc = params[1].loc;
                return;
            }
            case 5: {
                //# line 397
                params[0].counter = Tree.MOD;
                params[0].loc = params[1].loc;
                return;
            }
            case 6: {
                //# line 268
                params[0].type = null;
                params[0].ident = params[2].ident;
                return;
            }
            case 7: {
                //# line 273
                params[0].type= params[1].type;
                params[0].ident = params[2].ident;
                return;
            }
            case 8: {
                //# line 327
                params[0].counter = Tree.EQ;
                params[0].loc = params[1].loc;
                return;
            }
            case 9: {
                //# line 332
                params[0].counter = Tree.NE;
                params[0].loc = params[1].loc;
                return;
            }
            case 10: {
                //# line 353
                params[0].counter = Tree.LE;
                params[0].loc = params[1].loc;
                return;
            }
            case 11: {
                //# line 358
                params[0].counter = Tree.GE;
                params[0].loc = params[1].loc;
                return;
            }
            case 12: {
                //# line 363
                params[0].counter = Tree.LT;
                params[0].loc = params[1].loc;
                return;
            }
            case 13: {
                //# line 368
                params[0].counter = Tree.GT;
                params[0].loc = params[1].loc;
                return;
            }
            case 14: {
                //# line 171
                params[0].vlist = new ArrayList<VarDef>();
                params[0].vlist.add(params[1].vdef);
                if (params[2].vlist != null) {
                    params[0].vlist.addAll(params[2].vlist);
                }
                return;
            }
            case 15: {
                //# line 161
                params[0].vlist = params[1].vlist;
                return;
            }
            case 16: {
                //# line 165
                params[0].vlist = new ArrayList<VarDef>();
                return;
            }
            case 17: {
                //# line 375
                params[0].counter = Tree.PLUS;
                params[0].loc = params[1].loc;
                return;
            }
            case 18: {
                //# line 380
                params[0].counter = Tree.MINUS;
                params[0].loc = params[1].loc;
                return;
            }
            case 19: {
                //# line 964
                params[0].expr = new Tree.IfSubStmt(params[1].expr,params[3].stmt,params[1].loc);
                return;
            }
            case 20: {
                //# line 632
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 21: {
                //# line 813
                params[0].expr = params[3].expr;
                params[0].counter = 1 + params[3].counter;
                return;
            }
            case 22: {
                //# line 818
                params[0].expr = params[1].expr;
                params[0].counter = 0;
                return;
            }
            case 23: {
                //# line 970
                params[0].elist = new ArrayList<Expr>();
                params[0].elist.add(params[1].expr);
                params[0].elist.addAll(params[2].elist);
                return;
            }
            case 24: {
                /* no action */
                return;
            }
            case 25: {
                //# line 467
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 26: {
                //# line 320
                params[0].counter = Tree.AND;
                params[0].loc = params[1].loc;
                return;
            }
            case 27: {
                //# line 906
                params[0].stmt = new Tree.If(params[2].expr, params[4].stmt, params[5].stmt, params[1].loc);
                return;
            }
            case 28: {
                //# line 910
                params[0].stmt = params[2].stmt;
                return;
            }
            case 29: {
                //# line 699
                if(params[3].expr == null){
                    if(params[5].counter == Tree.DEFAULT){
                        params[0].counter = Tree.DEFAULT;
                        params[0].elist = new ArrayList<Expr>();
                        params[0].elist.add(params[2].expr);
                        params[0].elist.add(params[5].expr);
                    }else{
                        SemValue sem = new SemValue();
                        sem.expr = params[2].expr;
                        params[0].vec = new Vector<SemValue>();
                        params[0].vec.add(sem);
                        if (params[5].vec != null) {
                            params[0].vec.addAll(params[5].vec);
                        }
                    }
                }else{
                    params[0].elist = new ArrayList<Expr>();
                    params[0].elist.add(params[2].expr);
                    params[0].elist.add(params[3].expr);
                }
                return;
            }
            case 30: {
                //# line 722
                SemValue sem = new SemValue();
                sem.ident = params[2].ident;
                sem.loc = params[2].loc;
                sem.elist = params[3].elist;
                params[0].vec = new Vector<SemValue>();
                params[0].vec.add(sem);
                if (params[4].vec != null) {
                    params[0].vec.addAll(params[4].vec);
                }
                return;
            }
            case 31: {
                //# line 734
                params[0].counter = Tree.DEFAULT;
                params[0].expr = params[2].expr;
                return;
            }
            case 32: {
                /* no action */
                return;
            }
            case 33: {
                //# line 574
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 34: {
                //# line 942
                params[0].stmt = new Tree.GuardedStmt(params[2].elist,params[1].expr,params[1].loc);
                return;
            }
            case 35: {
                //# line 946
                params[0].stmt = new Tree.GuardedStmt(new ArrayList<Expr>(), null, params[0].loc);
                return;
            }
            case 36: {
                //# line 757
                params[0].expr = params[1].expr;
                return;
            }
            case 37: {
                //# line 761
                params[0].expr = new Tree.ReadIntExpr(params[1].loc);
                return;
            }
            case 38: {
                //# line 765
                params[0].expr = new Tree.ReadLineExpr(params[1].loc);
                return;
            }
            case 39: {
                //# line 769
                params[0].expr = new Tree.ThisExpr(params[1].loc);
                return;
            }
            case 40: {
                //# line 773
                if (params[2].ident != null) {
                    params[0].expr = new Tree.NewClass(params[2].ident, params[1].loc);
                } else {
                    params[0].expr = new Tree.NewArray(params[2].type, params[2].expr, params[1].loc);
                }
                return;
            }
            case 41: {
                //# line 781
                params[0].expr = new Tree.TypeTest(params[3].expr, params[5].ident, params[1].loc);
                return;
            }
            case 42: {
                //# line 785
                params[0].expr = params[2].expr;
                return;
            }
            case 43: {
                //# line 789
                if (params[2].elist != null) {
                    params[0].expr = new Tree.CallExpr(null, params[1].ident, params[2].elist, params[1].loc);
                } else {
                    params[0].expr = new Tree.Ident(null, params[1].ident, params[1].loc);
                }
                return;
            }
            case 44: {
                //# line 894
                params[0].stmt = new Tree.Break(params[1].loc);
                return;
            }
            case 45: {
                //# line 479
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 46: {
                /* no action */
                return;
            }
            case 47: {
                //# line 428
                params[0].expr = params[2].expr;
                return;
            }
            case 48: {
                /* no action */
                return;
            }
            case 49: {
                //# line 198
                params[0].slist.add(params[1].stmt);
                params[0].slist.addAll(params[2].slist);
                return;
            }
            case 50: {
                /* no action */
                return;
            }
            case 51: {
                //# line 837
                params[0].expr = new Tree.Literal(params[1].typeTag, params[1].literal, params[1].loc);
                return;
            }
            case 52: {
                //# line 841
                params[0].expr = new Null(params[1].loc);
                return;
            }
            case 53: {
                //# line 845
                params[0].expr = new Tree.ArrayConstant(params[2].elist, params[1].loc);
                return;
            }
            case 54: {
                //# line 181
                params[0].vlist = new ArrayList<VarDef>();
                params[0].vlist.add(params[2].vdef);
                if (params[3].vlist != null) {
                    params[0].vlist.addAll(params[3].vlist);
                }
                return;
            }
            case 55: {
                /* no action */
                return;
            }
            case 56: {
                //# line 936
                params[0].stmt = new Tree.Print(params[3].elist, params[1].loc);
                return;
            }
            case 57: {
                //# line 952
                params[0].elist = new ArrayList<Expr>();
                params[0].elist.add(params[2].expr);
                params[0].elist.addAll(params[3].elist);
                return;
            }
            case 58: {
                //# line 958
                params[0].elist = new ArrayList<Expr>();
                return;
            }
            case 59: {
                //# line 888
                params[0].stmt = new Tree.ForLoop(params[3].stmt, params[5].expr, params[7].stmt, params[9].stmt, params[1].loc);
                return;
            }
            case 60: {
                //# line 661
                params[0].expr = new Tree.Unary(params[1].counter, params[2].expr, params[1].loc);
                return;
            }
            case 61: {
                //# line 665
                params[0].expr = params[1].expr;
                return;
            }
            case 62: {
                //# line 437
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 63: {
                //# line 262
                params[0].stmt = new Tree.ForeachStmt(params[3].type, params[3].ident, params[5].expr, params[6].expr, params[8].stmt, params[1].loc);
                return;
            }
            case 64: {
                //# line 313
                params[0].counter = Tree.OR;
                params[0].loc = params[1].loc;
                return;
            }
            case 65: {
                //# line 916
                params[0].stmt = params[2].stmt;
                return;
            }
            case 66: {
                /* no action */
                return;
            }
            case 67: {
                //# line 121
                params[0].flist = new ArrayList<Tree>();
                if (params[1].vdef != null) {
                    params[0].flist.add(params[1].vdef);
                } else {
                    params[0].flist.add(params[1].fdef);
                }
                params[0].flist.addAll(params[2].flist);
                return;
            }
            case 68: {
                //# line 131
                params[0].flist = new ArrayList<Tree>();
                return;
            }
            case 69: {
                //# line 869
                params[0].elist = new ArrayList<Tree.Expr>();
                params[0].elist.add(params[2].expr);
                params[0].elist.addAll(params[3].elist);
                return;
            }
            case 70: {
                //# line 875
                params[0].elist = new ArrayList<Tree.Expr>();
                return;
            }
            case 71: {
                //# line 979
                params[0].elist = new ArrayList<Expr>();
                params[0].elist.add(params[2].expr);
                params[0].elist.addAll(params[3].elist);
                return;
            }
            case 72: {
                //# line 985
                params[0].elist = new ArrayList<Expr>();
                return;
            }
            case 73: {
                //# line 671
                if(params[2].elist != null && params[2].elist.size() == 2){
                    if(params[2].counter == Tree.DEFAULT){
                        params[0].expr = new Tree.ArrayDefault(params[1].expr, params[2].elist.get(0), params[2].elist.get(1), params[1].loc);
                    }else{
                        params[0].expr = new Tree.ArrayRange(params[1].expr, params[2].elist.get(0), params[2].elist.get(1), params[1].loc);
                    }
                }else{
                    params[0].expr = params[1].expr;
                    params[0].loc = params[1].loc;
                    if (params[2].vec != null) {
                        for (SemValue v : params[2].vec) {
                            if (v.expr != null) {
                                params[0].expr = new Tree.Indexed(params[0].expr, v.expr, params[0].loc);
                            } else if (v.elist != null) {
                                params[0].expr = new Tree.CallExpr(params[0].expr, v.ident, v.elist, v.loc);
                                params[0].loc = v.loc;
                            } else {
                                params[0].expr = new Tree.Ident(params[0].expr, v.ident, v.loc);
                                params[0].loc = v.loc;
                            }
                        }
                    }
                }
                return;
            }
            case 74: {
                //# line 825
                params[0].expr = params[1].expr;
                return;
            }
            case 75: {
                //# line 829
                params[0].expr = new Tree.TypeCast(params[2].ident, params[4].expr, params[4].loc);
                return;
            }
            case 76: {
                //# line 104
                params[0].cdef = new Tree.ClassDef(params[2].ident, params[3].ident, params[5].flist, params[1].loc);
                return;
            }
            case 77: {
                //# line 108
                params[0].cdef = new Tree.SealedClassDef(params[3].ident, params[4].ident, params[6].flist, params[1].loc);
                return;
            }
            case 78: {
                //# line 923
                params[0].stmt = new Tree.Return(params[2].expr, params[1].loc);
                return;
            }
            case 79: {
                //# line 861
                params[0].elist = new ArrayList<Tree.Expr>();
                params[0].elist.add(params[1].expr);
                params[0].elist.addAll(params[2].elist);
                return;
            }
            case 80: {
                //# line 192
                params[0].stmt = new Tree.Block(params[2].slist, params[1].loc);
                return;
            }
            case 81: {
                //# line 153
                params[0].vlist = params[2].vlist;
                params[0].stmt = params[4].stmt;
                return;
            }
            case 82: {
                /* no action */
                return;
            }
            case 83: {
                //# line 750
                params[0].elist = params[2].elist;
                return;
            }
            case 84: {
                /* no action */
                return;
            }
            case 85: {
                //# line 404
                params[0].counter = Tree.NEG;
                params[0].loc = params[1].loc;
                return;
            }
            case 86: {
                //# line 409
                params[0].counter = Tree.NOT;
                params[0].loc = params[1].loc;
                return;
            }
            case 87: {
                //# line 29
                params[0].clist = new ArrayList<ClassDef>();
                params[0].clist.add(params[1].cdef);
                if (params[2].clist != null) {
                    params[0].clist.addAll(params[2].clist);
                }
                params[0].prog = new Tree.TopLevel(params[0].clist, params[0].loc);
                return;
            }
            case 88: {
                //# line 418
                params[0].expr = params[1].expr;
                return;
            }
            case 89: {
                //# line 422
                params[0].expr = new Tree.ArrayComp(params[2].expr, params[4].ident, params[6].expr, params[7].expr, params[1].loc);
                return;
            }
            case 90: {
                //# line 85
                params[0].type = params[1].type;
                for (int i = 0; i < params[2].counter; ++i) {
                    params[0].type = new Tree.TypeArray(params[0].type, params[1].loc);
                }
                return;
            }
            case 91: {
                //# line 547
                if(params[2].elist != null && params[2].elist.size() != 0){
                    Expr ex = params[1].expr;
                    for(int i = 0; i < params[2].elist.size(); i++){
                        ex = new Tree.ArrayRepeat(ex,params[2].elist.get(i),params[2].loc);
                    }
                    params[0].expr = ex;
                }else{
                    params[0].expr = params[1].expr;
                }
                return;
            }
            case 92: {
                //# line 799
                params[0].ident = params[1].ident;
                return;
            }
            case 93: {
                //# line 803
                params[0].type = params[1].type;
                for (int i = 0; i < params[3].counter; ++i) {
                    params[0].type = new Tree.TypeArray(params[0].type, params[1].loc);
                }
                params[0].expr = params[3].expr;
                return;
            }
            case 94: {
                //# line 303
                params[0].loc = params[1].loc;
                params[0].expr = params[2].expr;
                return;
            }
            case 95: {
                /* no action */
                return;
            }
            case 96: {
                //# line 114
                params[0].ident = params[2].ident;
                return;
            }
            case 97: {
                /* no action */
                return;
            }
            case 98: {
                //# line 346
                params[0].counter = Tree.MOMO;
                params[0].loc = params[1].loc;
                return;
            }
            case 99: {
                //# line 94
                params[0].counter = 1 + params[3].counter;
                return;
            }
            case 100: {
                //# line 98
                params[0].counter = 0;
                return;
            }
            case 101: {
                //# line 497
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 102: {
                //# line 851
                params[0].elist = params[1].elist;
                return;
            }
            case 103: {
                //# line 855
                params[0].elist = new ArrayList<Tree.Expr>();
                return;
            }
            case 104: {
                //# line 57
                params[0].vdef = new Tree.VarDef(params[2].ident, params[1].type, params[2].loc);
                return;
            }
            case 105: {
                //# line 509
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 106: {
                /* no action */
                return;
            }
            case 107: {
                //# line 206
                params[0].stmt = params[1].vdef;
                return;
            }
            case 108: {
                //# line 210
                if (params[1].stmt == null) {
                    params[0].stmt = new Tree.Skip(params[2].loc);
                } else {
                    params[0].stmt = params[1].stmt;
                }
                return;
            }
            case 109: {
                //# line 218
                params[0].stmt = params[1].stmt;
                return;
            }
            case 110: {
                //# line 222
                params[0].stmt = params[1].stmt;
                return;
            }
            case 111: {
                //# line 226
                params[0].stmt = params[1].stmt;
                return;
            }
            case 112: {
                //# line 230
                params[0].stmt = params[1].stmt;
                return;
            }
            case 113: {
                //# line 234
                params[0].stmt = params[1].stmt;
                return;
            }
            case 114: {
                //# line 238
                params[0].stmt = params[1].stmt;
                return;
            }
            case 115: {
                //# line 242
                params[0].stmt = params[1].stmt;
                return;
            }
            case 116: {
                //# line 246
                params[0].stmt = params[1].stmt;
                return;
            }
            case 117: {
                //# line 250
                params[0].stmt = params[1].stmt;
                return;
            }
            case 118: {
                //# line 280
                params[0].expr = params[2].expr;
                return;
            }
            case 119: {
                /* no action */
                return;
            }
            case 120: {
                //# line 287
                if (params[2].expr == null) {
                    params[0].stmt = new Tree.Calculate(params[1].expr, params[1].loc);
                } else {
                    params[0].stmt = new Tree.Assign(params[1].expr, params[2].expr, params[2].loc);
                }
                return;
            }
            case 121: {
                //# line 295
                Tree.VarIdent ident = new Tree.VarIdent(params[2].ident,params[1].loc);
                params[0].stmt = new Tree.Assign(ident, params[4].expr, params[3].loc);
                return;
            }
            case 122: {
                /* no action */
                return;
            }
            case 123: {
                //# line 615
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 124: {
                /* no action */
                return;
            }
            case 125: {
                //# line 63
                params[0].type = new Tree.TypeIdent(Tree.INT, params[1].loc);
                return;
            }
            case 126: {
                //# line 67
                params[0].type = new Tree.TypeIdent(Tree.VOID, params[1].loc);
                return;
            }
            case 127: {
                //# line 71
                params[0].type = new Tree.TypeIdent(Tree.BOOL, params[1].loc);
                return;
            }
            case 128: {
                //# line 75
                params[0].type = new Tree.TypeIdent(Tree.STRING, params[1].loc);
                return;
            }
            case 129: {
                //# line 79
                params[0].type = new Tree.TypeClass(params[2].ident, params[1].loc);
                return;
            }
            case 130: {
                //# line 882
                params[0].stmt = new Tree.WhileLoop(params[3].expr, params[5].stmt, params[1].loc);
                return;
            }
            case 131: {
                //# line 449
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 132: {
                /* no action */
                return;
            }
            case 133: {
                //# line 526
                if(params[2].expr != null){
                    params[0].expr = new Tree.PlusPlus(params[1].expr, params[2].expr, params[2].loc);
                }else{
                    params[0].expr = params[1].expr;
                }
                return;
            }
            case 134: {
                //# line 742
                params[0].expr = params[2].expr;
                return;
            }
            case 135: {
                /* no action */
                return;
            }
            case 136: {
                //# line 536
                if(params[3].expr != null){
                    params[0].expr = new Tree.PlusPlus(params[2].expr, params[3].expr, params[2].loc);
                }else{
                    params[0].expr = params[2].expr;
                }
                return;
            }
            case 137: {
                /* no action */
                return;
            }
            case 138: {
                //# line 929
                params[0].expr = params[1].expr;
                return;
            }
            case 139: {
                /* no action */
                return;
            }
            case 140: {
                //# line 900
                params[0].stmt = params[2].stmt;
                return;
            }
            case 141: {
                //# line 256
                params[0].stmt = new Tree.ScopyClass(params[3].ident,params[5].expr,params[1].loc);
                return;
            }
            case 142: {
                //# line 586
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 143: {
                /* no action */
                return;
            }
            case 144: {
                //# line 644
                params[0].svec = new Vector<Integer>();
                params[0].lvec = new Vector<Location>();
                params[0].evec = new Vector<Expr>();
                params[0].svec.add(params[1].counter);
                params[0].lvec.add(params[1].loc);
                params[0].evec.add(params[2].expr);
                if (params[3].svec != null) {
                    params[0].svec.addAll(params[3].svec);
                    params[0].lvec.addAll(params[3].lvec);
                    params[0].evec.addAll(params[3].evec);
                }
                return;
            }
            case 145: {
                /* no action */
                return;
            }
            case 146: {
                //# line 603
                params[0].expr = params[1].expr;
                if (params[2].svec != null) {
                    for (int i = 0; i < params[2].svec.size(); ++i) {
                        params[0].expr = new Tree.Binary(params[2].svec.get(i), params[0].expr,
                            params[2].evec.get(i), params[2].lvec.get(i));
                    }
                }
                return;
            }
            case 147: {
                //# line 40
                params[0].clist = new ArrayList<ClassDef>();
                params[0].clist.add(params[1].cdef);
                if (params[2].clist != null) {
                    params[0].clist.addAll(params[2].clist);
                }
                return;
            }
            case 148: {
                /* no action */
                return;
            }
            case 149: {
                //# line 339
                params[0].counter = Tree.PLUSPLUS;
                params[0].loc = params[1].loc;
                return;
            }
            case 150: {
                //# line 137
                params[0].fdef = new Tree.MethodDef(true, params[3].ident, params[2].type, params[5].vlist,
                    (Block) params[7].stmt, params[3].loc);
                return;
            }
            case 151: {
                //# line 142
                if (params[3].vlist != null) {
                    params[0].fdef = new Tree.MethodDef(false, params[2].ident, params[1].type, params[3].vlist,
                        (Block) params[3].stmt, params[2].loc);
                } else {
                    params[0].vdef = new Tree.VarDef(params[2].ident, params[1].type, params[2].loc);
                }
                return;
            }
        }
    }
}
/* end of file */
