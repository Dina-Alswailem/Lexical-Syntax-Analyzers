// The class is to create the operator token and specify the operator type.
package calculator_lexicalstage;
public class Operator_Token {
    
    private char operatorSign; // save the operator in char type.
    private String operatorType; // identify its type
    
    public Operator_Token(String s){
        specifyToken(s);
    }
    
    private void specifyToken(String ope){
        switch(ope){
            
            case "+" -> {
                operatorSign = '+';
                operatorType = "Plus";
            }
            case "-" -> {
                operatorSign = '-';
                operatorType = "Minus";
            }
            case "/" -> {
                operatorSign = '/';
                operatorType = "Division";
            }
            case "*" -> {
                operatorSign = '*';
                operatorType = "Multiplication";
            }
            case "%" -> {
                operatorSign = '%';
                operatorType = "Modulo";
            }
            case "^" -> {
                operatorSign = '^';
                operatorType = "Power of";
            }
            case "=" -> {
                operatorSign = '=';
                operatorType = "Assignment";
            }
        }
    }
    
    @Override
    public String toString(){
        return String.format("<Operator, %c>", operatorSign);
    }
}
