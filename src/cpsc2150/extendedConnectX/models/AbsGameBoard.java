package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard{

    /**
     * This will return a string with an easy game board to print to the screen
     * @return string of a formatted gameboard which displays where players are
     * @post Game board remains unchanged. GameBoard is formatted correctly
     * toString = [formatted gameboard that's MAX_ROW by MAX_COLUMN size and displays player positions]
     */
    @Override
    public String toString(){
        //create initial row with columns number
        String gameBoard = "|";
        for(int i = 0; i < getNumColumns(); i++){
            gameBoard += i + "|";
        }
        gameBoard += "\n";

        //access using primary methods and print board
        for(int i = 0; i < getNumRows(); i++){
            gameBoard += "|";
            //each column print character and line
            for(int j = 0; j < getNumColumns(); j++){
                BoardPosition pos = new BoardPosition(i,j);
                gameBoard += whatsAtPos(pos) + "|";
            }
            gameBoard += "\n";
        }

        return gameBoard;
    }

}
