package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following 3-ary tree
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * N is in the range of [1, 1000]
 * Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 * <p>
 * Leetcode link : https://leetcode.ca/all/428.html
 */
public class serializeAndDeserializeNAryTree {
    private static final String SPLITER = ",";
    private static final String NULL_NODE = "#";

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    // pre-order traversal
    private void buildString(Node node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_NODE).append(SPLITER).append(0).append(SPLITER); // size=0
        } else {
            sb.append(node.val).append(SPLITER).append(node.children.size()).append(SPLITER);
            for (Node child : node.children) {
                buildString(child, sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }

        LinkedList<String> nodesList = new LinkedList<>(Arrays.asList(data.split(SPLITER)));
        return buildTree(nodesList);
    }

    private Node buildTree(LinkedList<String> nodes) {
        // @note: key is here, just keep popping the 1st as root of current sub-tree
        String val = nodes.removeFirst();

        if (val.equals(NULL_NODE)) {
            return null;
        } else {
            Node node = new Node(Integer.parseInt(val));
            int size = Integer.parseInt(nodes.removeFirst()); // to get children size

            node.children = new ArrayList<>(size);
            for (int i = 0; i < size; ++i) {
                node.children.add(buildTree(nodes));
            }

            return node;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
