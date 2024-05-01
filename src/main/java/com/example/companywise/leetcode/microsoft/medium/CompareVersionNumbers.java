package com.example.companywise.leetcode.microsoft.medium;

import java.util.Map;

/**
 * Given two version numbers, version1 and version2, compare them.
 * <p>
 * Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.
 * <p>
 * To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 * <p>
 * Return the following:
 * <p>
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".
 * <p>
 * Example 2:
 * <p>
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: version1 does not specify revision 2, which means it is treated as "0".
 * <p>
 * Example 3:
 * <p>
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= version1.length, version2.length <= 500
 * version1 and version2 only contain digits and '.'.
 * version1 and version2 are valid version numbers.
 * All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 * <p>
 * Leetcode link : https://leetcode.com/problems/compare-version-numbers/
 */
public class CompareVersionNumbers {
    public static void main(String[] args) {
        String version1 = "1.01", version2 = "1.001";
        CompareVersionNumbers obj = new CompareVersionNumbers();
        int result = obj.compareVersion(version1, version2);
        System.out.print(result);
    }

    public int compareVersion(String version1, String version2) {
        String[] version1Parts = version1.split("\\.");
        String[] version2Parts = version2.split("\\.");
        int maxLength = Math.max(version1Parts.length, version2Parts.length);
        for (int i = 0; i < maxLength; i++) {
            int part1 = 0;
            int part2 = 0;
            if (i < version1Parts.length) {
                part1 = Integer.parseInt(version1Parts[i]);
            }
            if (i < version2Parts.length) {
                part2 = Integer.parseInt(version2Parts[i]);
            }
            if (part1 < part2) {
                return -1;
            } else if (part1 > part2) {
                return 1;
            }
        }
        return 0;
    }

    public int compareVersion2(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();
        int i = 0;
        int j = 0;

        while (i < len1 || j < len2) {
            int temp1 = 0, temp2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                temp1 = temp1 * 10 + version1.charAt(i) - '0';
                i++;
            }

            while (j < len2 && version2.charAt(j) != '.') {
                temp2 = temp2 * 10 + version2.charAt(j) - '0';
                j++;
            }

            if (temp1 < temp2) {
                return -1;
            } else if (temp1 > temp2) {
                return 1;
            } else {
                i++;
                j++;
            }
        }
        return 0;
    }
}
