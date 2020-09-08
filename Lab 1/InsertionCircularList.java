package Lab1;

public class InsertionCircularList {

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
		for(int i = 1; i < size; i++) current = current.next; //Loop through the list via a for loop to get to the node before the tail
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

	//Queues a new node at the front of the list
	public void queueAtFront(String newData) {
		Node newNode = new Node(newData); //New node with inputed value
		Node currentHead = head; //Copied head node to be used when reassigning head's next node
		
		newNode.prev = tail;
		head = newNode; //New node becomes the head
		head.next = currentHead; //Head's next node becomes previous head
		size++;
		printQueue();
	}
	
	public void dequeueFromBack() {
		if (head == null) { //If there is no head (meaning the list is empty), don't do anything
			printQueue();
			return;
		}
		
		Node current = head; //Instantiated node to cycle through list
		for(int i = 1; i < size; i++) { //Loop to get instantiated node to the end of the list
			current = current.next;
		}
		current.prev.next = head; //The previous node's next node is removed
		tail = current;
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
		InsertionCircularList list = new InsertionCircularList();
		list.queue("THIS");
		list.queue("IS");
		list.queueAtFront("WOW!");
		list.queue("A");
		list.dequeue();
		list.queue("PHRASE");
		list.dequeue();
		list.dequeueFromBack();
		list.dequeueFromBack();
	}

}
