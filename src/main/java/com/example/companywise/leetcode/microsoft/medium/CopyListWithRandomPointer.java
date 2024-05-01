package com.example.companywise.leetcode.microsoft.medium;


import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list,
 * where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * <p>
 * Your code will only be given the head of the original linked list.
 * Example 1:
 * <p>
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * <p>
 * Example 3:
 * <p>
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 * <p>
 * Leetcode link : https://leetcode.com/problems/copy-list-with-random-pointer/description/
 */
public class CopyListWithRandomPointer {


    public Node copyRandomList2(Node head) {
        Map<Node, Node> nodeReference = new HashMap<>();
        Map<Node, Node> randomNodeRef = new HashMap<>();
        Node copiedNode = deepCopy2(head, nodeReference, randomNodeRef);
        Node current = head;

        while (current != null) {
            if (current.random == null) {
                nodeReference.get(current).random = null;
            } else {
                nodeReference.get(current).random = nodeReference.get(randomNodeRef.get(current));
            }
            current = current.next;
        }
        return copiedNode;

    }

    private Node deepCopy2(Node node, Map<Node, Node> newNodeReference, Map<Node, Node> randomNodeRef) {
        if (node != null) {
            Node newNode = new Node(node.val);
            newNode.next = deepCopy2(node.next, newNodeReference, randomNodeRef);
            randomNodeRef.put(node, node.random);
            newNodeReference.put(node, newNode);
            return newNode;
        }
        return null;
    }


    public Node copyRandomList(Node head) {
        Node copiedNode = deepCopy(head);
        Node current = head;
        Map<Integer, Integer> randomPointerIndex = new HashMap<>();
        Map<Integer, Node> newNodeReference = new HashMap<>();
        int currentIndex = 0;
        while (current != null) {
            randomPointerIndex.put(currentIndex++, current.random == null ? null : findRandomPointerIndex(current.random, head));
            current = current.next;
        }
        current = copiedNode;
        currentIndex = 0;
        while (current != null) {
            newNodeReference.put(currentIndex++, current);
            current = current.next;
        }
        current = copiedNode;
        currentIndex = 0;
        while (current != null) {
            current.random = newNodeReference.get(randomPointerIndex.get(currentIndex++));
            current = current.next;
        }
        return copiedNode;

    }

    private Integer findRandomPointerIndex(Node random, Node head) {
        Node current = head;
        Integer index = 0;
        while (current != null && current != random) {
            current = current.next;
            index++;
        }
        return index;
    }

    private Node deepCopy(Node node) {
        if (node != null) {
            Node newNode = new Node(node.val);
            newNode.next = deepCopy(node.next);
            return newNode;
        }
        return null;
    }


}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }


}
