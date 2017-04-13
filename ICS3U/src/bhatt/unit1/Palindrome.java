package bhatt.unit1;

import java.util.Scanner;

/**
 * Palindrome.java
 * This program checks if a word is a palindrome
 * April 13, 2017
 *
 * @author Rishab Bhatt
 */
public class Palindrome {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String s = scan.nextLine();

        System.out.println(isPalindrome(s) ? "TRUE" : "FALSE");
    }


    /**
     * Check if a string is a palindrome
     *
     * @param word word to check if it's a palindrome
     * @return true if palindrome or false if not
     */
    private static boolean isPalindrome(String word) {

        int lengthOfString = word.length();

        for (int i = 0; i < (lengthOfString / 2); i++) {
            // check the first letter against the last
            // check the second letter against the second last
            // ... and so on
            if (word.charAt(i) != word.charAt(lengthOfString - i - 1)) {
                return false;
            }
        }

        return true;
    }

}
