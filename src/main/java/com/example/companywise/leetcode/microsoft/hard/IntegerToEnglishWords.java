package com.example.companywise.leetcode.microsoft.hard;

/**
 * Convert a non-negative integer num to its English words representation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 123
 * Output: "One Hundred Twenty Three"
 * <p>
 * Example 2:
 * <p>
 * Input: num = 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * <p>
 * Example 3:
 * <p>
 * Input: num = 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 231 - 1
 */
public class IntegerToEnglishWords {
    private final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private final String[] belowTwenty1 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return helper(num);
    }

    private String helper(int num) {
        StringBuilder result = new StringBuilder();
        if (num < 10) result.append(belowTen[num]);
        else if (num < 20) result.append(belowTwenty1[num - 10]);
        else if (num < 100) result.append(belowHundred[num / 10]).append(" ").append(helper(num % 10));
        else if (num < 1000) result.append(helper(num / 100)).append(" Hundred ").append(helper(num % 100));
        else if (num < 1000000) result.append(helper(num / 1000)).append(" Thousand ").append(helper(num % 1000));
        else if (num < 1000000000)
            result.append(helper(num / 1000000)).append(" Million ").append(helper(num % 1000000));
        else result.append(helper(num / 1000000000)).append(" Billion ").append(helper(num % 1000000000));
        return result.toString().trim();
    }

    public String numberToWords1(int num) {
        return num == 0 ? "Zero" : helper1(num);
    }

    private final String[] belowTwenty = {"", "One", "Two", "Three", "Four",
            "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "", "Twenty", "Thirty", "Forty",
            "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private String helper1(int num) {
        StringBuilder s = new StringBuilder();

        if (num < 20)
            s.append(belowTwenty[num]);
        else if (num < 100)
            s.append(tens[num / 10]).append(" ").append(belowTwenty[num % 10]);
        else if (num < 1000)
            s.append(helper1(num / 100)).append(" Hundred ").append(helper1(num % 100));
        else if (num < 1000000)
            s.append(helper1(num / 1000)).append(" Thousand ").append(helper1(num % 1000));
        else if (num < 1000000000)
            s.append(helper1(num / 1000000)).append(" Million ").append(helper1(num % 1000000));
        else
            s.append(helper1(num / 1000000000)).append(" Billion ").append(helper1(num % 1000000000));

        return s.toString().trim();
    }
}
