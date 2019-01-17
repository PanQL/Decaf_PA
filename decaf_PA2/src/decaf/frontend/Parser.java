//### This file created by BYACC 1.8(/Java extension  1.13)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//###           14 Sep 06  -- Keltin Leung-- ReduceListener support, eliminate underflow report in error recovery
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 11 "Parser.y"
package decaf.frontend;

import decaf.tree.Tree;
import decaf.tree.Tree.*;
import decaf.error.*;
import java.util.*;
//#line 25 "Parser.java"
interface ReduceListener {
  public boolean onReduce(String rule);
}




public class Parser
             extends BaseParser
             implements ReduceListener
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

ReduceListener reduceListener = null;
void yyclearin ()       {yychar = (-1);}
void yyerrok ()         {yyerrflag=0;}
void addReduceListener(ReduceListener l) {
  reduceListener = l;}


//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:SemValue
String   yytext;//user variable to return contextual strings
SemValue yyval; //used to return semantic vals from action routines
SemValue yylval;//the 'lval' (result) I got from yylex()
SemValue valstk[] = new SemValue[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new SemValue();
  yylval=new SemValue();
  valptr=-1;
}
final void val_push(SemValue val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    SemValue[] newstack = new SemValue[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final SemValue val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final SemValue val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short VOID=257;
public final static short BOOL=258;
public final static short INT=259;
public final static short STRING=260;
public final static short CLASS=261;
public final static short NULL=262;
public final static short EXTENDS=263;
public final static short THIS=264;
public final static short WHILE=265;
public final static short FOR=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short RETURN=269;
public final static short BREAK=270;
public final static short NEW=271;
public final static short PRINT=272;
public final static short READ_INTEGER=273;
public final static short READ_LINE=274;
public final static short LITERAL=275;
public final static short IDENTIFIER=276;
public final static short AND=277;
public final static short OR=278;
public final static short STATIC=279;
public final static short INSTANCEOF=280;
public final static short LESS_EQUAL=281;
public final static short GREATER_EQUAL=282;
public final static short EQUAL=283;
public final static short NOT_EQUAL=284;
public final static short IFOR=285;
public final static short MOMO=286;
public final static short PLUSPLUS=287;
public final static short SCOPY=288;
public final static short SEALED=289;
public final static short VAR=290;
public final static short GUARD=291;
public final static short DEFAULT=292;
public final static short IN=293;
public final static short FOREACH=294;
public final static short UMINUS=296;
public final static short EMPTY=297;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    4,    5,    5,    5,    5,    5,
    5,    2,    2,    6,    6,    7,    7,    7,    9,    9,
   10,   10,    8,    8,   11,   12,   12,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   13,   23,
   23,   24,   24,   14,   14,   14,   28,   28,   26,   26,
   26,   27,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   30,   30,   30,   29,   29,   32,   32,   16,   17,   20,
   15,   33,   33,   18,   18,   19,   21,   22,   34,   34,
   34,   35,   31,   31,   36,   36,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    1,    1,    1,    2,
    3,    6,    7,    2,    0,    2,    2,    0,    1,    0,
    3,    1,    7,    6,    3,    2,    0,    1,    2,    1,
    1,    1,    2,    2,    2,    1,    2,    1,    1,    9,
    7,    2,    2,    3,    1,    0,    2,    0,    2,    4,
    2,    5,    1,    1,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    2,
    2,    3,    3,    1,    4,    5,    6,    5,    3,    6,
    1,    1,    1,    1,    0,    3,    1,    5,    9,    1,
    6,    2,    0,    2,    1,    4,    6,    4,    3,    1,
    0,    3,    3,    2,    3,    1,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    3,    0,    0,    2,    0,    0,
    0,   14,   18,    0,    0,   18,    7,    8,    6,    9,
    0,    0,   12,   16,    0,    0,   17,    0,   10,    0,
    4,    0,    0,   13,    0,    0,   11,    0,   22,    0,
    0,    0,    0,    5,    0,    0,    0,   27,   24,   21,
   23,    0,   82,   74,    0,    0,    0,    0,   90,    0,
    0,    0,    0,   81,    0,    0,    0,    0,    0,   25,
    0,    0,    0,   28,   36,   26,    0,   30,   31,   32,
    0,    0,    0,    0,   38,   39,    0,    0,    0,    0,
   55,   83,    0,    0,    0,    0,    0,   53,   54,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  104,
    0,    0,    0,   51,    0,   29,   33,   34,   35,   37,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   47,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  100,    0,    0,    0,    0,   72,   73,
    0,    0,   69,    0,  103,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   98,    0,   75,    0,    0,   96,    0,    0,  105,    0,
   43,   42,    0,    0,    0,    0,   88,    0,    0,  102,
   99,   76,    0,    0,   78,    0,    0,    0,   52,    0,
    0,   91,   77,   97,    0,    0,    0,    0,   92,    0,
   41,    0,    0,   89,   40,
};
final static short yydgoto[] = {                          3,
    4,    5,   74,   25,   40,   10,   15,   27,   41,   42,
   75,   52,   76,   77,   78,   79,   80,   81,   82,   83,
   84,   85,   86,  159,   87,   98,   99,   90,  195,   91,
   92,  148,  212,  143,  144,  112,
};
final static short yysindex[] = {                      -232,
 -242, -206,    0, -232,    0, -207, -215,    0, -213,  -56,
 -207,    0,    0,  -48, -106,    0,    0,    0,    0,    0,
 -197,  202,    0,    0,   35,  -90,    0,  197,    0,  -89,
    0,   45,    2,    0,   53,  202,    0,  202,    0,  -88,
   59,   62,   66,    0,  -14,  202,  -14,    0,    0,    0,
    0,    5,    0,    0,   73,   75,  -20,   87,    0,  148,
   77,   78,   81,    0,   82,   87,   87,   65,   -2,    0,
   83, -150,   89,    0,    0,    0,   74,    0,    0,    0,
   76,   80,   90,   91,    0,    0,  909,   96,    0, -140,
    0,    0,   87,   87,   87,   87,  909,    0,    0,   97,
   49,   87,  100,  117,   87,  -22,  -22, -112,  523,    0,
  121,   84, -108,    0,  113,    0,    0,    0,    0,    0,
   87,   87,   87,   87,   87,   87,   87,   87,   87,   87,
   87,   87,   87,    0,   87,   87,   87,  129,  549,  116,
  579,  600, -117,    0,  135,   79,  909,   58,    0,    0,
  659,  138,    0,  -27,    0,  136,  -95,  -70, -110,  607,
  941,  -32,  -32,  973,  973,   40,   40,  -22,  -22,  -22,
  -32,  -32,  776,  -10,  909,   87,   43,   87,   43,   43,
    0,   87,    0,  804,   87,    0,  -92,   87,    0,   87,
    0,    0,   87, -107,  149,  145,    0,  712,  -77,    0,
    0,    0,  909,  156,    0,  857,  512,   87,    0,   87,
   43,    0,    0,    0,   87,  -14,  -32,  157,    0,  868,
    0,   43,  -14,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,  207,    0,   85,    0,    0,    0,    0,
   85,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  150,    0,    0,    0,  171,    0,  171,    0,    0,
    0,  172,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -55,    0,    0,    0,    0,    0,  -53,    0,    0,
    0,    0,    0,    0,    0,  -62,  -62,  -62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  920,    6,    0,
    0,    0,  -62,  -55,  -62, -109,  185,    0,    0,    0,
    0,  -62,    0,    0,  -62,  352,  406,    0,    0,    0,
  137,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -62,  -62,  -62,  -62,  -62,  -62,  -62,  -62,  -62,  -62,
  -62,  -62,  -62,    0,  -62,  -62,  -62,  101,    0,    0,
    0,    0,    0,    0,    0,  -62,   60,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  152,
  -19, 1062, 1064,  453, 1112,  963,  983,  430,  459,  483,
 1066, 1089,    0,  814,    1,  -24,  -55,  -62,  -55,  -55,
    0,  -62,    0,    0,  -62,    0,    0,  -62,    0,  -62,
    0,    0,  -62,  158,    0,  209,    0,    0,  -33,    0,
    0,    0,   70,    0,    0,    0,    0,  -62,    0,  -23,
  -55,    0,    0,    0,  -62,    0, 1008,    0,    0,    0,
    0,  -55,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  252,   26,   34,   56,  257,  265,    0,  244,    0,
   -1,    0, -149,  -85,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, 1302,  328,  999,    0,    0,  -46,
    0,  110,    0,    0,  109,  140,
};
final static int YYTABLESIZE=1517;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         93,
   33,   33,   33,   46,  131,   95,   93,  181,  140,  129,
  127,   93,  128,  134,  130,  101,   85,   46,   23,   95,
   33,   68,  111,  134,   68,   93,  131,  197,    1,  199,
  200,  129,  127,    6,  128,  134,  130,   67,   68,   68,
   24,   44,   54,   49,   68,   51,   45,   54,   54,   66,
   54,   54,   54,   24,    7,    9,    2,   93,  135,   44,
   11,  219,   12,   69,   45,   54,   13,   54,  135,   39,
   26,   39,  224,   68,   16,   67,  131,   30,   29,   50,
  135,  129,   68,   26,   36,  134,  130,   66,   69,   93,
  110,   93,   38,   31,   37,   69,   54,   67,  186,   45,
   87,  185,   96,   87,   68,   46,   47,  111,   48,   66,
   86,   67,   93,   86,   94,  101,  102,  103,   68,   67,
  104,  105,  113,   66,  218,  114,   68,   48,  115,   70,
  135,   66,  116,   69,  117,  138,  145,   49,  118,  146,
  149,   49,   49,   49,   49,   49,   49,   49,  119,  120,
   17,   18,   19,   20,   21,   69,  137,  150,   49,   49,
   49,   49,   49,  152,  154,   48,   48,  156,  176,   69,
  158,   37,   22,  182,  178,  183,  155,   69,  188,  190,
  191,  101,  193,  204,  208,   32,   35,   44,  185,  209,
  211,   49,   67,   49,   50,   67,  213,  222,   50,   50,
   50,   50,   50,   50,   50,  192,    1,   15,    5,   67,
   67,   20,   19,   48,  221,   50,   50,   50,   50,   50,
   48,  225,   48,   93,   93,   93,   93,   93,   93,  106,
   93,   93,   93,   93,   53,   93,   93,   93,   93,   93,
   93,   93,   93,   94,   67,   68,   93,   64,   50,   84,
   50,   48,   48,  136,   93,    8,   93,   93,   68,   53,
   93,   17,   18,   19,   20,   21,   53,   14,   54,   55,
   56,   57,   64,   58,   59,   60,   61,   62,   63,   64,
   28,   43,   54,   54,   65,  196,   54,   54,   54,   54,
  201,   54,   71,  189,   72,    0,    0,    0,   73,   17,
   18,   19,   20,   21,   53,    0,   54,   55,   56,   57,
    0,   58,   59,   60,   61,   62,   63,   64,    0,    0,
    0,   34,   65,    0,    0,  108,   53,    0,   54,    0,
   71,    0,   72,    0,    0,   60,   73,   62,   63,   64,
   53,    0,   54,    0,   65,    0,    0,    0,   53,   60,
   54,   62,   63,   64,   72,    0,    0,   60,   65,   62,
   63,   64,    0,    0,    0,   49,   65,    0,   72,   17,
   18,   19,   20,   21,    0,    0,   72,   49,   49,   88,
    0,   49,   49,   49,   49,    0,   49,    0,   70,    0,
    0,    0,   70,   70,   70,   70,   70,    0,   70,    0,
    0,    0,  157,    0,   17,   18,   19,   20,   21,   70,
   70,   70,    0,   70,    0,    0,   67,    0,    0,    0,
    0,   88,   50,  100,    0,    0,    0,    0,   67,   67,
    0,    0,    0,    0,   50,   50,    0,    0,   50,   50,
   50,   50,   71,   50,   70,    0,   71,   71,   71,   71,
   71,    0,   71,   17,   18,   19,   20,   21,   17,   18,
   19,   20,   21,   71,   71,   71,   58,   71,    0,    0,
   58,   58,   58,   58,   58,   22,   58,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   58,   58,   58,
    0,   58,    0,   61,    0,   59,   61,    0,   71,   59,
   59,   59,   59,   59,   88,   59,   88,   88,    0,    0,
   61,   61,    0,    0,    0,    0,   59,   59,   59,   60,
   59,    0,   58,   60,   60,   60,   60,   60,    0,   60,
    0,    0,    0,    0,    0,    0,    0,   88,   88,    0,
   60,   60,   60,    0,   60,   61,    0,    0,  131,   88,
    0,   59,  216,  129,  127,    0,  128,  134,  130,  131,
    0,    0,    0,  153,  129,  127,    0,  128,  134,  130,
    0,  133,    0,  132,    0,   60,    0,    0,    0,    0,
    0,    0,  133,    0,  132,  131,    0,    0,    0,  177,
  129,  127,    0,  128,  134,  130,    0,    0,    0,    0,
    0,    0,  135,    0,    0,    0,    0,    0,  133,    0,
  132,    0,    0,  135,    0,  131,   70,    0,    0,  179,
  129,  127,    0,  128,  134,  130,    0,    0,   70,   70,
    0,    0,   70,   70,   70,   70,  131,   70,  133,  135,
  132,  129,  127,  131,  128,  134,  130,    0,  129,  127,
    0,  128,  134,  130,    0,    0,    0,  180,    0,  133,
    0,  132,    0,    0,    0,    0,  133,    0,  132,  135,
   71,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   71,   71,    0,    0,   71,   71,   71,   71,
  135,   71,    0,    0,   58,  131,    0,  135,    0,    0,
  129,  127,  187,  128,  134,  130,   58,   58,    0,    0,
   58,   58,   58,   58,    0,   58,    0,   61,  133,    0,
  132,    0,    0,   59,    0,    0,    0,    0,    0,   61,
   61,    0,    0,    0,    0,   59,   59,    0,    0,   59,
   59,   59,   59,    0,   59,    0,    0,   60,  131,  135,
    0,    0,    0,  129,  127,    0,  128,  134,  130,   60,
   60,    0,    0,   60,   60,   60,   60,    0,   60,    0,
  210,  133,    0,  132,    0,    0,  215,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  121,  122,
    0,    0,  123,  124,  125,  126,    0,  136,    0,  121,
  122,    0,  135,  123,  124,  125,  126,    0,  136,    0,
    0,    0,  131,    0,    0,    0,    0,  129,  127,    0,
  128,  134,  130,    0,    0,  121,  122,    0,    0,  123,
  124,  125,  126,    0,  136,  133,    0,  132,    0,    0,
  131,    0,    0,    0,    0,  129,  127,    0,  128,  134,
  130,    0,    0,    0,   79,  121,  122,   79,    0,  123,
  124,  125,  126,  133,  136,  132,  135,    0,  194,    0,
    0,   79,   79,   79,    0,   79,  121,  122,    0,    0,
  123,  124,  125,  126,    0,  136,    0,  123,  124,  125,
  126,    0,  136,  131,  135,    0,  202,  214,  129,  127,
    0,  128,  134,  130,  131,    0,   79,    0,  223,  129,
  127,    0,  128,  134,  130,    0,  133,    0,  132,    0,
    0,    0,    0,    0,    0,    0,    0,  133,    0,  132,
    0,    0,    0,    0,    0,  121,  122,    0,    0,  123,
  124,  125,  126,    0,  136,  131,    0,  135,    0,    0,
  129,  127,    0,  128,  134,  130,   53,    0,  135,    0,
    0,   53,   53,    0,   53,   53,   53,    0,  133,    0,
  132,    0,    0,    0,    0,    0,    0,  131,    0,   53,
    0,   53,  129,  127,    0,  128,  134,  130,  121,  122,
    0,    0,  123,  124,  125,  126,    0,  136,    0,  135,
  133,    0,  132,   56,    0,   56,   56,   56,    0,  131,
   53,    0,    0,    0,  129,  127,    0,  128,  134,  130,
   56,   56,   56,   57,   56,   57,   57,   57,    0,    0,
    0,  135,  133,    0,  132,    0,    0,    0,    0,    0,
   57,   57,   57,    0,   57,    0,    0,    0,   80,    0,
   89,   80,  121,  122,    0,   56,  123,  124,  125,  126,
    0,  136,    0,  135,    0,   80,   80,   80,    0,   80,
    0,    0,    0,    0,    0,   57,    0,    0,   79,    0,
  121,  122,    0,    0,  123,  124,  125,  126,    0,  136,
   79,   79,   89,    0,   79,   79,   79,   79,    0,   79,
   80,    0,   65,    0,   66,   65,   64,   66,    0,   64,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   65,
   65,   66,   66,   64,   64,    0,    0,    0,    0,   63,
    0,    0,   63,  121,  122,    0,    0,  123,  124,  125,
  126,    0,  136,    0,  121,  122,   63,   63,  123,  124,
  125,  126,   62,  136,   65,   62,   66,    0,   64,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   62,
   62,    0,    0,    0,    0,   89,    0,   89,   89,    0,
    0,   63,    0,    0,    0,  121,  122,    0,    0,  123,
  124,  125,  126,    0,  136,    0,   53,   53,    0,    0,
   53,   53,   53,   53,   62,   53,    0,    0,   89,   89,
    0,    0,    0,    0,    0,    0,    0,  121,    0,    0,
   89,  123,  124,  125,  126,    0,  136,   56,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   56,
   56,    0,    0,   56,   56,   56,   56,   57,   56,    0,
    0,    0,    0,  123,  124,    0,    0,    0,  136,   57,
   57,    0,    0,   57,   57,   57,   57,    0,   57,    0,
    0,    0,   80,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   80,   80,    0,    0,   80,   80,
   80,   80,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   65,    0,   66,    0,
   64,    0,    0,    0,    0,    0,    0,    0,   65,   65,
   66,   66,   64,   64,   65,   65,   66,   66,   64,   64,
    0,    0,    0,   63,    0,    0,    0,    0,    0,   97,
    0,    0,    0,    0,    0,   63,   63,  106,  107,  109,
    0,   63,   63,    0,    0,    0,   62,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   62,   62,
    0,    0,    0,    0,  139,    0,  141,  142,    0,    0,
    0,    0,    0,  147,    0,    0,  151,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  160,  161,  162,  163,  164,  165,  166,  167,
  168,  169,  170,  171,  172,    0,  173,  174,  175,    0,
    0,    0,    0,    0,    0,    0,    0,  184,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  147,    0,  198,
    0,    0,    0,  142,    0,    0,  203,    0,    0,  205,
    0,  206,    0,    0,  207,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  217,
    0,    0,    0,    0,    0,    0,  220,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   91,   59,   37,   59,   40,  125,   94,   42,
   43,   45,   45,   46,   47,  125,   41,   41,  125,   40,
   91,   41,   69,   46,   44,   59,   37,  177,  261,  179,
  180,   42,   43,  276,   45,   46,   47,   33,   58,   59,
   15,   41,   37,   45,   40,   47,   41,   42,   43,   45,
   45,   46,   47,   28,  261,  263,  289,   91,   91,   59,
  276,  211,  276,   91,   59,   60,  123,   62,   91,   36,
   15,   38,  222,   93,  123,   33,   37,   22,  276,   46,
   91,   42,   40,   28,   40,   46,   47,   45,   91,  123,
   93,  125,   40,   59,   93,   91,   91,   33,   41,   41,
   41,   44,  123,   44,   40,   44,   41,  154,  123,   45,
   41,   33,   40,   44,   40,   60,   40,   40,   40,   33,
   40,   40,   40,   45,  210,  276,   40,  123,   40,  125,
   91,   45,   59,   91,   59,  276,   40,   37,   59,   91,
   41,   41,   42,   43,   44,   45,   46,   47,   59,   59,
  257,  258,  259,  260,  261,   91,   61,   41,   58,   59,
   60,   61,   62,  276,   44,  123,  276,  276,   40,   91,
  115,   93,  279,  291,   59,   41,   93,   91,   41,   44,
  276,  291,  293,  276,  292,  276,  276,  276,   44,   41,
  268,   91,   41,   93,   37,   44,   41,   41,   41,   42,
   43,   44,   45,   46,   47,  276,    0,  123,   59,   58,
   59,   41,   41,  276,  216,   58,   59,   60,   61,   62,
  276,  223,  276,  257,  258,  259,  260,  261,  262,   93,
  264,  265,  266,  267,  262,  269,  270,  271,  272,  273,
  274,  275,  276,   59,   93,  265,  280,  275,   91,   41,
   93,  276,  276,  286,  288,    4,  290,  291,  278,  262,
  294,  257,  258,  259,  260,  261,  262,   11,  264,  265,
  266,  267,  275,  269,  270,  271,  272,  273,  274,  275,
   16,   38,  277,  278,  280,  176,  281,  282,  283,  284,
  182,  286,  288,  154,  290,   -1,   -1,   -1,  294,  257,
  258,  259,  260,  261,  262,   -1,  264,  265,  266,  267,
   -1,  269,  270,  271,  272,  273,  274,  275,   -1,   -1,
   -1,  125,  280,   -1,   -1,  261,  262,   -1,  264,   -1,
  288,   -1,  290,   -1,   -1,  271,  294,  273,  274,  275,
  262,   -1,  264,   -1,  280,   -1,   -1,   -1,  262,  271,
  264,  273,  274,  275,  290,   -1,   -1,  271,  280,  273,
  274,  275,   -1,   -1,   -1,  265,  280,   -1,  290,  257,
  258,  259,  260,  261,   -1,   -1,  290,  277,  278,   52,
   -1,  281,  282,  283,  284,   -1,  286,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,   -1,  290,   -1,  257,  258,  259,  260,  261,   58,
   59,   60,   -1,   62,   -1,   -1,  265,   -1,   -1,   -1,
   -1,   94,  265,  276,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   37,  286,   93,   -1,   41,   42,   43,   44,
   45,   -1,   47,  257,  258,  259,  260,  261,  257,  258,
  259,  260,  261,   58,   59,   60,   37,   62,   -1,   -1,
   41,   42,   43,   44,   45,  279,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   60,
   -1,   62,   -1,   41,   -1,   37,   44,   -1,   93,   41,
   42,   43,   44,   45,  177,   47,  179,  180,   -1,   -1,
   58,   59,   -1,   -1,   -1,   -1,   58,   59,   60,   37,
   62,   -1,   93,   41,   42,   43,   44,   45,   -1,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  210,  211,   -1,
   58,   59,   60,   -1,   62,   93,   -1,   -1,   37,  222,
   -1,   93,   41,   42,   43,   -1,   45,   46,   47,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   60,   -1,   62,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   -1,   62,   37,   -1,   -1,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   60,   -1,
   62,   -1,   -1,   91,   -1,   37,  265,   -1,   -1,   41,
   42,   43,   -1,   45,   46,   47,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,   37,  286,   60,   91,
   62,   42,   43,   37,   45,   46,   47,   -1,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   58,   -1,   60,
   -1,   62,   -1,   -1,   -1,   -1,   60,   -1,   62,   91,
  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,
   91,  286,   -1,   -1,  265,   37,   -1,   91,   -1,   -1,
   42,   43,   44,   45,   46,   47,  277,  278,   -1,   -1,
  281,  282,  283,  284,   -1,  286,   -1,  265,   60,   -1,
   62,   -1,   -1,  265,   -1,   -1,   -1,   -1,   -1,  277,
  278,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,  286,   -1,   -1,  265,   37,   91,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,  277,
  278,   -1,   -1,  281,  282,  283,  284,   -1,  286,   -1,
   59,   60,   -1,   62,   -1,   -1,  265,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,   -1,  286,   -1,  277,
  278,   -1,   91,  281,  282,  283,  284,   -1,  286,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,  286,   60,   -1,   62,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   41,  277,  278,   44,   -1,  281,
  282,  283,  284,   60,  286,   62,   91,   -1,   93,   -1,
   -1,   58,   59,   60,   -1,   62,  277,  278,   -1,   -1,
  281,  282,  283,  284,   -1,  286,   -1,  281,  282,  283,
  284,   -1,  286,   37,   91,   -1,   93,   41,   42,   43,
   -1,   45,   46,   47,   37,   -1,   93,   -1,   41,   42,
   43,   -1,   45,   46,   47,   -1,   60,   -1,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,  286,   37,   -1,   91,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   37,   -1,   91,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,   60,
   -1,   62,   42,   43,   -1,   45,   46,   47,  277,  278,
   -1,   -1,  281,  282,  283,  284,   -1,  286,   -1,   91,
   60,   -1,   62,   41,   -1,   43,   44,   45,   -1,   37,
   91,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   58,   59,   60,   41,   62,   43,   44,   45,   -1,   -1,
   -1,   91,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   -1,   62,   -1,   -1,   -1,   41,   -1,
   52,   44,  277,  278,   -1,   93,  281,  282,  283,  284,
   -1,  286,   -1,   91,   -1,   58,   59,   60,   -1,   62,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,  265,   -1,
  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,  286,
  277,  278,   94,   -1,  281,  282,  283,  284,   -1,  286,
   93,   -1,   41,   -1,   41,   44,   41,   44,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   58,   59,   58,   59,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,  277,  278,   -1,   -1,  281,  282,  283,
  284,   -1,  286,   -1,  277,  278,   58,   59,  281,  282,
  283,  284,   41,  286,   93,   44,   93,   -1,   93,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,
   59,   -1,   -1,   -1,   -1,  177,   -1,  179,  180,   -1,
   -1,   93,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,  286,   -1,  277,  278,   -1,   -1,
  281,  282,  283,  284,   93,  286,   -1,   -1,  210,  211,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,
  222,  281,  282,  283,  284,   -1,  286,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,
  278,   -1,   -1,  281,  282,  283,  284,  265,  286,   -1,
   -1,   -1,   -1,  281,  282,   -1,   -1,   -1,  286,  277,
  278,   -1,   -1,  281,  282,  283,  284,   -1,  286,   -1,
   -1,   -1,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  265,   -1,  265,   -1,
  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
  277,  278,  277,  278,  283,  284,  283,  284,  283,  284,
   -1,   -1,   -1,  265,   -1,   -1,   -1,   -1,   -1,   58,
   -1,   -1,   -1,   -1,   -1,  277,  278,   66,   67,   68,
   -1,  283,  284,   -1,   -1,   -1,  265,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,   -1,   -1,   93,   -1,   95,   96,   -1,   -1,
   -1,   -1,   -1,  102,   -1,   -1,  105,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  121,  122,  123,  124,  125,  126,  127,  128,
  129,  130,  131,  132,  133,   -1,  135,  136,  137,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  146,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  176,   -1,  178,
   -1,   -1,   -1,  182,   -1,   -1,  185,   -1,   -1,  188,
   -1,  190,   -1,   -1,  193,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  208,
   -1,   -1,   -1,   -1,   -1,   -1,  215,
};
}
final static short YYFINAL=3;
final static short YYMAXTOKEN=297;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"VOID","BOOL","INT","STRING",
"CLASS","NULL","EXTENDS","THIS","WHILE","FOR","IF","ELSE","RETURN","BREAK",
"NEW","PRINT","READ_INTEGER","READ_LINE","LITERAL","IDENTIFIER","AND","OR",
"STATIC","INSTANCEOF","LESS_EQUAL","GREATER_EQUAL","EQUAL","NOT_EQUAL","IFOR",
"MOMO","PLUSPLUS","SCOPY","SEALED","VAR","GUARD","DEFAULT","IN","FOREACH",
"\"|||\"","UMINUS","EMPTY",
};
final static String yyrule[] = {
"$accept : Program",
"Program : ClassList",
"ClassList : ClassList ClassDef",
"ClassList : ClassDef",
"VariableDef : Variable ';'",
"Variable : Type IDENTIFIER",
"Type : INT",
"Type : VOID",
"Type : BOOL",
"Type : STRING",
"Type : CLASS IDENTIFIER",
"Type : Type '[' ']'",
"ClassDef : CLASS IDENTIFIER ExtendsClause '{' FieldList '}'",
"ClassDef : SEALED CLASS IDENTIFIER ExtendsClause '{' FieldList '}'",
"ExtendsClause : EXTENDS IDENTIFIER",
"ExtendsClause :",
"FieldList : FieldList VariableDef",
"FieldList : FieldList FunctionDef",
"FieldList :",
"Formals : VariableList",
"Formals :",
"VariableList : VariableList ',' Variable",
"VariableList : Variable",
"FunctionDef : STATIC Type IDENTIFIER '(' Formals ')' StmtBlock",
"FunctionDef : Type IDENTIFIER '(' Formals ')' StmtBlock",
"StmtBlock : '{' StmtList '}'",
"StmtList : StmtList Stmt",
"StmtList :",
"Stmt : VariableDef",
"Stmt : SimpleStmt ';'",
"Stmt : IfStmt",
"Stmt : WhileStmt",
"Stmt : ForStmt",
"Stmt : ReturnStmt ';'",
"Stmt : PrintStmt ';'",
"Stmt : BreakStmt ';'",
"Stmt : StmtBlock",
"Stmt : OCStmt ';'",
"Stmt : GuardedStmt",
"Stmt : ForeachStmt",
"ForeachStmt : FOREACH '(' BoundVariable IN Expr WHILE Expr ')' StmtBlock",
"ForeachStmt : FOREACH '(' BoundVariable IN Expr ')' StmtBlock",
"BoundVariable : Type IDENTIFIER",
"BoundVariable : VAR IDENTIFIER",
"SimpleStmt : LValue '=' Expr",
"SimpleStmt : Call",
"SimpleStmt :",
"Receiver : Expr '.'",
"Receiver :",
"LValue : Receiver IDENTIFIER",
"LValue : Expr '[' Expr ']'",
"LValue : VAR IDENTIFIER",
"Call : Receiver IDENTIFIER '(' Actuals ')'",
"Expr : LValue",
"Expr : Call",
"Expr : Constant",
"Expr : Expr '+' Expr",
"Expr : Expr '-' Expr",
"Expr : Expr '*' Expr",
"Expr : Expr '/' Expr",
"Expr : Expr '%' Expr",
"Expr : Expr EQUAL Expr",
"Expr : Expr NOT_EQUAL Expr",
"Expr : Expr '<' Expr",
"Expr : Expr '>' Expr",
"Expr : Expr LESS_EQUAL Expr",
"Expr : Expr GREATER_EQUAL Expr",
"Expr : Expr AND Expr",
"Expr : Expr OR Expr",
"Expr : '(' Expr ')'",
"Expr : '-' Expr",
"Expr : '!' Expr",
"Expr : READ_INTEGER '(' ')'",
"Expr : READ_LINE '(' ')'",
"Expr : THIS",
"Expr : NEW IDENTIFIER '(' ')'",
"Expr : NEW Type '[' Expr ']'",
"Expr : INSTANCEOF '(' Expr ',' IDENTIFIER ')'",
"Expr : '(' CLASS IDENTIFIER ')' Expr",
"Expr : Expr MOMO Expr",
"Expr : Expr '[' Expr ']' DEFAULT Expr",
"Constant : LITERAL",
"Constant : NULL",
"Constant : ArrayConstant",
"Actuals : ExprList",
"Actuals :",
"ExprList : ExprList ',' Expr",
"ExprList : Expr",
"WhileStmt : WHILE '(' Expr ')' Stmt",
"ForStmt : FOR '(' SimpleStmt ';' Expr ';' SimpleStmt ')' Stmt",
"BreakStmt : BREAK",
"IfStmt : IF '(' Expr ')' Stmt ElseClause",
"ElseClause : ELSE Stmt",
"ElseClause :",
"ReturnStmt : RETURN Expr",
"ReturnStmt : RETURN",
"PrintStmt : PRINT '(' ExprList ')'",
"OCStmt : SCOPY '(' IDENTIFIER ',' Expr ')'",
"GuardedStmt : IF '{' IfBranches '}'",
"IfBranches : IfBranches GUARD IfSubStmt",
"IfBranches : IfSubStmt",
"IfBranches :",
"IfSubStmt : Expr ':' Stmt",
"ArrayConstant : '[' Constants ']'",
"ArrayConstant : '[' ']'",
"Constants : Constant ',' Constants",
"Constants : Constant",
};

