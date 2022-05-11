import java.util.ArrayList;

/**
 * The <code>Person</code> class is used to create and manipulate
 * the people who visit the amusement park.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class Person {
    private int number;
    private int maxLines;
    private int numRides;

    private ArrayList<Ride> lines = new ArrayList<Ride>(3);
    private Status status;

    /**
     * Constructor for a person.
     * @param number The ID of the person.
     * @throws IllegalArgumentException Indicates a negative ID was inputted
     * into the constructor.
     */
    public Person(int number) throws IllegalArgumentException{
        try {
            if (number < 1) {
                throw new IllegalArgumentException();
            }
            this.numRides = 0;
            this.number = number;

        }catch(IllegalArgumentException e){
            System.out.println("Error, number cannot be negative");
        }
    }

    /**
     * Getter for the ID of the person.
     * @return The integer ID of the person.
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * Getter for the maximum number of lines the person can be on.
     * @return The max amount of lines the person is allowed on.
     */
    public int getMaxLines(){
        return this.maxLines;
    }

    /**
     * Getter for the lines the person is currently on.
     * @return An arraylist of the rides in which the person is in line for.
     */
    public ArrayList<Ride> getLines(){
        return this.lines;
    }

    /**
     * Getter for the current status of the person.
     * @return The current status (Available,Holding,On Ride) of the person.
     */
    public Status getStatus(){
        return this.status;
    }

    /**
     * Getter for the number of rides the person has been on.
     * @return The number of rides the person has been on in total over
     * the course of the simulation.
     */
    public int getNumRides(){
        return this.numRides;
    }

    /**
     * Setter for the ID of the person.
     * @param number The integer ID to change the person's ID to.
     */
    public void setNumber(int number){
        this.number = number;
    }

    /**
     * Setter for the maximum number of lines the person can be on.
     * @param maxLines Desired amount of max lines the person can be on.
     */
    public void setMaxLines(int maxLines){
        this.maxLines = maxLines;
    }

    /**
     * Setter for the lines the person is currently on.
     * @param lines The arraylist of rides in which the person is currently
     *              on a line for.
     */
    public void setLines(ArrayList<Ride> lines){
        this.lines = lines;
    }

    /**
     * Adds the person to a specific ride.
     * @param ride Ride in which the person will be added to.
     */
    public void addLine(Ride ride){
        this.lines.add(ride);
    }

    /**
     * Setter for the current status of the person.
     * @param status The status in which to set the person to.
     */
    public void setStatus(Status status){
        this.status = status;
    }

    /**
     * Setter for the number of rides this person has gone on in total.
     * @param numRides Number to set as their total rides.
     */
    public void setNumRides(int numRides){
        this.numRides = numRides;
    }
}
