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

    static TreeNode creatTreeNode (int i, int ... vals) {
        
        if (i < vals.length) {
            return new TreeNode(vals[i], creatTreeNode(2*i+1, vals), creatTreeNode(2*i+2, vals));
        }
        return null;
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
        TreeNode root1 = TreeNode.creatTreeNode(0,1,2,3);
        TreeNode root2 = TreeNode.creatTreeNode(0, 1,3,2);
        System.out.println(TreeNode.leafSimilar(root1, root2));
    }
}
