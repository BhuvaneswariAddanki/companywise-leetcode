package com.example.companywise.leetcode.microsoft.easy;

/**
 * Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.
 *
 * For example:
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 *
 *
 * Example 1:
 *
 * Input: columnNumber = 1
 * Output: "A"
 *
 * Example 2:
 *
 * Input: columnNumber = 28
 * Output: "AB"
 *
 * Example 3:
 *
 * Input: columnNumber = 701
 * Output: "ZY"
 *
 *
 *
 * Constraints:
 *
 *     1 <= columnNumber <= 231 - 1
 *
 * Leetcode link : https://leetcode.com/problems/excel-sheet-column-title/description/
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder str= new StringBuilder();
        while(columnNumber>0)
        {
            columnNumber--;

            // Get the last character and append it at the end of the string.
            str.insert(0, (char) ((columnNumber) % 26 + 'A'));
            columnNumber/=26;
        }
        return str.toString();
    }
}
