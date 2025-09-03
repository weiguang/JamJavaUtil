package com.okayjam.code.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/08/02 20:50
 **/
public class Tree {
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


}
