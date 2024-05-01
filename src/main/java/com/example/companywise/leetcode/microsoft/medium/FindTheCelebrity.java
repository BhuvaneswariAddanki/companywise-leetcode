package com.example.companywise.leetcode.microsoft.medium;

import java.util.List;

/**
 * In a party of N people, only one person is known to everyone. Such a person may be present at the party,
 * if yes, (s)he doesn’t know anyone at the party. We can only ask questions like “does A know B? “.
 * Find the stranger (celebrity) in the minimum number of questions.
 * We can describe the problem input as an array of numbers/characters representing persons in the party.
 * We also have a hypothetical function HaveAcquaintance(A, B)
 * which returns true if A knows B, and false otherwise. How can we solve the problem?
 * <p>
 * Input:
 * MATRIX = { {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0} }
 * Output: id = 2
 * Explanation: The person with ID 2 does not know anyone but everyone knows him
 * <p>
 * Input:
 * MATRIX = { {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 1, 0} }
 * Output: No celebrity
 * Explanation: There is no celebrity.
 * <p>
 * <p>
 * <p>
 * Geeksforgeeks : https://www.geeksforgeeks.org/the-celebrity-problem/
 */
public class FindTheCelebrity {

    public static int celebrity(int[][] M, int n) {
        // This function returns the celebrity
        // index 0-based (if any)

        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (M[j][i] == 1) // j knows i
                j--;
            else // j doesnt know i so i cant be celebrity
                i++;
        }
        // i points to our celebrity candidate
        int candidate = i;

        // Now, all that is left is to check that whether
        // the candidate is actually a celebrity i.e: he is
        // known by everyone but he knows no one
        for (i = 0; i < n; i++) {
            if (i != candidate && M[i][candidate] == 0
                    || M[candidate][i] == 1) {
                return -1;
            }
        }
        // if we reach here this means that the candidate
        // is really a celebrity
        return candidate;
    }

    public static int celebrity(List<List<Integer>> M, int n) {
        // This function returns the celebrity
        // index 0-based (if any)

        int i = 0;
        int j = n - 1;
        while (i < j) {
            if (M.get(j).get(i) == 1) // j knows i
                j--;
            else // j doesnt know i so i cant be celebrity
                i++;
        }
        // i points to our celebrity candidate
        int candidate = i;

        // Now, all that is left is to check that whether
        // the candidate is actually a celebrity i.e: he is
        // known by everyone but he knows no one
        for (i = 0; i < n; i++) {
            if (i != candidate && M.get(i).get(candidate) == 0
                    || M.get(candidate).get(i) == 1) {
                return -1;
            }
        }
        // if we reach here this means that the candidate
        // is really a celebrity
        return candidate;
    }
}
