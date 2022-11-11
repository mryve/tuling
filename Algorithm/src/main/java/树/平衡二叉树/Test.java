package 树.平衡二叉树;

import 树.TreeNode;

public class Test {
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root) != -1;

    }

    public int check(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = check(root.left);
        int right = check(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return  -1;
        }
        return Math.max(left, right) + 1;
    }
}
