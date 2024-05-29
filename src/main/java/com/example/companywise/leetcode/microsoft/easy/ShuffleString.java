package com.example.companywise.leetcode.microsoft.easy;

/**
 * You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * <p>
 * Return the shuffled string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "abc", indices = [0,1,2]
 * Output: "abc"
 * Explanation: After shuffling, each character remains in its position.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * s.length == indices.length == n
 * 1 <= n <= 100
 * s consists of only lowercase English letters.
 * 0 <= indices[i] < n
 * All values of indices are unique.
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/shuffle-string/description/
 */
public class ShuffleString {

    public String restoreString(String s, int[] indices) {
        char[] ch = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            ch[indices[i]] = s.charAt(i);
        }
        return new String(ch);
    }
}
