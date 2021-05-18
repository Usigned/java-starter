package exercise;

import java.util.Stack;

public class Solution993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if ((root.val == x) || (root.val == y) || (root == null)) {
            return false;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            
            boolean mark = false;
            Stack<TreeNode> tempStack = new Stack<>();
            TreeNode temp;

            while (!stack.isEmpty()) {
                boolean sameParent = false;
                temp = stack.pop();
                if ((temp.left !=null) && ((temp.left.val == x) || (temp.left.val == y))) {
                    if (mark) {
                        if (sameParent) {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        mark = true;
                        sameParent = true;
                    }
                }

                if ((temp.right !=null) && (temp.right.val == x) || (temp.right.val == y)) {
                    if (mark) {
                        if (sameParent) {
                            return false;
                        }
                        return true;
                    }
                    else {
                        mark = true;
                    }
                }
                tempStack.push(temp.left);
                tempStack.push(temp.right);
            }

            if (mark) {
                return false;
            }
            else {
                while ( !tempStack.isEmpty()) {
                    if (tempStack.pop() != null)
                        stack.push(tempStack.pop());
                }
            }
        }

        return false;
    }
}
