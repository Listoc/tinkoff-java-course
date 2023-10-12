package edu.hw1;

public class Task8 {

    public static boolean knightBoardCapture(int[][] board) {
        final int BOARD_SIZE = 8;
        if (board == null) {
            throw new IllegalArgumentException("Null input");
        }

        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException("Wrong array size");
        }

        for (var el : board) {
            if (el.length != BOARD_SIZE) {
                throw new IllegalArgumentException("Wrong element size");
            }
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == 0) {
                    continue;
                }

                if (isBeatOtherKnights(board, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isBeatOtherKnights(int[][] board, int i, int j) {
        return isBeatSpecificKnight(board, i - 2, j - 1)
                || isBeatSpecificKnight(board, i - 2, j + 1)
                || isBeatSpecificKnight(board, i - 1, j - 2)
                || isBeatSpecificKnight(board, i - 1, j + 2)
                || isBeatSpecificKnight(board, i + 1, j - 2)
                || isBeatSpecificKnight(board, i + 1, j + 2)
                || isBeatSpecificKnight(board, i + 2, j - 1)
                || isBeatSpecificKnight(board, i + 2, j + 1);
    }


    private static boolean isBeatSpecificKnight(int[][] board, int i, int j) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board.length - 1) {
            return false;
        }

        return board[i][j] == 1;
    }

    private Task8() {
    }
}
