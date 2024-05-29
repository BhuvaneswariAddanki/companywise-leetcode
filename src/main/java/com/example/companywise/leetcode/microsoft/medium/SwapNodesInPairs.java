package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1]
 * Output: [1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * <p>
 * Leetcode link : https://leetcode.com/problems/swap-nodes-in-pairs/description/
 */
public class SwapNodesInPairs {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        SwapNodesInPairs swapNodesInPairs = new SwapNodesInPairs();
        ListNode result = swapNodesInPairs.swapPairs(head);
        System.out.print(result);
    }

    public ListNode swapPairs(ListNode head) {

        ListNode prev = null;
        ListNode prevTail = null;
        ListNode current = head;
        while (current != null) {
            ListNode tempNext = current.next;
            if (prev != null) {
                swap(prev, current, prevTail, tempNext);
                if (prevTail == null) {
                    head = current;
                }
                prevTail = prev;
                prev = null;
            } else {
                prev = current;
            }
            current = tempNext;
        }
        return head;
    }

    private void swap(ListNode prev, ListNode current, ListNode prevTail, ListNode tempNext) {

        current.next = prev;
        if (prevTail != null) {
            prevTail.next = current;
        }
        prev.next = tempNext;
    }

}
