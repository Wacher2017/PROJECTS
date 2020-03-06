package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0004Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 4.从尾到头打印链表");
        ch0004 ch0004 = new ch0004();
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        node.next = node1;
        node1.next = node2;
        System.out.println(">>> Input listNode: 1 -> 2 -> 3 ");
        System.out.println(">>> 使用递归, Output: " + ch0004.printListFromTailToHead(node));
        System.out.println(">>> 使用栈, Output: " + ch0004.printListFromTailToHead1(node));
        System.out.println(">>> 使用头插法, Output: " + ch0004.printListFromTailToHead2(node));
        System.out.println("====================================================");
    }

}
