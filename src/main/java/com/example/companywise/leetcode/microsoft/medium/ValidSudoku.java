package com.example.companywise.leetcode.microsoft.medium;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 *     Each row must contain the digits 1-9 without repetition.
 *     Each column must contain the digits 1-9 without repetition.
 *     Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 *     A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *     Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 *
 * Example 1:
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 *
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 *
 * Constraints:
 *
 *     board.length == 9
 *     board[i].length == 9
 *     board[i][j] is a digit 1-9 or '.'.
 *
 *
 *  Leetcode link : https://leetcode.com/problems/valid-sudoku/description/
 */
public class ValidSudoku {

        public boolean isValidSudoku(char[][] board) {
            return isValidRows(board) && isValidColumns(board) && isValidSubgrids(board);
        }

        private boolean isValidRows(char[][] board) {
            for (int row = 0; row < 9; row++) {
                if (!isValidSet(board[row])) {
                    return false;
                }
            }
            return true;
        }

        private boolean isValidColumns(char[][] board) {
            for (int col = 0; col < 9; col++) {
                char[] column = new char[9];
                for (int row = 0; row < 9; row++) {
                    column[row] = board[row][col];
                }
                if (!isValidSet(column)) {
                    return false;
                }
            }
            return true;
        }

        private boolean isValidSubgrids(char[][] board) {
            for (int rowStart = 0; rowStart < 9; rowStart += 3) {
                for (int colStart = 0; colStart < 9; colStart += 3) {
                    char[] subgrid = new char[9];
                    int index = 0;
                    for (int row = rowStart; row < rowStart + 3; row++) {
                        for (int col = colStart; col < colStart + 3; col++) {
                            subgrid[index++] = board[row][col];
                        }
                    }
                    if (!isValidSet(subgrid)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValidSet(char[] set) {
            boolean[] seen = new boolean[10];
            for (char c : set) {
                if (c != '.') {
                    int num = c - '0';
                    if (seen[num]) {
                        return false;
                    }
                    seen[num] = true;
                }
            }
            return true;
        }

}
