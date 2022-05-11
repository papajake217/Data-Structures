import java.util.ArrayList;
import java.util.Scanner;

/**
 * The <code>SevenFlags</code> class is used drive the simulation and
 * utilize the other classes in order to simulate the amusement park.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class SevenFlags {

    /**
     * Main Method of the simulation. Creates all the necessary
     * objects and runs the simulation with user input.
     * @param args Default parameter for main method.
     */
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        double[] probabilities = new double[4];

        System.out.println("Welcome to Seven Flags!");

        int regularCustomers,silverCustomers,goldCustomers;
        System.out.println("Please enter the number of regular customers: ");
        regularCustomers = sc.nextInt();
        while(regularCustomers < 0){
            System.out.println("Error, invalid number of customers.\n" +
                    "Please enter the number of regular customers: ");
            regularCustomers = sc.nextInt();
        }
        System.out.println("Please enter the number of silver customers: ");
        silverCustomers = sc.nextInt();
        while(silverCustomers < 0){
            System.out.println("Error, invalid number of customers.\n" +
                    "Please enter the number of silver customers: ");
            silverCustomers = sc.nextInt();
        }
        System.out.println("Please enter the number of gold customers: ");
        goldCustomers = sc.nextInt();
        while(goldCustomers < 0){
            System.out.println("Error, invalid number of customers.\n" +
                    "Please enter the number of gold customers: ");
            goldCustomers = sc.nextInt();
        }
        int id = 1;
        Person[] goldArray = new Person[goldCustomers];
        Person[] silverArray = new Person[silverCustomers];
        Person[] regularArray = new Person[regularCustomers];
        Person[][] peopleArray = {goldArray,silverArray,regularArray};

        for(int x = 0;x<3;x++) {
            id = 1;
            for (int i = 0; i < peopleArray[x].length; i++) {
                peopleArray[x][i] = new Person(id);
                id++;
                peopleArray[x][i].setStatus(Status.Available);
                peopleArray[x][i].setMaxLines(3-x);
            }
        }

        int simulationLength;
        System.out.println("Please enter the simulation length: ");
        simulationLength = sc.nextInt();
        while(simulationLength < 1){
            System.out.println("Error, simulation length must be " +
                    "1 or more.\n" +
                    "Please enter the simulation length: ");
            simulationLength = sc.nextInt();
        }



        Ride BSOD = new Ride(0,0,"Blue Scream of Death"
                ,new HoldingQueue(0),0,
                new ArrayList<Person>());
        Ride KK = new Ride(0, 0, "Kingda Knuth"
                , new HoldingQueue(0),0,
                new ArrayList<Person>());
        Ride TOT = new Ride(0,0,"i386 Tower of Terror"
                ,new HoldingQueue(0),0,
                new ArrayList<Person>());
        Ride GF = new Ride(0,0,"GeForce"
                ,new HoldingQueue(0),0,
                new ArrayList<Person>());

        BSOD.setAbbrev("BSOD");
        KK.setAbbrev("KK");
        TOT.setAbbrev("TOT");
        GF.setAbbrev("GF");
        Ride[] rides = {BSOD,KK,TOT,GF};
        int duration,capacity,holdingQueueSize;
        double probability;
        double probabilitySum = 0;
        for(int i=0;i<rides.length;i++){
            System.out.println("Please enter the duration of " +
                    rides[i].getName() + ": ");
            duration = sc.nextInt();
            while(duration < 1){
                System.out.println("Error, cannot have a duration less " +
                        "than 1.\nPlease enter a valid duration: ");
                duration = sc.nextInt();
            }
            rides[i].setDuration(duration);
            System.out.println("Please enter the capacity of " +
                    rides[i].getName() + ": ");
            capacity = sc.nextInt();
            while(capacity < 1){
                System.out.println("Error, cannot have a capacity less " +
                        "than 1.\nPlease enter a valid capacity: ");
                capacity = sc.nextInt();
            }
            rides[i].setCapacity(capacity);
            System.out.println("Please enter the holding queue size of " +
                    rides[i].getName() + ": ");
            holdingQueueSize = sc.nextInt();
            while(holdingQueueSize < 1){
                System.out.println("Error, cannot have a holding queue" +
                        " size less " +
                        "than 1.\nPlease enter a valid holding queue size: ");
                holdingQueueSize = sc.nextInt();
            }
            rides[i].getHoldingQueue().setMaxSize(holdingQueueSize);
            if(i < rides.length-1 && probabilitySum != 1) {
                System.out.println("Please enter the probability " +
                        "that a person " +
                        "chooses " + rides[i].getName() +
                        " (The sum of all rides " +
                        "probabilities must sum to 1 exactly): ");
                probability = sc.nextDouble();

                while (probability < 0 || probabilitySum + probability > 1) {
                    System.out.println("Error, invalid probability, " +
                            "please enter" +
                            " a new probability for " + rides[i].getName() +
                            ": ");
                    probability = sc.nextDouble();
                }
            } else{
                probability = 1-probabilitySum;
                System.out.printf("Probability for " + rides[i].getName() +
                        " has been set to %.2f to complete" +
                        " the distribution automatically.\n",probability);

            }
            probabilitySum += probability;
            probabilities[i] = probability;
            rides[i].setTimeLeft(0);
        }

        System.out.println("Press enter to run the simulation");
        sc.nextLine();
        sc.nextLine();

        for(int j=0;j<3;j++) {
            for (int i = 0; i < 3; i++) {
                for (int x = 0; x < peopleArray[i].length; x++) {
                    if(j==0) {
                        Ride chosenRide = RandomGenerator.selectRide(rides,
                                probabilities);
                        peopleArray[i][x].getLines().add(chosenRide);
                        chosenRide.addToRide(peopleArray[i][x]);
                    } else if(j == 1 && (i==0 || i == 1)){
                        Ride chosenRide = RandomGenerator.selectRide(rides,
                                probabilities);
                        peopleArray[i][x].getLines().add(chosenRide);
                        chosenRide.addToRide(peopleArray[i][x]);
                    } else if(j == 2 && i==0){
                        Ride chosenRide = RandomGenerator.selectRide(rides,
                                probabilities);
                        peopleArray[i][x].getLines().add(chosenRide);
                        chosenRide.addToRide(peopleArray[i][x]);
                    }
                }
            }
        }


        for(int i = 0;i<rides.length;i++){
            rides[i].setTimeLeft(rides[i].getDuration());
        }

        System.out.println();
        int time = 0;
        while(time <= simulationLength) {
            System.out.println("At Time " + time + ":");
            time++;

            String customerInfo = "";
            customerInfo += ("\nRegular Customers:");
            customerInfo += ("\nNum  Line  Status");
            customerInfo += ("\n-----------------");
            for(int z=0;z<regularArray.length;z++){
                customerInfo += ("\n" + regularArray[z].getNumber()
                        +"." + "  " +
                        regularArray[z].getLines().get(0).getAbbrev() +
                        "  " + regularArray[z].getStatus());
            }

            customerInfo += ("\n\nSilver Customers:");
            customerInfo += ("\nNum  Line1  Line2  Status");
            customerInfo += ("\n-------------------------");
            for(int z=0;z<silverArray.length;z++){
                customerInfo += ("\n" + silverArray[z].getNumber() +
                        "." + "  "
                        + silverArray[z].getLines().get(0).getAbbrev()
                        + "  " +
                        silverArray[z].getLines().get(1).getAbbrev()
                        + "  " +
                        silverArray[z].getStatus());
            }

            customerInfo += ("\n\nGold Customers:");
            customerInfo += ("\nNum  Line1  Line2  Line3  Status");
            customerInfo +=("\n--------------------------------");


            for(int z=0;z<goldArray.length;z++){
                customerInfo += ("\n" + goldArray[z].getNumber() + "." +
                        "  " + goldArray[z].getLines().get(0).getAbbrev() +
                        "  " +
                        goldArray[z].getLines().get(1).getAbbrev() + "  " +
                        goldArray[z].getLines().get(2).getAbbrev() + "  " +
                        goldArray[z].getStatus());
            }


            for (int i = 0; i < rides.length; i++) {

                rideInfo(rides[i]);
                System.out.println("---------------");

            }
            System.out.println(customerInfo);

            System.out.println("-----------------------------------------"
                    + "----------------------------\n");


            for (int i = 0; i < rides.length; i++) {


                if(rides[i].getTimeLeft() <= 1){
                    int length = rides[i].getPeopleOnRide().size();
                    for(int k=0;k<length;k++) {
                        Person onRide = rides[i].getPeopleOnRide().
                                remove(0);

                        onRide.getLines().remove(rides[i]);
                        Ride chooseRide = RandomGenerator.selectRide(rides,
                                probabilities);
                        if(!(chooseRide.getHoldingQueue().isFull())){
                            chooseRide.getHoldingQueue().enqueue(onRide);
                            onRide.setStatus(Status.Holding);
                        } else{
                            chooseRide.getVirtualLine().enqueue(onRide);
                            onRide.setStatus(Status.Available);
                        }
                        onRide.getLines().add(chooseRide);

                    }


                    length = rides[i].getCapacity();
                    for(int j=0;j<length;j++){
                        if (!(rides[i].getHoldingQueue().isEmpty())) {
                            Person inQueue = rides[i].
                                    getHoldingQueue().dequeue();
                                rides[i].getPeopleOnRide().add(inQueue);
                                inQueue.setStatus(Status.OnRide);
                                inQueue.setNumRides(inQueue.getNumRides() + 1);
                                rides[i].setNumRiders(rides[i].
                                        getNumRiders()+1);

                        }
                    }

                    length = rides[i].getVirtualLine().size();

                    for(int j=0;j<length;j++){
                        if(!(rides[i].getVirtualLine().isEmpty()) &&
                            !(rides[i].getHoldingQueue().isFull())){
                            Person inLine = rides[i].getVirtualLine()
                                    .dequeue();
                            if(inLine.getStatus() == Status.Available){
                                rides[i].getHoldingQueue().enqueue(inLine);
                                inLine.setStatus(Status.Holding);
                            } else{
                                rides[i].getVirtualLine().enqueue(inLine);
                            }
                        }
                    }

                    rides[i].setTimeLeft(rides[i].getDuration());
                } else{
                    rides[i].setTimeLeft(rides[i].getTimeLeft()-1);


                }


            }






        }
        double goldAverage = 0;
        for(int i=0;i<goldArray.length;i++){
            goldAverage += goldArray[i].getNumRides();
        }
        if(goldArray.length > 0) {
            goldAverage = goldAverage / goldArray.length;
        } else {
            goldAverage = 0;
        }

        double silverAverage = 0;
        for(int i=0;i<silverArray.length;i++){
            silverAverage += silverArray[i].getNumRides();
        }

        if(silverArray.length > 0) {
            silverAverage = silverAverage / silverArray.length;
        } else{
            silverAverage = 0;
        }

        double regularAverage = 0;
        for(int i=0;i<regularArray.length;i++){
            regularAverage += regularArray[i].getNumRides();
        }
        if(regularArray.length > 0) {
            regularAverage = regularAverage / regularArray.length;
        } else{
            regularAverage = 0;
        }


        System.out.printf("On average, Gold customers have taken %.2f rides." +
                "\nOn average, Silver customers have taken %.2f rides." +
                "\nOn average, regular customers have taken %.2f rides.\n",
                goldAverage,silverAverage,regularAverage);


        for(int i=0;i< rides.length;i++){
            System.out.println(rides[i].getName() + " has completed rides " +
                    "for " + rides[i].getNumRiders() + " people.");
        }


    }


    /**
     * Method to print current information about the ride including how
     * much time is left until it is finished and who is on the ride,
     * its holding queue, and its virtual line.
     * @param ride The ride to display current information about.
     */
    public static void rideInfo(Ride ride){
        System.out.print(ride.getName() + " - Time remaining: " +
                ride.getTimeLeft() + " min");
        int length = ride.getPeopleOnRide().size();

        System.out.print("\nOn Ride: ");
        for(int i=0;i<ride.getPeopleOnRide().size();i++){
            Person entry = ride.getPeopleOnRide().get(i);
            if(entry.getMaxLines() == 1){
                System.out.print("Regular " + entry.getNumber());
            } else if(entry.getMaxLines() == 2){
                System.out.print("Silver " + entry.getNumber());
            } else if(entry.getMaxLines() == 3){
                System.out.print("Gold " + entry.getNumber());
            }
            System.out.print(", ");
        }
        System.out.println();

        System.out.print("Holding Queue: ");
        for(int i=0;i<ride.getHoldingQueue().size();i++){
            Person entry = ride.getHoldingQueue().get(i);
            if(entry.getMaxLines() == 1){
                System.out.print("Regular " + entry.getNumber());
            } else if(entry.getMaxLines() == 2){
                System.out.print("Silver " + entry.getNumber());
            } else if(entry.getMaxLines() == 3){
                System.out.print("Gold " + entry.getNumber());
            }
            System.out.print(", ");
        }

        System.out.println();

        System.out.print("Virtual Line: ");
        for(int i=0;i<ride.getVirtualLine().size();i++){
            Person entry = ride.getVirtualLine().get(i);
            if(entry.getMaxLines() == 1){
                System.out.print("Regular " + entry.getNumber());
            } else if(entry.getMaxLines() == 2){
                System.out.print("Silver " + entry.getNumber());
            } else if(entry.getMaxLines() == 3){
                System.out.print("Gold " + entry.getNumber());
            }
            System.out.print(", ");
        }

        System.out.println();

    }





}
