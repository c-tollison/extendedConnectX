package cpsc2150.extendedConnectX.models;
/**
 * JavaDoc and Contracts for the class GameBoard class which keeps track
 * of all the positions on the board.
 * This class also checks for wins and this class will take in all user input.
 * @author Carson Tollison
 * @invariant (0 <= row <= MAX_ROW) and (0 <= column <= MAX_COLUMN) AND char needs to be 'X' or 'O'
 */
public class GameBoard {

    static final int MAX_ROW = 6;
    static final int MAX_COLUMN = 9;
    private char [][] board;

    /**
     * This is the constructor for the GameBoard class
     * @post board is initialized to [MAX_ROW][MAX_COLUMN]
     */
    public GameBoard(){}

    /**
     * returns true if the column can accept another token; false otherwise.
     * @param c is an int with the column the token is to be placed in
     * @return a boolean if the column is free or exists
     * @post  checkIfFree() = true iff [(0 <= c < MAX_COLUMN) && (row < ROW_MAX)
     * AND checkIfFree() = false iff [(c < 0) || (c >= MAX_COLUMN) || (row == ROW_MAX - 1)
     */
    public boolean checkIfFree(int c) {}

    /**
     * places the character p in column c. The token will
     * be placed in the lowest available row in column c.
     * @param p is the character from the player. 'X' or 'O'
     * @param c is the column for the character to go into
     * @pre checkIfFree() returned true
     * @post p is placed into the correct c and is placed at the bottom of the row.
     * No other column or row is affected by the change
     */
    public void placeToken(char p, int c) {}

    /**
     * this function will check to see if the last token placed in column c resulted
     * in the player winning the game. If so it will return true, otherwise false.
     * @param c is the column that is the last token was placed into
     * @return returns a boolean based on either the player won or not
     * @post checkForWin() = true iff (checkHorizWin == true) OR
     * (checkVertWin == true) OR (checkDiagWin == true) AND
     * checkForWin() = False iff (checkHorizWin == False) OR
     * (checkVertWin == False) OR (checkDiagWin == False)
     */
    public boolean checkForWin(int c) {}

    /**
     * this function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining.
     * It will return true if the game is tied and false otherwise.
     * @return returns a boolean based on either the player winning or not
     * @post checkTie() == False iff [(checkHorizWin == False) OR
     * (checkVertWin == False) OR (checkDiagWin == False)]
     * AND [checkIfFree==False for all position]
     */
    public boolean checkTie() {}

    /**
     * checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row horizontally. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post The board positions are not changed for row and column
     */
    public boolean checkHorizWin(BoardPosition pos, char p) {}

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row vertically. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post The board positions are not changed for row and column
     */
    public boolean checkVertWin(BoardPosition pos, char p) {}

    /**
     * checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in 5 in a row diagonally. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post The board positions are not changed for row and column
     */
    public boolean checkDiagWin(BoardPosition pos, char p) {}

    /**
     * returns what is in the GameBoard at position pos
     * If no marker is there, it returns a blank space char.
     * @param pos the position which we want to know
     * @return char
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post the board positions are not changed and char is
     * returned with correct value at pos
     */
    public char whatsAtPos(BoardPosition pos) {}

    /**
     * Returns true if the player is at pos; otherwise, it returns false
     * @param pos the position which we want ot know
     * @param player the player we are checking for
     * @return boolean
     * @post the board positions are not changed and isPlayerAtPos == true if
     * [char == player] AND isPlayerAtPos if [char != player]
     */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {}

    /**
     * This will return a string with an easy game board to print to the screen
     * @return string
     * @post Game board remains unchanged. GameBoard is formatted correctly
     */
    @Override
    public String toString(){}
}
