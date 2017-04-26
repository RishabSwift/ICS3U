package bhatt.unit1;

/*
 * Life.java
 * This is the Game of Life program devised by John Conway. It loops over generations and finds the alive and dead cells
 * April 26, 2017
 *
 * @author Rishab Bhatt
 */

import java.util.Scanner;

public class Life {

    private final int xLength = 10;
    private final int yLength = 5;
    private boolean coordinates[][] = new boolean[xLength][yLength];
    private int generations, currentGeneration;

    private Life(int generations) {
        this.generations = generations;
    }

    public static void main(String[] args) {

        Life life = new Life(10);
        life.getInput();

        // Print the start
        System.out.println("Given Input:");
        life.printCoordinates(life.coordinates);

        for (int i = 1; i <= life.generations; i++) {
            life.currentGeneration = i;
            life.newGeneration();
        }
    }

    /**
     * Print the coordinates
     *
     * @param coordinates Coordinate
     */
    private void printCoordinates(boolean[][] coordinates) {

        for (boolean[] rows : coordinates) {
            for (boolean column : rows) {
                System.out.print(column ? "x" : "o");
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Create a new generation
     */
    private void newGeneration() {

        boolean[][] newCoordinates = new boolean[xLength][yLength];

        int numberOfAliveCells = 0;

        for (int row = 0; row < coordinates.length; row++) {
            for (int column = 0; column < coordinates[row].length; column++) {
                // If cell is alive set the value to 1 or else 0
                if (isCellAlive(row, column)) {
                    newCoordinates[row][column] = true;
                    numberOfAliveCells++;
                } else {
                    newCoordinates[row][column] = false;
                }
            }
        }

        // If there are no alive cells
        if (numberOfAliveCells == 0) {
            System.out.println("All cells are dead by Generation " + currentGeneration);
            return;
        }

        coordinates = newCoordinates;

        // Print coordinates
        System.out.printf("Generation %d: \n", currentGeneration);
        printCoordinates(coordinates);
    }


    /**
     * Check if a cell is alive given a row and column
     *
     * @param row    Row of Coordinate
     * @param column Column of Coordinate
     * @return boolean
     */
    private boolean isCellAlive(int row, int column) {


        // If the current cell is alive
        boolean cellAlive = coordinates[row][column];

        int numberOfNeighbours = 0;
        // check one to the right of column
        if (coordinates[row].length > column + 1) {
            if (coordinates[row][column + 1]) {
                numberOfNeighbours++;

            }
        }
        // check one to the left of column
        if (coordinates[row].length > column - 1 && column - 1 >= 0) {
            if (coordinates[row][column - 1]) {
                numberOfNeighbours++;
            }
        }

        // check one above column
        if (coordinates.length > row - 1 && row - 1 >= 0) {
            if (coordinates[row - 1][column]) {
                numberOfNeighbours++;
            }
        }

        // check one below column
        if (coordinates.length > row + 1) {
            if (coordinates[row + 1][column]) {
                numberOfNeighbours++;
            }
        }

        // check one top left diagonal to column
        if (coordinates.length > row - 1 && coordinates[row].length > column - 1 && row - 1 >= 0 && column - 1 >= 0) {
            if (coordinates[row - 1][column - 1]) {
                numberOfNeighbours++;
            }
        }

        // check one top right diagonal to column
        if (coordinates.length > row - 1 && coordinates[row].length > column + 1 && row - 1 >= 0) {
            if (coordinates[row - 1][column + 1]) {
                numberOfNeighbours++;
            }
        }

        // check one bottom left diagonal to column
        if (coordinates.length > row + 1 && coordinates[row].length > column - 1 && column - 1 >= 0) {
            if (coordinates[row + 1][column - 1]) {
                numberOfNeighbours++;
            }
        }

        // check one bottom right diagonal to column
        if (coordinates.length > row + 1 && coordinates[row].length > column + 1) {
            if (coordinates[row + 1][column + 1]) {
                numberOfNeighbours++;
            }
        }

//        System.out.println("Number of neighbours for " + row + ", " + column + " was " + numberOfNeighbours);

        // If the number of neighbours was 2 or 3, the cell is alive
        if ((numberOfNeighbours == 2) && cellAlive) {
            return true;
        } else if (numberOfNeighbours == 3) {
            return true;
        }

        return false;
    }

    /**
     * Get input from user using scanner
     * THe input will be coordinates like (x, y)
     */
    private void getInput() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the coordinates separated by space (e.g. \"5 30\").");
        System.out.println("Type \"stop\" to end.");

        while (true) {

            // Get the x value and trim it
            String _x = scan.next().trim();

            // stop if they enter "stop"
            if (_x.equals("stop")) {
                break;
            }

            // Get the y value and trim it
            String _y = scan.next().trim();

            // Parse the input
            int x = Integer.parseInt(_x);
            int y = Integer.parseInt(_y);

            // Check if the values are valid
            if (x >= xLength || y >= yLength) {
                System.out.printf("You have entered a value greater than (%d, %d). Please try again.\n", xLength, yLength);
            } else {
                coordinates[x][y] = true;
            }

        }


    }

}
