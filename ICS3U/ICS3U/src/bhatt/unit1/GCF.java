package bhatt.unit1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * GCF.java
 * Calculate the greatest common factor
 * Mar 29, 2017
 *
 * @author Rishab Bhatt
 */
public class GCF {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the first number: ");
        int num1 = Integer.parseInt(in.readLine());

        System.out.print("Please enter the second number: ");
        int num2 = Integer.parseInt(in.readLine());

        System.out.println("The Greatest Common Denominator is " + calculateGcfUsingLoop(num1, num2));
    }


    /**
     * Calculate the greatest common factor without using recursion..
     *
     * @param num1 The first number
     * @param num2 The second number
     * @return The GCF of two numbers
     */
    private static int calculateGcfUsingLoop(int num1, int num2) {

        // Only true if num1 is not 0
        while (num1 != 0) {
            if (num1 >= num2)
                num1 -= num2;
            else
                num2 -= num1;

        }

        return num1 == 0 ? num2 : num1;
    }

    /**
     * Calculate the Greatest Common Factor Keep doing it until the second
     * number is 0
     * <p>
     * Recursion is my friend
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int calculateGCF(int num1, int num2) {

        // If the second number is a 0..
        if (num2 == 0)
            return num1;

        // Let's do recursion to do the same thing.. Much cleaner and faster
        // We do a recursive call until number 2 is 0 which means we have found the GCD

        return calculateGCF(num2, num1 % num2);
    }


}
