package exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution671 {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        int first = root.val;
        int second = -1;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            if (temp.val != first && (temp.val < second || second == -1))
                second = temp.val;
            else {
                if (temp.left != null)
                    stack.add(temp.left);
                if (temp.right != null)
                    stack.add(temp.right);
            }
        }
        return second;
    }
}
