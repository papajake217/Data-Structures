import java.io.Serializable;

/**
 * The <code>Course</code> class is used to create and manipulate courses
 * to be stored inside the student's arraylist of courses.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class Course implements Serializable{
    /**
     * Three letter department of the course.
     */
    private String department;
    /**
     * Number of the course.
     */
    private int number;
    /**
     * Semester the course is taken.
     */
    private String semester;

    /**
     * Constructor for a course.
     * @param department Three letter department abbreviation of the course.
     * @param number Number of the course.
     * @param semester The semester and year it is to be taken during.
     */
    public Course(String department,int number,String semester){
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    /**
     * Getter for the department of the course.
     * @return The course's department.
     */
    public String getDepartment(){
        return this.department;
    }

    /**
     * Getter for the number of the course.
     * @return The course number.
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * Getter for the semester of the course.
     * @return The semester that the course is taken during.
     */
    public String getSemester(){
        return this.semester;
    }

    /**
     * Setter for the department of the course.
     * @param department Department to be set as the department of the course.
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * Setter for the number of the course.
     * @param number Number to be set as the number of the course.
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * Setter for the semester of the course.
     * @param semester Semester to be set as the semester of the course.
     */
    public void setSemester(String semester){
        this.semester = semester;
    }

}
