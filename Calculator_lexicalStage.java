// Compilers course project, 1445-third semester, 2024 may.
//Develop a lexical analyzer program to scan and recognize a CalcLanguage. This language simulates a calculator to get and evaluate an arithmetic expression (which is just a string). 

package calculator_lexicalstage;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Stack;
/**
 *
 * @author bedoo
 */
public class Calculator_lexicalStage {
    static String lexeme = ""; 

    public static void main(String[] args) throws Invalid_Identifier_Name_Exception, ParseException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Hello, here is java calculator\nPlease enter your equation:\n"); //welcome sentence
    String equation = scanner.nextLine() + " $"; // save the user input
    
    Stack<Character> chars = new Stack<>(); // to save input from tha user as character in staack
    for(int i=0; i<equation.length(); i++){ // moving through input from index 0 to its length
        chars.push(equation.charAt(i)); // push every character-in user input- at index(..) to the stack
    }
    
    String[] lexemes = new String[100]; // now we will initiate new array to save input as lexemes 
    char chara;
    int i = 0;
    while (!chars.isEmpty()){ // move throw 'chars' stack
        chara = chars.pop();
        //System.out.println(chara);
        if (chara == ' '){ // the white space means the lexeme ends.
        lexemes[i]=lexeme; // push the new lexeme to tha stack
        //System.out.println(lexemes[i]+i);
        lexeme = "";
        i++;
        //System.out.println(lexeme);
        }
        else{
        lexeme = chara + lexeme; // else, add the character to complete lexeme
        }
    }
    lexemes[i] = lexeme ; // if the stack is empty, the loop will stop and the last lexeme won`t be added, so we added after the loop stop
    i++;
    
    Token token = new Token (lexemes, i); // create tokens, i = the number elements added to the array used
    
    System.out.println("\nlist of Tokens:"); // print tokens

    for (int j = 0; j < Token.used; j++){
        System.out.println(Token.tokens[j].toString());
    }
    
    System.out.println(""); // to add empty line between to lists

    System.out.println("Symbol Table:"); // print symbol table
    SymbolTable.displaySymbolTable();
    
    scanner.close();
    }
}
