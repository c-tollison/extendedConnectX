package cpsc2150.extendedConnectX.models;

/**
 * A gameBoard containing characters
 * This is a gameboard for connectX
 * The gameboard is bounded by MAX_ROW and MAX_COLUMN
 * Initialization ensures:
 *      IGameBoard size is [MAX_ROW] by [MAX_COLUMN]
 * Constraints:
 *     every position needs to be inbetween 0 <= row < MAX_ROW
 *     AND  0 <= column < MAX_COLUMN
 */
public interface IGameBoard {

    static final int MAX_ROW = 6;
    static final int MAX_COLUMN = 9;
    static final int NUM_TO_WIN = 5;

    /**
     * returns true if the column can accept another token; false otherwise.
     * @param c is an int with the column the token is to be placed in
     * @return a boolean if the column is free or exists
     * @pre 0 <= c < MAX_Column
     * @post  checkIfFree() = true iff [top most row for that column position is taken]
     * Or False iff [top most row for that column position is not taken]
     * gameBoard = #gameBoard
     */
    default boolean checkIfFree(int c){
        BoardPosition pos = new BoardPosition(0, c);
        //checks top row
        if(whatsAtPos(pos) ==  ' ') {
            return true;
        }
        return false;
    }

    /**
     * this function will check to see if the last token placed in column c resulted
     * in the player winning the game. If so it will return true, otherwise false.
     * @param c is the column that is the last token was placed into
     * @pre c = [c is the column from the last play]
     * @return returns a boolean based on either the player won or not
     * @post checkForWin() = true iff (checkHorizWin == true) OR
     * (checkVertWin == true) OR (checkDiagWin == true) AND
     * checkForWin() = False iff (checkHorizWin == False) OR
     * (checkVertWin == False) OR (checkDiagWin == False)
     * theBoard = #theBoard
     */
    default boolean checkForWin(int c){
        int row = 0;
        char p = ' ';
        //until we found the last found piece
        for(int i = 0; i < MAX_ROW; i++){
            BoardPosition pos = new BoardPosition(0, c);
            p = whatsAtPos(pos);
            if(p != ' '){
                row = i;
            }
        }
        //pos we will be using in all checks
        BoardPosition pos = new BoardPosition(row, c);
        //check win condition
        if (checkHorizWin(pos, p) || checkVertWin(pos, p) || checkDiagWin(pos, p)) {
            return true;
        }

        return false;
    }

    /**
     * this function will check to see if the game has resulted in a
     * tie. A game is tied if there are no free board positions remaining.
     * It will return true if the game is tied and false otherwise.
     * @return returns a boolean based on either the player winning or not
     * @pre game has not been won
     * @post checkTie() == False iff [(checkHorizWin == False) OR
     * (checkVertWin == False) OR (checkDiagWin == False)]
     * AND [checkIfFree==False for all position]
     * theBoard = #theBoard
     */
    default boolean checkTie(){
        //check if any more placements possible in top row
        for(int i = 0; i < MAX_COLUMN; i++){
            BoardPosition pos = new BoardPosition(0,i);
            //if blank space then no tie
            if(whatsAtPos(pos) == ' '){
                return false;
            }
        }

        return true;
    }

