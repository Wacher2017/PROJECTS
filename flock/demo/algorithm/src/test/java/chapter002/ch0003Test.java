package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0003Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 3.替换空格");
        ch0003 ch0003 = new ch0003();
        StringBuffer sb = new StringBuffer("A B C");
        System.out.println(">>> Input: A B C, Output: " + ch0003.replaceSpace(sb));
        System.out.println("====================================================");
    }

}
