package com.example.companywise.leetcode.microsoft.medium;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 */
public class SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int key) {
        int row = 0 ;
        int col = matrix[0].length - 1;
        while(row<matrix.length && col >=0){
            if(key == matrix[row][col]){
                return true;
            }
            else if( key>matrix[row][col]){
                // Bottom movement
                row++;
            }
            else if(key<matrix[row][col]){
                // Left movement
                col--;
            }
        }
        return false;
    }
}
