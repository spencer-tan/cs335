package cs335;

public class Node {
	int src; //index of the linked list that the node is at
	int dest; //element/node
	double weight; //weight of the node if graph is weighted


	/**
	 * Default Constructor
	 */
	public Node(){

	}

	/**
	 * Constructor to create a node with one element
	 * @param d the element to be inserted into the node
	 */
	public Node(int d) {
		dest = d;
	}

	/**
	 * Get the index of the list that the node is apart of
	 * @return integer for the index of the list that the node is in
	 */
	public int getV() {
		return src;
	}

	/**
	 * Set the index of the list
	 * @param i
	 */
	public void setSrc(int i) {
		src = i;
	}

	/**
	 * Set the weight of the node
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Get the element in the node
	 * @return
	 */
	public int getY(){
		return dest;
	}

	/**
	 * Get the weight of the node
	 * @return double the weight of the node
	 */
	public double getWeight() {
		return this.weight;
	}


	/**
	 * Print the contents of the node
	 * @return String of contents of the node
	 */
	public String toStringWeight() {
		if (weight == 0){
			return (Integer.toString(dest) + " ");
		} else {
			return (dest + "(" + (int)weight + ") ");
		}
	}

	public String toString() {
		return (Integer.toString(dest));
	}


}