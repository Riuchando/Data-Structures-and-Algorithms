package com.bishoy.chapter4;

// postfix.java
// parses postfix arithmetic expressions
// to run this program: C>java PostfixApp
import java.io.*; // for I/O

class ParsePost {
	private StackX<Integer> theStack;
	private String input;

//--------------------------------------------------------------
	public ParsePost(String s) {
		input = s;
	}

//--------------------------------------------------------------
	public int doParse() {
		theStack = new StackX<Integer>(20); // make new stack
		char ch;
		int j;
		int num1, num2, interAns;

		for (j = 0; j < input.length(); j++) // for each char,
		{
			ch = input.charAt(j); // read from input
			theStack.displayStack("" + ch + " "); // *diagnostic*
			if (ch >= '0' && ch <= '9') // if it's a number
				theStack.push((int) (ch - '0')); // push it
			else // it's an operator
			{
				num2 = theStack.pop(); // pop operands
				num1 = theStack.pop();
				switch (ch) // do arithmetic
				{
				case '+':
					interAns = num1 + num2;
					break;
				case '-':
					interAns = num1 - num2;
					break;
				case '*':
					interAns = num1 * num2;
					break;
				case '/':
					interAns = num1 / num2;
					break;
				default:
					interAns = 0;
				} // end switch
				theStack.push(interAns); // push result
			} // end else
		} // end for
		interAns = theStack.pop(); // get answer
		return interAns;
	} // end doParse()
} // end class ParsePost
////////////////////////////////////////////////////////////////

class PostfixApp {
	public static void main(String[] args) throws IOException {
		String input;
		int output;

		while (true) {
			System.out.print("Enter postfix: ");
			System.out.flush();
			input = getString(); // read a string from kbd
			if (input.equals("")) // quit if [Enter]
				break;
			// make a parser
			ParsePost aParser = new ParsePost(input);
			output = aParser.doParse(); // do the evaluation
			System.out.println("Evaluates to " + output);
		} // end while
	} // end main()
//--------------------------------------------------------------

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//--------------------------------------------------------------
} // end class PostfixApp
////////////////////////////////////////////////////////////////