package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 * <p>
 * Leetcode link : https://leetcode.com/problems/edit-distance/description/
 */
public class EditDistance {
    /**
     * Approach :
     * <p>
     * The approach here that I am using is dynamic programming. The idea is to build a 2D matrix dp where dp[i][j] represents the minimum number of operations required to transform the substring word1[0...i-1] into the substring word2[0...j-1].
     * <p>
     * How is Matrix built :
     * <p>
     * The matrix is built iteratively using the following recurrence relation:
     * <p>
     * If word1[i-1] == word2[j-1], then dp[i][j] = dp[i-1][j-1]. That is, no operation is required because the characters at positions i-1 and j-1 are already the same.
     * Otherwise, dp[i][j] is the minimum of the following three values:
     * <p>
     * dp[i-1][j-1] + 1: replace the character at position i-1 in word1 with the character at position j-1 in word2.
     * dp[i-1][j] + 1: delete the character at position i-1 in word1.
     * dp[i][j-1] + 1: insert the character at position j-1 in word2 into word1 at position i.
     * <p>
     * The base cases are:
     * <p>
     * dp[i][0] = i: transforming word1[0...i-1] into an empty string requires i deletions.
     * dp[0][j] = j: transforming an empty string into word2[0...j-1] requires j insertions.
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        final int m = word1.length();//first word length
        final int n = word2.length();///second word length
        // dp[i][j] := min # of operations to convert word1[0..i) to word2[0..j)
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i)
            dp[i][0] = i;

        for (int j = 1; j <= n; ++j)
            dp[0][j] = j;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (word1.charAt(i - 1) == word2.charAt(j - 1))//same characters
                    dp[i][j] = dp[i - 1][j - 1];//no operation
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1; //replace   //delete  //insert

        return dp[m][n];
    }
}
