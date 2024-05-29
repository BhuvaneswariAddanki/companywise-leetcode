package com.example.companywise.leetcode.microsoft.medium;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 * <p>
 * Implement the Trie class:
 * <p>
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 * <p>
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 * <p>
 * Leetcode link : https://leetcode.com/problems/implement-trie-prefix-tree/description/
 */
public class ImplementTrie {

    public class Node {
        private boolean isWord;
        private Node[] child;

        public Node() {
            isWord = false;
            child = new Node[26];
        }
    }

    Node start;

    public ImplementTrie() {
        start = new Node();
    }

    public void insert(String word) {
        int n = word.length();

        Node head = start;

        for (int i = 0 ; i < n ; i++) {
            int index = word.charAt(i) - 'a';
            if (head.child[index] == null) {
                head.child[index] = new Node();
            }

            head = head.child[index];
        }

        head.isWord = true;
    }

    public boolean search(String word) {

        int n = word.length();

        Node head = start;
        for (int i = 0 ; i < n ; i++) {
            int index = word.charAt(i) - 'a';

            if (head.child[index] == null) return false;
            head = head.child[index];
        }

        return head.isWord;
    }

    public boolean startsWith(String prefix) {

        int m = prefix.length();

        Node head = start;
        for (int i = 0 ; i < m ; i++) {
            int index = prefix.charAt(i) - 'a';

            if (head.child[index] == null) return false;
            head = head.child[index];
        }

        return true;
    }
}
