package com.example.companywise.leetcode.microsoft.hard;

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * <p>
 * Example 3:
 * <p>
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * <p>
 * Leetcode link : https://leetcode.com/problems/regular-expression-matching/description/
 * geekforgeeks : https://www.geeksforgeeks.org/implementing-regular-expression-matching/
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "aa", p = "a";
        RegularExpressionMatching obj = new RegularExpressionMatching();
        System.out.print("result match : " + obj.isMatch(s,p));
    }


    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i][j-2];
                    if (p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
    public boolean isMatchExactPSMatch(String s, String p) {

        int index1 = 0;
        int index2 = 0;
        while (index1 < s.length() && index2 < p.length()) {
            if (p.charAt(index2) == '.' || s.charAt(index1) == s.charAt(index2)) {
                index1++;
                index2++;
            } else if (p.charAt(index2) == '*') {
                if (index2 == p.length() - 1) {
                    return true;
                }
                index2++;
                if (p.charAt(index2) == '.') {
                    if (index1 < s.length() - 1) {
                        index1++;
                    } else {
                        return false;
                    }
                    index2++;
                } else if (p.charAt(index2) != '*') {
                    while (index1 < s.length() && s.charAt(index1) != p.charAt(index2)) {
                        index1++;
                        index2++;
                    }
                    if (index1 < s.length() && s.charAt(index1) == p.charAt(index2)) {
                        index1++;
                        index2++;
                    } else {
                        return false;
                    }

                }

            }  else {
                return false;
            }
        }
        return index1 == s.length() && p.length() == index2;
    }
}
