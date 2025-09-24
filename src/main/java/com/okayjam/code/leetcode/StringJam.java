package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
//        System.out.println(new StringJam().partition("bb"));
//        System.out.println(new StringJam().reverseWords("   a good   example  "));
//        System.out.println(new StringJam().lengthOfLongestSubstringTwoDistinct("ccaabbb"));
//        System.out.println(new StringJam().compareVersion("1.2", "1.10"));
//        System.out.println(new StringJam().fractionToDecimal(-1, -2147483648
//        System.out.println(new StringJam().findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
//        System.out.println(new StringJam().reverseBits(43261596));
//        System.out.println(new StringJam().isHappy(19));
//        System.out.println(new StringJam().calculate(" 3+5 / 2 "));
//        System.out.println(new StringJam().diffWaysToCompute("10+5"));
        System.out.println(new StringJam().isAnagram("anagram", "nagaram"));
    }


    /**
     * 242. 有效的字母异位词
     * https://leetcode.cn/problems/valid-anagram/description/
     * @param s s
     * @param t t
     * @return ans
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {return false;}
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }


    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {return false;}
        Map<Character, Integer> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (map1.getOrDefault(c , 0) < 1 ) {
                return false;
            }
            map1.put(c, map1.get(c) - 1);
        }
        return true;
    }


    static final int ADDITION = -1;
    static final int SUBTRACTION = -2;
    static final int MULTIPLICATION = -3;
    /**
     * 241. 为运算表达式设计优先级
     * <a href="https://leetcode.cn/problems/different-ways-to-add-parentheses/description/">...</a>
     * @param expression 表达式
     * @return  ans
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ops = new ArrayList<>();
        String[] op = expression.split("\\d+");
        String[] nums = expression.split("[+\\-*]");
        ops.add(Integer.valueOf(nums[0]));
        for (int i = 1; i < op.length; i++) {
            switch (op[i]) {
                case "+" : ops.add(ADDITION); break;
                case "-" : ops.add(SUBTRACTION); break;
                case "*" : ops.add(MULTIPLICATION); break;
                default: break;
            }
            ops.add(Integer.valueOf(nums[i]));
        }
        List[][] dp = new ArrayList[ops.size()][ops.size()];
        for (int i = 0; i < ops.size(); i++) {
            for (int j = 0; j < ops.size(); j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
        return diffWaysToCompute(dp, 0, ops.size() - 1, ops);
    }

    public List<Integer> diffWaysToCompute(List<Integer>[][] dp, int l, int r, List<Integer> ops) {
        if (dp[l][r].isEmpty()) {
            if (l == r) {
                dp[l][r].add(ops.get(l));
            } else {
                for (int i = l; i < r; i += 2) {
                    List<Integer> left = diffWaysToCompute(dp, l, i, ops);
                    List<Integer> right = diffWaysToCompute(dp, i + 2, r, ops);
                    for (int lv : left) {
                        for (int rv : right) {
                            if (ops.get(i + 1) == ADDITION) {
                                dp[l][r].add(lv + rv);
                            } else if (ops.get(i + 1) == SUBTRACTION) {
                                dp[l][r].add(lv - rv);
                            } else {
                                dp[l][r].add(lv * rv);
                            }
                        }
                    }
                }
            }
        }
        return dp[l][r];
    }



    /**
     * 227. 基本计算器 II
     * <a href="https://leetcode.cn/problems/basic-calculator-ii/description/">leetcode地址</a>
     * @param s 表达式
     * @return 结果
     */
    public int calculate(String s) {
        int ans = 0;
        Deque<Integer> stackNums = new LinkedList<>();
        int num = 0;
        // 提前把数字push到stack，遇到下一个运算符，就开始算上一次，因为这样保证了前一个操作数在stack
        char preSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            // 如果计算到最好一位，无论是什么数字还是空格都要做最后一次运算
            if ((Character.isDigit(c) ||  c == ' ') &&i != s.length()-1 ) continue;
            switch (preSign) {
                case '+': stackNums.push(num); break;
                case '-': stackNums.push(-num);;break;
                case '*': stackNums.push(stackNums.pop() * num); break;
                case '/': stackNums.push(stackNums.pop() / num); break;
                default: break;
            }
            preSign = c;
            num = 0;
        }
        while (!stackNums.isEmpty()) {
            ans += stackNums.pop();
        }
        return ans;
    }


    /**
     * 205. 同构字符串
     * https://leetcode.cn/problems/isomorphic-strings/description/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        if (s.length() != t.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map1.getOrDefault(c1, c2) != c2) {
                return false;
            }
            if (map2.getOrDefault(c2, c1) != c1) {
                return false;
            }
            map1.put(c1, c2);
            map2.put(c2, c1);
        }
        return true;
    }

    /**
     * 202. 快乐数
     * https://leetcode.cn/problems/happy-number/solutions/224894/kuai-le-shu-by-leetcode-solution/
     * 快慢指针
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }


    public boolean isHappy2(int n) {
        int re = n;
        HashSet<Integer> set = new HashSet<>();
        while (re != 1) {
            int a = re;
            re = 0;
            while (a != 0) {
                re += (a % 10) * (a % 10);
                a /= 10;
            }
//            System.out.printf("re: %d\n", re);
            if (set.contains(re)) return false;
            set.add(re);
        }
        return true;
    }


    /**
     * 201. 数字范围按位与
     * https://leetcode.cn/problems/bitwise-and-of-numbers-range/
     *
     * @param m
     * @param n
     * @return
     */
    public int rangeBitwiseAnd(int m, int n) {
        int shift = 0;
        // 找到公共前缀
        while (m < n) {
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    public int rangeBitwiseAnd2(int m, int n) {
        while (m < n) {
            // 抹去最右边的 1
            n = n & (n - 1);
        }
        return n;
    }

    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    /**
     * 190. 颠倒二进制位
     * https://leetcode.cn/problems/reverse-bits/solutions/685436/dian-dao-er-jin-zhi-wei-by-leetcode-solu-yhxz/
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    public int reverseBits2(int n) {
        int res = 0;
        for (int i = 1; i <= 32; i++) {
            res |= (n & 1) << (32 - i);
            n = n >>> 1;
//            System.out.println(Integer.toBinaryString(res));
        }
        return res;
    }


    Map<Character, Integer> bin = new HashMap<Character, Integer>() {{
        put('A', 0);
        put('C', 1);
        put('G', 2);
        put('T', 3);
    }};

    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10;
        Map<Integer, Integer> map = new HashMap<>();
        if (s.length() < L) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        int n = s.length();
        int x = 0;
        for (int i = 0; i < L - 1; ++i) {
            x = (x << 2) | bin.get(s.charAt(i));
        }
        for (int i = 0; i <= n - L; i++) {
            x = ((x << 2) | bin.get(s.charAt(i + L - 1)));
            x &= ((1 << (L * 2)) - 1);
            int v = map.getOrDefault(x, 0) + 1;
            if (v == 2) {
                res.add(s.substring(i, i + L));
            }
            map.put(x, v);
        }
        return res;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        Map<String, Integer> map = new HashMap<>();
        if (s.length() < 10) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        int n = s.length();
        for (int i = 10; i <= n; i++) {
            String s1 = s.substring(i - 10, i);
//            map.compute(s1, (k, v) -> v == null ? 1 : v + 1);
            int v = map.getOrDefault(s1, 1) + 1;
            if (v == 2) {
                res.add(s1);
            }
            map.put(s1, v);
        }
//        return map.entrySet().stream().filter(v -> v.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        return res;
    }


    public void reverseWords(char[] s) {
        reverseCharArr(s, 0, s.length - 1);
        int cur = 0;
        for (int i = 1; i < s.length; ++i) {
            if (s[i] == ' ') {
                reverseCharArr(s, cur, i - 1);
                cur = i + 1;
            }
        }
        if (cur < s.length - 1) {
            reverseCharArr(s, cur, s.length - 1);
        }
    }

    public void reverseCharArr(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    /**
     * 171. Excel 表列序号
     * https://leetcode.cn/problems/excel-sheet-column-number/submissions/662326362/
     *
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        if (columnTitle == null || columnTitle.isEmpty()) {
            return 0;
        }
        int ans = 0;
        long pow = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            char c = columnTitle.charAt(i);
            int num = (int) ((c - 'A' + 1) * pow);
            ans += num;
            pow *= 26;
        }
        return ans;
    }


    public String convertToTitle(int columnNumber) {
        StringBuilder ans = new StringBuilder();
        while (columnNumber > 0) {
            int a = (columnNumber - 1) % 26;
            ans.append((char) (a + 'A'));
            columnNumber = (columnNumber - a - 1) / 26;
        }
        return ans.reverse().toString();
    }

    public String fractionToDecimal(int numerator, int denominator) {
        boolean f = numerator < 0 ^ denominator < 0;
        long numeratorLong = Math.abs((long) numerator);
        long denominatorLong = Math.abs((long) denominator);
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf((long) numerator / (long) denominator);
        }

        long a = numeratorLong / denominatorLong;
        long mod = numeratorLong % denominatorLong;
        HashMap<Long, Integer> modList = new HashMap<>();
        StringBuilder r = new StringBuilder();
        int i = 0;
        while (mod != 0) {
            modList.put(mod, i++);
            mod *= 10;
            r.append(mod / denominatorLong);
            mod %= denominatorLong;
            if (modList.containsKey(mod)) {
                int ii = modList.get(mod);
                r.insert(ii, "(");
                r.append(")");
                break;
            }
        }
        return ((f) ? "-" : "") + a + "." + r;
    }

    /**
     * 165. 比较版本号
     * https://leetcode.cn/problems/compare-version-numbers/submissions/661950566/
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] split = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        for (int i = 0; i < split.length || i < split2.length; i++) {
            int x = 0, y = 0;
            if (i < split.length) {
                x = Integer.parseInt(split[i]);
            }
            if (i < split2.length) {
                y = Integer.parseInt(split2[i]);
            }
            if (x != y) {
                return x - y > 0 ? 1 : -1;
            }
        }
        return 0;
    }


    /**
     * 161. 相隔为 1 的编辑距离
     * https://leetcode.cn/problems/one-edit-distance/submissions/661515165/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int ns = s.length();
        int nt = t.length();
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }
        if (nt - ns > 1) {
            return false;
        }
        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (ns == nt) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return ns + 1 == nt;
    }

    /**
     * 159. 至多包含两个不同字符的最长子串
     * https://leetcode.cn/problems/longest-substring-with-at-most-two-distinct-characters/
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int ans = 0;
        int idx = 0;
        Map<Character, Integer> map = new HashMap<>();
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (list.size() < 2 || list.contains(c)) {
                ans = Math.max(ans, i - idx + 1);
            } else {
                idx = map.get(list.removeFirst()) + 1;
                map.remove(c);
            }
            map.put(c, i);
            if (list.contains(c)) {
                list.removeFirstOccurrence(c);
            }
            list.add(c);
        }
        return ans;
    }


    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();
        return String.join(" ", list);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        HashSet<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }


    /**
     * 131. 分割回文串(时间较长)
     * https://leetcode.cn/problems/palindrome-partitioning/
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (s == null || s.isEmpty()) return new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        if (s.length() == 1) {
            List<String> t = new ArrayList<>();
            t.add(s);
            ans.add(t);
            return ans;
        }
        for (int i = 0; i < s.length(); i++) {
            String leftStr = s.substring(0, i + 1);
            if (s.charAt(0) != s.charAt(i) || !new StringBuilder(leftStr).reverse().toString().equals(leftStr)) {
                continue;
            }
            if (i == s.length() - 1) {
                List<String> left = new ArrayList<>();
                left.add(leftStr);
                ans.add(left);
                break;
            }
            String rightStr = s.substring(i + 1);
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
        if (s.length() <= 1) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char c1 = s.charAt(start);
            if (!Character.isLetterOrDigit(c1)) {
                start++;
                continue;
            }
            char c2 = s.charAt(end);
            if (!Character.isLetterOrDigit(c2)) {
                end--;
                continue;
            }
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
