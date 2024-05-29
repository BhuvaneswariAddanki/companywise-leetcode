package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * <p>
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * <p>
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * <p>
 * Follow-up:
 * <p>
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 * <p>
 * Leetcode link : https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
 */
public class PopulatingNextRightPointersInEachNode {
    // Definition for a Node.
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
            String s = next == null ? ", next=" + next : "";
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right + s + '}';
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1, new Node(2, new Node(4), new Node(5)), new Node(3, new Node(6), new Node(7)));
        PopulatingNextRightPointersInEachNode obj = new PopulatingNextRightPointersInEachNode();
        obj.connect(root);
        System.out.print(root);
    }

    public Node connect(Node root) {
        Deque<Node> nodeDeque = new ArrayDeque<>();
        if (root != null) {
            nodeDeque.offer(root);
        }
        while (!nodeDeque.isEmpty()) {
            int prevSize = nodeDeque.size();
            for (int i = 1; i <= prevSize; i++) {
                Node currentNode = nodeDeque.remove();
                if (i < prevSize) {
                    currentNode.next = nodeDeque.peek();
                }
                if (currentNode.left != null) {
                    nodeDeque.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    nodeDeque.offer(currentNode.right);
                }
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }

        Node curr = root;
        while (curr.left != null) {
            Node temp = curr;
            while (curr != null) {
                curr.left.next = curr.right;
                curr.right.next = (curr.next != null) ? curr.next.left : null;
                curr = curr.next;
            }
            curr = temp.left;
        }
        return root;
    }
}
