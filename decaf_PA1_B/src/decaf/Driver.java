package decaf;

import java.io.IOException;
import java.util.*;

import decaf.tree.Tree;
import decaf.error.DecafError;
import decaf.frontend.Lexer;
import decaf.frontend.Parser;
import decaf.utils.IndentPrintWriter;

public final class Driver {

    private static Driver driver;

    private Option option;

    private Set<DecafError> errors;

    private Lexer lexer;

    private Parser parser;

    public static Driver getDriver() {
        return driver;
    }

    public void issueError(DecafError error) {
        errors.add(error);
    }

    public void checkPoint() {
        if (errors.size() > 0) {
            for (DecafError error : errors) {
                option.getErr().println(error);
            }
            System.exit(1);
        }
    }

    private void init() {
        lexer = new Lexer(option.getInput());
        parser = new Parser();
        lexer.setParser(parser);
        parser.setLexer(lexer);
        errors = new TreeSet<>(new Comparator<DecafError>() {
            @Override
            public int compare(DecafError e1, DecafError e2) {
                return e1.getLocation().compareTo(e2.getLocation());
            }
        });
//		parser.diagnose();
    }

    private void compile() {
        Tree.TopLevel tree = parser.parseFile();
        checkPoint();
        if (option.getLevel() == Option.Level.LEVEL0) {
            IndentPrintWriter pw = new IndentPrintWriter(option.getOutput(), 4);
            tree.printTo(pw);
            pw.close();
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        driver = new Driver();
        driver.option = new Option(args);//设置编译输入和输出
        driver.init();//设置词法分析器和语法分析器并结合二者
        driver.compile();//开始编译
    }

    public Option getOption() {
        return option;
    }
}
