package com.okayjam.code.leetcode;

import java.util.LinkedList;
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

/**
 * @author chen2
 */
public class LinkedListJam {

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
