/**
 *The <code>EndOfListException</code> class is a custom exception for the case
 * in which the end of the linked list has been reached, therefore such
 * situations where the cursor is attempting to move forward or the list is
 * empty and a node is trying to be moved cannot occur. It's a general
 * exception for accessing nodes in the list that do not exist.
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class EndOfListException extends Exception{

    /**
     * Constructor which contains the default message of the exception.
     */
    public EndOfListException(){
        super("Error, end of the list reached.");
    }

    /**
     * Overloads the other constructor to display a custom message instead.
     * @param message The message that is to be displayed when the
     *                exception is thrown.
     */
    public EndOfListException(String message){
        super(message);
    }
}
