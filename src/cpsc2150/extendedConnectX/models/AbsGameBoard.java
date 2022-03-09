package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard{

    /**
     * This will return a string with an easy game board to print to the screen
     * @return string
     * @post Game board remains unchanged. GameBoard is formatted correctly
     */
    @Override
    public String toString(){
        //create initial row with columns number
        String gameBoard = "|";
        for(int i = 0; i < MAX_COLUMN; i++){
            gameBoard += i + "|";
        }
        gameBoard += "\n";

        //access using primary methods and print board
        for(int i = 0; i < MAX_ROW; i++){
            gameBoard += "|";
            //each column print character and line
            for(int j = 0; j < MAX_COLUMN; j++){
                BoardPosition pos = new BoardPosition(i,j);
                gameBoard += whatsAtPos(pos) + "|";
            }
            gameBoard += "\n";
        }

        return gameBoard;
    }

}
