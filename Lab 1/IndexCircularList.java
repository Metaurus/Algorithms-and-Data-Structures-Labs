package Lab1;

public class IndexCircularList {

	Node head; //Initializes a node to be the head of the list (initially null)
	Node tail; //Initializes a node to the the tail of the list (initially null)
	int size = 0;
	
	//The class of a node (Attributes: stored data, previous node, next node, and index)
	public class Node { 
		String data;
		int index;
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
			newNode.prev = null;
			head = newNode;
			head.next = tail;
			tail = head; //When the head is the only node, it is also the tail which loops back to itself
			tail.prev = head;
			tail.next = head;
			head.index = 1;
			size++;
			printQueue();
			return;
		}

		Node current = head; //Instantiated node to cycle through the list

		if(current.next == tail) current.index++;
		
		for(int i = 1; i < size; i++) { //A loop to get to the end of the the list (the tail)
			current.index++;
			current = current.next;
		}

		newNode.index = 1;
		current.next = newNode; //When the current node is at the end, its next node will be the new one
		newNode.prev = current; //Link the previous node of the new node to be the instantiated one (the previous tail)
		newNode.next = head;
		tail = newNode;
		if(newNode.prev != head) newNode.prev.index++; //For some reason the tail of the list did not increment after queue. This fixes that
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
		newNode.index = head.index + 1;
		head = newNode; //New node becomes the head
		head.next = currentHead; //Head's next node becomes previous head
		size++;
		printQueue();
	}
	
	//Dequeues the node at the end of the list
	public void dequeueFromBack() {
		if (head == null) { //If there is no head (meaning the list is empty), don't do anything
			printQueue();
			return;
		}
		
		Node current = head; //Instantiated node to cycle through list
		for(int i = 1; i < size; i++) { //Loop to get instantiated node to the end of the list
			current = current.next;
			current.prev.index--;
		}
		
		if(current.prev == tail && current.next == tail) { //If there is only one node left, set it equal to null
			head = null;
			printQueue();
			return;
		}
		
		current.prev.next = head; //The previous node's next node is removed
		tail = current.prev;
		size--;
		printQueue();
	}
	
	//Cycles through the nodes and prints their data
	public void printQueue() {
		Node current = head; //Instantiated node which will cycle through
		for(int i = 0; i < size; i++) { //While the current instantiated node isn't null (meaning thats it's in the list), increment through the list and print the data
			System.out.print(current.data + "[" +current.index+ "] ");
			current = current.next;
		}
		if(head == null) System.out.println("THE QUEUE IS EMPTY!");
		System.out.println("\n");
	}

	//Removes a node at a certain index
	public void removeAtIndex(int index) {
		Node current = head;
		while(current.index != index) { //Loop through until proper index reached
			current.index--;
			current = current.next;
		}
		if(index == 1 && current == head) { //If the only node is the head, set it equal to null and reset the indices
			head.index = 0;
			head = null;
		}
		else if(index != 1 && current == head) { //If the head is chosen but is not the only node, set the head to be the next node
			head = current.next;
		}
		else if(index == 1 && current.next == head) { //If the tail is chosen, set the tail equal to the previous node
			tail = tail.prev;
		}
		else { //Otherwise, change the next and previous references to remove the middle node which is the chosen index
			Node copy = current;
			current.prev.next = current.next;
			current.next.prev = copy.prev;
		}

		size--;
		printQueue();
	}
	
	//Main function where everything is called
	public static void main(String[] args) {
		IndexCircularList list = new IndexCircularList();
		list.queue("THIS");
		list.queue("IS");
		list.queueAtFront("WOW!");
		list.queue("A");
		list.dequeue();
		list.queue("PHRASE");
		list.dequeueFromBack();
		list.removeAtIndex(3);
		list.dequeueFromBack();
		list.removeAtIndex(1);
	}

}