//#line 569 "Parser.y"
    
	/**
	 * 打印当前归约所用的语法规则<br>
	 * 请勿修改。
	 */
    public boolean onReduce(String rule) {
		if (rule.startsWith("$$"))
			return false;
		else
			rule = rule.replaceAll(" \\$\\$\\d+", "");

   	    if (rule.endsWith(":"))
    	    System.out.println(rule + " <empty>");
   	    else
			System.out.println(rule);
		return false;
    }
    
    public void diagnose() {
		addReduceListener(this);
		yyparse();
	}
//#line 734 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    //if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      //if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        //if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        //if (yychar < 0)    //it it didn't work/error
        //  {
        //  yychar = 0;      //change it to default string (no -1!)
          //if (yydebug)
          //  yylexdebug(yystate,yychar);
        //  }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        //if (yydebug)
          //debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      //if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0 || valptr<0)   //check for under & overflow here
            {
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            //if (yydebug)
              //debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            //if (yydebug)
              //debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0 || valptr<0)   //check for under & overflow here
              {
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        //if (yydebug)
          //{
          //yys = null;
          //if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          //if (yys == null) yys = "illegal-symbol";
          //debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          //}
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    //if (yydebug)
      //debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    if (reduceListener == null || reduceListener.onReduce(yyrule[yyn])) // if intercepted!
      switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 60 "Parser.y"
{
						tree = new Tree.TopLevel(val_peek(0).clist, val_peek(0).loc);
					}
break;
case 2:
//#line 66 "Parser.y"
{
						yyval.clist.add(val_peek(0).cdef);
					}
break;
case 3:
//#line 70 "Parser.y"
{
                		yyval.clist = new ArrayList<Tree.ClassDef>();
                		yyval.clist.add(val_peek(0).cdef);
                	}
break;
case 5:
//#line 80 "Parser.y"
{
						yyval.vdef = new Tree.VarDef(val_peek(0).ident, val_peek(1).type, val_peek(0).loc);
					}
break;
case 6:
//#line 86 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.INT, val_peek(0).loc);
					}
break;
case 7:
//#line 90 "Parser.y"
{
                		yyval.type = new Tree.TypeIdent(Tree.VOID, val_peek(0).loc);
                	}
break;
case 8:
//#line 94 "Parser.y"
{
                		yyval.type = new Tree.TypeIdent(Tree.BOOL, val_peek(0).loc);
                	}
break;
case 9:
//#line 98 "Parser.y"
{
                		yyval.type = new Tree.TypeIdent(Tree.STRING, val_peek(0).loc);
                	}
break;
case 10:
//#line 102 "Parser.y"
{
                		yyval.type = new Tree.TypeClass(val_peek(0).ident, val_peek(1).loc);
                	}
break;
case 11:
//#line 106 "Parser.y"
{
                		yyval.type = new Tree.TypeArray(val_peek(2).type, val_peek(2).loc);
                	}
break;
case 12:
//#line 112 "Parser.y"
{
						yyval.cdef = new Tree.ClassDef(val_peek(4).ident, val_peek(3).ident, val_peek(1).flist, val_peek(5).loc);
					}
break;
case 13:
//#line 116 "Parser.y"
{
						yyval.cdef = new Tree.ClassDef(val_peek(4).ident, val_peek(3).ident, val_peek(1).flist, val_peek(6).loc);
						yyval.cdef.isSealed = true;
					}
break;
case 14:
//#line 123 "Parser.y"
{
						yyval.ident = val_peek(0).ident;
					}
break;
case 15:
//#line 127 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 16:
//#line 133 "Parser.y"
{
						yyval.flist.add(val_peek(0).vdef);
					}
break;
case 17:
//#line 137 "Parser.y"
{
						yyval.flist.add(val_peek(0).fdef);
					}
break;
case 18:
//#line 141 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.flist = new ArrayList<Tree>();
                	}
