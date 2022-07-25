package com.okayjam.shezhao.tx;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);

    }

    Node findNode(Node l1, Node l2) {
        // check
        if (l1 == null || l2 == null) {
            return null;
        }
        // hashmap
        HashMap<Node, Object> nodeObjectHashMap = new HashMap<>();
        Node curNode = l1;
        while (curNode != null) {
            nodeObjectHashMap.put(curNode, "");
            curNode = curNode.next;
        }
        // iter search
        curNode = l2;
        while (curNode != null) {
            Object o = nodeObjectHashMap.get(curNode);
            if (o != null) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }


    class Node {
        int data;
        Node next;
    }


}