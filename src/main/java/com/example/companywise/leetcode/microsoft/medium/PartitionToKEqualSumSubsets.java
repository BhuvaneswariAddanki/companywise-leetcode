package com.example.companywise.leetcode.microsoft.medium;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 * Example 2:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     1 <= k <= nums.length <= 16
 *     1 <= nums[i] <= 104
 *     The frequency of each element is in the range [1, 4]
 *
 *
 * Leetcode link : https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        //sum%k must equal to 0 if not just return false
        //if we have to to divide the array greater than array size retun false(we can't)
        if (sum % k != 0 || nums.length < k) {
            return false;
        }

        //sort so we can take last element and start filling our bucket
        Arrays.sort(nums);
        //our target is sum/k and we have to find this in nums, k times then it is valid
        return canDoFurtherPartition(nums, sum / k, nums.length - 1, new int[k]);

    }

    private boolean canDoFurtherPartition(int[] nums, int target, int index, int[] bucket) {

        //we have taken all the elements
        if (index == -1) {
            return true;
        }

        //start filling the buckets
        for (int j = 0; j < bucket.length; j++) {
            //can we take this ith element
            if (bucket[j] + nums[index] <= target) {
                //if we take this element
                bucket[j] += nums[index];

                //go to next element (in our case go to smallest ele bcz we have sorted)
                //if we can fill all buckets then return true
                if (canDoFurtherPartition(nums, target, index - 1, bucket)) {
                    return true;
                }
                bucket[j] -= nums[index];
            }
            //if our bucket is empty means we have not taken any elements in the buckets
            if (bucket[j] == 0)
               return false;
        }
        //all buckets are full but i is pointing to some element (elements still left)
        //or our bucket is empty means we haven't take any element (not valid)
        return false;
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return false;
        }

        int arrayLength = nums.length;
        int targetSum = 0;
        for(int i = 0; i < arrayLength; i++) {
            targetSum += nums[i];
        }
        if(targetSum % k != 0) {
            return false;
        }
        targetSum /= k;

        nums = quickSort(nums, 0, arrayLength - 1);
        boolean[] visited = new boolean[arrayLength];

        return depthFirstSearch(nums, visited, 0, 0, 0, targetSum, k);
    }

    boolean depthFirstSearch(int[] nums, boolean[] visited, int start, int currentSum, int count, int targetSum, int k) {
        if(count == k - 1) {
            return true;
        }
        if(currentSum == targetSum) {
            return depthFirstSearch(nums, visited, 0, 0, count + 1, targetSum, k);
        }

        for(int i = start; i < nums.length; i++) {
            if(visited[i]) {
                continue;
            }
            if(currentSum + nums[i] <= targetSum) {
                visited[i] = true;
                if(depthFirstSearch(nums, visited, i + 1, currentSum + nums[i], count, targetSum, k)) {
                    return true;
                }
                visited[i] = false;
            }

            if(currentSum == 0 || currentSum + nums[i] == targetSum) {
                return false;
            }

            while(i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return false;
    }

    int[] quickSort(int[] array, int low, int high) {
        if(low >= high) {
            return array;
        }

        int greaterThanPivot = low, i = low + 1, lessThanPivot = high;
        int pivot = array[low];

        while(i <= lessThanPivot) {
            if(array[i] > pivot) {
                array = swap(array, greaterThanPivot++, i++);
            }
            else if(array[i] < pivot) {
                array = swap(array, i, lessThanPivot--);
            }
            else {
                i++;
            }
        }
        array = quickSort(array, low, greaterThanPivot - 1);
        array = quickSort(array, lessThanPivot + 1, high);

        return array;
    }

    int[] swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        return array;
    }
}
