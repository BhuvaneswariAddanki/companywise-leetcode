package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
 * <p>
 * The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
 * <p>
 * The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Rectangle Area
 * <p>
 * Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * Output: 45
 * <p>
 * Example 2:
 * <p>
 * Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * Output: 16
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -104 <= ax1 <= ax2 <= 104
 * -104 <= ay1 <= ay2 <= 104
 * -104 <= bx1 <= bx2 <= 104
 * -104 <= by1 <= by2 <= 104
 * <p>
 * Leetcode link : https://leetcode.com/problems/rectangle-area/description/
 */
public class RectangleArea {

    /**
     * Simple area calculation of rectangle and checking for overlap and finding the overlap and subtracting the overlap.
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);
        int xMax = Math.max(ax1, bx1);
        int xMin = Math.min(ax2, bx2);
        int yMax = Math.max(ay1, by1);
        int yMin = Math.min(ay2, by2);
        int y = yMin - yMax;
        int x = xMin - xMax;

        int area3 = x * y;
        if (x < 0 || y < 0) area3 = 0;
        return area1 + area2 - area3;
    }


}
