package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)
 * <p>
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * <p>
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 * <p>
 * The right-most node is also defined by the same way with left and right exchanged.
 * <p>
 * Example 1
 * <p>
 * Input:
 * 1
 * \
 * 2
 * / \
 * 3   4
 * <p>
 * Ouput:
 * [1, 3, 4, 2]
 * <p>
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 * <p>
 * <p>
 * <p>
 * Example 2
 * <p>
 * Input:
 * ____1_____
 * /          \
 * 2            3
 * / \          /
 * 4   5        6
 * / \      / \
 * 7   8    9  10
 * <p>
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 * <p>
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 * <p>
 * <p>
 * Leetcode link : https://leetcode.ca/all/545.html
 * https://leetcode.com/problems/boundary-of-binary-tree/description/
 * <p>
 * geekforgeeks : https://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 */
public class BoundaryOfBinaryTree {


    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        // root
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        // left boundary
        TreeNode t = root.left;
        while (t != null) {
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            t = t.left == null ? t.right : t.left;
        }
        // leaves
        addLeaves(root, res);

        // right boundary
        t = root.right;
        Deque<Integer> rightBoundary = new ArrayDeque<>();
        while (t != null) {
            if (!isLeaf(t)) {
                rightBoundary.push(t.val);
            }
            t = t.right == null ? t.left : t.right;
        }
        while (!rightBoundary.isEmpty()) {
            res.add(rightBoundary.poll());
        }
        return res;
    }

    private void addLeaves(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (isLeaf(root)) {
                res.add(root.val);
                return;
            }
            if (root.left != null) {
                addLeaves(root.left, res);
            }
            if (root.right != null) {
                addLeaves(root.right, res);
            }
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

}
