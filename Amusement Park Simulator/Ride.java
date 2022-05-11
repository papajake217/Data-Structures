import java.util.ArrayList;

/**
 * The <code>Ride</code> class is used to create and manipulate
 * the rides at the amusement park.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class Ride {
    private int duration;
    private int timeLeft;
    private int capacity;
    private String name;
    VirtualLine virtualLine;
    HoldingQueue holdingQueue;
    ArrayList<Person> peopleOnRide;
    private String abbrev;
    private int numRiders;

    /**
     * Constructor for rides.
     * @param duration How long the ride lasts in minutes.
     * @param timeLeft How much time is left until the ride is finished.
     * @param name The name of the ride.
     * @param holdingQueue The Queue of people who are up next to go on the
     *                     ride.
     * @param capacity How many people the ride can hold at once.
     * @param peopleOnRide The list of people on the ride currently.
     */
    public Ride(int duration, int timeLeft,
                String name, HoldingQueue holdingQueue, int capacity,
                ArrayList<Person> peopleOnRide){
        this.duration = duration;
        this.timeLeft = timeLeft;
        this.name = name;
        this.virtualLine = new VirtualLine();
        this.capacity = capacity;
        this.holdingQueue = holdingQueue;
        this.peopleOnRide = peopleOnRide;
        this.numRiders = 0;
    }

    /**
     * Getter for the duration of the ride.
     * @return The time it takes for a ride to run from start to finish.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Getter for the amount of time left until the ride is finished.
     * @return The amount of time in minutes until the ride is done.
     */

    public int getTimeLeft(){
        return this.timeLeft;
    }

    /**
     * Getter for the name of the ride.
     * @return A string name of the ride.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the virtual line of the ride.
     * @return The current virtual line of the ride.
     */
    public VirtualLine getVirtualLine(){
        return this.virtualLine;
    }

    /**
     * Getter for the capacity of the ride.
     * @return The maximum amount of people allowed on the ride at once.
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Getter for the holding queue of the ride.
     * @return The current holding queue of the ride.
     */
    public HoldingQueue getHoldingQueue() {
        return this.holdingQueue;
    }

    /**
     * Getter for the arraylist of people currently on the ride.
     * @return The arraylist of people on the ride currently.
     */
    public ArrayList<Person> getPeopleOnRide(){
        return this.peopleOnRide;
    }

    /**
     * Getter for how many people have been on the ride in total.
     * @return The total amount of people that have been on the ride.
     */
    public int getNumRiders(){
        return this.numRiders;
    }

    /**
     * Setter for the duration of the ride.
     * @param duration How long the ride will last from start to finish.
     */
    public void setDuration(int duration){
        this.duration = duration;
    }

    /**
     * Setter for the time left of the ride.
     * @param timeLeft The time currently left until the ride finishes.
     */
    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }

    /**
     * Setter for the name of the ride.
     * @param name The string name to be set as the ride's name.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for the virtual line of the ride.
     * @param virtualLine The virtual line to be set as the current line for
     *                    the ride.
     */
    public void setVirtualLine(VirtualLine virtualLine){
        this.virtualLine = virtualLine;
    }

    /**
     * Setter for the holding queue of the ride.
     * @param holdingQueue The desired holding queue to be set as the holding
     *                     queue of the ride.
     */
    public void setHoldingQueue(HoldingQueue holdingQueue){
        this.holdingQueue = holdingQueue;
    }

    /**
     * Setter for the capacity of the ride.
     * @param capacity The desired maximum amount of people who may be
     *                 on the ride at once.
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }


    /**
     * Setter for the people currently on the ride.
     * @param peopleOnRide The arraylist of people currently on the ride.
     */
    public void setPeopleOnRide(ArrayList<Person> peopleOnRide){
        this.peopleOnRide = peopleOnRide;
    }

    /**
     * Method used to add all the people at the start to the rides
     * in the correct order such that ride seats fill up first, then holding
     * queue, and then the virtual line.
     * @param p The person to be added to the ride.
     */
    public void addToRide(Person p){
        if(this.timeLeft == 0 && this.capacity > this.
                getPeopleOnRide().size() && p.getStatus() == Status.Available) {
            this.peopleOnRide.add(p);
            this.numRiders += 1;
            p.setStatus(Status.OnRide);
            p.setNumRides(p.getNumRides()+1);
        } else if (!(this.holdingQueue.isFull()) && p.getStatus() ==
                Status.Available){
            this.holdingQueue.enqueue(p);
            p.setStatus(Status.Holding);
        } else{
            this.virtualLine.add(p);
        }
    }

    /**
     * Setter for the abbreviation of the ride.
     * @param abbrev The desired abbreviation to be set for the ride.
     */
    public void setAbbrev(String abbrev){
        this.abbrev = abbrev;
    }

    /**
     * Setter for the number of customers the ride has served.
     * @param numRiders The desired amount of customers the ride has served.
     */
    public void setNumRiders(int numRiders){
        this.numRiders = numRiders;
    }

    /**
     * Getter for the abbreviation of ride.
     * @return The abbreviation of the name of the ride.
     */
    public String getAbbrev(){
        return this.abbrev;
    }


}
