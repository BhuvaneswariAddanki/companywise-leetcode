package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 *
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 *
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     0 <= n <= 5 * 106
 *
 *
 * Leetcode link : https://leetcode.com/problems/count-primes/description/
 */
public class CountPrimes {
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                count++;
                for (int j = 2; j <= (n - 1) / i; j++) {
                    notPrimes[i * j] = true;
                }
            }
        }
        return count;
    }
}
