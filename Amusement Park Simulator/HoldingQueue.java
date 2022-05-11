/**
 * The <code>HoldingQueue</code> class is used to create and manipulate
 * the holding queue of each ride. Extends the VirtualLine class.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class HoldingQueue extends VirtualLine{
    /**
     * Maximum amount of people allowed in the holding queue at once.
     */
    private int maxSize;

    /**
     * Constructor for the holding queue.
     * @param maxSize The maximum amount of people allowed in the holding
     *                queue at once.
     */
    public HoldingQueue(int maxSize){
        super();
        this.maxSize = maxSize;
    }

    /**
     * Enqueues a person in the back of the holding queue.
     * @param p The person to be enqueued.
     */
    public void Enqueue(Person p){
        if(this.size() < maxSize){
            super.enqueue(p);
        }
    }

    /**
     * Dequeues a person from the front of the holding queue.
     * @return The person dequeued.
     */
    public Person Dequeue(){
        if(this.size() > 0){
            return super.dequeue();
        } else{
            return null;
        }
    }

    /**
     * Getter for the max size of the holding queue.
     * @return The maximum amount of people allowed in the holding queue at
     * once.
     */
    public int getMaxSize(){
        return this.maxSize;
    }

    /**
     * Setter for the max size of the holding queue.
     * @param maxSize The desired maximum amount of people allowed in the
     *                holding queue at once.
     */
    public void setMaxSize(int maxSize){
        this.maxSize = maxSize;
    }

    /**
     * Method that tells whether the queue is full or not.
     * @return A boolean representing if the queue is full or not.
     */
    public boolean isFull(){
        if(this.size() == maxSize){
            return true;
        }
        return false;
    }

}
