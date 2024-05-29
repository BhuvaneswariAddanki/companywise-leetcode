package com.example.companywise.leetcode.microsoft.medium;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/min-stack/description/
 */
public class MinStack {

    private MinStackNode head;

    public MinStack() {
        head = new MinStackNode(null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        Integer min = Math.min(head.getMinSoFar(), val);
        MinStackNode minStackNode = new MinStackNode(val, min);
        minStackNode.setNext(head);
        head = minStackNode;
    }

    public void pop() {
        if (head.val != null) {
            head = head.getNext();
        }
    }

    public int top() {
        if (head.val != null) {
            return head.val;
        }
        return -1;
    }

    public int getMin() {
        return head.getMinSoFar();
    }

    public class MinStackNode {
        private Integer val;
        private Integer minSoFar;
        private MinStackNode next;

        public MinStackNode(Integer val, Integer minSoFar) {
            this.val = val;
            this.minSoFar = minSoFar;
        }


        public MinStackNode getNext() {
            return next;
        }

        public void setNext(MinStackNode next) {
            this.next = next;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public Integer getMinSoFar() {
            return minSoFar;
        }

        public void setMinSoFar(Integer minSoFar) {
            this.minSoFar = minSoFar;
        }
    }

}
