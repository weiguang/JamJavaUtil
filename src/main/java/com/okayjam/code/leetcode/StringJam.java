package com.okayjam.code.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class StringJam {

    public static void main(String[] args) {
//        System.out.println(new StringJam().convert("PAYPALISHIRING", 4));
//        System.out.println(new StringJam().reverse(1534236469));
//        System.out.println(new StringJam().myAtoi("-91283472332"));
//        System.out.println(new StringJam().myAtoi("+1"));
//        System.out.println(new StringJam().myAtoi("+-12"));
//        System.out.println(new StringJam().myAtoi("     +004500"));
//        System.out.println(new StringJam().myAtoi("20000000000000000000"));
//        System.out.println(new StringJam().isPalindrome(12321));
//        System.out.println(new StringJam().maxArea(new int[]{1,2,3,1000,9}));
//        System.out.println(new StringJam().intToRoman(58));
//        System.out.println(new StringJam().letterCombinations("273"));
//        System.out.println(new StringJam().isValid("()[]{}"));
//        System.out.println(new StringJam().lengthOfLastWord("a bb ccc"));
//        System.out.println(new StringJam().simplifyPath("/../"));
//        System.out.println(new StringJam().minDistance("horse", "ros"));
//        System.out.println(new StringJam().numDecodings("12"));
//        System.out.println(new StringJam().restoreIpAddresses("101023"));
//        System.out.println(new StringJam().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
//        System.out.println(new StringJam().isPalindrome1("A man, a plan, a canal: Panama"));
        System.out.println(new StringJam().partition("bb"));
    }

    /**
     * 131. 分割回文串(时间较长)
     * https://leetcode.cn/problems/palindrome-partitioning/
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) return new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        if (s.length() == 1) { List<String> t =  new ArrayList<>(); t.add(s); ans.add(t);  return ans;}
        for (int i = 0; i < s.length() ; i++) {
            String leftStr = s.substring(0, i +1);
            if (s.charAt(0) != s.charAt(i) ||  !new StringBuilder(leftStr).reverse().toString().equals(leftStr)) {
                continue;
            }
            if (i == s.length() - 1) {  List<String> left = new ArrayList<>(); left.add(leftStr);ans.add(left); break;}
            String rightStr = s.substring( i +1);
            List<List<String>> right = partition(rightStr);
                for (List<String> strings2 : right) {
                    strings2.add(0, leftStr);
                    ans.add(strings2);
                }
        }
        return ans;
    }

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        long sum = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            int nn1 = num1.length() - 1 - i;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int nn2 = num2.length() - 1 - j;
                sum += (long) ((long) n1 * n2 * Math.pow(10, nn1) * Math.pow(10, nn2));
            }
        }
        return String.valueOf(sum);
    }


    public boolean isPalindrome1(String s) {
        if (s.length() <= 1) { return true;}
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char c1 = s.charAt(start);
            if (!Character.isLetterOrDigit(c1) ) {  start++; continue; }
            char c2 = s.charAt(end);
            if (!Character.isLetterOrDigit(c2) ) { end--; continue; }
            if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }

            }
        }
        return dp[n][m];
    }


    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        restoreIpAddresses(s, 0, temp, ans);
        return ans;
    }

    public void restoreIpAddresses(String s, int i, List<String> temp, List<String> ans) {
        if (temp.size() == 4) {
            if (i >= s.length()) {
                ans.add(temp.stream().collect(Collectors.joining(".")));
            }
            return;
        }
        if (i >= s.length()) {
            return;
        }
        temp.add(s.charAt(i) + "");
        restoreIpAddresses(s, i + 1, temp, ans);
        temp.remove(temp.size() - 1);
        if (s.charAt(i) != '0' && i < s.length() - 1) {
            temp.add(s.substring(i, i + 2));
            restoreIpAddresses(s, i + 2, temp, ans);
            temp.remove(temp.size() - 1);
        }
        if (s.charAt(i) != '0' && i < s.length() - 2 && s.substring(i, i + 3).compareTo("255") <= 0) {
            temp.add(s.substring(i, i + 3));
            restoreIpAddresses(s, i + 3, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }

    public int numDecodings(String s) {
        int n = s.length();
        int a1 = 0, a2 = 1, a3 = 0;
        for (int i = 0; i < n; i++) {
            a3 = 0;
            if (s.charAt(i) != '0') {
                a3 += a2;
            }
            if (i > 0 && s.charAt(i - 1) != '0' && s.substring(i - 1, i + 1).compareTo("26") <= 0) {
                a3 += a1;
            }
            a1 = a2;
            a2 = a3;
        }
        return a3;
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m == 0 || n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // A插入
                    int left = dp[i][j - 1];
                    // A删除
                    int down = dp[i - 1][j];
                    // 替换
                    int leftDown = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(left, down), leftDown) + 1;
                }
            }
        }
        return dp[m][n];

    }

    public String simplifyPath(String path) {
        String[] split = path.split("/");
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("..")) {
                if (!ans.isEmpty()) {
                    ans.remove(ans.size() - 1);
                }
                continue;
            } else if (split[i].equals("") || split[i].equals(".")) {
                continue;
            }
            ans.add(split[i]);
        }
        return "/" + ans.stream().collect(Collectors.joining("/"));
    }


    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if ((long) mid * mid <= x) {
                l = mid + 1;
                ans = mid;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }


    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }
        if (b == null || b.length() == 0) {
            return a;
        }
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, flag = 0;
        while (i >= 0 || j >= 0) {
            int cur = flag;
            cur += i >= 0 ? a.charAt(i--) - '0' : 0;
            cur += j >= 0 ? b.charAt(j--) - '0' : 0;
            flag = cur / 2;
            ans.append(cur % 2);
        }
        if (flag == 1) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }


    public int[] plusOne(int[] digits) {
        int flag = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
                flag = 1;
            } else {
                digits[i] += flag;
                return digits;
            }
        }
//        if (flag == 1) {
//         int [] ans =  new int[digits.length +1];
//         ans[0] = flag;
//         System.arraycopy(digits, 0, ans, 1, digits.length);
//         return ans;
//        }
        // 跳出for循环就是全部为9的情况，那么结果就是高位为1，其他全部为0
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }

    public int lengthOfLastWord(String s) {
        int cur = s.length() - 1;
        while (s.charAt(cur) == ' ') {
            cur--;
        }
        int ans = 0;
        while (cur >= 0 && s.charAt(cur--) != ' ') {
            ans++;
        }
        return ans;
    }


    public List<String> generateParenthesis(int n) {
        ArrayList<String> ans = new ArrayList<>();
        String cur = "";
        generate(ans, cur, 0, 0, n);
        return ans;
    }

    public void generate(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }
        if (open < max) {
            generate(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            generate(ans, cur + ")", open, close + 1, max);
        }
    }


    Map<Character, Character> pairs = new HashMap<Character, Character>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
    }};

    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character c1 = pairs.get(c);
            if (c1 == null) {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty() || stack.pop() != c1) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    static final String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (int i = digits.length() - 1; i >= 0; i--) {
            String s = map[digits.charAt(i) - '0'];
            int size = result.size();
            List<String> curList = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (size == 0) {
                    curList.add(c + "");
                    continue;
                }
                for (String subResult : result) {
                    String s1 = c + subResult;
                    curList.add(s1);
                }
            }
            result = curList;
        }
        return result;
    }


    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = symbolValues.get(s.charAt(i));
            if (i < s.length() - 1 && symbolValues.get(s.charAt(i + 1)) > value) {
                ans -= value;
            } else {
                ans += value;
            }
        }

        int ansure;
        return ans;
    }

    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        while (num >= 1000) {
            num -= 1000;
            ans.append('M');
        }
        if (num >= 900) {
            num -= 900;
            ans.append("CM");
        }
        if (num >= 500) {
            num -= 500;
            ans.append('D');
        }
        if (num >= 400) {
            num -= 400;
            ans.append("CD");
        }
        while (num >= 100) {
            num -= 100;
            ans.append('C');
        }
        if (num >= 90) {
            num -= 90;
            ans.append("XC");
        }
        if (num >= 50) {
            num -= 50;
            ans.append('L');
        }
        if (num >= 40) {
            num -= 40;
            ans.append("XL");
        }
        while (num >= 10) {
            num -= 10;
            ans.append('X');
        }
        if (num >= 9) {
            num -= 9;
            ans.append("IX");
        }
        if (num >= 5) {
            num -= 5;
            ans.append('V');
        }
        if (num >= 4) {
            num -= 4;
            ans.append("IV");
        }
        while (num >= 1) {
            num -= 1;
            ans.append('I');
        }
        return ans.toString();
    }

    public int maxArea(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }

    public int myAtoi(String s) {
        s = s.trim();
        char flag = ' ';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == '-' || c == '+') && (sb.length() != 0 || flag == '+')) {
                break;
            } else if (c == ' ' && s.length() == 0) {
                continue;
            } else if ((c != '-' && c != '+') && (c < '0' || c > '9')) {
                break;
            }
            if (c == '0' && (sb.length() == 0 || (sb.length() == 1 && (sb.charAt(0) == '-' || sb.charAt(0) == '+')))) {
                flag = '+';
                continue;
            }
            sb.append(c);
            if (sb.length() > 12) {
                break;
            }
        }
        if (sb.length() == 0 || sb.toString().equals("-") || sb.toString().equals("+")) {
            return 0;
        }
        long ans = Long.parseLong(sb.toString());
        if (ans > Integer.MAX_VALUE) {
            ans = Integer.MAX_VALUE;
        }
        if (ans < Integer.MIN_VALUE) {
            ans = Integer.MIN_VALUE;
        }
        return (int) ans;
    }

    public int reverse(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) return 0;
        long sum = 0;
        int c = Math.abs(x);
        while (c != 0) {
            sum = sum * 10 + c % 10;
            c = c / 10;
        }
        sum = x > 0 ? sum : sum * -1;
        return (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) ? 0 : (int) sum;
    }


    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] sbs = new StringBuilder[numRows];
        sbs[0] = new StringBuilder(s.length());
        int t = 0;
        int flag = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (sbs[t] == null) {
                sbs[t] = new StringBuilder();
            }
            sbs[t].append(c);
            if (t == 0 || t == numRows - 1) {
                flag = -1 * flag;
            }
            t += flag;
        }
        for (int i = 1; i < sbs.length; i++) {
            sbs[0].append(sbs[i] == null ? "" : sbs[i]);
        }
        return sbs[0].toString();
    }

    /**
     * 验证回文串
     * https://leetcode.cn/problems/valid-palindrome/
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase((s.charAt(j)))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
