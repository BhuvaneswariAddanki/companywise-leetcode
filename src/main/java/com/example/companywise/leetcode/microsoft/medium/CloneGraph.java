package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {

    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return cloneNode(node, map);
    }

    public Node cloneNode(Node node, Map<Integer, Node> map) {
        if (node == null) return null;
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }
        Node cloned = new Node(node.val);
        map.put(node.val, cloned);
        for (Node neighbor : node.neighbors) {
            cloned.neighbors.add(cloneNode(neighbor, map));
        }
        return cloned;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
