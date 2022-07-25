package com.okayjam.test;

/**
 * @author Chen weiguang chen2621978@gmail.com
 * @date 2022/07/13 14:37
 **/
public class linkedlist {

    public class Node {
        public int val;
        public Node next;

        public Node() {
            this.val = 0;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public Node createNode() {
        // int[] vals = {1, 8, 3, 6, 5, 4, 7, 2};
        int[] vals = {1, 8, 3, 6, 5, 4, 7, 2, 9, -1, 100, -100};
        Node dummyNode = new Node();
        Node head = dummyNode;

        int length = vals.length;
        for (int i=0; i!=length; i++){
            head.next = new Node(vals[i], null);
            head = head.next;
        }
        return dummyNode.next;
    }

    public void printNode(Node node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
        System.out.println();
    }

//    输入: 1->8->3->6->5->4->7->2->NULL
//    输出: 1->2->3->4->5->6->7->8->NULL
    public void run() {
        Node head = createNode();
        printNode(head); // print origin

        Node h1 = null; //偶数
        Node h2 = head, tail2 = head; //奇数
        Node temp = head.next;
        for (int i=2; temp != null; i++) {
            // 偶数处理
            if ((i & 1) == 0) {
                if (h1 == null) {
                    h1 = temp;
                    temp = temp.next;
                    h1.next = null;
                } else  {
                    Node t = temp.next;
                    temp.next = h1;
                    h1 = temp;
                    temp = t;
                }
            } else {
                    tail2.next = temp;
                    tail2 = temp;
                    temp = temp.next;
                    tail2.next = null;

            }

        }

        printNode(h1); // print origin
        printNode(h2); // print origin
        // 合并
        Node newList = null, np = null;
        while (h1 != null || h2 != null)  {
            Node cur = null;
            if (h1 == null) {
                cur = h2;
                h2 = h2.next;
            } else if (h2 == null) {
                cur = h1;
                h1 = h1.next;
            } else {
                if ( h1.val < h2.val) {
                    cur = h1;
                    h1 = h1.next;
                } else {
                    cur = h2;
                    h2 = h2.next;
                }
            }
            if (newList == null) {
                newList = np = cur;
            } else {
                np.next = cur;
                np = cur;
            }
        }
        printNode(newList); // print origin



    }

    public static void main(String[] args) {
        linkedlist l = new linkedlist();
        l.run();
    }

}
