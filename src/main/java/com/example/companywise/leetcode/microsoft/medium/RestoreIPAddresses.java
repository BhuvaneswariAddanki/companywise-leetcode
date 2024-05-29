package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 * <p>
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * <p>
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * <p>
 * Example 2:
 * <p>
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * <p>
 * Example 3:
 * <p>
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * s consists of digits only.
 * <p>
 * Leetcode link : https://leetcode.com/problems/restore-ip-addresses/description/
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);

        return res;
    }

    void backtrack(String s, int index, List<Integer> curr, List<String> res) {
        if (index == s.length()) {
            StringBuilder sb = new StringBuilder();
            if (curr.size() == 4) {
                for (Integer integer : curr) {
                    sb.append(integer).append(".");

                }
                sb.deleteCharAt(sb.length()-1);
                res.add(sb.toString());
            }
            return;
        }


        int curDigit = s.charAt(index) - '0';

        curr.add(curDigit); //1,0
        backtrack(s, index + 1, curr, res);
        curr.remove(curr.size() - 1);

        if (curr.size() > 0) {
            int last = curr.get(curr.size() - 1);
            int candidate = last * 10 + curDigit;
            if (last != 0 && candidate <= 255) {
                curr.remove(curr.size() - 1);
                curr.add(candidate);
                backtrack(s, index + 1, curr, res);
            }
        }
    }
}
