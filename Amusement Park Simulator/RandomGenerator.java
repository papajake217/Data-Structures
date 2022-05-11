/**
 * The <code>RandomGenerator</code> class is used to select a random ride
 * for a person to go to.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class RandomGenerator {

    /**
     * Method that specifies a probability distribution in an array which
     * corresponds to the probability that a given ride is chosen.
     * @param rides The array of rides to choose from.
     * @param probabilities The array that represents the probability
     *                      distribution of the chance a given ride is chosen.
     * @return The ride randomly chosen by using the specified
     * probability distribution.
     */
    public static Ride selectRide(Ride[] rides, double[] probabilities){
        double firstRange = probabilities[0];
        double secondRange = probabilities[0] + probabilities[1];
        double thirdRange = probabilities[0] + probabilities[1] +
                probabilities[2];
        double choice = Math.random();
        if(choice <= firstRange){
            return rides[0];
        } else if (choice > firstRange && choice <= secondRange) {
            return rides[1];
        } else if (choice > secondRange && choice <= thirdRange){
            return rides[2];
        } else{
            return rides[3];
        }

    }


    /**
     * Selects a random ride such that all rides have an equal chance to
     * be picked.
     * @param rides The array of rides to choose from.
     * @return The ride chosen.
     */
    public static Ride selectRide(Ride[] rides){
        int choice = (int) (Math.random() * 4);
        return rides[choice];
    }



}
