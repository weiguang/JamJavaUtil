package com.okayjam.code.leetcode;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/07/03 10:18
 **/
public class ArrayString {
    public static void main(String[] args) {
        System.out.println("Default main method!");
//        int[] nums = new int[]{100,4,200,1,3,2};
//        System.out.println(new ArrayString().longestConsecutive(nums));
//        System.out.println(Arrays.toString(new ArrayString().twoSum(new int[]{100, 4, 200, 1, 3, 2}, 9)));
//        System.out.println(Arrays.toString(new ArrayString().plusOne(new int[]{9})));
//        System.out.println((new ArrayString().candy(new int[]{1,2,3})));
//        System.out.println((new ArrayString().threeSumClosest(new int[]{-1,2,1,-4},  1)));
//        System.out.println(new ArrayString().search(new int[]{4,5,6,7,0,1,2},  0));
//        System.out.println(new ArrayString().combinationSum2(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 27));
//        System.out.println(new ArrayString().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
//        System.out.println(new ArrayString().firstMissingPositive(new int[]{3, 4, -1, 1}));
//        System.out.println(new ArrayString().multiply("123", "456"));
//        System.out.println(new ArrayString().permuteUnique(new int[]{1,1,3}));
//        System.out.println(new ArrayString().myPow(2.0000, -2147483648));
//        System.out.println(new ArrayString().uniquePaths(3, 7));
//        new ArrayString().sortColors(new int[] {2,0,2,1,1,0});
        System.out.println(new ArrayString().combine(4, 2));
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    public int maxProfit22(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; ++i) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        int curProfit = prices[0];
        for (int price : prices) {
            if (price >= curProfit) {
                maxProfit += price - curProfit;
                curProfit = price;
            } else {
                curProfit = price;
            }
        }
        return maxProfit;
    }

