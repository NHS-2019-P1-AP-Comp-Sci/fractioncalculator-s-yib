/**
 * @author Mr. Rasmussen
 */
package fracCalc;

import java.util.Scanner;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner sc = new Scanner(System.in);
		String equation = "";
		System.out.println("Fraction Calculator\n");
		System.out.println("Fraction format: whole_numerator/denominator (Ex: 1_1/2)");
		System.out.println("Operators: +, -, *, /");
		System.out.println("\"quit\" to end program.\n");
		while (!equation.equals("quit")) {
			System.out.println("Values to calculate?");
			equation = sc.nextLine();
			if (!equation.equals("quit")) {
				System.out.println(produceAnswer(equation) + "\n");
			} else {
				System.out.println("Program ended.");
			}
		}
		sc.close();
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		String value1 = input.substring(0, input.indexOf(' '));
		String value2 = input.substring(input.indexOf(' ', input.indexOf(' ') + 1) + 1, input.length());
		String operator = input.substring(input.indexOf(' ') + 1, input.indexOf(' ') + 2);
		int value1Whole = returnWholeValue(value1);
		int value1Num = returnFracNum(value1);
		int value1Denom = returnFracDenom(value1);
		int value2Whole = returnWholeValue(value2);
		int value2Num = returnFracNum(value2);
		int value2Denom = returnFracDenom(value2);
		String result = "";
		if (operator.equals("+")) {
			int resultNum = ((value1Num + value1Whole * value1Denom) * value2Denom)
					+ ((value2Num + value2Whole * value2Denom) * value1Denom);
			int resultDenom = value1Denom * value2Denom;
			int resultWhole = resultNum / resultDenom;
			resultNum = resultNum % resultDenom;
			int factor = simplify(resultNum, resultDenom);
			resultNum /= factor;
			resultDenom /= factor;
			result = format(resultWhole, resultNum, resultDenom);
		} else if (operator.equals("-")) {
			int resultNum = ((value1Num + value1Whole * value1Denom) * value2Denom)
					- ((value2Num + value2Whole * value2Denom) * value1Denom);
			int resultDenom = value1Denom * value2Denom;
			int resultWhole = resultNum / resultDenom;
			resultNum = resultNum % resultDenom;
			int factor = simplify(resultNum, resultDenom);
			resultNum /= factor;
			resultDenom /= factor;
			result = format(resultWhole, resultNum, resultDenom);
		} else if (operator.equals("*")) {
			int resultDenom = value1Denom * value2Denom;
			int resultNum = (value1Whole * value1Denom + value1Num) * (value2Whole * value2Denom + value2Num);
			int resultWhole = resultNum / resultDenom;
			resultNum = resultNum % resultDenom;
			int factor = simplify(resultNum, resultDenom);
			resultNum /= factor;
			resultDenom /= factor;
			result = format(resultWhole, resultNum, resultDenom);
		} else if (operator.equals("/")) {
			int resultDenom = value1Denom * (value2Num + value2Whole * value2Denom);
			int resultNum = (value1Whole * value1Denom + value1Num) * (value2Denom);
			int resultWhole = resultNum / resultDenom;
			resultNum = resultNum % resultDenom;
			int factor = simplify(resultNum, resultDenom);
			resultNum /= factor;
			resultDenom /= factor;
			result = format(resultWhole, resultNum, resultDenom);
		}
		return result;
	}

	// takes in a string takeFrom and returns the whole value
	// or returns a default value if whole value does not exist
	public static int returnWholeValue(String takeFrom) {
		String value = "";
		if (takeFrom.indexOf("_") != -1) {
			value = takeFrom.substring(0, takeFrom.indexOf("_"));
		} else if (takeFrom.indexOf("/") == -1) {
			value = takeFrom;
		} else {
			value = "0";
		}
		return Integer.parseInt(value);
	}

	// takes in a string takeFrom and returns the numerator
	// or returns a default value if numerator does not exist
	public static int returnFracNum(String takeFrom) {
		String value = "";
		if (takeFrom.indexOf("/") != -1) {
			if (takeFrom.indexOf("_") == -1) {
				value = takeFrom.substring(0, takeFrom.indexOf("/"));
			} else {
				value = takeFrom.substring(takeFrom.indexOf("_") + 1, takeFrom.indexOf("/"));
			}
		} else {
			value = "0";
		}
		if (takeFrom.startsWith("-") && takeFrom.contains("_")) {
			return Integer.parseInt(value) * -1;
		} else {
			return Integer.parseInt(value);
		}
	}

	// takes in a string takeFrom and returns the denominator
	// or returns a default value if denominator does not exist
	public static int returnFracDenom(String takeFrom) {
		String value = "";
		if (takeFrom.indexOf("/") != -1) {
			value = takeFrom.substring(takeFrom.indexOf("/") + 1);
		} else {
			value = "1";
		}
		return Integer.parseInt(value);
	}

	// takes in an int num and an int denom to return
	// the greatest common factor of the two
	public static int simplify(int num, int denom) {
		if (denom == 0) {
			return num;
		}
		return simplify(denom, num % denom);
	}

	// takes in an int whole, an int num, and an int denom
	// and formats them properly to return them
	public static String format(int whole, int num, int denom) {
		String value = "";
		if (whole != 0) {
			value = "" + whole;
		} else if (whole == 0 && num == 0) {
			value = "" + whole;
		}
		if (whole != 0 && num != 0) {
			value = value + "_";
		}
		if (num != 0 && denom != 1) {
			if (whole < 0) {
				value = value + Math.abs(num) + "/" + Math.abs(denom);
			} else
				value = value + num + "/" + denom;
		}
		return value;
	}
}
