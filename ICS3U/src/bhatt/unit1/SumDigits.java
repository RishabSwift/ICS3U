package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SumDigits.java
 * Find the total sum of digits
 * Monday, April 3, 2017
 * @author rishabbhatt
 *
 */
public class SumDigits {

	
	/**
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Please enter a number: ");
		int number = Integer.parseInt(in.readLine());
		
		System.out.println("The sum of the digits in " + number + " is " + getSumOfDigits(number));
		

	}

	/**
	 * Get the total sum of digits
	 * @param number The given number to find the sum of digits
	 * @return The sum of digits
	 */
	public static int getSumOfDigits(int number) {
		int sum = 0;

		while (number > 0) {
			sum += number % 10;
			number /= 10;
		}

		return sum;
	}

}
