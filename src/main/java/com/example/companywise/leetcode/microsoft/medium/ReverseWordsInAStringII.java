package com.example.companywise.leetcode.microsoft.medium;

/**
 * 186. Reverse Words in a String II
 * <p>
 * Given an input string , reverse the string word by word.
 * <p>
 * Example:
 * <p>
 * Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * <p>
 * Note:
 * <p>
 * A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces.
 * The words are always separated by a single space.
 * <p>
 * Follow up: Could you do it in-place without allocating extra space?
 * <p>
 * link : https://leetcode.ca/all/186.html
 */
public class ReverseWordsInAStringII {

    public void reverseWords(char[] s) {

        int i = 0;
        for (int j = 0; j < s.length; j++) {
            if (s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            } else if (j == s.length - 1) {
                reverse(s, i, j);
            }
        }
        reverse(s, 0, s.length - 1);
    }

    private void reverse(char[] s, int i, int j) {

        for (int p1 = i, p2 = j; p1 < p2; p1++, p2--) {
            char temp = s[p1];
            s[p1] = s[p2];
            s[p2] = temp;
        }
    }
}
