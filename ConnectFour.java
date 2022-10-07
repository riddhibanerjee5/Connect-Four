import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        int height = 0;
        int length = 0;
        System.out.print("What would you like the height of the board to be? ");
        height = scnr.nextInt();
        System.out.print("What would you like the length of the board to be? ");
        length = scnr.nextInt();

        char[][] board = new char[height][length];

        initializeBoard(board);
        printBoard(board);

        char[] chipType = {'x', 'o'}; // sets the two chip types

        System.out.println("  Player 1: " + chipType[0]); // player 1 = x
        System.out.println("  Player 2: " + chipType[1]); // player 2 = o

        int currPlayer = 1;
        int col = 0;
        int row = 0;


        while (true) {
            col = -1;
            while (col >= length || col < 0) {
                System.out.print("Player " + currPlayer + ": Which column would you like to choose? ");
                col = scnr.nextInt() ;
            }

            // insert the chip of the current player in the chosen column of the board, and return the row number
            row = insertChip(board, col, chipType[currPlayer - 1]);


            printBoard(board);

            if (checkIfWinner(board, col, row, chipType[currPlayer - 1])) {
                System.out.print("Player " + currPlayer + " won the game!");
                break;
            } else if (checkIfTie(board, col, row))  {
                System.out.print("Draw. Nobody wins.");
                break;
            } else {
                // change the turn of the player by assigning the right player number to currPlayer;
                if (currPlayer == 1) {
                    currPlayer = 2;
                } else {
                    currPlayer = 1;
                }
            }

        }


    }


    public static void initializeBoard(char[][] array) {
        char boardChar = '-'; // character for makeup of the board
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                array[x][y] = boardChar;
            }
        }
    }

    public static void printBoard(char[][] array) {
        System.out.println("");
        for (int x = array.length-1; x >= 0 ; x--) {
            for (int y = 0; y < array[x].length; y++) {
                System.out.print(array[x][y] +  " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        char boardChar = '-';
        int row = -1;
        for (int x = 0; x < array.length; x++) { // start from the last row so that the row zero is at the bottom of the board
            if (array[x][col] == boardChar) {
                array[x][col] = chipType;
                row = x;
                break;
            }
        }
        return row;
    }

    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int tCount = 0;
        int rows = array.length;
        int cols = array[0].length;
        for (int y = 0; y < cols; y++) { // win conditions if chips are vertically four-in-a-row
            tCount = 0;
            for (int x = 0; x < rows; x++) {
                if (array[x][y] == chipType) {
                    tCount = tCount + 1;
                }
            }
            if (tCount == 4) { //
                return true;
            }
        }
        for (int y = 0; y < rows; y++) { // win conditions if chips are horizontally four-in-a-row
            tCount = 0;
            for (int x = 0; x < cols; x++) {
                if (array[y][x] == chipType) {
                    tCount = tCount + 1;
                }
            }
            if (tCount == 4) {
                return true;
            }
        }
        // no winning match found by checking along the rows and the columns
        return false;
    };

    public static boolean checkIfTie(char[][] array, int col, int row) {
        char boardChar = '-';
        for (int x = 0; x < array.length; x++) {
            for (int y = 0; y < array[x].length; y++) {
                if (array[x][y] == boardChar) {
                    return false;
                }
            }
        }
        return true; // no empty space is found = tie
    };

}