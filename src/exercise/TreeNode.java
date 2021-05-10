package exercise;

import java.util.Iterator;
import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static class Iter implements Iterator<TreeNode> {
        TreeNode root;
        Stack<TreeNode> stack;

        Iter (TreeNode root) {
            this.root = root;
            stack = new Stack<TreeNode>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public TreeNode next() {
            while (true) {
                TreeNode curr = stack.pop();
                if (curr.right != null) {
                    stack.push(curr.right);
                }
                if (curr.left != null) {
                    stack.push(curr.left);
                }
                if (curr.right == null && curr.left ==null) {
                    return curr;
                }
            }
        }
    }
    static public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Iter iter1 = new Iter(root1);
        Iter iter2 = new Iter(root2);
        while (iter1.hasNext()) {
            if (!iter2.hasNext() || iter1.next().val != iter2.next().val) {
                return false;
            }
        }
        if (iter2.hasNext()) {
            return false;
        }
        else {
            return true;
        }
    }

    public static void main(String[] args) {
        TreeNode _3 = new TreeNode(3, null, null);
        TreeNode _2 = new TreeNode(2, null, null);
        TreeNode root1 = new TreeNode(1, _2, _3);
        TreeNode root2 = new TreeNode(1, _3, _2);
        System.out.println(TreeNode.leafSimilar(root1, root2));
    }
}
