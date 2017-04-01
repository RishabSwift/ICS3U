package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * FindPrime.java
 * This program checks if a number is a prime number
 * Mar 29, 2017
 *
 * @author Rishab Bhatt
 */
public class FindPrime {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter a number: ");
        int number = Integer.parseInt(in.readLine());

        boolean isNumberPrime = isPrime(number);

        System.out.println("The number is " + (isNumberPrime ? "prime" : "not prime"));

    }

    /**
     * Check if a number is prime
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        // If number is 0 or 1 obviously not prime
        if (num <= 1)
            return false;

        // If number is greater than 2 and it's a multiple of 2 not prime
        if (num > 2 && num % 2 == 0) {
            return false;
        }

        // We only need to check up to the square root of the number because math rocks
        int top = (int) Math.sqrt(num) + 1;
        for (int i = 3; i < top; i += 2) {
            if (num % i == 0) {
                return false;

            }
        }

        // The number is prime
        return true;
    }


}
