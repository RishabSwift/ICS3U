package bhatt.unit1;

/*
 * PerfectIntegers.java
 * Find perfect integers up to N
 * 4 19, 2017
 *
 * @author Rishab Bhatt
 */

public class PerfectIntegers {

    public static void main(String[] args) {

        int lastNumber = 5000;

        // Loop up to the last number
        for (int i = 2; i < lastNumber; i++) {
            int sum = 0;
            for (int j = 1; j < i; j++) {
                // Check if number (i) is divisible by j, if so, it's one of the factors
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum == i) {
                System.out.println(i);
            }
        }
    }
}
