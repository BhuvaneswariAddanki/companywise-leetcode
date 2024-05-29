package com.example.companywise.leetcode.microsoft.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 * <p>
 * Leetcode link : https://leetcode.com/problems/sort-colors/description/
 */
public class SortColors {
    public void sortColors(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int i = 0;
        for (; i < map.getOrDefault(0, 0); i++) {
            nums[i] = 0;
        }
        int count = i + map.getOrDefault(1, 0);
        for (; i < count; i++) {
            nums[i] = 1;
        }
        count = i + map.getOrDefault(2, 0);
        for (; i < count; i++) {
            nums[i] = 2;
        }
    }
    public void sortColors2(int[] nums) {
        int n = nums.length, j = 0, k = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) {
                swap(nums, i, j);
                j++;
                if(k < j)
                    k++;
            }
            if(nums[i] == 1 && k < n) {
                swap(nums, i, k);
                k++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
