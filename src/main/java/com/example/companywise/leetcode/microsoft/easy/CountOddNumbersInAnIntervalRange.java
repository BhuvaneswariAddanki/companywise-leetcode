package com.example.companywise.leetcode.microsoft.easy;

/**
 * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: low = 3, high = 7
 * Output: 3
 * Explanation: The odd numbers between 3 and 7 are [3,5,7].
 * <p>
 * Example 2:
 * <p>
 * Input: low = 8, high = 10
 * Output: 1
 * Explanation: The odd numbers between 8 and 10 are [9].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= low <= high <= 10^9
 * <p>
 * Leetcode link : https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/description/
 */
public class CountOddNumbersInAnIntervalRange {

    public int countOdds(int low, int high) {
        int noOfOdds = (high - low) / 2;
        return low % 2 != 0 || high % 2 != 0 ? noOfOdds + 1 : noOfOdds;
    }
}
