package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given a string s, return the longest
 * palindromic
 * substring
 * in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * <p>
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * <p>
 * leetcode link : https://leetcode.com/problems/longest-palindromic-substring/description/
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String s = "babad";
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        String result = obj.longestPalindrome(s);
        System.out.print(result);

    }

    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {

            int oddMax = findPossibleMaxPalindrome(s, i, i);
            int evenMax = findPossibleMaxPalindrome(s, i, i + 1);
            int currentMax = Math.max(oddMax, evenMax);
            if (currentMax > end - start) {
                start = i - (currentMax - 1) / 2;
                end = i + currentMax / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    private int findPossibleMaxPalindrome(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public String longestPalindrome2(String s) {
        String result = "";
        if (s == null || s.length() == 1) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            String subStringWithEvenLength = longestSubstringWithCenter(s, i, i + 1);
            String subStringWithOddLength = longestSubstringWithCenter(s, i, i);
            String maxSubString = "";
            if (subStringWithEvenLength.length() > subStringWithOddLength.length()) {
                maxSubString = subStringWithEvenLength;
            } else {
                maxSubString = subStringWithOddLength;
            }

            if (result.length() < maxSubString.length()) {
                result = maxSubString;
            }
        }
        return result;

    }

    private String longestSubstringWithCenter(String s, int left, int right) {
        if (left < 0 || right >= s.length()) {
            return "";
        }
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return s.substring(left + 1, right);
    }

}
