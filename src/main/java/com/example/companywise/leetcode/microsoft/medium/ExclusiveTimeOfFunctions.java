package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 * <p>
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.
 * <p>
 * You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 * <p>
 * A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 * <p>
 * Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3,4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
 * Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
 * Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
 * Output: [8]
 * Explanation:
 * Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
 * Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
 * Function 0 (initial call) resumes execution then immediately calls itself again.
 * Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
 * Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
 * So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
 * <p>
 * Example 3:
 * <p>
 * Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
 * Output: [7,1]
 * Explanation:
 * Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
 * Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
 * Function 0 (initial call) resumes execution then immediately calls function 1.
 * Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
 * Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
 * So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 1 <= logs.length <= 500
 * 0 <= function_id < n
 * 0 <= timestamp <= 109
 * No two start events will happen at the same timestamp.
 * No two end events will happen at the same timestamp.
 * Each function has an "end" log for each "start" log.
 * <p>
 * Leetcode link : https://leetcode.com/problems/exclusive-time-of-functions/description/
 */
public class ExclusiveTimeOfFunctions {
    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] result = new int[n];
        if (n == 0 || logs == null || logs.isEmpty()) {
            return result;
        }

        // This stack will store the function ids
        Deque<Integer> stack = new ArrayDeque<>();
        // Previous time = start/resume time of the previous function
        int prevTime = 0;

        for (String log : logs) {
            String[] logParts = log.split(":");
            int curTime = Integer.parseInt(logParts[2]);

            if ("start".equals(logParts[1])) {
                // Function is starting now
                if (!stack.isEmpty()) {
                    // Add the exclusive time of previous function
                    result[stack.peek()] += curTime - prevTime;
                }
                stack.push(Integer.parseInt(logParts[0]));
                // Setting the start time for next log.
                prevTime = curTime;
            } else {
                // Function is ending now.
                // Make sure to +1 to as end takes the whole unit of time.
                result[stack.pop()] += curTime - prevTime + 1;
                // prevTime = resume time of the function. Thus adding 1.
                prevTime = curTime + 1;
            }
        }

        return result;
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] solution = new int[n];
        int i = 0;
        while (i < logs.size()) {
            LogLine logLine = readLogLine(logs.get(i));
            Result result = helper(logs, solution, i + 1, logLine.functionId, logLine.time);
            i = result.logIndex + 1;
        }

        return solution;
    }

    Result helper(List<String> logs, int[] solution, int logIndex, int functionId, int startTime) {
        int ignoredTime = 0;
        while (logIndex < logs.size()) {
            String log = logs.get(logIndex);
            LogLine logLine = readLogLine(log);
            if (logLine.start) {
                Result result = helper(logs, solution, logIndex + 1, logLine.functionId, logLine.time);
                logIndex = result.logIndex + 1;
                ignoredTime += result.totalElapsedTime;
            } else {
                int exclusiveTimeLoggedSoFar = solution[logLine.functionId];
                solution[logLine.functionId] = exclusiveTimeLoggedSoFar + logLine.time - startTime + 1 - ignoredTime;
                return (new Result(logIndex, logLine.time - startTime + 1));
            }
        }
        return new Result(0, 0);
    }

    LogLine readLogLine(String log) {
        int i = 0;
        int nextId = 0;
        while (Character.isDigit(log.charAt(i))) {
            nextId = nextId * 10;
            nextId += (log.charAt(i) - '0');
            i++;
        }
        i++; // skip ':'
        boolean start = false;
        if (log.charAt(i) == 's') {
            start = true;
        }
        while (log.charAt(i) != ':') {
            i++;
        }
        i++; // skip ':'
        int time = 0;
        while (i < log.length()) {
            time = time * 10;
            time += (log.charAt(i) - '0');
            i++;
        }

        return new LogLine(nextId, start, time);
    }


class Result {
    int totalElapsedTime;
    int logIndex;

    Result(int logIndex, int totalElapsedTime) {
        this.totalElapsedTime = totalElapsedTime;
        this.logIndex = logIndex;
    }
}

class LogLine {
    int functionId;
    boolean start;
    int time;

    LogLine(int functionId, boolean start, int time) {
        this.functionId = functionId;
        this.start = start;
        this.time = time;
    }

}
}
