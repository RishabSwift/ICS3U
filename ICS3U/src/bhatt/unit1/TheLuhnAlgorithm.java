package bhatt.unit1;

import java.io.BufferedReader;
import java.io.FileReader;

public class TheLuhnAlgorithm {

    public static void main(String[] args) {

        String fileName = "/Users/rishabbhatt/git/ICS3U/ICS3U/src/DATA1.txt";
        try {

            //Create object of FileReader
            FileReader inputFile = new FileReader(fileName);

            //Instantiate the BufferedReader Class
            BufferedReader bufferReader = new BufferedReader(inputFile);

            //Variable to hold the one line data
            String line;

            // Read file line by line and print on the console
            while ((line = bufferReader.readLine()) != null) {


                String oneLine[] = line.split(" ");

                for (String number : oneLine) {

                    int validNum = 0;
                    for (int j = 0; j < 10; j++) {

                        boolean valid = validate(number, String.valueOf(j));
                        if (valid) {
                            validNum = j;
                        }
                    }

                    System.out.print(validNum);

                }
                System.out.println();

            }

            //Close the buffer reader
            bufferReader.close();
        } catch (Exception e) {
            System.out.println("Error while reading file line by line:" + e.getMessage());
        }


//
//        Scanner scan = new Scanner(System.in);
//        String num = scan.next();
//
//        int validNum = 0;
//        for (int i = 0; i < 10; i++) {
//
////            System.out.println("---------- CHECKING CHECKDIGIT " + i + "-----------");
//            boolean valid = validate(num, String.valueOf(i));
//            if (valid) {
//                validNum = i;
////                System.out.println("!!!!!Valid number: " + validNum);
//            }
//        }
//
//        System.out.println(validNum);

    }


    private static boolean validate(String baseNumber, String checkDigit) {

        // combine them together
        String idNumber = baseNumber + checkDigit;

        // Every second digit
        boolean secondDigit = false;

        // 48305
        int sum = 0;

        // double every digit starting from the right
        for (int i = idNumber.length() - 1; i >= 0; i--) {


            int num = Integer.parseInt(idNumber.substring(i, i + 1));

            // If it's the second digit
            if (secondDigit) {
                int sumOfDigits = 0;

                int doubleOfDigits = num * 2;

                // if there are two digits
                if (doubleOfDigits > 9) {
                    String s = String.valueOf(doubleOfDigits);
                    sumOfDigits += Integer.parseInt(s.substring(0, 1)) + Integer.parseInt(s.substring(1));
                } else {
                    sumOfDigits = doubleOfDigits;
                }
                sum += sumOfDigits;
            } else {
                sum += num;
            }

            // reverse the second Digit
            secondDigit = !secondDigit;
        }

//        System.out.println("----------");
//        System.out.println("Sum: " + sum);
        return sum % 10 == 0;
    }


}
