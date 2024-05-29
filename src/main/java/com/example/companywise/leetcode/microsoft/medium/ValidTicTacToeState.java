package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 * <p>
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.
 * <p>
 * Here are the rules of Tic-Tac-Toe:
 * <p>
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: board = ["O  ","   ","   "]
 * Output: false
 * Explanation: The first player always plays "X".
 * <p>
 * Example 2:
 * <p>
 * Input: board = ["XOX"," X ","   "]
 * Output: false
 * Explanation: Players take turns making moves.
 * <p>
 * Example 3:
 * <p>
 * Input: board = ["XOX","O O","XOX"]
 * Output: true
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board.length == 3
 * board[i].length == 3
 * board[i][j] is either 'X', 'O', or ' '
 * Leetcode link : https://leetcode.com/problems/valid-tic-tac-toe-state/description/
 */
public class ValidTicTacToeState {

    public boolean validTicTacToe(String[] board) {
        int[] arr = new int[2];
        boolean xwin = false, owin = false;
        int xdiag = 0, odiag = 0;
        for (int i = 0; i < 3; i++) {
            int x = 0, o = 0;
            for (int j = 0; j < 3; j++) {
                if (i == 0) {
                    if (board[i].charAt(j) == 'X' && board[i + 1].charAt(j) == 'X' && board[i + 2].charAt(j) == 'X')
                        xwin = true;
                    if (board[i].charAt(j) == 'O' && board[i + 1].charAt(j) == 'O' && board[i + 2].charAt(j) == 'O')
                        owin = true;
                }
                if (board[i].charAt(j) == 'X') {
                    if (i == j) xdiag++;
                    arr[1]++;
                    x++;
                } else if (board[i].charAt(j) == 'O') {
                    if (i == j) odiag++;
                    arr[0]++;
                    o++;
                }
            }
            if (o == 3 && owin) return false;
            if (owin && xwin) return false;
            if (x == 3) xwin = true;
            else if (o == 3) owin = true;
        }

        if (xdiag == 3) xwin = true;
        if (odiag == 3) owin = true;

        if (board[0].charAt(2) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(0) == 'X') xwin = true;
        if (board[0].charAt(2) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(0) == 'O') owin = true;

        if (arr[0] >= arr[1] && xwin || (arr[1] > arr[0] && owin)) return false;
        if (arr[0] > arr[1] || Math.abs(arr[0] - arr[1]) > 1) return false;

        return !xwin || !owin;
    }
}
