package com.example.companywise.leetcode.microsoft.easy;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * <p>
 * Implement the MyHashMap class:
 * <p>
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= key, value <= 106
 * At most 104 calls will be made to put, get, and remove.
 * <p>
 * Leetcode link : https://leetcode.com/problems/design-hashmap/description/
 */
public class DesignHashMap {
    class Node {
        int key;
        int val;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    class MyHashMap {

        private Node[] map;

        public MyHashMap() {
            map = new Node[1000];
            for (int i = 0; i < 1000; i++) {
                map[i] = new Node(-1, -1);
            }
        }

        public void put(int key, int value) {
            int hash = hash(key);
            Node cur = map[hash];

            while (cur.next != null) {
                if (cur.next.key == key) {
                    cur.next.val = value;
                    return;
                }
                cur = cur.next;
            }

            cur.next = new Node(key, value);
        }

        public int get(int key) {
            int hash = hash(key);
            Node cur = map[hash].next;

            while (cur != null) {
                if (cur.key == key)
                    return cur.val;
                cur = cur.next;
            }

            return -1;
        }

        public void remove(int key) {
            int hash = hash(key);
            Node cur = map[hash];

            while (cur.next != null) {
                if (cur.next.key == key) {
                    cur.next = cur.next.next;
                    return;
                }
                cur = cur.next;
            }
        }

        private int hash(int key) {
            return key % 1000;
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
