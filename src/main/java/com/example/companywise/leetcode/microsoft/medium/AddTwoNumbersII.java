package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * <p>
 * Example 2:
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * <p>
 * Example 3:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 * <p>
 * <p>
 * <p>
 * Follow up: Could you solve it without reversing the input lists?
 * <p>
 * Leetcode link : https://leetcode.com/problems/add-two-numbers-ii/description/
 */
public class AddTwoNumbersII {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(0);
        ListNode listNode2 = new ListNode(0);
        AddTwoNumbersII obj = new AddTwoNumbersII();
        ListNode result = obj.addTwoNumbers(listNode1, listNode2);
        System.out.print(result);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = getStack(l1);
        Deque<ListNode> stack2 = getStack(l2);
        ListNode prev = null;
        int carryForward = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = carryForward;
            if (!stack1.isEmpty()) {
                sum += stack1.pop().val;
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop().val;
            }
            ListNode node = new ListNode(sum % 10);
            node.next = prev;
            prev = node;
            carryForward = sum / 10;
        }
        if (carryForward != 0) {
            ListNode node = new ListNode(carryForward);
            node.next = prev;
            prev = node;
        }

        return prev;

    }

    private Deque<ListNode> getStack(ListNode l1) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode current = l1;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        return stack;
    }
}
