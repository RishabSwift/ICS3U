package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * GCD.java
 * Calcualte the greatest common denominator
 * Mar 29, 2017
 * @author Rishab Bhatt
 *
 */
public class GCD {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Please enter the first number: ");
		int num1 = Integer.parseInt(in.readLine());

		System.out.print("Please enter the second number: ");
		int num2 = Integer.parseInt(in.readLine());

		System.out.println("The Greatest Common Denominator is " + calculateGCD(num1, num2));

	}

	/**
	 * Calculate the Greatest Common Denominator
	 * Keep doing it until the second number is 0
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int calculateGCD(int num1, int num2) {
		// If the second number is a 0..
		if (num2 == 0)
			return num1;
		
		// Let's do recursion to do the same thing.. Much cleaner and faster
		// We do a recursive call until number 2 is 0 which means we have found the GCD
		return calculateGCD(num2, num1 % num2);
	}

}
