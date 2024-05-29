package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * <p>
 * Leetcode link : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexMap.put(inorder[i], i);
        }
        return construct(preorder, inOrderIndexMap, 0, 0, inorder.length - 1);

    }

    private TreeNode construct(int[] preorder, Map<Integer, Integer> inOrderIndexMap, int preIndex, int inIndexStart, int inIndexEnd) {
        if (inIndexStart > inIndexEnd || preIndex >= preorder.length) {
            return null;
        }
        int val = preorder[preIndex];
        int pos = inOrderIndexMap.get(val);

        TreeNode root = new TreeNode(val);
        root.left = construct(preorder, inOrderIndexMap, preIndex + 1, inIndexStart, pos - 1);
        root.right = construct(preorder, inOrderIndexMap, preIndex + pos - inIndexStart + 1, pos + 1, inIndexEnd);
        return root;
    }
}
