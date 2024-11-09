// The class is to create the Identifier token and specify the operator type, create symbol table, and error handlers.
package calculator_lexicalstage;

/**
 *
 * @author bedoo
 */
public class Identifier_Token {
    
    private static int counter = 1; // so we can stay counting, and haven`t repeated ids.
    private int identifierId;
    
    public Identifier_Token(String s) throws Invalid_Identifier_Name_Exception{
        newToken(s); // create new token or object of type identifier
    }
    
    private void newToken(String s){
        identifierId = counter;
        counter++;
        new SymbolTable(s, identifierId);
    }
    
    @Override
    public String toString(){
        return String.format("<Identifier, "+identifierId+">");
    }
}
