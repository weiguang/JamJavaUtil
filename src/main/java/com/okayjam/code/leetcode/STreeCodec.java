package com.okayjam.code.leetcode;


import com.okayjam.code.leetcode.Tree.TreeNode;
/**
 * com.okayjam.code.leetcode
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/08/05 09:00
 **/
public class STreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        return "[" + serializeDfs(root) + "]";
    }

    private String serializeDfs(TreeNode root) {
        if(root == null) return "";
        String left = serializeDfs(root.left);
        String right = serializeDfs(root.right);
        return String.join(",", String.valueOf(root.val), left, right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty() || data.equals("[]")) return null;
        String substring = data.substring(1, data.length() - 1);
        TreeNode root = null;
        for (String val : substring.split(",")) {
            if (val.isEmpty()) continue;
            root = deserialize(root, Integer.parseInt(val));
        }
        return root;
    }

    private TreeNode deserialize(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if (val <= root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                deserialize(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                deserialize(root.right, val);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        STreeCodec deser = new STreeCodec();
//         String tree = ser.serialize(root);
         TreeNode ans = deser.deserialize("[2,1,3]");
    }
}
