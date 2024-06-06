package com.example.companywise.leetcode.microsoft.hard;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 */
public class BinaryTreeMaximumPathSum {


    // this variable is going to store maximum sum we have found
    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxUtil(root);
        return result;
    }

    public int maxUtil(TreeNode root) {

        if (root == null)
            return 0;
        // left & right variable is storing maximum path sum of left & right subtree
        int left = maxUtil(root.left);
        int right = maxUtil(root.right);
// checking for Case 1 here either we are coming from left subtree or right subtree in this case that's why taking maximum of left & right
        int max1 = Math.max(Math.max(left, right) + root.val, root.val);
        // checking for case 2 here and comparing it with previous case 1 value . In this case max sum is root's value + left child value+ right child value
        int max2 = Math.max(max1, left + right + root.val);
        // finally comparing the maximum sum we have got so far and updating its value checking case 3 as well

        result = Math.max(max2, result);
        return max1;

    }

    // just returns the nodes in post-order
    public Iterable<TreeNode> topSort(TreeNode root) {
        Deque<TreeNode> result = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode curr = stack.pop();
                result.push(curr);
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
        }
        return result;
    }

    public int maxPathSum2(TreeNode root) {
        int result = Integer.MIN_VALUE;
        Map<TreeNode, Integer> maxRootPath = new HashMap<>(); // cache
        maxRootPath.put(null, 0); // for simplicity we want to handle null nodes
        for (TreeNode node : topSort(root)) {
            // as we process nodes in post-order their children are already cached
            int left = Math.max(maxRootPath.get(node.left), 0);
            int right = Math.max(maxRootPath.get(node.right), 0);
            maxRootPath.put(node, Math.max(left, right) + node.val);
            result = Math.max(left + right + node.val, result);
        }
        return result;
    }
}
