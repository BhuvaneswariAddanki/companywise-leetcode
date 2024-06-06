package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.hard.BasicCalculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 * <p>
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3+2*2"
 * Output: 7
 * <p>
 * Example 2:
 * <p>
 * Input: s = " 3/2 "
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 * <p>
 * Leetcode link : https://leetcode.com/problems/basic-calculator-ii/description/
 */
public class BasicCalculatorII {

    public static void main(String[] args) {
        String s = "3+2*2";
        BasicCalculatorII basicCalculatorII = new BasicCalculatorII();
        int res = basicCalculatorII.calculate2(s);
        System.out.print(res);
    }


    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        int num = 0, tmp = 0, res = 0;
        char op = '+';
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                tmp = tmp*10 + c - '0';
            } else if (c != ' ') {
                //process the numerical value of string so far; based on what 'op' we have before it
                num = cal(num, tmp, op);
                if (c == '+' || c == '-') {
                    res += num;
                    num = 0;
                }
                // reset
                tmp = 0;
                op = c;
            }
        }
        return res + cal(num, tmp, op);
    }
    private int cal(int num, int tmp, char op) {
        if (op == '+') return num + tmp;
        else if (op == '-') return num - tmp;
        else if (op == '*') return num * tmp;
        else    return num / tmp;
    }

    public int calculate2(String s) {
        Deque<Integer> st = new ArrayDeque<>();

        int num = 0;
        char operator = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (isOperator(c) || i == s.length() - 1) {
                if (operator == '+') st.push(num);
                else if (operator == '-') st.push(-num);
                else if (operator == '*') st.push(st.pop() * num);
                else if (operator == '/') st.push(st.pop() / num);

                num = 0;
                operator = c;
            }
        }

        int ans = 0;

        while (!st.isEmpty()) {
            ans += st.pop();
        }

        return ans;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

}
