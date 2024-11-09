package calculator_lexicalstage;

import java.util.HashMap;
// The rest of the classes (Identifier_Token, Invalid_Identifier_Name, Number_Token, Operator_Token, SymbolTable, Token, Stop_Token) remain the same as previously defined.

import java.util.Map;
import java.util.Stack;

/**
 *
 * @author bedoo
 */
public class LL1Parser {
    private Stack<String> stack = new Stack<>();
    private String[] input;
    private int index = 0;

    private static final Map<String, String[]> parsingTable = new HashMap<>();

    static {
        // Populate the parsing table (simplified for the sake of brevity)
        parsingTable.put("PROGRAM", new String[] {"STMTS"});
        parsingTable.put("STMTS", new String[] {"STMT", "STMTS_PRIME"});
        parsingTable.put("STMTS_PRIME", new String[] {";", "STMT", "STMTS_PRIME", "ε"});
        parsingTable.put("STMT", new String[] {"id", "=", "EXPR"});
        parsingTable.put("EXPR", new String[] {"TERM", "EXPR_PRIME"});
        parsingTable.put("EXPR_PRIME", new String[] {"+", "TERM", "EXPR_PRIME", "-", "TERM", "EXPR_PRIME", "ε"});
        parsingTable.put("TERM", new String[] {"FACTOR", "TERM_PRIME"});
        parsingTable.put("TERM_PRIME", new String[] {"*", "FACTOR", "TERM_PRIME", "/", "FACTOR", "TERM_PRIME", "ε"});
        parsingTable.put("FACTOR", new String[] {"(", "EXPR", ")", "id", "integer", "+", "FACTOR", "-", "FACTOR", "FACTOR_PRIME"});
        parsingTable.put("FACTOR_PRIME", new String[] {"^", "FACTOR", "ε"});
    }

    public LL1Parser(String[] input) {
        this.input = input;
        stack.push("PROGRAM");
    }

    public void parse() {
        while (!stack.isEmpty()) {
            String top = stack.pop();
            if (isNonTerminal(top)) {
                String[] production = getProduction(top, input[index]);
                if (production != null) {
                    for (int i = production.length - 1; i >= 0; i--) {
                        stack.push(production[i]);
                    }
                } else {
                    throw new RuntimeException("Syntax error at: " + input[index]);
                }
            } else if (top.equals(input[index])) {
                index++;
            } else if (top.equals("ε")) {
                // Epsilon, do nothing
            } else {
                throw new RuntimeException("Syntax error at: " + input[index]);
            }
        }

        if (index != input.length) {
            throw new RuntimeException("Syntax error at end of input");
        }

        System.out.println("Parsing successful");
    }

    private boolean isNonTerminal(String symbol) {
        return parsingTable.containsKey(symbol);
    }

    private String[] getProduction(String nonTerminal, String terminal) {
        // This is a simplified way to get the production based on the table
        String[] productions = parsingTable.get(nonTerminal);
        for (String prod : productions) {
            if (prod.contains(terminal) || prod.equals("ε")) {
                return prod.split(" ");
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String equation = "a = b + c ; d = e ^ f $";
        String[] tokens = equation.split(" ");

        LL1Parser parser = new LL1Parser(tokens);
        parser.parse();
    }
    
}
