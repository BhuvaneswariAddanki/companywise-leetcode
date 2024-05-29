package com.example.companywise.leetcode.microsoft.easy;

import com.example.companywise.leetcode.microsoft.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * In a binary tree, a lonely node is a node that is the only child of its parent node. The root of the tree is not lonely because it does not have a parent node.
 * <p>
 * Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. Return the list in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,4]
 * Output: [4]
 * Explanation: Light blue node is the only lonely node.
 * Node 1 is the root and is not lonely.
 * Nodes 2 and 3 have the same parent and are not lonely.
 * <p>
 * Example 2:
 * <p>
 * Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2]
 * Output: [6,2]
 * Explanation: Light blue nodes are lonely nodes.
 * Please remember that order doesn't matter, [2,6] is also an acceptable answer.
 * <p>
 * Example 3:
 * <p>
 * <p>
 * Input: root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
 * Output: [77,55,33,66,44,22]
 * Explanation: Nodes 99 and 88 share the same parent. Node 11 is the root.
 * All other nodes are lonely.
 * <p>
 * Example 4:
 * <p>
 * Input: root = [197]
 * Output: []
 * <p>
 * Example 5:
 * <p>
 * Input: root = [31,null,78,null,28]
 * Output: [78,28]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * Each node's value is between [1, 10^6].
 * <p>
 * <p>
 * <p>
 * Leetcode link : https://leetcode.ca/all/1469.html
 */
public class FindAllTheLonelyNodes {
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode root) {
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        if (root.left == null) {
            ans.add(root.right.val);
        }
        if (root.right == null) {
            ans.add(root.left.val);
        }
        dfs(root.left);
        dfs(root.right);
    }
}
