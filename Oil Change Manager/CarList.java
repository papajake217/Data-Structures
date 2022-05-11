/**
 *The <code>CarList</code> class allows a linked list of cars to be created
 * and manipulated
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class CarList {
    private CarListNode head; //Head node of the list.
    private CarListNode tail; //Tail node of the list.
    private CarListNode cursor; //Cursor node of the list.
    private int carAmount; // Number of cars currently in the list.

    /**
     * Default constructor of the car linked list which initializes it as
     * an empty linked list with no head, tail, or cursor. The number of
     * cars is also set to 0.
     */
    public CarList(){
        head = null;
        tail = null;
        cursor = null;
        carAmount = 0;
    }

    /**
     * Getter for the number of cars or nodes in the linked list.
     * @return The number of cars currently in the linked list.
     */
    public int numCars(){
        return this.carAmount;
    }

    /**
     * Getter for the car which is at the cursor currently.
     * @return The car in the node at the cursor.
     */
    public Car getCursorCar(){
        if(cursor != null) {
            return cursor.getData();
        } else{
            return null;
        }
    }

    /**
     * Getter for the cursor itself.
     * @return The cursor node.
     */
    public CarListNode getCursor(){
        return this.cursor;
    }

    /**
     * Getter for the head of the linked list.
     * @return The head node of the linked list.
     */
    public CarListNode getHead(){
        return this.head;
    }

    /**
     * Getter for the tail of the linked list.
     * @return The tail node of the linked list.
     */
    public CarListNode getTail(){
        return this.tail;
    }

    /**
     * Setter for the cursor of the linked list.
     * @param node Node to set the cursor of the linked list at.
     */
    public void setCursor(CarListNode node){
        cursor = node;
    }

    /**
     * Setter for the head of the linked list.
     * @param node Node to be set as the head of the linked list.
     */
    public void setHead(CarListNode node){
        head = node;
    }

    /**
     * Setter for the tail of the linked list.
     * @param node Node to be set as the tail of the linked list.
     */
    public void setTail(CarListNode node){
        tail = node;
    }

    /**
     * Resets the cursor's location to the head of the linked list.
     */
    public void resetCursorToHead(){
        if(head != null){
            cursor = head;
        } else {
            cursor = null;
        }
    }

    /**
     * Moves the cursor forward in the linked list by one node.
     * @throws EndOfListException Indicates the end of the list has been
     * reached, therefore there is no next node to move forward to.
     */
    public void cursorForward() throws EndOfListException{
        try{
            if(cursor == tail){
                throw new EndOfListException();
            }
            cursor = cursor.getNext();

        } catch (EndOfListException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves the cursor backward in the linked list by one Node.
     * @throws EndOfListException Indicates that the cursor is at the head,
     * and is attempting to move backwards in the linked list
     * which is impossible.
     */
    public void cursorBackward() throws EndOfListException{
        try{
            if(cursor == head){
                throw new EndOfListException();
            }

            cursor = cursor.getPrev();
        } catch (EndOfListException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserts a car in the linked list between the last node and the
     * node that the cursor is currently on.
     * @param newCar The car to be inserted into the linked list.
     * @throws IllegalArgumentException Indicates the car that is trying to
     * be added is null, therefore holding no data and as such cannot be
     * placed into the linked list.
     */
    public void insertBeforeCursor(Car newCar) throws IllegalArgumentException{
        try{
            if(newCar == null){
                throw new IllegalArgumentException();
            }

            CarListNode newNode = new CarListNode(newCar);
            carAmount++;
            if(cursor == null){
                head = newNode;
                tail = newNode;
                cursor = newNode;
            }
            if(cursor == head){
                newNode.setNext(cursor);
                cursor.setPrev(newNode);
                head = newNode;
            } else {
                newNode.setPrev(cursor.getPrev());
                newNode.setNext(cursor);
                cursor.getPrev().setNext(newNode);
                cursor.setPrev(newNode);
            }

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Adds a new node to the end of the linked list, essentially appending
     * to it's tail and changing this new node to its tail.
     * @param newCar The car to be added to the end of the linked list.
     * @throws IllegalArgumentException Indicates the car that is trying to
     * be added is null, therefore holding no data and as such cannot be
     * placed into the linked list.
     */
    public void appendToTail(Car newCar) throws IllegalArgumentException{
        try{
            if(newCar == null){
                throw new IllegalArgumentException();
            }else {
                CarListNode newNode = new CarListNode(newCar);
                carAmount++;
                if (tail != null) {
                    tail.setNext(newNode);
                    newNode.setPrev(tail);
                    tail = newNode;
                    newNode.setNext(null);
                } else {
                    head = newNode;
                    tail = newNode;
                    cursor = newNode;
                }
            }

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the node of the linked list that is currently where the cursor
     * is. It will also stitch back together the linked list by connecting
     * the two nodes adjacent to the removed node.
     * @return The car that was removed from the linked list.
     * @throws EndOfListException Indicates the cursor is null, or at an
     * invalid position so there is nothing to remove. In this context it
     * most likely means the list is empty.
     */
    public Car removeCursor() throws EndOfListException{
        try{
            if(cursor == null){
                throw new EndOfListException();
            }else if(cursor == head && head == tail){
                Car removed = cursor.getData();
                cursor.setData(null);
                head.setData(null);
                tail.setData(null);
                carAmount--;
                return removed;
            } else if (cursor == tail){
                Car removed = cursor.getData();
                cursor.getPrev().setNext(null);
                cursor = cursor.getPrev();
                tail = cursor;
                carAmount--;
                return removed;
            } else if (cursor == head) {
                Car removed = cursor.getData();
                cursor = cursor.getNext();
                cursor.setPrev(null);
                head = cursor;
                carAmount--;
                return removed;
            } else{
                Car removed = cursor.getData();
                cursor.getNext().setPrev(cursor.getPrev());
                cursor.getPrev().setNext(cursor.getNext());
                cursor = cursor.getPrev();
                carAmount--;
                return removed;
            }

        } catch(EndOfListException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Formats the data in the linked last in a string with each car's
     * make and owner.
     * @return The formatted string describing all the data in the
     * linked list.
     */
    public String toString(){
        String result = "";
        result += "  Make                 Owner";
        result += ("\n---------------------------------------\n");
        CarListNode tempNode = this.head;
        if(carAmount == 0){
            result += ("[Empty]");
        }
        for(int i=0;i<carAmount;i++){
            if(tempNode == cursor){
                result += "->";
            }else{
                result += "  ";
            }
            result += tempNode.toString();
            result += "\n";
            tempNode = tempNode.getNext();
        }
        return result;
    }




}
