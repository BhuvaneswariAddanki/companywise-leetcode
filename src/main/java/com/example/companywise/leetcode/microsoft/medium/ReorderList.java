package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * <p>
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * <p>
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 * <p>
 * Leetcode link : https://leetcode.com/problems/reorder-list/description/
 */
public class ReorderList {
    public void reorderList(ListNode head) {

        //1.find Middle element
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //2. Reverse the second half of the linked list
        ListNode prev = null;
        ListNode current = slow;
        while (current != null) {
            ListNode tempNext = current.next;
            current.next = prev;
            prev = current;
            current = tempNext;
        }
        //3. Merge fist half and reversed 2nd half of linked list
        ListNode first = head;
        ListNode second = prev;
        while (first != slow && second != null) {
            ListNode tempNextFirst = first.next;
            ListNode tempNextSecond = second.next;
            first.next = second;
            second.next = tempNextFirst;
            first = tempNextFirst;
            second = tempNextSecond;
        }
        if (first != null && first.next != null) {
            first.next.next = null;
        }

    }
}
