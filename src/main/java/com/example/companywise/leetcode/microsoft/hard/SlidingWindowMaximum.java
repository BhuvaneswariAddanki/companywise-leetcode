package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 * <p>
 * Leetcode link : https://leetcode.com/problems/sliding-window-maximum/description/
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 1, 2, 0, 5};
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int[] res = obj.maxSlidingWindow(nums, 3);
        for (int ele : res) {
            System.out.print(ele + "   ");
        }
    }

    //brute-force
    public int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] arr = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            arr[i] = max;
        }
        return arr;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int[] arr = new int[nums.length - k + 1];
        int in = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
           // Remove the indexes which are out of range from current window size
            while (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll();
            }
            // Remove the elements which are smaller than the current ele
            while (!dq.isEmpty() &&  nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offer(i);
            // Take the Leftmost element from the queue to get the maximum ele  in the window
            if (i >= k - 1) arr[in++] = nums[dq.peek()];
        }
        return arr;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int left = 0;
        int right = k - 1;
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        int[] ans = new int[nums.length - k + 1];
        while (right < nums.length) {
            if (left <= maxIndex) {
                if (nums[right] >= max) {
                    maxIndex = right;
                    max = nums[right];
                }
            } else if (nums[right] >= max - 1) {
                maxIndex = right;
                max = nums[right];
            } else if (nums[left] >= max - 1) {
                maxIndex = left;
                max = nums[left];
            } else {
                max = nums[left];
                for (int i = left + 1; i < right + 1; i++) {
                    if (nums[i] >= max) {
                        max = nums[i];
                        maxIndex = i;
                    }
                }
            }
            ans[left] = max;
            left++;
            right++;
        }
        return ans;
    }

}
