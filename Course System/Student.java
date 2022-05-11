import java.io.Serializable;
import java.util.ArrayList;

/**
 * The <code>Student</code> class is used to create and store student
 * information.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class Student implements Serializable{
    /**
     * WebID of the student.
     */
    private String webID;
    /**
     * Courses that the student is taking.
     */
    private ArrayList<Course> courses;

    /**
     * Constructor for a student, creates an empty arraylist of courses
     * for them to add courses to.
     * @param webID The webID of the student.
     */
    public Student(String webID){
        this.webID = webID;
        courses = new ArrayList<Course>();
    }

    /**
     * Getter for the webId of the student.
     * @return The webID of the student in a string.
     */
    public String getWebID(){
        return this.webID;
    }

    /**
     * Getter for the courses the student is taking.
     * @return An arraylist of the courses that the student is taking.
     */
    public ArrayList<Course> getCourses(){
        return this.courses;
    }

    /**
     * Setter for the webID of the student.
     * @param webID WebId to set as the student's webID.
     */
    public void setWebID(String webID){
        this.webID = webID;
    }

    /**
     * Setter for the courses of the student.
     * @param courses Arraylist of courses to set as the student's courses.
     */
    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
    }
}
