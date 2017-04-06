package bhatt.unit1;

import java.util.Scanner;

/**
 * ReverseWords.java
 * Reverse given words or string
 * <p>
 * April 6, 2017
 *
 * @author Rishab Bhatt
 */
public class ReverseWords {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a sentence: ");
        String sentence = scan.nextLine();

        for (int i = sentence.length() - 1; i >= 1; i--) {
            System.out.print(sentence.charAt(i));
        }


    }
}
