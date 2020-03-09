# 剑指 Offer 题解
## 目录
### 1. 数组中重复的数字

>View Code: ch0001

**题目描述**

在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
请找出数组中任意一个重复的数字。
```
Input:
{2, 3, 1, 0, 2, 5}
Output:
2
```
**解题思路**

要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。

对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
本题要求找出重复的数字，因此在调整过程中，如果第 i 位置上已经有一个值为 i 的元素，就可以知道 i 值重复。

以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可知道 2 重复。

```java
public boolean duplicate(int[] nums, int length, int[] duplication) {
    if (nums == null || length <= 0)
        return false;
    for (int i = 0; i < length; i++) {
        while (nums[i] != i) {
            if (nums[i] == nums[nums[i]]) {
                duplication[0] = nums[i];
                return true;
            }
            swap(nums, i, nums[i]);
        }
    }
    return false;
}

private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```
### 2. 二维数组中查找

>View Code: ch0002

**题目描述**

给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。
给定一个数，判断这个数是否在该二维数组中。

```
Consider the following matrix:
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]

Given target = 5, return true.
Given target = 20, return false.
```
**解题思路**

要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。

该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。
因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。

```java
public boolean Find(int target, int[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
        return false;
    int rows = matrix.length, cols = matrix[0].length;
    int r = 0, c = cols - 1; // 从右上角开始
    while (r <= rows - 1 && c >= 0) {
        if (target == matrix[r][c])
            return true;
        else if (target > matrix[r][c])
            r++;
        else
            c--;
    }
    return false;
}
```
### 3. 替换空格

>View Code: ch0003

**题目描述**

将一个字符串中的空格替换成 "%20"。

```
Input:
"A B"

Output:
"A%20B"
```
**解题思路**

① 在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。
因为一个空格要替换成三个字符（%20），所以当遍历到一个空格时，需要在尾部填充两个任意字符。

② 令 P1 指向字符串原来的末尾位置，P2 指向字符串现在的末尾位置。
P1 和 P2 从后向前遍历，当 P1 遍历到一个空格时，就需要令 P2 指向的位置依次填充 02%（注意是逆序的），否则就填充上 P1 指向字符的值。
从后向前遍是为了在改变 P2 所指向的内容时，不会影响到 P1 遍历原来字符串的内容。

③ 当 P2 遇到 P1 时（P2 <= P1），或者遍历结束（P1 < 0），退出。

```java
public String replaceSpace(StringBuffer str) {
    int P1 = str.length() - 1;
    for (int i = 0; i <= P1; i++)
        if (str.charAt(i) == ' ')
            str.append("  ");

    int P2 = str.length() - 1;
    while (P1 >= 0 && P2 > P1) {
        char c = str.charAt(P1--);
        if (c == ' ') {
            str.setCharAt(P2--, '0');
            str.setCharAt(P2--, '2');
            str.setCharAt(P2--, '%');
        } else {
            str.setCharAt(P2--, c);
        }
    }
    return str.toString();
}
```
### 4. 从尾到头打印链表

>View Code: ch0004

**题目描述**

从尾到头反过来打印出每个结点的值。
输入一个链表，按链表从尾到头的顺序返回一个ArrayList。

结点定义：

```java
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
```
**解题思路**

结点遍历顺序只能从头到尾，但是输出的顺序却为从尾到头，是典型的“后进先出”问题。
这就要联想到使用栈，从而也可以联想到使用递归。

1.使用递归

