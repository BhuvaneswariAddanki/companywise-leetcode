package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * <p>
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        return zigzag(root);
    }

    private List<List<Integer>> zigzag(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> treeNodeDeque = new ArrayDeque<>();
        treeNodeDeque.offer(root);
        boolean flip = false;
        while (!treeNodeDeque.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = treeNodeDeque.size(); i > 0; i--) {
                TreeNode node = treeNodeDeque.remove();
                level.add(node.val);
                if (node.left != null) {
                    treeNodeDeque.offer(node.left);
                }
                if (node.right != null) {
                    treeNodeDeque.offer(node.right);
                }
            }
            if (!flip) {
                result.add(level);
            } else {
                List<Integer> reverseLevel = new ArrayList<>();
                for (int i = level.size() - 1; i >= 0; i--) {
                    reverseLevel.add(level.get(i));
                }
                result.add(reverseLevel);
            }

            flip = !flip;

        }
        return result;
    }
}
