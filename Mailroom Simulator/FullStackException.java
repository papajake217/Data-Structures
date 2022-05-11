/**
 *The <code>FullStackException</code> class is a custom exception
 * for the case in which a stack is full and therefore has no room for
 * more elements to be pushed into it.
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class FullStackException extends Exception{

    /**
     * Constructor which contains the default message for this exception.
     */
    public FullStackException(){
        super("Error, stack is full.");
    }

    /**
     * Overloads the other constructor to display a custom message for the
     * exception instead.
     * @param message Message to be displayed when the exception occurs.
     */
    public FullStackException(String message){
        super(message);
    }
}
