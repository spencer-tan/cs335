package cs335;

public class Node<E> {
	private E element;
	private Node<E> next;

	public Node(E dataItem) {
		element = dataItem;
		next = null;
	}

	public Node(E dataItem, Node<E> nodeRef) {
		element = dataItem;
		next = nodeRef;
	}

	public Node<E> setNext(Node<E> nodeRef) {
		next = nodeRef;
		return next;
	}

	public E setElement(E dataItem) {
		element = dataItem;
		return element;
	}

	public E getElement() {
		return element;
	}

	public Node<E> getNext() {
		// TODO Auto-generated method stub
		return next;
	}
}
