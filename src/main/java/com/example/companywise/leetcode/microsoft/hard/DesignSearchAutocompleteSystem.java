package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character'#'). Foreach characterthey typeexcept '#', you need to return thetop 3historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 * <p>
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * <p>
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * <p>
 * If less than 3 hot sentences exist, then just return as many as you can.
 * <p>
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * <p>
 * Your job is to implement the following functions:
 * <p>
 * The constructor function:
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times):This is the constructor. The input ishistorical data.Sentencesis a string array consists of previously typed sentences.Timesis the corresponding times a sentence has been typed. Your system should record these historical data.
 * <p>
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 * <p>
 * List<String> input(char c):The inputcis the next character typed by the user. The character will only be lower-case letters ('a'to'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be thetop 3historical hot sentences that have prefix the same as the part of sentence already typed.
 * <p>
 * Example:
 * Operation:AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you":5times
 * "island":3times
 * "ironman":2times
 * "i love leetcode":2times
 * Now, the user begins another search:
 * <p>
 * Operation:input('i')
 * Output:["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix"i". Among them, "ironman" and "i love leetcode" have same hot degree. Since' 'has ASCII code 32 and'r'has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * <p>
 * Operation:input(' ')
 * Output:["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix"i ".
 * <p>
 * Operation:input('a')
 * Output:[]
 * Explanation:
 * There are no sentences that have prefix"i a".
 * <p>
 * Operation:input('#')
 * Output:[]
 * Explanation:
 * The user finished the input, the sentence"i a"should be saved as a historical sentence in system. And the following input will be counted as a new search.
 * <p>
 * Note:
 * <p>
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * <p>
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * <p>
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * <p>
 * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are
 * <p>
 * persisted across multiple test cases . Please see here for more details.
 * <p>
 * Leetcode link : https://aaronice.gitbook.io/lintcode/trie/design-search-autocomplete-system
 */
public class DesignSearchAutocompleteSystem {

    class AutocompleteSystem {
        class TrieNode {
            Map<Character, TrieNode> next;
            Map<String, Integer> count;
            boolean isWord;

            public TrieNode() {
                next = new HashMap<>();
                count = new HashMap<>();
                isWord = false;
            }
        }

        TrieNode root;
        String prefix;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            prefix = "";

            for (int i = 0; i < sentences.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        private void add(String str, int count) {
            char[] chas = str.toCharArray();
            TrieNode node = root;

            for (char c : chas) {
                TrieNode nextNode = node.next.get(c);
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    node.next.put(c, nextNode);
                }
                node = nextNode;
                node.count.put(str, node.count.getOrDefault(str, 0) + count);
            }

            node.isWord = true;
        }

        public List<String> input(char c) {
            if (c == '#') {
                add(prefix, 1);
                prefix = "";
                return new ArrayList<>();
            }

            prefix = prefix + c;
            // System.out.println(prefix);
            TrieNode node = root;
            for (char cc : prefix.toCharArray()) {
                node = node.next.get(cc);
                if (node == null) return new ArrayList<>();
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> (o1.count == o2.count ? o1.str.compareTo(o2.str) : o2.count - o1.count));

            for (String str : node.count.keySet()) {
                pq.add(new Pair(str, node.count.get(str)));
            }

            List<String> res = new ArrayList<>();
            for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
                Pair pair = pq.poll();
                // System.out.println(pair.str + " " + pair.count);
                res.add(pair.str);
            }

            return res;
        }

        class Pair {
            String str;
            int count;

            public Pair(String str, int count) {
                this.str = str;
                this.count = count;
            }
        }

    }

}
