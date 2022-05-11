/**
 *The <code>DeanException</code> class is a custom exception for the case in
 * which the line is full and a new student cannot be added.
 * The default message of this exception is written in the context of the
 * program, telling the user that the dean of the school took them away.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/
public class DeanException extends Exception{

    /**
     * Constructor which contains the default message for the exception which
     * is written in the context of the program.
     */
    public DeanException(){
        super("You have tried to add a student to a line which is already " +
                "full.\nAs they were about to go on line" +
                ", Mr.Mean the Dean swooped in to escort them to detention. " +
                "That'll show them! No student was added to the line.");
    }

    /**
     * Another constructor that overloads the other which can display an
     * alternative message instead of the default one.
     * @param message The message that is to be thrown in place of the
     *                default message when the exception occurs.
     */
    public DeanException(String message){
        super(message);
    }
}
