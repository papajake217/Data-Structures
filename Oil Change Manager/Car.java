/**
        *The <code>Car</code> class allows car objects to be created and
 * manipulated.
         *
         *
         * @author Jake Papaspiridakos
        *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
         **/

public class Car {
    private Make make; //Make/Company of car.
    private String owner; //Name of the car's owner.

    /**
     * Return an instance of Car.
     * @param make Make/Company of the car.
     * @param owner Name of the car's owner.
     */
    public Car(Make make, String owner){
        this.make = make;
        this.owner = owner;
    }

    /**
     * Getter for the make of the car.
     * @return Make of the car.
     */
    public Make getMake(){
        return this.make;
    }

    /**
     * Getter for the owner of the car.
     * @return The name of the owner of the car.
     */
    public String getOwner(){
        return this.owner;
    }

    /**
     * Setter for the make of the car.
     * @param make Make of the car.
     */
    public void setMake(Make make){
        this.make = make;
    }

    /**
     * Setter for the owner of the car.
     * @param owner Name of the owner of the car.
     */
    public void setOwner(String owner){
        this.owner = owner;
    }

    /**
     * Provides a string to describe the car.
     * @return A formatted string with the make of the car and its owner.
     */
    public String toString(){
        return String.format("%-20s %-20s",this.make,this.owner);
    }


}
