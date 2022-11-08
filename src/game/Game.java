package game;

public class Game {
    public boolean isFirstPlayerTurn;      //true if first player. false if second
    public int[][] board;          // 0 == empty space. 1 == taken by player one. 2 == taken by player 2
    public int lastColumn;
    public int lastRow;

    public Game() {
        board = new int[4][8];
        InitBoard();
        isFirstPlayerTurn = true;       // chooses random player to start
    }

    public void InitBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int play(int column) {
        lastColumn = column;
        if (column >= board[0].length)
            return -1;
        if (!placePiece())
            return -1;
        printBoard();
        int winner = CheckForWinner();
        isFirstPlayerTurn = !isFirstPlayerTurn;
        return winner;
    }

    public void printBoard() {
        if (isFirstPlayerTurn)
            System.out.println("first player turn ");
        else
            System.out.println("second player turn ");
        int i;
        for (i = 0; i < board[0].length - 1; i++) {
            System.out.print(i + "  ");
        }
        System.out.println(i++);
        for (i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0)
                    System.out.print(" , ");
                else
                    System.out.print(board[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public boolean placePiece() {
        int rowPointer = 0;
        while (true) {
            if (board[0][lastColumn] != 0) {
                return false;
            }
            if (rowPointer == board.length) {
                board[rowPointer - 1][lastColumn] = getTurnVal();
                lastRow = rowPointer - 1;
                return true;
            }
            if (board[rowPointer][lastColumn] != 0) {
                board[rowPointer - 1][lastColumn] = getTurnVal();
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

    private int CheckForWinner() {
        int winner = CheckVertically();
        if (winner != 0)
            return winner;
        winner = CheckHorizontally();
        if (winner != 0)
            return winner;
        winner = CheckDiagonally1();
        if (winner != 0)
            return winner;
        winner = CheckDiagonally2();
        if (winner != 0)
            return winner;
        return 0;
    }

    private int CheckDiagonally1() {
        int FirstColumn = Math.max(0, lastRow + lastColumn - (board.length - 1));
        int FirstRow = lastRow + lastColumn - FirstColumn;
        int numOfSquares = Math.min(FirstRow + 1, board[0].length - FirstColumn);
        int target = getTurnVal();
        int counter = 0;
        int row = FirstRow;
        int column = FirstColumn;
        for (int i = 0; i < numOfSquares; i++) {
            if (board[row][column] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4)
                return target;
            row--;
            column++;
        }
        return 0;
    }

    private int CheckDiagonally2() {
        int FirstColumn = Math.max(0, lastColumn - lastRow);
        int FirstRow = Math.max(0, lastRow - lastColumn);
        int numOfSquares = Math.min(board.length - (FirstColumn + 1) * FirstRow, board[0].length - (FirstRow + 1) * FirstColumn);//it's working!
        int target = getTurnVal();
        int counter = 0;
        int row = FirstRow;
        int column = FirstColumn;
        for (int i = 0; i < numOfSquares; i++) {
            if (board[row][column] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4)
                return target;
            row++;
            column++;
        }
        return 0;
    }

    private int CheckHorizontally() {
        int target = getTurnVal();
        int counter = 0;
        for (int i = 0; i < board[0].length; i++) {
            if (board[lastRow][i] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4) return target;
        }
        return 0;
    }

    private int CheckVertically() {
        int target = getTurnVal();
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][lastColumn] == target)
                counter++;
            else
                counter = 0;
            if (counter == 4)
                return target;
        }
        return 0;
    }


}
