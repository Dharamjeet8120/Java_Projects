package com.TicGame;

public class TicTacToe {

    private char[] board;

    public TicTacToe() {
        resetBoard();
    }

    public void resetBoard() {

        board = new char[]{
                '1', '2', '3',
                '4', '5', '6',
                '7', '8', '9'
        };
    }

    public char[] getBoard() {
        return board;
    }

    public boolean isValidMove(int position) {

        return position >= 0
                && position < 9
                && board[position] != 'X'
                && board[position] != 'O';
    }

    public void makeMove(int position, char symbol) {

        board[position] = symbol;
    }

    public boolean checkWin(char symbol) {

        int[][] winningPatterns = {

                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},

                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},

                {0, 4, 8},
                {2, 4, 6}
        };

        for (int[] pattern : winningPatterns) {

            if (board[pattern[0]] == symbol
                    && board[pattern[1]] == symbol
                    && board[pattern[2]] == symbol) {

                return true;
            }
        }

        return false;
    }

    public boolean isDraw() {

        for (char position : board) {

            if (position != 'X' && position != 'O') {
                return false;
            }
        }

        return true;
    }
}