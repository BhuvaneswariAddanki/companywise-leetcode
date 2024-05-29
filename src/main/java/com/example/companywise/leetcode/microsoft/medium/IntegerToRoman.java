package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.easy.LinkedListCycle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Seven different symbols represent Roman numerals with the following values:
 * Symbol	Value
 * I	1
 * V	5
 * X	10
 * L	50
 * C	100
 * D	500
 * M	1000
 * <p>
 * Roman numerals are formed by appending the conversions of decimal place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 * <p>
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral.
 * If the value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM).
 * Only powers of 10 (I, X, C, M) can be appended consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple times. If you need to append a symbol 4 times use the subtractive form.
 * <p>
 * Given an integer, convert it to a Roman numeral.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 3749
 * <p>
 * Output: "MMMDCCXLIX"
 * <p>
 * Explanation:
 * <p>
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M)
 * 700 = DCC as 500 (D) + 100 (C) + 100 (C)
 * 40 = XL as 10 (X) less of 50 (L)
 * 9 = IX as 1 (I) less of 10 (X)
 * Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal places
 * <p>
 * Example 2:
 * <p>
 * Input: num = 58
 * <p>
 * Output: "LVIII"
 * <p>
 * Explanation:
 * <p>
 * 50 = L
 * 8 = VIII
 * <p>
 * Example 3:
 * <p>
 * Input: num = 1994
 * <p>
 * Output: "MCMXCIV"
 * <p>
 * Explanation:
 * <p>
 * 1000 = M
 * 900 = CM
 * 90 = XC
 * 4 = IV
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num <= 3999
 * <p>
 * Leetcode link : https://leetcode.com/problems/integer-to-roman/description/
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        String result = integerToRoman.intToRoman(3749);
        System.out.print(result);
    }

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        Map<Integer, String> storeIntRoman = new LinkedHashMap<>();
        storeIntRoman.put(1000, "M");
        storeIntRoman.put(900, "CM");
        storeIntRoman.put(500, "D");
        storeIntRoman.put(400, "CD");
        storeIntRoman.put(100, "C");
        storeIntRoman.put(90, "XC");
        storeIntRoman.put(50, "L");
        storeIntRoman.put(40, "XL");
        storeIntRoman.put(10, "X");
        storeIntRoman.put(9, "IX");
        storeIntRoman.put(5, "V");
        storeIntRoman.put(4, "IV");
        storeIntRoman.put(1, "I");
        for (Map.Entry<Integer, String> entry : storeIntRoman.entrySet()) {
            while (num >= entry.getKey()) {
                roman.append(entry.getValue());
                num -= entry.getKey();
            }
        }
        return roman.toString();
    }

    public String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();

        while (num >= 1000) {
            sb.append('M');
            num -= 1000;
        }

        while (num >= 900) {
            sb.append("CM");
            num -= 900;
        }

        while (num >= 500) {
            sb.append('D');
            num -= 500;
        }

        while (num >= 400) {
            sb.append("CD");
            num -= 400;
        }

        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }

        while (num >= 90) {
            sb.append("XC");
            num -= 90;
        }

        while (num >= 50) {
            sb.append('L');
            num -= 50;
        }

        while (num >= 40) {
            sb.append("XL");
            num -= 40;
        }

        while (num >= 10) {
            sb.append('X');
            num -= 10;
        }

        while (num >= 9) {
            sb.append("IX");
            num -= 9;
        }

        while (num >= 5) {
            sb.append('V');
            num -= 5;
        }

        while (num >= 4) {
            sb.append("IV");
            num -= 4;
        }

        while (num >= 1) {
            sb.append('I');
            num -= 1;
        }

        return sb.toString();
    }
}