要逆序打印链表 1->2->3（3,2,1)，可以先逆序打印链表 2->3(3,2)，最后再打印第一个节点 1。
而链表 2->3 可以看成一个新的链表，要逆序打印该链表可以继续使用求解函数，也就是在求解函数中调用自己，这就是递归函数。

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    ArrayList<Integer> ret = new ArrayList<>();
    if (listNode != null) {
        ret.addAll(printListFromTailToHead(listNode.next));
        ret.add(listNode.val);
    }
    return ret;
}
```
2.使用头插法

头插法顾名思义是将节点插入到头部：在遍历原始链表时，将当前节点插入新链表的头部，使其成为第一个节点。

链表的操作需要维护后继关系，例如在某个节点 node1 之后插入一个节点 node2，我们可以通过修改后继关系来实现：

```
node3 = node1.next;
node2.next = node3;
node1.next = node2;
```
为了能将一个节点插入头部，我们引入了一个叫头结点的辅助节点，该节点不存储值，只是为了方便进行插入操作。
不要将头结点与第一个节点混起来，第一个节点是链表中第一个真正存储值的节点。

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    // 头插法构建逆序链表
    ListNode head = new ListNode(-1);
    while (listNode != null) {
        ListNode memo = listNode.next;
        listNode.next = head.next;
        head.next = listNode;
        listNode = memo;
    }
    // 构建 ArrayList
    ArrayList<Integer> ret = new ArrayList<>();
    head = head.next;
    while (head != null) {
        ret.add(head.val);
        head = head.next;
    }
    return ret;
}
```
3.使用栈

栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。

```java
public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    Stack<Integer> stack = new Stack<>();
    while (listNode != null) {
        stack.add(listNode.val);
        listNode = listNode.next;
    }
    ArrayList<Integer> ret = new ArrayList<>();
    while (!stack.isEmpty())
        ret.add(stack.pop());
    return ret;
}
```
### 5. 重建二叉树

>View Code: ch0005

**题目描述**

根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

**解题思路**

```
///              1
///           /     \
///          2       3  
///         /       / \
///        4       5   6
///         \         /
///          7       8
```
基础知识

前序遍历：根结点 ---> 左子树 ---> 右子树  
中序遍历：左子树---> 根结点 ---> 右子树  
后序遍历：左子树 ---> 右子树 ---> 根结点  
层次遍历：只需按层次遍历即可

例如

前序遍历：1 2 4 7 3 5 6 8  
中序遍历：4 7 2 1 5 3 8 6  
后序遍历：7 4 2 5 8 6 3 1  
层次遍历：1 2 3 4 5 6 7 8

前序遍历首先访问根结点然后遍历左子树，最后遍历右子树。在遍历左、右子树时，仍然先访问根结点，然后遍历左子树，最后遍历右子树。
中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
二叉树的什么什么遍历，其实也是很好记的，就是根在呢就是什么遍历，在前就是前遍历，中就是中序遍历，后就是后序遍历，其他的是层次遍历。

解题

根据前序遍历，可以知道根节点（1），根据中序遍历可以知道左子树（4,7,2）和右子树（5,3,8,6）。
找到左右子树之后，我们可以以相同的方式找到左右子树，也就是说这是一个递归的过程。根>左>右。

```java
// 缓存中序遍历数组每个值对应的索引
private Map<Integer, Integer> indexForInOrders = new HashMap<>();

public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
    for (int i = 0; i < in.length; i++)
        indexForInOrders.put(in[i], i);
    return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
}

private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
    if (preL > preR)
        return null;
    TreeNode root = new TreeNode(pre[preL]);
    int inIndex = indexForInOrders.get(root.val);
    int leftTreeSize = inIndex - inL;
    root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
    root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
    return root;
}
```
### 6. 二叉树的下一个结点

>View Code: ch0006

**题目描述**

给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回 。
注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。

```java
public class TreeLinkNode {

    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; // 指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}
```

**解题思路**

我们先来回顾一下中序遍历的过程：先遍历树的左子树，再遍历根节点，最后再遍历右子树。
所以最左节点是中序遍历的第一个节点。

```java
void traverse(TreeNode root) {
    if (root == null) return;
    traverse(root.left);
    visit(root);
    traverse(root.right);
}
```
①如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点；     
②否则，向上找第一个左链接指向的树包含该节点的祖先节点。

