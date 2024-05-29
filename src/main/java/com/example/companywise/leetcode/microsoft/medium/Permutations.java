package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 * Leetcode link : https://leetcode.com/problems/permutations/description/
 */
public class Permutations {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Permutations permutations = new Permutations();
        List<List<Integer>> result = permutations.permute(nums);
        System.out.print(result);
    }

    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> response = new ArrayList<>();
        if (nums.length == 0) {
            return response;
        }
        List<Integer> inputList = new ArrayList<>();
        for (int num : nums) {
            inputList.add(num);
        }
        backTrack(inputList, new ArrayList<>(), response);
        return response;

    }

    private void backTrack(List<Integer> choices, List<Integer> combSoFar, List<List<Integer>> result) {
        if (choices.isEmpty()) {
            result.add(new ArrayList<>(combSoFar));
        }
        int index = 0;
        int size = choices.size();
        while (index < size) {
            Integer choice = choices.get(index);
            combSoFar.add(choice);
            choices.remove(index);
            backTrack(new ArrayList<>(choices), new ArrayList<>(combSoFar), result);
            combSoFar.remove(combSoFar.size() - 1);
            choices.add(index, choice);
            index++;
        }
    }

    public List<List<Integer>> permute2(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> input = Arrays.stream(nums).boxed().collect(Collectors.toList());
        permutation(input, result, new ArrayList<>(), new boolean[input.size()]);
        return result;
    }

    private void permutation(List<Integer> possibilities, List<List<Integer>> result, List<Integer> ans, boolean[] used) {
        if (ans.size() == possibilities.size()) {
            result.add(new ArrayList<>(ans));
        } else {

            for (int i = 0; i < possibilities.size(); i++) {
                if (used[i]) continue;
                Integer ele = possibilities.get(i);
                ans.add(ele);
                used[i] = true;
                permutation(possibilities, result, ans, used);
                ans.remove(ele);
                used[i] = false;
            }
        }
    }
}
