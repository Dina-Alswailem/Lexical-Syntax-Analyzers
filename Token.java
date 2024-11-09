// The class will receive a lexemes array, its role to initiate a token for every element in the stack, display error handleres, and manages the symbol table.
package calculator_lexicalstage;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

/**
 *
 * @author bedoo
 */
public class Token {
    
    String[] lexemes = new String[100]; // initiate new array to save the received array from the constructor
    static Object[] tokens = new Object[100];
    static int used = 0; // to know how many objects we creates and added to tokens array.
    int size; // number of elements added to the lexemes array
    
    public Token(String[] array, int size) throws Invalid_Identifier_Name_Exception, ParseException{
        lexemes = array;
        this.size = size;
        createTokens();
    }
    
    private void createTokens() throws Invalid_Identifier_Name_Exception, ParseException{
        //System.out.println(lexemes);
        int i;
        for (i = size - 1; i > 0; i--){
            String s = lexemes[i];
            //System.out.print(s+" ");
            //System.out.println(s.length());
            //if (s.length() == 1){
                if (isNumber(s)){
                    //System.out.println(isNumber(s));
                    tokens[used] = new Number_Token(s);
                    used ++;
                }
                else if (isOperator(s)){
                    tokens[used] = new Operator_Token(s);
                    used ++;
                }
                
            //}
            else if (isIdentifier(s)){
                tokens[used] = new Identifier_Token(s);
                used ++;
            }
            else{
                return;
            }
        }
        if (lexemes[i].equals("$")){
        tokens[used] = new Stop_Token();
        used ++;
        }
    }
        
    public static boolean isNumber(String s){ // checks if the received string is number or not, and return boolean value.
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(s, pos);
        return s.length() == pos.getIndex();           
    }
        
    public static boolean isOperator(String s){ // checks if the received string is operator or not, and return boolean value.
        return switch (s) {
            case "+", "-", "/", "*", "%", "^", "=" -> true;
            default -> false;
        };
        
    }
    
        
    public static boolean isIdentifier(String s) throws Invalid_Identifier_Name_Exception{ // checks if the string 's' does not violate identifier roles.
        int v = s.charAt(0) * 1; // the integer value of character at index 0.
        if (isNumber(Character.toString(s.charAt(0)))){ // it returns true if the first character in the s is number.
            throw new Invalid_Identifier_Name_Exception("The identifier name cannot start by number! "+s);
        }
        else if ( v > 122 | v < 65 | ( v > 90 && v < 97 ) && s.charAt(0) != '_'){ // if the value out of these domains, it means that it is not a value of alphapet character.
            throw new Invalid_Identifier_Name_Exception("The identifier name cannot begin with a sign other than '_'! "+s);
        }
        else{
            for (int i = 1; i < s.length(); i++){
                v = s.charAt(i) * 1;
                if (!isNumber(Character.toString(s.charAt(i))) &&  (v > 122 | v < 65 | ( v > 90 && v < 97 )) && s.charAt(i) != '_'){ // checks if the character at index i is not a number or a letter or '_'.
                    throw new Invalid_Identifier_Name_Exception("The identifier name cannot contain a sign other than '_'! "+s);
                }
            }
        }
        return true;   
    }
}

class Stop_Token{
    
    private final char stop_token = '$';
    
    Stop_Token(){}

    @Override
    public String toString(){
        return String.format("<stop, %c>", stop_token);
    }
}