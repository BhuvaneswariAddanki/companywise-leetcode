package com.example.companywise.leetcode.microsoft.easy;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * <p>
 * Note that you must do this in-place without making a copy of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [0]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you minimize the total number of operations done?
 * <p>
 * Leetcode link : https://leetcode.com/problems/move-zeroes/description/
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int nextIndex = 0;
        int index = 0;
        while (index < nums.length) {
            if (nums[index] != 0) {
                nums[nextIndex++] = nums[index];
            }
            index++;
        }
        for (int i = nextIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
