package cpsc2150.extendedConnectX.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoard {
    /**
     * This function will create call constructor for the Gamebaord clas
     * @param row the amount of rows for the board
     * @param column the amount of columns for the board
     * @param numberToWin the number to win a game
     * @return new constructed GameBoard of size [row] * [column]
     * @post MakeAGameBoard = [new Gameboard of size [row][column] and numberToWin set]
     */
    private IGameBoard MakeAGameBoard(int row, int column, int numberToWin ){
        return new GameBoard(row, column, numberToWin);
    }

    /**
     * helper to verify with toString of GameBoard and turns the array into string
     * @pre board is initialized
     * @post self = #self
     * @post toString = [a string that represents a 2d array in the gameboard format]
     * @return a string representation of a 2d array
     */
    private String toString(char[][] board){
        int row = board.length;
        int column = board[0].length;
        //create initial row with columns number
        String gameBoard = "|";
        for(int i = 0; i < column; i++){
            if( i < 10) {
                gameBoard += "" + ' ' + i + "|";
            }
            else gameBoard += i + "|";
        }
        gameBoard += "\n";

        //access using primary methods and print board
        for(int i = 0; i < row; i++){
            gameBoard += "|";
            //each column print character and line
            for(int j = 0; j < column; j++){
                gameBoard += board[i][j] + " |";
            }
            gameBoard += "\n";
        }

        return gameBoard;
    }

    //Case: row = 5, column = 5, numberToWin = 3
    @Test
    public void testConstructor_row5_column5_numberToWin3() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 3;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: The max inputs for a new board
    @Test
    public void testConstructor_rowMAX_columnMAX_numberToWinMAX() {
        //GameBoard constructor
        int row = IGameBoard.MAX_ROW;
        int column = IGameBoard.MAX_COLUMN;
        int numberToWin = IGameBoard.MAX_NUM_TO_WIN;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: The min inputs for a new board
    @Test
    public void testConstructor_rowMIN_columnMIN_numberToWinMIN() {
        //GameBoard constructor
        int row = IGameBoard.MINIMUM;
        int column = IGameBoard.MINIMUM;
        int numberToWin = IGameBoard.MINIMUM;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //checkIfFree
    //Case: trying to put a new token in a column thats full
    @Test
    public void testCheckIfFree_ColumnFull() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 3;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //Fill in GameBoard column 0
        for(int i = 0; i < row; i++){
            gb.placeToken('X', 0);
            expectedBoard[i][0] = 'X';
        }
        //try to place a different token inside same row, but it should reject
        gb.placeToken('O', 0);

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: Column is empty
    @Test
    public void testCheckIfFree_ColumnEmpty() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 3;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //fill in column thats empty and should let place
        gb.placeToken('O', 0);
        expectedBoard[row - 1][0] = 'O';

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: Column is empty
    @Test
    public void testCheckIfFree_ColumnPartialFilled() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 3;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }

        //fill in column thats empty and should let place
        gb.placeToken('O', 0);
        expectedBoard[row - 1][0] = 'O';

        //check to make sure gb initialized to the correct size
        assertEquals(toString(expectedBoard), gb.toString());
    }
}