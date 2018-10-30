package cs335;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("./src/CS335/test.txt"); //create file
        int verticies = 0; //number of verticies

        Graph graph = new Graph(); //create a graph
        try {
            Scanner scan = new Scanner(file); //scan the file

            String temp = scan.nextLine(); //grab the first line
            String allVert = temp.substring(0,1); //grab the number of verticies of the graph, file(0)
            verticies = Integer.parseInt(allVert); //parse it into an int and set it to the variable verticies
            graph.setVerticies(verticies); //set the verticies of the graph
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

            LinkedList<Node>[] adjLists = new LinkedList[verticies]; //create the adjacency list, array of linked list of nodes

            for (int i = 0; i < verticies; i++) {
                adjLists[i] = new LinkedList<Node>(); //instantiate an empty list at each index of the adjacency list
            }

            while (scan.hasNextLine()) { // while the file has a next line in the file
                String currentLine = scan.nextLine(); //grab the line
                String strVert = currentLine.substring(0, 1); //get the vertex
                int currVert = Integer.parseInt(strVert); //parse the vertex into an int
                String strDest = currentLine.substring(2, 3); //get the destination
                int newDest = Integer.parseInt(strDest); //parse the destination into the int
                Node node = new Node(newDest); //create a new node passing it the element (destination)
                node.setSrc(currVert); //assign that node's source (the index of the list that the node is apart of) to the index (the vertex in this case is the source)
                if(graph.getWeighted()) { //if the graph is weighted
                    String thisWeight = currentLine.substring(4); //get the weight
                    double weight = Double.parseDouble(thisWeight); //parse it into a double
                    node.setWeight(weight); //set the weight of the graph
                }
                if (adjLists != null){ //if the adjacency list is not null
                    adjLists[currVert].addFirst(node); //add the node to the list at the index that it's at
                }
                graph.setList(adjLists); //set the adjacency list to the one we created
            }
        }catch(IOException exception) { //file exception if file cant be found
            System.out.println("File not found");
        }
        System.out.println("-------Printing AdjList------"); //print the list
        System.out.println(graph.toString());
        System.out.println("-------BFS-------");
        graph.bfs(0); //call bfs
        System.out.println();
        System.out.println("-------DFS-----");
        graph.dfs(0); //call dfs

    } //end main

}
