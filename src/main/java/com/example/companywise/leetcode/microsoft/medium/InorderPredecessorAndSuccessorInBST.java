package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

/**
 * You need to find the inorder successor and predecessor of a given val .
 * In case the given val  is not found in BST, then return the two values within which this val  will lie.
 * <p>
 * GeekForGeeks link : https://www.geeksforgeeks.org/inorder-predecessor-successor-given-val -bst/
 */
public class InorderPredecessorAndSuccessorInBST {

    /**
     * Input: root node, val
     * output: predecessor node, successor node
     * <p>
     * 1. If root is NULL
     * then return
     * 2. if val  is found then
     * a. If its left subtree is not null
     * Then predecessor will be the right most
     * child of left subtree or left child itself.
     * b. If its right subtree is not null
     * The successor will be the left most child
     * of right subtree or right child itself.
     * return
     * 3. If val is smaller than root node
     * set the successor as root
     * search recursively into left subtree
     * else
     * set the predecessor as root
     * search recursively into right subtree
     */

    TreeNode pre, suc;

    public void findPreSuc(TreeNode root, int val) {

        // Base case
        if (root == null)
            return;

        // If val  is present at root
        if (root.val == val) {

            // The maximum value in left 
            // subtree is predecessor
            if (root.left != null) {
                TreeNode tmp = root.left;
                while (tmp.right != null)
                    tmp = tmp.right;

                pre = tmp;
            }

            // The minimum value in 
            // right subtree is successor
            if (root.right != null) {
                TreeNode tmp = root.right;

                while (tmp.left != null)
                    tmp = tmp.left;

                suc = tmp;
            }
            return;
        }

        // If val  is smaller than 
        // root's val , go to left subtree
        if (root.val > val) {
            suc = root;
            findPreSuc(root.left, val);
        }

        // Go to right subtree
        else {
            pre = root;
            findPreSuc(root.right, val);
        }
    }
}
