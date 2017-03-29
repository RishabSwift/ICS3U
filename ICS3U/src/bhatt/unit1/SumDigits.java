package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumDigits {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Please enter a number: ");
		int number = Integer.parseInt(in.readLine());
		
		System.out.println("The sum of the digits in " + number + " is " + getSumOfDigits(number));
		

	}

	/**
	 * Get the total sum of digits
	 * @param number
	 * @return
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
