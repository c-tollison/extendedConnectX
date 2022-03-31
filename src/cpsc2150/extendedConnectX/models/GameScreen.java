package cpsc2150.extendedConnectX.models;

import java.util.Scanner;

public class GameScreen {
    static final int MAX_PLAYER = 10;
    static final int MIN_PLAYER = 2;

    public static void main(String[] args){
        //initialize the GameBoard and get input
        int players = amountPlayers();
        char[] tokens = assignTokens(players);
        IGameBoard board = createBoard();
        boolean gameState = true;
        int counter = 0;
        boolean valid = false;
        int userInput = 0;
        Scanner scanner = new Scanner(System.in);
        String scan;

        //game loop
        System.out.println(board);
        while(gameState){
            while(!valid) {
                //always prints the write player using remainder
                System.out.println("Player " + tokens[counter % players] + " what column do you want to place your marker in?");
                scan = scanner.nextLine();
                userInput = Integer.parseInt(scan);
                //set to true means the input was valid
                valid = checkPlayerInput(userInput, board);
                //check if column is full if valid input
                if(valid && board.checkIfFree(userInput)){
                    System.out.println("Column is full");
                    //back to false because it was valid input but the space wasn't free
                    valid = false;
                }
            }
            //set valid back to false for next player input
            valid = false;
            board.placeToken(tokens[counter % players], userInput);
            System.out.println(board);
            //check for a win
            if(board.checkForWin(userInput)) {
                System.out.println("Player " + tokens[counter % players] + " Won!");
               //back to one to reset counter for next game
                counter = -1;
                gameState = playAgain();
                //if the gameState is true that means they want to play again
                if(gameState){
                    //reset the gameboard
                    players = amountPlayers();
                    tokens = assignTokens(players);
                    board = createBoard();
                    System.out.println(board);
                }
            }
            //check for a tie
            else if(board.checkTie()){
                System.out.println("It was a tie!");
                //back to one to reset counter for next game
                counter = -1;
                gameState = playAgain();
                //if the gameState is true that means they want to play again
                if(gameState){
                    //reset the gameboard
                    players = amountPlayers();
                    tokens = assignTokens(players);
                    board = createBoard();
                    System.out.println(board);
                }
            }
            //increment after every turn because its next players turn
            counter++;
        }

    }

    /**
     * checks to see if player input is valid for column
     * @param column for which they are trying to insert
     * @return boolean based on if the input is valid or not
     * @pre the GameBoard exists
     * @post checkPlayerInput = [true if 0 <= column < board.MAX_COLUMN] and
     * checkPlayerInput = [False if column < 0 or column >= board.MAX_COLUMN]
     */
    private static boolean checkPlayerInput(int column, IGameBoard board){
        //checks columns
        if(column < 0 ){
            System.out.println("Column cannot be less than 0");
            return false;
        }
        else if(column >= board.getNumColumns()){
            System.out.println("Column cannot be greater than " + (board.getNumColumns() - 1));
            return false;
        }
        return true;
    }

    /**
     * checks to see if players want to play again
     * @return boolean based on if they want to play or not
     * @pre the GameBoard exists and GameState is false meaning someone won
     * @post playAgain = [true if player chooses yes] AND
     * playAgain = [false if player chooses no]
     */
    private static boolean playAgain() {
        Scanner input = new Scanner(System.in);
        String in;
        char user = ' ';
        //check for correct input
        while (user != 'Y' && user != 'N' && user != 'y' && user != 'n'){
            System.out.println("Would you like to play again? Y/N");
            in = input.nextLine();
            user = in.charAt(0);
        }
        //checks input
        return user == 'Y' || user == 'y';
    }

    /**
     * This function allows us to gather all data from user about the next game
     * and choose what type of game we want to play
     * @return initialized implementation of IGameBoard
     * @post the GameBoard is initialized
     * createBoard = new GameBoard iff [choice == F || f] AND
     * createBoard = new GameBoardMem iff [choice == M || m]
     */
    private static IGameBoard createBoard(){
        //initialize the numbers for constructor
        int rows = amountRows();
        int columns = amountColumns();
        int numToWin = amountNumToWin(rows, columns);
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        char choice = ' ';
        //get user input
        while(choice != 'F' && choice != 'f' && choice != 'M' && choice != 'm'){
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game(M/m)?");
            scan = scanner.nextLine();
            choice = scan.charAt(0);
            if(choice != 'F' && choice != 'f' && choice != 'M' && choice != 'm'){
                System.out.println("Please enter F or M");
            }
        }

        if(choice == 'F' || choice == 'f'){
            return new GameBoard(rows, columns, numToWin);
        }
        else return new GameBoardMem(rows, columns, numToWin);

    }

