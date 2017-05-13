package bhatt.unit1;

import java.util.Arrays;
import java.util.Scanner;

/*
 * Mancala.java
 * This program is a simple console game of Mancala
 * May 8, 2017
 *
 * @author Rishab Bhatt
 */
public class Mancala {

    private int[] firstPlayerBoard;
    private int[] secondPlayerBoard;
    private int firstPlayerScore, secondPlayerScore;

    private int input = 0;
    private boolean isFirstPlayer;
    private boolean gameOver = false;
    private boolean replay = false;

    /**
     * Start the program
     *
     * @param args
     */
    public static void main(String[] args) {

        Mancala game = new Mancala();
        game.reset();
        game.printBoard();
        game.play();
    }

    /**
     * Play the game
     */
    private void play() {

        isFirstPlayer = true;

        // Only continue if the game is not yet over
        while (!gameOver) {
            getInput();
            moveStones();

            // If they can replay
            while (replay) {
                System.out.println("\nYou get to go again!");
                getInput();
                moveStones();

                // Check if the game was over after replaying
                if (isGameOver()) {
                    gameOver = true;
                    break;
                }
            }

            // If the game is over, exit immediately
            if (isGameOver()) {
                gameOver = true;
                break;
            }

            // Update the user's turn
            updateUserTurn();
        }

        // If the game was over...
        if (gameOver) {
            calculateRemainingStones();
            System.out.println("\n\nGame Over!");
            if (firstPlayerScore > secondPlayerScore) {
                System.out.println("Player A Won!");
            } else if (firstPlayerScore == secondPlayerScore) {
                System.out.println("It's a tie!");
            } else {
                System.out.println("Player B Won!");
            }

            System.out.printf("\n\nPoints: \nPlayer A: %3d \nPlayer B: %3d", firstPlayerScore, secondPlayerScore);
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


        // 5
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


                // If you are on your own side and go lap over theirs,
                // you must break out without touching their final score
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
        secondPlayerBoard = new int[]{3, 3, 3, 3, 3, 3, 0};
        firstPlayerBoard = new int[]{3, 3, 3, 3, 3, 3, 0};

        isFirstPlayer = false;

        // Set player score to 0
        updateScore();
    }

    /**
     * If either of the players have 0 stones left to play, the game is done
     *
     * @return True if game is over
     */
    private boolean isGameOver() {
        return isBoardEmpty(firstPlayerBoard) || isBoardEmpty(secondPlayerBoard);
    }

    /**
     * Check if a board is empty
     *
     * @param playerBoard The player board to check if empty
     * @return boolean, depending if the hole is empty
     */
    private boolean isBoardEmpty(int[] playerBoard) {
        boolean none = true;
        for (int i = 0; i < 6; i++) {
            if (playerBoard[i] > 0) {
                none = false;
            }
        }
        return none;
    }

    /**
     * Calculate the remaining stones and put them in the well
     */
    private void calculateRemainingStones() {
        for (int i = 0; i < 6; i++) {
            firstPlayerBoard[6] += firstPlayerBoard[i];
            secondPlayerBoard[6] += secondPlayerBoard[i];
        }
        updateScore();
        ;
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
        return letters.indexOf(letter.toUpperCase());
    }

    /**
     * Get the number of stones in a given hole using the position
     * USE THE GIVEN BOARD
     *
     * @param board    Game board
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
     * @param board    Game board
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
     * Print the latest board
     */
    private void printBoard() {

        String padding = "  ";
        String firstPlayer = "";
        String secondPlayer = "";
        String footer = "A B C D E F".replaceAll(" ", padding + " ");

        // Store the second players board in a string, going backwards and skipping the first "0"
        for (int i = secondPlayerBoard.length - 2; i >= 0; i--) {
            secondPlayer += secondPlayerBoard[i] + padding;

            // Even the number of spaces. For e.g if there are 2 digits, then the spaces for the rest don't align so this fixes this
            if (String.valueOf(secondPlayerBoard[i]).length() == 1) {
                secondPlayer += " ";
            }
        }

        // Store the first players board in a string, going forwards and skipping the last "0"
        for (int i = 0; i < firstPlayerBoard.length - 1; i++) {
            // Even the number of spaces. For e.g if there are 2 digits, then the spaces for the rest don't align so this fixes this
            firstPlayer = firstPlayer + (firstPlayerBoard[i] + padding);
            if (String.valueOf(firstPlayerBoard[i]).length() == 1) {
                firstPlayer += " ";
            }
        }

        // Initial extra lines
        System.out.println("\n\n");

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

    }

}
