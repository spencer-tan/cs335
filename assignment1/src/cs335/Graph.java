package cs335;

import java.io.File;

public class Graph extends SLList  {
    File file;
    int verticies;
    int edges;
    boolean weighted;
    boolean directed;


    public Graph() {

    }

    public Graph(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public int getVerticies() {
        return verticies;
    }

    public void setVerticies(int v) {
        verticies = v;
    }

    public int getEdges() {
        return edges;
    }

    public void setEdges(int e) {
        edges = e;
    }

    public boolean getWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weight) {
        weighted = weight;
    }

    public boolean getDirected() {
        return directed;
    }

    public void setDirected(boolean direct) {
        directed = direct;
    }

}
