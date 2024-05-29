package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an absolute path for a Unix-style file system, which begins with a slash '/', transform this path into its simplified canonical path.
 * <p>
 * In Unix-style file system context, a single period '.' signifies the current directory, a double period ".." denotes moving up one directory level, and multiple slashes such as "//" are interpreted as a single slash. In this problem, treat sequences of periods not covered by the previous rules (like "...") as valid names for files or directories.
 * <p>
 * The simplified canonical path should adhere to the following rules:
 * <p>
 * It must start with a single slash '/'.
 * Directories within the path should be separated by only one slash '/'.
 * It should not end with a slash '/', unless it's the root directory.
 * It should exclude any single or double periods used to denote current or parent directories.
 * <p>
 * Return the new path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: path = "/home/"
 * <p>
 * Output: "/home"
 * <p>
 * Explanation:
 * <p>
 * The trailing slash should be removed.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: path = "/home//foo/"
 * <p>
 * Output: "/home/foo"
 * <p>
 * Explanation:
 * <p>
 * Multiple consecutive slashes are replaced by a single one.
 * <p>
 * Example 3:
 * <p>
 * Input: path = "/home/user/Documents/../Pictures"
 * <p>
 * Output: "/home/user/Pictures"
 * <p>
 * Explanation:
 * <p>
 * A double period ".." refers to the directory up a level.
 * <p>
 * Example 4:
 * <p>
 * Input: path = "/../"
 * <p>
 * Output: "/"
 * <p>
 * Explanation:
 * <p>
 * Going one level up from the root directory is not possible.
 * <p>
 * Example 5:
 * <p>
 * Input: path = "/.../a/../b/c/../d/./"
 * <p>
 * Output: "/.../b/d"
 * <p>
 * Explanation:
 * <p>
 * "..." is a valid name for a directory in this problem.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 * <p>
 * <p>
 * Leetcode link : https://leetcode.com/problems/simplify-path/description/
 */
public class SimplifyPath {
    public static void main(String[] args) {
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.print(" result : " + simplifyPath.simplifyPath("/home/"));
        System.out.print(" result : " + simplifyPath.simplifyPath("/home//foo/"));
        System.out.print(" result : " + simplifyPath.simplifyPath("/home/user/Documents/../Pictures"));
        System.out.print(" result : " + simplifyPath.simplifyPath("/.../a/../b/c/../d/./"));
    }

    public String simplifyPath(String path) {
        String[] folderPaths = path.split("/");
        int left = -1;
        int right = 0;
        while (right < folderPaths.length) {
            String folderPath = folderPaths[right];
            if (null != folderPath && !folderPath.isEmpty() && !folderPath.equals(".")) {
                if (folderPath.equals("..") && left != -1) {
                    left = left - 1;
                } else {
                    folderPaths[++left] = folderPath;
                }
            }
            right++;

        }
        if (left == -1) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        if (!"..".equals(folderPaths[0])) {
            result.append("/").append(folderPaths[0]);
        }
        for (int i = 1; i <= left; i++) {
            result.append("/").append(folderPaths[i]);
        }
        if (result.length() == 0) {
            return "/";
        }
        return result.toString();
    }

    public String simplifyPath2(String path) {
        String[] paths = path.split("/");
        Deque<String> pathsQueue = new ArrayDeque<>();

        for (String folderPath : paths) {
            if (null != folderPath && !folderPath.isEmpty() && !folderPath.equals(".")) {
                if (folderPath.equals("..")) {
                    if (!pathsQueue.isEmpty()) {
                        pathsQueue.removeLast();
                    } else {
                        pathsQueue.offer(folderPath);
                    }
                } else {
                    pathsQueue.offer(folderPath);
                }
            }

        }
        if (pathsQueue.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        String first = pathsQueue.remove();
        if (!"..".equals(first)) {
            result.append("/").append(first);
        }
        while (!pathsQueue.isEmpty()) {
            result.append("/").append(pathsQueue.remove());
        }
        if (result.length() == 0) {
            return "/";
        }

        return result.toString();
    }
}
