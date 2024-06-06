package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * <p>
 * Follow up: Could you do it in one pass?
 * <p>
 * Leetcode link : https://leetcode.com/problems/reverse-linked-list-ii/description/
 */
public class ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(3, new ListNode(5));
        ReverseLinkedListII obj = new ReverseLinkedListII();
        obj.reverseBetween(listNode, 1, 2);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode current = head;
        ListNode prev = null;
        int i = 1;
        while (i < left) {
            prev = current;
            current = current.next;
            i++;
        }
        ListNode leftNode = current;
        ListNode prev1 = null;
        while (i <= right) {
            ListNode tempNext = current.next;
            current.next = prev1;
            prev1 = current;
            current = tempNext;
            i++;
        }

        if (current != null) {
            leftNode.next = current;
        }
        if (prev != null) {
            prev.next = prev1;
            return head;
        } else {
            return prev1;
        }
    }
}
