import java.util.HashSet;
import java.util.LinkedList;

/**
 * The <code>Node</code> class is used to create and manipulate nodes
 * that represent locations in the program.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/


public class Node {

    private String name;
    private HashSet<Edge> edges;
    private boolean visited;
    private LinkedList<String> path;
    private int distance;

    /**
     * Constructor for a node.
     * @param name Name of the node (City).
     */
    public Node(String name){
        this.name = name;
        this.edges = new HashSet<Edge>();
        this.visited = false;
        this.path = new LinkedList<String>();
        this.distance = Integer.MAX_VALUE;
    }

    /**
     * Getter for the name of the node.
     * @return Name of the node.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the edges the node is connected to.
     * @return A hashset of the edges connected to the node.
     */
    public HashSet<Edge> getEdges(){
        return edges;
    }

    /**
     * Getter for if the node was visited.
     * @return Whether the node has been visited or not.
     */
    public boolean getVisited(){
        return this.visited;
    }

    /**
     * Getter for the path currently taken to get to the node.
     * @return A list of all nodes taken to get to this node.
     */
    public LinkedList<String> getPath(){
        return this.path;
    }

    /**
     * Getter for the distance traveled so far to get to this node.
     * @return Distance from source to this node.
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * Setter for the name of the node.
     * @param name Name to set the node to.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for the hashset of edges of the node.
     * @param edges Hashset of the edges connected to the node.
     */
    public void setEdges(HashSet<Edge> edges){
        this.edges = edges;
    }

    /**
     * Setter for whether the node has been visited or not.
     * @param visited Whether the node was visited or not.
     */
    public void setVisited(boolean visited){
        this.visited = visited;
    }

    /**
     * Setter for the path taken to get to the node.
     * @param path A linked list of nodes taken to get to this node.
     */
    public void setPath(LinkedList<String> path){
        this.path = path;
    }

    /**
     * Setter for the distance from the source node to this node.
     * @param distance Distance to get to this node.
     */
    public void setDistance(int distance){
        this.distance = distance;
    }

}
