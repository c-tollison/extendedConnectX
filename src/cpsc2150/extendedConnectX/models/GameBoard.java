package cpsc2150.extendedConnectX.models;
/**
 * JavaDoc and Contracts for the class GameBoard class which keeps track
 * of all the positions on the board.
 * This class also checks for wins and this class will take in all user input.
 * @author Carson Tollison
 * @invariant (0 <= row < MAX_ROW) and (0 <= column < MAX_COLUMN)
 * @invariant Tokens can be any char but need to be uppercase if applicable
 * @invariant numberToWin cannot be greater than the rows or columns
 * @invariant No gaps between tokens in each column on the board
 * @correspondence self = [board of characters used by the players]
 * @correspondence Structure = board[MAX_ROW][MAX_COLUMN]
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private final int rows;
    private final int columns;
    private final int numberToWin;
    private final char[][] board;

    /**
     * This is the constructor for the GameBoard class
     * @param rows = number of rows for game
     * @param columns = number of columns for game
     * @param numberToWin = number of tokens to win in a row
     * @post board is initialized to [MAX_ROW][MAX_COLUMN]
     * All positions are set to a blank character
     */
    public GameBoard(int rows, int columns, int numberToWin) {
        this.rows = rows;
        this.columns = columns;
        this.numberToWin = numberToWin;

        //initialize the board
        board = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++){
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void placeToken(char p, int c) {
        //place a token by starting at bottom and moving up
        for(int i = 0; i < rows; i++){
            //if blank then place and break
            if(board[i][c] == ' ') {
                board[i][c] = p;
                return;
            }
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumColumns() {
        return columns;
    }

    @Override
    public int getNumToWin() {
        return numberToWin;
    }

}
