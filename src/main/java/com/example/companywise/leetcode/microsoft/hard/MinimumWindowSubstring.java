package com.example.companywise.leetcode.microsoft.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * <p>
 * Leetcode link : https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int count = map.size();
        int i = 0;
        int j = 0;
        int start = 0;
        int ans = Integer.MAX_VALUE;
        while (j < s.length()) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) - 1);
            if (map.get(s.charAt(j)) == 0) {
                count--;
            }
            if (count == 0) {
                while (count == 0) {

                    if (ans > j - i + 1) {
                        ans = j - i + 1;
                        start = i;
                    }
                    map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
                    if (map.get(s.charAt(i)) > 0) {
                        count++;
                    }
                    i++;
                }
            }
            j++;
        }
        return ans != Integer.MAX_VALUE ? s.substring(start, start + ans) : "";

    }
}
