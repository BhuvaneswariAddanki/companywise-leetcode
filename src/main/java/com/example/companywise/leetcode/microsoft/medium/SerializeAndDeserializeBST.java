package com.example.companywise.leetcode.microsoft.medium;

import com.example.companywise.leetcode.microsoft.TreeNode;

/**
 * Serialization is converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You need to ensure that a binary search tree can be serialized to a string, and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: [2,1,3]
 *
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 *
 * Constraints:
 *
 *     The number of nodes in the tree is in the range [0, 104].
 *     0 <= Node.val <= 104
 *     The input tree is guaranteed to be a binary search tree.
 *
 * Leetcode link : https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 */
public class SerializeAndDeserializeBST {
    int serializedTreeIndexPointer;

    public String serialize(TreeNode root) {
        StringBuilder serializedTree = new StringBuilder();
        preorder(root, serializedTree);
        return serializedTree.toString();
    }

    void preorder(TreeNode root, StringBuilder serializedTree) {
        if (root == null) {
            return;
        }
        serializedTree.append(root.val).append(",");
        preorder(root.left, serializedTree);
        preorder(root.right, serializedTree);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String serializedTree) {
        serializedTreeIndexPointer = 0;
        String serializedArray[] = serializedTree.split(",");
        return treeBuilder(serializedArray, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    TreeNode treeBuilder(String serializedArray[], int min, int max) {
        if (serializedTreeIndexPointer >= serializedArray.length)
            return null;

        Integer a = parseStringToInt(serializedArray[serializedTreeIndexPointer]);
        if (a == null || a > max || a < min) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(serializedArray[serializedTreeIndexPointer++]));
        root.left = treeBuilder(serializedArray, min, root.val);
        root.right = treeBuilder(serializedArray, root.val, max);

        return root;
    }

    Integer parseStringToInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
