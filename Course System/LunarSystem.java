import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


/**
 * The <code>LunarSystem</code> class is where the program runs. The registrar
 * is the only built-in registered webID and has the ability to register and
 * deregister students, as well as view all who are enrolled in a course.
 * Any students who are registered can log in and add/drop courses, as well
 * as view the ones they are currently enrolled in. Includes a save
 * feature to save all information such as students, webIDs, courses, etc.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class LunarSystem {
    private static HashMap<String, Student> database;
    static boolean running;
    static Scanner sc = new Scanner(System.in);

    /**
     * Main method where the storage file is loaded or created if not found.
     * Runs the main program's loop until the user decides to exit.
     * @param args Default main method parameters.
     */
    public static void main(String[] args){
        System.out.println("Welcome to the Lunar System, " +
                "a second place course registration system for " +
                "second rate courses at a second class school.");
        running = true;
        Boolean previousData = false;
        try {
            File check = new File("lunar.ser");
            if(check.exists()) {
                FileInputStream file = new FileInputStream("lunar.ser");
                ObjectInputStream inStream = new ObjectInputStream(file);
                database = (HashMap<String, Student>) inStream.readObject();
                previousData = true;
                System.out.println("Previous data found");
                inStream.close();
            } else{
                System.out.println("No previous data found");
                database = new HashMap<String,Student>();
            }
        } catch(FileNotFoundException e) {

        } catch (IOException g) {

        } catch (ClassNotFoundException f){

        }


        while(running){
            printGeneralOptions();
            String input = sc.nextLine();
            switch(input.toUpperCase()){
                case "L":
                    System.out.println("Please enter webID: ");
                    String login = sc.nextLine().toLowerCase();

                    if(login.toUpperCase().equals("REGISTRAR")){
                        registrarControl();
                    } else{
                        if(database.containsKey(login)){
                            Student chosen = database.get(login);
                            studentControl(chosen);
                        } else{
                            System.out.println("Error login does not exist");
                        }
                    }

                    break;
                case "X":
                    System.out.println("System state saved, " +
                            "system shut down for maintenance.");
                    if(previousData) {
                        try {
                            FileOutputStream outFile = new
                                    FileOutputStream("lunar.ser");
                            ObjectOutputStream outStream = new
                                    ObjectOutputStream(outFile);
                            outStream.writeObject(database);
                            outStream.close();
                        } catch (IOException g) {
                            System.out.println("Error IO Exception");
                            System.exit(1);
                        }
                        System.exit(0);
                    } else{
                        File newFile = new File("lunar.ser");
                        try {
                            FileOutputStream outFile = new
                                    FileOutputStream("lunar.ser");
                            ObjectOutputStream outStream = new
                                    ObjectOutputStream(outFile);
                            outFile = new FileOutputStream(newFile);
                            outStream = new ObjectOutputStream(outFile);
                            outStream.writeObject(database);
                            outStream.close();
                            System.exit(0);

                        } catch(FileNotFoundException g){
                            System.out.println("Error saving file");
                        } catch(IOException e){
                            System.out.println("IO Exception");
                        }
                    }
                    break;
                case "Q":
                    System.out.println("Good bye, " +
                            "please pick the right SUNY next time!");

                    File file = new File("lunar.ser");
                    file.delete();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Error invalid command");
                    break;
            }
        }


    }

    /**
     * Prints the general options to the user.
     */
    public static void printGeneralOptions(){
        System.out.println("Menu:\n" +
                "   L)Login\n" +
                "   X)Save state and quit\n" +
                "   Q)Quit without saving state");
    }


    /**
     * Prints the registrar's options to the user.
     */
    public static void printRegistrarOptions(){
        System.out.println("Options:\n" +
                "   R)Register A Student\n" +
                "   D)De-Register A Student\n" +
                "   E)View students enrolled in a class\n" +
                "   L)Logout");
    }

    /**
     * Prints the student's options to the user.
     */
    public static void printStudentOptions(){
        System.out.println("Options:" +
                "\n" +
                "    A)Add a class" +
                "\n" +
                "    D)Drop a class" +
                "\n" +
                "    C)View your classes sorted by course name/department" +
                "\n" +
                "    S)View your courses sorted by semester" +
                "\n" +
                "   L)Logout");
    }

    /**
     * Provides the functionality for the registrar, allowing the user
     * to register and deregister students along with listing out students
     * in a particular course. Runs in a loop until logged out of.
     */
    public static void registrarControl(){
        System.out.println("Welcome Registrar");
        String input = "A";
        while(!(input.equalsIgnoreCase("L"))){
            printRegistrarOptions();

            System.out.println("Enter an option: ");
            input = sc.nextLine().toUpperCase();
            switch(input){
                case "R":
                    System.out.println("Please enter a webID for the " +
                            "student:");
                    String webID = sc.nextLine().toLowerCase();
                    Student newStudent = new Student(webID);
                    if(database.containsKey(newStudent.getWebID())){
                        System.out.println(webID + " already exists");
                    } else{
                        database.put(webID,newStudent);
                        System.out.println(webID + " registered");
                    }
                    break;

                case "D":
                    System.out.println("Please enter the student's webID:");
                    String stID = sc.nextLine().toLowerCase();
                    if(database.containsKey(stID)){
                        database.remove(stID);
                        System.out.println(stID + " deregistered");
                    } else{
                        System.out.println("Student does not exist");
                    }

                    break;

                case "E":
                    System.out.println("Enter course:");
                    String inputCourse = sc.nextLine();
                    getEnrolled(inputCourse);
                    break;

                case "L":
                    System.out.println("Registrar logged out");
                    break;

                default:
                    System.out.println("Error, invalid command");
                    break;
            }
        }
    }

    /**
     * Provides functionality for a student to add and drop classes, as well
     * as view the classes they are enrolled in. Runs in a loop until
     * they log out.
     * @param student Student that logged in to the system.
     */
    public static void studentControl(Student student){
        String input = "A";
        System.out.println("Welcome " + student.getWebID());
        while(!(input.equals("L"))){
            printStudentOptions();
            System.out.println("Enter an option: ");
            input = sc.nextLine().toUpperCase();
            switch(input){
                case "A":
                    System.out.println("Enter course name: ");
                    String courseName = sc.nextLine().toUpperCase();
                    System.out.println("Select a semester: ");
                    String semester = sc.nextLine();
                    String[] courseArray = courseName.split(" ");
                    courseName = courseArray[0];
                    int courseNumber = Integer.parseInt(courseArray[1]);
                    Course newCourse = new Course(courseName,courseNumber,
                            semester);
                    boolean doesNotExist = true;
                    String newCourseName = newCourse.getDepartment() + " " +
                            newCourse.getNumber();
                    for(int i=0;i<student.getCourses().size();i++) {
                        Course currCourse = student.getCourses().get(i);
                        String currName = currCourse.getDepartment()
                                + " " + currCourse.getNumber();
                        if (currName.equals(newCourseName)) {
                            doesNotExist = false;
                        }
                    }
                    if(doesNotExist) {
                        student.getCourses().add(newCourse);
                        String formalSemester;
                        String season = Character.toString(semester.charAt(0));
                        String year = "";
                        for (int i = 1; i < semester.length(); i++) {
                            year += semester.charAt(i);
                        }
                        if (season.equals("F")) {
                            formalSemester = "Fall ";
                        } else {
                            formalSemester = "Spring ";
                        }

                        formalSemester += year;

                        System.out.println(courseName + courseNumber +
                                " added " +
                                "in " + formalSemester);
                    }else{
                        System.out.println("Course already enrolled in, " +
                                "current schedule unchanged");
                    }

                    break;

                case "D":
                    System.out.println("Enter the course: ");
                    String studentCourse = sc.nextLine();
                    boolean removed = false;
                    for(int i=0;i<student.getCourses().size();i++){
                        Course currentCourse = student.getCourses().get(i);
                        String currentName = currentCourse.getDepartment()
                                + " " + currentCourse.getNumber();
                        if(currentName.equals(studentCourse)){
                            System.out.println(studentCourse + " " +
                                    "has been removed");
                            student.getCourses().remove(i);
                            removed = true;
                            break;
                        }

                    }

                    if(!(removed)){
                        System.out.println("Course not found");
                    }


                    break;

                case "C":
                    System.out.println("Dept. Course Semester");
                    System.out.println("---------------------");

                    Collections.sort(student.getCourses(),
                            new CourseNameComparator());

                    for(int i=0;i<student.getCourses().size();i++){
                        Course currentClass = student.getCourses().get(i);
                        String dep = currentClass.getDepartment();
                        int num = currentClass.getNumber();
                        String sem = currentClass.getSemester();
                        System.out.println(dep + " " + num + " " + sem);
                    }
                    break;

                case "S":
                    System.out.println("Dept. Course Semester");
                    System.out.println("---------------------");

                    Collections.sort(student.getCourses(),
                            new SemesterComparator());

                    for(int i=0;i<student.getCourses().size();i++){
                        Course currentClass = student.getCourses().get(i);
                        String dep = currentClass.getDepartment();
                        int num = currentClass.getNumber();
                        String sem = currentClass.getSemester();
                        System.out.println(dep + " " + num + " " + sem);
                    }
                    break;


                case "L":
                    System.out.println(student.getWebID() + " logged out");
                    break;

                default:
                    System.out.println("Error invalid command");
                    break;
            }

        }
    }


    /**
     * Prints the students who are enrolled in a chosen course.
     * @param course Course to list the student's currently enrolled in it.
     */
    public static void getEnrolled(String course){
        System.out.println("Students enrolled in " + course + ":");
        System.out.println("Student       Semester");
        System.out.println("------------------------");

        for(Student student : database.values()){

            for(int i=0;i<student.getCourses().size();i++){
                Course current = student.getCourses().get(i);
                String className = current.getDepartment() + " " +
                        current.getNumber();
                if(className.equals(course)){
                    System.out.println(student.getWebID() + "        " +
                            student.getCourses().get(i).getSemester());
                }

            }
        }
    }

}
