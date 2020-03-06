package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0007Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 7.用两个栈实现队列");
        ch0007 ch0007 = new ch0007();
        ch0007.push(3);
        ch0007.push(5);
        ch0007.push(1);
        ch0007.push(7);
        ch0007.push(2);
        System.out.println(">>> push: 3, 5, 1, 7, 2");
        try {
            System.out.println(">>> pop1: " + ch0007.pop());
            System.out.println(">>> pop2: " + ch0007.pop());
            System.out.println(">>> pop3: " + ch0007.pop());
            System.out.println(">>> pop4: " + ch0007.pop());
            System.out.println(">>> pop5: " + ch0007.pop());
            System.out.println(">>> pop6: " + ch0007.pop());
        } catch (Exception e) {
            System.out.println(">>> pop exception: " + e.getMessage());
        }
        System.out.println("====================================================");
    }

}
