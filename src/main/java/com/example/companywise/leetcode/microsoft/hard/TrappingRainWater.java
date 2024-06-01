package com.example.companywise.leetcode.microsoft.hard;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * <p>
 * Example 2:
 * <p>
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * <p>
 * Leetcode link : https://leetcode.com/problems/trapping-rain-water/description/
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int n = height.length;
        int leftMax = 0;
        int rightMax = 0;
        int trappedArea = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                if (leftMax <= height[left]) {
                    leftMax = height[left];
                }
                trappedArea += leftMax - height[left++];
            } else {
                if (rightMax <= height[right]) {
                    rightMax = height[right];
                }
                trappedArea += rightMax - height[right--];
            }
        }
        return trappedArea;
    }
}
