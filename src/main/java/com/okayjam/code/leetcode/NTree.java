package com.okayjam.code.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/07/31 09:03
 **/
public class NTree {

    /**
     * 590. N-ary Tree Postorder Traversal
     * @param root root
     * @return ans
     */
    public List<Integer> postorder(Node root) {
        if (root == null) return postorderAns;
        for (Node child : root.children) {
            postorder(child);
        }
        postorderAns.add(root.val);
        return postorderAns;
    }

    List<Integer> postorderAns = new ArrayList<>();
    public List<Integer> postorder2(Node root) {
        if (root == null) return postorderAns;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (int i = 0; i < pop.children.size() ; i++) {
                stack.push(pop.children.get(i));
            }
            postorderAns.add(pop.val);
        }
        Collections.reverse(postorderAns);
        return postorderAns;
    }

    /**
     * 589. N-ary Tree Preorder Traversal
     * 递归和迭代
     * @param root root
     * @return ans
     */
    public List<Integer> preorder(Node root) {
        if (root == null) return preorderAns;
        preorderAns.add(root.val);
        for (Node child : root.children) {
            preorder(child);
        }
        return preorderAns;
    }

    List<Integer> preorderAns = new ArrayList<>();
    public List<Integer> preorder2(Node root) {
        if (root == null) return preorderAns;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            preorderAns.add(pop.val);
            for (int i = pop.children.size() - 1; i >= 0 ; i--) {
                stack.push(pop.children.get(i));
            }
        }
        return preorderAns;
    }

    /**
     * 559. N 叉树的最大深度
     * @param root root
     * @return ans
     */
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int max = 0;
        for (Node child : root.children) {
           max = Math.max(max, maxDepth(child));
        }
        return max + 1;
    }


    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {return Collections.emptyList();}
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<Integer> list = new ArrayList<>();
            while(qSize-- > 0) {
                Node node = queue.poll();
                list.add(node.val);
                if (node.children != null) {
                    queue.addAll(node.children);
                }
            }
            res.add(list);
        }
        return res;
    }

}


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};