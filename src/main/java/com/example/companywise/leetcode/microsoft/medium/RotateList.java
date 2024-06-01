package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 * <p>
 * Leetcode link : https://leetcode.com/problems/rotate-list/description/
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        ListNode first = head;
        int length = length(head);
        if (length <= 1 || (k % length) == 0) {
            return head;
        }
        int count = k % length;
        while (count > 0) {
            first = first.next;
            count--;
        }
        ListNode second = head;
        while (first.next != null) {
            second = second.next;
            first = first.next;

        }
        first.next = head;
        head = second.next;
        second.next = null;
        return head;
    }

    private int length(ListNode head) {
        ListNode current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
