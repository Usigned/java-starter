package exercise;

import java.util.*;

public class Solution863 {
    Map<Integer, TreeNode> parents = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        findParents(root);
        findNeighbors(target, null, 0, k);
        return this.res;
    }

    private void findNeighbors(TreeNode cur, TreeNode from, int i, int k) {
        if (cur == null)
            return;
        if (i == k) {
            res.add(cur.val);
            return;
        }
        if (cur.left != from)
            findNeighbors(cur.left, cur, i+1, k);
        if (cur.right != from)
            findNeighbors(cur.right, cur, i+1, k);
        if (parents.get(cur.val) != from)
            findNeighbors(parents.get(cur.val), cur, i+1, k);
    }

    private void findParents(TreeNode root) {
        if (root.left != null) {
            parents.put(root.left.val, root);
            findParents(root.left);
        }
        if (root.right != null) {
            parents.put(root.right.val, root);
            findParents(root.right);
        }
    }
}
