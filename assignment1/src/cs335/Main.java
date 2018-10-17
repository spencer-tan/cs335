package cs335;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.sql.Array;
import java.sql.SQLOutput;
import java.io.File;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.*;

public class Main extends Graph{
    public static void main(String[] args) {
        File file = new File("./src/CS335/test.txt");
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Graph graph = new Graph(file);
        try {
            Scanner console = new Scanner(graph.getFile());
                String line = console.nextLine(); //grab the line and put it into a string
                System.out.println("Line: " + line);
                String strVerticies = line.substring(0,1); //split the string and grab token 0
                int verticies = Integer.parseInt(strVerticies); //assign the number of vertices to token 0
                graph.setVerticies(verticies); //set the number of vertices to the parsed number of vertices

                String strEdges = line.substring(2,3); //split the string and grab token 2
                int edges = Integer.parseInt(strEdges); //assign the number of edges to token 2
                graph.setEdges(edges); //set the number of edges to the parsed number of edges

                String strDirected = line.substring(4,5); //split the string and grab token 6
                int direction = Integer.parseInt(strDirected); //assign 0 or 1 to the show directed or not
                boolean direct = false;
                if(direction == 1) {
                    direct = true;
                }
                graph.setDirected(direct); //set the boolean directed true if its directed false if its not directed

                String strWeight = line.substring(6); //split the string and grab token 4
                int weighted = Integer.parseInt(strWeight); //assign 0 or 1 to the weight
                boolean weight = false;
                if(weighted == 1) {
                    weight = true;
                }
                graph.setWeighted(weight); //set the boolean weighted true if its weighted false if its not weighted
            System.out.println(graph.getWeighted());
            System.out.println(graph.getDirected());
        }
        catch (IOException exception) {
            System.out.println("File not found");
        }

        ArrayList<Double> allWeights = new ArrayList<>();
        if(graph.getWeighted()) {
           allWeights = getWeightList(graph);
        }
        Point[] finalCord = getPointers(graph);
        SLList[] adjacentArray = makeList(finalCord);
        for(int i = 0; i < adjacentArray.length; i++) {
            System.out.println("Final: " + adjacentArray[i]);
        }



    }

    public static ArrayList<Double> getWeightList(Graph graph) {
        if(graph.getWeighted() == true) { //if the graph is weighted
            ArrayList<Double> weights = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(graph.getFile()); //create a scanner to scan the file
                String temp = scanner.nextLine(); //grab the first line and throw it away
                while(scanner.hasNextLine()) { //while there is a next line in the scanner (starting at line 2 because we threw the first one away
                    String start = scanner.nextLine(); //grab the starting line, line 2
                    double currWeight = Double.parseDouble(start.substring(4)); //grab the weight on the line
                    weights.add(currWeight); //add the weight on each line to the arraylist of weights
                }
                for(int j = 0; j < weights.size(); j++){
                    System.out.println(weights.get(j));
                }
                return weights; //return double array of weights
            } catch (IOException exception) {
                System.out.println("File not found");
            }
        }
        return null;
    }

    public static Point[] getPointers(Graph graph) {
        Point[] pointers = new Point[graph.getEdges()];
        try {
            Scanner scan = new Scanner(graph.getFile());
            String temp = scan.nextLine();
            int i = 0;
            while(scan.hasNextLine()) {
                String start = scan.nextLine();
                int currX = Integer.parseInt(start.substring(0,1));
                //System.out.println("Current X: " + currX);
                int currY = Integer.parseInt(start.substring(2,3));
                //System.out.println("Current Y: " + currY);
                Point currentPointer = new Point(currX, currY);
                pointers[i] = currentPointer;
                i++;
            }

        } catch (IOException exeception) {
            System.out.println("File not found");
        }
        return pointers;
    }

    public static SLList[] makeList(Point[] pointers) {
        SLList[] adjList = new SLList[pointers.length];
        for(int i = 0; i < adjList.length -1; i++) {
                int indexI = (int)pointers[i].getX();
                SLList temp = new SLList();
                temp.insert(indexI, pointers[i].getY());
                System.out.println("Get Y: " + pointers[i].getY());
                adjList[indexI] = temp;
        }
        return adjList;
    }


}
