package com.example.companywise.leetcode.microsoft.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 * <p>
 * Example 1:
 * <p>
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * <p>
 * Example 2:
 * <p>
 * Input: [[7,10],[2,4]]
 * Output: true
 * <p>
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * <p>
 * <p>
 * Leetcode link : https://leetcode.ca/all/252.html
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 1; i < intervals.length; ++i) {
            int[] a = intervals[i - 1];
            int[] b = intervals[i];
            if (a[1] > b[0]) {
                return false;
            }
        }
        return true;
    }
}
