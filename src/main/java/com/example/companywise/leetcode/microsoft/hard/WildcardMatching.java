package com.example.companywise.leetcode.microsoft.hard;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 * <p>
 * Leetcode link : https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildcardMatching {

    /**
     * Here's a step-by-step explanation of your dynamic programming approach for wildcard pattern matching:
     * <p>
     * Create a 2D boolean array dp with dimensions (p.length()+1) x (s.length()+1). dp[i][j] will represent whether the pattern p starting from index i matches the input string s starting from index j.
     * <p>
     * Initialize the base cases:
     * For i = p.length() (when pattern is empty), and j = s.length() (when input is empty), dp[i][j] is set to true since an empty pattern matches an empty string.
     * For i = p.length() (when pattern is empty) and any valid j, dp[i][j] is set to false since a non-empty input string can't match an empty pattern.
     * For j = s.length() (when input is empty) and any valid i, you need to check if the pattern consists of only *. If yes, then it matches the empty string, otherwise, it doesn't match.
     * <p>
     * Fill in the dp array by considering the characters of the pattern and the input string one by one, starting from the last characters of each:
     * <p>
     * If p.charAt(i) is a ?, the result depends on whether the rest of the pattern (p[i+1:]) matches the rest of the input string (s[j+1:]). Therefore, dp[i][j] is set to dp[i+1][j+1].
     * <p>
     * If p.charAt(i) is a *, you have two choices:
     * You can consider the * as matching zero characters in the input string, i.e., dp[i][j] depends on dp[i+1][j].
     * You can consider the * as matching one or more characters in the input string, i.e., dp[i][j] depends on dp[i][j+1].
     * Therefore, dp[i][j] is set to dp[i+1][j] || dp[i][j+1].
     * <p>
     * If p.charAt(i) is a regular character and matches s.charAt(j), then the result depends on whether the rest of the pattern and input string match (dp[i][j] is set to dp[i+1][j+1]).
     * <p>
     * If p.charAt(i) is a regular character but doesn't match s.charAt(j), then dp[i][j] is set to false.
     * <p>
     * The final answer is stored in dp[0][0], which represents whether the entire pattern p matches the entire input string s.
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[0].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[0].length - 1) {
                    dp[i][j] = true;
                } else if (i == dp.length - 1) {
                    dp[i][j] = false;
                } else if (j == dp[0].length - 1) {
                    if (p.charAt(i) == '*') {
                        dp[i][j] = dp[i + 1][j];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    if (p.charAt(i) == '?') {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else if (p.charAt(i) == '*') {
                        dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                    } else if (p.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[0][0];
    }

    public boolean isMatch2(String s, String p) {

        int n = s.length();
        int m = p.length();
        int i = 0, j = 0, startIndex = -1, match = 0;

        while (i < n) {
            // If the current characters match or the
            // p has a '?', move to the next
            // characters in both p and s.
            if (j < m
                    && (p.charAt(j) == '?'
                    || p.charAt(j)
                    == s.charAt(i))) {
                i++;
                j++;
            }
            // If the p has a '*' character, mark the
            // current position in the p and the s
            // as a proper match.
            else if (j < m && p.charAt(j) == '*') {
                startIndex = j;
                match = i;
                j++;
            }
            // If we have not found any match and no '*'
            // character, backtrack to the last '*'
            // character position and try for a different
            // match.
            else if (startIndex != -1) {
                j = startIndex + 1;
                match++;
                i = match;
            }
            // If none of the above cases comply, the
            // p does not match.
            else {
                return false;
            }
        }

        // Consume any remaining '*' characters in the given
        // p.
        while (j < m && p.charAt(j) == '*') {
            j++;
        }

        // If we have reached the end of both the p
        // and the s, the p matches the s.
        return j == m;
    }

}
