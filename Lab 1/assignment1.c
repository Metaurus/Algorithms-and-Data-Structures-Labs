#include <stdio.h>
#include <string.h>

char pop();
void push(char c);
int isEmpty();
int isFull();
void doubleArraySize();
void halveArraySize();
void recursiveInverse();
void iterativeInverse();

int size = 5;
char chars[5];
int N = 0;

//FUNCTION: Pops the last element from the stack and returns it
char pop() { 
	if(isEmpty()) return 0; // Checks if stack is empty. If it is, return.
	if(N <= size / 4) halveArraySize(); //Checks if stack is a quarter filled. If it is, halve its size.
	char copy = chars[N];
	chars[N--] = 0; //Sets the current index to be null (0) before popping and decrementing
	return copy;
}

//FUNCTION: Inserts (pushes) a character to the end of the stack
void push(char c) {
	if(isFull()) doubleArraySize(); //Checks if array is full. If it is, double its size.
	chars[++N] = c; //Increments index and sets the element to be the new character
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

//FUNCTION: Doubles the size of the array used for the stack
void doubleArraySize() {
	size *= 2;
	char copy[size];
	int i;
	for(i = 0; i < size / 2; i++) copy[i] = chars[i]; //Copies current array to a new one with doubled size
	memcpy(chars, copy, size); //Overwrites current array with copy
}

//FUNCTION: Halves the size of the array used for the stack
void halveArraySize() {
	printf("\nI HALVED!");
	size /= 2;
	char copy[size];
	int i;
	for(i = 0; i < size; i++) copy[i] = chars[i]; //Copies current array to a new one with halved size
	memcpy(chars, copy, size); //Overwrites current array with copy
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
	for(i = 0; i < size; i++) { //For loop that cycles through the stack, popping each element
		char c = pop();
		putchar(c);
	}
}

int main() {
	int i;
	char c[100];
	printf("ENTER CHARACTERS: \n");
	fgets(c, 100, stdin);
	for(i = 0; i < sizeof(c); i++) {
		if(c[i]!='\n') {
			push(c[i]);
			putchar(c[i]);
		}
	}
	printf("THESE ARE THE ORIGINAL CHARACTERS: ");
	printf("\n\n\nRECURSIVE STARTING...\n");
	recursiveInverse();
	for(i = 0; i < sizeof(c); i++) {
		if(c[i]!='\n') {
			push(c[i]);
			putchar(c[i]);
		}
	}
	printf("\n\n\nITERATIVE STARTING...\n");
	iterativeInverse();
	printf("%d", size);
}