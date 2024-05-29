package com.example.companywise.leetcode.microsoft.hard;

import java.util.Stack;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 3 * 105
 *     s consists of digits, '+', '-', '(', ')', and ' '.
 *     s represents a valid expression.
 *     '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 *     '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 *     There will be no two consecutive operators in the input.
 *     Every number and running calculation will fit in a signed 32-bit integer.
 *
 *  Leetcode link : https://leetcode.com/problems/basic-calculator/description/
 */
public class BasicCalculator {
    public static void main(String[] args) {
        String input = "(1+(4+5+2)-3)+(6+8)";
        BasicCalculator basicCalculator = new BasicCalculator();
       int result = basicCalculator.calculate(input);
       System.out.print(result);
    }

    /**
     * Simple iterative solution by identifying characters one by one. One important thing is that the input is valid, which means the parentheses are always paired and in order.
     * Only 5 possible input we need to pay attention:
     *
     *     digit: it should be one digit from the current number
     *
     *     '+': number is over, we can add the previous number and start a new number
     *
     *     '-': same as above
     *
     *     '(': push the previous result and the sign into the stack, set result to 0, just calculate the new result within the parenthesis.
     *
     *     ')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis, second is the temporary result before this pair of parenthesis. We add them together.
     *
     * Finally if there is only one number, from the above solution, we haven't add the number to the result, so we do a check see if the number is zero.
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                number = 10 * number + (c - '0');
            }else if(c == '+' || c == '-'){
                result += sign * number;
                number = 0;
                sign = c== '+' ? 1 : -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        return result + sign * number;
    }

    int idx; // this index traverse the string in one pass, between different level of recursion
    public int calculateUsingRecursion(String s) {
        idx = 0; // Initialization should be here
        return calc(s);
    }

    private int calc(String s) {
        int res = 0, num = 0, sign = 1;
        while (idx < s.length()) {
            char c = s.charAt(idx++);
            if (c >= '0' && c <= '9') num = num * 10 + c - '0';
            else if (c == '(') num = calc(s); // ( is start of a new sub-problem, Let recursion solve the sub-problem
            else if (c == ')') return res + sign * num;
            else if (c == '+' || c == '-') { // only when we meet a new sign, we know a while number has been read
                res += sign * num;
                num = 0;
                sign = c == '-' ? -1 : 1;
            }
        }
        return res + sign * num; // last number is not processed yet
    }
}