    /**
     * checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in max number for win in a row horizontally. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean based on if it was a horizontal win or not
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * AND [Pos was the location were the marker was placed]
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post The board positions are not changed for row and column
     * checkHorizWin = true iff [last pos is the last to make max number for win in horizontal row]
     * OR false iff [last pos is not the last to make max number for win in horizontal row]
     * theBoard = #theBoard
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int counter = 0;
        int row = pos.getRow();
        //go through this row
        for(int i = 0; i < MAX_COLUMN; i++){
            //count if match up to 5
            BoardPosition newPos = new BoardPosition(row, i);
            if(isPlayerAtPos(newPos, p)){
                counter++;
                if(counter == NUM_TO_WIN){
                    return true;
                }
            }
            //no match, restart
            else {
                counter = 0;
            }
        }
        //no match
        return false;
    }

    /**
     * Checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in max number for win in a row vertically. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean based on if it was a vertical win or not
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * AND [Pos was the location were the marker was placed]
     * @post The board positions are not changed for row and column
     * checkVertWin = true iff [last pos is the last to make max number for win in vertical row]
     * OR false iff [last pos is not the last to make max number for win in vertical row]
     * theBoard = #theBoard
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int counter = 0;
        int column = pos.getColumn();
        //go through this column
        for(int i = MAX_ROW - 1; i >= 0; i--){
            //count if match up to 5
            BoardPosition newPos = new BoardPosition(i, column);
            if(isPlayerAtPos(newPos, p)){
                counter++;
                if(counter == NUM_TO_WIN){
                    return true;
                }
            }
            //no match, restart
            else {
                counter = 0;
            }
        }
        //no match
        return false;
    }

    /**
     * checks to see if the last token placed (which was placed in
     * position pos by player p) resulted in max number for win in a row diagonally. Returns
     * true if it does, otherwise false
     * @param pos the position of player p last token
     * @param p the last player to go
     * @return boolean based on if it was a Diag win or not
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * AND [Pos was the location were the marker was placed]
     * @pre BoardPosition.row AND BoardPosition.column exist
     * @post The board positions are not changed for row and column
     * checkDiagWin = true iff [last pos is the last to make max number for win in diagonal row]
     * OR false iff [last pos is not the last to make max number for win in diagonal row]
     * theBoard = #theBoard
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int counter = 0;
        //start from bottom and go left
        int offset = MAX_ROW - pos.getRow();
        int right_x = pos.getRow() + offset;
        int right_y = pos.getColumn() - offset;
        int left_x = pos.getRow() + offset;
        int left_y = pos.getColumn() + offset;

        //move right
        while(right_x >= 0 && right_x <  MAX_ROW && right_y >= 0 && right_y < MAX_COLUMN){
            BoardPosition newPos = new BoardPosition(right_x, right_y);
            if(isPlayerAtPos(newPos, p)){
                counter++;
                if(counter == NUM_TO_WIN){
                    return true;
                }
            }
            else {
                counter = 0;
            }
            right_x--;
            right_y++;
        }

        //move left
        while(left_x >= 0 && left_x <  MAX_ROW && left_y >= 0 && left_y < MAX_COLUMN){
            BoardPosition newPos = new BoardPosition(left_x, left_y);
            if(isPlayerAtPos(newPos, p)){
                counter++;
                if(counter == NUM_TO_WIN){
                    return true;
                }
            }
            else {
                counter = 0;
            }
            left_x--;
            left_y--;
        }

        //no match
        return false;
    }

    /**
     * Returns true if the player is at pos; otherwise, it returns false
     * @param pos the position which we want ot know
     * @param player the player we are checking for
     * @return boolean that represents if a player is at a boardposition or not
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @pre player = [valid character symbols]
     * @post the board positions are not changed and isPlayerAtPos == true if
     * [char == player] AND isPlayerAtPos if [char != player]
     * theBoard = #theBoard
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        if(whatsAtPos(pos) == player){
            return true;
        }
        return false;
    }

    /**
     * places the character p in column c. The token will
     * be placed in the lowest available row in column c.
     * @param p is the character from the player. 'X' or 'O'
     * @param c is the column for the character to go into
     * @pre checkIfFree() returned true
     * 0 <= c < MAX_COLUMN
     *
     * @post p is placed into the correct c and is placed at the bottom of the row.
     * No other column or row is affected by the change
     */
    public void placeToken(char p, int c);

    /**
     * returns what is in the GameBoard at position pos
     * If no marker is there, it returns a blank space char.
     * @param pos the position which we want to know
     * @return char representing which player is at a certain position
     * @pre 0 <= pos.getRow() < MAX_ROW AND 0 <= pos.getColumn() < MAX_COLUMN
     * @post the board positions are not changed and char is
     * returned with correct value at pos
     * theBoard = #theBoard
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * returns the max number of rows in the gameboard
     * @return number of rows
     * @post self = #self
     */
    public int getNumRows();

    /**
     * returns the max number of columns in the gameboard
     * @return number of columns
     * @post self = #self
     */
    public int getNumColumns();

    /**
     * returns the number of tokens in a row needed to win the game
     * @return number of tokens needed to win
     * @post self = #self
     */
    public int getNumToWin();
}
