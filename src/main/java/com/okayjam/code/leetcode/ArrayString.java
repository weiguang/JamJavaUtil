package com.okayjam.code.leetcode;


import java.util.*;
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
//        System.out.println(new ArrayString().combine(4, 2));
//        System.out.println(new ArrayString().canCompleteCircuit1(new int[] {5,1,2,3,4}, new int[]{4,4,1,5,1}));
//        System.out.println(new ArrayString().findMin(new int[] {2,2,1,1,2}));
//        System.out.println(new ArrayString().largestNumber(new int[] {3,30,34,5,9}));
//        System.out.println(new ArrayString().rob(new int[] {2,7,9,3,1}));
//        System.out.println(new ArrayString().findKthLargest(new int[] {3,2,1,5,6,4}, 2));
//        System.out.println(new ArrayString().combinationSum3(3,9));
//        System.out.println(new ArrayString().containsNearbyAlmostDuplicate(new int[] {1,5,9,1,5,9}, 2, 3));
//        System.out.println(new ArrayString().containsNearbyAlmostDuplicate(new int[] {8,7,15,1,6,1,9,15}, 1, 3));
//        System.out.println(new ArrayString().computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
//        System.out.println(new ArrayString().maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3));
//        System.out.println(new ArrayString().numSquares(13));
//          new ArrayString().moveZeroes(new int[] {0,1,0,3,12});
//        System.out.println(new ArrayString().canWin("+++++"));
//        System.out.println(new ArrayString().lengthOfLIS(new int[] {0,8,9,4,2}));
//        System.out.println(Arrays.toString(new ArrayString().findErrorNums(new int[] {3, 2, 2})));
//        System.out.println(Arrays.toString(new ArrayString().smallerNumbersThanCurrent(new int[] {8,1,2,2,3})));
//        System.out.println(new ArrayString().buildArray(new int[] {1, 2, 3}, 3));
//        System.out.println(new ArrayString().canBeEqual("abcd", "dacb"));
//        System.out.println(new ArrayString().lastStoneWeight(new  int[]{2,7,4,1,8,1}));
//        System.out.println(new ArrayString().canReach("01111010111101110110111011110", 2,2));
//        System.out.println(new ArrayString().bulbSwitch(5));
//        System.out.println(new ArrayString().coinChange(new int[]{186,419,83,408}, 6249));
//          new ArrayString().wiggleSort2(new int[]{1,3,2,2,3,2});
//        System.out.println( new ArrayString().canMeasureWater(3,5,4));
//        System.out.println( new ArrayString().largestDivisibleSubset(new int[]{1,2,4,8}));
//        System.out.println( new ArrayString().kSmallestPairs(new int[]{1,7,11}, new int[] {2,4,6}, 3));
//        System.out.println( new ArrayString().integerReplacement(7));
//        System.out.println( new ArrayString().toHex(26));
//        System.out.println(Arrays.deepToString(new ArrayString().reconstructQueue(
//                new int[][] {new int[] {7, 0}, new int[] {4, 4}, new int[] {7, 1}, new int[] {5, 0}, new int[] {6, 1},
//                        new int[] {5, 2}})));
//        new ArrayString().findDuplicates(new int[]{4,3,2,7,8,2,3,1});
//        System.out.println(new ArrayString().findSubsequences(new int[]{4,6,7,7}));
        System.out.println( -15 / 7);

     }


    public void reverse(int[] nums, int s, int e) {
        while (s < e) {
            swap(nums, s, e);
            s++;
            e--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }



    /**
     * 554. 砖墙
     * @param wall wall
     * @return ans
     */
    public int leastBricks(List<List<Integer>> wall) {
        // 前缀和，如果两行的前缀和相同就说明垂直线在一起，求最多重复的前缀和就行
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (List<Integer> walli : wall) {
            int sum = 0;
            // 因为最后的垂直边不算，所以最后的值不能算
            for (int i = 0; i < walli.size() - 1; i++) {
                sum += walli.get(i);
                // 这个语句意义是 oldVale = map.get(key); map.put(key, oldValue + 1); 可以减少一次key查询
                Integer newValue = map.merge(sum, 1, Integer::sum);
                max = Math.max(max, newValue);
            }
        }
        return wall.size() - max;
    }


    /**
     * 553. 最优除法
     * 数学 ans = nums[0] / (nums[1]/nums[2]/.../nums[n-1])
     * @param nums nums
     * @return ans
     */
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) return String.format("%d", nums[0]);
        if (nums.length == 2) return String.format("%d/%d", nums[0], nums[1]);
        return String.format("%d/(%s)", nums[0], Arrays.stream(nums).skip(1).mapToObj(String::valueOf).collect(Collectors.joining("/")));
    }

    /**
     * 540. 有序数组中的单一元素
     * 二分判断， 因为其中有一段可能包含了单个数，根据规律可以判断单数在哪一段
     * 如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等；
     * 如果 mid 是奇数，则比较 nums[mid−1] 和 nums[mid] 是否相等。
     * * 利用按位异或的性质，可以得到 mid 和相邻的数之间的如下关系，其中 ⊕ 是按位异或运算符：
     * 当 mid 是偶数时，mid+1=mid⊕1； eg: 1010 ^ 1 = 1011
     * 当 mid 是奇数时，mid−1=mid⊕1。 eg: 1011 ^ 1 = 1010
     * 其实就是翻转最后最后一位
     * 因此在二分查找的过程中，不需要判断 mid 的奇偶性，mid 和 mid⊕1 即为每次需要比较元素的两个下标。
     * @param nums nums
     * @return ans
     */
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else  {
                right = mid;
            }
        }
        return nums[left];
    }

    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 跳过重复
            if(i != 0 && nums[i] == nums[i-1]) continue;
            int target = nums[i] + k;
            // 如果+k 已经超出最大值，就没有符合的数据了，可以跳过
            if (target > nums[nums.length - 1]) break;
            int search = Arrays.binarySearch(nums, i + 1, nums.length, target);
            if (search > 0) ans++;
        }
        return ans;
    }
    
    /**
     * 526. 优美的排列
     * 从左向右依次向目标排列中放入数
     * 用 vis 数组标记哪些数被使用过;用二维数组 match 保存每个位置的符合条件的数,当我们尝试向位置 index 放入数时，我们只需要遍历 match[index] 即可。
     * @param n n
     * @return ans
     */
    public int countArrangement(int n) {
        List<Integer>[] match = new List[n + 1];
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        countArrangementDfs(1, n, match, vis);
        return countArrangementAns;
    }
    int countArrangementAns = 0;

    public void countArrangementDfs(int index, int n, List<Integer>[] match, boolean[] vis) {
        if (index == n + 1) {
            countArrangementAns++;
            return;
        }
        for (int x : match[index]) {
            if (!vis[x]) {
                vis[x] = true;
                countArrangementDfs(index + 1, n, match, vis);
                vis[x] = false;
            }
        }
    }


    /**
     * 525. 连续数组
     * 使用map存前缀和，我们把0看作-1累加， 如果preSum[i]=preSum[j], 那么（i,j]之前的和为0，说明有相同的-1和相同的1累加
     * @param nums nums
     * @return ans
     */
    public int findMaxLength(int[] nums) {
        int ans = 1;
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        for (int i = 0; i < nums.length; i++) {
            counter += nums[i] == 0 ? -1:1;
            Integer preIdx = map.get(counter);
            if (preIdx != null) {
                ans = Math.max(ans, i - preIdx);
            } else {
                map.put(counter, i);
            }
        }
        return ans;
    }

    /**
     * 523. 连续的子数组和
     * 第一个是官方解，比较难理解， 第二个是网友的解，更好理解
     * @param nums nums
     * @param k k
     * @return ans
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            // 使用了前缀和思想， 最终我们只需要求余数，使用了记录余数替代sum
            // 例如 10 % 7 = 3，  (10 + n) % 7 = (3 + n) % 7 。 10和3都是一样
            remainder = (remainder + nums[i]) % k;
            Integer pre = map.get(remainder);
            if (pre != null) {
                if (i - pre >1) return true;
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        // 前缀和
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
        Set<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            // 这里只把n-2 的加入 ，保证查出来的都是大于1的区间
            set.add(sum[i - 2] % k);
            if (set.contains(sum[i] % k)) return true;
        }
        return false;
    }

    /**
     *   518. 零钱兑换 II
     * @param amount amount
     * @param coins coins
     * @return ans
     */
    public int change(int amount, int[] coins) {
        // dp[i] 的定义：表示凑成金额 i 的硬币组合数
        int[] dp = new int[amount + 1];
        // 金额为 0 的组合数为 1
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j]  += dp[j - coin];
            }
        }
        return dp[amount];
    }


    /**
     * 516. 最长回文子序列
     * 用 dp[i][j] 表示字符串 s 的下标范围 [i,j] 内的最长回文子序列的长度。
     * 假设字符串 s 的长度为 n，则只有当 0≤i≤j<n 时，才会有 dp[i][j]>0，否则 dp[i][j]=0, 并且dp[i][i]=1 。
     * @param s s
     * @return ans
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n -1; i >= 0 ; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i+1][j-1] + 2;
                else dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
            }
        }
        return dp[0][n -1];
    }

    /**
     * 509. 斐波那契数
     * <a href="https://leetcode.cn/problems/fibonacci-number">509. Fibonacci Number</a>
     * @param n n
     * @return ans
     */
    public int fib(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
        return (int) Math.round(fibN / sqrt5);
    }

    public int fib2(int n) {
        if (n < 2) return n;
        int a = 0, b = 1 ;
        while (n-- > 1) {
            int t = a + b;
            a = b;
            b = t;
        }
        return b;
    }

    /**
     * 507. 完美数
     * @param num num
     * @return ans
     */
    public boolean checkPerfectNumber(int num) {
        // 答案只有几个，可以直接写出来 6,28,496,8128,33550336
        return num == 6 || num == 28 || num == 496 || num == 8128 || num == 33550336;

//        if (num < 3) return false;
//        int end = (int) Math.sqrt(num);
//        long sum = (end * end == num ? -end : 0) + 1;
//        for (int i = 2; i <= Math.sqrt(num) ; i++) {
//            int j = num / i;
//            if (i * j == num) sum += i+j;
//            if (sum > num) break;
//        }
//        return sum == num;
    }


    /**
     * 506. 相对名次
     * @param score score
     * @return ans
     */
    public String[] findRelativeRanks(int[] score) {
         String[] ans = new String[score.length];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < score.length; i++) {
            treeMap.put(score[i], i);
        }
        int i = 1;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            int idx = entry.getValue();
            if (i == 1) ans[idx] = "Gold Medal";
            else if (i == 2) ans[idx] = "Silver Medal";
            else if (i == 3) ans[idx] = "Bronze Medal";
            else ans[idx] = String.valueOf(i);
            i++;
        }
        return ans;
    }

    /**
     * 504. 七进制数
     * @param num nums
     * @return ans
     */
    public String convertToBase7(int num) {
//        return Integer.toString(num,7);
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        int num1 = num > 0 ? num : -num;
        while (num1 != 0) {
            sb.append(num1 % 7);
            num1 /= 7;
        }
        if (num < 0) {sb.append("-");}
        return sb.reverse().toString();
    }

    /**
     * 503. 下一个更大元素 II
     * 这里是可以循环的，因此可以遍历的时候设置为两遍， 处理一下下表就行
     * @param nums nums
     * @return ans
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        // 默认找不到就是 -1
        Arrays.fill(ans, -1);
        Deque<Integer> stack = new LinkedList<>(); // 存索引
        for (int i = 0; i < nums.length * 2; i++) {
            int i1 = i % nums.length;
            // 单调栈查找下一个更大元素
            while (!stack.isEmpty() && nums[i1] > nums[stack.peek()]) {
                int idx = stack.pop();
                ans[idx] = nums[i1];
            }
            stack.push(i1);
        }
        return ans;
    }

    /**
     * 496. 下一个更大元素 I
     * @param nums1 nums1
     * @param nums2 nums2
     * @return ans
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> ngeMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>(); // 存索引
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                int idx = stack.pop();
                ngeMap.put(nums2[idx], nums2[i]);
            }
            stack.push(i);
        }
        // 栈中剩余元素没有 NGE
//        while (!stack.isEmpty()) {
//            ngeMap.put(nums2[stack.pop()], -1);
//        }
        stack = null;

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = ngeMap.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    /**
     * 495. 提莫攻击
     * @param timeSeries t
     * @param duration d
     * @return ans
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = duration, start = timeSeries[0];
        for (int i = 1; i < timeSeries.length; i++) {
            ans += start + duration < timeSeries[i] ? duration :timeSeries[i] - start;
            start = timeSeries[i];
        }
        return ans;
    }

    /**
     * 494. 目标和
     * 第一种是 dp， 第二种是回溯
     * @param nums nums
     * @param target target
     * @return ans
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 添加 - 号的元素之和为 neg，则其余添加 + 的元素之和为 sum−neg, 得到下面公式
        // (sum−neg)−neg=sum−2⋅neg=target 这个公式可以得到 neg = (sum -taeget) / 2
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        //定义二维数组 dp，其中 dp[i][j] 表示在数组 nums 的前 i 个数中选取元素，使得这些元素之和等于 j 的方案数
        // 如果 j≥num，则如果不选 num，方案数是 dp[i−1][j]，如果选 num，方案数是 dp[i−1][j−num]，此时有 dp[i][j]=dp[i−1][j]+dp[i−1][j−num]
        // 由于 dp 的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 dp 的第一个维度，将空间复杂度优化到 O(neg)
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            // 内层循环需采用倒序遍历的方式，这种方式保证转移来的是 dp[i−1][] 中的元素值
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        return findTargetSumWaysDfs(nums, target, 0, 0);
    }

    private int findTargetSumWaysDfs(int[] nums, int target, int idx, int temp) {
        if (idx == nums.length)  return temp == target ? 1 : 0;
        return findTargetSumWaysDfs(nums, target, idx + 1, temp + nums[idx]) +
                findTargetSumWaysDfs(nums, target, idx + 1, temp - nums[idx]);
    }


    public int[] constructRectangle(int area) {
        int w = (int) Math.sqrt(area);
        while (area % w != 0) w--;
        return new int[]{area / w, w};
    }

    /**
     * 491. 非递减子序列
     * @param nums nums
     * @return ans
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findSubsequencesDfs(0, Integer.MIN_VALUE, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void findSubsequencesDfs(int cur, int last, int[] nums, List<Integer> temp, List<List<Integer>> ans) {
        if (cur == nums.length) {
            if (temp.size() >= 2) ans.add(new ArrayList<>(temp));
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            findSubsequencesDfs(cur + 1, nums[cur], nums, temp, ans);
            temp.remove(temp.size() - 1);
        }
        if (nums[cur] != last) {
            findSubsequencesDfs(cur + 1, last, nums, temp, ans);
        }
    }

    /**
     * 486. 预测赢家
     * @param nums nums
     * @return ans
     */
    public boolean predictTheWinner(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        System.arraycopy(nums, 0, dp, 0, length);
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }



    /**
     * 477. 汉明距离总和
     * 重点理解 若长度为 n 的数组 nums 的所有元素二进制的第 i 位共有 c 个 1，n−c 个 0，则些元素在二进制的第 i 位上的汉明距离之和为 c⋅(n−c)
     * @param nums nums
     * @return ans
     */
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 31; i++) {
            int c = 0;
            for (int num : nums) {
                c += (num >> i) & 1;
            }
            ans += c * (n -c);
        }
        return ans;
    }

    /**
     * 476. 数字的补数
     * @param num num
     * @return ans
     */
    public int findComplement(int num) {
        boolean f = true;
        for (int i = 31; i > 0 ; i--) {
            if (f && (1<<i & num) == 0) continue;
            f = false;
            num ^= 1<<i;
        }
        return num ^ 1;
    }

    /**
     * 475. 供暖器
     * @param houses h1
     * @param heaters h2
     * @return ans
     */
    public int findRadius(int[] houses, int[] heaters) {
        int max = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            // 利用java的函数， 在 heaters 中二分查找 house 的位置
//            int l = Arrays.binarySearch(heaters, houses[i]);
//            // 如果没找到，binarySearch 会返回 -(insertion point) - 1
//            if (l < 0) {
//                l = -l - 1; // 此时 l 是第一个大于 house 的供暖器索引
//            }

            // 手写查找
            int l = 0, r = heaters.length - 1;
            // 查找第一个 >= house 的 heater 索引
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (heaters[mid] >= house) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            // l 就是第一个 >= house 的供暖器下标
            int dist1 = (l < heaters.length) ? Math.abs(heaters[l] - house) : Integer.MAX_VALUE;
            int dist2 = (l > 0) ? Math.abs(heaters[l - 1] - house) : Integer.MAX_VALUE;
            max = Math.max(max, Math.min(dist1, dist2));
        }
        return max;
    }

    /**
     * 474. 一和零
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     *  两个解：
     * 第一个是优化后的
     * 第二个是官解
     * @param strs strs
     * @param m m
     * @param n n
     * @return ans
     */
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] 表示最多有 i 个 0 和 j 个 1 时，能拼出的最大字符串数
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            // 统计当前字符串中 '0' 和 '1' 的个数
            int[] zerosOnes = getZerosOnes(str);
            int zeroes = zerosOnes[0], ones = zerosOnes[1];

            // 倒序遍历背包容量，防止重复使用同一个字符串
            for (int j = m; j >= zeroes; j--) {
                for (int k = n; k >= ones; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zeroes][k - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public int findMaxForm1(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 0; i < length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if(j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-zeros][k-ones]);
                    }
                }
            }
        }
        return dp[length][m][n];
    }


    private int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    /**
     * 473. 火柴拼正方形
     * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。
     * 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
     * 如果你能使这个正方形，则返回 true ，否则返回 false 。
     * @param matchsticks p
     * @return ans
     */
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) return false;
        int sum = 0, max = 0;
        for (int m : matchsticks) {
            sum += m;
            max = Math.max(max, m);
        }
        if (sum % 4 != 0) return false;
        int side = sum / 4;
        if (max > side) return false;
        // 关键优化：排序，先放大火柴，剪枝最快
        Arrays.sort(matchsticks);
        // 从最长的火柴开始递归（即数组末尾）
        return findSide(matchsticks, side, matchsticks.length - 1, new long[4]);
    }

    private boolean findSide(int[] matchsticks, long targetSide, int idx, long[] sides) {
        // 所有火柴放置完毕
        if (idx < 0) return true;
        int currentMatch = matchsticks[idx];
        for (int i = 0; i < 4; i++) {
            // 如果放入当前边超长，跳过
            if (sides[i] + currentMatch > targetSide) continue;
            // 优化 2：剪枝 - 如果当前边和上一条边长度一样，放哪条都一样，直接跳过
            if (i > 0 && sides[i] == sides[i - 1]) continue;
            // 尝试放入
            sides[i] += currentMatch;
            if (findSide(matchsticks, targetSide, idx - 1, sides)) return true;
            // 回溯
            sides[i] -= currentMatch;
        }
        return false;
    }


    /**
     * 464. 我能赢吗
     * @param maxChoosableInteger  maxChoosableInteger
     * @param desiredTotal desiredTotal
     * @return ans
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * (maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        final Map<Integer, Boolean> memo = new HashMap<>();
        return canIWinDfs(maxChoosableInteger, 0, desiredTotal, 0, memo);
    }

    private boolean canIWinDfs(int maxChoosableInteger, int usedNumbers, int desiredTotal, int currentTotal, Map<Integer, Boolean> memo) {
        Boolean b = memo.get(usedNumbers);
        if (b != null) return b;
        boolean res = false;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (((usedNumbers >> i) & 1) != 0) continue;
            // 情况 A：选了 i 之后直接达到或超过 desiredTotal，当前玩家直接获胜
            if (i + currentTotal >= desiredTotal) {
                res = true;
                break;
            }
            // 情况 B：选了 i 之后，轮到对手选。如果对手在接下来的状态中必败（返回 false），
            // 说明当前玩家选 i 是正确决策，当前玩家稳赢！
            if (!canIWinDfs(maxChoosableInteger, usedNumbers | (1 << i), desiredTotal, currentTotal + i, memo)) {
                res = true;
                break;
            }
        }
        memo.put(usedNumbers, res);
        return res;
    }




    /**
     * 462. 最小操作次数使数组元素相等 II
     * @param nums nums
     * @return ans
     */
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ret = 0, x = nums[n / 2];
        for (int num : nums) {
            ret += Math.abs(num - x);
        }
        return ret;
    }

    /**
     * 461. 汉明距离
     * 与  2220. 转换数字的最少位翻转次数  思路一样
     * @param x x
     * @param y y
     * @return ans
     */
    public int hammingDistance(int x, int y) {
        int s = x ^ y, ret = 0;
        // 调用系统函数
//        ret = Integer.bitCount(s);
        // 计算二进制1的方法
        while (s != 0) {
            s &= s - 1;
            ret++;
        }
        return ret;
    }

    /**
     * 457. 环形数组是否存在循环
     * 将确定无法形成合法环的节点设为 0，使得时间复杂度稳定降至 $O(n)$，空间复杂度维持在 $O(1)。
     * @param nums nums
     * @return ans
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            // 如果已经被标记为 0，说明从该点出发不可能构成合法环，直接跳过
            if (nums[i] == 0) {
                continue;
            }

            int slow = i;
            int fast = next(nums, i);

            // 保持同向的条件：确保 fast 指针走 1 步和走 2 步的节点与 slow 节点方向一致
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    // 相遇了！检查是否是自环（长度为 1）
                    if (slow == next(nums, slow)) {
                        break; // 自环无效，跳出
                    }
                    return true; // 找到合法的环
                }

                slow = next(nums, slow);             // 慢指针走 1 步
                fast = next(nums, next(nums, fast)); // 快指针走 2 步
            }

            // 优化：如果这次探索没有成功找到合法的环，
            // 将从 i 出发且同方向的节点置为 0，避免以后重复探索
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }

        return false;
    }

    // 辅助函数：计算当前位置向前/向后移动后的下一个下标
    private int next(int[] nums, int cur) {
        int n = nums.length;
        // (cur + nums[cur]) % n 可能为负数，加上 n 再 % n 可以确保结果落在 [0, n - 1]
        return ((cur + nums[cur]) % n + n) % n;
    }


    /**
     * 456. 132 模式
     * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
     * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
     * 这里的栈就是为了维护3的候选列表，然后2 要尽可能接近3，这样 1的选择范围才多
     * @param nums nums
     * @return ans
     */
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        // 1. 维护候选的 "3"（单调递减栈）
        Deque<Integer> stack = new LinkedList<>();
        // 2. 记录当前找到的最大 "2"（次大值），初始为负无穷
        int maxK = Integer.MIN_VALUE;
        // 3. 从 n - 2 开始倒序遍历，寻找符合条件的 "1"
        stack.push(nums[n -1]);
        for (int i = n -2; i >= 0; i--) {
            // 检查 1：当前元素能否作为 "1"？
            // 只要 nums[i] 比当前找到的最大 "2" 还小，说明已经满足 nums[i] < maxK
            // 同时 maxK 是由某个大于它的 "3" 弹出的，因此必定存在 132 模式！
            if (nums[i] < maxK) return true;
            // 检查 2：当前元素能否作为更大的 "3"？
            // 如果 nums[i] 比栈顶大，说明它可以作为更靠左、更大的 "3"
            // 此时弹出所有比 nums[i] 小的栈顶元素，这些被弹出的元素都可以退化为合法的 "2"
            while(!stack.isEmpty() && nums[i] > stack.peek()) maxK = stack.pop();
            // 检查 3：将当前元素入栈，作为后续潜在的 "3"
            // 剪枝优化：如果 nums[i] <= maxK，它就算未来被弹出，也无法把 maxK 变大，因此无须入栈
            if (nums[i] > maxK) stack.push(nums[i]);
        }
        return false;
    }


    /**
     * 455. 分发饼干
     * @param g g
     * @param s s
     * @return ans
     */
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int g1 = 0, s1 = 0 , ans = 0 ;
        while(g1 < g.length && s1 < s.length) {
            if (s[s1] >= g[g1]) {ans++; g1++;}
             s1++;
        }
        return ans;
    }


    /**
     * 454. 四数相加 II
     * <a href="https://leetcode.cn/problems/4sum-ii/">454. 四数相加 II</a>
     * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     * @param nums1 1
     * @param nums2 2
     * @param nums3 3
     * @param nums4 4
     * @return ans
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int n = nums1.length;
        int ans = 0;
        HashMap<Integer, Integer> map1 = new HashMap<>();
        for (int value : nums1) {
            for (int j = 0; j < n; j++) {
                map1.merge(-value - nums2[j], 1, Integer::sum);
            }
        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                ans += map1.getOrDefault(nums3[k] + nums4[l], 0);
            }
        }
        return ans;
    }

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(v -> v[1]));
        int ans = 1;
        int right = 0;
        int pos = points[0][1];
        while (right < points.length) {
            if (pos < points[right][0]) {
//                pos = Math.min(pos, points[right][1]);
//            } else {
                ans++;
                pos = points[right][1];
            }
            right++;
        }
        return ans;
    }

    /**
     * 451. 根据字符出现频率排序
     * <a href="https://leetcode.cn/problems/sort-characters-by-frequency">451. 根据字符出现频率排序</a>
     * 给定一个字符串 s ，根据字符出现的 频率 对其进行 降序排序 。一个字符出现的 频率 是它出现在字符串中的次数。
     * 返回 已排序的字符串 。如果有多个答案，返回其中任何一个。
     * @param s s
     * @return ans
     */
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }
        return map.entrySet().stream()
                .sorted(Comparator.<Map.Entry<Character, Integer>>comparingInt(Map.Entry::getValue).reversed())
                .map(v -> String.valueOf(v.getKey()).repeat(v.getValue()))
                .collect(Collectors.joining());
    }


    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        if (nums.length  == nums[nums.length -1] - nums[0]  + 1) return Collections.emptyList();
        List<Integer> ans = new ArrayList<>(nums[nums.length -1] - nums[0]  + 1 - nums.length );
        int i, r = 0;
        for (i = nums[0]; i < nums[nums.length -1]; i++) {
            if (nums[r] > i) ans.add(i);
            else r++;
        }
        return ans;
    }

    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int[] point : points) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int[] ints : points) {
                Integer distance = (point[0] - ints[0]) * (point[0] - ints[0]) + (point[1] - ints[1]) * (point[1] - ints[1]) ;
                cnt.merge(distance, 1, Integer::sum);
            }
            for (Integer value : cnt.values()) {
                ans += value * (value -1);
            }
        }
        return ans;
    }



    public List<Integer> findDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] !=nums[nums[i] -1]) {
                swap(nums, i, nums[i] - 1); i--;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i  + 1 != nums[i]) ans.add(nums[i]);
        }
        return ans;
    }


    public int arrangeCoins(int n) {
        int start = 1, end = 65535;
        while (start < end ) {
            int mid = (end - start + 1) / 2 + start;
            long sum = (long) mid * (1 + mid) / 2;
            if (sum <= n) start = mid;
            else end = mid - 1;
        }
        return  start;
    }





    public String addStrings2(String num1, String num2) {
        if (num1.isEmpty()) return num2;
        if (num2.isEmpty()) return num1;
        int d = 0;
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 ) {
            int a = i >= 0 ?  num1.charAt(i) - '0' : 0 ;
            int b = j >= 0  ? num2.charAt(j) - '0' : 0;
            d = a + b + d;
            sb.append(d % 10);
            d = d/10;
            i--;j--;
        }
        if (d > 0) sb.append(d);
        return sb.reverse().toString();
    }




    public int thirdMax(int[] nums) {
        if (nums.length == 0) return 0;
        Integer[] m = new Integer[]{null, null,null};
        for (int num : nums) {
            if (m[0] == null || num > m[0]) {
                m[2] = m[1];
                m[1] =  m[0];
                m[0] = num;
            } else if (m[1] == null || num > m[1] ) {
                if (m[0] == num)  continue;
                m[2] = m[1];
                m[1] = num;
            } else if ((m[2] == null || m[2] < num )  && m[1] != num ) {
                m[2] = num;
            }
        }
        return m[2] == null ? m[0] : m[2];
    }


    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (v1, v2) -> {if (v1[0] == v2[0]) return v1[1] - v2[1]; return v2[0] - v1[0];});
