package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [["Q"]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 9
 * <p>
 * Leetcode link : https://leetcode.com/problems/n-queens/description/
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<>();
        placeQueens(board, result, 0);
        return result;

    }

    private void placeQueens(char[][] board, List<List<String>> result, int row) {
        if (row == board.length) {
            result.add(construct(board));
            return;
        }
        for (int k = 0; k < board.length; k++) {
            if (isSafe(board, row, k)) {
                board[row][k] = 'Q';
                placeQueens(board, result, row + 1);
                board[row][k] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        // verify vertical line
        for (char[] chars : board) {
            if (chars[col] == 'Q') {
                return false;
            }
        }
        //Left diagonal
        int maxLeft = Math.min(row, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row - i][col - i] == 'Q') {
                return false;
            }
        }
        //Right diagonal
        int maxRight = Math.min(row, board.length - 1 - col);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row - i][col + i] == 'Q') {
                return false;
            }
        }
        return true;

    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] chars : board) {
            res.add(new String(chars));
        }
        return res;
    }

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];

        for (int i = 0; i < n; ++i)
            Arrays.fill(board[i], '.');

        dfs(n, 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], board, ans);
        return ans;
    }

    private void dfs(int n, int i, boolean[] cols, boolean[] diag1, boolean[] diag2, char[][] board,
                     List<List<String>> ans) {
        if (i == n) {
            ans.add(construct(board));
            return;
        }

        for (int j = 0; j < cols.length; ++j) {
            if (cols[j] || diag1[i + j] || diag2[j - i + n - 1])
                continue;
            board[i][j] = 'Q';
            cols[j] = diag1[i + j] = diag2[j - i + n - 1] = true;
            dfs(n, i + 1, cols, diag1, diag2, board, ans);
            cols[j] = diag1[i + j] = diag2[j - i + n - 1] = false;
            board[i][j] = '.';
        }
    }
}
