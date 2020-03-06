package chapter002;

import java.util.List;

/**
 * 二叉树的下一个结点
 * @author Chunming_Wang
 */
public class ch0006  {

    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if(pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            while (pNode.parent != null) {
                TreeLinkNode parentNode = pNode.parent;
                if(parentNode.left == pNode) {
                    return parentNode;
                }
                pNode = pNode.parent;
            }
        }
        return null;
    }

    /**
     * 中序遍历: 左子树---> 根结点 ---> 右子树
     */
    public void midTraversal(TreeLinkNode node, List<Integer> treeList) {
        if(node != null) {
            midTraversal(node.left, treeList);
            treeList.add(node.val);
            midTraversal(node.right, treeList);
        }
    }

}

class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode parent = null; // 指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}
