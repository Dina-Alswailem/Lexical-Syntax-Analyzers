// class represinting custom exeption for identefier name errors.
package calculator_lexicalstage;

/**
 *
 * @author bedoo
 */
public class Invalid_Identifier_Name_Exception extends Exception{
    
    private final String roles = "The Identifier name accepts only alphapet (A-Z, a-z), underscore '_', numbers, and can not start with numbers. Please try again.";
    
    public Invalid_Identifier_Name_Exception (String str)  
    {  
        // calling the constructor of parent Exception
        super(str);  
        displayRoles();
    }  
    
    private void displayRoles(){
        System.out.print(roles);
    }
}
