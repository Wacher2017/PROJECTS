package chapter002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chunming_Wang
 */
public class ch0005Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 5.重建二叉树");
        ch0005 ch0005 = new ch0005();
        System.out.println(">>> 普通二叉树");
        System.out.println("            1       ");
        System.out.println("        /      \\   ");
        System.out.println("       2        3   ");
        System.out.println("      /       /   \\");
        System.out.println("     4       5     6");
        System.out.println("      \\          / ");
        System.out.println("        7        8  ");
        //构建普通二叉树
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        TreeNode leftL = new TreeNode(4);
        left.left = leftL;
        TreeNode rightL = new TreeNode(5);
        TreeNode rightR = new TreeNode(6);
        right.left = rightL;
        right.right = rightR;
        leftL.right = new TreeNode(7);
        rightR.left = new TreeNode(8);

        List<Integer> preTreeList = new ArrayList<>();
        ch0005.preTraversal(root, preTreeList);
        System.out.println(">>> 前序遍历，Output: " + preTreeList);
        List<Integer> minTreeList = new ArrayList<>();
        ch0005.midTraversal(root, minTreeList);
        System.out.println(">>> 中序遍历，Output: " + minTreeList);
        List<Integer> endTreeList = new ArrayList<>();
        ch0005.endTraversal(root, endTreeList);
        System.out.println(">>> 后序遍历，Output: " + endTreeList);
        List<Integer> levelTreeList = new ArrayList<>();
        try {
            ch0005.levelTraversal(root, levelTreeList);
            System.out.println(">>> 层次遍历，Output: " + levelTreeList);
        } catch (InterruptedException e) {
            System.out.println(">>> 层次遍历中断移异常：" + e.getMessage());
        }
        System.out.println();
        System.out.println(">>> 根据前序遍历和中序遍历的结果，重建出该二叉树");
        System.out.println(">>> 实现方法一, Output: ");
        int[] pre = preTreeList.stream().mapToInt(Integer::valueOf).toArray();
        int[] mid = minTreeList.stream().mapToInt(Integer::valueOf).toArray();
        TreeNode treeNode1 = ch0005.reConstructBinaryTree(pre, mid);
        print(treeNode1);
        System.out.println();
        System.out.println(">>> 实现方法二, Output: ");
        TreeNode treeNode2 = ch0005.reConstructBinaryTree(preTreeList, minTreeList);
        print(treeNode2);
        System.out.println("====================================================");
    }

    private static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    private static void print(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

}
