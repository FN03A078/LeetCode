package com.LeetCode.com.shan.Tree;

import java.util.*;

/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 10:23 2022/6/24
 */
public class Tree {
    public static void main(String[] args) {
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE );
    }

    /**
     * 367.给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    //递龟
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //DFS
    public int maxDepthDFS(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        int max = 0;
        stack.push(root);
        value.push(1);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            max = Math.max(max, temp);
            if (node.left != null) {
                stack.push(node.left);
                value.push(temp + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                value.push(temp + 1);
            }
        }
        return max;
    }

    //BFS
    public int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int count = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size-- > 0) {
                TreeNode cur = deque.pop();
                if (cur.left != null) deque.addLast(cur.left);
                if (cur.right != null) deque.addLast(cur.right);
            }
            count++;
        }
        return count;
    }

    /**
     * 372.给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 例如，给定如下二叉树: root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4]
     */
    //递归
    public TreeNode lowestCommonAncestor(TreeNode cur, TreeNode p, TreeNode q) {
        if (cur == null || cur == p || cur == q) return cur;
        TreeNode left = lowestCommonAncestor(cur.left, p, q);
        TreeNode right = lowestCommonAncestor(cur.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return cur;
    }

    //非递归
    public TreeNode lowestCommonAncestor2(TreeNode cur, TreeNode p, TreeNode q) {
        //记录遍历到的父节点
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        //根节点没有父节点
        parent.put(cur, null);
        queue.add(cur);
        //直到两个节点都找到为止
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            assert node != null;
            if (node.left != null) {
                parent.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }
        //记录p和其祖先节点
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        //查看p和其祖先节点是否包含q节点，如果不包含再看是否包含q的父节点……
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

    /**
     * 374.二叉树的最小深度
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return level;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return -1;
    }

    /**
     * 375.在每个树行中找最大值
     */
    //BFS
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        return res;
    }

    //DFS
    public List<Integer> largestValuesDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res, 1);
        return res;
    }

    private static void helper(TreeNode root, List<Integer> res, int level) {
        if (root == null) return;
        if (level == res.size() + 1) {
            res.add(root.val);
        } else {
            res.set(level - 1, Math.max(res.get(level - 1), root.val));
        }
        helper(root.left, res, level + 1);
        helper(root.right, res, level + 1);
    }

    /**
     * 387.二叉树中的最大路径和
     * 给定一个非空二叉树，返回其最大路径和。
     * 路径被定义为一条从树中任意节点出发，达到任意节点的序列。
     * 该路径至少包含一个节点，且不一定经过根节点。
     */

    /**
     * 388.先序遍历构造二叉树
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            addTreeNode(root, preorder[i]);
        }
        return root;
    }

    private static void addTreeNode(TreeNode root, int data) {
        TreeNode node = new TreeNode(data);
        TreeNode p = root;
        while (true) {
            if (p.val > data) {
                if (p.left == null) {
                    p.left = node;
                    break;
                } else {
                    p = p.left;
                }
            } else {
                if (p.right == null) {
                    p.right = node;
                    break;
                } else {
                    p = p.right;
                }
            }
        }
    }

    //递归写法
    private static TreeNode addTreeNode2(TreeNode root, int data) {
        if (root == null)
            return new TreeNode(data);
        else if (root.val > data)
            root.left = addTreeNode2(root.left, data);
        else
            root.right = addTreeNode2(root.right, data);
        return root;
    }

    /**
     * 399.根据一棵树的前序遍历与中序遍历构造二叉树。
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> preList = new ArrayList<>();
        List<Integer> inOrderList = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            preList.add(preorder[i]);
            inOrderList.add(inorder[i]);
        }
        return helper(preList, inOrderList);
    }

    private static TreeNode helper(List<Integer> preList, List<Integer> inOrderList) {
        if (preList.size() == 0) return null;
        int rootVal = preList.remove(0);
        TreeNode root = new TreeNode(rootVal);
        int mid = inOrderList.indexOf(rootVal);
        root.left = helper(preList, inOrderList.subList(0, mid));
        root.right = helper(preList, inOrderList.subList(mid, inOrderList.size()));
        return root;
    }

    /**
     * 400.二叉树的锯齿形层次遍历
     */
    //BFS
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    level.add(node.val);
                } else {
                    level.add(0, node.val);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    //DFS
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(root, res, 0);
        return res;
    }

    private static void DFS(TreeNode cur, List<List<Integer>> res, int level) {
        if (cur == null) return;
        if (res.size() <= level) {
            LinkedList<Integer> integers = new LinkedList<>();
            res.add(integers);
        }
        List<Integer> list = res.get(level);
        if (level % 2 == 0) {
            list.add(cur.val);
        } else {
            list.add(0, cur.val);
        }
        DFS(cur.left, res, level + 1);
        DFS(cur.right, res, level + 1);
    }

    /**
     * 401.给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            //左子树最大值
            TreeNode max = findMax(root.left);
            root.val = max.val;
            root.left = deleteNode(root.left, root.val);

            /*  right min
            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = deleteNode(root.right,root.val);*/

        }
        return root;
    }

    private static TreeNode findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 403.验证二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right,root.val, max);
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
