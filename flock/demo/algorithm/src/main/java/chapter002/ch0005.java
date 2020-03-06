package chapter002;

import sun.misc.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重建二叉树
 * @author Chunming_Wang
 */
public class ch0005 {

    /**
     * 前序遍历: 根结点 ---> 左子树 ---> 右子树
     */
    public void preTraversal(TreeNode node, List<Integer> treeList) {
        if(node != null) {
            treeList.add(node.val);
            preTraversal(node.left, treeList);
            preTraversal(node.right, treeList);
        }
    }

    /**
     * 中序遍历: 左子树---> 根结点 ---> 右子树
     */
    public void midTraversal(TreeNode node, List<Integer> treeList) {
        if(node != null) {
            midTraversal(node.left, treeList);
            treeList.add(node.val);
            midTraversal(node.right, treeList);
        }
    }

    /**
     * 后序遍历: 左子树 ---> 右子树 ---> 根结点
     */
    public void endTraversal(TreeNode node, List<Integer> treeList) {
        if(node != null) {
            endTraversal(node.left, treeList);
            endTraversal(node.right, treeList);
            treeList.add(node.val);
        }
    }

    /**
     * 层次遍历: 只需按层次遍历即可
     * 思路：根据层次遍历的顺序，每一层都是从左到右的遍历输出，借助于一个队列。
     *       先从根节点入队，将其出队访问，如果当前节点的左节点不为空左节点入队，如果当前右节点部位空右节点入队。
     *       所以出队顺序是从左到右。
     */
    public void levelTraversal(TreeNode node, List<Integer> treeList) throws InterruptedException {
        if(node != null) {
            Queue<TreeNode> queue = new Queue<>();
            queue.enqueue(node);

            TreeNode currentNode = null;
            while (!queue.isEmpty()) {
                currentNode = queue.dequeue();
                treeList.add(currentNode.val);
                if(currentNode.left != null) {
                    queue.enqueue(currentNode.left);
                }
                if(currentNode.right != null) {
                    queue.enqueue(currentNode.right);
                }
            }
        }
    }

    /**
     * 根据二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 思路：根据前序遍历找到根，根据中序遍历找到左右子树，依次递归。
     * 归结：根 > 左 > 右
     */
    public TreeNode reConstructBinaryTree(List<Integer> preTree, List<Integer> midTree) {
        if(preTree == null || preTree.size() == 0 || midTree == null || preTree.size() == 0) {
            return null;
        }
        //根节点
        int rootNode = preTree.get(0);
        if(!midTree.contains(rootNode)) {
            return null;
        }
        //移除根节点
        preTree.remove(0);
        TreeNode treeNode = new TreeNode(rootNode);

        //左右子树
        List<Integer> leftTree = null;
        List<Integer> tempList = new ArrayList<>();
        boolean isTree = false;
        for(Integer item : midTree) {
            tempList.add(item);
            if(item == rootNode) {
                isTree = true;
                tempList.remove(item);
                leftTree = tempList;

                tempList = new ArrayList<>();
            }
        }
        if(!isTree) {
            System.out.println("不是正确的树");
            return null;
        }

        List<Integer> rightTree = tempList;

        //递归左右节点
        treeNode.left = reConstructBinaryTree(preTree, leftTree);
        treeNode.right = reConstructBinaryTree(preTree, rightTree);
        return treeNode;
    }

    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        for (int i = 0; i < in.length; i++) {
            indexForInOrders.put(in[i], i);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if(preL > preR) return null;
        TreeNode root = new TreeNode(pre[preL]);
        int inIndex = indexForInOrders.get(root.val);
        int leftTreeSize = inIndex - inL;
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

}

/**
 * 二叉树
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
