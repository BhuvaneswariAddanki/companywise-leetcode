package com.example.companywise.leetcode.microsoft.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Medium
 * <p>
 * Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), find the minimum number of conference rooms required.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * [[0, 30],[5, 10],[15, 20]]
 * Output:
 * 2
 * <p>
 * Example 2:
 * <p>
 * Input:
 * [[7,10],[2,4]]
 * <p>
 * Output:
 * 1
 * <p>
 * Leetcode link : https://aaronice.gitbook.io/lintcode/sweep-line/meeting-rooms-ii
 */
public class MeetingRoomsII {
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public int minMeetingRooms3(Interval[] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }
        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length);

        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));

        // Add the first meeting
        allocator.add(intervals[0].end);

        // Iterate over remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (intervals[i].start >= allocator.peek()) {
                allocator.poll();
            }

            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end time.
            allocator.add(intervals[i].end);
        }

        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }

    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for (int start : starts) {
            if (start < ends[endsItr]) {
                rooms++;
            } else {
                endsItr++;
            }
        }
        return rooms;
    }

    public int minMeetingRooms2(Interval[] intervals) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>();
        int ans = 0, cnt = 0;
        for (Interval i : intervals) {
            int start = i.start;
            int end = i.end;
            tmap.put(start, tmap.getOrDefault(start, 0) + 1);
            tmap.put(end, tmap.getOrDefault(end, 0) - 1);
        }
        for (int k : tmap.keySet()) {
            cnt += tmap.get(k);
            ans = Math.max(ans, cnt);
        }
        return ans;
    }


}
