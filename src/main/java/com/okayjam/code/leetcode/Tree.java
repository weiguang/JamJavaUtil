package com.okayjam.code.leetcode;

import java.util.*;
import com.okayjam.code.leetcode.LinkedListJam.ListNode;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:50
 **/
public class Tree {






	/**
	 * 99. 恢复二叉搜索树
	 * https://leetcode.cn/problems/recover-binary-search-tree/
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


	TreeNode p = null;
	public void flatten(TreeNode root) {
		if (root == null) { return;}
		TreeNode right = root.right;
		TreeNode left = root.left;
		root.left = null;
		root.right = null;
		if (p == null) {
			p = root;
		} else {
			p.right = root;
			p = p.right;
		}
		flatten(left);
		flatten(right);
	}

	public void flatten1(TreeNode root) {
		if (root == null) { return;}
		TreeNode right = root.right;
		TreeNode left = root.left;
		root.left = null;
		root.right = null;
		p.right = root;
		p = p.right;
		flatten1(left);
		flatten1(right);
	}


	List<List<Integer>> ans = new ArrayList<>();
	List<Integer> t = new ArrayList<>();
	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		if (root == null) {return ans;}
		t.add(root.val);
		if (root.left == null && root.right == null ) {
			if (root.val == targetSum) {
				ans.add(new ArrayList<>(t));
			}
			t.remove(t.size() - 1);
			return ans;
		}
		pathSum(root.left, targetSum - root.val);
		pathSum(root.right, targetSum - root.val);
		t.remove(t.size() - 1);
		return ans;
	}

	public boolean hasPathSum(TreeNode root, int targetSum) {
		if (root == null) {return false;}
		if (root.left == null && root.right == null ) {return root.val == targetSum;}
		return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
	}


	public int minDepth(TreeNode root) {
		if (root == null) {return 0;}
		if (root.left == null && root.right == null) {return 1;}
		if  (root.left == null) {return minDepth(root.right) + 1;}
		if (root.right == null) {return minDepth(root.left) + 1;}
		return Math.min(minDepth(root.left), minDepth(root.right)) +1;
	}



	public int maxDepth(TreeNode root) {
		if (root == null) {return 0;}
		if  (root.left == null && root.right == null) {return 1;}
		int leftDepth = maxDepth(root.left);
		int rightDepth = maxDepth(root.right);
		return Math.max(leftDepth, rightDepth) + 1;
	}


	/**
	 * 103. 二叉树的锯齿形层序遍历
	 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null) {return Collections.emptyList();}
		List<List<Integer>> ans = new ArrayList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> levelAns = new ArrayList<>(size);
			ans.add(levelAns);
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.pollFirst();
				if (ans.size()  % 2 == 0) {
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
		if (root == null) {return Collections.emptyList();}
		List<List<Integer>> ans = new ArrayList<>();
		Deque<TreeNode> queue = new LinkedList<>();
		queue.addLast(root);
		while(!queue.isEmpty()) {
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
		if (n == 0) {return new ArrayList<>();}
		return generateTrees(1, n);
	}

	public int numTrees(int n) {
		int[] dp = new int[n +1];
		dp[0] =dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] += dp[j - 1] * dp[i - j];
			}
		}
		return dp[n];
	}

	public List<TreeNode> generateTrees(int start, int  end) {
		List<TreeNode> ans = new ArrayList<>();
		if (start > end )  {ans.add(null); return ans;}
		if (start == end) { ans.add(new TreeNode(start)); return ans;}
		for (int i = start; i <= end ; i++) {
			List<TreeNode> left =  generateTrees(start, i -1);
			List<TreeNode> right =  generateTrees(i +1, end);
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
			}else {
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
		while(!stack.isEmpty()) {
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

	public void preorderTraversal(List<Integer>list, TreeNode root) {
		if (root != null) {
			list.add(root.val);
			preorderTraversal(list, root.left);
			preorderTraversal(list, root.right);
		}
	}


	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
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
