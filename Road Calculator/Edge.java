import java.util.Comparator;

/**
 * The <code>Edge</code> class is used to create and manipulate edges, which
 * are comprised of two nodes. It is used to store which nodes are connected
 * and their associated cost.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class Edge implements Comparable<Object> {

    private Node A;
    private Node B;
    private int cost;

    /**
     * Constructor for an edge.
     * @param A First node in the edge.
     * @param B Second node in the edge.
     * @param cost How "expensive" it is to travel from A to B.
     */
    public Edge(Node A, Node B, int cost){
        this.A = A;
        this.B = B;
        this.cost = cost;
    }

    /**
     * Getter for the first node in the edge.
     * @return The first node in the edge.
     */
    public Node getA(){
        return this.A;
    }

    /**
     * Getter for the second node in the edge.
     * @return The second node in the edge.
     */
    public Node getB(){
        return this.B;
    }

    /**
     * Getter for the cost of traveling between nodes A and B.
     * @return The cost of using this edge.
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * Setter for node A of the edge.
     * @param A Node to be set as node A.
     */
    public void setA(Node A){
        this.A = A;
    }

    /**
     * Setter for node B of the edge.
     * @param B Node to be set as node B.
     */
    public void setB(Node B){
        this.B = B;
    }

    /**
     * Setter for the cost of traveling along the edge.
     * @param cost Integer to be set as the new cost of using the edge.
     */
    public void setCost(int cost){
        this.cost = cost;
    }

    /**
     * Converts the information contained in the edge into a string.
     * @return A string containing the names of the two nodes, as well as
     * the cost of the edge.
     */
    public String toString(){
        return (A.getName() + " to " + B.getName() + " " + this.cost);
    }


    /**
     * Compares two edges in terms of cost to each other.
     * @param otherEdge The edge to compare to this edge.
     * @return An integer, 1 or -1, representing which edge is cheaper, or
     * 0 if they are equal.
     */
    public int compareTo(Object otherEdge){
        Edge other = (Edge) otherEdge;

        if (other.getCost() > this.cost){
            return -1;
        } else if (other.getCost() < this.cost){
            return 1;
        } else{
            return 0;
        }

    }

}
