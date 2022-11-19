package 树.二叉树的遍历;


import 树.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 */
public class Test {

    //前序遍历递归方式
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderAccessTree(root, list);
        return list;
    }

    public void inorderAccessTree(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderAccessTree(root.left, list);
        list.add(root.val);
        inorderAccessTree(root.right, list);
    }

    //前序遍历循环方式
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

    //中序遍历递归方式
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderAccessTree(root, list);
        return list;
    }

    public void preorderAccessTree(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorderAccessTree(root.left, list);
        preorderAccessTree(root.right, list);
    }

    //中序遍历循环方式
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return list;
    }



    //后序遍历递归方式
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderAccessTree(root, list);
        return list;
    }

    public void postorderAccessTree(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorderAccessTree(root.left, list);
        postorderAccessTree(root.right, list);
        list.add(root.val);
    }

    //后序遍历循环方式
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre) {
                list.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }

}
