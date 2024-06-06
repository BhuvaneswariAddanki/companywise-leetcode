package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * <p>
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * <p>
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * Leetcode link : https://leetcode.com/problems/word-ladder/description/
 */
public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log"));
        WordLadder wordLadder = new WordLadder();
        int result = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.print(result);
    }

    Map<String, List<String>> wordMap = new HashMap<>();
    int count = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty()) {
            return 0;
        }


        Map<String, Integer> ladder = new HashMap<>();
        int min = Integer.MAX_VALUE;
        Deque<String> queue = new ArrayDeque<>();
        for (String word : wordList) {
            ladder.put(word, Integer.MAX_VALUE);
        }
        ladder.put(beginWord, 0);
        wordList.add(endWord);
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int step = ladder.get(word) + 1;
            if (step > min) {
                continue;
            }

            for (int i = 0; i < word.length(); i++) {
                StringBuilder w = new StringBuilder(word);
                for (char c = 'a'; c <= 'z'; c++) {
                    w.setCharAt(i, c);
                    String newWord = w.toString();

                    if (ladder.containsKey(newWord)) {
                        if (step > ladder.get(newWord)) {
                            continue;
                        } else if (step < ladder.get(newWord)) {
                            queue.add(newWord);
                            ladder.put(newWord, step);
                        }
                        if (wordMap.containsKey(newWord)) {
                            wordMap.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            wordMap.put(newWord, list);
                        }

                        if (endWord.equals(newWord)) {
                            min = step;
                        }

                    }
                }
            }

        }
        //BackTracking
        LinkedList<String> result = new LinkedList<>();
        backTrace(beginWord, endWord, result);
        return count == Integer.MAX_VALUE ? 0 : count;
    }

    private void backTrace(String beginWord, String word, LinkedList<String> list) {

        if (word.equals(beginWord)) {
            list.add(0, word);
            count = Math.min(count, list.size());
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (wordMap.get(word) != null) {
            for (String adjacent : wordMap.get(word)) {

                backTrace(beginWord, adjacent, list);
            }
        }
        list.remove(0);
    }
}
