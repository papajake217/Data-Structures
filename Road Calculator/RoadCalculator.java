import java.util.*;

import big.data.DataSource;

/**
 * The <code>RoadCalculator</code> class is used to create the graph of nodes
 * and drives the program. It prints out all the roads and an MST. It then
 * allows the user to select a source city and a destination city
 * so that the program may find the shortest path and display it along with
 * its cost.
 *
 *
 * @author Jake Papaspiridakos
 *      email: jake.papaspiridakos@stonybrook.edu
 *      Stony Brook ID: 113325146
 **/



public class RoadCalculator {
    private static HashMap<String, Node> graph;
    private static LinkedList<Edge> mst;
    private static Scanner sc = new Scanner(System.in);
    static int numNodes = 0;
    static int numEdges = 0;

    /**
     * Default main method.
     * @param args Default parameter.
     */
    public static void main(String[] args){
        System.out.println("Enter graph URL: ");
        String location = sc.nextLine();
        System.out.println("\nRoads:");
        HashMap<String,Node> graph = buildGraph(location);
        System.out.println("\nMST:");
        LinkedList<Edge> MST = buildMST(graph);
        for(int i=0;i<MST.size();i++){
            System.out.println(MST.get(i).toString());
        }
        System.out.println();
        while(true) {
            System.out.println("Enter a starting point for shortest path " +
                    "or Q to quit");
            String source = sc.nextLine();
            if(source.toUpperCase().equals("Q")){
                System.out.println("Goodbye! Thank you for choosing my" +
                        " shortest path service.");
                System.exit(0);
            }

            System.out.println("Enter a destination: ");
            String dest = sc.nextLine();

            if(!(graph.containsKey(source)) || (!(graph.containsKey(dest)))){
                System.out.println("Error, source or destination does not" +
                        " exist.");
            } else if(source.equals(dest)) {
                System.out.println("Already at destination, cost is 0.");

            } else{

                int dist = Djikstra(graph, source, dest);
                if (dist == Integer.MAX_VALUE) {
                    System.out.println("No path possible");
                }  else {
                    System.out.println("Distance: " + dist);
                    System.out.println("Path:");
                    System.out.println(graph.get(dest).getPath().toString());
                }

            }

        }

    }


    /**
     * Builds the graph from the XML. Creates all the nodes and inserts
     * them into a hashmap depending on their name.
     * @param location The URL of the XML file.
     * @return A completed graph of the nodes.
     */
    public static HashMap<String,Node> buildGraph(String location){
        HashMap<String,Node> graph = new HashMap<String,Node>();
        DataSource ds = DataSource.connectXML(location);
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).
                replace("\"","").split(",");

        String roadNamesStr=ds.fetchString("roads");

        String[] roadNames=roadNamesStr.substring(2,roadNamesStr.length()-2).
                split("\",\"");

        for (int i=0;i< cityNames.length;i++){
            String city = cityNames[i];
            Node newNode = new Node(city);
            numNodes+= 1;
            graph.put(city,newNode);
        }

        for(int i=0;i<roadNames.length;i++){
            System.out.println(roadNames[i]);
            String[] info = roadNames[i].split(",");
            String cityOne = info[0];
            String cityTwo = info[1];
            int distance = Integer.parseInt(info[2]);

            Node A = graph.get(cityOne);
            Node B = graph.get(cityTwo);

            Edge newEdge = new Edge(A,B,distance);
            numEdges += 1;

            A.getEdges().add(newEdge);
            B.getEdges().add(newEdge);
        }


        return graph;
    }

    /**
     * Creates the Minimum Spanning Tree from the graph.
     * @param graph Graph to create the MST from.
     * @return A linked list of the edges taken to create the MST.
     */
    public static LinkedList<Edge> buildMST(HashMap<String,Node> graph){
        LinkedList<Edge> MST = new LinkedList<Edge>();
        int lowestCost = Integer.MAX_VALUE;
        Edge lowestEdge = null;
        boolean isNull = true;

        for(Node currentNode: graph.values()){
            if(currentNode.getVisited() == false) {
                for (Edge currentEdge : currentNode.getEdges()) {
                    isNull = false;
                    if (currentEdge.getCost() < lowestCost) {
                        lowestCost = currentEdge.getCost();
                        lowestEdge = currentEdge;
                    }
                }
            }
        }

        Node currentNode = lowestEdge.getA();
        currentNode.setVisited(true);
        boolean allNotVisited = true;
        lowestEdge = null;
        ArrayList<Node> visited = new ArrayList<Node>();
        visited.add(currentNode);
        while(allNotVisited){

            lowestCost = Integer.MAX_VALUE;

            for(int i=0;i<visited.size();i++){
                for(Edge edge : visited.get(i).getEdges()){
                    if(edge.getCost() < lowestCost
                        && (!(MST.contains(edge)))){
                        if(!(edge.getA().getVisited() &&
                        edge.getB().getVisited())) {
                            lowestCost = edge.getCost();
                            lowestEdge = edge;
                        }
                    }
                }
            }

            if(!(visited.contains(lowestEdge.getA()))){
                visited.add(lowestEdge.getA());

            } else if (!(visited.contains(lowestEdge.getB()))){
                visited.add(lowestEdge.getB());
            }
            lowestEdge.getA().setVisited(true);
            lowestEdge.getB().setVisited(true);

            MST.add(lowestEdge);
            allNotVisited = false;
            for(Node node: graph.values()){
                if(node.getVisited() == false){
                    allNotVisited = true;
                }
            }


        }

        return MST;
    }

    /**
     * Uses Djikstra's algorithm to find the shortest path in terms of
     * cost between two cities.
     * @param graph Graph of nodes to find the shortest path in.
     * @param source Name of the city to start in.
     * @param dest Name of the city to end in.
     * @return Cost of the shortest distance between the source and destination.
     */
    public static int Djikstra(HashMap<String,Node> graph, String source,
                               String dest){

        boolean notFound = true;
        Node src = graph.get(source);
        Node destination = graph.get(dest);
        ArrayList<Node> unvisitedNodes = new ArrayList<Node>();
        Node currentNode = null;
        for(Node node : graph.values()){
            if(node.getName().equals(source)){
                currentNode = node;
                node.setDistance(0);
            } else {
                unvisitedNodes.add(node);
            }
        }
        boolean destNotFound = true;

        while(destNotFound){

            for(Edge edge : currentNode.getEdges()){
                Node otherNode = null;
                if(edge.getA() == currentNode){
                    otherNode = edge.getB();
                } else{
                    otherNode = edge.getA();
                }
                if (unvisitedNodes.contains(destination)) {
                    if ((currentNode.getDistance() + edge.getCost()) <
                            otherNode.getDistance()) {
                        otherNode.setDistance(currentNode.getDistance() +
                                edge.getCost());
                        otherNode.setPath((LinkedList<String>) currentNode.
                                getPath().clone());
                        otherNode.getPath().add(currentNode.getName());
                    }
                } else{
                    destNotFound = false;
                }
            }

                unvisitedNodes.remove(currentNode);


                int lowest = Integer.MAX_VALUE;

                for (Node node : unvisitedNodes) {
                    if (node.getDistance() < lowest) {
                        lowest = node.getDistance();
                        currentNode = node;
                    }
                }

                if (lowest == Integer.MAX_VALUE) {
                    destNotFound = false;
                }

        }

        return destination.getDistance();
    }






}
