package chapter002;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 * @author Chunming_Wang
 */
public class ch0004 {

    /**使用递归*/
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        if(listNode != null) {
            result.addAll(printListFromTailToHead(listNode.next));
            result.add(listNode.val);
        }
        return result;
    }

    /**使用栈*/
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    /**使用头插法*/
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        // 头插法构建逆序链表
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            ListNode memo = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = memo;
        }
        // 构建 ArrayList
        ArrayList<Integer> result = new ArrayList<>();
        head = head.next;
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        return result;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
