package cs335;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class KrushkalMST {

    static class Edge {
        int source;
        int destination;
        double weight;

        public Edge(){

        }

        public Edge(int destination){
            this.destination = destination;
        }

        public Edge(int source, int destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * Get Source of Edge
         * @return
         */
        public int getSource(){
            return source;
        }

        /**
         * Set the index of the list
         * @param s thr new integer source
         */
        public void setSource(int s) {
            source = s;
        }

        /**
         * Set the weight of the node
         * @param weight
         */
        public void setWeight(double weight) {
            this.weight = weight;
        }

        /**
         * Get the destination of the edge
         * @return
         */
        public int getDestination(){
            return destination;
        }

        public double getWeight(){
            return weight;
        }

        /**
         * Print the contents of the edge with the weight
         * @return String of contents of the node
         */
        public String toStringWeight() {
            if (weight == 0){
                return (Integer.toString(destination) + " ");
            } else {
                return (destination + "(" + (int)weight + ") ");
            }
        }

        public String toString() {
            return (Integer.toString(destination));
        }

    }

    static class Graph {
        int vertices;
        int edges;
        ArrayList<Edge> allEdges = new ArrayList<>();
        boolean weighted; // 1 if weighted 0 if unweighted
        boolean directed; //1 if directed 0 if undirected
        LinkedList<Edge> adjLists[]; //linked list of nodes

        /**
         * Default Constructor
         */
        public Graph() {

        }

        Graph(int vertices) {
            this.vertices = vertices;
        }

        /**
         * Gets the adjacency list
         * @return LinkedList<Edge> adjacency list
         */
        public LinkedList<Edge>[] getList() {
            return adjLists;
        }

        /**
         * Set vertices
         * @param v new integer to be set as the vertices
         */
        public void setVertices(int v) {
            vertices = v;
        }

        public int getEdges(){
            return edges;
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
         * Sets the new Adjacency list to the one passed in
         * @param list LinkedList<Edge> list the new list to be set
         */
        public void setList(LinkedList<Edge>[] list) {
            adjLists = list;
        }

        /**
         * To string method to print the whole adjacency list.
         * @return a string representation of the adjacency list
         */
        public String toString() {
            String result = "";
            for (int i = 0; i < vertices; i++) {
                LinkedList<Edge> currList = adjLists[i];
                Edge currEdge = new Edge();
                String allNode = "";
                for(int j = 0; j < currList.size(); j++) {
                    currEdge = currList.get(j); //only gets head
                    Edge[] edgeArray = new Edge[currList.size()];
                    edgeArray[j] = currEdge;
                    for(int n = 0; n < edgeArray.length; n++) {
                        if(edgeArray[n] != null) {
                            allNode += edgeArray[n].toStringWeight();
                        }
                    }
                }
                result += i + ": " + allNode + " \n";
            }
            return result;
        }

        /**
         * Runs a Breadth-First Search of the graph and prints when each vertex is discovered, when each edge is processed, and when each vertex is visited.
         * @param v the starting vertex of the graph
         */
        public void bfs(int v) {

            //Mark all the vertices as not visited(By default set as false)
            boolean[] processed = new boolean[vertices];
            boolean[] discovered = new boolean[vertices];
            int[] parent = new int[vertices];
            // Create a queue for BFS
            LinkedList<Edge> queue = new LinkedList<Edge>();

            // Mark the current node as visited and enqueue it
            Edge edge = new Edge(v);
            queue.add(edge); //queue the head of the list
            discovered[v] = true;

            while (queue.size() != 0) {
                Edge head = queue.peekFirst();
                edge = queue.poll(); // Get all adjacent vertices of the dequeued vertex s
                System.out.println("Process " + edge + " early");
                processed[edge.getDestination()] = true;
                Iterator<Edge> i = adjLists[edge.getDestination()].listIterator();
                while (i.hasNext()) {
                    Edge n = i.next();
       /*         if (directed) {
                    if ((!processed[n.getDest()])) {
                        parent[n.getDest()] = n.getSrc();
                        if(parent[n.getSrc()] == parent[n.getDest()])
                    }
                } else { */
                    if ((!processed[n.getDestination()]) || directed){
                        //             System.out.println("Parent Array: " + Arrays.toString(parent)); // Dequeue a vertex from queue and print it
                        //             System.out.println("parent at " + n.getSrc + ": " + parent[n.getSrc] + " and parent at " + n.getDest() + ": " + parent[n.getDest()]);
                        if((parent[n.getSource()] == parent[n.getDestination()] && parent[n.getSource()] == -1 && parent[n.getDestination()] == -1)|| (parent[n.getSource()] == -1 && parent[n.getDestination()] == -1)){
                            //                  System.out.println("head.getDest = " + head.getDest());
                            System.out.println("Process edge " + n.getSource() + " " + n + " (Cross Edge)"); //process the edge
                        } else if (n.getDestination() != parent[n.getSource()]) {
                            System.out.println("Process edge " + n.getSource() + " " + n + " (Tree Edge)"); //process the edge
                        } else {
                            System.out.println("Process edge " + n.getSource() + " " + n + " (Back Edge)"); //process the edge
                        }
                        if(n.getSource() == 0) {
                            parent[n.getDestination()] = -1;
                        } else {
                            parent[n.getDestination()] = n.getSource();
                        }
                        if(!discovered[n.getDestination()]){
                            queue.add(n);
                            discovered[n.getDestination()] = true;
                        }
                    }
                }
                System.out.println("Process " + edge.getDestination() + " late");
         /*   System.out.println("Parent: " + Arrays.toString(parent));
            if (discovered[node.getDest()] == true) {
                System.out.println("Process " + node.getDest() + " late");
            }*/
            }
        }

        /**
         * Runs a Depth-First traversal of the graph. Prints out each vertex when each vertex is discovered/processed
         * as well as when each edge is processed calls a helper function to traverse
         * @param start the starting vertex
         */
        public void dfs(int start){
            boolean[] discovered = new boolean[vertices];
            boolean[] processed = new boolean[vertices];
            int[] parent =  new int[vertices]; //parent array
            dfsUtil(start, discovered, processed, parent);
            System.out.println("Process " + start + " late ");
        }


        public void dfsUtil(int v, boolean[] visited, boolean[] processed, int[] parent){
            visited[v] = true; //set the starting vertex visited true
            System.out.println("Process " + v + " early "); //process v early
            Iterator<Edge> i = this.adjLists[v].listIterator(); //create an iterator to go through the list of nodes
            while (i.hasNext()) //while there is a next node
            {
                Edge e = i.next(); //grab the node

                if (!visited[e.getDestination()]) { //if the node is not visited, print the edge, set the node as visited, and call the function again
                    parent[e.getDestination()] = v; //set the parent of the node to the previous node
                    System.out.println("Process edge " + e.getSource() + " " + e + " (Tree Edge)");
                    processed[v] = true;
                    dfsUtil(e.getDestination(), visited, processed, parent);
                    System.out.println("Process " + e + " late ");
                } else if((!processed[e.getDestination()] && (parent[v] != e.getDestination()) || directed)) {
                    if (parent[e.getSource()] != e.getDestination() && parent[e.getDestination()] == 0) {
                        System.out.println("Process edge " + e.getSource() + " " + e + " (Back Edge)");
                    }

                }
            }
        }



        public void addEdge(int source, int destination, double weight) {
            Edge edge = new Edge(source, destination, weight);
            allEdges.add(edge); //add to total edges
        }

        public void kruskalMST(){
            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingDouble(o -> o.weight));

            //add all the edges to priority queue, //sort the edges on weights
            for (int i = 0; i <allEdges.size() ; i++) {
                pq.add(allEdges.get(i));
            }

            //create a parent []
            int [] parent = new int[vertices];

            //makeset
            makeSet(parent);

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int index = 0;
            while(index < vertices-1){
                Edge edge = pq.remove();
                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.source);
                int y_set = find(parent, edge.destination);

                if(x_set==y_set){
                    //ignore, will create cycle
                }else {
                    //add it to our final result
                    mst.add(edge);
                    index++;
                    union(parent,x_set,y_set);
                }
            }
            //print MST
            System.out.println("Minimum Spanning Tree: ");
            printGraph(mst);
        }

        public void makeSet(int [] parent){
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i <vertices ; i++) {
                parent[i] = i;
            }
        }

        public int find(int [] parent, int vertex){
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if(parent[vertex]!=vertex)
                return find(parent, parent[vertex]);;
            return vertex;
        }

        public void union(int [] parent, int x, int y){
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        }

        public void printGraph(ArrayList<Edge> edgeList){
            for (int i = 0; i <edgeList.size() ; i++) {
                Edge edge = edgeList.get(i);
                System.out.println("Edge-" + i + " source: " + edge.source +
                        " destination: " + edge.destination +
                        " weight: " + edge.weight);
            }
        }
    } //ends graph class

    public static void main(String[] args) {
        File file = new File("./src/CS335/test.txt"); //create file
        int vertices = 0; //number of vertices

        Graph graph = new Graph(); //create a graph
        try {
            Scanner scan = new Scanner(file); //scan the file

            String temp = scan.nextLine(); //grab the first line
            String allVert = temp.substring(0,1); //grab the number of vertices of the graph, file(0)
            vertices = Integer.parseInt(allVert); //parse it into an int and set it to the variable vertices
            graph.setVertices(vertices); //set the vertices of the graph
            String strDirected = temp.substring(4, 5); //grab the token 0 or 1 to see if it's directed
            int direction = Integer.parseInt(strDirected); //assign 0 or 1 to the show directed or not
            boolean direct = false; //new variable direct to check if the graph is directed or not
            if (direction == 1) {
                direct = true;
            }
            graph.setDirected(direct); //set the boolean directed true if its directed false if its not directed
            String strWeight = temp.substring(6); //grab the weight of the graph (0 or 1)
            int weighted = Integer.parseInt(strWeight); //parse it to an int
            boolean isWeighted = false; //new variable weight to check if the graph is weighted
            if (weighted == 1) {
                isWeighted = true;
            }
            graph.setWeighted(isWeighted); //set the weight of the graph

            LinkedList<Edge>[] adjLists = new LinkedList[vertices]; //create the adjacency list, array of linked list of nodes

            for (int i = 0; i < vertices; i++) {
                adjLists[i] = new LinkedList<Edge>(); //instantiate an empty list at each index of the adjacency list
            }

            while (scan.hasNextLine()) { // while the file has a next line in the file
                double weight = 0;
                String currentLine = scan.nextLine(); //grab the line
                String strVert = currentLine.substring(0, 1); //get the vertex
                int currVert = Integer.parseInt(strVert); //parse the vertex into an int
                String strDest = currentLine.substring(2, 3); //get the destination
                int newDest = Integer.parseInt(strDest); //parse the destination into the int
                Edge edge = new Edge(newDest); //create a new node passing it the element (destination)
                edge.setSource(currVert); //assign that node's source (the index of the list that the node is apart of) to the index (the vertex in this case is the source)
                if(graph.getWeighted()) { //if the graph is weighted
                    String thisWeight = currentLine.substring(4); //get the weight
                    weight = Double.parseDouble(thisWeight); //parse it into a double
                    edge.setWeight(weight); //set the weight of the graph
                }
                if (adjLists != null){ //if the adjacency list is not null
                    adjLists[currVert].addFirst(edge); //add the node to the list at the index that it's at
                }
                if(graph.getDirected()) {
                    int tempVert = currVert;
                    currVert = newDest;
                    newDest = tempVert;
                    Edge newEdge = new Edge(newDest);
                    newEdge.setSource(currVert);
                    newEdge.setWeight(weight);
                    adjLists[currVert].addFirst(edge);
                }
                graph.setList(adjLists); //set the adjacency list to the one we created for bfs/dfs
                graph.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
            }
        }catch(IOException exception) { //file exception if file cant be found
            System.out.println("File not found");
        }
        System.out.println("-------Printing AdjList------"); //print the list
        System.out.println(graph.toString());
        System.out.println("-------BFS-------");
        graph.bfs( 0); //call bfs
        System.out.println();
        System.out.println("-------DFS-----");
        graph.dfs(0); //call dfs
        System.out.println();
        graph.kruskalMST();
    }
}