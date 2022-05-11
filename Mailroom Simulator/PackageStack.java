import java.util.Stack;
/**
 *The <code>PackageStack</code> class allows stacks of packages to be
 * created and manipulated.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class PackageStack<Package> extends Stack<Package> {
    /**
     * //Maximum number of elements in a stack.
     */
    private final int CAPACITY;

    /**
     * //Whether a stack is the floor or not.
     */
    private boolean isFloorStack;

    /**
     * Creates an instance of a stack of packages.
     * @param isFloorStack Whether the stack is the floor stack or not.
     */

    public PackageStack(boolean isFloorStack){
        super();
        this.isFloorStack = isFloorStack;
        this.CAPACITY = 7;
    }

    /**
     * Creates an instance of a stack of packages but with no parameters.
     */
    public PackageStack(){
        super();
        this.isFloorStack = false;
        this.CAPACITY = 7;
    }


    /**
     * Pushes a package onto the stack. Will not push if the stack is full.
     * @param pack Package to be pushed onto the stack.
     * @return Null in this case. Included in the context of Java's built-
     * in stack.
     */
    public Package push(Package pack){
        try {
            if (!(this.isFull())) {
                super.push(pack);
            } else {
                throw new FullStackException();
            }
        } catch (FullStackException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    /**
     * Gets the package at the top of the stack without altering the stack.
     * @return The package object at the top of the stack.
     */
    public Package peek(){
        return (Package) super.peek();
    }

    /**
     * Checks if the stack is full. Always returns false if it is the floor
     * stack.
     * @return Whether the stack is full or not.
     */
    public boolean isFull(){
        if(this.isFloorStack){
            return false;
        }
        return this.size() == CAPACITY;
    }

    /**
     * Displays the stack in a string formatted to display all the
     * packages inside it.
     * @return A string describing the stack of packages.
     */
    public String toString(){
        String result = "";
        for (int i=0;i<this.size();i++){
            result += this.elementAt(i).toString() + " | ";
        }

        if(this.size() == 0){
            result += "Empty";
        }
        return result;
    }


}
