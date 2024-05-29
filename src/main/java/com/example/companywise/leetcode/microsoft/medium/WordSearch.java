package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * <p>
 * Example 3:
 * <p>
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 * <p>
 * Leetcode link : https://leetcode.com/problems/word-search/description/
 */
public class WordSearch {

    /**
     * Approach
     * <p>
     * Iterate through each cell in the grid.
     * For each cell, start a DFS search to explore all possible paths to construct the word.
     * During DFS, mark visited cells to avoid revisiting them and backtrack when a path leads to a dead end.
     * If the word is successfully constructed during DFS, return true; otherwise, return false.
     * <p>
     * Time complexity:
     * <p>
     * Let m be the number of rows in the grid and n be the number of columns.
     * In the worst-case scenario, the DFS traversal explores all cells in the grid.
     * Since there are m * n cells, the time complexity is O(m * n * 4^L),
     * where L is the length of the word. The factor of 4^L comes from the four possible directions
     * (up, down, left, right) at each step of the DFS.
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, 0, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int index, int i, int j) {
        if (index == word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        char temp = board[i][j];
        board[i][j] = '#'; // Mark as visited

        boolean found = dfs(board, word, index + 1, i + 1, j) ||
                dfs(board, word, index + 1, i - 1, j) ||
                dfs(board, word, index + 1, i, j + 1) ||
                dfs(board, word, index + 1, i, j - 1);

        board[i][j] = temp; // Restore the original value
        return found;
    }
}
