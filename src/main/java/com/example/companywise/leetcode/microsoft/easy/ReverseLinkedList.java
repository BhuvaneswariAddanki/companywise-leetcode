package com.example.companywise.leetcode.microsoft.easy;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * <p>
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 * <p>
 * <p>
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 * <p>
 * Leetcode link : https://leetcode.com/problems/reverse-linked-list/description/
 */
public class ReverseLinkedList {

    public ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode tempNext = current.next;
            current.next = prev;
            prev = current;
            current = tempNext;
        }
        return prev;
    }

    public ListNode reverseRecursive(ListNode current, ListNode prev) {
        if (current == null) {
            return prev;
        }
        ListNode tempNext = current.next;
        current.next = prev;
        return reverseRecursive(tempNext, current);
    }
}
