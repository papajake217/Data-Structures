
/**
 *The <code>Package</code> class allows package objects to be created
 * and manipulated.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public class Package {
    private String recipient; //Name of recipient.
    private int arrivalDate; //Day that the package arrived.
    private double weight; //Weight of the package.


    /**
     * Return an instance of Package.
     * @param recipient Name of the recipient.
     * @param arrivalDate Day that package arrived.
     * @param weight Weight of the package.
     */
    public Package(String recipient, int arrivalDate, double weight){
        this.recipient = recipient;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    /**
     * Getter for recipient.
     * @return The name of the recipient.
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Getter for arrival date.
     * @return The day that the package arrived.
     */
    public int getArrivalDate(){
        return arrivalDate;
    }

    /**
     * Getter for the weight of the package.
     * @return The weight of the package.
     */
    public double getWeight(){
        return weight;
    }

    /**
     * Setter for the name of the recipient.
     * @param recipient Name of the recipient.
     */
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }

    /**
     * Setter for the arrival date of the package.
     * @param arrivalDate The date that the package arrived.
     */
    public void setArrivalDate(int arrivalDate){
        this.arrivalDate = arrivalDate;
    }

    /**
     * Setter for the weight of the package.
     * @param weight The weight of the package.
     */
    public void setWeight(double weight){
        this.weight = weight;
    }

    /**
     * Creates a string to display information about the package.
     * @return A string describing the package including its recipient
     * and its arrival date
     */
    public String toString(){
        return this.recipient + " " + this.arrivalDate;
    }

}


