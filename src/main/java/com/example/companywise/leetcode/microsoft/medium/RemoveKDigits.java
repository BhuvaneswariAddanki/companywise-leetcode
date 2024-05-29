package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * <p>
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * <p>
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= num.length <= 105
 * num consists of only digits.
 * num does not have any leading zeros except for the zero itself.
 * <p>
 * Leetcode link : https://leetcode.com/problems/remove-k-digits/description/
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if(num.length() == k) return "0";
        char[] nums = num.toCharArray();
        char[] ans = new char[nums.length];
        int top = -1;
        int count = k;

        for (char c: nums){
            while (count > 0 && top >= 0 && ans[top] > c){
                --top;
                --count;
            }
            ++top;
            ans[top] = c;
        }

        int nonZeroStart = 0;
        while(ans[nonZeroStart]=='0' && nonZeroStart<nums.length-k-1){
            nonZeroStart++;
        }

        return String.valueOf(ans,nonZeroStart,nums.length-k-nonZeroStart);
    }

    public String removeKdigits2(String num, int k) {
        StringBuilder s = new StringBuilder();
        s.append(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            if (k > 0 && s.length() > 0 && num.charAt(i) < s.charAt(s.length() - 1)) {
                s.deleteCharAt(s.length() - 1);
                k--;
                i--;
            } else {
                s.append(num.charAt(i));
            }
        }
        // remove the righter most digits (s is in ascending order now)
        /*
         * First, we observe that if we have a sorted ordering of digits already, eg 12445,
         * we can just remove k elements from the right for lowest value.
         */
        while (k > 0 && s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
            k--;
        }
        //removing starting zeros
        while (s.length() > 0 && s.charAt(0) == '0') {
            s.deleteCharAt(0);
        }
        //return 0 if no digits ar there in result
        if (s.length() == 0) {
            s.append('0');
        }
        return s.toString();

    }
}