    public int maxProfit1(int[] prices) {
        int ans = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                ans = Math.max(prices[i] - min, ans);
            }
        }
        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetsWithDup(nums, 0);
        return ans;
    }

    public void subsetsWithDup(int[] nums, int start) {
        ans.add(new ArrayList<>(t));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            t.add(nums[i]);
            subsetsWithDup(nums, i + 1);
            t.remove(t.size() - 1);
        }
    }


    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int idex = 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[idex - 1] != nums[i]) {
                nums[++idex] = nums[i];
            }
        }
        return idex + 1;
    }


    public boolean exist(char[][] board, String word) {
        boolean[][] flag = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    flag[i][j] = true;
                    boolean f = exist(board, word, i, j, 0, flag);
                    if (f) {
                        return true;
                    }
                    flag[i][j] = false;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word, int row, int clo, int i, boolean[][] flag) {
        if (row < 0 || row >= board.length || clo < 0 || clo >= board[0].length || board[row][clo] != word.charAt(i)) {
            return false;
        }
        if (i == word.length() - 1) {
            return true;
        }
        i++;
        boolean f = false;
        if (row > 0 && !flag[row - 1][clo]) {
            flag[row - 1][clo] = true;
            f = exist(board, word, row - 1, clo, i, flag);
            flag[row - 1][clo] = false;
        }
        if (!f && row < board.length - 1 && !flag[row + 1][clo]) {
            flag[row + 1][clo] = true;
            f = exist(board, word, row + 1, clo, i, flag);
            flag[row + 1][clo] = false;
        }
        if (!f && clo > 0 && !flag[row][clo - 1]) {
            flag[row][clo - 1] = true;
            f = exist(board, word, row, clo - 1, i, flag);
            flag[row][clo - 1] = false;
        }
        if (!f && clo < board[0].length - 1 && !flag[row][clo + 1]) {
            flag[row][clo + 1] = true;
            f = exist(board, word, row, clo + 1, i, flag);
            flag[row][clo + 1] = false;
        }
        return f;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 初始化
        // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
        // 末尾加一位 n + 1 作为哨兵
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<Integer>(temp.subList(0, k)));
            j = 0;
            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }


    private List<List<Integer>> ans1 = new ArrayList<>();

    public List<List<Integer>> combine1(int n, int k) {
        getCombine(n, k, 1, new ArrayList<>());
        return ans;
    }

    public void getCombine(int n, int k, int start, List<Integer> list) {
        if (k == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            list.add(i);
            getCombine(n, k - 1, i + 1, list);
            list.remove(list.size() - 1);
        }
    }


    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }
        int i = 0;
        int p0 = 0, p2 = len - 1;
        while (i <= p2) {
            if (nums[i] == 0) {
                swap(nums, i++, p0++);
            } else if (nums[i] == 2) {
                swap(nums, i, p2--);
            } else {
                i++;
            }
        }
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public void setZeroes(int[][] matrix) {
        boolean[] fi = new boolean[matrix.length];
        boolean[] fj = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    fi[i] = true;
                    fj[j] = true;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (fi[i] || fj[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public int climbStairs(int n) {
        if (n < 3) return n;
        int pre1 = 1, pre2 = 2;
        for (int i = 2; i < n; i++) {
            int t = pre1 + pre2;
            pre1 = pre2;
            pre2 = t;
        }
        return pre2;
    }


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = Integer.MAX_VALUE;
                if (i > 0) {
                    cur = dp[i - 1][j];
                }
                if (j > 0) {
                    cur = Math.min(cur, dp[i][j - 1]);
                }
                dp[i][j] = (cur == Integer.MAX_VALUE ? 0 : cur) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i - 1 >= 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0, top = 0, bottom = n - 1, right = n - 1;
        int cur = 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                matrix[top][i] = cur++;
            }
            top++;
            if (top > bottom) break;
            ;
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = cur++;
            }
            right--;
            if (left > right) break;
            ;
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = cur++;
            }
            bottom--;
            if (top > bottom) break;
            ;
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = cur++;
            }
            left++;
        }
        return matrix;
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int left = 0, top = 0, bottom = matrix.length - 1, right = matrix[0].length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) break;
            ;
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;
            ;
            for (int i = right; i >= left; i--) {
                ans.add(matrix[bottom][i]);
            }
            bottom--;
            if (top > bottom) break;
            ;
            for (int i = bottom; i >= top; i--) {
                ans.add(matrix[i][left]);
            }
            left++;
        }
        return ans;
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> ans = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            ans.add(newInterval);
            return ans.toArray(new int[ans.size()][]);
        }
        ans.add(intervals[0]);
        boolean flag = true;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            int[] cur = ans.get(ans.size() - 1);
            if (flag) {
                if (cur[0] > newInterval[1]) {
                    ans.remove(ans.size() - 1);
                    ans.add(newInterval);
                    ans.add(cur);
                    flag = false;
                } else if (cur[1] >= newInterval[0]) {
                    cur[1] = Math.max(newInterval[1], cur[1]);
                    cur[0] = Math.min(newInterval[0], cur[0]);
                    flag = false;

                }
            }
            if (cur[1] >= interval[0]) {
                cur[1] = Math.max(interval[1], cur[1]);
            } else {
                ans.add(interval);
            }
        }
        if (flag) {
            int[] cur = ans.get(ans.size() - 1);
            if (cur[0] > newInterval[1]) {
                ans.remove(ans.size() - 1);
                ans.add(newInterval);
                ans.add(cur);
            } else if (cur[1] >= newInterval[0]) {
                cur[1] = Math.max(newInterval[1], cur[1]);
                cur[0] = Math.min(newInterval[0], cur[0]);
            } else {
                ans.add(newInterval);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                max = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
        }
        return max;
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        long n1 = n;
        if (n1 < 0) {
            n1 = -n1;
        }
        double ans;
        if (n1 == 1) {
            return n > 0 ? x : 1 / x;
        } else if (n1 == 2) {
            return n > 0 ? x * x : 1 / (x * x);
        } else {
            ans = myPow(x, (int) (n1 >> 1));
            ans = (n1 % 2 == 0 ? 1 : x) * ans * ans;
            return n > 0 ? ans : 1 / ans;
        }
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }


    boolean[] used = null;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        Arrays.sort(nums);
        permuteUnique(nums, ans, new ArrayList<>());
        return ans;
    }

    public void permuteUnique(int[] nums, List<List<Integer>> ans, List<Integer> cur) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<>(cur));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            cur.add(nums[i]);
            permuteUnique(nums, ans, cur);
            cur.remove(cur.size() - 1);
            used[i] = false;
        }
    }


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0) return ans;
        permute(nums, ans, new ArrayList<>());
        return ans;
    }

    public void permute(int[] nums, List<List<Integer>> ans, List<Integer> cur) {
        if (cur.size() == nums.length) {
            ans.add(new ArrayList<>(cur));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!cur.contains(nums[i])) {
                cur.add(nums[i]);
                permute(nums, ans, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }


    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String sum = "0";
        for (int i = num1.length() - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            int nn1 = num1.length() - 1 - i;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int nn2 = num2.length() - 1 - j;
                d2 = d1 * d2;
                StringBuilder sb = new StringBuilder().append(d2);
                for (int i1 = 0; i1 < nn1 + nn2; i1++) {
                    sb.append('0');
                }
                sum = addStrings(sum, sb.toString());
            }
        }
        return sum;
    }

    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) {
            return num2;
        }
        if (num2 == null || num2.isEmpty()) {
            return num1;
        }
        int length = Math.max(num1.length(), num2.length());
        StringBuilder sb = new StringBuilder(length + 1);
        StringBuilder n1 = new StringBuilder(num1).reverse();
        StringBuilder n2 = new StringBuilder(num2).reverse();
        int carry = 0, i = 0;
        while (i < length) {
            int d1 = num1.length() - 1 >= i ? n1.charAt(i) - '0' : 0;
            int d2 = num2.length() - 1 >= i ? n2.charAt(i) - '0' : 0;
            d1 = d1 + d2 + carry;
            if (d1 >= 10) {
                carry = 1;
                d1 -= 10;
            } else {
                carry = 0;
            }
            sb.append(d1);
            i++;
        }
        if (carry == 1) {
            sb.append('1');
        }
        return sb.reverse().toString();
    }


    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    Set<String> set = new HashSet<>();
    HashMap<Integer, Integer> can = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for (int i = 0; i < candidates.length; i++) {
            can.put(candidates[i], can.getOrDefault(candidates[i], 0) + 1);
        }
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum2(candidates, target, ans, new ArrayList<>(), 0);
        return ans;
    }

    public void combinationSum2(int[] candidates, int target, List<List<Integer>> ans, List<Integer> cur, int index) {
        if (index >= candidates.length) {
            return;
        }
        if (target == 0 && !cur.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>(cur.stream().sorted().collect(Collectors.toList()));
            String j = list.stream().map(v -> "" + v).collect(Collectors.joining(","));
            if (!set.contains(j)) {
                ans.add(list);
                set.add(j);
            }
            return;
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : can.entrySet()) {
            int key = integerIntegerEntry.getKey();
            int value = integerIntegerEntry.getValue();
            if (value > 0 && target - key >= 0) {
                cur.add(key);
                can.put(key, value - 1);
                combinationSum2(candidates, target - key, ans, cur, 0);
                cur.remove(cur.size() - 1);
                can.put(key, value);
            }
        }
    }


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationSum(candidates, target, ans, new ArrayList<>(), 0);
        return ans;
    }

    public void combinationSum(int[] candidates, int target, List<List<Integer>> ans, List<Integer> cur, int index) {
        if (index >= candidates.length) {
            return;
        }
        if (target == 0 && !cur.isEmpty()) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        combinationSum(candidates, target, ans, cur, index + 1);
        if (target - candidates[index] >= 0) {
            cur.add(candidates[index]);
            combinationSum(candidates, target - candidates[index], ans, cur, index);
            cur.remove(cur.size() - 1);
        }
    }


    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public int removeDuplicates(int[] nums) {
        int cur = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[cur] == nums[i]) {
                continue;
            }
            nums[++cur] = nums[i];
        }
        return cur + 1;
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = nums.length - 1;
            int target = -nums[first];
            for (int serond = first + 1; serond < nums.length; serond++) {
                if (serond > first + 1 && nums[serond] == nums[serond - 1]) {
                    continue;
                }
                while (serond < third && nums[serond] + nums[third] > target) {
                    third--;
                }
                if (serond == third) {
                    break;
                }
                if (nums[serond] + nums[third] == target) {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(nums[first]);
                    integers.add(nums[serond]);
                    integers.add(nums[third]);
                    result.add(integers);
                }
            }
        }
        return result;
    }


    public int candy(int[] ratings) {
        if (ratings.length < 2) {
            return ratings.length;
        }
        int re = 0, pre = 1;
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] > ratings[i + 1]) {
                pre++;
            } else if (pre > 1) {
                pre = 1;
            }
            re += pre;
        }
        if (ratings[ratings.length - 1] > ratings[ratings.length - 2]) {
            pre += 1;
        } else {
            pre = 1;
        }
        re += pre;
        return re;
    }

    /**
     * 加油站
     * https://leetcode.cn/problems/gas-station/
     *
     * @param gas  [1,4,1,2,6]
     * @param cost [1,1,6,4,2]
     * @return 3
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int idx = -1;
        for (int i = 0, sum = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            sum += gas[i] - cost[i];
            if (sum < 0) {
                idx = i;
                sum = 0;
            }
        }
        return total >= 0 ? idx : -1;
    }

    public int[] plusOne(int[] digits) {
        int flag = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (flag == 0) {
                break;
            }
            int t = (digits[i] + flag);
            flag = t / 10;
            digits[i] = t % 10;
        }
        int[] re;
        if (flag == 0) {
            re = digits;
        } else {
            re = Arrays.copyOf(digits, digits.length + 1);
            re[0] = flag;
        }
        return re;
    }

    /**
     * 旋转图像
     * https://leetcode.cn/problems/rotate-image/
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
            }
        }
        // 对角线
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        rotate(matrix, 0, matrix.length - 1);
    }

    public void rotate(int[][] matrix, int start, int end) {
        while (start < end) {
            for (int i = 0; i < end - start; i++) {
                int t = matrix[start][start + i];
                matrix[start][start + i] = matrix[end - i][start];
                matrix[end - i][start] = matrix[end][end - i];
                matrix[end][end - i] = matrix[start + i][end];
                matrix[start + i][end] = t;
            }
            start++;
            end--;
        }
    }


    /**
     * 42. 接雨水
     * https://leetcode.cn/problems/trapping-rain-water/
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int re = 0;
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[max]) {
                max = i;
            }
        }
        for (int i = 0, left = 0; i < max; i++) {
            if (height[i] >= left) {
                left = height[i];
            } else {
                re += left - height[i];
            }
        }
        for (int j = height.length - 1, right = 0; j > max; j--) {
            if (height[j] > right) {
                right = height[j];
            } else {
                re += right - height[j];
            }
        }
        return re;
    }

    public int trap2(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int re = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currW = i - left - 1;
                int curH = Math.min(height[left], height[i]) - height[top];
                re += currW * curH;
            }
            stack.push(i);
        }
        return re;
    }


    /**
     * 36. 有效的数独
     * https://leetcode.cn/problems/valid-sudoku/
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][][] box = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int idx = c - '0' - 1;
                    rows[i][idx]++;
                    cols[j][idx]++;
                    box[i / 3][j / 3][idx]++;
                    if (rows[i][idx] > 1 || cols[j][idx] > 1 || box[i / 3][j / 3][idx] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        for (; i >= 0 && nums[i] >= nums[i + 1]; i--) {
        }
        if (i >= 0) {
            int j = nums.length - 1;
            for (; j >= 0 && nums[i] >= nums[j]; j--) {
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public int[] twoSum(int[] nums, int target) {
        int[] re = new int[2];
        Map<Integer, Integer> map = new HashMap<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            Integer t = map.get(target - nums[i]);
            if (t != null) {
                re[0] = t;
                re[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return re;
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> map = new HashSet<>((int) (nums.length / 0.75 + 1));
        for (int i = 0; i < nums.length; i++) {
            map.add(nums[i]);
        }
        int re = 0;
        for (int num : nums) {
            if (map.contains(num - 1)) {
                continue;
            }
            int tmax = 1;
            int cur = num;
            while (map.contains(++cur)) {
                tmax++;
            }
            re = Math.max(re, tmax);
        }
        return re;
    }


    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] re = new int[n * m];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i & 1) == 1) {
                    re[index++] = mat[j][i - j];
                } else {
                    re[index++] = mat[i - j][j];
                }
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if ((i & 1) == 1) {
                    re[index++] = mat[m - 1 - i + j][n - j - 1];

                } else {
                    re[index++] = mat[n - 1 - j][m - j - 1];
                }
            }
        }
        return re;
    }
}
