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
