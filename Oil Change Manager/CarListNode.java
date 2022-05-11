/**
 * The <code>CarListNode</code> class allows nodes containing a car, as well
 * as pointers to the previous and next cars in a linked list, to be
 * created and manipulated.
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class CarListNode {
    private Car data; //Car stored in the node.
    private CarListNode next; //Pointer to the next node in the list.
    private CarListNode prev; //Pointer to the previous node in the list.

    /**
     * Constructor for CarListNode.
     * @param initData Car to be stored in the node.
     * @throws IllegalArgumentException Indicates the car being inputted
     * is null, meaning it contains no data and cannot be stored.
     */
    public CarListNode(Car initData) throws IllegalArgumentException{
        try {
            if (initData == null) {
                throw new IllegalArgumentException();
            }
            this.data = initData;
            next = null;
            prev = null;
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Getter for the car stored in the node.
     * @return The car stored in the node.
     */
    public Car getData(){
        return this.data;
    }

    /**
     * Getter for the next node in the list.
     * @return The next node in the linked list.
     */
    public CarListNode getNext(){
        return this.next;
    }

    /**
     * Getter for the previous node in the list.
     * @return The previous node in the list.
     */
    public CarListNode getPrev(){
        return this.prev;
    }

    /**
     * Setter for the car stored in the node.
     * @param data Car to be stored in the node.
     */
    public void setData(Car data){
        this.data = data;
    }

    /**
     * Setter for the pointer to the next node in the linked list.
     * @param next Node to be set as the next node in the linked list.
     */
    public void setNext(CarListNode next){
        this.next = next;
    }

    /**
     * Setter for the pointer to the previous node in the linked list.
     * @param prev Node to be set as the previous node in the linked list.
     */
    public void setPrev(CarListNode prev){this.prev = prev;}

    /**
     * Provides the stored car's information via its toString method.
     * @return The stored car's formatted string containing its
     * make and owner.
     */
    public String toString(){
        return data.toString();
    }
}
