package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * <p>
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * <p>
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * The sum of all shortest transformation sequences does not exceed 105.
 * <p>
 * Leetcode link : https://leetcode.com/problems/word-ladder-ii/description/
 */
public class WordLadderII {
    //TODO : trace the result by debugging the code
    Map<String, List<String>> map;
    List<List<String>> results;


    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordList = new HashSet<>(Set.of("hot", "dot", "dog", "lot", "log", "cog"));
        WordLadderII wordLadderII = new WordLadderII();
        List<List<String>> result  = wordLadderII.findLadders(beginWord,endWord,wordList);
        System.out.print(result);

    }

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        results = new ArrayList<>();
        if (dict.isEmpty()) {
            return results;
        }

        int min = Integer.MAX_VALUE;

        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);

        map = new HashMap<>();

        Map<String, Integer> ladder = new HashMap<>();
        for (String string : dict) {
            ladder.put(string, Integer.MAX_VALUE);
        }
        ladder.put(start, 0);

        dict.add(end);
        //BFS: Dijisktra search
        while (!queue.isEmpty()) {

            String word = queue.poll();

            int step = ladder.get(word) + 1;//'step' indicates how many steps are needed to travel to one word.

            if (step > min) continue;

            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String newWord = builder.toString();
                    if (ladder.containsKey(newWord)) {

                        if (step > ladder.get(newWord))//Check if it is the shortest path to one word.
                            continue;
                        else if (step < ladder.get(newWord)) {
                            queue.add(newWord);
                            ladder.put(newWord, step);
                        }
                        // It is a KEY line. If one word already appeared in one ladder,
                        // Do not insert the same word inside the queue twice. Otherwise it gets TLE.

                        if (map.containsKey(newWord)) //Build adjacent Graph
                            map.get(newWord).add(word);
                        else {
                            List<String> list = new LinkedList<>();
                            list.add(word);
                            map.put(newWord, list);
                            //It is possible to write three lines in one:
                            //map.put(newWord,new LinkedList<String>(Arrays.asList(new String[]{word})));
                            //Which one is better?
                        }

                        if (newWord.equals(end))
                            min = step;

                    }//End if dict contains newWord
                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
        }//End While

        //BackTracking
        LinkedList<String> result = new LinkedList<>();
        backTrace(end, start, result);

        return results;
    }

    private void backTrace(String word, String start, List<String> list) {
        if (word.equals(start)) {
            list.add(0, start);
            results.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null) {
            for (String s : map.get(word)) {
                backTrace(s, start, list);
            }
        }
        list.remove(0);
    }

    List<String> list;

    public List<List<String>> findLadders2(String start, String end, Set<String> dict) {
        results = new ArrayList<>();
        if (dict.isEmpty())
            return results;

        int curr = 1, next = 0;
        boolean found = false;
        list = new LinkedList<>();
        map = new HashMap<>();

        Queue<String> queue = new ArrayDeque<>();
        Set<String> unvisited = new HashSet<>(dict);
        Set<String> visited = new HashSet<>();

        queue.add(start);
        unvisited.add(end);
        unvisited.remove(start);
        //BFS
        while (!queue.isEmpty()) {

            String word = queue.poll();
            curr--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String new_word = builder.toString();
                    if (unvisited.contains(new_word)) {
                        //Handle queue
                        if (visited.add(new_word)) {//Key statement,Avoid Duplicate queue insertion
                            next++;
                            queue.add(new_word);
                        }

                        if (map.containsKey(new_word)) {//Build Adjacent Graph
                            map.get(new_word).add(word);
                        } else {
                            List<String> l = new LinkedList<>();
                            l.add(word);
                            map.put(new_word, l);
                        }

                        if (new_word.equals(end) && !found) {
                            found = true;
                        }

                    }

                }//End:Iteration from 'a' to 'z'
            }//End:Iteration from the first to the last
            if (curr == 0) {
                if (found) break;
                curr = next;
                next = 0;
                unvisited.removeAll(visited);
                visited.clear();
            }
        }//End While

        backTrace(end, start);

        return results;
    }

    private void backTrace(String word, String start) {
        if (word.equals(start)) {
            list.add(0, start);
            results.add(new ArrayList<>(list));
            list.remove(0);
            return;
        }
        list.add(0, word);
        if (map.get(word) != null)
            for (String s : map.get(word))
                backTrace(s, start);
        list.remove(0);
    }
}
