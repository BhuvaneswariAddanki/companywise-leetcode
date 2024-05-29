package com.example.companywise.leetcode.microsoft.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a C++ program, remove comments from it. The program source is an array of strings source where source[i] is the ith line of the source code.
 * This represents the result of splitting the original source code string by the newline character '\n'.
 * Leetcode link : https://leetcode.com/problems/remove-comments/description/
 */
public class RemoveComments {

    public static void main(String[] args) {
        String[] source = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ",
                "int a, b, c;", "/* This is a test", "   multiline  ",
                "   comment for ", "   testing */", "a = b + c;", "}"};
        RemoveComments removeComments = new RemoveComments();
        List<String> result = removeComments.removeComments(source);
        System.out.print(result);
    }
    public List<String> removeComments2(String[] source) {
        List<String> result = new ArrayList<>();
        StringBuilder strBuilder = null;
        boolean isCommentBlockOn = false;
        int strLen;
        for(String line: source) {
            if(!isCommentBlockOn) {
                strBuilder = new StringBuilder();
            }
            strLen = line.length();
            for(int index = 0; index < strLen; index++) {
                if (!isCommentBlockOn && (index + 1 < strLen) && line.charAt(index) == '/' && line.charAt(index + 1) == '*') {
                    isCommentBlockOn = true;
                    index++;
                } else if (isCommentBlockOn && (index + 1 < strLen) && line.charAt(index) == '*' && line.charAt(index + 1) == '/') {
                    isCommentBlockOn = false;
                    index++;
                } else if (!isCommentBlockOn && (index + 1 < strLen) && line.charAt(index) == '/' && line.charAt(index + 1) == '/') {
                    break;
                } else if (!isCommentBlockOn) {
                    strBuilder.append(line.charAt(index));
                }
            }
            if(!isCommentBlockOn && strBuilder.length() > 0) {
                result.add(strBuilder.toString());
            }
        }
        return result;
    }

    public List<String> removeComments(String[] source) {
        List<String> result = new ArrayList<>();
        boolean multiLineCommentOn = false;

        for (String l : source) {
            String line = l.trim();
            if (!line.isEmpty()) {
                if (!multiLineCommentOn && startOfMultiLineComment(line)) {
                    multiLineCommentOn = true;
                }
                if (!singleLineComment(line) && !multiLineCommentOn) {
                    result.add(l);
                }
                if (multiLineCommentOn && endOfMultiLineComment(line)) {
                    multiLineCommentOn = false;
                }
            } else {
                result.add(l);
            }
        }
        return result;
    }

    private boolean endOfMultiLineComment(String line) {
        return line.endsWith("*/");
    }

    private boolean startOfMultiLineComment(String line) {
        return line.startsWith("/*");
    }

    private boolean singleLineComment(String line) {
        return line.startsWith("//");
    }
}
