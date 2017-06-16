package bhatt.unit1;

/**
 * PythagoreanTriple.java
 * This program finds teh Pythagorean Triple
 * April 19, 2017
 *
 * @author Rishab Bhatt
 */
public class PythagoreanTriple {

    public static void main(String[] args) {

        // A number to find the perfect square to
        int lastNumber = 100;

        for (int c = 1; c < lastNumber; c++) {
            // 1, 2, 3, 4, 5
            for (int b = 1; b < c; b++) {
                // 1: 1 • 2: 1, 2 • 3: 1, 2, 3 • 4: 1, 2, 3, 4 • 5: 1, 2, 3, 4, 5
                for (int a = 1; a < b; a++) {
                    // 1: 1 • 2: 1, 2
                    double aSquared = Math.pow(a, 2);
                    double bSquared = Math.pow(b, 2);
                    double cSquared = Math.pow(c, 2);

                    // only do math if cSquared is a perfect square
                    if (isPerfectSquare(cSquared)) {
                        if (aSquared + bSquared == cSquared) {
                            System.out.println("a = " + a + ", b = " + b + ", c = " + c);
                        }
                    }
                }
            }
        }
    }

    /**
     * Check if a number is a perfect square
     *
     * @param num A number to check against
     * @return boolean
     */
    private static boolean isPerfectSquare(double num) {
        int squareRoot = (int) Math.sqrt(num);
        return num == Math.pow(squareRoot, 2);
    }
}
