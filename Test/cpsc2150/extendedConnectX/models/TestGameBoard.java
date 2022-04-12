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
        for(int i = row - 1; i >= 0; i--){
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
        assertEquals(3, gb.getNumToWin());
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
        assertEquals(IGameBoard.MAX_NUM_TO_WIN, gb.getNumToWin());
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
        assertEquals(IGameBoard.MINIMUM, gb.getNumToWin());
    }

    //checkIfFree
    //Case: trying to put a new token in a column that's full
    @Test
    public void testCheckIfFree_Column_Full() {
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

        //check to make sure column full
        assertTrue(gb.checkIfFree(0));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: Column is empty
    @Test
    public void testCheckIfFree_Column_Empty() {
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

        //check to make sure column returns free
        assertFalse(gb.checkIfFree(0));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: Column is empty
    @Test
    public void testCheckIfFree_Column_Partial_Filled() {
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
        expectedBoard[0][0] = 'O';

        //check to make sure column returns free
        assertFalse(gb.checkIfFree(0));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //checkHorizWin
    //Case: check on an empty board
    @Test
    public void testHorizWin_no_Win_Condition_empty_Board() {
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
        //check to make sure horizontal win doesn't occur on empty board
        assertFalse(gb.checkHorizWin(new BoardPosition(0, 0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: The bottom row has a win condition
    @Test
    public void testHorizWin_bottom_Row_Win_right_Marker_End() {
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

        //fill bottom rows with 3 'X' in a row
        for(int i = 0; i < numberToWin; i++){
            gb.placeToken('X', i);
            expectedBoard[0][i] = 'X';
        }
        //check to make sure horizontal win occurs when last row is place
        assertTrue(gb.checkHorizWin(new BoardPosition(0,numberToWin - 1), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: This checks a board with multiple tokens and win condition marker placed in middle
    @Test
    public void testHorizWin_last_Marker_Middle() {
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

        //fill board with 2 rows of no wins
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < column; j++){
                //dont want the win condition to happen so put a character in middle
                if(j == 2){
                    gb.placeToken('O', j);
                    expectedBoard[i][j] = 'O';
                }
                else {
                    gb.placeToken('X', j);
                    expectedBoard[i][j] = 'X';
                }
            }
        }

        //skip middle marker
        for(int i = 0; i < numberToWin; i++){
            if(i != 1) {
                gb.placeToken('X', i);
                expectedBoard[2][i] = 'X';
            }
        }

        //last marker in middle
        gb.placeToken('X', 1);
        expectedBoard[2][1] = 'X';

        //check to make sure horizontal win occurs when middle is placed
        assertTrue(gb.checkHorizWin(new BoardPosition(2,1), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //last marker puts enough for win condition in a row except the tokens are different
    @Test
    public void testHorizWin_last_Marker_Not_Win() {
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

        //middle marker different
        for(int i = 0; i < numberToWin; i++){
            if(i == 1) {
                gb.placeToken('X', i);
                expectedBoard[0][i] = 'X';
            }
            else {
                gb.placeToken('O', i);
                expectedBoard[0][i] = 'O';
            }
        }

        //check to make sure horizontal win occurs when middle is placed
        assertFalse(gb.checkHorizWin(new BoardPosition(0, 2), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //checkVertWin
    //Case: check on an empty board
    @Test
    public void testVertWin_no_Win_Condition_empty_Board() {
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
        //check to make sure vertical win doesn't occur on empty board
        assertFalse(gb.checkVertWin(new BoardPosition(0, 0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: last marker placed creates win
    @Test
    public void testVertWin_win_last_marker_placed() {
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

        for(int i = 0; i < numberToWin; i++){
            gb.placeToken('X', 0);
            expectedBoard[i][0] = 'X';
        }
        //vertical win because last placed for win conditon
        assertTrue(gb.checkVertWin(new BoardPosition(2,0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: last marker placed does not create win
    @Test
    public void testVertWin_not_win_last_marker_placed() {
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

        for(int i = 0; i < numberToWin - 1; i++){
            gb.placeToken('X', 0);
            expectedBoard[i][0] = 'X';
        }

        gb.placeToken('O', 0);
        expectedBoard[2][0] = 'O';
        //no win condition because wrong token
        assertFalse(gb.checkVertWin(new BoardPosition(2, 0), 'O'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: last marker placed is win but there is distinct because it is at the top of the column
    //function needs to skip other character
    @Test
    public void testVertWin_win_last_placed_top_column() {
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

        for(int i = 0; i < row; i++){
            if(i < 2) {
                gb.placeToken('X', 0);
                expectedBoard[i][0] = 'X';
            }
            else {
                gb.placeToken('O', 0);
                expectedBoard[i][0] = 'O';
            }
        }
        //win condition because token placed is
        assertTrue(gb.checkVertWin(new BoardPosition(row - 1,0), 'O'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //checkDiagWin
    //Case: check on an empty board
    @Test
    public void testDiagWin_no_Win_Condition_empty_Board() {
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
        //check to make sure Diagonal win doesn't occur on empty board
        assertFalse(gb.checkDiagWin(new BoardPosition(0, 0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from left to right last token placed on right side
    @Test
    public void testDiagWin_win_condition_met_ltoR_right() {
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

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        expectedBoard[0][0] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][2] = 'O';
        expectedBoard[1][2] = 'O';
        expectedBoard[2][2] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(2,2), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from right to left last token placed on right side
    @Test
    public void testDiagWin_win_condition_met_RtoL_right() {
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

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        expectedBoard[0][2] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][0] = 'O';
        expectedBoard[1][0] = 'O';
        expectedBoard[2][0] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(2,0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from left to right last token placed on left side
    @Test
    public void testDiagWin_win_condition_met_ltoR_left() {
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

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        expectedBoard[0][0] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][2] = 'O';
        expectedBoard[1][2] = 'O';
        expectedBoard[2][2] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(0,0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from right to left last token placed on left side
    @Test
    public void testDiagWin_win_condition_met_RtoL_left() {
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

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        expectedBoard[0][2] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][0] = 'O';
        expectedBoard[1][0] = 'O';
        expectedBoard[2][0] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(0,2), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from left to right last token placed in middle
    @Test
    public void testDiagWin_win_condition_met_ltoR_middle() {
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

        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);

        expectedBoard[0][0] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][2] = 'O';
        expectedBoard[1][2] = 'O';
        expectedBoard[2][2] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(1,1), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: check diag win from right to left last token placed in middle
    @Test
    public void testDiagWin_win_condition_met_RtoL_middle() {
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

        gb.placeToken('X', 2);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        expectedBoard[0][2] = 'X';
        expectedBoard[0][1] = 'O';
        expectedBoard[1][1] = 'X';
        expectedBoard[0][0] = 'O';
        expectedBoard[1][0] = 'O';
        expectedBoard[2][0] = 'X';
        //check to make sure diag win
        assertTrue(gb.checkDiagWin(new BoardPosition(1,1), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //checkTie
    //Case: check on an empty board
    @Test
    public void testcheckTie_empty_Board() {
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
        //check to make sure no tie on empty board
        assertFalse(gb.checkTie());
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: checks full board for tie
    @Test
    public void testcheckTie_full_board_tie() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 4;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++) {
                if(i == 2){
                    if (j % 2 == 0){
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                    else {
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                }
                else {
                    if (j % 2 == 0){
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                    else {
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                }


            }
        }
        //check to make sure tie on fill
        assertTrue(gb.checkTie());
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: almost full but one case is free so no tie
    @Test
    public void testcheckTie_space_free_no_tie() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 4;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++) {
                if(i == 2){
                    if (j % 2 == 0){
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                    else {
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                }
                else {
                    if (j % 2 == 0){
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                    else {
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                }
                //leaves one free
                if(i == row - 1 && j == column - 2){
                    break;
                }
            }
        }

        //check to make sure no tie on last file
        assertFalse(gb.checkTie());
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: Half full board no tie
    @Test
    public void testcheckTie_half_board_no_tie() {
        //GameBoard constructor
        int row = 5;
        int column = 5;
        int numberToWin = 4;

        //construct both board and array
        IGameBoard gb = MakeAGameBoard(row, column, numberToWin);
        //create a 2d array and set values to blank
        char[][] expectedBoard = new char[row][column];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                expectedBoard[i][j] = ' ';
            }
        }
        for(int i = 0; i <= row / 2; i++){
            for(int j = 0; j < column; j++) {
                if(i == 2){
                    if (j % 2 == 0){
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                    else {
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                }
                else {
                    if (j % 2 == 0){
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                    else {
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                }
            }
        }

        //check to make sure no tie on last file
        assertFalse(gb.checkTie());
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //whatsAtPos
    //Case: check on an empty board
    @Test
    public void testwhatsAtPos_empty_Board() {
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

        //check to make sure its blank on empty board
        assertEquals(gb.whatsAtPos(new BoardPosition(0,0)), ' ');
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place multiple tokens in column make sure know are overriden
    @Test
    public void testwhatsAtPos_first_in_column() {
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

        //place multiple element
        gb.placeToken('X', 2);
        expectedBoard[0][2] = 'X';

        for(int i = 1; i < row; i++){
            gb.placeToken('O', 2);
            expectedBoard[i][2] = 'O';
        }
        //check to make sure its X after place into column
        assertEquals(gb.whatsAtPos(new BoardPosition(0,2)), 'X');
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place left side
    @Test
    public void testwhatsAtPos_left_Place() {
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

        //place multiple element
        gb.placeToken('X', 0);
        expectedBoard[0][0] = 'X';

        //check to make sure its X after place into column
        assertEquals(gb.whatsAtPos(new BoardPosition(0,0)), 'X');
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place middle side
    @Test
    public void testwhatsAtPos_middle_place() {
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

        //place multiple element
        gb.placeToken('X', 2);
        expectedBoard[0][2] = 'X';

        //check to make sure its X after place into column
        assertEquals(gb.whatsAtPos(new BoardPosition(0,2)), 'X');
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place right side
    @Test
    public void testwhatsAtPos_right_place() {
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

        //place multiple element
        gb.placeToken('X', column - 1);
        expectedBoard[0][column -1] = 'X';

        //check to make sure its X after place into column
        assertEquals(gb.whatsAtPos(new BoardPosition(0,column - 1)), 'X');
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //isPlayerAtPos
    //case: no player at pos because nothing on board
    @Test
    public void testIsPlayerAtPos_emptyBoard(){
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

        //check to make sure its blank
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,0), ' '));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place multiple tokens in column make sure know are overriden
    @Test
    public void testIsPlayerAtPos_first_in_column() {
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

        //place multiple element
        gb.placeToken('X', 2);
        expectedBoard[0][2] = 'X';

        for(int i = 1; i < row; i++){
            gb.placeToken('O', 2);
            expectedBoard[i][2] = 'O';
        }
        //check to make sure its X after place into column
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,2), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place left side
    @Test
    public void testIsPlayerAtPos_left_Place() {
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

        //place multiple element
        gb.placeToken('X', 0);
        expectedBoard[0][0] = 'X';

        //check to make sure its X after place into column
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,0), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place middle side
    @Test
    public void testIsPlayerAtPos_middle_place() {
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

        //place multiple element
        gb.placeToken('X', 2);
        expectedBoard[0][2] = 'X';

        //check to make sure its X after place into column
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,2), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //Case: place right side
    @Test
    public void testIsPlayerAtPos_right_place() {
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

        //place multiple element
        gb.placeToken('X', column - 1);
        expectedBoard[0][column -1] = 'X';

        //check to make sure its X after place into column
        assertTrue(gb.isPlayerAtPos(new BoardPosition(0,column - 1), 'X'));
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //placeToken
    //case: place in the lower corner
    @Test
    public void testplaceToken_left_corner_place(){
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

        gb.placeToken('X', 0);
        expectedBoard[0][0] = 'X';
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //case all tokens are placed correctly in filled bored
    @Test
    public void testplaceToken_board_filled(){
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

        for(int i = 0; i <= row / 2; i++){
            for(int j = 0; j < column; j++) {
                if(i == 2){
                    if (j % 2 == 0){
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                    else {
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                }
                else {
                    if (j % 2 == 0){
                        gb.placeToken('O', j);
                        expectedBoard[i][j] = 'O';
                    }
                    else {
                        gb.placeToken('X', j);
                        expectedBoard[i][j] = 'X';
                    }
                }
            }
        }
        //compare boards

        assertEquals(toString(expectedBoard), gb.toString());
    }

    //case: place in the right corner
    @Test
    public void testplaceToken_right_corner_place(){
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

        gb.placeToken('X', column - 1);
        expectedBoard[0][column - 1] = 'X';
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //case: place at the top of a column
    @Test
    public void testplaceToken_top_column(){
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

        for(int i = 0; i < row; i++) {
            gb.placeToken('X', 0);
            expectedBoard[i][0] = 'X';
        }
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }

    //case: place at the top of a column
    @Test
    public void testplaceToken_middle_column(){
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

        for(int i = 0; i < row / 2; i++) {
            gb.placeToken('X', 0);
            expectedBoard[i][0] = 'X';
        }
        //compare boards
        assertEquals(toString(expectedBoard), gb.toString());
    }
}