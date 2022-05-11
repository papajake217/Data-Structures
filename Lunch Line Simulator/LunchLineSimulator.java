import java.util.Scanner;

/**
 *The <code>LunchLineSimulator</code> class is where the lunch line program
 * runs, containing the part of the program that the user interacts with.
 * This allows the methods in the StudentLine class to be organized and used
 * in the context of the program.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 *      Recitation: R04
 **/

public class LunchLineSimulator {

    private static StudentLine realityA = new StudentLine(); //One version of
    // the line created of type StudentLine.
    private static StudentLine realityB = new StudentLine(); //The other
    // version of the line of type StudentLine.
    private static StudentLine activeReality = realityA;
    //A variable of type StudentLine which stores the reality that is being
    // altered by the user currently. This is alternated between realityA and
    // realityB throughout the course of the program.

    /**
     * The main loop of the program which keeps it running and checks if the
     * user wishes to quit.
     * Exceptions are prepared to be thrown from here in the methods called
     * while this method is run.
     * @throws EmptyLineException Included here, so it can be thrown correctly
     * in the methods it calls.
     * @throws DeanException Included here, so it can be thrown correctly in
     * the methods it calls.
     * @param args Default main parameter.
     */
    public static void main(String[] args) throws EmptyLineException,
            DeanException {
        System.out.println("Welcome to my Middle School Lunch Line " +
                "Simulator! Here you will have complete control over " +
                "a lunch line. Oh the power!\nFeel free to reign chaos on" +
                " the line, or let them eat in peace. The choice is yours!" +
                " You will start in Reality A.");
        printMenu();
        System.out.println();
        while(true) {
            String selection = chooseOption();
            System.out.println();
            if(selection.toUpperCase().compareTo("Q") == 0){
                System.out.println("You have graduated from the lunch line. " +
                        "Good day!");
                break;
            }
            performOption(selection);
        }

    }

    /**
     * Prints each option the user has, with the character they must type and
     * a corresponding description.
     */
    public static void printMenu(){
        System.out.println("\nMenu: \nA) Add a student to line at the end\nC)"+
                " Have a new student cut a friend\nT) Have two students" +
                " trade places\nB) Have the bully remove a student\nU) " +
                "Update a student's money amount\nS) Serve a student" +
                "\nP) Print the current reality's lunch line\nO) Switch to " +
                "the other reality\nE) Check if the realities are equal\n" +
                "D) Duplicate this reality into the other reality\n" +
                "Q) Quit middle school and move on to real life");
    }

    /**
     * Allows the program to accept the user's choice on the menu.
     * @return A one letter string indicating which option the user chooses.
     */
    public static String chooseOption(){
        System.out.print("\nSelect an option from the menu: ");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        while(!(isValidOption(option))){
            System.out.println("Error, invalid input, please enter a new " +
                    "selection: ");
            option = sc.nextLine();
        }

        return option;
    }