//        Arrays.sort(people, Comparator.<int[]>comparingInt(a -> a[0]).reversed().thenComparing(v -> v[1]));
        List<int[]> list = new ArrayList<>(people.length);
        for (int[] p : people) {
            // 直接插入到 k 指定的索引位置，省略了你原来的内层 for 循环
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][2]);
    }


    public String toHex(int num) {
        if (num ==0 ) return "0";
        StringBuilder sb = new StringBuilder(8);
        while (num != 0 && sb.length() < 8) {
            int digit = num & 0xf;
            num >>= 4;
            sb.append((char) (digit < 10 ? digit + 48 : digit + 87));
        }
        return sb.reverse().toString();
    }

    /**
     * 402. 移掉 K 位数字
     * <a href="https://leetcode.cn/problems/remove-k-digits">402. 移掉 K 位数字</a>
     * @param num num
     * @param k k
     * @return ans
     */
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k >= n) {
            return "0";
        }
        // 单调栈：保证栈内字符非递减。当当前字符比栈顶小且还能删除时，弹出栈顶（删除左侧较大的数字能让结果更小）
        char[] stack = new char[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (top > 0 && k > 0 && stack[top - 1] > c) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        // 若仍可删除，此时栈是非递减的，直接从末尾截断即可
        top -= k;

        // 去除前导 0
        int start = 0;
        while (start < top && stack[start] == '0') {
            start++;
        }
        return start == top ? "0" : new String(stack, start, top - start);
    }

    /**
     * 401. 二进制手表
     *<a href="https://leetcode.cn/problems/binary-watch">401. 二进制手表</a>
     * 这道题的数据规模实在太小了（一天只有 $12 \times 60 = 720$ 分钟）。
     * 如果我们不写复杂的递归回溯，而是直接遍历这 720 个时间点，利用位运算 Integer.bitCount() 来数它们二进制里有多少个 1，不仅代码极短，而且几乎没有任何递归压栈开销
     * @param turnedOn n
     * @return ans
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        // 直接枚举 12 * 60 种情况
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                // Integer.bitCount 属于 JVM 底层硬件级别的优化，速度快
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return ans;
    }


    int[] bits = new int[]{1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
    public List<String> readBinaryWatch2(int turnedOn) {
        if (turnedOn >= 9) { return Collections.emptyList(); }
        List<String> ans = new ArrayList<>();
        readBinaryWatch(turnedOn, 0, 0, 0 , ans);
//        Collections.sort(ans);
        return ans;
    }

    public void readBinaryWatch(int turnedOn, int idx, int curHour, int curMin, List<String> ans) {
        // 越界剪枝：小时不能 >= 12，分钟不能 >= 60
        if (curHour >= 12 || curMin >= 60) {
            return;
        }
        // 2. 剩余灯数不足剪枝：剩下的坑位不够填满 turnedOn
        if (10 - idx < turnedOn) {
            return;
        }
        if (turnedOn == 0) {
            ans.add(curHour + ":" + (curMin < 10 ? "0" + curMin : curMin));
            return ;
        }
        for (int i = idx; i < bits.length; i++) {
            if (i < 4) { // 前4个元素是小时
                readBinaryWatch(turnedOn - 1, i + 1, curHour + bits[i], curMin, ans);
            } else { // 后6个元素是分钟
                readBinaryWatch(turnedOn - 1, i + 1, curHour, curMin + bits[i], ans);
            }
        }
    }


    /**
     * 400. 第 N 位数字
     * <a href="https://leetcode.cn/problems/nth-digit/">400. 第 N 位数字</a>
     * @param n n
     * @return ans
     */
    public int findNthDigit(int n) {
        if(n < 10) return n;
        int[] m =  new int[10];
        m[1] = 9;
        int j = 1;
        while(m[j] < n) {
            m[j + 1] = (int) (m[j] + Math.pow(10, j) * 9 * (j + 1)) ;
            j++;
        }
        int re = n - m[j -1] -1;
        int start = (int) Math.pow(10, j -1);
        // 对应的数字
        int num = start + re / j ;
        // 对应第几位
        int b = re % j;
        return (num / (int)(Math.pow(10, j - b - 1))) % 10;
//        return Integer.toString(num).charAt(b) - '0';
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }



    Map<Integer, Integer> replaceMap = new HashMap<>();
    public int integerReplacement(int n) {
        if (n == 1  ) return 0;
        if (replaceMap.containsKey(n)) return replaceMap.get(n);
        int t;
        if ((n & 1) == 0)  {
            t = integerReplacement(n >> 1) + 1;
        } else {
            t = 2 + Math.min(integerReplacement(n / 2), integerReplacement(n / 2 + 1));
        }
        replaceMap.put(n, t);
        return replaceMap.get(n);
     }

        static class IntervalClass {
            public int idx;
            public int[] interval;
            IntervalClass(int idx, int[] interval) {
                this.idx = idx;
                this.interval = interval;
            }
        }

    public int[] findRightInterval(int[][] intervals) {
        IntervalClass[] obj = new IntervalClass[intervals.length];
        for (int i = 0; i <intervals.length; i++) {
            obj[i] = new IntervalClass(i, intervals[i]);
        }
        Arrays.sort(obj, (a, b) -> {if (a.interval[0] == b.interval[0]) return a.interval[1] - b.interval[1];  return a.interval[0] - b.interval[0];});
        int[] ans = new int[intervals.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < intervals.length; i++) {

        // 二分
            int left = 0;
            int right = intervals.length - 1;
            int target = -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (obj[mid].interval[0]  >= intervals[i][1]) {
                    target = obj[mid].idx;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = target;
// 普通查找
//            for (int j = i ; j < intervals.length ; j++) {
//                if (obj[j].interval[0] >=  obj[i].interval[1] ) {
//                        ans[obj[i].idx] = obj[j].idx;
//                    break;
//                }
//            }
    }
        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0 ) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int ans = 1;
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= right) {
                right = intervals[i][1];
                ans++;
            }
        }
        return intervals.length - ans;
    }


    public int countBattleships(char[][] board) {
        int ans = 0;
        for(int i = 0; i < board.length ; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') continue;
                    if (j > 0 && board[i][j - 1] == 'X') continue;
                    ans++;
                }
            }
        }
        return ans;
    }

    public int lastRemaining(int n) {
        int a1 = 1;
        int k = 0, cnt = n, step = 1;
        while (cnt > 1) {
            if ((k & 1) == 0) {
                a1 += step;
            } else if ( (cnt & 1) == 1) {
                a1 += step;
            }
            k++;
            cnt >>=1;
            step <<=1;
        }
        return a1;
    }


    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;
        int[] c  = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (c[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    public List<Integer> lexicalOrder(int n) {
        if (n < 1) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 1; i <= n; i++) {
            ans.add(num);
            // 先尝试在最后一位添加0，这个是下一个最小的字典序数字，例如从 1，10， 然后尝试20的时候跳过
            if (num * 10 <= n) {
                num *= 10;
            } else {
                // 对于n=13
                // 如果累加到9了，那么就需要前一位+1，所以num/10得到前一位 ++， 例如 遍历获取 2-9
                // 如果累加的时候已经大于n了，就需要前一位加一， 遍历获取 11-13
                while( num % 10 == 9  || num + 1 > n) {
                    num /= 10;
                }
                num++;
            }
        }
        return ans;
    }


    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n-1][n-1];
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(checkMidNums(matrix, k, mid)) {
                right = mid ;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

     boolean checkMidNums(int[][] matrix, int k, int mid) {
        int n = matrix.length;
        int i = n -1, j = 0, num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i+1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
     }

        public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.<int[]>comparingInt(v -> v[0]));
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }
        for(int i = 1; i < k ; i++) {
            int[] cur = queue.poll();
            if (cur != null && cur[2] < n - 1) {
                queue.offer(new int[] {matrix[cur[1]][cur[2] + 1], cur[1], cur[2] + 1});
            }
        }
        return Objects.requireNonNull(queue.poll())[0];
    }


    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {return 0;}
        int[] memo = new int[1001];
        Arrays.fill(memo, -1);
        return combinationSum4Dfs(nums, target, memo);
    }

    public int combinationSum4Dfs(int[] nums, int target, int[] memo) {
        if (target < 0 ) return 0;
        if (memo[target] != -1 ) return memo[target];
        if (target == 0) return 1;
        int cnt = 0;
        for (int num : nums) {
            int t = combinationSum4Dfs(nums, target - num, memo);
            cnt += t;
        }
        memo[target] = cnt;
        return cnt;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        int up  =1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                    up = Math.max(up, down + 1);
            } else  if (nums[i] < nums[i - 1]) {
                down = Math.max(down, up + 1);
            }
        }
        return Math.max(up, down);
    }


    /**
     * 375. 猜数字大小 II
     * <a href="https://leetcode.cn/problems/guess-number-higher-or-lower-ii">375. 猜数字大小 II</a>
     * 第一种dp 官方答案，第二重dfs更容易理解
     * @param n n
     * @return ans
     */
    public int getMoneyAmount(int n) {
        // 严格使用 n + 1 的空间
        int[][] dp = new int[n+1][n +1];
        // 从下往上遍历起点 i
        for (int i = n-1; i > 0; i--) {
            // 从左往右遍历终点 j
            for (int j = i+1; j <= n; j++) {
                // 【核心微调】：先假设第一次猜最大值 j，此时右边没有数字，开销为 j + dp[i][j-1]
                dp[i][j] = j + dp[i][j-1];
                // 然后让 k 只遍历到 j - 1，这样 k + 1 最大就是 j，永远不会越界
//                关于为什么k<j即可，
//                当k=j时 cost = j + max(cost[i,j-1], cost[j+1,j]) = j + max(cost[i,j-1], 0) = j + cost[i,j-1]
//                当k=j-1时 cost = j-1 + max(cost[i,j-2], cost[j,j]) = j-1 + max(cost[i,j-2], 0) = j-1 + cost[i,j-2]
//                后者显然小于前者，故无需考虑k=j的情况
                for (int k = i; k < j; k++) {
                    int cost = k + Math.max(dp[i][k-1], dp[k+1][j]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n];
    }

    public int getMoneyAmount2(int n) {
        int[][] memo = new int[n + 1][n + 1];
        return getMoneyAmountDfs(1, n, memo);
    }

    int getMoneyAmountDfs(int i, int j, int[][] memo) {
        if (i>=j) return 0;
        if (memo[i][j] != 0 ) return memo[i][j];
        int cost = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int c = k + Math.max(getMoneyAmountDfs(i, k - 1, memo), getMoneyAmountDfs(k + 1, j, memo));
            cost = Math.min(cost, c);
        }
        memo[i][j] = cost;
        return cost;
    }



    int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while(low <= high) {
            int mid = low + (high - low)/2;
            int guess = guess(mid);
            if(guess == 0) {
                return mid;
            }  else if(guess == -1) {
                high = mid - 1;
            }  else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public List<List<Integer>> kSmallestPairs0(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>(k,Comparator.<int[]>comparingInt(l -> nums1[l[0]] + nums2[l[1]]));
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            q.offer(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();

        while (k-- > 0 && !q.isEmpty()) {
            int[] peek = q.poll();
            ans.add(List.of(nums1[peek[0]], nums2[peek[1]]));

            if (peek[1] + 1 < nums2.length ) {
                q.offer(new int[]{peek[0], peek[1] + 1} );
            }
        }
        return ans;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> lists = new PriorityQueue<>(Comparator.<List<Integer>>comparingInt(l -> l.get(0) + l.get(1)).reversed());
        for (int value : nums1) {
            for (int j = 0; j < nums2.length; j++) {
                ArrayList<Integer> cur = new ArrayList<>();
                cur.add(value);
                cur.add(nums2[j]);
                if (lists.size() < k) {
                    lists.add(cur);
                    continue;
                }
                List<Integer> peek = lists.peek();
                if (peek != null && peek.get(0) + peek.get(1) <= cur.get(0) + cur.get(1)) {
                    if (j == 0) {
                        return new ArrayList<>(lists);
                    }
                    break;
                }
            }
        }
        return new ArrayList<>(lists);
    }

    public int getSum(int a, int b) {
        while (b != 0 ) {
            int  carry = (a & b) << 1;
            a ^= b;
            b = carry;
        }
        return a;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxVal = 0, maxSize = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 ) { dp[i] = Math.max(dp[i], dp[j] + 1); }
            }
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxVal = nums[i];
            }
        }
        // 第 2 步：倒推获得最大子集
        List<Integer> ans = new ArrayList<>();
        // 如果答案只有一个，不需要遍历，返回第一就行
        if (maxSize == 1) {
            ans.add(nums[0]);
            return ans;
        }

        for (int i = n - 1; i >=0 && maxSize > 0 ; i--) {
            if (dp[i] == maxSize && maxVal % nums[i] == 0) {
                ans.add(nums[i]);
                maxVal = nums[i];
                maxSize--;
            }
        }
        return ans;
    }




    public boolean isPerfectSquare(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long)mid * mid;
            if (square == num) {
                return true;
            }
            if (square < num) {
                left = mid + 1;
            } else  {
                right = mid - 1;
            }
        }
        return false;
    }



    /**
     * 365. 水壶问题
     * <a href="https://leetcode.cn/problems/water-and-jug-problem">365. 水壶问题</a>
     * @param x x
     * @param y y
     * @param target target
     * @return ans
     */
    public boolean canMeasureWater2(int x, int y, int target) {
        if (target == 0 ) {return true;}
        if (x + y < target ) { return false;}
        return target % gcd(x,y) == 0;
    }

    public boolean canMeasureWater(int x, int y, int target) {
        Deque<int[]> queue = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        queue.offer(new int[]{0, 0});
         while (!queue.isEmpty()) {
            int[] pop = queue.pop();
            int remainX = pop[0];
            int remainY = pop[1];
            long hash = ((long) remainX << 32) | remainY;
            if (visited.contains(hash)) {continue;}
            // visited
            visited.add(hash);
            if (remainX == target || remainY == target || remainX + remainY == target) {return true;}
            // add state
            queue.offerLast(new int[]{0, remainY});
            queue.offerLast(new int[]{remainX, 0});
            queue.offerLast(new int[]{x, remainY});
            queue.offerLast(new int[]{remainX, y});
            // x > y
            int x1 = Math.min(remainX, y - remainY);
            queue.offerLast(new int[]{remainX - x1, remainY + x1});
            // y ->x
            int y1 = Math.min(remainY, x - remainX);
            queue.offerLast(new int[]{remainX + y1,  remainY - y1});
        }
        return false;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }



    /**
     * 357. 统计各位数字都不同的数字个数
     * <a href="https://leetcode.cn/problems/count-numbers-with-unique-digits/">357. 统计各位数字都不同的数字个数</a>
     * 对于d位数不同数字的个数，d>=2,使用排列组合 ，最高位可选 9,次低位8，然后9-i，到1，
     * 例如 d=3，就是 9*9*8
     *     d=4，就是 9*9*8*7
     *     d=5，就是 9*9*8*7*6
     * 最后累加1-d位数的不同个数就是答案了
     * @param n n
     * @return ans
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0 ) return 1;
        if (n == 1) return 10;
        int ans = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }


    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) { return intersect(nums2, nums1); }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.compute(i, (k, v) -> v == null ? 1 : v + 1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i : nums2) {
            Integer value = map.computeIfPresent(i, (k, v) -> v - 1);
            if (value !=null && value >=0) {
                ans.add(i);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) { return intersection(nums2, nums1); }
            Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> ansSet = new HashSet<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                ansSet.add(num);
            }
        }
        return ansSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(integerIntegerEntry);
            }  else {
                if (queue.peek() != null && queue.peek().getValue() < integerIntegerEntry.getValue()) {
                    queue.poll();
                    queue.add(integerIntegerEntry);
                }
            }
        }

        return queue.stream().mapToInt(Map.Entry::getKey).toArray();
    }


    /**
     * 343. 整数拆分
     * <a href="https://leetcode.cn/problems/integer-break">343. 整数拆分</a>
     * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
     * 返回 你可以获得的最大乘积 。
     * 大于1的数，只需要关注2，3 的拆分，4能拆分为2*2， 大于 4的数据，其实只需要按3拆分就行，
     * @param n n
     * @return ans
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(Math.max(2 * (i - 2), 2 * dp[i - 2]), Math.max(3 * (i - 3), dp[3] * dp[i - 3]));
        }
        return dp[n];
    }

    /**
     * 342. 4的幂
     * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
     * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
     * <a href="https://leetcode.cn/problems/power-of-four">342. 4的幂</a>
     * @param n n
     * @return ans
     */
    public boolean isPowerOfFour(int n) {
        // (n & (n - 1)) == 0 这个是判断是2次幂，保证只有1个1
        // 然后4的次幂在前面的条件下， 1是在 奇数位置的， 例如0 ， 4：100， 16：10000，通过mask确认偶数数位置是0就行
        // 如果是负整数，最高位可以为0，也符合条件
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x2aaaaaaa) == 0;
    }


    /**
     * <a href="https://leetcode.cn/problems/increasing-triplet-subsequence">334. 递增的三元子序列</a>
     * @param nums nums
     * @return f
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int first = nums[0], second = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            if (nums[i] > second) {
                return true;
            } else if (nums[i] > first) {
                second = nums[i];
            } else {
                first = nums[i];
            }
        }
        return false;
    }

    /**
     * <a href="https://leetcode.cn/problems/wiggle-sort-ii/">324. 摆动排序 II</a>
     * @param nums 测试 [1,3,2,2,3,2]
     */
    public void wiggleSort2(int[] nums) {
            int[] arr = nums.clone();
            Arrays.sort(arr);
            int n = nums.length;
            int x = (n + 1) / 2;
            for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
                nums[i] = arr[j];
                if (i + 1 < n) {
                    nums[i + 1] = arr[k];
                }
            }
        }


    /**
     * <a href="https://leetcode.cn/problems/coin-change">322. 零钱兑换</a>
     * @param coins c
     * @param amount a
     * @return ans
     */
    public int coinChange(int[] coins, int amount) {
       int[] dp = new int[amount + 1];
       Arrays.fill(dp, amount + 1);
       dp[0] = 0;
       for(int i = 1; i <= amount; i++) {
           for (int coin : coins) {
               if (coin <= i) {
                   dp[i] = Math.min(dp[i], dp[i - coin] + 1);
               }
           }
       }
       return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * <a href="https://leetcode.cn/problems/bulb-switcher">319. 灯泡开关</a>
     * 第 i 个灯泡状态的修改次数等于 i 的约数个数，当 i 的约数个数为 偶数 时，第 i 个灯泡的状态等价于没有修改（关闭状态）；
     * 当 i 的约数个数为 奇数 时，第 i 个灯泡的状态等价于修改一次（打开状态）
     * 有个推论：约数个数为 奇数 的正整数一定是一个完全平方数！
     * 问题转为 n 个正整数(1, 2, ..., n) 中有多少完全平方数？ 而n中完全平方数个数 答案为 int(sqrt(n)) ， +0.5 是处理精度问题
     * 大概说明这个结论：设P,A,B 为正整数，如果 P=A*B，则A和B为P的因数，因数A和B总是成对出现，是如果他们相等呢？这个时候他们一起只会为因数的个数贡献 1
     *                P=A*A，这种情况对于P来说最多只能出现1次，而这种情况只可能出现在完全平方数中，只有完全平方数的因数的个数是奇数个
     * @param n n
     * @return 亮灯数
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n + 0.5);
    }


    public int maxProduct(String[] words) {
        int ans = 0;
        Arrays.sort(words, Comparator.nullsLast(Comparator.comparingInt(String::length).reversed()));
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            masks[i] = letterMask(words[i]);
        }
        for (int i = 0; i <words.length -1; i++) {
            for (int j = i+1; j < words.length; j++) {
                // 没有交集的情况
                if ((masks[i] & masks[j]) ==0 ) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                    break;
                }
            }
        }
        return ans;
    }

    public static int letterMask(String word) {
        int wordMask = 0;
        for (int i = 0, len = word.length(); i < len; i++) {
            // 将对应字母的位置为 1
            wordMask |= (1 << (word.charAt(i) - 'a'));
        }
        return wordMask;
    }

    public static boolean hasCommonLetter(String... words) {
        if (words == null || words.length < 2) return false;

        int mask = 0xFFFFFFFF;

        for (String word : words) {
            int wordMask = 0;
            for (int i = 0, len = word.length(); i < len; i++) {
                // 将对应字母的位置为 1
                wordMask |= (1 << (word.charAt(i) - 'a'));
            }
            // 求所有单词掩码的交集
            mask &= wordMask;

            // 交集已为空，无需继续处理后续单词，直接返回
            if (mask == 0) return false;
        }
        return true;
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        if (minJump > s.length() || maxJump < minJump) {
            return false;
        }
        int len = s.length();
        boolean[] dp = new boolean[len];
        dp[0] = s.charAt(0) == '0';
        for (int i = 1; i < minJump; i++) {
            dp[i] = false;
        }
        int cur = 0 ;
        for (int i = minJump ; i < len; i++) {
            if (maxJump == minJump) {dp[i] = s.charAt(i) == '0' && dp[i-maxJump] ;continue;}
            int a = i - maxJump - 1;
            if (a >= 0 && dp[a]) cur--;
            int b = i - minJump;
            if (b < len && dp[b]) cur++;
            dp[i] = s.charAt(i) == '0' &&  cur > 0 ;
        }
        return dp[s.length() - 1];
    }

    public int furthestDistanceFromOrigin(String moves) {
        int r = 0, l = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'R': r++; break;
                case 'L': l++; break;
                default:  break;
            }
        }
        if (r > l) return  moves.length() - 2 * l;
        else if (l > r) return  moves.length()- 2 *r ;
        else return moves.length() - r - l;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(
                  Comparator.<List<Integer>>comparingInt(o -> o.get(0) +  o.get(1)).reversed());
        int pre = Integer.MAX_VALUE;
        for (int value : nums1) {
            if (queue.size() >= k && value >= pre) {
                break;
            }
            for (int j = 0; j < nums2.length; j++) {
                int i1 = nums2[j];
                if (queue.size() < k) {
                    queue.add(List.of(value, i1));
                    continue;
                }
                List<Integer> peek = queue.peek();
                if (peek.get(0) + peek.get(1) <= value + i1) {
                    if (j == 0) return new ArrayList<>(queue);
                    break;
                }
                queue.poll();
                queue.add(List.of(value, i1));
            }
        }
       return new ArrayList<>(queue);
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            priorityQueue.add(stone);
        }
        while (priorityQueue.size() > 1) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            int diff = Math.abs(a - b);
            if (diff > 0) {
                priorityQueue.add(diff);
            }
        }
        return !priorityQueue.isEmpty() ? priorityQueue.poll():0;
    }

    public boolean canBeEqual(String s1, String s2) {
        char[] c1 = new char[]{s1.charAt(0),s1.charAt(2)};
        char[] c2 = new char[]{s2.charAt(0),s2.charAt(2)};
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (!Arrays.equals(c1, c2)) return false;
        c1 = new char[]{s1.charAt(1),s1.charAt(3)};
        c2 = new char[]{s2.charAt(1),s2.charAt(3)};
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public int binaryGap(int n) {
        String binaryString = Integer.toBinaryString(n);
        int ans = 0;
        int idx = 0 ;
        while(idx < binaryString.length() && binaryString.charAt(idx) != '1') { idx++;}
        for (int i = idx +1; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '1') {
                ans = Math.max(ans, i - idx);
                idx = i;
            }
        }
        return ans;
    }


    public List<String> buildArray(int[] target, int n) {
        int i = 1;
        List<String> list = new ArrayList<>();
        for (int nums : target) {
            for (; i <= n; i++) {
                list.add("Push");
                if (nums == i) {
                    i++;
                    break;
                } else {
                    list.add("Pop");
                }
            }
        }
        return list;
    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        int l = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < l; ) {
            if (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, nums[i - 1], i);
            } else {
                i++;
            }
        }
        for (int i = 0; i < l; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int l = nums.length;
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        for (int i = 1; i < 101; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] res = new int[l];
        for (int i = 0; i < l; i++) {
            res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return res;
    }

    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (nums[i] > nums[j]) {
                    res[i]++;
                } else if (nums[i] < nums[j]) {
                    res[j]++;
                }
            }
        }
        return res;
    }


    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; ) {
            if (nums[i] != i + 1) {
                if (nums[i] == nums[nums[i] - 1]) {
                    ans[0] = nums[i];
                    i++;
                } else {
                    swap(nums, i, nums[i] - 1);
                }
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans[1] = i + 1;
                break;
            }
        }
        return ans;
    }


    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        int m = primes.length;
        int[] pointers = new int[m];
        long[] nums = new long[m];
        Arrays.fill(nums, 1);
        for (int i = 1; i <= n; i++) {
            long min = Arrays.stream(nums).min().getAsLong();
            dp[i] = Math.toIntExact(min);
            for (int j = 0; j < m; j++) {
                if (nums[j] == min) {
                    pointers[j]++;
                    nums[j] = (long) dp[pointers[j]] * primes[j];
                }
            }
        }
        return dp[n];
    }


    /**
     * 309. 买卖股票的最佳时机含冷冻期
     * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown">309. 买卖股票的最佳时机含冷冻期</a>
     * @param prices p
     * @return ans
     */
    public int maxProfit(int[] prices) {
        if( prices.length < 2) return 0;
        int f0 = -prices[0];
        int f1 = 0, f2= 0;
        int nf0,nf1, nf2;
        for (int i = 1; i < prices.length; i++) {
            nf0 = Math.max(f0, f2 - prices[i]);
            nf1 = f0 + prices[i];
            nf2 = Math.max(f1, f2);
            f0 = nf0;
            f1 = nf1;
            f2 = nf2;
        }
        return Math.max(f1, f2);
    }


    /**
     * 300. 最长递增子序列
     * <a href="https://leetcode.cn/problems/longest-increasing-subsequence">300. 最长递增子序列</a>
     * @param nums nums
     * @return ans
     */
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums.length ==0) return 0;
        int[] dp = new int[nums.length];
        int ans = 1;
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    /**
     * 294. 翻转游戏 II
     * <a href="https://leetcode.cn/problems/flip-game-ii">294. 翻转游戏 II</a>
     * @param currentState state
     * @return ans
     */
    public boolean canWin(String currentState) {
        return canWin(new StringBuilder(currentState));
    }

    public boolean canWin(StringBuilder state) {
        int n = state.length();
        for (int i = 1; i < n; i++) {
            if (state.charAt(i) == '+' && state.charAt(i-1) == '+') {
                state.setCharAt(i, '-');
                state.setCharAt(i -1, '-');
                if (!canWin(state)) {
                    state.setCharAt(i, '+');
                    state.setCharAt(i -1, '+');
                    return true;
                }
                state.setCharAt(i, '+');
                state.setCharAt(i -1, '+');
            }
        }
        return false;
    }


    /**
     * 293. 翻转游戏
     * <a href="https://leetcode.cn/problems/flip-game">293. 翻转游戏</a>
     * @param currentState cs
     * @return ans
     */
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder(currentState);
        for (int i = 1; i < currentState.length(); i++) {
            if (currentState.charAt(i-1) == '+' && currentState.charAt(i) == '+' ) {
                sb.setCharAt(i-1, '-');
                sb.setCharAt(i, '-');
                ans.add(sb.toString());
                sb.setCharAt(i-1, '+');
                sb.setCharAt(i, '+');
            }
        }
        return ans;
    }

    /**
     * 292. Nim 游戏
     * <a href="https://leetcode.cn/problems/nim-game">292. Nim 游戏</a>
     * @param n n
     * @return ans
     */
    public boolean canWinNim(int n) {
            if (n <4) return true;
            return n % 4 != 0;
    }

    /**
     * 287. 寻找重复数
     * <a href="https://leetcode.cn/problems/find-the-duplicate-number">287. 寻找重复数</a>
     * @param nums nums
     * @return ans
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public int findDuplicate2(int[] nums) {
        int i =0;
        while (i < nums.length) {
            if (nums[i] -1 != i) {
                if (nums[i] == nums[nums[i] -1]) {
                    return nums[i];
                }
                swap(nums, i , nums[i] -1);
                continue;
            }
            i++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] -1 != j) {
                return nums[j];
            }
        }
        return -1;
    }


    /**
     * 283. 移动零
     * <a href="https://leetcode.cn/problems/move-zeroes/">283. 移动零</a>
     * @param nums nums
     */
    public void moveZeroes(int[] nums) {
     int l = 0, r = 0;
     while ( r < nums.length) {
         if (nums[r] != 0) swap(nums, l++, r);
         r++;
     }
    }

    /**
     * 280. 摆动排序
     * <a href="https://leetcode.cn/problems/wiggle-sort/">280. 摆动排序</a>
     * @param nums nums
     */
    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length -1; i++) {
            if ( (i % 2 == 0  && nums[i] > nums[i+1] )
                   || (i % 2 == 1 && nums[i] < nums[i +1])
            ) {
                swap(nums, i, i+1);
            }
        }
    }



    /**
     * 279. 完全平方数
     * <a href="https://leetcode.cn/problems/perfect-squares">279. 完全平方数</a>
     * 其实答案只有 1-4， 有数学公式
     * @param n n
     * @return ans
     */
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = n;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }


    public int numSquares2(int n) {
        if (n <= 0) return 0;
        if (n < 4) return n;
        int square = n;
        int sqrt = (int)Math.sqrt(n);
        for (int i = sqrt; i > 1 ; i--) {
            int curSquare = 0;
            int c = 0 ;
            while(n -(c + i * i) >= 0 )  {c += i*i; curSquare ++;}
             curSquare += numSquares(n - c);
            if (curSquare < square) square = curSquare;
            else return square;
        }
        return square;
    }


    /**
     * 278. 第一个错误的版本
     * <a href="https://leetcode.cn/problems/first-bad-version/">278. 第一个错误的版本</a>
     * @param n n
     * @return ans
     */
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start <= end) {
            int mid = (end - start) /2 + start;
            boolean f = isBadVersion(mid);
            if (f) {
                end = mid -1;
            } else {
                start = mid +1;
            }
        }
        return start;
    }
    boolean isBadVersion(int n) {return true;}

    /**
     * 276. 栅栏涂色
     * <a href="https://leetcode.cn/problems/paint-fence">276. 栅栏涂色</a>
     * @param n n
     * @param k k
     * @return ans
     */
    public int numWays(int n, int k) {
        if (n == 1) return k;
        if (n == 2) return k*k;
        int p1 = k, p2 = k*k, t = 0;
        for (int i = 3; i <= n; i++) {
            t = (k -1) * (p1 + p2);
            p1 = p2;
            p2 = t;
        }
        return p2;
    }


    /**
     * 274. H 指数
     * <a href="https://leetcode.cn/problems/h-index/">274. H 指数</a>
     * @param citations c
     * @return ans
     */
    public int hIndex(int[] citations) {
       Arrays.sort(citations);
       int n = citations.length;
        for (int i = n ; i > 0 ; i--) {
            if (citations[n-i] >= i) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 268. 丢失的数字
     * <a href="https://leetcode.cn/problems/missing-number/">268. 丢失的数字</a>
     * 方法一是计算0-n的总和 - 数组的总和， 就会得到缺失的数字
     * 方法二是异或一次数组的，然后再异或0-n，缺失的就是数字就是结果，因为出现两次的被异或为0了
     * @param nums nums
     * @return ans
     */
    public int missingNumber(int[] nums) {
        long sum = 0;
        long n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        return (int)(n * (n + 1) / 2 - sum);
    }

    public int missingNumber2(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }



    public boolean isUgly(int n) {
        if (n < 1) return false;
        while (n > 1) {
            if (n % 2 == 0) n /= 2;
            else if (n % 3 == 0) n /= 3;
            else if (n % 5 == 0) n /= 5;
            else return false;
        }
        return true;

    }

    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 1, i3 = 1, i5 = 1;
        for (int i = 1; i < n; i++) {
            int min = Math.min(Math.min(dp[i2] * 2, dp[i3] * 3),  dp[i5] * 5);
            dp[i] = min;
            if (dp[i] == dp[i2] * 2) {
                i2++;
            }
            if (dp[i] == dp[i3] * 3) {
                i3++;
            }
            if (dp[i] ==  dp[i5] * 5) {
                i5++;
            }
        }
        return dp[n - 1];
    }


    /**
     * 260. 只出现一次的数字 III
     * <a href="https://leetcode.cn/problems/single-number-iii/">260. 只出现一次的数字 III</a>
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int lsb = (xor == Integer.MIN_VALUE ? xor : xor & -xor);
        int n1 = 0, n2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0 ) {
                n1 ^= num;
            } else {
                n2 ^= num;
            }
        }
        return new int[]{n1, n2};
    }


    /**
     * 259. 较小的三数之和
     * <a href="https://leetcode.cn/problems/3sum-smaller">259. 较小的三数之和</a>
     * @param nums nuns
     * @param target target
     * @return ans
     */
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i+1, k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum >= target) {
                    k--;
                } else {
                    ans += k-j;
                    j++;
                }
            }
        }
        return ans;
    }



    /**
     * 258. 各位相加
     * <a href="https://leetcode.cn/problems/add-digits/">258. 各位相加</a>
     * @param num num
     * @return ans
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

    public int addDigits2(int num) {
        while (num > 9) {
            int x = num;
            num = 0;
            while (x > 0){
                num += x % 10;
                x = x/10;
            }
        }
        return num;
    }


    /**
     * 256. 粉刷房子
     * <a href="https://leetcode.cn/problems/paint-house">256. 粉刷房子</a>
     * @param costs c
     * @return ans
     */
    public int minCost(int[][] costs) {
        if (costs.length == 0) {return 0;}
        int dp0 =costs[0][0], dp1= costs[0][1], dp2= costs[0][2];
        int dp0New,dp1New;
        for (int i = 1; i < costs.length; i++) {
            dp0New =  Math.min(dp1, dp2)  + costs[i][0];
            dp1New = Math.min(dp0, dp2)  + costs[i][1];
            dp2 = Math.min(dp0, dp1)  + costs[i][2];
            dp0 = dp0New;
            dp1 = dp1New;
        }
        return Math.min(Math.min(dp0, dp1), dp2);
    }

    public int minCost2(int[][] costs) {
        if (costs.length == 0) {return 0;}
        int[][] dp = new int[costs.length][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2])  + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2])  + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1])  + costs[i][2];
        }
        return Math.min(Math.min(dp[costs.length - 1][0], dp[costs.length - 1][1]), dp[costs.length - 1][2]);
    }


    /**
     * 245. 最短单词距离 III
     * <a href="https://leetcode.cn/problems/shortest-word-distance-iii/">245.最短单词距离III</a>
     */
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length;
        int i1 = -1, i2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (wordsDict[i].equals(word1)) {
                i1 = i;
                ans = ( i2 != -1 && i1 != i2) ? Math.min(ans, Math.abs(i1 - i2)) : ans;
            }
            if (wordsDict[i].equals(word2)) {
                i2 = i;
                ans = (i1 !=-1 && i1 != i2) ? Math.min(ans, Math.abs(i1 - i2)) : ans;
            }
        }
        return ans;
    }

    /**
     * 243. 最短单词距离
     * <a href="https://leetcode.cn/problems/shortest-word-distance/description/">...</a>
     */
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length;
        int i1 = -1, i2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (wordsDict[i].equals(word1)) {
                i1 = i;
                ans = ( i2 != -1 ) ? Math.min(ans, Math.abs(i1 - i2)) : ans;
            } else if (wordsDict[i].equals(word2)) {
                i2 = i;
                ans = (i1 !=-1) ? Math.min(ans, Math.abs(i1 - i2)) : ans;
            }
        }
        return ans;
    }


    /**
     * 239. 滑动窗口最大值
     * https://leetcode.cn/problems/sliding-window-maximum/description/
     * @param nums 数组
     * @param k 窗口大小
     * @return ans
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k -1   && queue.peekFirst() < i-k+1) {
                queue.pollFirst();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            if (i >= k -1) {
                ans[i-k+1] = nums[queue.peek()];
            }
        }
        return ans;
    }

    /**
     * 238. 除自身以外数组的乘积
     * https://leetcode.cn/problems/product-of-array-except-self/description/
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        long sum = 1;
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 ) {
                if (zero == 0) sum *= nums[i] == 0 ? 1 : nums[i];
                else {sum = 0; break;}
                zero++;
            } else {sum *= nums[i];}
        }
        int[] ans = new int[nums.length];
        // 两个0以上
        if (zero > 1) return ans;
        for (int i = 0; i < nums.length; i++) {
            // 没有0
          if (zero == 0) {
              ans[i] = (int)(sum / nums[i]);
              // 1个0
          } else if ( nums[i] == 0) {
              ans[i] = (int)sum;
          }
        }
        return ans;
    }


    /**
     * 229. 多数元素 II
     * https://leetcode.cn/problems/majority-element-ii/description/
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        return Arrays.stream(nums).boxed()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > nums.length / 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    /**
     * 223. 矩形面积
     * https://leetcode.cn/problems/rectangle-area
     *
     * @return int
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int x = calJiaoDistance(ax1, ax2, bx1, bx2);
        int y = calJiaoDistance(ay1, ay2, by1, by2);
        int a1 = (ax2 - ax1) * (ay2 - ay1);
        int a2 = (bx2 - bx1) * (by2 - by1);
        return a1 + a2 - x * y;
    }

    public int calJiaoDistance(int ax1, int ax2, int bx1, int bx2) {
        return Math.max(Math.min(ax2, bx2) - Math.max(ax1, bx1), 0);
    }

    public int calJiaoDistance2(int ax1, int ax2, int bx1, int bx2) {
        int[][] points = new int[2][2];
        if (ax1 <= bx1) {
            points[0][0] = ax1;
            points[0][1] = ax2;
            points[1][0] = bx1;
            points[1][1] = bx2;
        } else {
            points[0][0] = bx1;
            points[0][1] = bx2;
            points[1][0] = ax1;
            points[1][1] = ax2;
        }
        // 无相交
        if (points[1][0] >= points[0][1]) {
            return 0;
        }
        // 包含关系
        if (points[1][1] <= points[0][1]) {
            return points[1][1] - points[1][0];
        }
        return points[0][1] - points[1][0];
    }


    /**
     * 220. 存在重复元素 III
     * https://leetcode.cn/problems/contains-duplicate-iii
     *
     * @param nums
     * @param indexDiff
     * @param valueDiff
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        int left = 0;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(nums[0]);
        for (int right = 1; right < nums.length; right++) {
            if (right - left > indexDiff) {
                set.remove(nums[left]);
                left++;
            }
            if (set.contains(nums[right])) {
                return true;
            }
            Integer ceiling = set.ceiling(nums[right] - valueDiff);
            if (ceiling != null && Math.abs(ceiling - nums[right]) <= valueDiff) {
                return true;
            }
            set.add(nums[right]);
        }
        return false;
    }


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(nums[i]);
            if (idx != null && Math.abs(idx - i) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }


    /**
     * 217. 存在重复元素
     * https://leetcode.cn/problems/contains-duplicate/description/
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }


    /**
     * 216. 组合总和 III
     * https://leetcode.cn/problems/combination-sum-iii/
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        int sum = 0;
        if (n < k) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        combinationSum3(n, k, ans, t, 0, 1);
        return ans;
    }

    public void combinationSum3(int n, int k, List<List<Integer>> ans, List<Integer> cur, int curSum, int index) {
        if (curSum > n || cur.size() > k) {
            return;
        }
        if (cur.size() == k && curSum == n) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i <= 9; i++) {
            if (curSum + i > n) {
                return;
            }
            cur.add(i);
            curSum += i;
            combinationSum3(n, k, ans, cur, curSum, i + 1);
            cur.remove(cur.size() - 1);
            curSum -= i;
        }
    }


    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                queue.add(nums[i]);
            } else if (queue.peek() < nums[i]) {
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }


    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int rob = nums[start];
        int noRob = 0;
        for (int i = start + 1; i <= end; i++) {
            System.out.printf("%d :%d, %d\n", i, noRob, rob);
            int tempNoRob = Math.max(rob, noRob);
            rob = noRob + nums[i];
            noRob = tempNoRob;
        }
        return Math.max(rob, noRob);
    }


    public int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target && left <= right) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }


    public int countPrimes(int n) {
        int ans = 0;
        int[] notPrimes = new int[n + 1];
        for (int i = 2; i < n; i++) {
            if (notPrimes[i] == 0) {
                ans++;
                if (((long) i * i) >= n) {
                    continue;
                }
                for (int j = i * i; j < n; j += i) {
                    notPrimes[j] = 1;
                }
            }
        }
        return ans;
    }

    /*
        会超时
     */
    public int countPrime2s(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes(i)) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isPrimes(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 198. 打家劫舍
     * https://leetcode.cn/problems/house-robber/
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int rob = nums[0];
        int noRob = 0;
        for (int i = 1; i < nums.length; i++) {
            System.out.printf("%d :%d, %d\n", i, noRob, rob);
            int tempNoRob = Math.max(rob, noRob);
            rob = noRob + nums[i];
            noRob = tempNoRob;
        }
        return Math.max(rob, noRob);
    }


    /**
     * 191. 位1的个数
     * https://leetcode.cn/problems/number-of-1-bits/description/
     * 第一种方法，每次 n & n-1 把最低位变为0
     * 第二种方法，直观的每次判断最后一位是否为1，然后右移继续判断最后一位
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= n - 1;
            ++ans;
        }
        return ans;
    }

    public int hammingWeight2(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n & 1;
            n = n >>> 1;
        }
        return ans;
    }


    /**
     * 189. 轮转数组
     * https://leetcode.cn/problems/rotate-array/description/
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        if (k == 0) {
            return;
        }
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }




    /**
     * 179. 最大数
     * https://leetcode.cn/problems/largest-number/
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        String s = Arrays.stream(nums).mapToObj(String::valueOf).sorted((v1, v2) -> (v2 + v1).compareTo(v1 + v2)).collect(Collectors.joining());
        return s.charAt(0) == '0' ? "0" : s;
    }


    /**
     * 172. 阶乘后的零
     * 方法一：优化后5的质因数
     * 方法二：正常数学的求5的质因数方法
     * https://leetcode.cn/problems/factorial-trailing-zeroes/submissions/662331594/
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n / 5;
            n = n / 5;
        }
        return ans;
    }

    public int trailingZeroes2(int n) {
        int ans = 0;
        for (int i = 5; i < n; i++) {
            for (int j = i; j % 5 == 0; j /= 5) {
                ans++;
            }
        }
        return ans;
    }


    public int majorityElement(int[] nums) {
        int cur = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (cur == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    cur = nums[i];
                    count = 1;
                }
            }
        }
        return cur;
    }

    /**
     * 163. 缺失的区间
     * https://leetcode.cn/problems/missing-ranges/
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            ans.add(Arrays.asList(lower, upper));
            return ans;
        }
//        Arrays.sort(nums);
        if (lower < nums[0]) {
            ans.add(Arrays.asList(lower, nums[0] - 1));
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                continue;
            }
            ans.add(Arrays.asList(nums[i] + 1, nums[i + 1] - 1));
        }
        if (upper > nums[nums.length - 1]) {
            ans.add(Arrays.asList(nums[nums.length - 1] + 1, upper));
        }
        return ans;
    }


    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                r = r - 1;
            }
        }
        return nums[l];
    }


    /**
     * https://leetcode.cn/problems/find-peak-element/submissions/661522727/
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                //说明此时mid为上坡路，既然是上坡，那么mid肯定不是山峰，所以left=mid+1（题目要求nums[i]!=nums[i+1]，所以不可能存在“平峰”的情况）
                left = mid + 1;
            } else {
                //说明此时mid为下坡路，那么有可能自己本身就是山峰，或者在下山的过程中，所以right=mid而不能等于mid-1
                right = mid;
            }
        }
        return left;
    }


    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        long ans = nums[0];
        long max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long mx = max, mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            min = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(ans, max);
        }

        return (int) ans;
    }


    /**
     * 150. 逆波兰表达式求值
     * https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Integer b = stack.pop();
                    stack.push(stack.pop() - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    b = stack.pop();
                    stack.push(stack.pop() / b);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }


    /**
     * 137. 只出现一次的数字 II
     * https://leetcode.cn/problems/single-number-ii/
     *
     * @param nums
     * @return
     */
    public int singleNumber2(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 统计对应位数为1的数量
            int total = 0;
            for (int num : nums) {
                total += (num >> i) & 1;
            }
            // 如果不是不是3的倍数，说明有额外的一个数这位是1
            if (total % 3 != 0) {
                // 当前位设置为1
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public int singleNumber21(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }


    /**
     * 136. 只出现一次的数字
     * https://leetcode.cn/problems/single-number/solutions/242211/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    /**
     * 45. 跳跃游戏 II
     * https://leetcode.cn/problems/jump-game-ii/
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i + 1; j <= i + nums[i] && j < n; j++) {
                dp[j] = dp[j] == 0 ? dp[i] + 1 : Math.min(dp[j], dp[i] + 1);
                if (j == n - 1) {
                    return dp[j];
                }
            }
        }
        return dp[n - 1];
    }


    /**
     * 78. 子集 (第三个解法，递归)
     * https://leetcode.cn/problems/subsets/
     *
     * @param nums
     * @return
     */
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subsets(nums, 0);
        return ans;
    }

    public void subsets(int[] nums, int start) {
        ans.add(new ArrayList<>(t));
        for (int i = start; i < nums.length; i++) {
            t.add(nums[i]);
            subsets(nums, i + 1);
            t.remove(t.size() - 1);
        }
    }


    /**
     * 78. 子集 (第一个解法，模拟二进制)
     * https://leetcode.cn/problems/subsets/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 1 << nums.length; i++) {
            ArrayList<Integer> t = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((1 << j & i) != 0) {
                    t.add(nums[j]);
                }
            }
            ans.add(t);
        }
        return ans;
    }


    /**
     * 78. 子集 (第二个解法)
     * https://leetcode.cn/problems/subsets/
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for (int num : nums) {
            int len = ans.size();
            for (int i = 0; i < len; i++) {
                ArrayList<Integer> t = new ArrayList<>(ans.get(i));
                t.add(num);
                ans.add(t);
            }
        }
        return ans;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetsWithDup(nums, 0);
        return ans;
    }

    /**
     * 90. 子集 II （带重复元素的子集，78题不带重复元素）
     * https://leetcode.cn/problems/subsets-ii/
     *
     * @param nums
     * @param start
     */
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


    /**
     * 81. 搜索旋转排序数组 II
     * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    public boolean search(int[] nums, int target, int start, int end) {
        if (start > end) {
            return false;
        }
        int mid = start + (end - start) / 2;
        if (nums[mid] == target || nums[start] == target || nums[end] == target) {
            return true;
        }
        if (nums[start] < nums[mid]) {
            if (nums[mid] >= target && nums[start] <= target) {
                return search(nums, target, start, mid - 1);
            } else {
                return search(nums, target, mid + 1, end);
            }
        } else if (nums[end] > nums[mid]) {
            if (nums[mid] <= target && nums[end] >= target) {
                return search(nums, target, mid + 1, end);
            } else {
                return search(nums, target, start, mid - 1);
            }
        } else {
            return search(nums, target, ++start, --end);
        }
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
        return total >= 0 ? idx + 1 : -1;
    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            if (gas[i] < cost[i] || gas[i] == 0) {
                continue;
            }
            int remaining = gas[i] - cost[i];
            int j = i;
            for (j = i + 1; j < i + n; j++) {
                remaining = remaining + gas[j % n] - cost[j % n];
                if (remaining < 0) {
                    break;
                }
            }
            if (j == i + n) {
                return i;
            }
            i = j;
        }
        return -1;
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

    /**
     * 一共有 m+n−1 条对角线. 设对角线从上到下的编号为 i∈[0,m+n−2]
     * 当 i 为偶数时，则第 i 条对角线的走向是从下往上遍历:每次行索引减 1，列索引加 1
     * 当 i<m 时，则此时对角线遍历的起点位置为 (i,0)；
     * 当 i≥m 时，则此时对角线遍历的起点位置为 (m−1,i−m+1)；
     * 当 i 为奇数时，则第 i 条对角线的走向是从上往下遍历:每次行索引加 1，列索引减 1
     * 当 i<n 时，则此时对角线遍历的起点位置为 (0,i)；
     * 当 i≥n 时，则此时对角线遍历的起点位置为 (i−n+1,n−1)；
     * @param mat mat
     * @return ans
     */
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[n * m];
        int idx = 0;
        for (int i = 0; i < m +n -1; i++) {
            if ((i & 1) == 0) {
                int x = i < m ? i : m -1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    ans[idx++] = mat[x--][y++];
                }
            } else {
                int x = i < n ? 0 : i - n  + 1;
                int y = i <n ? i : n - 1;
                while (x < m && y >= 0) {
                    ans[idx++] = mat[x++][y--];
                }
            }
        }
        return ans;
    }

    public int[] findDiagonalOrder2(int[][] mat) {
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
