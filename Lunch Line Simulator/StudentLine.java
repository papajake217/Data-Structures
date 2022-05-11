/**
 *The <code>StudentLine</code> class allows lines of students to be created and
 * manipulated.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class StudentLine {
    private Student[] students; //An array of objects of type Student.
    private int studentCount; //The number of students currently on the line.
    final private int CAPACITY = 20; //The maximum number of students the line
    // can hold.

    /**
     * Default constructor for the line. Creates an empty line.
     */
    public StudentLine(){
        this.students = new Student[CAPACITY]; //Creates the students array
        // with size of capacity.
        this.studentCount = 0; //Begins the count of students on the line at 0.
    }

    /**
     * Obtains the amount of students in line from outside the class.
     * @return The number of students that are currently on the line.
     */
    public int numStudents(){
        return this.studentCount;
    }

    /**
     * Retrieves a student from a chosen index. Does not alter the line.
     * @param index The index at which to retrieve the student at.
     * @return The chosen student as an object of its respective class.
     * @exception ArrayIndexOutOfBoundsException Indicates that the index
     * inputted into the function is invalid, either being less than 0 or
     * greater than the capacity.
     */
    public Student getStudent(int index) throws ArrayIndexOutOfBoundsException{
        try{
            return students[index];
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        return null;

    }

    /**
     * Getter method for the capacity of the line.
     * @return The maximum number of students that can fit on the line.
     */
    public int getCAPACITY(){
        return this.CAPACITY;
    }

    /**
     * Removes a student from the line at a chosen index and returning them.
     * It will then move all students with a higher index down by one to fill
     * the hole. Finally, it will set the array at the index to null to
     * remove the student from the actual list.
     * @param index The index at which to remove the student at.
     * @return The student that has been removed from the line.
     * @exception ArrayIndexOutOfBoundsException Indicates an invalid index of
     * the students array trying to be accessed.
     * @exception EmptyLineException Indicates there is no student on the line,
     * therefore there is nothing to remove.
     */
    public Student removeStudent(int index) throws
            ArrayIndexOutOfBoundsException,EmptyLineException{
        try {
            if(studentCount == 0){
                throw new EmptyLineException();
            } else if (index > CAPACITY || index>this.numStudents()-1) {
                throw new ArrayIndexOutOfBoundsException();
            } else {

                Student chosenStudent = new Student(students[index].getName(),
                        students[index].getMoney());

                for (int i = index + 1; i < numStudents(); i++) {
                    students[i - 1] = students[i].clone();
                }

                this.studentCount--;
                students[index] = null;
                return chosenStudent;
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You have tried to remove a student at " +
                    "an index that does not exist.\nIn order " +
                    "to prevent chaos this action has been reverted. " +
                    "The line is unchanged.");
        } catch(EmptyLineException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Add a student at a chosen index. If students exist at that index and at
     * higher indexes, then they are all moved up by one index.
     * @param index The index at which to add a student to line at.
     * @param student The Student object which will be placed at the index.
     * @exception IllegalArgumentException Indicates an invalid index at which
     * the student is attempting to be placed at.
     * @exception DeanException Indicates the line is full meaning a student
     * cannot be added to it.
     */
    public void addStudent(int index,Student student) throws
            IllegalArgumentException,DeanException  {

        try {
            if(index > CAPACITY-1){
                throw new IllegalArgumentException();
            }

            if(studentCount == CAPACITY){
                throw new DeanException();
            }

            for (int i = studentCount; i > index; i--) {
                students[i] = students[i - 1].clone();
            }

            students[index] = new Student(student.getName(),
                    student.getMoney());

            this.studentCount++;
        } catch (IllegalArgumentException e){
            System.out.println("Cannot add student to an invalid index, " +
                    "student not added.");
        } catch (DeanException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to swap two students on the line by using a temporary student to
     * store one while the other is copied into their place.
     * @param index1 The index of the first student to be swapped.
     * @param index2 The index of the second student to be swapped.
     * @exception ArrayIndexOutOfBoundsException Indicates the swap cannot
     * occur because either or both indexes are invalid. This can either mean
     * there is no student at one or both of those indexes, or the index is
     * out of range of the students array.
     */
    public void swapStudents(int index1,int index2) throws
            ArrayIndexOutOfBoundsException{
        try {
            if (students[index1] == null || students[index2] == null) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                Student temp = new Student(students[index1].getName(),
                        students[index1].getMoney());
                students[index1].setMoney(students[index2].getMoney());
                students[index1].setName(students[index2].getName());
                students[index2].setMoney(temp.getMoney());
                students[index2].setName(temp.getName());
            }
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("You have tried to swap students in at " +
                    "least one place where they do not exist. Luckily, " +
                    "this mistake was caught before " +
                    "a hole was ripped in the spacetime continuum." +
                    "\nThe line is unchanged and reality shall resume.");
        }
    }

    /**
     * Clones the line and all the students on it in the order they are in.
     * @return A deep clone of the line of students.
     */
    public StudentLine clone(){
        StudentLine clonedLine = new StudentLine();
        for(int i=0;i<this.studentCount;i++){
            clonedLine.students[i] = this.students[i].clone();
            clonedLine.studentCount++;
        }
       return clonedLine;
    }

    /**
     * Used to tell whether the student line is equivalent to another. It will
     * automatically detect if the object it is being compared to is even a
     * line of students.
     * @param o An object which the line will be compared to.
     * @return A boolean value representing whether the object is equivalent to
     * the student line or not.
     */
    public boolean equals(Object o){
        if(o instanceof StudentLine){
            if(((StudentLine) o).numStudents() != this.studentCount){
                return false;
            }
            if(((StudentLine) o).studentCount == this.studentCount){
                for(int i=0;i<this.studentCount;i++){
                    if (!(((StudentLine) o).students[i]
                            .equals(this.students[i]))){
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and formats a string that represents the students in line in
     * order and the information about them.
     * @return A string that represents the line in a list format, displaying
     * the position, name, and money of each student in order.
     */
    public String toString(){
        String status = "";
        for(int i=0;i<this.studentCount;i++){
            if(i < 9 )status += (i+1) + ". " + students[i].toString() + " \n";
            else status += (i+1) + "." + students[i].toString() + " \n";
        }

        return status;
    }

}
