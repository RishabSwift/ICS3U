package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * HighestNumber.java
 *
 * This program finds out the highest number given an array of numbers.
 * 
 * March 29, 2017
 * @author Rishab Bhatt
 *
 */
public class HighestNumber {

	public static void main(String[] args) throws NumberFormatException, IOException {

		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("How many numbers do you want to enter?");

		int maxNum = Integer.parseInt(in.readLine());

        int numbers[] = new int[maxNum];

		System.out.println("Start entering " + maxNum + " numbers");

		// Get user input
		for (int i = 0; i < maxNum; i++) {
			numbers[i] = Integer.parseInt(in.readLine());
		}

		System.out.println("The max number from those numbers is " + getMaxNumber(numbers));

	}

	/**
	 * Get the maximum number from an array of numbers
	 * 
	 * @param num The array containing the numbers
	 * @return The maximum number from the num array
	 */
	public static int getMaxNumber(int[] num) {
		int maxNum = num[0];

		for (int i = 0; i < num.length; i++) {
			// Check if the current number is higher than the max number
			maxNum = Math.max(maxNum, num[i]);
		}

		return maxNum;
	}

}
