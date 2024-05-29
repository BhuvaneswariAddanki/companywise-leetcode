package com.example.companywise.leetcode.microsoft.easy;

import com.example.companywise.leetcode.microsoft.hard.ReverseNodesInKGroup;

/**
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "Mr Ding"
 * Output: "rM gniD"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 104
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 * <p>
 * Leetcode link :  https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
 */
public class ReverseWordsInAStringIII {
    public static void main(String[] args) {
        ReverseWordsInAStringIII obj = new ReverseWordsInAStringIII();
        String result = obj.reverseWords("Let's take LeetCode contest");
        System.out.print(result);
    }

    public String reverseWords(String s) {
        char[] ch = s.toCharArray();
        int prevEndIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            if (ch[i] == ' ') {
                reverse(ch, prevEndIndex + 1, i - 1);
                prevEndIndex = i;
            }
        }
        reverse(ch, prevEndIndex + 1, s.length()-1);
        return new String(ch);

    }

    private void reverse(char[] ch, int start, int end) {
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }
}
