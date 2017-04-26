package bhatt.unit1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.PrimitiveIterator.OfDouble;

public class GameOfLife {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the coordinates seperated by space (e.g. \"5 30\").");
        System.out.println("Type \"stop\" to end.");

        int xLength = 10;
        int yLength = 5;

        int coordinates[][] = new int[xLength][yLength];

        while (true) {

            String _x = scan.next().trim();

            // stop if they enter "stop"
            if (_x.equals("stop")) {
                break;
            }

            String _y = scan.next().trim();

            int x = Integer.parseInt(_x);
            int y = Integer.parseInt(_y);

            // Check if the values are valid
            if (x >= xLength || y >= yLength) {
                System.out.printf("You have entered a value greater than (%d, %d). Please try again.\n", xLength,
                        yLength);
            } else {
                coordinates[x][y] = 1;
            }

        }

        for (int i = 0; i < coordinates.length; i++) {

            for (int j = 0; j < coordinates[i].length; j++) {
                System.out.print(coordinates[i][j]);
            }
            System.out.println();
        }

        int[][] _coordinates = coordinates;

        System.out.println("----------");
        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < coordinates.length; i++) {

            int numberOfNeighbours = 0;
            for (int j = 0; j < coordinates[i].length; j++) {

                // 1, 1
                // 0, 1
                // 1, 0
                // 0, -1
                // 1, -1
                // -1, -1
                // -1, 0
                // -1, 1


                // if cell alive on previous day

                if (coordinates[i][j] == 1) {

                    // check one to the right of j
                    if (coordinates[i].length > j + 1) {
                        if (coordinates[i][j + 1] == 1) {
                            numberOfNeighbours++;
                            list.add("right");

                        }
                    }
                    // check one to the left of j
                    if (coordinates[i].length > j - 1 && j - 1 >= 0) {
                        if (coordinates[i][j - 1] == 1) {
                            numberOfNeighbours++;
                            list.add(" left");
                        }
                    }

                    // check one above j
                    if (coordinates.length > i - 1 && i - 1 >= 0) {
                        if (coordinates[i - 1][j] == 1) {
                            numberOfNeighbours++;
                            list.add("above");
                        }
                    }

                    // check one below j
                    if (coordinates.length > i + 1) {
                        if (coordinates[i + 1][j] == 1) {
                            numberOfNeighbours++;
                            list.add("below");
                        }
                    }

                    // check one top left diagonal to j
                    if (coordinates.length > i - 1 && coordinates[i].length > j - 1 && i - 1 >= 0 && j - 1 >= 0) {
                        if (coordinates[i - 1][j - 1] == 1) {
                            numberOfNeighbours++;
                            list.add("top left");
                        }
                    }

                    // check one top right diagonal to j
                    if (coordinates.length > i - 1 && coordinates[i].length > j + 1 && i - 1 >= 0) {
                        if (coordinates[i - 1][j + 1] == 1) {
                            numberOfNeighbours++;
                            list.add("top right");
                        }
                    }

                    // check one bottom left diagonal to j
                    if (coordinates.length > i + 1 && coordinates[i].length > j - 1 && j - 1 >= 0) {
                        if (coordinates[i + 1][j - 1] == 1) {
                            numberOfNeighbours++;
                            list.add("bottom left");
                        }
                    }

                    // check one bottom right diagonal to j
                    if (coordinates.length > i + 1 && coordinates[i].length > j + 1) {
                        if (coordinates[i + 1][j + 1] == 1) {
                            numberOfNeighbours++;
                            list.add("bottom right");
                        }
                    }

                    // if the number of neighbors was 2 or 3
//                    if (numberOfNeighbours > 0) {
//                        System.out.print(" [" + numberOfNeighbours + "->]");
//                    }

                    // if the number of neighbours was less than 2, the cell dies
                    if (numberOfNeighbours < 2) {
                        coordinates[i][j] = 0;
                    }

                } else {

                    numberOfNeighbours = 0;


                    if (_coordinates[i][j] == 0) {

                        // check one to the right of j
                        if (_coordinates[i].length > j + 1) {
                            if (_coordinates[i][j + 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }
                        // check one to the left of j
                        if (_coordinates[i].length > j - 1 && j - 1 >= 0) {
                            if (_coordinates[i][j - 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one above j
                        if (_coordinates.length > i - 1 && i - 1 >= 0) {
                            if (_coordinates[i - 1][j] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one below j
                        if (_coordinates.length > i + 1) {
                            if (_coordinates[i + 1][j] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one top left diagonal to j
                        if (_coordinates.length > i - 1 && _coordinates[i].length > j - 1 && i - 1 >= 0 && j - 1 >= 0) {
                            if (_coordinates[i - 1][j - 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one top right diagonal to j
                        if (_coordinates.length > i - 1 && _coordinates[i].length > j + 1 && i - 1 >= 0) {
                            if (_coordinates[i - 1][j + 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one bottom left diagonal to j
                        if (_coordinates.length > i + 1 && _coordinates[i].length > j - 1 && j - 1 >= 0) {
                            if (_coordinates[i + 1][j - 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }

                        // check one bottom right diagonal to j
                        if (_coordinates.length > i + 1 && _coordinates[i].length > j + 1) {
                            if (_coordinates[i + 1][j + 1] == 1) {
                                numberOfNeighbours++;
                            }
                        }


                    }
//					// if the number of neighbours was less than 2, the cell
//					// dies
                    if (numberOfNeighbours == 3) {
                        _coordinates[i][j] = 1;
                    }
//
                }

                numberOfNeighbours = 0;
                System.out.print(coordinates[i][j]);

            }
            list.add("NEW");
            System.out.println();

        }

        System.out.println("------ NEW --- ");
        for (int i = 0; i < coordinates.length; i++) {

            for (int j = 0; j < coordinates[i].length; j++) {
                System.out.print(coordinates[i][j]);
            }
            System.out.println();
        }

        System.out.println(list);

    }

    public static int getNumberOfNeighbours(int[][] coordinates, int forNumber) {

        ArrayList<String> list = new ArrayList<String>();

        int numberOfNeighbours = 0;

        for (int i = 0; i < coordinates.length; i++) {

            numberOfNeighbours = 0;

            for (int j = 0; j < coordinates[i].length; j++) {

                // If the current coordinate number is the number they passed
                // in, we check for the neighbours for that
                if (coordinates[i][j] == forNumber) {

                    if (coordinates[i][j + 1] == 1) {
                        numberOfNeighbours++;
                        list.add("right");

                    }
                    // check one to the left of j
                    if (coordinates[i][j - 1] == 1) {
                        numberOfNeighbours++;
                        list.add(" left");
                    }

                    // check one above j
                    if (coordinates[i - 1][j] == 1) {
                        numberOfNeighbours++;
                        list.add("above");
                    }

                    // check one below j
                    if (coordinates[i + 1][j] == 1) {
                        numberOfNeighbours++;
                        list.add("below");
                    }

                    // check one top left diagonal to j
                    if (coordinates[i - 1][j - 1] == 1) {
                        numberOfNeighbours++;
                        list.add("top left");
                    }

                    // check one top right diagonal to j
                    if (coordinates[i - 1][j + 1] == 1) {
                        numberOfNeighbours++;
                        list.add("top right");
                    }

                    // check one bottom left diagonal to j
                    if (coordinates[i + 1][j - 1] == 1) {
                        numberOfNeighbours++;
                        list.add("bottom left");
                    }

                    // check one bottom right diagonal to j
                    if (coordinates[i + 1][j + 1] == 1) {
                        numberOfNeighbours++;
                        list.add("bottom right");
                    }

                    // if the number of neighbors was 2 or 3
                    if (numberOfNeighbours > 0) {
                        System.out.print(" [" + numberOfNeighbours + "->]");
                    }

                    // if the number of neighbours was less than 2, the cell
                    // dies
                    if (numberOfNeighbours < 2) {
                        coordinates[i][j] = 0;
                    }
                }
            }
        }

        return numberOfNeighbours;
    }

}
