package com.okayjam.code.leetcode;

import java.util.*;

import com.okayjam.code.leetcode.LinkedListJam.ListNode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:50
 **/
public class Tree {

    public static void main(String[] args) {
//        TreeNode left = new TreeNode(3, null, null);
//        TreeNode right = new TreeNode(15, null, null);
//        TreeNode root = new TreeNode(7, left, right);
//        BSTIterator iterator = new BSTIterator(root);
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        new Tree().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }

    /**
     * 543. 二叉树的直径
     * @param root root
     * @return ans
     */
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeDeep(root);
        return diameterOfBinaryTreeAns;
    }

    int diameterOfBinaryTreeAns = 0;
    public int diameterOfBinaryTreeDeep(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTreeDeep(root.left);
        int right = diameterOfBinaryTreeDeep(root.right);
        diameterOfBinaryTreeAns = Math.max(diameterOfBinaryTreeAns, left + right);
        return Math.max(left, right) + 1;
    }


    /**
     * 538. 把二叉搜索树转换为累加树
     * @param root root
     * @return ans
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) { return null; }
        convertBST(root.right);
        convertBSTSum += root.val;
        root.val = convertBSTSum;
        convertBST(root.left);
        return root;
    }
    int convertBSTSum = 0;



    /**
     * 530. 二叉搜索树的最小绝对差
     * 中序遍历可以得到有序序列
     * @param root root
     * @return ans
     */
    public int getMinimumDifference(TreeNode root) {
        getMinimumDifferenceDfs(root);
        return minimumDifferenceAns;
    }

    int minimumDifferenceAns = Integer.MAX_VALUE;
    int minimumDifferencePre = -1;
    private void getMinimumDifferenceDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimumDifferenceDfs(root.left);
        // 中序遍历， 不过需要记住 前一个节点的信息，所以加了个pre全局参数
        if (minimumDifferencePre == -1) {
            minimumDifferencePre = root.val;
        } else {
            minimumDifferenceAns = Math.min(minimumDifferenceAns, root.val - minimumDifferencePre);
            minimumDifferencePre = root.val;
        }
        getMinimumDifferenceDfs(root.right);
    }
    



        public List<Integer> largestValues(TreeNode root) {
        if(root == null) return Collections.emptyList();
        List<Integer> ans = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode p = queue.poll();
                max = Math.max(max, p.val);
                if (p.left != null) queue.add(p.left);
                if (p.right != null) queue.add(p.right);
            }
            ans.add(max);
        }
        return ans;
    }

    /**
     * 513. 找树左下角的值 Find Bottom Left Tree Value
     * 使用广度优先搜索遍历每一层的节点。在遍历一个节点时，需要先把它的非空右子节点放入队列，然后再把它的非空左子节点放入队列，这样才能保证从右到左遍历每一层的节点
     * 。广度优先搜索所遍历的最后一个节点的值就是最底层最左边节点的值。
     * @param root root
     * @return ans
     */
    public int findBottomLeftValue(TreeNode root) {
        int ret = 0;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.right != null) {
                queue.offer(p.right);
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            ret = p.val;
        }
        return ret;
    }


    public int findBottomLeftValue2(TreeNode root) {
        findFrequentTreeSum(root, 1);
        return findBottomLeftValue;
    }

    int findBottomLeftValue;
    int findBottomLeftValueDeep = 0;
    public void findFrequentTreeSum(TreeNode root, int deep) {
        if (root == null) return;
        if ((root.left == null && root.right == null) && deep > findBottomLeftValueDeep) {
            findBottomLeftValueDeep = deep;
            findBottomLeftValue = root.val;
        }
        findFrequentTreeSum(root.left, deep + 1);
        findFrequentTreeSum(root.right, deep + 1);
    }

    /**
     * 508. 出现次数最多的子树元素和
     * @param root root
     * @return ans
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        findFrequentTreeSum(root, map);
        return map.entrySet().stream().filter(v -> v.getValue() == findFrequentTreeSumMaxCnt).mapToInt(Map.Entry::getKey).toArray();
    }

    int findFrequentTreeSumMaxCnt = 0;
    public int findFrequentTreeSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0 ;
        int sum =root.val + findFrequentTreeSum(root.left, map) +  findFrequentTreeSum(root.right, map);
        int cnt = map.merge(sum, 1, Integer::sum);
        // 更新最大值
        findFrequentTreeSumMaxCnt = Math.max(findFrequentTreeSumMaxCnt, cnt);
        return sum;
    }

    /**
     * 501. 二叉搜索树中的众数
     * @param root root
     * @return ans
     */
    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        // 使用content传递这几个参数，这个几参数会更新 int base = 0, count = 0, max = 0;
        int[] content = new int[3];
        findModeDfs(root, content, ans);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void findModeDfs(TreeNode root, int[] content, List<Integer> ans) {
        if (root == null) return;
        findModeDfs(root.left, content, ans);
        findModeUpdate(root.val, content, ans);
        findModeDfs(root.right, content, ans);
    }

    private void findModeUpdate(int val, int[] content, List<Integer> ans) {
        if (val == content[0]) content[1]++;
        else {content[0] = val; content[1] = 1;}
        if (content[1] == content[2]) ans.add(val);
        else if (content[1] > content[2]) {
            content[2] = content[1];
            ans.clear();
            ans.add(val);
        }
    }



    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val == key) {
            if (root.right == null && root.left == null) {
                return null;
            }
            if (root.right == null  ) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        } else if (root.val > key ) {
            root.left = deleteNode(root.left, key);
        } else  {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }
        int ret;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }


    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, false);
    }

    public int sumOfLeftLeaves(TreeNode root, boolean left) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return left ? root.val: 0;
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }


    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }

    public int[] countBits2(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int t = i;
            while (t != 0 ) {
                t &= t - 1;
                res[i]++;
            }
        }
        return res;
    }

    public int rob(TreeNode root) {
        int[] ints = robDfs(root);
        return Math.max(ints[0], ints[1]);
    }

    public int[] robDfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = robDfs(root.left);
        int[] right = robDfs(root.right);
        int selected = left[1] + right[1] + root.val;
        int nonSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]) ;
        return new int[]{selected, nonSelected};
    }


    public boolean isValidSerialization(String preorder) {
        int count = 0;
        String[] split = preorder.split(",");
        if ("#".equals(preorder)) return true;
        if(split[0].charAt(0) == '#') {return false;}
        count = 2;
        for (int i = 1; i < split.length; i++) {
            if (split[i].charAt(0) == '#') {
                count--;
                if (count == 0 && i < split.length -1) {return false;}
            } else {
                count++;
            }
        }
        return count == 0;
    }


    /**
     * 298. 二叉树最长连续序列
     * <a href="https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence/">298. 二叉树最长连续序列</a>
     *
     * @param root root
     * @return ans
     */
    public int longestConsecutive(TreeNode root) {
        return root == null ? 0 : longestConsecutive(root, 1);
    }

    public int longestConsecutive(TreeNode root, int cur) {
        if (root == null) return cur;
        int l = 1, r = 1;
        if (root.left != null) {
            l = longestConsecutive(root.left, root.left.val == root.val + 1 ? cur + 1 : 1);
        }
        if (root.right != null) {
            r = longestConsecutive(root.right, root.right.val == root.val + 1 ? cur + 1 : 1);
        }
        return Math.max(cur, Math.max(l, r));
    }


    /**
     * 285. 二叉搜索树中的中序后继
     * <a href="https://leetcode.cn/problems/inorder-successor-in-bst">285. 二叉搜索树中的中序后继</a>
     *
     * @param root root
     * @param p    p
     * @return ans
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        if (p.right != null) {
            ans = p.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return ans;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                ans = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ans;
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null, node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (pre == p) {
                return node;
            }
            pre = node;
            node = node.right;
        }
        return null;
    }


    /**
     * 270. 最接近的二叉搜索树值
     * <a href="https://leetcode.cn/problems/closest-binary-search-tree-value/">270. 最接近的二叉搜索树值</a>
     *
     * @param root   root
     * @param target target
     * @return ans
     */
    public int closestValue(TreeNode root, double target) {
        TreeNode ans = root, cur = root;
        while (cur != null) {
            double ansVal = Math.abs(ans.val - target);
            double curVal = Math.abs(cur.val - target);
            if (ansVal > curVal || (ansVal == curVal && cur.val < ans.val)) {
                ans = cur;
            }
            if (Math.abs(target - cur.val) < 0.0000001) {
                return cur.val;
            }
            if (cur.left == null) {
                cur = cur.right;
            } else if (cur.right == null) {
                cur = cur.left;
            } else if (cur.val > target) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans.val;
    }

    /**
     * 261. 以图判树
     * <a href="https://leetcode.cn/problems/graph-valid-tree/">261. 以图判树</a>
     *
     * @param n     n
     * @param edges edges
     * @return ans
     */
    public boolean validTree(int n, int[][] edges) {
        // 方便遍历，先换个数据结构保存边
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjacencyList.get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                stack.push(neighbour);
                parent.put(neighbour, node);
            }
        }
        return parent.size() == n;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<String> cur = new ArrayList<>();
        binaryTreePaths(root, ans, cur);
        return ans;
    }

    public void binaryTreePaths(TreeNode root, List<String> ans, List<String> cur) {
        if (root == null) return;
        cur.add(root.val + "");
        if (root.left == null && root.right == null) {
            ans.add(String.join("->", cur));
        } else {
            binaryTreePaths(root.left, ans, cur);
            binaryTreePaths(root.right, ans, cur);
        }
        cur.remove(cur.size() - 1);
    }


    /**
     * 255. 验证二叉搜索树的前序遍历序列
     * <a href="https://leetcode.cn/problems/verify-preorder-sequence-in-binary-search-tree">255. 验证二叉搜索树的前序遍历序列</a>
     *
     * @param preorder
     * @return
     */
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new LinkedList<>();
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < preorder.length; i++) {
            int num = preorder[i];
            if (num <= min) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

    public boolean verifyPreorder2(int[] preorder) {
        return verifyPreorderDfs(preorder, 0, preorder.length);
    }

    public boolean verifyPreorderDfs(int[] preorder, int start, int end) {
        if (start >= end) return true;
        int mid = start + 1;
        while (mid < end && preorder[mid] < preorder[start]) {
            mid++;
        }
        for (int i = mid; i < end; i++) {
            if (preorder[i] < preorder[start]) {
                return false;
            }
        }
        return verifyPreorderDfs(preorder, start + 1, mid) &&
                verifyPreorderDfs(preorder, mid, end);
    }


    /**
     * 250. 统计同值子树
     * <a href="https://leetcode.cn/problems/count-univalue-subtrees/">250. 统计同值子树</a>
     *
     * @param root root
     * @return ans
     */
    public int countUnivalSubtrees(TreeNode root) {
        countUnivalSubtreesDfs(root);
        return countUnivalSubtreesAns;
    }

    int countUnivalSubtreesAns = 0;

    public boolean countUnivalSubtreesDfs(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) {
            countUnivalSubtreesAns++;
            return true;
        }
        boolean l = countUnivalSubtreesDfs(root.left);
        boolean r = countUnivalSubtreesDfs(root.right);
        if (!l || (root.left != null && root.left.val != root.val)) return false;
        if (!r || (root.right != null && root.right.val != root.val)) return false;
        countUnivalSubtreesAns++;
        return true;
    }


    TreeNode lowestAns = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorSearch(root, p, q);
        return lowestAns;
    }

    public boolean lowestCommonAncestorSearch(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null) return false;
        boolean l = lowestCommonAncestorSearch(cur.left, p, q);
        boolean r = lowestCommonAncestorSearch(cur.right, p, q);
        if (l && r) {
            lowestAns = cur;
        } else if ((l || r) && (cur.val == q.val || cur.val == p.val)) {
            lowestAns = cur;
        }
        return l || r || cur.val == q.val || cur.val == p.val;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
     *
     * @return common
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }


    /**
     * 230. 二叉搜索树中第 K 小的元素
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/">...</a>
     *
     * @return int
     */
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) break;
            root = root.right;
        }
        return root == null ? 0 : root.val;
    }


    TreeNode ansk = null;

    public int kthSmallest2(TreeNode root, int k) {
        deepSearch(root, k, 0);
        return ansk.val;
    }

    public int deepSearch(TreeNode root, int k, int base) {
        if (root == null || ansk != null) {
            return 0;
        }
        int left = deepSearch(root.left, k, base);
        if (base + left + 1 == k) {
            ansk = root;
            return left + 1;
        }
        int right = deepSearch(root.right, k, left + 1 + base);
        return left + right + 1;
    }


    /**
     * 226. 翻转二叉树
     * https://leetcode.cn/problems/invert-binary-tree/description/
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        return root;
    }


    /**
     * 222. 完全二叉树的节点个数
     * https://leetcode.cn/problems/count-complete-tree-nodes/description/
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lh = 0, rh = 0;
        TreeNode t = root;
        while (t != null) {
            lh++;
            t = t.left;
        }
        t = root;
        while (t != null) {
            rh++;
            t = t.right;
        }
        // 完全二叉树
        if (lh == rh) {
            return (int) Math.pow(2, lh) - 1;
        }
        // 非完全二叉树
        return 1 + countNodes(root.left) + countNodes(root.right);
    }


    /**
     * 199. 二叉树的右视图
     * https://leetcode.cn/problems/binary-tree-right-side-view/submissions/663336595/
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans.add(queue.peekLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return ans;
    }

    static class BSTIterator {
        Deque<TreeNode> queue;

        public BSTIterator(TreeNode root) {
            queue = new LinkedList<TreeNode>();
            TreeNode node = root;
            while (node != null) {
                queue.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode poll = queue.pop();
            if (poll == null) return -1;
            TreeNode node = poll.right;
            while (node != null) {
                queue.push(node);
                node = node.left;
            }
            return poll.val;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }


    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode l = upsideDownBinaryTree(root.left);
        upsideDownBinaryTree(root.right);
        left.left = right;
        left.right = root;
        root.left = null;
        root.right = null;
        return l;
    }

    /**
     * 99. 恢复二叉搜索树
     * https://leetcode.cn/problems/recover-binary-search-tree/
     *
     * @param root
     */

    //第一个最大值节点
    TreeNode firstMax = null;
    //最后一个最小值节点
    TreeNode lastMin = null;
    //前一个节点
    TreeNode prev;

    public void recoverTree(TreeNode root) {
        helper(root);
        if (firstMax != null && lastMin != null) {
            int tmp = firstMax.val;
            firstMax.val = lastMin.val;
            lastMin.val = tmp;
        }
    }

    public void helper(TreeNode root) {
        if (null == root) return;
        helper(root.left);
        if (prev != null && root.val < prev.val) {
            lastMin = root;
            if (firstMax == null) firstMax = prev;
        }
        prev = root;
        helper(root.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null) {
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            } else {
                return false;
            }
        }
        return p == null && q == null;
    }

    /**
     * 101. 对称二叉树
     * https://leetcode.cn/problems/symmetric-tree/
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }


    public boolean isSymmetric(TreeNode root, TreeNode root1) {
        if (root != null && root1 != null) {
            return root.val == root1.val && isSymmetric(root.left, root1.right) && isSymmetric(root.right, root1.left);
        }
        return root == null && root1 == null;
    }


    /**
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * 105. 从前序与中序遍历序列构造二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>((int) (preorder.length / 0.75) + 1);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreePreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    public TreeNode buildTreePreIn(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = map.get(rootVal);
        int len = rootIndex - inStart;
        root.left = buildTreePreIn(preorder, inorder, preStart + 1, preStart + len, inStart, rootIndex - 1, map);
        root.right = buildTreePreIn(preorder, inorder, preStart + len + 1, preEnd, rootIndex + 1, inEnd, map);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>((int) (inorder.length / 0.75) + 1);
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeInPost(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, inMap);
    }

    public TreeNode buildTreeInPost(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inMap.get(rootVal);
        int len = inRootIndex - inStart;
        root.left = buildTreeInPost(inorder, postorder, inStart, inRootIndex - 1, postStart, postStart + len - 1, inMap);
        root.right = buildTreeInPost(inorder, postorder, inRootIndex + 1, inEnd, postStart + len, postEnd - 1, inMap);
        return root;
    }


    /**
     * 107. 二叉树的层序遍历 II
     * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelAns = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                levelAns.add(node.val);
            }
            ans.add(levelAns);
        }
        Collections.reverse(ans);
        return ans;
    }


    /**
     * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }


    /**
     * 109. 有序链表转换二叉搜索树
     * https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == null || head == tail) {
            return null;
        }
        ListNode mid = searchMid(head, tail);
        if (mid == null) {
            return null;
        }
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(mid.next, tail);
        return root;
    }

    public ListNode searchMid(ListNode head, ListNode target) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != target && fast.next != target) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 110. 平衡二叉树
     * https://leetcode.cn/problems/balanced-binary-tree/
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getTreeDeep(root) >= 0;
    }

    public int getTreeDeep(TreeNode root) {
        if (root == null) return 0;
        int left = getTreeDeep(root.left);
        int right = getTreeDeep(root.right);
        if (left == -1 || right == -1 || Math.abs(getTreeDeep(root.left) - getTreeDeep(root.right)) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 116. 填充每个节点的下一个右侧节点指针
     * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                cur.next = (i == size - 1) ? null : q.peek();
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
        }
        return root;
    }


    StringBuilder sumNumbersAnsSb = new StringBuilder();
    int sumNumbersAns = 0;

    public int sumNumbers(TreeNode root) {
        if (root == null) return sumNumbersAns;
        sumNumbersAnsSb.append(root.val);
        if (root.left == null && root.right == null) {
            sumNumbersAns += Integer.parseInt(sumNumbersAnsSb.toString());
            sumNumbersAnsSb.deleteCharAt(sumNumbersAnsSb.length() - 1);
            return sumNumbersAns;
        }
        sumNumbers(root.left);
        sumNumbers(root.right);
        sumNumbersAnsSb.deleteCharAt(sumNumbersAnsSb.length() - 1);
        return sumNumbersAns;
    }


    TreeNode tp = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        root.right = null;
        if (tp == null) {
            tp = root;
        } else {
            tp.right = root;
            tp = tp.right;
        }
        flatten(left);
        flatten(right);
    }

    public void flatten1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = null;
        root.right = null;
        tp.right = root;
        tp = tp.right;
        flatten1(left);
        flatten1(right);
    }


    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> t = new ArrayList<>();

    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return ans;
        }
        t.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                ans.add(new ArrayList<>(t));
            }
            t.remove(t.size() - 1);
            return ans;
        }
        pathSum1(root.left, targetSum - root.val);
        pathSum1(root.right, targetSum - root.val);
        t.remove(t.size() - 1);
        return ans;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    /**
     * 103. 二叉树的锯齿形层序遍历
     * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelAns = new ArrayList<>(size);
            ans.add(levelAns);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                if (ans.size() % 2 == 0) {
                    levelAns.add(0, node.val);
                } else {
                    levelAns.add(node.val);
                }
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
//			if(ans.size() %2 == 0) {
//				Collections.reverse(levelAns);
//			}
        }
        return ans;
    }


    public List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelAns = new ArrayList<>(size);
            ans.add(levelAns);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.pollFirst();
                levelAns.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }

        }
        return ans;
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long low, long high) {
        if (root == null) {
            return true;
        }
        if (root.val <= low || root.val >= high) {
            return false;
        }
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }


    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        if (start == end) {
            ans.add(new TreeNode(start));
            return ans;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode cur = new TreeNode(i);
                    cur.left = leftNode;
                    cur.right = rightNode;
                    ans.add(cur);
                }
            }
        }
        return ans;
    }


    /**
     * 二叉树的层序遍历
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            // 当前队列里面的数据就是目前这一层的节点
            int curSize = queue.size();
            List<Integer> level = new ArrayList<>(queue.size() + 1);
            list.add(level);
            for (int i = 0; i < curSize; i++) {
                TreeNode pop = queue.pop();
                level.add(pop.val);
                if (pop.left != null) {
                    queue.addLast(pop.left);
                }
                if (pop.right != null) {
                    queue.addLast(pop.right);
                }
            }
        }
        // 如果需要翻转
//		Collections.reverse(list);
        return list;
    }

    /**
     * 二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        return list;
    }


    /**
     * 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        return list;
    }


    /**
     * 144. 二叉树的前序遍历
     * https://leetcode.cn/problems/binary-tree-preorder-traversal/
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeFirst();
            list.add(node.val);
            if (node.right != null) {
                stack.addFirst(node.right);
            }
            if (node.left != null) {
                stack.addFirst(node.left);
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversal(list, root);
        return list;
    }

    public void preorderTraversal(List<Integer> list, TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal(list, root.left);
            preorderTraversal(list, root.right);
        }
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }


}
