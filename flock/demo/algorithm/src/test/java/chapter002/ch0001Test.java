package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0001Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 1.数组中重复的数字");
        ch0001 ch0001 = new ch0001();
        int[] nums = {2, 3, 1, 0, 2, 5};
        int[] duplication = new int[1];
        boolean rs = ch0001.duplicate(nums, nums.length, duplication);
        System.out.println(">>> 数组: {2, 3, 1, 0, 2, 5}");
        System.out.println(">>> 是否有重复: " + rs);
        System.out.println(">>> Output: " + duplication[0]);
        System.out.println("====================================================");
    }

}
