package Lab1;

import java.util.Scanner;

public class ExtraAssignment {
	
	public static void main(String[] args) {
		Assignment2 stack = new Assignment2(); //Create a new object of stack assignment
		Scanner sc = new Scanner(System.in);	//Create a scanner object for input
		System.out.println("ENTER CHARACTERS: ");	//Enter the sequence of characters (as a string)
		String input = sc.nextLine();	//Store the input as a string
		boolean balanced = true; //Boolean variable which determines if the brackets are balanced or not
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(' || input.charAt(i) == '{' || input.charAt(i) == '[') {
				System.out.println("I PUSHED AN OPENENING BRACKET");
				stack.push(input.charAt(i));
			}
			if(input.charAt(i) == ')' || input.charAt(i) == '}' || input.charAt(i) == ']') {
				System.out.println("I POPPED A CLOSING BRACKET");
				char poppedBracket = stack.pop();
				if(input.charAt(i) == ')' && poppedBracket != '(') balanced = false;
				else if(input.charAt(i) == '}' && poppedBracket != '{') balanced = false;
				else if(input.charAt(i) == ']' && poppedBracket != '[') balanced = false;
			}
		}
		
		System.out.print("THE BRACKETS ARE ");
		if(balanced) System.out.print("BALANCED!\n");
		else if(!balanced) System.out.print("NOT BALANCED!\n");
	}

}