break;
case 20:
//#line 149 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.vlist = new ArrayList<Tree.VarDef>(); 
                	}
break;
case 21:
//#line 156 "Parser.y"
{
						yyval.vlist.add(val_peek(0).vdef);
					}
break;
case 22:
//#line 160 "Parser.y"
{
                		yyval.vlist = new ArrayList<Tree.VarDef>();
						yyval.vlist.add(val_peek(0).vdef);
                	}
break;
case 23:
//#line 167 "Parser.y"
{
						yyval.fdef = new MethodDef(true, val_peek(4).ident, val_peek(5).type, val_peek(2).vlist, (Block) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 24:
//#line 171 "Parser.y"
{
						yyval.fdef = new MethodDef(false, val_peek(4).ident, val_peek(5).type, val_peek(2).vlist, (Block) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 25:
//#line 177 "Parser.y"
{
						yyval.stmt = new Block(val_peek(1).slist, val_peek(2).loc);
						yyval.slist = val_peek(1).slist;
					}
break;
case 26:
//#line 184 "Parser.y"
{
						yyval.slist.add(val_peek(0).stmt);
					}
break;
case 27:
//#line 188 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.slist = new ArrayList<Tree>();
                	}
break;
case 28:
//#line 195 "Parser.y"
{
						yyval.stmt = val_peek(0).vdef;
					}
break;
case 29:
//#line 200 "Parser.y"
{
                		if (yyval.stmt == null) {
                			yyval.stmt = new Tree.Skip(val_peek(0).loc);
                		}
                	}
break;
case 30:
//#line 206 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 31:
//#line 210 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 32:
//#line 214 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 33:
//#line 218 "Parser.y"
{
						yyval.stmt = val_peek(1).stmt;
					}
break;
case 34:
//#line 222 "Parser.y"
{
						yyval.stmt = val_peek(1).stmt;
					}
break;
case 35:
//#line 226 "Parser.y"
{
						yyval.stmt = val_peek(1).stmt;
					}
break;
case 36:
//#line 230 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 37:
//#line 234 "Parser.y"
{
						yyval.stmt = val_peek(1).stmt;
					}
break;
case 38:
//#line 238 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 40:
//#line 245 "Parser.y"
{
						yyval.stmt = new Tree.ForeachStmt(val_peek(6).vdef, val_peek(4).expr, val_peek(2).expr, val_peek(0).slist, val_peek(0).stmt.loc, val_peek(8).loc);
					}
break;
case 41:
//#line 249 "Parser.y"
{
						yyval.stmt = new Tree.ForeachStmt(val_peek(4).vdef, val_peek(2).expr, null, val_peek(0).slist, val_peek(0).stmt.loc, val_peek(6).loc);
					}
break;
case 42:
//#line 255 "Parser.y"
{
						yyval.vdef = new Tree.BoundVariable(val_peek(0).ident, val_peek(1).type, val_peek(1).loc);
					}
break;
case 43:
//#line 259 "Parser.y"
{
						yyval.vdef = new Tree.BoundVariable(val_peek(0).ident, new Tree.TypeVar(val_peek(1).loc), val_peek(1).loc);
					}
break;
case 44:
//#line 265 "Parser.y"
{
						yyval.stmt = new Tree.Assign(val_peek(2).lvalue, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 45:
//#line 269 "Parser.y"
{
                		yyval.stmt = new Tree.Exec(val_peek(0).expr, val_peek(0).loc);
                	}
break;
case 46:
//#line 273 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 47:
//#line 279 "Parser.y"
{
						yyval.expr = val_peek(1).expr;
					}
break;
case 48:
//#line 283 "Parser.y"
{
                		yyval = new SemValue();
                	}
break;
case 49:
//#line 289 "Parser.y"
{
						yyval.lvalue = new Tree.Ident(val_peek(1).expr, val_peek(0).ident, val_peek(0).loc);
						if (val_peek(1).loc == null) {
							yyval.loc = val_peek(0).loc;
						}
					}
break;
case 50:
//#line 296 "Parser.y"
{
                		yyval.lvalue = new Tree.Indexed(val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
                	}
break;
case 51:
//#line 300 "Parser.y"
{
                		yyval.lvalue = new Tree.VarIdent(val_peek(0).ident, val_peek(0).loc);
                	}
break;
case 52:
//#line 306 "Parser.y"
{
						yyval.expr = new Tree.CallExpr(val_peek(4).expr, val_peek(3).ident, val_peek(1).elist, val_peek(3).loc);
						if (val_peek(4).loc == null) {
							yyval.loc = val_peek(3).loc;
						}
					}
break;
case 53:
//#line 315 "Parser.y"
{
						yyval.expr = val_peek(0).lvalue;
					}
break;
case 54:
//#line 319 "Parser.y"
{
						yyval.expr = val_peek(0).expr;
					}
break;
case 55:
//#line 323 "Parser.y"
{
						yyval.expr = val_peek(0).expr;
					}
break;
case 56:
//#line 327 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.PLUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 57:
//#line 331 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.MINUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 58:
//#line 335 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.MUL, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 59:
//#line 339 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.DIV, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 60:
//#line 343 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.MOD, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 61:
//#line 347 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.EQ, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 62:
//#line 351 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.NE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 63:
//#line 355 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.LT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 64:
//#line 359 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.GT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 65:
//#line 363 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.LE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 66:
//#line 367 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.GE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 67:
//#line 371 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.AND, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 68:
//#line 375 "Parser.y"
{
                		yyval.expr = new Tree.Binary(Tree.OR, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 69:
//#line 379 "Parser.y"
{
                		yyval = val_peek(1);
                	}
break;
case 70:
//#line 383 "Parser.y"
{
                		yyval.expr = new Tree.Unary(Tree.NEG, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 71:
//#line 387 "Parser.y"
{
                		yyval.expr = new Tree.Unary(Tree.NOT, val_peek(0).expr, val_peek(1).loc);
                	}
break;
case 72:
//#line 391 "Parser.y"
{
                		yyval.expr = new Tree.ReadIntExpr(val_peek(2).loc);
                	}
break;
case 73:
//#line 395 "Parser.y"
{
                		yyval.expr = new Tree.ReadLineExpr(val_peek(2).loc);
                	}
break;
case 74:
//#line 399 "Parser.y"
{
                		yyval.expr = new Tree.ThisExpr(val_peek(0).loc);
                	}
break;
case 75:
//#line 403 "Parser.y"
{
                		yyval.expr = new Tree.NewClass(val_peek(2).ident, val_peek(3).loc);
                	}
break;
case 76:
//#line 407 "Parser.y"
{
                		yyval.expr = new Tree.NewArray(val_peek(3).type, val_peek(1).expr, val_peek(4).loc);
                	}
break;
case 77:
//#line 411 "Parser.y"
{
                		yyval.expr = new Tree.TypeTest(val_peek(3).expr, val_peek(1).ident, val_peek(5).loc);
                	}
break;
case 78:
//#line 415 "Parser.y"
{
                		yyval.expr = new Tree.TypeCast(val_peek(2).ident, val_peek(0).expr, val_peek(0).loc);
                	}
break;
case 79:
//#line 419 "Parser.y"
{
                		yyval.expr = new Tree.ArrayRepeat(val_peek(2).expr,val_peek(0).expr,val_peek(2).loc);
                	}
break;
case 80:
//#line 423 "Parser.y"
{
                		yyval.expr = new Tree.ArrayDefault(val_peek(5).expr,val_peek(3).expr,val_peek(0).expr,val_peek(3).loc);
                	}
break;
case 81:
//#line 430 "Parser.y"
{
						yyval.expr = new Tree.Literal(val_peek(0).typeTag, val_peek(0).literal, val_peek(0).loc);
					}
break;
case 82:
//#line 434 "Parser.y"
{
						yyval.expr = new Null(val_peek(0).loc);
					}
break;
case 83:
//#line 438 "Parser.y"
{
						yyval.expr = val_peek(0).expr;
					}
break;
case 85:
//#line 445 "Parser.y"
{
                		yyval = new SemValue();
                		yyval.elist = new ArrayList<Tree.Expr>();
                	}
break;
case 86:
//#line 452 "Parser.y"
{
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 87:
//#line 456 "Parser.y"
{
                		yyval.elist = new ArrayList<Tree.Expr>();
						yyval.elist.add(val_peek(0).expr);
                	}
break;
case 88:
//#line 463 "Parser.y"
{
						yyval.stmt = new Tree.WhileLoop(val_peek(2).expr, val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 89:
//#line 469 "Parser.y"
{
						yyval.stmt = new Tree.ForLoop(val_peek(6).stmt, val_peek(4).expr, val_peek(2).stmt, val_peek(0).stmt, val_peek(8).loc);
					}
break;
case 90:
//#line 475 "Parser.y"
{
						yyval.stmt = new Tree.Break(val_peek(0).loc);
					}
break;
case 91:
//#line 481 "Parser.y"
{
						yyval.stmt = new Tree.If(val_peek(3).expr, val_peek(1).stmt, val_peek(0).stmt, val_peek(5).loc);
					}
break;
case 92:
//#line 487 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 93:
//#line 491 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 94:
//#line 497 "Parser.y"
{
						yyval.stmt = new Tree.Return(val_peek(0).expr, val_peek(1).loc);
					}
break;
case 95:
//#line 501 "Parser.y"
{
                		yyval.stmt = new Tree.Return(null, val_peek(0).loc);
                	}
break;
case 96:
//#line 507 "Parser.y"
{
						yyval.stmt = new Print(val_peek(1).elist, val_peek(3).loc);
					}
break;
case 97:
//#line 513 "Parser.y"
{
						yyval.stmt = new Tree.ScopyClass(val_peek(3).ident,val_peek(1).expr,val_peek(5).loc,val_peek(3).loc);
					}
break;
case 98:
//#line 519 "Parser.y"
{
						yyval.stmt = new Tree.GuardedStmt(val_peek(1).elist,val_peek(3).loc);
					}
break;
case 99:
//#line 524 "Parser.y"
{
						yyval.elist = val_peek(2).elist;
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 100:
//#line 529 "Parser.y"
{
						yyval.elist = new ArrayList<Expr>();
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 101:
//#line 534 "Parser.y"
{
						yyval = new SemValue();	
					}
break;
case 102:
//#line 540 "Parser.y"
{
						yyval.expr = new Tree.IfSubStmt(val_peek(2).expr,val_peek(0).stmt,val_peek(2).loc);
					}
break;
case 103:
//#line 546 "Parser.y"
{
						yyval.elist = val_peek(1).elist;
						yyval.expr = new Tree.ArrayConstant(val_peek(1).elist,val_peek(2).loc);
					}
break;
case 104:
//#line 551 "Parser.y"
{
						yyval.expr = new Tree.ArrayConstant(null,val_peek(1).loc);
					}
break;
case 105:
//#line 557 "Parser.y"
{
						yyval.elist = val_peek(0).elist;
						yyval.elist.add(0,val_peek(2).expr);
					}
break;
case 106:
//#line 562 "Parser.y"
{
						yyval.elist = new ArrayList<Tree.Expr>();
						yyval.elist.add(val_peek(0).expr);
					}
break;
//#line 1514 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    //if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      //if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        //if (yychar<0) yychar=0;  //clean, if necessary
        //if (yydebug)
          //yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      //if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
