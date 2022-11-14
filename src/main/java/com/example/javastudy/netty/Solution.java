package com.example.javastudy.netty;

class TreeNode{
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}
public class Solution {

    public void Mirror(TreeNode root){
        if (root == null) return;
        change(root);
    }

    private TreeNode change(TreeNode root) {
        if(null == root) return null;
        TreeNode left = change(root.left);
        TreeNode right = change(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

}
