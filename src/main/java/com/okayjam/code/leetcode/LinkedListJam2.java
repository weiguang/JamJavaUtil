package com.okayjam.code.leetcode;

/**
 * @author chen2
 */

public class LinkedListJam2 {

	public Node flatten(Node head) {
		if (head == null) {return null;}
		flattenSub(head);
		return head;
	}

	Node flattenSub(Node head) {
		Node cur = head;
		while(cur != null) {
			if (cur.child != null )  {
				Node tail = flattenSub(cur.child);
				tail.next = cur.next;
				if (cur.next != null) {
					cur.next.prev = tail;
				}
				cur.next = cur.child;
				cur.child.prev = cur;
				cur.child = null;
				cur = tail;
			}
			if (cur.next == null) {	break;}
			cur = cur.next;
		}
		return cur;
	}




	static class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;
	};



}


