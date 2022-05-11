import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * The <code>MailroomManager</code> class creates 6 stacks for packages
 * to be put on to. It also provides user control over the stacks in a
 * simulation of a mail room.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class MailroomManager {
    static PackageStack<Package> AGStack = new PackageStack<Package>
            (false);
    static PackageStack<Package> HJStack = new PackageStack<Package>
            (false);
    static PackageStack<Package> KMStack = new PackageStack<Package>
            (false);
    static PackageStack<Package> NRStack = new PackageStack<Package>
            (false);
    static PackageStack<Package> SZStack = new PackageStack<Package>
            (false);
    static PackageStack<Package> floorStack = new PackageStack<Package>
            (true);

    static PackageStack[] stackArray = {AGStack,HJStack,KMStack,NRStack,
    SZStack,floorStack};
    static int day = 0;

    /**
     * Main method with a loop of the user choosing an option to perform
     * on the stacks.
     * @param args Default main parameter.
     */
    public static void main(String[] args){
        System.out.println("Welcome to Wagner Mail Room Manager! You " +
                "are now in control of SBU student's mail. It is day 0.");
        printMenu();
        while(true){
            chooseOption();
        }
    }

    /**
     * Prints all the options that the user may choose.
     */
    public static void printMenu() {
        System.out.println("Menu:" +
                "\nD) Deliver a package" +
                "\nG) Get someone's package" +
                "\nT) Make it tomorrow" +
                "\nP) Print the stacks" +
                "\nM) Move a package from one stack to another" +
                "\nF) Find all packages in the wrong stack and move to floor" +
                "\nL) List all packages awaiting a user" +
                "\nE) Empty the floor" +
                "\nQ) Quit");
    }


    /**
     * Method which accepts a user's choice and puts it through a switch
     * case to determine which action to perform, and then performs
     * that corresponding action.
     */
    public static void chooseOption() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase();
        switch (input) {
            case "D":
                System.out.println("Please enter the recipient's name: ");
                String recipient = sc.nextLine();
                System.out.println("Please enter the weight of the package: ");
                double weight = sc.nextDouble();
                Package userPackage = new Package(recipient, day, weight);
                Character firstLetter =
                        Character.toUpperCase(recipient.charAt(0));
                int stackIndex = 0;
                if(firstLetter >= 'A' && firstLetter <= 'G'){
                    stackIndex = 0;
                } else if(firstLetter >= 'H' && firstLetter <= 'J'){
                    stackIndex = 1;
                } else if(firstLetter >= 'K' && firstLetter <= 'M'){
                    stackIndex = 2;
                } else if(firstLetter >= 'N' && firstLetter <= 'R'){
                    stackIndex = 3;
                } else if(firstLetter >= 'S' && firstLetter <= 'Z'){
                    stackIndex = 4;
                }

                int stackPlaced = 5;
                int[] order = getOrder(stackIndex);
                for(int i=0;i<6;i++){
                    int stackPicked = order[i];
                    if (!(stackArray[stackPicked].isFull())){
                        stackArray[stackPicked].push(userPackage);
                        stackPlaced = stackPicked;
                        break;
                    }
                }

                System.out.println("A " + userPackage.getWeight() + " lb " +
                        "package is " +
                        "awaiting pickup by " + userPackage.getRecipient());
                if(stackPlaced != stackIndex && stackPlaced != 5){
                    stackIndex++;
                    stackPlaced++;
                    System.out.println("As stack " + stackIndex +
                            " was full, it was placed in stack " +
                            stackPlaced + ".");
                } else if (stackPlaced != stackIndex && stackPlaced == 5){
                    stackIndex++;
                    System.out.println("As stack " + stackIndex +
                            " was full, and all other stacks were full," +
                            " it was placed on the floor.");
                }
                break;
            case "G":
                System.out.println("Please enter the recipient's name: ");
                String person = sc.nextLine();
                int[] findOrder = new int[6];
                firstLetter = Character.toUpperCase(person.charAt(0));
                stackIndex = 5;
                if(firstLetter >= 'A' && firstLetter <= 'G'){
                    stackIndex = 0;
                } else if(firstLetter >= 'H' && firstLetter <= 'J'){
                    stackIndex = 1;
                } else if(firstLetter >= 'K' && firstLetter <= 'M'){
                    stackIndex = 2;
                } else if(firstLetter >= 'N' && firstLetter <= 'R'){
                    stackIndex = 3;
                } else if(firstLetter >= 'S' && firstLetter <= 'Z'){
                    stackIndex = 4;
                }
                findOrder = getOrder(stackIndex);
                PackageStack<Package> chosenStack;
                int index = 0;
                int foundStack = 0;
                boolean found = false;
                int stackPicked = 0;
                for(int i = 0;i<5;i++) {
                    stackPicked = findOrder[i];
                    Package temp;
                    String name;
                    PackageStack tempStack = new PackageStack(false);
                    int stackLength = stackArray[stackPicked].size();
                    for (int j = 0; j < stackLength; j++) {
                        temp = (Package) stackArray[stackPicked].pop();
                        floorStack.push(temp);
                        name = temp.getRecipient();
                        if (name.compareTo(person) == 0) {
                            found = true;
                        }
                    }

                    for(int k = 0;k<stackLength;k++){
                        temp = (Package) floorStack.pop();
                        stackArray[stackPicked].push(temp);
                    }
                    if(found){
                        break;
                    }
                }
                    boolean flag = true;
                    int count = 0;
                    Package element = (Package) stackArray[stackPicked].peek();
                    if(found == true) {
                        while (flag) {
                            element = (Package)
                                    stackArray[stackPicked].peek();

                            if (element.getRecipient().
                                    compareToIgnoreCase(person) == 0) {
                                flag = false;
                            } else {
                                stackArray[stackPicked].pop();
                                floorStack.push(element);
                                count++;

                            }
                        }
                        boolean onTop = false;
                        if(count != 0) {
                            System.out.println("Move " + count + " packages" +
                                    " from Stack " + ((int)stackPicked+1) +
                                    " to floor.");

                            printStacks();
                        } else{
                            System.out.println("Package was found on the " +
                                    "top of Stack " + ((int)stackPicked+1)
                                    + " and removed.");
                            onTop = true;
                            printStacks();
                        }
                        System.out.println("Give " + person + " " +
                                element.getWeight() + " lb package delivered" +
                                " on day " + element.getArrivalDate() + ".");

                        stackArray[stackPicked].pop();
                        if(onTop) printStacks();
                        for(int x=0;x<count;x++){
                            Package floorPackage = (Package)
                                    floorStack.pop();
                            stackArray[stackPicked].push(floorPackage);
                        }

                        if(count != 0){
                            System.out.println("Return " + count + " " +
                                    "packages to Stack " +
                                    ((int)stackPicked+1) +
                                    " from floor.");
                            printStacks();
                        }


                    } else{
                        System.out.println("Error, no recipient by that name " +
                                "was found. Stacks are unchanged.");
                    }
                    break;


            case "T":
                day++;
                System.out.println("Time moves forward, it is now day "
                        + day + ".");
                int packagesRemoved = 0;
                int length;
                PackageStack<Package> timeStack = new PackageStack<Package>
                        (true);
                for(int i=0;i<stackArray.length;i++){
                    length = stackArray[i].size();

                    for(int j=0;j<length;j++){
                            Package checkElement = (Package) stackArray[i]
                                    .pop();
                            if(day - checkElement.getArrivalDate() >= 5){
                                packagesRemoved++;
                        } else{
                                timeStack.push(checkElement);
                            }
                    }

                    int timeLength = timeStack.size();
                    for(int k=0;k<timeLength;k++){
                        Package timeElement = timeStack.pop();
                        stackArray[i].push(timeElement);
                    }
                }
                if(packagesRemoved > 0){
                    System.out.println(packagesRemoved + " packages have " +
                            "been returned to sender.");
                }
                break;


            case "P":
                printStacks();
                break;

            case "M":
                System.out.println("Enter the source stack (Enter 0 for " +
                        "floor): ");
                int src = sc.nextInt();
                System.out.println("Enter the destination stack (Enter" +
                        " 0 for floor): ");
                int dest = sc.nextInt();
                if(src <= 5 && src >= 0 && dest <= 5 && dest >= 0) {
                    if (src == 0) {
                        src = 5;
                    } else{
                        src--;
                    }

                    if(dest == 0){
                        dest = 5;
                    } else{
                        dest--;
                    }
                    try {
                        if(stackArray[src].size() == 0){
                            throw new EmptyStackException();
                        }
                        if(stackArray[dest].isFull() &&
                        stackArray[dest] != floorStack){
                           throw new FullStackException();
                        }
                        element = (Package) stackArray[src].pop();
                        stackArray[dest].push(element);
                        
                    } catch(EmptyStackException e){
                        System.out.println("Error, source stack is empty." +
                                " Stacks are unchanged.");
                    }catch (FullStackException g) {
                        System.out.println("Error, destination stack" +
                                " is full. Stacks are unchanged. ");
                    }
                } else {
                    System.out.println("Error invalid source or destination" +
                            " stack.");
                }
                break;

            case "F":
                PackageStack<Package> temp =
                        new PackageStack<Package>(false);
                for(int i=0;i<stackArray.length-1;i++){
                    int arrayLength = stackArray[i].size();
                    for(int j=0;j<arrayLength;j++){
                       element = (Package) stackArray[i].pop();
                       firstLetter = Character.toUpperCase
                            (element.getRecipient().charAt(0));
                           if(i == 0) {
                             if(firstLetter >= 'A' && firstLetter <= 'G'){
                                 temp.push(element);
                             } else{
                                 floorStack.push(element);
                             }

                           } else if(i==1){
                               if(firstLetter >= 'H' && firstLetter <= 'J'){
                                   temp.push(element);
                               } else{
                                   floorStack.push(element);
                               }
                           } else if (i==2){
                               if(firstLetter >= 'K' && firstLetter <= 'M'){
                                   temp.push(element);
                               } else{
                                   floorStack.push(element);
                               }
                            } else if (i==3){
                               if(firstLetter >= 'N' && firstLetter <= 'R'){
                                   temp.push(element);
                               } else{
                                   floorStack.push(element);
                               }
                           } else if (i == 4){
                               if(firstLetter >= 'S' && firstLetter <= 'Z'){
                                   temp.push(element);
                               } else{
                                   floorStack.push(element);
                               }
                           }

                       }
                        int tempSize = temp.size();
                        for(int k=0;k<tempSize;k++){
                            stackArray[i].push((Package) temp.pop());
                        }
                    }
                System.out.println("Misplaced packages, if there were any, " +
                        "were moved to floor.");
              break;

            case "L":
                System.out.println("Please enter the recipient's name: ");
                recipient = sc.nextLine();

                int counter = 0;
                int packageNumber = 0;
                int[] stackNum = {1,2,3,4,5};
                String result = "";
                PackageStack<Package> tempStack =
                        new PackageStack<Package>(true);
                for(int i=0;i<6;i++){
                    length = stackArray[i].size();
                    for(int j=0;j<length;j++){
                        element = (Package) stackArray[i].pop();
                        if(element.getRecipient().
                                compareToIgnoreCase(recipient) == 0){
                            packageNumber++;
                            //NOTE: check if its floor stack and say if it is
                            if(i != 5) {
                                result += "\nPackage " + packageNumber +
                                        " is in Stack " + stackNum[i] +
                                        ". It was delivered on day " +
                                        element.getArrivalDate() +
                                        " and weighs " + element.getWeight() +
                                        " lbs.";
                            } else{
                                result += "\nPackage " + packageNumber +
                                        " is on the floor" +
                                        ". It was delivered on day " +
                                        element.getArrivalDate() +
                                        " and weighs " + element.getWeight() +
                                        " lbs.";
                            }
                        }
                        tempStack.push(element);
                    }
                    for(int j=0;j<length;j++){
                        element = (Package) tempStack.pop();
                        stackArray[i].push(element);
                    }

                }

                String finalString =  recipient + " has " + packageNumber
                        + " packages total." + result;
                if(packageNumber > 0){
                    System.out.println(finalString);
                } else{
                    System.out.println("Error, no recipient found with " +
                            "that name.");
                }


                break;

            case "E":
                int floorLength = floorStack.size();
                for(int i=0;i<floorLength;i++){
                    floorStack.pop();
                }
                System.out.println("Floor has been emptied. The packages " +
                        "were thrown away.");
                break;
            case "Q":
                System.out.println("Thank you for your help! Bye Bye.");
                System.exit(0);
            default:
                System.out.println("Error, invalid choice.");
                break;
        }
    }


    /**
     * Gets the order in which the stacks must be accessed in order for
     * delivering/searching to be in the closest stack to the correct one.
     * @param num The index of the stack to find the closest stacks to.
     * @return An integer array which describes the order to access the
     * stacks in case the correct one is full.
     */
    public static int[] getOrder(int num){
        int[] result = new int[6];
        int index;
        int element = 1;
        result[0] = num;
        for(int i=1;i<5;i++){
            index = num + i;
            if(index < 5 && index >= 0){
                result[element] = index;
                element++;
            }
            index = num - i;
            if(index < 5 && index >= 0){
                result[element] = index;
                element++;
            }

        }
        result[5] = 5;
        return result;
    }


    /**
     * Prints all the stacks with their current packages.
     */
    public static void printStacks(){
        System.out.println("Current Packages:" +
                "\n------------------------------------------------" +
                "\nStack 1 (A-G): " + AGStack.toString() +
                "\nStack 2 (H-J): " + HJStack.toString() +
                "\nStack 3 (K-M): " + KMStack.toString() +
                "\nStack 4 (N-R): " + NRStack.toString() +
                "\nStack 5 (S-Z): " + SZStack.toString() +
                "\nFloor: " + floorStack.toString());
    }

    }

