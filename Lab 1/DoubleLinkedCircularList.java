package Lab1;

public class DoubleLinkedCircularList {

	Node head; //Initializes a node to be the head of the list (initially null)
	Node tail; //Initializes a node to be the tail of the list (initially null)
	int size = 0;
	
	//The class of a node (Attributes: stored data, previous node, next node)
	public class Node { 
		String data;
		Node prev;
		Node next;

		Node(String d) { //Constructor of the node
			this.data = d;
		}
	}

	//Queues a new node at the end of the list
	public void queue(String newData) {
		Node newNode = new Node(newData); //Initializes a new node with the inputed data
		newNode.next = null; //Next node is null because the new node ends up being the tail
		if (head == null) { //If there is no head yet, the list is empty. Make the new node as the head of the list and return
			newNode.prev = tail;
			head = newNode;
			head.next = tail;
			tail = head; //When the head is the only node, it is also the tail which loops back to itself
			tail.prev = head;
			tail.next = head;
			size++;
			printQueue();
			return;
		}

		Node current = head;
		current = tail; //Set the current node to the end
		tail = newNode; //When the current node is at the end, its next node will be the new one
		tail.prev = current; //Link the previous node of the new node to be the instantiated one (which equals the previous tail)
		tail.next = head; //Link the tail to the head
		current.next = tail; //Link the tail to the link before
		size++;
		
		printQueue();

	}

	//Dequeues the node at the head of the list
	public void dequeue() {
		if(head.prev == null && head.next == null) { //If the only node in the list is the head, set everything equal to null and return
			head = null;
			printQueue();
			return;
		}
		head = head.next; //The new head is the node after the previous head
		head.prev = tail; //The previous node to the head is set to tail
		size--;
		printQueue();
	}

	//Cycles through the nodes and prints their data
	public void printQueue() {
		Node current = head; //Instantiated node which will cycle through
		for(int i = 0; i < size; i++) { //While the current instantiated node isn't null (meaning thats it's in the list), increment through the list and print the data
			System.out.print(current.data + " ");
			current = current.next;
		}
		System.out.println("\n");
	}

	//Main function where everything is called
	public static void main(String[] args) {
		DoubleLinkedCircularList list = new DoubleLinkedCircularList();
		list.queue("THIS");
		list.queue("IS");
		list.queue("A");
		list.dequeue();
		list.queue("PHRASE");
		list.dequeue();

	}

}
