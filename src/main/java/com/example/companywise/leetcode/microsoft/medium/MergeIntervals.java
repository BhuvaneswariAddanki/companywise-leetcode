package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * Leetcode link : https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {

        List<Interval> intervalList = constructIntervalList(intervals);
        List<Interval> sortedList = intervalList.stream()
                .sorted(Comparator.comparing(Interval::getStart)
                        .thenComparing(Interval::getEnd))
                .collect(Collectors.toList());
        int index = 0;
        while (index < sortedList.size() - 1) {
            if (sortedList.get(index).start <= sortedList.get(index + 1).start && sortedList.get(index).end >= sortedList.get(index + 1).start) {
                sortedList.get(index).setEnd(Math.max(sortedList.get(index + 1).end, sortedList.get(index).end));
                sortedList.remove(sortedList.get(index + 1));
            } else {
                index++;
            }
        }
        int[][] result = new int[sortedList.size()][2];
        int i = 0;
        for (Interval interval : sortedList) {
            result[i][0] = interval.start;
            result[i][1] = interval.end;
            i++;
        }
        return result;
    }

    private List<Interval> constructIntervalList(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }
        return intervalList;
    }

    public class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
