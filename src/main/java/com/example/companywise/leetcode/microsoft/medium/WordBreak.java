package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * <p>
 * Leetcode link : https://leetcode.com/problems/word-break/description/
 */
public class WordBreak {
    public static void main(String[] args) {
        List<String> dict = new ArrayList<>();
        dict.add("aaaa");
        dict.add("aaa");
        WordBreak wordBreak = new WordBreak();
        wordBreak.wordBreak("aaaaaaa", dict);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length()];
        return dfs(s, 0, wordDict, dp);
    }
    private boolean dfs(String s, int i, List<String> wordDict, int[] dp) {
        if (i == s.length())
            return true;
        if (dp[i] != 0)
            return dp[i] == 1;
        boolean found = false;
        for (String word : wordDict) {
            if (s.startsWith(word, i) && dfs(s, i + word.length(), wordDict, dp)) {
                found = true;
                break;
            }
        }
        dp[i] = found ? 1 : -1;
        return dp[i] == 1;
    }

    public  boolean wordBreak2(String s, List<String> dictionary) {
        // create a dp table to store results of sub problems
        // value of dp[i] will be true if string s can be segmented
        // into dictionary words from 0 to i.
        boolean[] dp = new boolean[s.length() + 1];

        // dp[0] is true because an empty string can always be segmented.
        dp[0] = true;

        for(int i = 0; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && dictionary.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
