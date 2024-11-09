// the class create new object of type symbol for every identifier and add it to the array to act as table, the array index is the same as the ID number for the identifier.
package calculator_lexicalstage;
/**
 *
 * @author bedoo
 */
public class SymbolTable {
    
    private static final Symbol symbols[] = new Symbol[100];
    static int counter = 0; // to counts how many times we initiate new Symbol object and add it to the array
    
    public SymbolTable(String i, int id){ // i = identifier
        symbols[id] = new Symbol(i, id); //the array index is the same as the ID number for the identifier.
        counter++;
    }
    
    public static void displaySymbolTable(){ // to print the table content
        for (int i = 1; i <= counter; i++){
            System.out.println(symbols[i].toString());
        }
    } 
}

class Symbol{
    
    private final int identifierId;
    private final String identifierName;
    
    Symbol(String s, int id){
        identifierId = id;
        identifierName = s;
    }
    
    @Override
    public String toString(){
        return String.format("Id = "+identifierId+", Name = %s", identifierName);
    }
}
