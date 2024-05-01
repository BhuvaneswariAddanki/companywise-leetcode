package com.example.companywise.leetcode.microsoft.medium;

/**
 * Design Tic-Tac-Toe
 * <p>
 * Design
 * <p>
 * Medium
 * <p>
 * Design a Tic-tac-toe game that is played between two players on anxngrid.
 * <p>
 * You may assume the following rules:
 * <p>
 * A move is guaranteed to be valid and is placed on an empty block.
 * <p>
 * Once a winning condition is reached, no more moves is allowed.
 * <p>
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * <p>
 * Example:
 * <p>
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 * <p>
 * TicTacToe toe = new TicTacToe(3);
 * <p>
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * <p>
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * <p>
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * <p>
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * <p>
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * <p>
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * <p>
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * <p>
 * Follow up:
 * Could you do better than O(n^2) permove()operation?
 * <p>
 * Could you get O(1) per move() operation?
 * <p>
 * Leetcode link :https://aaronice.gitbook.io/lintcode/data_structure/design-tic-tac-toe
 * https://leetcode.ca/all/348.html
 * <p>
 * geeksforgeeks : https://www.geeksforgeeks.org/low-level-design-of-tic-tac-toe-system-design/
 */
public class DesignTicTacToe {
    private char[][] board;
    private static char X = 'X';
    private static char O = 'O';
    private int size;

    /** Initialize your data structure here. */
    /**
     * Your TicTacToe object will be instantiated and called as such:
     * TicTacToe obj = new TicTacToe(n);
     * int param_1 = obj.move(row,col,player);
     */
    public DesignTicTacToe(int n) {
        board = new char[n][n];
        size = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     *
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        char c;
        if (player == 1) {
            c = X;
        } else {
            c = O;
        }
        if (board[row][col] != 0) {
            // throw error, occupied
            return 0;
        }
        board[row][col] = c;

        if (hasWon(row, col, size, c)) {
            return player;
        }
        return 0;
    }

    private boolean hasWon(int row, int col, int n, char c) {

        // check horizontal
        boolean rowLine = true;
        for (int i = 0; i < n; i++) {
            rowLine = rowLine && (board[i][col] == c);
        }
        // check vertical
        boolean colLine = true;
        for (int j = 0; j < n; j++) {
            colLine = colLine && (board[row][j] == c);
        }
        // check diagonal
        if (row + col == n - 1 || row == col) {
            boolean diagLine1 = true;
            boolean diagLine2 = true;
            for (int j = 0; j < n; j++) {
                diagLine1 = diagLine1 && (board[j][j] == c);
            }
            for (int j = 0; j < n; j++) {
                diagLine2 = diagLine2 && (board[n - 1 - j][j] == c);
            }
            return rowLine || colLine || diagLine1 || diagLine2;
        } else {
            return rowLine || colLine;
        }
    }

    /***
     * The key observation is that in order to win Tic-Tac-Toe you must have the entire row or column.
     * Thus, we don't need to keep track of an entire n^2 board.
     * We only need to keep a count for each row and column.
     * If at any time a row or column matches the size of the board then that player has won.
     *
     * To keep track of which player, I add one for Player1 and -1 for Player2.
     * There are two additional variables to keep track of the count of the diagonals.
     * Each time a player places a piece we just need to check the count of that row, column, diagonal and anti-diagonal.
     */

    public class TicTacToe {
        private int[] rows;
        private int[] cols;
        private int diagonal;
        private int antiDiagonal;

        /**
         * Initialize your data structure here.
         */
        public TicTacToe(int n) {
            rows = new int[n];
            cols = new int[n];
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         *
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either:
         * 0: No one wins.
         * 1: Player 1 wins.
         * 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            int size = rows.length;
            int toAdd = player == 1 ? 1 : -1;
            int target = (player == 1) ? size : -size;

            rows[row] += toAdd;
            cols[col] += toAdd;
            if (row == col) {
                diagonal += toAdd;
            }

            if (col + row == cols.length - 1) {
                antiDiagonal += toAdd;
            }


            if (rows[row] == target ||
                    cols[col] == target ||
                    diagonal == target ||
                    antiDiagonal == target) {
                return player;
            }

            return 0;
        }
    }


}

