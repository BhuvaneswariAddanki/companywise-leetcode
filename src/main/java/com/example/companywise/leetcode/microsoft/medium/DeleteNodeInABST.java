package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 * <p>
 * Basically, the deletion can be divided into two stages:
 * <p>
 * Search for a node to remove.
 * If the node is found, delete the node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * <p>
 * Example 3:
 * <p>
 * Input: root = [], key = 0
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -105 <= Node.val <= 105
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -105 <= key <= 105
 * <p>
 * <p>
 * <p>
 * Follow up: Could you solve it with time complexity O(height of tree)?
 * <p>
 * Leetcode link : https://leetcode.com/problems/delete-node-in-a-bst/description/
 */
public class DeleteNodeInABST {

    public TreeNode deleteNode(TreeNode root, int key) {
        return delete(root, key);

    }

    public TreeNode delete(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.val) {
            node.left = delete(node.left, key);
        } else if (key > node.val) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                TreeNode leftMostInRight = node.right;
                while (leftMostInRight.left != null) {
                    leftMostInRight = leftMostInRight.left;
                }
                node.val = leftMostInRight.val;
                node.right = delete(node.right, leftMostInRight.val);
                return node;
            }
        }
        return node;
    }

}
