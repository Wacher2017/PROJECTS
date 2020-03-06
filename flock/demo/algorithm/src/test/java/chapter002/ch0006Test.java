package chapter002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chunming_Wang
 */
public class ch0006Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 6.二叉树的下一个结点");
        ch0006 ch0006 = new ch0006();
        System.out.println(">>> 普通二叉树");
        System.out.println("            1       ");
        System.out.println("        /      \\   ");
        System.out.println("       2        3   ");
        System.out.println("      /       /   \\");
        System.out.println("     4       5     6");
        System.out.println("      \\          / ");
        System.out.println("        7        8  ");
        //构建普通二叉树
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode left = new TreeLinkNode(2);
        TreeLinkNode right = new TreeLinkNode(3);
        root.left = left;
        root.right = right;
        TreeLinkNode leftL = new TreeLinkNode(4);
        left.left = leftL;
        left.parent = root;
        right.parent = root;
        TreeLinkNode rightL = new TreeLinkNode(5);
        TreeLinkNode rightR = new TreeLinkNode(6);
        right.left = rightL;
        right.right = rightR;
        rightL.parent = right;
        rightR.parent = right;
        leftL.parent = left;
        leftL.right = new TreeLinkNode(7);
        leftL.right.parent = leftL;
        rightR.left = new TreeLinkNode(8);
        rightR.left.parent = rightR;

        List<Integer> minTreeList = new ArrayList<>();
        ch0006.midTraversal(root, minTreeList);
        System.out.println(">>> 中序遍历，Output: " + minTreeList);
        TreeLinkNode node = ch0006.getNext(right);
        System.out.println(">>> 节点3按中序遍历顺序的下一个结点值为：" + node.val);
        System.out.println("====================================================");
    }

}
