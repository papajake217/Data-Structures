import java.util.Comparator;

/**
 * The <code>SemesterComparator</code> class implements comparator and is
 * used to compare two courses by their semester and year. Returns a number
 * signifying which comes first by semester and year.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class SemesterComparator implements Comparator<Course> {

    /**
     * Compares two courses and returns a number representing which comes first
     * if sorted by the semester that the course is taken.
     * @param left First course to be compared.
     * @param right Second course to be compared.
     * @return -1 if left course is "less" than the right course, 1 if right
     * course is "less" than the left course, or 0 if they are equal.
     */
    public int compare(Course left, Course right){
        String leftYear = left.getSemester().substring(1);
        String rightYear = right.getSemester().substring(1);
        int leftYearNum = Integer.parseInt(leftYear);
        int rightYearNum = Integer.parseInt(rightYear);
        if(leftYearNum < rightYearNum){
            return -1;
        } else if (leftYearNum > rightYearNum){
            return 1;
        }else{
            if(left.getSemester().charAt(0) > right.getSemester().charAt(0)){
                return -1;
            } else if (left.getSemester().charAt(0) <
                    right.getSemester().charAt(0)){
                return 1;
            } else{
            return 0;
        }
        }
    }
}