```java
public TreeLinkNode GetNext(TreeLinkNode pNode) {
    if (pNode.right != null) {
        TreeLinkNode node = pNode.right;
        while (node.left != null)
            node = node.left;
        return node;
    } else {
        while (pNode.next != null) {
            TreeLinkNode parent = pNode.next;
            if (parent.left == pNode)
                return parent;
            pNode = pNode.next;
        }
    }
    return null;
}
```
### 7. 用两个栈实现队列

>View Code: ch0007

**题目描述**

用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。

**解题思路**

in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
一个元素进入 in 栈之后，出栈的顺序被反转。
当元素要出栈时，需要先进入 out 栈，此时元素出栈顺序再一次被反转，
因此出栈顺序就和最开始入栈顺序是相同的，先进入的元素先退出，这就是队列的顺序。

```java
Stack<Integer> in = new Stack<Integer>();
Stack<Integer> out = new Stack<Integer>();

public void push(int node) {
    in.push(node);
}

public int pop() throws Exception {
    if (out.isEmpty())
        while (!in.isEmpty())
            out.push(in.pop());

    if (out.isEmpty())
        throw new Exception("queue is empty");

    return out.pop();
}
```
### 8. 斐波那契数列

>View Code: ch0008

**题目描述**

求斐波那契数列的第 n 项，n <= 39。

f(n) = f(n - 2) + f(n - 1) (n > 1), 当 n = 0 时，f(n) = 0; 当 n = 1 时，f(n) = 1;

**解题思路**

如果使用递归求解，会重复计算一些子问题。
例如，计算 f(4) 需要计算 f(3) 和 f(2)，计算 f(3) 需要计算 f(2) 和 f(1)，可以看到 f(2) 被重复计算了。

递归是将一个问题划分成多个子问题求解，动态规划也是如此，但是动态规划会把子问题的解缓存起来，从而避免重复求解子问题。

```java
public int Fibonacci(int n) {
    if (n <= 1)
        return n;
    int[] fib = new int[n + 1];
    fib[1] = 1;
    for (int i = 2; i <= n; i++)
        fib[i] = fib[i - 1] + fib[i - 2];
    return fib[n];
}
```
考虑到第 i 项只与第 i-1 和第 i-2 项有关，因此只需要存储前两项的值就能求解第 i 项，从而将空间复杂度由 O(N) 降低为 O(1)。

```java
public int Fibonacci(int n) {
    if (n <= 1)
        return n;
    int pre2 = 0, pre1 = 1;
    int fib = 0;
    for (int i = 2; i <= n; i++) {
        fib = pre2 + pre1;
        pre2 = pre1;
        pre1 = fib;
    }
    return fib;
}
```
由于待求解的 n 小于 40，因此可以将前 40 项的结果先进行计算，之后就能以 O(1) 时间复杂度得到第 n 项的值。

```java
public class Solution {

    private int[] fib = new int[40];

    public Solution() {
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }

    public int Fibonacci(int n) {
        return fib[n];
    }
}
```
### 9. 矩形覆盖

>View Code: ch0009

**题目描述**

我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？

f(n) = f(n - 2) + f(n - 1) (n > 1), 当 n = 0 时，f(n) = 0; 当 n = 1 时，f(n) = 1;

**解题思路**

当 n 为 1 时，只有一种覆盖方法;  
当 n 为 2 时，有两种覆盖方法;  
要覆盖 2*n 的大矩形，可以先覆盖 2*1 的矩形，再覆盖 2*(n-1) 的矩形；或者先覆盖 2*2 的矩形，再覆盖 2*(n-2) 的矩形。
而覆盖 2*(n-1) 和 2*(n-2) 的矩形可以看成子问题。  
该问题的递推公式如下:

f(n) = f(n - 1) + f(n - 2) (n > 1), 当 n = 1 时，f(n) = 1; 当 n = 2 时，f(n) = 2;

```java
public int RectCover(int n) {
    if (n <= 2)
        return n;
    int pre2 = 1, pre1 = 2;
    int result = 0;
    for (int i = 3; i <= n; i++) {
        result = pre2 + pre1;
        pre2 = pre1;
        pre1 = result;
    }
    return result;
}
```