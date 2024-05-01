package com.example.companywise.leetcode.microsoft.hard;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 * <p>
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 * <p>
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 * <p>
 * <p>
 * <p>
 * Follow-up: Can you solve the problem in O(1) extra memory space?
 * <p>
 * Leetcode link : https://leetcode.com/problems/reverse-nodes-in-k-group/description/
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ReverseNodesInKGroup obj = new ReverseNodesInKGroup();
        ListNode result = obj.reverseKGroup(head, 2);
        System.out.print(result);


    }

    ListNode result = new ListNode(-1);
    ListNode resultHead = result;
    public ListNode reverseKGroup(ListNode head, int k) {
        return reverseNextKGroup(head, k);

    }

    private ListNode reverseNextKGroup(ListNode node, int k) {
        if (node != null) {
            int count = 1;
            ListNode current = node;
            ListNode currentHead = current;
            while (count < k && current != null) {
                current = current.next;
                count++;
            }
            ListNode tempNext = null;
            if (current != null && current.next != null) {
                tempNext = current.next;
            }
            if (count == k && current != null) {
                result.next = reverse(currentHead, k);
                result = currentHead;
                reverseNextKGroup(tempNext, k);
            } else {
                result.next = currentHead;
            }
        }
        return resultHead.next;


    }

    private ListNode reverse(ListNode start, int k) {
        ListNode current = start;
        ListNode prev = null;
        int count = 0;
        while (count < k) {
            ListNode tempNext = current.next;
            current.next = prev;
            prev = current;
            current = tempNext;
            count++;
        }
        return prev;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = dummy;
        while (cur.next != null) {
            for (int i = 0; i < k && cur != null; ++i) {
                cur = cur.next;
            }
            if (cur == null) {
                return dummy.next;
            }
            ListNode t = cur.next;
            cur.next = null;
            ListNode start = pre.next;
            pre.next = reverseList(start);
            start.next = t;
            pre = start;
            cur = pre;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode q = p.next;
            p.next = pre;
            pre = p;
            p = q;
        }
        return pre;
    }
}
