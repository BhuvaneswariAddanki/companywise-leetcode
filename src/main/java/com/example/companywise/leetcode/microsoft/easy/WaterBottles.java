package com.example.companywise.leetcode.microsoft.easy;

/**
 * There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.
 * <p>
 * The operation of drinking a full water bottle turns it into an empty bottle.
 * <p>
 * Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numBottles = 9, numExchange = 3
 * Output: 13
 * Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 9 + 3 + 1 = 13.
 * <p>
 * Example 2:
 * <p>
 * Input: numBottles = 15, numExchange = 4
 * Output: 19
 * Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
 * Number of water bottles you can drink: 15 + 3 + 1 = 19.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= numBottles <= 100
 * 2 <= numExchange <= 100
 * <p>
 * Leetcode link : https://leetcode.com/problems/water-bottles/description/
 */
public class WaterBottles {

    public int numWaterBottles(int numBottles, int numExchange) {
        // Initialize empty to know current empty bottles.
        int empty = numBottles;
        while(empty >= numExchange ){ //Check if we have sufficient empty bottles.
            // Increase numBottles by number of filled bottles we get through exchange
            numBottles += empty/numExchange;
            //Update number of empty bottles.
            empty = empty%numExchange+empty/numExchange;
        }
        //Return total number of bottles of water drank.
        return numBottles;
    }
}
