/**
 *The <code>EmptyLineException</code> class is a custom exception for the case
 * in which there is an attempt to remove a student while the line is empty.
 * The default message of this exception is written in the context of the
 * program, telling the user the issue and that the action of removing the
 * student has stopped.
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class EmptyLineException extends Exception{

	/**
	 * Constructor which contains the default message for the exception
	 * which is written in the context of the program.
	 */
	public EmptyLineException(){
		super("You have just tried to remove a student on an empty line." +
				"\nLuckily our friendly neighborhood" +
				" timekeeper came on the scene just in time to reverse time " +
				"to just before this crisis." +
				" Everything has returned to normal.");
	}

	/**
	 * Another constructor that overloads the other which can display an
	 * alternative message instead of the default one.
	 * @param message The message that is to be thrown in place of the
	 *                   default message when the exception occurs.
	 */
	public EmptyLineException(String message){

		super("message");
	}
}
