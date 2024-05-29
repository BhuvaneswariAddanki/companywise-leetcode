package com.example.companywise.leetcode.microsoft.hard;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * <p>
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 * <p>
 * Leetcode link : https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        SerializeAndDeserializeBinaryTree obj = new SerializeAndDeserializeBinaryTree();
        String result = obj.serialize(root);
        TreeNode s = obj.deserialize(result);
        System.out.print(s);
    }

    int serializedTreeIndexPointer = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder levelOrder = new StringBuilder();
        if (root != null) {
            performLevelOrderTraversal(root, levelOrder);
            levelOrder.deleteCharAt(levelOrder.length() - 1);
        }

        return levelOrder.toString();
    }

    private void performLevelOrderTraversal(TreeNode root, StringBuilder levelOrder) {


        Deque<TreeNode> treeNodeDeque = new ArrayDeque<>();
        treeNodeDeque.offer(root);
        levelOrder.append(root.val);
        levelOrder.append(",");

        while (!treeNodeDeque.isEmpty()) {
            for (int i = treeNodeDeque.size(); i > 0; i--) {
                TreeNode current = treeNodeDeque.remove();

                if (current.left != null) {
                    treeNodeDeque.offer(current.left);
                    levelOrder.append(current.left.val);
                    levelOrder.append(",");
                } else {
                    levelOrder.append("null,");
                }
                if (current.right != null) {
                    treeNodeDeque.offer(current.right);
                    levelOrder.append(current.right.val);
                    levelOrder.append(",");
                } else {
                    levelOrder.append("null,");
                }
            }
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        String[] serializedArray = data.split(",");
        return treeBuilder(serializedArray);

    }

    TreeNode treeBuilder(String[] serializedArray) {
        Deque<TreeNode> treeNodeDeque = new ArrayDeque<>();
        TreeNode root = getNextNode(serializedArray);
        treeNodeDeque.offer(root);
        while (!treeNodeDeque.isEmpty()) {
            for (int i = treeNodeDeque.size(); i > 0; i--) {
                TreeNode current = treeNodeDeque.remove();
                current.left = getNextNode(serializedArray);
                if (current.left != null) {
                    treeNodeDeque.offer(current.left);
                }
                current.right = getNextNode(serializedArray);
                if (current.right != null) {
                    treeNodeDeque.offer(current.right);
                }
            }
        }
        return root;
    }


    Integer parseStringToInt(String s) {
        if (s.equalsIgnoreCase("null")) {
            return null;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private TreeNode getNextNode(String[] serializedArray) {
        if (serializedTreeIndexPointer >= serializedArray.length)
            return null;

        Integer a = parseStringToInt(serializedArray[serializedTreeIndexPointer++]);
        if (a == null) {
            return null;
        }
        return new TreeNode(a);
    }
}
