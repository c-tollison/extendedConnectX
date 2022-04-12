package cpsc2150.extendedConnectX.models;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGameBoardMem {
    private IGameBoard MakeAGameBoard(int row, int column, int numberToWin ){
        return new GameBoardMem(row, column, numberToWin);
    }

    //helper to verify with toString of GameBoard
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
                BoardPosition pos = new BoardPosition(i, j);
                gameBoard += board[i][j] + " |";
            }
            gameBoard += "\n";
        }

        return gameBoard;
    }
}
