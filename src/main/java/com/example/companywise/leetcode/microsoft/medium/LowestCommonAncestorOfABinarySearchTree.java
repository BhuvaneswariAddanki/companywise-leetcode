package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the BST.
 * Leetcode link : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 */
public class LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q);
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root != null) {
            if (root.val == p.val || root.val == q.val) {
                return root;
            }
            if (p.val < root.val && q.val < root.val) {
                return lca(root.left, p, q);
            } else if (p.val > root.val && q.val > root.val) {
                return lca(root.right, p, q);
            } else {
                return root;
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestorWithNoGuarantee(TreeNode root, TreeNode p, TreeNode q) {
        boolean[] eleFound = new boolean[2];
        TreeNode result = lca2(root, p, q, eleFound);
        return eleFound[0] && eleFound[1] ? result : null;
    }

    private TreeNode lca2(TreeNode root, TreeNode p, TreeNode q, boolean[] eleFound) {
        if (root != null) {
            TreeNode temp = null;
            if (root.val == p.val) {
                eleFound[0] = true;
                temp = root;
            }
            if (root.val == q.val) {
                eleFound[1] = true;
                temp = root;
            }
            TreeNode left = null;
            TreeNode right = null;
            if (!eleFound[0] || !eleFound[1]) {
                if (p.val < root.val && q.val < root.val) {
                    left = lca2(root.left, p, q, eleFound);
                }
               else if (p.val > root.val && q.val > root.val) {
                    right = lca2(root.right, p, q, eleFound);
                } else {
                    left = lca2(root.left, p, q, eleFound);
                    right = lca2(root.right, p, q, eleFound);
                }
            }

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
        return null;
    }
}
