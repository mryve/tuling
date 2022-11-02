package 树.二叉树的中序遍历;


import 树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */
public class Test {

    /**
     * 思路: 遍历和循环两种方式
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        accessTree(root, list);
        return list;
    }

    public void accessTree(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        accessTree(root.left, list);
        list.add(root.val);
        accessTree(root.right, list);
    }

    //循环方式
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}
