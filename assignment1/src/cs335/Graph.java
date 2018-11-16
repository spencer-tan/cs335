package cs335;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.*;

public class Graph {
    int verticies; //number of verticies
    int edges; //number of edges
    boolean weighted; // 1 if weighted 0 if unweighted
    boolean directed; //1 if directed 0 if undirected
    LinkedList<Node> adjLists[]; //linked list of nodes


    /**
     * Default Constructor
     */
    public Graph() {

    }

    /**
     * Gets the adjacency list
     * @return LinkedList<Node> adjacency list
     */
    public LinkedList<Node>[] getList() {
        return adjLists;
    }

    /**
     * Sets the new Adjacency list to the one passed in
     * @param list LinkedList<Node> list the new list to be set
     */
    public void setList(LinkedList<Node>[] list) {
        adjLists = list;
    }

    /**
     * To string method to print the whole adjacency list.
     * @return a string representation of the adjacency list
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < verticies; i++) {
            LinkedList<Node> currList = adjLists[i];
            Node currNode = new Node();
            String allNode = "";
            for(int j = 0; j < currList.size(); j++) {
                currNode = currList.get(j); //only gets head
                Node[] nodeArray = new Node[currList.size()];
                nodeArray[j] = currNode;
                for(int n = 0; n < nodeArray.length; n++) {
                    if(nodeArray[n] != null) {
                        allNode += nodeArray[n].toStringWeight();
                    }
                }
            }
            result += i + ": " + allNode + " \n";
        }
        return result;
    }

    /**
     * Set verticies
     * @param v new integer to be set as the verticies
     */
    public void setVerticies(int v) {
        verticies = v;
    }

    /**
     * Gets the number of edges in the graph
     * @return int the number of edges
     */
    public int getEdges() {
        return edges;
    }

    /**
     * Sets a new number of edges
     * @param e the new number of edges
     */
    public void setEdges(int e) {
        edges = e;
    }

    /**
     * Get the weight of the graph
     * @return true if weighted, false if not
     */
    public boolean getWeighted() {
        return weighted;
    }

    /**
     * Set the weight of the graph
     * @param weight true if weighted false if not weighted
     */
    public void setWeighted(boolean weight) {
        weighted = weight;
    }

    /**
     * Get if the graph is directed
     * @return true if directed, false if not
     */
    public boolean getDirected() {
        return directed;
    }

    /**
     * Set the direction of the graph
     * @param direct true if directed false if not
     */
    public void setDirected(boolean direct) {
        directed = direct;
    }

    /**
     * Runs a Breadth-First Search of the graph and prints when each vertex is discovered, when each edge is processed, and when each vertex is visited.
     * @param v the starting vertex of the graph
     */
    public void bfs(int v) {

        //Mark all the vertices as not visited(By default set as false)
        boolean[] processed = new boolean[verticies];
        boolean[] discovered = new boolean[verticies];
        int[] parent = new int[verticies];
        // Create a queue for BFS
        LinkedList<Node> queue = new LinkedList<Node>();

        // Mark the current node as visited and enqueue it
        Node node = new Node(v);
        queue.add(node); //queue the head of the list
        discovered[v] = true;

        while (queue.size() != 0) {
            Node head = queue.peekFirst();
            node = queue.poll(); // Get all adjacent vertices of the dequeued vertex s
      //      System.out.println("node = " + node);
            System.out.println("Process " + node + " early");
            processed[node.getY()] = true;
            Iterator<Node> i = adjLists[node.getY()].listIterator();
            while (i.hasNext()) {
                Node n = i.next();
       /*         if (directed) {
                    if ((!processed[n.getY()])) {
                        parent[n.getY()] = n.getV()();
                        if(parent[n.getV()()] == parent[n.getY()])
                    }
                } else { */
                    if ((!processed[n.getY()])) {
    //                    System.out.println("Parent Array: " + Arrays.toString(parent)); // Dequeue a vertex from queue and print it
                        System.out.println("parent at " + n.getV() + ": " + parent[n.getV()] + " and parent at " + n.getY() + ": " + parent[n.getY()]);
                        if(parent[n.getV()] == parent[n.getY()] && (parent[n.getV()] != head.getY())){
                            System.out.println("head.getY = " + head.getY());
                            System.out.println("Process edge " + n.getV() + " " + n + " (Cross Edge)"); //process the edge
                        } else if (n.getY() != parent[n.getV()]) {
                            System.out.println("Process edge " + n.getV() + " " + n + " (Tree Edge)"); //process the edge
                        } else {
                            System.out.println("Process edge " + n.getV() + " " + n + " (Back Edge)"); //process the edge
                        }
                        parent[n.getY()] = n.getV();
                    }
              if(!discovered[n.getY()]){
                  queue.add(n);
                  discovered[n.getY()] = true;
              }
            }
            System.out.println("Process " + node.getY() + " late");
         /*   System.out.println("Parent: " + Arrays.toString(parent));
            if (discovered[node.getY()] == true) {
                System.out.println("Process " + node.getY() + " late");
            }*/
        }
    }

    public void findPath(int start, int end, int parents[]) {
        if ((start == end) || (end == -1)) {
            System.out.println(start);
         } else {
             findPath(start,parents[end],parents);
             System.out.println(end);
        }
    }

    /**
     * Runs a Depth-First traversal of the graph. Prints out each vertex when each vertex is discovered/processed
     * as well as when each edge is processed calls a helper function to traverse
     * @param start the starting vertex
     */
    public void dfs(int start){
        boolean[] discovered = new boolean[verticies];
        boolean[] processed = new boolean[verticies];
        int[] parent =  new int[verticies]; //parent array
        dfsUtil(start, discovered, processed, parent);
        System.out.println("Process " + start + " late ");
    }


    public void dfsUtil(int v, boolean[] visited, boolean[] processed, int[] parent){
        visited[v] = true; //set the starting vertex visited true
        System.out.println("Process " + v + " early "); //process v early
        Iterator<Node> i = this.adjLists[v].listIterator(); //create an iterator to go through the list of nodes
        while (i.hasNext()) //while there is a next node
        {
            Node n = i.next(); //grab the node

            if (!visited[n.getY()]) { //if the node is not visited, print the edge, set the node as visited, and call the function again
                parent[n.getY()] = v; //set the parent of the node to the previous node
                System.out.println("Process edge " + n.getV() + " " + n + " (Tree Edge)");
                processed[v] = true;
                dfsUtil(n.getY(), visited, processed, parent);
                System.out.println("Process " + n + " late ");
            } else if((!processed[n.getY()] && (parent[v] != n.getY()) || directed)) {
                if (parent[n.getV()] != n.getY() && parent[n.getY()] == 0) {
                    System.out.println("Process edge " + n.getV() + " " + n + " (Back Edge)");
                }

            }
        }
    }
}