    /**
     * The functions gather the amount of players that will participate
     * @return the number of players in this game
     * @post amountPlayers = [the amount of people scanned in by the user]
     */
    private static int amountPlayers() {
        int players = 0;
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        //start to read in data for player
        while(players > MAX_PLAYER || players < MIN_PLAYER) {
            System.out.println("How many players?");
            scan = scanner.nextLine();
            players = Integer.parseInt(scan);
            //too high
            if (players > MAX_PLAYER) {
                System.out.println("Must be " + MAX_PLAYER + " players or fewer");
            }
            //too low
            if (players < MIN_PLAYER) {
                System.out.println("Must be at least " + MIN_PLAYER + " players");
            }
        }
        return players;
    }

    /**
     * The functions creates a char array with all the players tokens
     * @param players = the number of players in this game
     * @return the tokens that the players choose
     * @post assignTokens = [char[] based on user choices]
     */
    private static char[] assignTokens(int players){
        char[] tokens = new char[players];
        //fill array with blank chars for searching later
        for(int i = 0; i < players; i++){
            tokens[i] = ' ';
        }
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        int counter = 1;
        //this will go until we have a char for each character
        while(counter <= players){
            boolean exist = false;
            System.out.println("Enter the character to represent player " + counter);
            scan = scanner.nextLine();
            char token = scan.charAt(0);
            token = Character.toUpperCase(token);
            //check to see if the token exists within the array
            for(int i = 0; i < players; i++){
                if(tokens[i] == token){
                    System.out.println(token + " is already taken as a player token!");
                    exist = true;
                    break;
                }
            }
            //that char did not exist so assign and increment
            if(!exist){
                tokens[counter - 1] = token;
                counter++;
            }
        }

        return tokens;
    }

    /**
     * The functions gathers the amount of rows the user wants to play with
     * @return the number of rows that will be used in this game
     * @post amountRows = [number of rows]
     */
    private static int amountRows() {
        int rows = 0;
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        //checks to make sure that rows falls within range
        while (rows > IGameBoard.MAX_ROW || rows < IGameBoard.MINIMUM) {
            System.out.println("How many rows should be on the board?");
            scan = scanner.nextLine();
            rows = Integer.parseInt(scan);
            if (rows < IGameBoard.MINIMUM) {
                System.out.println("The number of rows need to be greater than " + IGameBoard.MINIMUM);
            }
            if (rows > IGameBoard.MAX_ROW) {
                System.out.println("The number of rows needs to be less than or equal to " + IGameBoard.MAX_ROW);
            }

        }

        return rows;
    }

    /**
     * The functions gathers the amount of columns the user wants to play with
     * @return the number of columns that will be used in this game
     * @post amountColumns = [number of Columns]
     */
    private static int amountColumns() {
        int columns = 0;
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        //checks to make sure that columns falls within range
        while(columns > IGameBoard.MAX_COLUMN || columns < IGameBoard.MINIMUM){
            System.out.println("How many columns should be on the board?");
            scan = scanner.nextLine();
            columns = Integer.parseInt(scan);
            if(columns < IGameBoard.MINIMUM){
                System.out.println("The number of columns need to be greater than " + IGameBoard.MINIMUM);
            }
            if(columns > IGameBoard.MAX_COLUMN){
                System.out.println("The number of columns needs to be less than or equal to " + IGameBoard.MAX_COLUMN);
            }

        }
        return columns;
    }

    /**
     * The functions gathers the amount of tokens it takes in a row to get a win
     * @param rows = the number of rows of the baord
     * @param columns = the number of columns of the board
     * @return the number of tokens it takes to win
     * @post amountNumToWin = [number of tokens to win from user]
     */
    private static int amountNumToWin(int rows, int columns) {
        int numToWin = 0;
        //scanners
        Scanner scanner = new Scanner(System.in);
        String scan;
        //checks to make sure that numsToWin falls within range
        while(numToWin > rows || numToWin > columns || numToWin < IGameBoard.MINIMUM || numToWin > IGameBoard.MAX_NUM_TO_WIN){
            System.out.println("How many in a row to win?");
            scan = scanner.nextLine();
            numToWin = Integer.parseInt(scan);
            if(numToWin > rows){
                System.out.println("The number in a row to win cannot be more than the number of rows: " + rows);
            }
            if(numToWin > columns){
                System.out.println("The number in a row to win cannot be more than the number of columns: " + columns);
            }
            if(numToWin < IGameBoard.MINIMUM){
                System.out.println("The number in a row to win cannot be less than " + IGameBoard.MINIMUM);
            }
            if(numToWin > IGameBoard.MAX_NUM_TO_WIN){
                System.out.println("The number in a row to win cannot be greater than " + IGameBoard.MAX_NUM_TO_WIN);
            }
        }
        return numToWin;
    }
}