    /**
     * Checks if the user has entered a valid command into the program.
     * @param input The input of the user.
     * @return A boolean indicating whether the command exists in the program.
     */
    public static boolean isValidOption(String input){
        String[] validInputs = new String[]{"A","C","T","B","U","S","P","O",
                "E","D","Q"};
        for(int i=0;i<validInputs.length;i++){
            if(input.toUpperCase().compareTo(validInputs[i]) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Carries out the action intended by the user via a switch statement.
     * The switch statement runs a method that corresponds to the user's
     * choice.
     * @param option The single character string that the user inputted.
     * @throws EmptyLineException Used in the methods the switch statement
     * calls.
     * @throws DeanException Used in the methods the switch statement calls.
     */
    public static void performOption(String option) throws EmptyLineException,
            DeanException {
        Scanner sc = new Scanner(System.in);
        option = option.toUpperCase();
        switch(option){
            case "A":
                addStudent();
                break;
            case "C":
                cutFriend();
                break;
            case "T":
                tradePlace();
                break;
            case "B":
                bullyStudent();
                break;
            case "U":
                updateStudent();
                break;
            case "S":
                serveStudent();
                break;
            case "P":
                printLine();
                break;
            case "O":
                switchReality();
                break;
            case "E":
                compareRealities();
                break;
            case "D":
                duplicateReality();
                break;
            default:
                break;
        }
    }

    /**
     * Adds a student defined by the user to the line.
     * @throws DeanException Indicates the line is full and a student cannot be
     * added to it as a result. Student will not be added when this is thrown.
     */
    public static void addStudent() throws DeanException {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter name of student: ");
            String name = sc.nextLine();
            System.out.println("Enter amount of money: ");
            double money = sc.nextDouble();
            Student newStudent = new Student(name, money);
            if(activeReality.numStudents() == activeReality.getCAPACITY()){
                throw new DeanException();
            }
            activeReality.addStudent(activeReality.numStudents(), newStudent);
            System.out.println(name + " has been added to the line in position "
                    + activeReality.numStudents() + " with $" + money);
        } catch (DeanException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * Allows a new student to come into the line and cut a friend.
     * The user defines the new student and which position they will cut into
     * the line at.
     * This causes the rest of the line behind this new student to be moved
     * back by one spot in order to create room for them.
     * @throws DeanException Indicates the line is full and a new student
     * cannot be added.
     * @throws NullPointerException Indicates the user is trying to cut a
     * student at an index which does not exist. This can either be at an index
     * that is always inaccessible in this program or at one where no student
     * is in line yet.
     */
    public static void cutFriend() throws DeanException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter name of student: ");
            String name = sc.nextLine();
            System.out.println("Enter amount of money: ");
            double money = sc.nextDouble();
            Student newStudent = new Student(name, money);
            System.out.println("Enter the position in line: ");
            int index = sc.nextInt();
            index -= 1;
            if(activeReality.getStudent(index) == null || index + 1 >
                    activeReality.numStudents()){
                throw new NullPointerException();
            }
            if(!(activeReality.numStudents() == activeReality.getCAPACITY())) {
                System.out.println(name + " has cut " + activeReality
                        .getStudent(index).getName() + " in line and is " +
                        "now in position " + (index + 1) + ". " + name +
                        " has $" + money);
            }
            activeReality.addStudent(index, newStudent);
        } catch(NullPointerException e){
            System.out.println("You cannot cut a student at an index that " +
                    "does not exist! The child had no friends to cut and " +
                    "was left wandering aimlessly.\nThey were then promptly " +
                    "removed. Line is unchanged.");
        }

    }

    /**
     * Allows the user to select two students whose positions will be swapped
     * on the line.
     * Catches the exception when the user inputs an invalid index which then
     * gives a null pointer exception due to nothing existing at that index.
     * The exception is however really an ArrayIndexOutOfBounds exception
     * triggered in the swapStudent method this method calls.
     */
    public static void tradePlace() throws NullPointerException{
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the first student's index: ");
            int indexOne = sc.nextInt();
            indexOne -= 1;
            System.out.println("Enter the second student's index: ");
            int indexTwo = sc.nextInt();
            indexTwo -= 1;
            activeReality.swapStudents(indexOne, indexTwo);
            if(activeReality.getStudent(indexOne) == null || activeReality
                    .getStudent(indexTwo) == null){
                throw new NullPointerException();
            }
            System.out.println(activeReality.getStudent(indexOne)
                    .getName() + " has traded places with " + activeReality
                    .getStudent(indexTwo).getName());
        } catch(NullPointerException ignored){
            System.out.println("Null pointer");
        } catch(ArrayIndexOutOfBoundsException e){
            
        }



    }

    /**
     * Allows a student to be removed from any index in the line via bullying.
     * The user selects which student to be removed and the action is performed
     * as long as the index is valid.
     * @throws EmptyLineException Thrown when the line is empty and no one can
     * be removed via bullying.
     * @throws NullPointerException Thrown when the return value which is the
     * student that was removed is null. This exception is further handled in
     * StudentLine.
     */
    public static void bullyStudent() throws EmptyLineException,
            NullPointerException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the index of the student you wish to " +
                    "bully: ");
            int index = sc.nextInt();
            index -= 1;
            Student removedStudent = activeReality.removeStudent(index);
            System.out.println(removedStudent.getName() + " has been " +
                    "bullied off the line by Punchin' Patrick and ran away" +
                    " crying!");
        } catch (NullPointerException ignored){

        }
    }

    /**
     * Updates a student's money, either taking money away or giving them more.
     * The user is prompted to choose a student and choose a new amount of
     * money for the student to have.
     * @throws NullPointerException Indicates an attempt to update a student's
     * money at an invalid index, therefore returning null and causing an
     * error. No update to the line is made when this happens.
     */
    public static void updateStudent() throws NullPointerException{
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the index of the student: ");
            int index = sc.nextInt();
            index -= 1;
            System.out.println("Enter a new money amount: ");
            double newMoney = sc.nextDouble();
            if(activeReality.getStudent(index) == null){
                throw new NullPointerException();
            }
            if (newMoney < 0) {
                System.out.println("A middle school student can't be in " +
                        "debt. Money was not updated");
            } else {
                activeReality.getStudent(index).setMoney(newMoney);
                System.out.println("Due to unforeseen circumstances, " +
                        activeReality.getStudent(index).getName() +
                        " now has $" + newMoney);
            }
        } catch (NullPointerException e){
            System.out.println("Invalid index, a student does not exist " +
                    "at that position. Line has not been updated.");
        }
    }

    /**
     * Serves the student at the front of the line. This removes the
     * student at the 0th index of the student array, then moving everyone
     * else up the line by one index.
     */
    public static void serveStudent() {
        try {
            Student servedStudent = activeReality.removeStudent(0);
            if(servedStudent == null) {
                throw new NullPointerException();
            }
                System.out.println(servedStudent.getName() + " has been" +
                        " served toxic tendies! He is now off to eat lunch.");

        } catch(NullPointerException | EmptyLineException ignored){

        }

    }

    /**
     * Prints the lunch line by displaying which reality the user is in,
     * as well as displaying the line of students in order.
     */
    public static void printLine(){
        System.out.print("\nLunch line in Reality ");
        if(activeReality == realityA) System.out.print("A");
        else System.out.print("B");

        System.out.println("\n" + activeReality.toString());

    }

    /**
     * Allows the user to switch between reality A and B, it will switch
     * to the other reality by checking which reality is stored in
     * activeReality.
     */
    public static void switchReality(){
        if(activeReality == realityA){
            activeReality = realityB;
            System.out.println("Reality has been switched to Reality B");
        } else{
            activeReality = realityA;
            System.out.println("Reality has been switched to Reality A");
        }

    }

    /**
     * Checks if the two realities are equal. It goes through both realities
     * by index and compares the name of the student and amount of money at a
     * given position.
     * A boolean flag is declared in the beginning as true, which will be
     * unchanged as long as no inequality is detected.
     * If even one inequality is found, the boolean flag becomes false. This
     * then affects whether the program tells the user that the realities
     * are equal or not.
     */
    public static void compareRealities() {
        boolean equal = true;
        if (realityA.numStudents() == realityB.numStudents()) {
            for (int i = 0; i < realityA.numStudents(); i++) {
                boolean namesEqual = realityA.getStudent(i)
                        .getName()
                        .compareTo(realityB.
                                getStudent(i)
                                .getName()) == 0;
                boolean moneyEqual =  realityA.getStudent(i).
                        getMoney() == realityB.getStudent(i).getMoney();
                if (!(namesEqual && moneyEqual)){
                    equal = false;
                }
            }
        } else{
            equal = false;
        }

        if(equal){
            System.out.println("The realities are equal.");
        } else{
            System.out.println("The realities are not equal");
        }
    }

    /**
     * Duplicates one reality into the other. It will access the clone method
     * in studentLine to duplicate itself to the other reality.
     * The reality that is duplicated depends on which is active. If A is
     * active, then A will be duplicated into B and vice versa.
     */
    public static void duplicateReality(){
        if(activeReality == realityA){
            realityB = realityA.clone();
            System.out.println("Reality A has been duplicated into Reality B");
        } else{
            realityA = realityB.clone();
            System.out.println("Reality B has been duplicated into Reality A");
        }
    }
}
