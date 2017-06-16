package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Factorial.java
 * Calculate factorial given number
 * Mar 29, 2017
 *
 * @author Rishab Bhatt
 */
public class Factorial {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter a number: ");
        long number = Integer.parseInt(in.readLine());

        System.out.println(number + "! is " + calculateFactorial(number));

    }

    /**
     * Calculate factorial of a number
     *
     * @param num The number to find the factorial
     * @return The factorial of the given number
     */
    private static long calculateFactorial(long num) {

        long answer = 1;

        for (int i = 1; i <= num; i++) {
            answer *= i;
        }

        return answer;
    }

}
