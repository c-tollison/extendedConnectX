package cpsc2150.extendedConnectX.models;
/**
 * JavaDoc and Contracts for the class GameBoard class which keeps track
 * of all the positions on the board.
 * This class also checks for wins and this class will take in all user input.
 * @author Carson Tollison
 * @invariant (0 <= row < MAX_ROW) and (0 <= column < MAX_COLUMN)
 * @invariant char needs to be 'X' or 'O'
 * @invariant No gaps between tokens in each column on the board
 * @correspondence Structure = board
 * @correspondence self = [board of characters used by the players]
 */
public class GameBoard extends AbsGameBoard implements IGameBoard{

    private char [][] board;

    /**
     * This is the constructor for the GameBoard class
     * @post board is initialized to [MAX_ROW][MAX_COLUMN]
     * All positions are set to a blank character
     */
    public GameBoard() {
        //initialize the board
        board = new char[MAX_ROW][MAX_COLUMN];
        for(int i = 0; i < MAX_ROW; i++) {
            for(int j = 0; j < MAX_COLUMN; j++){
                board[i][j] = ' ';
            }
        }
    }

    @Override
    public void placeToken(char p, int c) {
        //place a token by starting at bottom and moving up
        for(int i = MAX_ROW - 1; i >= 0; i--){
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
        return MAX_ROW;
    }

    @Override
    public int getNumColumns() {
        return MAX_COLUMN;
    }

    @Override
    public int getNumToWin() {
        return NUM_TO_WIN;
    }

}
