package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
 * <p>
 * Return the maximum possible length of s.
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All the valid concatenations are:
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * Maximum length is 4.
 * <p>
 * Example 2:
 * <p>
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
 * <p>
 * Example 3:
 * <p>
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Explanation: The only string in arr has all 26 characters.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lowercase English letters.
 * Leetcode link : https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/description/
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    public static void main(String[] args) {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        MaximumLengthOfAConcatenatedStringWithUniqueCharacters obj = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters();
        int result = obj.maxLength(arr);
        System.out.print(result);

    }

    public int maxLength(List<String> arr) {
        List<Set<Character>> dp = new ArrayList<>(Arrays.asList(new HashSet<>())); // auxiliary dp storage
        int res = 0; // will store number of unique chars in resultant string
        for (String s : arr) {
            Set<Character> a = new HashSet<>();
            for (char c : s.toCharArray()) {
                a.add(c);
            }
            // if we do not have duplicate chars in current string
            if (a.size() == s.length()) {
                for (int i = dp.size() - 1; i >= 0; --i) {
                    Set<Character> c = new HashSet<>(dp.get(i));
                    if (Collections.disjoint(a, c)) {
                        Set<Character> combined = new HashSet<>(c);
                        combined.addAll(a);
                        dp.add(combined);
                        res = Math.max(res, combined.size());
                    }
                }
            }
        }
        return res;
    }
}
