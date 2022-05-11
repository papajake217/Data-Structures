import java.util.Comparator;

/**
 * The <code>CourseNameComparator</code> class implements comparator and is
 * used to compare two courses by their department and number. Returns
 * a number signifying which comes first in order of department and number.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class CourseNameComparator implements Comparator<Course> {

    /**
     * Compares two courses and returns a number representing which comes first
     * if sorted by the name of the course.
     * @param left First course to compare.
     * @param right Second course to compare.
     * @return -1 if left course is "less" than the right course, 1 if right
     * course is "less" than the left course, or 0 if they are equal.
     */
    public int compare(Course left,Course right){
        if(left.getDepartment().compareTo(right.getDepartment()) < 0){
            return -1;
        } else if (left.getDepartment().compareTo(right.getDepartment()) > 0){
            return 1;
        } else{
            if(left.getNumber() < right.getNumber()){
                return -1;
            } else if (left.getNumber() > right.getNumber()){
                return 1;
            } else{
                return 0;
            }

        }
    }
}
