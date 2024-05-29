package com.example.companywise.leetcode.microsoft.hard;

import com.example.companywise.leetcode.microsoft.ListNode;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 * <p>
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * <p>
 * Implement the MedianFinder class:
 * <p>
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 * <p>
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 * <p>
 * <p>
 * <p>
 * Follow up:
 * <p>
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * <p>
 * Leetcode link : https://leetcode.com/problems/find-median-from-data-stream/description/
 */
public class FindMedianFromDataStream {
    public static void main(String[] args) {
        FindMedianFromDataStream medianFinder = new FindMedianFromDataStream();
        medianFinder.addNum(-1);
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());

    }

    private ListNode listNode;
    private int size = 0;

    public FindMedianFromDataStream() {

    }

    public void addNum(int num) {
        ListNode node = new ListNode(num);
        node.next = listNode;
        listNode = node;
        size++;
    }

    public double findMedian() {
        if (size == 0) {
            return 0.0;
        } else if (size == 1) {
            return listNode.val;
        } else if (size == 2) {
            return (listNode.val + listNode.next.val) / 2.0;
        }
        ListNode first = listNode;
        ListNode second = listNode;
        ListNode prev = null;
        while (second != null && second.next != null) {
            prev = first;
            first = first.next;
            second = second.next.next;
        }
        if (size % 2 == 0) {
            return (prev.val + first.val) / 2.0;
        } else {
            return first.val;
        }
    }
}
