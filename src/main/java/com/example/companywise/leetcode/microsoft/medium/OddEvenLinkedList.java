package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
 * <p>
 * The first node is considered odd, and the second node is even, and so on.
 * <p>
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 * <p>
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the linked list is in the range [0, 104].
 * -106 <= Node.val <= 106
 * <p>
 * Leetcode link : https://leetcode.com/problems/odd-even-linked-list/description/
 */
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode currentEven = evenHead;
        ListNode currentOdd = head;

        while (currentEven!=null && currentEven.next != null) {
            currentOdd.next = currentEven.next;
            currentOdd = currentOdd.next;
            currentEven.next = currentOdd.next;
            currentEven = currentEven.next;
        }
        currentOdd.next = evenHead;
        return head;
    }
}
