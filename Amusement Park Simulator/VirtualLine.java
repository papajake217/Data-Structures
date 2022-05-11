import java.util.ArrayList;

/**
 * The <code>VirtualLine</code> class is used to create and manipulate
 * the virtual line of each ride. Extends the ArrayList class.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class VirtualLine extends ArrayList<Person> {


    /**
     * Enqueues a person into the back of the virtual line.
     * @param p Person to be enqueued into the back of the line.
     */
    public void enqueue(Person p){
        this.add(p);
    }

    /**
     * Dequeues the person in the front of line.
     * @return The person that was dequeued from the front of the line.
     */
    public Person dequeue(){
        return this.remove(0);
    }

    /**
     * Checks the person at the front of the line.
     * @return The person who is at the front of the virtual line.
     */
    public Person peek(){
        return this.get(this.size()-1);
    }

    /**
     * Tells whether the virtual line is empty or not.
     * @return A boolean representing whether the line is empty or not.
     */
    public boolean isEmpty(){
        return super.isEmpty();
    }


}
