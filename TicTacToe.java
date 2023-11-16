
/**
 * Write a description of class TicTacToe here.
 *
 * @author Eric Shen
 * @version Nov 9
 */
import java.util.Scanner;
public class TicTacToe{
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';
    static int roundNum = 1;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean playGame = true;
        while(playGame) {
            playGame = false;
            roundNum=1;
            currentPlayer = 'X';
            assignBoard(board);
            while(noOneWin()&&roundNum<10) {
                System.out.println("Round: " + roundNum);
                gameSetUp(board);

                System.out.println(currentPlayer + ", make your move (row, col):");
                String move = input.nextLine();
                int row = Integer.parseInt(move.substring(0,1));
                int col = Integer.parseInt(move.substring(2));
                if(makeMove(row, col)){
                    System.out.println();
                    if(noOneWin()){
                        switchPlayer();
                        roundNum++;
                    } else {
                        gameSetUp(board);
                        System.out.println("Player-" + currentPlayer + " won!");
                        break;
                    }
                }
            }
            System.out.println("Play again? Y/N");
            String playAgain = input.nextLine();
            if("Y".equals(playAgain)) {playGame = true;
            } else {playGame = false;
            }
        }
    }

    public static char[][] assignBoard(char[][] board) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3;j++) {
                board[i][j]= ' ';
            }
        }
        return board;
    }

    public static char[][] gameSetUp(char[][] board) {
        for(int row = 0;row<board.length;row++) {
            for(int col = 0;col<board[0].length;col++) {
                System.out.print("[" + board[row][col]+"]");
            }
            System.out.println();
        }
        return board;
    }

    public static boolean noOneWin() {
        boolean win = true;
        if(checkWin()){
            win =false;
        }
        return win;
    }

    public static boolean makeMove(int row, int col) {
        if(row<0||col<0||row>=3||col>=3||board[row][col]=='X'||board[row][col]=='O') {
            System.out.println("Invalid move. Please try again.");
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    public static boolean checkWin() {
        boolean win=false;
        //check row
        for(int row = 0; row < board.length;row++) {
            if(currentPlayer==board[row][0]&&currentPlayer==board[row][1]&&currentPlayer==board[row][2])
                win=true;
        }
        //check column
        for(int col = 0; col < board.length;col++) {
            if(currentPlayer==board[0][col]&&currentPlayer==board[1][col]&&currentPlayer==board[2][col])
                win=true;
        }
        //check diagonal
        if((currentPlayer==board[0][0]&&currentPlayer==board[1][1]&&currentPlayer==board[2][2])
        ||(currentPlayer==board[0][2]&&currentPlayer==board[1][1]&&currentPlayer==board[2][0]))
            win = true;
        return win;
    }

    public static char switchPlayer() {
        if(roundNum%2==0) currentPlayer = 'X';
        else currentPlayer = 'O';
        return currentPlayer;
    }

}