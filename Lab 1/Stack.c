#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char pop();
void push(char c);
int isEmpty();
int isFull();
void doubleArraySize();
void halveArraySize();
void recursiveInverse();
void iterativeInverse();
void resetStack(char input[]);

int size = 5; //Size of the stack (changes frequently with pops and pushes)
char * chars; //Declaration of stack heap
int N = 0; //Index at which the current top stack value is located

//FUNCTION: Pops the last element from the stack and returns it
char pop() { 
	if(isEmpty()) return 0; // Checks if stack is empty. If it is, return.
	if(N <= size / 4 && size > 10) halveArraySize(); //Checks if stack is a quarter filled. If it is, halve its size.
	char copy = chars[N];
	chars[N--] = 0; //Sets the current index to be null (0) before popping and decrementing
	return copy;
}

//FUNCTION: Inserts (pushes) a character to the end of the stack
void push(char c) {
	if(isFull()) doubleArraySize(); //Checks if array is full. If it is, double its size.
	chars[++N] = c; //Increments index and sets the element to be the new character
}

///FUNCTION: Doubles the size of the array used for the stack
void doubleArraySize() {
	size *= 2;
	char * copy = realloc(chars, size * sizeof(char));
	chars = copy;
}

//FUNCTION: Halves the size of the array used for the stack
void halveArraySize() {
	size /= 2;
	char * copy = realloc(chars, size * sizeof(char));
	chars = copy;
}

//FUNCTION: Checks to see if the stack is empty or not
int isEmpty() {
	if(N == 0) return 1;
	else return 0;
}

//FUNCTION: Checks to see if the stack is full or not
int isFull() {
	if(N == size) return 1;
	else return 0;
}

//FUNCTION: Inverts the characters using recursion
void recursiveInverse() {
	char c = pop();
	putchar(c);
	if(c != 0) recursiveInverse(); //As long as the current character doesn't equal 0, the recursion of popping continues
}

//FUNCTION: Inverts the characters using iteration
void iterativeInverse() {
	int i;
	while(N != 0) { //For loop that cycles through the stack, popping each element
		char c = pop();
		putchar(c);
	}
}

//FUNCTION: Pushes a set of characters back into the stack
void resetStack(char * input) {
	int i;
	int indices = 0; //Indices to loop through to push all the characters into the stack
	while(input[indices] != 0) indices++; //For some reason sizeof() wasn't providing the right value, so the indices are manually counted
	for(i = 0; i < indices-1; i++) {
		if(input[i] == 0) break; //If the current index is empty, stop the loop to save resources
		push(input[i]); //Push every index of the char array into the stack
	}
}

int main() {
	chars = malloc(size * sizeof(char)); //Use a heap for the stack because it is dynamic memory and can be modified easier
	char input[100]; //Use array of 100 chars because C can't take Strings and modify their length or something
	printf("Enter characters (MAX 100): ");
	fgets(input, sizeof(input), stdin); //Read the value the user enters into "input"
	printf("You Entered: ");
	puts(input);
	
	printf("\n\n\nRECURSIVE STARTING... \n");
	resetStack(input); //Re-pushes all the values back into the stack (first one is initializing the stack values)
	recursiveInverse(); 
	
	printf("\n\n\nITERATIVE STARTING... \n");
	resetStack(input);
	iterativeInverse();

	free(chars); //Deallocate memory that is used for the stack (to prevent memory leak)
	
	return 0;
}