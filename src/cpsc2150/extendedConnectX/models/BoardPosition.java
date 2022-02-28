package cpsc2150.extendedConnectX.models;
/**
 * JavaDoc and Contracts for the class BoardPosition which is used to keep track
 * of cells of the game board.
 *
 * @author Carson Tollison
 * @version 1.0
 *
 * @invariant (0 <= row <= MAX_ROW) and (0 <= column <= MAX_COLUMN)
 */
public class BoardPosition {
    private int row;
    private int column;

    /**
     * This is the constructor for the BoardPosition class.
     * Will initialize the current row and column position
     *
     * @return void
     *
     * @pre
     * (0 <= pRow <= MAX_ROW)
     * (0 <= pColumn <= MAX_COLUMN)
     *
     * @post
     * row = pRow
     * column = pColumn
     */
    public void BoardPosition(int row, int column){}

    /**
     * returns the integer value of row
     *
     * @return row
     *
     * @post
     * returns row
     */
    public int getRow() {}

    /**
     * returns the integer value of column
     *
     * @return column
     *
     * @post
     * returns column
     */
    public int getColumn() {}

    /**
     * Checks to see if two BoardPositions are equal
     *
     * @return boolean
     *
     * @pre
     * BP.row >= 0
     * BP.column >= 0
     * @post
     * equals = true iff (BoardPosition.row == BP.row) && (BoardPosition.column == BP.column) AND
     * equals = false iff ((BoardPosition.row != BP.row) && (BoardPosition.column == BP.column) AND
     * equals = false iff ((BoardPosition.row == BP.row) && (BoardPosition.column != BP.column)
     */
    public boolean equals(BoardPosition BP) {}

    /**
     * Returns a string in the format "<row>,<column>" of the current board position values
     *
     * @return string of the format "<row>,<column>"
     *
     * @pre
     *
     * @post
     * "<row>,<column>" = row and column
     */
    @Override
    public String toString() {}

}
