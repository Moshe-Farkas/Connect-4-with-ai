package game;

import java.util.Random;

public class Game {
    public boolean isFirstPlayerTurn;      //true if first player. false if second
    public int[][] board;          // 0 == empty space. 1 == taken by player one. 2 == taken by player 2
    public int lastColumn;
    public int lastRow;

    public Game() {
        board = new int[6][7];
        InitBoard();
        Random rand = new Random();
        isFirstPlayerTurn = rand.nextInt() % 2 == 0;       // chooses random player to start
    }

    public void InitBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public boolean placePiece(int column) {
        int rowPointer = 0;
        while (true) {
            if (board[0][column] != 0) {
                return false;
            }
            lastColumn = column;
            if (rowPointer == board.length) {
                board[rowPointer - 1][column] = getTurnVal();
                lastRow = rowPointer - 1;
                return true;
            }

            if (board[rowPointer][column] != 0) {
                board[rowPointer - 1][column] = getTurnVal();
                lastRow = rowPointer - 1;
                return true;

            }
            rowPointer++;
        }
    }

    private void SwitchPlayer() {
        isFirstPlayerTurn = !isFirstPlayerTurn;
    }

    private int getTurnVal() {
        if (isFirstPlayerTurn) {
            return 1;
        } else {
            return 2;
        }
    }

    private int CheckForWinner(int column) {
        int winner = checkVertically();
        if (winner != 0)
            return winner;
        return 0;
    }

    private int CheckHorizantlly() {
        int target = getTurnVal();
        int counter = 0;
        for (int i = 0; i < board[0].length; i++) {
            if (board[lastRow][i] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4)
                return target;
        }

        return 0;
    }

    private int checkVertically() {
        int target = getTurnVal();
        int counter = 0;
        for(int i = 0; i < board.length; i++) {
            if (board[i][lastColumn] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4)
                return target;
        }
        return 0;
    }
    private int checkDiagnally(){



        return 0;
    }


}
