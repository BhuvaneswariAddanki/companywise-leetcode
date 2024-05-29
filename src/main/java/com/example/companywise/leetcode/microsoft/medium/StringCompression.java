package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given an array of characters chars, compress it using the following algorithm:
 * <p>
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * <p>
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * <p>
 * The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * <p>
 * After you are done modifying the input array, return the new length of the array.
 * <p>
 * You must write an algorithm that uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * <p>
 * Example 2:
 * <p>
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * Explanation: The only group is "a", which remains uncompressed since it's a single character.
 * <p>
 * Example 3:
 * <p>
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 * Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 * <p>
 * Leetcode link : https://leetcode.com/problems/string-compression/description/
 */
public class StringCompression {

    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        StringCompression stringCompression = new StringCompression();
        stringCompression.compress(chars);
        for (char ch : chars) {
            System.out.print(ch + " ");
        }

    }

    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }

        char prev = chars[0];
        int count = 1;
        int index = 0;
        for (int i = 1; i <= chars.length; i++) {
            if (i != chars.length && prev == chars[i]) {
                count++;
            } else {
                chars[index++] = prev;
                if (count > 1) {
                    int num = count;
                    int start = index;
                    while (num > 0) {
                        int r = num % 10;
                        chars[index++] = Character.forDigit(r, 10);
                        num = num / 10;
                    }
                    int end = index - 1;
                    while (start < end) {
                        char temp = chars[start];
                        chars[start] = chars[end];
                        chars[end] = temp;
                        start++;
                        end--;
                    }
                }
                count = 1;
                if (i != chars.length) {
                    prev = chars[i];
                }
            }
        }
        return index;
    }

    public int compress2(char[] chars) {
        int len = chars.length;
        int index = 0;

        for (int i = 0; i < len;){
            char a = chars[i];
            chars[index] = a;
            i++;
            index++;
            int count=1;
            //this is for checking frequency
            while (i < len && chars[i] == a){
                count++;
                i++;
            }

            //this is for adding frequency to char array
            if (count > 1) {
                if (count < 10) {
                    chars[index] = (char) ((count % 10) + 48);
                    index++;
                } else {
                    int digit = 0;
                    int c = count;
                    while (c > 0) {
                        digit++;
                        c = c / 10;
                    }
                    int real = index + digit;
                    while (count > 0) {
                        chars[index + digit - 1] = (char) ((count % 10) + 48);
                        digit--;
                        count = count / 10;
                    }
                    index = real;
                }
            }
        }
        return index;
    }
}
