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
        String words[] = sentence.split(" ");

        // Loop through each word
        for (String word : words) {

            String endOfWord = "";

            // If you don't want to use regex.. this is simpler
            if (word.contains(".") || word.contains("!") || word.contains("?")) {
//            if (word.matches("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)")) {
                String punctuation = Character.toString(word.charAt(word.length() - 1));
                word = word.replace(punctuation, "");
                endOfWord += punctuation;
            }

            // If the word has a upper case or something... 
            if (Character.isUpperCase(word.charAt(0))) {
                // Set the first letter to lower case
                char firstLetter = Character.toLowerCase(word.charAt(0));

                // Set the last letter to upper case
                char lastLetter = Character.toUpperCase((word.charAt(word.length() - 1)));
                word = word.substring(1, word.length() - 1);
                word = firstLetter + word + lastLetter;

            }

            // Loop through each letter in the word
            for (int j = word.length() - 1; j >= 0; j--) {
                System.out.print(word.charAt(j));
            }

            // Put any punctuations at the end of the word
            System.out.print(endOfWord);

            // Don't add the trailing space after the last word
            System.out.print(" ");

        }

    }
}
