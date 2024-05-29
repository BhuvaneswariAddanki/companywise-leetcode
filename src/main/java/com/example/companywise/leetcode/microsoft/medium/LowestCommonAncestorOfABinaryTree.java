package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [2, 105].
 *     -109 <= Node.val <= 109
 *     All Node.val are unique.
 *     p != q
 *     p and q will exist in the tree.
 *
 *
 * Leetcode link : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {


        return findEle(root, p, q);

    }

    private TreeNode findEle(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return null;
        }
        if (node.val == p.val || node.val == q.val) {
            return node;
        }
        TreeNode nodeLeft = findEle(node.left, p, q);
        TreeNode nodeRight = findEle(node.right, p, q);
        if (nodeLeft != null && nodeRight != null) {
            return node;
        } else if (nodeLeft != null) {
            return nodeLeft;
        } else {
            return nodeRight;
        }
    }
    /**
     * No guarantee that p and q exist in the binary tree
     * return -1/NULL if any of the given keys are not present in the tree
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        boolean[] eleFound = new boolean[2];
        TreeNode lca = findLcaElement(root, p, q, eleFound);
        return eleFound[0] && eleFound[1] ? lca : null;
    }

    private TreeNode findLcaElement(TreeNode root, int p, int q, boolean[] eleFound) {
        if (root == null) {
            return null;
        }
        TreeNode temp = null;
        if (root.val == p) {
            eleFound[0] = true;
            temp = root;
        }
        if (root.val == q) {
            eleFound[1] = true;
            temp = root;
        }
        TreeNode left = findLcaElement(root.left, p, q, eleFound);
        TreeNode right = findLcaElement(root.right, p, q, eleFound);
        if (temp != null) {
            return temp;
        }
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }
}
