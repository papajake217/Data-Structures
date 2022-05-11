/**
 * The <code>Status</code> enum is where all the possible statuses
 * of the customers are stored.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/

public enum Status {
    /**
     * Not on any rides or holding queues.
     */
    Available,

    /**
     * In a holding queue.
     */
    Holding,

    /**
     * On a ride currently.
     */
    OnRide
}
