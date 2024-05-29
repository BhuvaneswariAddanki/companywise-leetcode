package com.example.companywise.leetcode.microsoft.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Word Break Problem | (Trie solution)
 * Exercise:
 * The above solutions only find out whether a given string can be segmented or not. Extend the above Dynamic Programming solution to print all possible partitions of input string.
 * Examples:
 * <p>
 * Input: ilikeicecreamandmango
 * Output:
 * i like ice cream and man go
 * i like ice cream and mango
 * i like icecream and man go
 * i like icecream and mango
 * Input: ilikesamsungmobile
 * Output:
 * i like sam sung mobile
 * i like samsung mobile
 * <p>
 * geekforgeeks link : https://www.geeksforgeeks.org/word-break-problem-dp-32/
 */
public class WordBreakProblem2 {
    static boolean CanParseUtil(Map<String, Boolean> mp,
                                String word) {

        // if the size id zero that means we completed the
        // word. so we can return true
        int size = word.length();
        if (size == 0) {
            return true;
        }
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            temp.append(word.charAt(i));

            // if the temp exist in hashmap and the parsing
            // operation of the remaining word is true, we
            // can return true.
            if (mp.containsKey(temp.toString())
                    && CanParseUtil(mp,
                    word.substring(i + 1))) {
                return true;
            }
        }

        // if there is a mismatch in the dictionary, we can
        // return false.
        return false;
    }

    static String CanParse(String[] words, String word) {

        // store the words in the hashmap
        Map<String, Boolean> mp = new HashMap<>();
        for (String it : words) {
            mp.put(it, true);
        }
        return CanParseUtil(mp, word) ? "YES"
                : "NO";
    }

    public static void main(String[] args) {
        String[] words
                = {"mobile", "samsung", "sam", "sung", "man",
                "mango", "icecream", "and", "go", "i",
                "like", "ice", "cream"};
        String word = "samsungandmangok";
        System.out.println(CanParse(words, word));
    }
}
