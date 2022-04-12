package cpsc2150.extendedConnectX.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JavaDoc and Contracts for the class GameBoard class which keeps track
 * of all the positions on the board using a hashmap
 * This class also checks for wins and this class will take in all user input.
 * @author Carson Tollison
 * @invariant (0 <= row < MAX_ROW) and (0 <= column < MAX_COLUMN)
 * @invariant Tokens can be any char but need to be uppercase if applicable
 * @invariant numberToWin cannot be greater than the rows or columns
 * @invariant No BoardPosition can be repeated on a key
 * @correspondence self = [hashmap of key to list, char gives access to a
 * list of values with board positions]
 * @correspondence Structure = [Map<Character, List<BoardPosition>>]
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    private final int rows;
    private final int columns;
    private final int numberToWin;
    private final Map<Character, List<BoardPosition>> map;
    /**
     * This is the constructor for the GameBoard class
     * @param rows = number of rows for game
     * @param columns = number of columns for game
     * @param numberToWin = number of tokens to win in a row
     * @post board is initialized to [MAX_ROW][MAX_COLUMN]
     * All positions are set to a blank character
     */
    public GameBoardMem(int rows, int columns, int numberToWin) {
        this.rows = rows;
        this.columns = columns;
        this.numberToWin = numberToWin;
        //initialize the board
        this.map = new HashMap<>();
    }

    @Override
    public void placeToken(char p, int c) {
        //any characters that we have not created a list for we need to create
        if(!map.containsKey(p)){
            map.put(p, new ArrayList<>());
        }
        //place a token by starting at bottom and moving up
        for(int i = 0; i < rows; i--){
            //if blank then place and break
            BoardPosition bp = new BoardPosition(i, c);
            char check = whatsAtPos(bp);
            //blank then ad that pos
            if(check == ' '){
                map.get(p).add(bp);
                break;
            }
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        //iterate through every key on the map
        for(Map.Entry<Character, List<BoardPosition>> m: map.entrySet()){
            //iterate through each list of the map
            for(int i = 0; i < m.getValue().size(); i++){
                //if the bp matches the one we are looking for then it exists
                if(m.getValue().get(i).equals(pos)){
                    return m.getKey();
                }
            }
        }
        //nothing found so must be nothing
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        //iterate through the list at that key
        for(int i = 0; i < map.get(player).size(); i++){
            //if the bp matches the one we are looking for then it exists
            if(map.get(player).get(i).equals(pos)){
                return true;
            }
        }
        //no value matched this value at this key
        return false;
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
