package Lab1;

public class Assignment6 {

	Node head; // Initializes a node to be the head of the list (initially null)
	Node tail;

	// The class of a node (Attributes: stored data, previous node, next node)
	public class Node {
		int data;
		Node prev;
		Node next;

		Node(int d) { // Constructor of the node
			this.data = d;
		}
	}

	// Queues a new node at the end of the list
	public void queue(int newData) {
		Node newNode = new Node(newData); // Initializes a new node with the inputed data
		newNode.next = null; // Next node is null because the new node ends up being the tail
		if (head == null) { // If there is no head yet, the list is empty. Make the new node as the head of
							// the list and return
			newNode.prev = newNode;
			head = newNode;
			printQueue();
			return;
		}

		Node current = head; // Instantiated node to cycle through the list

		while (current.next != null) { // A loop to get to the end of the the list (the tail)
			current = current.next;
		}

		current.next = newNode; // When the current node is at the end, its next node will be the new one
		newNode.prev = current; // Link the previous node of the new node to be the instantiated one (which
								// equals the previous tail)
		tail = newNode;
		sortQueue();
		printQueue();

	}

	// Dequeues the node at the head of the list
	public void dequeue() {
		if (head.prev == null && head.next == null) { // If the only node in the list is the head, set everything equal
														// to null and return
			head = null;
			printQueue();
			return;
		}
		head = head.next; // The new head is the node after the previous head
		head.prev = null; // The previous head is set to null to be removed
		printQueue();
	}

	// Cycles through the nodes and prints their data
	public void printQueue() {
		Node current = head; // Instantiated node which will cycle through
		while (current != null) { // While the current instantiated node isn't null (meaning thats it's in the
									// list), increment through the list and print the data
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println("\n");
	}

	public void sortQueue() {
		Node iterator = head;
		if (head.next == tail) {
			if (tail.data < head.data) {
				int dataCopy = head.data;
				head.data = tail.data;
				tail.data = dataCopy;
			}
		} else {
			while (iterator.next != null) {
				if (tail.data > iterator.data && tail.data < iterator.next.data) {
					if (iterator.next.next == null) {
						int dataCopy = iterator.data;
						iterator.data = tail.data;
						tail.data = dataCopy;
					} else {
						Node copy = iterator.next;
						iterator.next = tail;
						tail.next = copy;
					}
				}
				iterator = iterator.next;
			}
		}
	}

	// Main function where everything is called
	public static void main(String[] args) {
		Assignment6 list = new Assignment6();
		list.queue(56);
		list.queue(18);
		list.queue(20);
		list.queue(32);
		list.queue(97);
		list.queue(40);
	}

}
