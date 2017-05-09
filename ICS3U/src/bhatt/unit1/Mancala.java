package bhatt.unit1;

/*
 * Mancala.java
 * Program Description
 * May 8, 2017
 *
 * @author Rishab Bhatt
 */


import java.util.Arrays;
import java.util.Scanner;

public class Mancala {

    private int[] firstPlayerBoard;
    private int[] secondPlayerBoard;
    private int firstPlayerScore, secondPlayerScore;

    private int input = 0;
    private int currentPosition = 0;
    private boolean isFirstPlayer;
    private boolean gameOver = false;
    private boolean replay = false;

    public static void main(String[] args) {

        Mancala game = new Mancala();
        game.reset();
        game.printBoard();
        game.play();
    }

    private void play() {

        isFirstPlayer = true;

        while (!gameOver) {
            getInput();
            moveStones();

            // If they can replay
            while (replay) {
                System.out.println("You get to go again!");
                getInput();
                moveStones();
            }

            // Update the user's turn
            updateUserTurn();
        }
    }

    /**
     * Move current player's stones from one slot to another, depending on the input and the hole
     */
    private void moveStones() {

        int[] currentPlayerBlock = getCurrentPlayerBlock();
        int[] otherPlayerBlock = getOtherPlayerBlock();

        // Get the total number of stones in position
        int totalStonesInPosition = getStonesInHole(input);

        // Set the current stone count to 0
        updateHole(currentPlayerBlock, input, 0);


        // The current block being played
        int[] playingBlock = currentPlayerBlock;
        int current = input + 1;

//        boolean currentSide = true;
        boolean ownSide = true;
        replay = false;

        // Start spreading the stones in each slot
        while (totalStonesInPosition > 0) {


            // If you are on your own side and land on an empty one, you get their opposite
            if (ownSide && totalStonesInPosition == 1 && currentPlayerBlock[current] == 0) {
                // If we are not in the well and if their side is not empty
                if (current < 6 && otherPlayerBlock[6 - current] > 0) {

                    // Steal all their pieces and put them in our well, as well as one that we just put in our own slot
                    playingBlock[6] += otherPlayerBlock[5 - current] + 1;

                    // Set their pieces to 0 in slot opposite to ours
                    otherPlayerBlock[5 - current] = 0;

                    // Exit from the loop
                    break;
                }
            }

            // Update total stones and add 1 to the playing block
            totalStonesInPosition--;
            playingBlock[current]++;

            // If you are on your own side and put the last one on your own well, Replay!
            if (ownSide && current == 6 && totalStonesInPosition == 0) {
                replay = true;
                break;
            }

            // If you are on your own side, and  put one stone on your own well and have more remaining,
            // You must switch to the other side
            else if (ownSide && current == 6) {
                ownSide = false;
                current = 0;
                playingBlock = otherPlayerBlock;


                // If you are on your own side and go lap over theirs, you must break out without touching their final score
            } else if (!ownSide && current == 5) {
                ownSide = true;
                current = 0;
                playingBlock = currentPlayerBlock;
            }


            // If you are on your own side, just move over
            else {
                current++;
            }

        }

        updateScore();
        printBoard();
    }

    /**
     * Reset the board
     */
    private void reset() {

        // Reset the board
        firstPlayerBoard = new int[]{4, 4, 4, 4, 4, 4, 0};
        secondPlayerBoard = new int[]{4, 4, 4, 4, 4, 4, 0};

        isFirstPlayer = false;

        // Set player score to 0
        updateScore();
    }


    /**
     * Get user input
     */
    private void getInput() {

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

        // Set input to letter
        input = getCorrespondingPosition(letter);

        // If they are not the first player, we must reverse the input and set the current input as that
        if (!isFirstPlayer) {
            input = 7 - (input + 2);
        }

        // Check if they entered a value where there are stones in the hole
        if (getStonesInHole(input) == 0) {
            System.out.println();
            System.out.print("You have 0 stones in this slot. Please choose another!");
            System.out.println();
            getInput();
        }

    }


    /**
     * Return the block of the current player whose turn it is
     *
     * @return Current Player Block
     */
    private int[] getCurrentPlayerBlock() {
        return isFirstPlayer ? firstPlayerBoard : secondPlayerBoard;
    }

    /**
     * Return the block of the other player who does not have the current turn
     *
     * @return Other Player Block
     */
    private int[] getOtherPlayerBlock() {
        return isFirstPlayer ? secondPlayerBoard : firstPlayerBoard;
    }


    /**
     * Update the user's turn
     */
    private void updateUserTurn() {
        isFirstPlayer = !isFirstPlayer;
    }

    /**
     * Get corresponding position as an index for the letter
     * E.g. if they passed in C, it should return 2 because it's the third one over, minus 1
     *
     * @param letter The letter between A-F
     */
    private int getCorrespondingPosition(String letter) {

        String letters = "ABCDEF";
        currentPosition = letters.indexOf(letter.toUpperCase());
        return currentPosition;
    }

    /**
     * Get the number of stones in a given hole using the position
     * USE THE GIVEN BOARD
     *
     * @param board Game board
     * @param position The position's index
     * @return Total stones in hole
     */
    private int getStonesInHole(int[] board, int position) {
        return board[position];
    }

    /**
     * Get the number of stones in a given hole using the letter position
     * USE THE GIVEN BOARD
     *
     * @param board Game board
     * @param position The position's index
     * @return Total stones in hole
     */
    private int getStonesInHole(int[] board, String position) {
        return board[getCorrespondingPosition(position)];
    }

    /**
     * Get the number of stones in the current player's hole using the number position
     *
     * @param position Index using int
     * @return stones in current player's hole
     */
    private int getStonesInHole(int position) {
        int[] board = getCurrentPlayerBlock();
        return getStonesInHole(board, position);
    }

    /**
     * Get the number of stones in the current player's hole using the letter
     *
     * @param letter Index using letter
     * @return stones in current player's hole
     */
    private int getStonesInHole(String letter) {
        int[] board = getCurrentPlayerBlock();
        return getStonesInHole(board, letter);
    }

    /**
     * Update user's total score based on how many stones they have in the well
     */
    private void updateScore() {
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
    private void updateHole(int[] board, int index, int newNumber) {
        board[index] = newNumber;
    }


    /**
     * Print the board with updated results
     */
    private void printBoard() {

        String padding = "   ";
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
            firstPlayer = firstPlayer + (firstPlayerBoard[i] + padding);
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

        System.out.println("  -----------------------");

        // Print the lettering
        System.out.println(padding + footer);

        // Blank lines below
        System.out.println("\n\n");

    }

}
