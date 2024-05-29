package com.example.companywise.leetcode.microsoft.medium;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 * <p>
 * Leetcode link : https://leetcode.com/problems/rotate-image/description/
 */
public class RotateImage {
    /**
     * Intuition
     * <p>
     * Transpose the matrix by swapping elements along the main diagonal, then symmetrically flip it for the desired rotation.
     * <p>
     * 1  2  3
     * 4  5  6
     * 7  8  9
     * <p>
     * After transpose, it will be swap(matrix[i][j], matrix[j][i])
     * <p>
     * 1  4  7
     * 2  5  8
     * 3  6  9
     * <p>
     * Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])
     * <p>
     * 7  4  1
     * 8  5  2
     * 9  6  3
     * <p>
     * Approach
     * <p>
     * Transpose Step:
     * The transpose method swaps the elements across the main diagonal of the matrix.
     * It iterates through the upper triangle of the matrix (matrix[i][j] and matrix[j][i]) and exchanges the values.
     * Reverse Rows Step:
     * The reverseRows method then reverses each row of the transposed matrix.
     * For each row (matrix[r]), it uses two pointers (left and right) to swap elements symmetrically across the center.
     * Combination in rotate Method:
     * The rotate method combines these two steps to achieve a 90-degree clockwise rotation.
     * It first transposes the matrix and then reverses the rows of the transposed matrix.
     * <p>
     * Complexity
     * <p>
     * Time complexity:O(n∗n)O(n*n)O(n∗n)
     * <p>
     * Space complexity:O(1)O(1)O(1)
     * <p>
     * reference link : https://leetcode.com/problems/rotate-image/solutions/4500835/beats100-easiest-and-full-explained-java-c-python/
     *
     * @param matrix
     */
    private void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reverseRows(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            int left = 0;
            int right = matrix.length - 1;

            while (left < right) {
                int temp = matrix[r][left];
                matrix[r][left] = matrix[r][right];
                matrix[r][right] = temp;

                left++;
                right--;
            }
        }

    }

    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverseRows(matrix);
    }
}
