package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 * <p>
 * Leetcode link : https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class LetterCombinationsOfAPhoneNumber {

    Map<Character, String> dailMap = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        constructDialMap();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        recursive(sb, digits, 0, result);
        return result;

    }

    private void recursive(StringBuilder sb, String digits, int index, List<String> result) {
        if (index == digits.length()) {
            result.add(sb.toString());
        } else {
            String charList = dailMap.getOrDefault(digits.charAt(index), "");
            for (Character ch : charList.toCharArray()) {
                sb.append(ch);
                recursive(sb, digits, index + 1, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private Map<Character, String> constructDialMap() {
        dailMap.put('2', "abc");
        dailMap.put('3', "def");
        dailMap.put('4', "ghi");
        dailMap.put('5', "jkl");
        dailMap.put('6', "mno");
        dailMap.put('7', "pqrs");
        dailMap.put('8', "tuv");
        dailMap.put('9', "wxyz");
        return dailMap;
    }

    public List<String> letterCombinations2(String digits) {

        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }

        dailMap = constructDialMap();
        List<String> prev = new ArrayList<>();

        prev.add("");
        for (int i = 1; i <= digits.length(); i++) {
            List<String> current = new ArrayList<>();
            for (String s : prev) {
                for (Character ch : dailMap.get(digits.charAt(i - 1)).toCharArray()) {
                    current.add(s + ch);
                }
            }
            prev = current;
        }

        return prev;
    }

}
