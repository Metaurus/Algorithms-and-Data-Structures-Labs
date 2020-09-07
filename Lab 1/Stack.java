package Lab1;

import java.util.Scanner;

public class Stack {
	
	private static char[] chars;
	private static char[] stack = new char[10];
	private static int N = 0;
	
	public static void main(String[] args) {
		Stack s = new Stack();
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER CHARACTERS: ");
		String input = sc.nextLine();
		chars = input.toCharArray();
		s.resetStack();
		s.recursiveInverse();
		System.out.println("\n");
		s.resetStack();
		s.iterativeInverse();

	}
	
	//Re-pops all the values back into the stack
	public void resetStack() {
		N = 0;
		for(int i = 0; i < chars.length; i++) {
			push(chars[i]);
		}
	}
	
	//Pushes a character value into the stack
	public void push(char c) {
		if(isFull()) changeStackSize(2); //If the stack is full, increase its size by 2
		stack[++N] = c;
	}
	
	//Pops a character value out of the stack
	public char pop() {
		if(!isEmpty()) {
			if(N <= stack.length / 4) changeStackSize(0.5);  //If the stack size is down to 1/4, then halve the stack size
			char copy = stack[N];
			stack[N--] = 0;
			return copy;
		}
		else return 0;
		
	}
	
	//Checks if the stack is empty or not
	public boolean isEmpty() {
		if(N == 0) return true;
		else return false;
	}
	
	//Checks if the stack is full or not
	public boolean isFull() {
		if(N == stack.length-1) return true;
		else return false;
	}
	
	//Modifies the size of the stack using a parameter
	public void changeStackSize(double modifier) {
		char[] copy = new char[(int)(stack.length*modifier)];
		for(int i = 0; i < stack.length; i++) {
			if(i >= copy.length) break;
			copy[i] = stack[i];
		}
		stack = copy;
	}
	
	//Prints the inputed characters in reverse using recursion
	public void recursiveInverse() {
		char c = pop();
		System.out.print(c);
		if(N != 0) recursiveInverse();
	}
	
	//Prints the inputed characters in reverse using iteration
	public void iterativeInverse() {
		for(char c : stack) {
			c = pop();
			System.out.print(c);
		}
	}

}
