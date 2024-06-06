package com.example.companywise.leetcode.microsoft.easy;

/**
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
 * <p>
 * Two rectangles overlap if the area of their intersection is positive. To be clear, two rectangles that only touch at the corner or edges do not overlap.
 * <p>
 * Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rec1.length == 4
 * rec2.length == 4
 * -109 <= rec1[i], rec2[i] <= 109
 * rec1 and rec2 represent a valid rectangle with a non-zero area.
 * <p>
 * Leetcode link : https://leetcode.com/problems/rectangle-overlap/description/
 */
public class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // rec 1 : x11,y11,x12,y12
        //rec 2 : x21,y21,x22,y22
        // x11 <  x22 && x12 > x21
        // y11 < y22 && y12 > y21

        return rec1[0] < rec2[2]
                && rec1[1] < rec2[3]
                && rec1[2] > rec2[0]
                && rec1[3] > rec2[1];
    }
}
