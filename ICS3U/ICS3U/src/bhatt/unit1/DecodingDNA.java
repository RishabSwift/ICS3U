package bhatt.unit1;

import java.util.Scanner;

public class DecodingDNA {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String dna = scan.nextLine();

        StringBuilder reverseDNA = new StringBuilder(dna);
        reverseDNA.reverse();

        // System.out.println(reverseDNA);

    }
}
