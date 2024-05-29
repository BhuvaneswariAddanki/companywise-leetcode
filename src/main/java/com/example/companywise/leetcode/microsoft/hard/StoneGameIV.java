package com.example.companywise.leetcode.microsoft.hard;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there are n stones in a pile. On each player's turn,
 * that player makes a move consisting of removing any non-zero square number of stones in the pile.
 * <p>
 * Also, if a player cannot make a move, he/she loses the game.
 * <p>
 * Given a positive integer n, return true if and only if Alice wins the game otherwise return false,
 * assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: true
 * Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: false
 * Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).
 * <p>
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: true
 * Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 105
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/stone-game-iv/description/
 */
public class StoneGameIV {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            if (dp[i]) continue;
            for (int j = 1; i + j * j <= n; j++) {
                dp[i + j * j] = true;
            }
        }
        return dp[n];
    }
}
