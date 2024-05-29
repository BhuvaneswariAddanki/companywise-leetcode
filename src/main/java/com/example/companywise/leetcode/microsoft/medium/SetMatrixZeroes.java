package com.example.companywise.leetcode.microsoft.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/set-matrix-zeroes/description/
 */
public class SetMatrixZeroes {
    /**
     * Intuition
     * <p>
     * By storing the zero coordinates in a LinkedList, we can efficiently set zeros in the matrix without altering the original structure.
     * This approach allows us to handle zeros in a flexible manner and set them in rows and columns separately.
     * <p>
     * Approach
     * <p>
     * Create a LinkedList to store the coordinates of cells containing zeros.
     * Traverse the matrix to find zeros and add their coordinates to the LinkedList.
     * Iterate over the LinkedList and set zeros in the corresponding rows and columns in the matrix.
     * <p>
     * Complexity
     * <p>
     * Time complexity:O(m * n)
     * <p>
     * Space complexity:O(k)
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        List<int[]> zerosList = new LinkedList<>();

        // Traverse the matrix to find zeros and store their coordinates in the linked list
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 0) {
                    zerosList.add(new int[]{row, col});
                }
            }
        }

        // Set zeros in the matrix based on the coordinates stored in the linked list
        for (int[] zero : zerosList) {
            int zeroRow = zero[0];
            int zeroCol = zero[1];

            // Set zeros in the row
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[zeroRow][col] = 0;
            }

            // Set zeros in the column
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][zeroCol] = 0;
            }
        }
    }
}
