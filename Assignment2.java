package Lab1;

import java.util.Scanner;

public class Assignment2 {
	
	private static char[] chars;
	private static char[] stack = new char[10];
	private static int N = 0;
	
	public static void main(String[] args) {
		Assignment2 s = new Assignment2();
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
	
	public void resetStack() {
		N = 0;
		for(int i = 0; i < chars.length; i++) {
			push(chars[i]);
		}
	}
	
	public void push(char c) {
		if(isFull()) changeStackSize(2);
		stack[++N] = c;
	}
	
	public char pop() {
		if(!isEmpty()) {
			if(N <= stack.length / 4) changeStackSize(0.5); 
			char copy = stack[N];
			stack[N--] = 0;
			return copy;
		}
		else return 0;
		
	}
	
	public boolean isEmpty() {
		if(N == 0) return true;
		else return false;
	}
	
	public boolean isFull() {
		if(N == stack.length-1) return true;
		else return false;
	}
	
	public void changeStackSize(double modifier) {
		char[] copy = new char[(int)(stack.length*modifier)];
		for(int i = 0; i < stack.length; i++) {
			if(i >= copy.length) break;
			copy[i] = stack[i];
		}
		stack = copy;
	}
	
	public void recursiveInverse() {
		char c = pop();
		System.out.print(c);
		if(N != 0) recursiveInverse();
	}
	
	public void iterativeInverse() {
		for(char c : stack) {
			c = pop();
			System.out.print(c);
		}
	}

}
