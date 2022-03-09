package cpsc2150.extendedConnectX.models;

import java.util.Scanner;

public class GameScreen {
    private static GameBoard board = new GameBoard();

    public static void main(String[] args){
        boolean gameState = true;
        int counter = 0;
        char[] player = {'X', 'O'};
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String scan;
        int userInput = 0;

        //game loop
        while(gameState){
            System.out.println(board.toString());
            while(!valid) {
                System.out.println("Player " + player[counter % 2] + " what column do you want to place your marker in?");
                scan = scanner.nextLine();
                userInput = Integer.parseInt(scan);
                valid = checkPlayerInput(userInput);
                //check if column is full if valid input
                if(valid && board.checkIfFree(userInput)){
                    System.out.println("Column is full");
                    valid = false;
                }
            }
            //set valid back to false
            valid = false;
            board.placeToken(player[counter % 2], userInput);
            if(board.checkForWin(userInput)) {
                System.out.println("Player " + player[counter % 2] + " Won!");
                counter = -1;
                gameState = playAgain();
                board = new GameBoard();
            }
            else if(board.checkTie()){
                System.out.println("It was a tie!");
                counter = -1;
                gameState = playAgain();
                board = new GameBoard();
            }
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
    public static boolean checkPlayerInput(int column){
        //checks columns
        if(column < 0 ){
            System.out.println("Column cannot be less than 0");
            return false;
        }
        else if(column >= board.MAX_COLUMN) {
            System.out.println("Column cannot be greater than " + (board.MAX_COLUMN - 1));
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
    public static boolean playAgain() {
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
        if(user == 'Y' || user == 'y'){
            return true;
        }
        return false;
    }
}
