package bhatt.unit1;

/*
 * Mancala.java
 * Program Description
 * May 8, 2017
 *
 * @author Rishab Bhatt
 */


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Mancala {

    private int[] firstPlayerBoard;
    private int[] secondPlayerBoard;
    private int firstPlayerScore, secondPlayerScore;

    private int input = 0;
    private int currentPosition = 0;
    private boolean isFirstPlayer;
    private boolean gameOver = false;

    public static void main(String[] args) {

        Mancala game = new Mancala();
        game.reset();
        game.printBoard();
        game.getInput();
        game.moveStones();
    }

    public void play() {

        while (!gameOver) {

            moveStones();
        }
    }

    /**
     * Move current player's stones from one slot to another, depending on the input and the hole
     */
    public void moveStones() {
        int[] currentPlayerBlock = getCurrentPlayerBlock();
        int[] otherPlayerBlock = getOtherPlayerBlock();

        // Get the total number of stones in position
        int totalStonesInPosition = getStonesInHole(input);

        // Set the current stone count to 0
        updateHole(currentPlayerBlock, input, 0);

        // The current block being played
        int[] playingBlock = currentPlayerBlock;
        int current = input + 1;

        // Start spreading the stones in each slot
        while (totalStonesInPosition > 0) {

            totalStonesInPosition--;

            // Increase each slot with 1
            playingBlock[current]++;

            // If you are here...       |_ _ _ _ _ _ |  Player B
            //                          |             x|
            //                          |_ _ _ _ _ _|   Player A
            // ... we need to go to the other person's side if we scored in our bin
            if (current == 6) {
                playingBlock = getOtherPlayerBlock();
                current = 0;
            } else {
                current++;
            }
            System.out.println("Current: " + current);
        }

        updateScore();

        printBoard();

    }

    /**
     * Get user input
     */
    public void getInput() {

        System.out.printf("\nPlayer %s: ", isFirstPlayer ? "A" : "B");

        Scanner scan = new Scanner(System.in);
        String letter = scan.next().toUpperCase();

        // Convert the letters to array
        String[] letters = "A B C D E F".split(" ");

        // Check if the input they entered is valid
        while (!Arrays.asList(letters).contains(letter)) {

            System.out.println();
            System.out.print("You have entered an invalid letter. \nPlease enter a letter between A and F: ");
            letter = scan.next().toUpperCase();
            System.out.println();
        }

        // Check if they entered a value where there are stones in the hole
        if (getStonesInHole(letter) == 0) {
            System.out.println();
            System.out.print("You have 0 stones in this slot. Please choose another!");
            System.out.println();
            getInput();
        }

        // Set input to letter
        input = getCorrespondingPosition(letter);

        // If they are not the first player, we must reverse the input and set the current input as that
        if (!isFirstPlayer) {
            input = 7 - (input + 2);
        }

        // Reverse the player so it's the other player's turn
//        updateTurn();
    }


    /**
     * Reset the board
     */
    public void reset() {

        // Reset the board
        firstPlayerBoard = new int[]{4, 4, 4, 4, 4, 4, 0};
        secondPlayerBoard = new int[]{4, 4, 4, 4, 4, 4, 0};

        isFirstPlayer = true;

        // Set player score to 0
        updateScore();
    }


    /**
     * Return the block of the current player whose turn it is
     *
     * @return Current Player Block
     */
    public int[] getCurrentPlayerBlock() {
        return isFirstPlayer ? firstPlayerBoard : secondPlayerBoard;
    }

    /**
     * Return the block of the other player who does not have the current turn
     *
     * @return Other Player Block
     */
    public int[] getOtherPlayerBlock() {
        return isFirstPlayer ? secondPlayerBoard : firstPlayerBoard;
    }


    /**
     * Update the user's turn
     */
    public void updateUserTurn() {
        isFirstPlayer = !isFirstPlayer;
    }

    /**
     * Get corresponding position as an index for the letter
     * E.g. if they passed in C, it should return 2 because it's the third one over, minus 1
     *
     * @param letter The letter between A-F
     */
    public int getCorrespondingPosition(String letter) {

        String letters = "ABCDEF";
        currentPosition = letters.indexOf(letter.toUpperCase());
        return currentPosition;
    }

    /**
     * Get the number of stones in a given hole using the position
     * USE THE GIVEN BOARD
     *
     * @param board
     * @param position
     * @return
     */
    public int getStonesInHole(int[] board, int position) {
        return board[position];
    }

    /**
     * Get the number of stones in a given hole using the letter position
     * USE THE GIVEN BOARD
     *
     * @param board
     * @param position
     * @return
     */
    public int getStonesInHole(int[] board, String position) {
        return board[getCorrespondingPosition(position)];
    }

    /**
     * Get the number of stones in the current player's hole using the number position
     *
     * @param position
     * @return
     */
    public int getStonesInHole(int position) {
        int[] board = getCurrentPlayerBlock();
        return getStonesInHole(board, position);
    }

    /**
     * Get the number of stones in the current player's hole using the letter
     *
     * @param letter
     * @return
     */
    public int getStonesInHole(String letter) {
        int[] board = getCurrentPlayerBlock();
        return getStonesInHole(board, letter);
    }

    /**
     * Update user's total score based on how many stones they have in the well
     */
    public void updateScore() {
        firstPlayerScore = firstPlayerBoard[6];
        secondPlayerScore = secondPlayerBoard[6];
    }

    /**
     * Update a hole in a given index in a given array
     *
     * @param board     The player's board
     * @param index     The index to update
     * @param newNumber The new number to update old number with
     */
    public void updateHole(int[] board, int index, int newNumber) {
        board[index] = newNumber;
    }

//    /**
//     * Update a hole in a given board with the number of stone to add
//     *
//     * @param board       The player's board
//     * @param numberToAdd The number of stones to add to a specific board
//     */
//    public void updateHole(int[] board, int numberToAdd) {
//        for (int i = 0; i < board.length; i++) {
//            board[i] += numberToAdd;
//        }
//    }

    /**
     * Print the board with updated results
     */
    public void printBoard() {

        String padding = "  ";
        String firstPlayer = "";
        String secondPlayer = "";
        String footer = "A B C D E F".replaceAll(" ", padding);


        // Border Line
//        for (int i = 0; i <= footer.length() * 2; i++) {
//            System.out.print("-");
//        }
//
//        for (int i = 0; i <= footer.length() * 2; i++) {
//            if (i == footer.length() * 2 - 1) {
//                System.out.print("|");
//            }
//        }

        // Store the first players board in a string, going forwards and skipping the last "0"
        for (int i = secondPlayerBoard.length - 2; i >= 0; i--) {
            secondPlayer += secondPlayerBoard[i] + padding;
        }

        // Store the second players board in a string, going backwards and skipping the first "0"
        for (int i = 0; i < firstPlayerBoard.length - 1; i++) {
            firstPlayer += firstPlayerBoard[i] + padding;
        }


        // Print the second players board (on the top)
        System.out.println(padding + secondPlayer + "     Player B");

        // Print the second player score (on the left)
        System.out.print(secondPlayerScore + padding);

        // Print padding
        for (int i = 3; i <= footer.length(); i++) {
            System.out.print(" ");
        }

        // Print the second player score (on the right)
        System.out.println(padding + firstPlayerScore);

        // Print the first players board (on the bottom)
        System.out.println(padding + firstPlayer + "     Player A");

        // Print the lettering
        System.out.println(padding + footer);

        // Blank lines below
        System.out.println("\n\n\n");

    }

}
