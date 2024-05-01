package com.example.companywise.leetcode.microsoft.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * Given n rectangular buildings in a 2-dimensional city, computes the skyline of these buildings, eliminating hidden lines. The main task is to view buildings from aside and remove all sections that are not visible.
 * <p>
 * All buildings share a common bottom and every building is represented by a triplet (left, ht, right)
 * <p>
 * left: is x coordinated on the left side (or wall)
 * right: is x coordinate of the right side.
 * ht: is the height of the building.
 * <p>
 * A skyline is a collection of rectangular strips. A rectangular strip is represented as a pair (left, ht) where left is x coordinate of the left side of the strip and ht is the height of the strip.
 * <p>
 * Examples:
 * <p>
 * Input: buildings[][] = { {1, 11, 5}, {2, 6, 7}, {3, 13, 9}, {12, 7, 16}, {14, 3, 25}, {19, 18, 22}, {23, 13, 29}, {24, 4, 28} }
 * Output: { {1, 11}, {3, 13}, {9, 0}, {12, 7}, {16, 3}, {19, 18}, {22, 3}, {23, 13}, {29, 0} }
 * Explanation:
 * The skyline is formed based on the key-points (representing by “green” dots)
 * eliminating hidden walls of the buildings.
 * <p>
 * <p>
 * Input: buildings[ ][ ] = { {1, 11, 5} }
 * Output: { {1, 11}, {5, 0} }
 * geekforgeeks : https://www.geeksforgeeks.org/the-skyline-problem-set-2/
 * Leetcode link : https://leetcode.com/problems/the-skyline-problem/description/
 */
public class TheSkylineProblem {
    /**
     * From the given triplets for each building, retrieve the left wall location, height and right wall location value.
     * Store the left wall with its negative value of height and the right wall with its actual height as a array of pairs.
     * This is done in order to distinguish between left and right walls of the same building.
     * Sort the walls in ascending order.
     * Traverse the array walls, if a left wall is found, store the height of the left wall in the multiset M. Otherwise, if a right wall is encountered, remove its corresponding height from the multiset.
     * Check if the top value has changed or not. If it has changed, then update the top value and store the current wall’s abscissa(x-coordinate) value and the updated top value in a array as skyline.
     * Print the value pairs stored in the skyline array.
     */
    public static List<List<Integer>> createSkyline(int[][] buildings) {
        List<int[]> wall = new ArrayList<>();
        for (int[] building : buildings) {
            wall.add(new int[]{building[0], -building[1]});
            wall.add(new int[]{building[2], building[1]});
        }
        wall.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        List<List<Integer>> skyline = new ArrayList<>();
        TreeSet<Integer> leftWallHeight = new TreeSet<>(Collections.singleton(0));
        int top = 0;
        for (int[] w : wall) {
            if (w[1] < 0) {
                leftWallHeight.add(-w[1]);
            } else {
                leftWallHeight.remove(w[1]);
            }
            int curTop = leftWallHeight.last();
            if (curTop != top) {
                top = curTop;
                skyline.add(Arrays.asList(w[0], top));
            }
        }
        return skyline;
    }
}
