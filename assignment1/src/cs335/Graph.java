package cs335;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

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
                        allNode += nodeArray[n];
                    }
                }
            }
            result += i + ": " + allNode + "\n";
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
     * @param graph the graph object to be searched
     * @param start the starting vertex of the graph
     */
    public void bfs(Graph graph, int start){
        Queue<Node> q = new LinkedList<Node>(); //queue initialization of linked list of nodes
        boolean[] discovered = new boolean[this.verticies]; //boolean array of discovered, a vertex is discovered when it is queued
        boolean[] processed = new boolean[this.verticies]; //boolean array of processed, vertex is processed when it is dequeued

        LinkedList<Node>[] adjlist = graph.getList(); //grab the adjacency list
        LinkedList<Node> list = adjlist[start]; //grab the linked list at the starting index
        Node head = new Node(start);
        q.add(head); //queue the head of the list
        discovered[start] = true; //head is now discovered
        while(!(q.isEmpty())){ //while the queue is not empty
            Node temp = q.poll(); //dequeue the head
            System.out.println("Process " + temp + " early"); //discover head i.e. process 0 early
            Node current = list.element(); //grabs the first element in the list and sets it to current node
            discovered[current.getElement()] = true; //set the discovered vertex to true in the discovered array
            System.out.println("Process Edge " + temp.getSrc() + " " + current);
          /*  while (current != null){
                if((processed[current.getSrc()] == false) || graph.getDirected()){
                    processed[current.getSrc()] = true;
                }
                if((discovered[current.getSrc()]) == false){
                    q.add(current);
                    discovered[current.getSrc()] = true;
                }
                list = adjlist[current.getElement()];
                System.out.println("Process " + current.getElement() + " late");
            }*/
        }
    } //end bfs

    /**
     * Runs a Depth-First traversal of the graph. Prints out each vertex when each vertex is discovered/processed
     * as well as when each edge is processed calls a helper function to traverse
     * @param start the starting vertex
     */
    public void dfs(int start){
        boolean[] visited = new boolean[this.verticies];
        dfsUtil(start, visited);
    }

    /**
     * Helper function to iterate over the list of nodes at the given vertex. Uses an iterator and checks to see if the node at the
     * @param start
     * @param visited
     */
    public void dfsUtil(int start, boolean[] visited){
        visited[start] = true; //set the starting vertex visited true
        System.out.println("Processed " + start + " early ");

        // Recur for all the vertices adjacent to this vertex and call dfsUtil again if its not visited
        Iterator<Node> i = this.adjLists[start].listIterator(); //create an iterator to go through the list of nodes
        while (i.hasNext()) //while there is a next node
        {
            Node n = i.next(); //grab the node
            System.out.println("Processing " + n); //process the node
            if (!visited[n.getSrc()]) //if the node is not visited, call the function again
                dfsUtil(n.getSrc(), visited);
        }
    }

}
