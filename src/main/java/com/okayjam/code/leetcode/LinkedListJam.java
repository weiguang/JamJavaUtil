package com.okayjam.code.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @author chen2
 */

class ListNode {
	int val;
	ListNode next;
	ListNode() {}
	ListNode(int val) {
		this.val = val;
	}
	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}

class Node {
	int val;
	Node next;
	Node random;

	public Node(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}
}


/**
 * @author chen2
 */
public class LinkedListJam {

	/**
	 * https://leetcode.cn/problems/reorder-list/
	 *  重排链表
	 * @param head
	 */
	public void reorderList(ListNode head) {
		if (head == null || head.next == null) {
			return;
		}
		ListNode slow = head, fast = head, pre= null;
		while (fast != null && fast.next != null) {
			pre = slow;
			slow = slow.next;
			fast = fast.next.next;
		}
		pre.next = null;
		slow = reverseList(slow);
		ListNode cur = head;
		while(cur.next != null) {
			ListNode t = cur.next;
			cur.next = slow;
			slow = slow.next;
			cur.next.next =t;
			cur = t;
		}
		cur.next  = slow;
	}

	/**
	 * 环形链表 II
	 * https://leetcode.cn/problems/linked-list-cycle-ii/
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow = head, fast = head;
		while (fast != null) {
			slow = slow.next;
			if (fast.next != null) {
				fast = fast.next.next;
			} else {
				return null;
			}
			if (fast == slow) {
				ListNode cur = head;
				while (cur != slow) {
					cur = cur.next;
					slow = slow.next;
				}
				return slow;
			}
		}
		return null;
	}

	/**
	 * 环形链表
	 * https://leetcode.cn/problems/linked-list-cycle/
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		ListNode slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}



	/**
	 * 复制带随机指针的链表
	 * https://leetcode.cn/problems/copy-list-with-random-pointer/
	 * @param head
	 * @return
	 */
	public Node copyRandomList(Node head) {
		// 合并
		for (Node cur = head; cur != null ; cur = cur.next.next) {
			Node node = new Node(cur.val);
			node.next = cur.next;
			cur.next = node;
		}

		for (Node cur = head; cur != null ; cur = cur.next.next) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
		}
		Node newHead = new Node(-1);
		for (Node cur = head, new_cur = newHead; cur != null;) {
			new_cur.next = cur.next;
			new_cur = cur.next;
			cur.next = cur.next.next;
			cur = cur.next;
		}
		return newHead.next;
	}


	public ListNode reverseKGroup(ListNode head, int k) {
		int i = 0;
		ListNode p = head;
		ListNode prev = new ListNode(-1, head);
		ListNode f = prev;
		while( p != null) {
			if (++i == k) {
				ListNode begin = f.next ,end = p;
				p = p.next;
				ListNode reverse = reverse(f, begin, end);
				f = begin;
				i=0;
			} else {
				p = p.next;
			}
		}
		return prev.next;
	}

	public ListNode reverse(ListNode pre, ListNode head, ListNode end) {
		ListNode endNext = end == null? null: end.next;
		if (pre == null) {
			pre = new ListNode(-1, head);
		}
		ListNode p = head, cur = p.next, next ;
		while (cur != endNext) {
			pre.next = cur;
			next = cur.next;
			cur.next = p;
			p=cur;
			cur = next;
		}
		head.next = endNext;
		return pre.next;
	}


	/**
	 * 反转链表
	 * https://leetcode.cn/problems/reverse-linked-list/
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	public ListNode reverseList2(ListNode head) {
		ListNode p = head, pre = null;
		while(p != null) {
			ListNode next = p.next;
			p.next = pre;
			pre = p;
			p = next;
		}
		return pre;
	}



	/**
	 * 两两交换链表中的节点
	 * https://leetcode.cn/problems/swap-nodes-in-pairs/
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null ) {
			return head;
		}
		ListNode first = new ListNode(-1);
		ListNode pre = first, cur = head, next = head.next;
		while(next != null) {
			pre.next = next;
			cur.next = next.next;
			next.next=cur;

			pre = cur;
			cur = pre.next;
			next = cur == null? null: cur.next;
		}
		return  first.next;
	}

	/**
	 * 删除排序链表中的重复元素 II
	 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = new ListNode(Integer.MIN_VALUE);
		newHead.next = head;
		ListNode cur=head, pre = newHead;
		while(cur != null) {
			boolean flag = false;
			while (cur.next!= null && cur.val == cur.next.val) {
				flag = true;
				cur = cur.next;
			}
			if (flag) {
				cur = cur.next;
				continue;
			}
			pre.next = cur;
			pre = pre.next;
			cur = cur.next;
		}
		// 后面都不符合要设置为null
		pre.next = null;
		return newHead.next;
	}

	/**
	 * 删除排序链表中的重复元素
	 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode cur=head, p = head.next;
		while(p != null) {
			if (cur.val != p.val) {
				cur.next = p;
				cur = p;
			}
			p = p.next;
		}
		cur.next = null;
		return head;
	}

	/**
	 * 分隔链表
	 * https://leetcode.cn/problems/partition-list/
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode less = null, big = null;
		ListNode l = null, b = null;
		while (head != null) {
			if (head.val < x) {
				if (less == null) {
					less = l = head;
				} else {
					l.next = head;
					l = l.next;
				}
			} else {
				if (big == null ) {
					big = b = head;
				}else {
					b.next = head;
					b = b.next;
				}
			}
			head = head.next;
		}
		if (b != null) {
			b.next = null;
		}
		if (l != null) {
			l.next = big;
		} else {
			return big;
		}
		return less;
	}


	/**
	 *  两数相加
	 *  https://leetcode.cn/problems/add-two-numbers/
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode newList = new ListNode(0);
		ListNode h = newList;
		while (l1 != null || l2 != null) {
			if (h.next != null) {
				h = h.next;
			}
			int cur = 0;
			if (l1 == null) {
				cur = l2.val + h.val;
				l2 = l2.next;
			} else if (l2 == null) {
				cur = l1.val + h.val;
				l1 = l1.next;
			} else {
				cur = l1.val + l2.val + h.val;
				l1 = l1.next;
				l2 = l2.next;
			}
			h.val = cur % 10;
			h.next = new ListNode(cur / 10);

		}
		if (h.next.val == 0) {
			h.next = null;
		}
		return newList;
	}

}
