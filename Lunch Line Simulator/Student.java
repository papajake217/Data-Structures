/**
 *The <code>Student</code> class allows student objects to be created and
 * manipulated.
 *
 * 
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class Student{
    private String name; //Student's name.
    private double money; //Amount of money the student has.


    /**
     * Return an instance of <code>Student</code>
     *
     * @param name  The name of the student.
     * @param money The amount of money the Student has.
     *
     **/
    public Student(String name, double money) {
        this.name = name;
        this.money = money;
    }

    /**
     * Getter method for the student's name.
     * @return The name of the student.
     */

    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the amount of money the student has.
     * @return The amount of money the student has.
     */

    public double getMoney() {
        return this.money;
    }

    /**
     * Setter method for the student's name.
     * @param name Name that will be given to the student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for the amount of money the student has.
     * @param money Amount of money that the student will have.
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Checks whether two students are equal based on their name and money.
     * @param obj An object that will be compared to the student.
     * @return A boolean value that represents whether the object was
     * equivalent to the student in terms of name and money.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student student = (Student) obj;
            if (this.name.compareTo(student.getName()) == 0 &&
                    this.money == student.getMoney()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Creates a formatted description of the student.
     * @return A string that describes the student, displaying their name
     * and the amount of money they have.
     */
    public String toString() {
        return String.format("%-20s $%-20.2f",this.name,this.money);
    }


    /**
     * A clone method that copies this student into a new student created
     * inside the method.
     * @return A deep copy of the student.
     */
    public Student clone() {
        return new Student(this.name,this.money);
    }
}
