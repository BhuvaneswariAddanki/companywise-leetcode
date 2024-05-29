package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 * <p>
 * Leetcode link : https://leetcode.com/problems/generate-parentheses/description/
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generate(result, sb, 0, 0, n);
        return result;

    }

    private void generate(List<String> result, StringBuilder sb, int openCount, int closeCount, int n) {

        if (sb.length() == n * 2) {
            result.add(sb.toString());
        } else {
            if (openCount < n) {
                sb.append("(");
                generate(result, sb, openCount + 1, closeCount, n);
                sb.deleteCharAt(sb.length() - 1);
            }
            if (closeCount < openCount) {
                sb.append(")");
                generate(result, sb, openCount, closeCount + 1, n);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

    }

}
