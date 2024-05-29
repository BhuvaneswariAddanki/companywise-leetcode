package com.example.companywise.leetcode.microsoft.hard;

import com.example.companywise.leetcode.microsoft.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * <p>
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 * <p>
 * Leetcode link : https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class MergeKSortedLists {

    public ListNode mergeKListRecursive(ListNode[] lists, int si, int ei) {
        if (si == ei) return lists[si];
        int mid = si + (ei - si) / 2;
        return merge(mergeKListRecursive(lists, si, mid), mergeKListRecursive(lists, mid + 1, ei));
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        return mergeKListRecursive(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }
        PriorityQueue<ListNodeWithSize> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(ListNodeWithSize::getSize));
        for (ListNode listNode : lists) {
            priorityQueue.offer(new ListNodeWithSize(listNode, size(listNode)));
        }
        while (priorityQueue.size() > 1) {
            ListNodeWithSize list1 = priorityQueue.remove();
            ListNodeWithSize list2 = priorityQueue.remove();
            ListNode mergedList = merge(list1.listNode, list2.listNode);
            priorityQueue.offer(new ListNodeWithSize(mergedList, list2.size + list1.size));
        }
        return priorityQueue.remove().listNode;

    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode l1 = head1;
        ListNode l2 = head2;
        ListNode dummyHead = new ListNode(-1);
        ListNode result = dummyHead;
        while (l1 != null && l2 != null) {
            if (l2.val < l1.val) {
                result.next = l2;
                l2 = l2.next;
            } else {
                result.next = l1;
                l1 = l1.next;
            }
            result = result.next;
        }
        if (l1 != null) {
            result.next = l1;
        } else {
            result.next = l2;
        }
        return dummyHead.next;

    }

    private int size(ListNode list) {
        ListNode current = list;
        int size = 0;
        while (current != null) {
            current = current.next;
            size++;
        }
        return size;
    }

    public class ListNodeWithSize {
        ListNode listNode;
        int size;

        public ListNode getListNode() {
            return listNode;
        }

        public void setListNode(ListNode listNode) {
            this.listNode = listNode;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public ListNodeWithSize(ListNode listNode, int size) {
            this.listNode = listNode;
            this.size = size;
        }
    }
}
