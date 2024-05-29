package com.example.companywise.leetcode.microsoft.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 *
 * Example 2:
 *
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 *
 *
 *
 * Constraints:
 *
 *     1 <= points.length <= 300
 *     points[i].length == 2
 *     -104 <= xi, yi <= 104
 *     All the points are unique.
 *
 *
 *  Leetcode link : https://leetcode.com/problems/max-points-on-a-line/description/
 */
public class MaxPointsOnALine {

    public int maxPoints(int[][] points) {

        if(points.length <= 2) return points.length;
        final int inf = 999999;
        int maxPoints = 0;

        for(int i=0; i < points.length; i++){

            Map<Double, Integer> map = new HashMap<>();

            for(int j=i+1; j<points.length; j++){
                double slope = 0;

                // For Avoiding division by 0
                if(points[j][0]-points[i][0] != 0)
                    slope = ((double)(points[j][1]-points[i][1])/(double)(points[j][0]-points[i][0]));

                else
                    slope = inf; // Assigning slope as infinity in case of division by 0

                if(slope == -0.0) slope = 0.0; // because 0.0 and -0.0 are treated as different keys by map. But we want to avoid it.

                map.put(slope, map.getOrDefault(slope, 0)+1);
                maxPoints = Math.max(maxPoints , map.get(slope)+1);
            }
        }
        return maxPoints;
    }
}
