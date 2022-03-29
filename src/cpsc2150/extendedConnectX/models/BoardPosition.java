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
    private final int row;
    private final int column;

    /**
     * This is the constructor for the BoardPosition class.
     * Will initialize the current row and column position
     * @param row is what we set #row to
     * @param column is what we set #column to
     * @pre
     * (0 <= pRow <= MAX_ROW)
     * (0 <= pColumn <= MAX_COLUMN)
     * @post
     * row = pRow
     * column = pColumn
     */
    public BoardPosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * returns the integer value of row
     * @return row
     * @post getRow() = [return row]
     * row and column do not change
     */
    public int getRow() {
        return row;
    }

    /**
     * returns the integer value of column
     * @return column
     * @post getColumn() = [return column]
     * row and column do not change
     */
    public int getColumn() {
        return column;
    }

    /**
     * Checks to see if two BoardPositions are equal
     * @return boolean based on if BoardPositions are equal or not
     * @param obj which is a board position
     * @pre
     * BP.row >= 0
     * BP.column >= 0
     * @post
     * equals = true iff (BoardPosition.row == BP.row) && (BoardPosition.column == BP.column) AND
     * equals = false iff ((BoardPosition.row != BP.row) && (BoardPosition.column == BP.column) AND
     * equals = false iff ((BoardPosition.row == BP.row) && (BoardPosition.column != BP.column)
     * row and column do not change
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != getClass()) return false;
        BoardPosition BP = (BoardPosition) obj;
        return this.row == BP.getRow() && this.column == BP.getColumn();
    }

    /**
     * Returns a string in the format "<row>,<column>" of the current board position values
     * @return string of the format "<row>,<column>"
     * @post toString = [string of formatted like "<row>, <column>"]
     * row and columns do not change
     */
    @Override
    public String toString() {
        return row + "," + column;
    }
}
