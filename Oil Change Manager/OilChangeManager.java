import java.util.Scanner;

/**
 *The <code>OilChangeManager</code> class is where the main program runs
 * and is where the linked lists are created and used to simulate two
 * lines of cars waiting for an oil change.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class OilChangeManager {
    static CarList joeList = new CarList(); //Linked list for Joe.
    static CarList donnyList = new CarList(); //Linked list for Donny.
    static CarList currentList; //Tracks the current linked list.
    static CarList finishedList = new CarList(); //Linked list for finished.
    static Car carClipboard; //Holds a cut car.

    /**
     * Runs the program in an infinite loop of the other methods of this
     * class.
     * @param args Default main parameter.
     */
    public static void main(String[] args){
        while (true){
            printMenu();
            menuInput();
        }

    }

    /**
     * Prints the menu of options the user has access to in the program.
     */
    public static void printMenu(){
        System.out.println("\nMenu: " +
                "\n     L) Edit Job Lists for Joe and Donny" +
                "\n     M) Merge Job Lists" +
                "\n     P) Print Job Lists" +
                "\n     F) Paste car to end of finished car list" +
                "\n     S) Sort the lists" +
                "\n     Q) Quit");
    }

    /**
     * Takes input from the user and performs one of multiple actions on
     * the linked lists. It will not accept invalid inputs and is not
     * case-sensitive. Also gives a few general commands for the lists.
     */
    public static void menuInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease select an Option: ");
        String userInput = sc.nextLine();
        userInput = userInput.toUpperCase();
        switch(userInput){
            case "L":
                listInput();
                break;

            case "M":
                System.out.println("Please enter a destination list -" +
                        " Joe (J) or Donny (D):");
                String choice = sc.nextLine();

                if(choice.compareToIgnoreCase("J") == 0){
                    CarListNode temp = joeList.getCursor();
                    joeList.resetCursorToHead();
                    donnyList.resetCursorToHead();
                    int j = joeList.numCars() - 1;
                    try {
                        for (int i = 0; i < j ; i++) {
                            joeList.cursorForward();
                            Car removedCar = donnyList.removeCursor();
                            joeList.insertBeforeCursor(removedCar);
                        }
                    } catch (EndOfListException e){

                    } finally{
                        try {
                            int x = donnyList.numCars();
                            for (int i = 0; i < x; i++) {
                                Car removedCar = donnyList.removeCursor();
                                joeList.appendToTail(removedCar);
                            }
                        } catch(EndOfListException d){

                        }
                    }
                    joeList.setCursor(temp);
                    donnyList.setCursor(null);
                    donnyList.setTail(null);
                    donnyList.setHead(null);
                    System.out.println("Donny's list merged into Joe's.");
                } else if(choice.compareToIgnoreCase("D") == 0){
                    CarListNode temp = donnyList.getCursor();
                    joeList.resetCursorToHead();
                    donnyList.resetCursorToHead();
                    int j = donnyList.numCars()- 1;
                    try {
                        for (int i = 0; i < j ; i++) {
                            donnyList.cursorForward();
                            Car removedCar = joeList.removeCursor();
                            donnyList.insertBeforeCursor(removedCar);
                        }
                    } catch (EndOfListException e){

                    } finally{
                        try {
                            int x = joeList.numCars();
                            for (int i = 0; i < x; i++) {
                                Car removedCar = joeList.removeCursor();
                                donnyList.appendToTail(removedCar);
                            }
                        } catch(EndOfListException d){

                        }

                    }
                    donnyList.setCursor(temp);
                    joeList.setCursor(null);
                    joeList.setTail(null);
                    joeList.setHead(null);
                    System.out.println("Joe's list merged into Donny's.");
                } else{
                    System.out.println("Error invalid choice.");
                }
                break;

            case "P":
                System.out.println("\nJoe's List:");
                System.out.println(joeList.toString());
                System.out.println("\nDonny's List:");
                System.out.println(donnyList.toString());
                System.out.println("\nFinished List:");
                System.out.println(finishedList.toString());
                break;

            case "F":
                try{
                    finishedList.insertBeforeCursor(carClipboard);
                    System.out.println("Cursor has been pasted to " +
                            "finished list.");
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "S":
                    CarListNode tempCursorJoe = joeList.getCursor();
                    CarListNode tempCursorDonny = donnyList.getCursor();
                    for(int k = 0;k<2;k++) {
                        if (k == 0) currentList = joeList;
                        if (k == 1) currentList = donnyList;
                        int length = currentList.numCars();
                        if (length > 1) {
                            currentList.resetCursorToHead();
                            while (currentList.getCursor().getNext() != null) {
                                try {
                                    int valOne = currentList.getCursor().
                                            getData().getMake().ordinal();
                                    int valTwo = currentList.getCursor().
                                            getNext().getData().getMake()
                                            .ordinal();
                                    if (valOne > valTwo) {
                                        currentList.
                                                insertBeforeCursor(currentList.
                                                getCursor().getNext()
                                                        .getData());
                                        currentList.cursorForward();
                                        currentList.removeCursor();
                                        currentList.resetCursorToHead();
                                    } else {
                                        currentList.cursorForward();
                                    }
                                } catch (EndOfListException e) {

                                }
                            }
                        }
                    }
                    joeList.setCursor(tempCursorJoe);
                    donnyList.setCursor(tempCursorDonny);
                    System.out.println("Joe and Donny's " +
                            "lists have been sorted.");
                break;

            case "Q":
                System.out.println("Enjoy your retirement!");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid input. Please select a valid" +
                        " option from the menu");
        }
    }

    /**
     * Allows the user to edit either Joe or Donny's list in a few
     * different ways. The user can manipulate and traverse the list
     * using these commands.
     * @throws IllegalArgumentException Indicates an invalid input
     * by the user.
     */
    public static void listInput() throws IllegalArgumentException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select a list - Joe (J) or Donny (D): ");
        String choice = sc.nextLine().toUpperCase();
        String person;
        if(choice.compareTo("J") == 0){
            currentList = joeList;
            person = "Joe";
        } else if (choice.compareTo("D") == 0){
            currentList = donnyList;
            person = "Donny";
        } else{
            System.out.println("Error, invalid choice.");
            return;
        }

        System.out.println("Options:" +
                "\n     A) Add a car to the end of the list" +
                "\n     F) Cursor Forward" +
                "\n     H) Cursor to Head" +
                "\n     T) Cursor to Tail" +
                "\n     B) Cursor Backward" +
                "\n     I) Insert car before cursor" +
                "\n     X) Cut car at cursor" +
                "\n     V) Paste before cursor" +
                "\n     R) Remove cursor");

        System.out.println("Please select an option: ");
        choice = sc.nextLine().toUpperCase();
        switch(choice){
            case "A":
                System.out.println("Please enter vehicle make " +
                        "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, " +
                        "or Lincoln): ");
                String make = sc.nextLine().toUpperCase();
                try{
                    Make.valueOf(make);
                    System.out.println("Enter the owner's name: ");
                    String name = sc.nextLine();
                    Car newCar = new Car(Make.valueOf(make),name);
                    currentList.appendToTail(newCar);
                    System.out.println(name + "'s " + make + " has been" +
                            " scheduled for an oil change with " + person +
                            " and has been added to his list.");
                } catch(IllegalArgumentException e){
                    System.out.println("Error, invalid make.");
                    break;
                }
                break;

            case "F":
                try {
                    if(currentList.getCursor() != currentList.getTail()) {
                        currentList.cursorForward();
                        System.out.println("Cursor moved forward in " +
                                person + "'s list.");
                    } else{
                        System.out.println("Error, cannot move forward");
                    }
                } catch(EndOfListException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "H":
                currentList.resetCursorToHead();
                System.out.println("Cursor has been set to Head in" +
                        " " + person + "'s list.");
                break;

            case "T":
                try {
                    currentList.resetCursorToHead();
                    for (int i = 0; i < currentList.numCars()-1; i++) {
                        currentList.cursorForward();
                    }
                    System.out.println("Cursor has been set to Tail" +
                            " in " + person + "'s list");
                } catch(EndOfListException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "B":
                try{
                    currentList.cursorBackward();
                    System.out.println("Cursor moved backward in " +
                            person + "'s list");
                } catch(EndOfListException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "I":
                System.out.println("Please enter vehicle make " +
                        "(Ford, GMC, Chevy, Jeep, Dodge, Chrysler, " +
                        "or Lincoln): ");
                make = sc.nextLine().toUpperCase();
                try{
                    Make.valueOf(make);
                    System.out.println("Enter the owner's name: ");
                    String name = sc.nextLine();
                    Car newCar = new Car(Make.valueOf(make),name);
                    currentList.insertBeforeCursor(newCar);

                    System.out.println(name + "'s " + make + " has been" +
                            " scheduled for an oil change with " + person +
                            " and has been added to his list" +
                            " before the cursor.");

                } catch(IllegalArgumentException e){
                    System.out.println("Error, invalid make.");
                    break;
                }
                break;

            case "X":
                try {
                    carClipboard = currentList.removeCursor();
                    System.out.println("Cursor cut in " + person +
                            "'s list.");

                } catch(EndOfListException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "V":
                try{
                    if(carClipboard == null){
                        System.out.println("Nothing to paste");
                    } else {
                        currentList.insertBeforeCursor(carClipboard);
                        System.out.println("Cursor pasted in " + person +
                                "'s list.");
                        carClipboard = null;
                    }
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;

            case "R":
                try{
                    currentList.removeCursor();
                    System.out.println("Cursor has been removed in " +
                            person + "'s list.");
                } catch(EndOfListException e){
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Error, invalid choice.");
                break;
        }
    }






}
