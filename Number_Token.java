// The class is to create the Number token and specify its type.
package calculator_lexicalstage;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author bedoo
 */
public class Number_Token {
    
    private final Number number; // to save any received number with any type.
    
    public Number_Token(String s) throws ParseException{
        number = NumberFormat.getInstance().parse(s);
    }
    
    @Override
    public String toString(){
        return String.format("<Number, "+number+">");
    }
}

