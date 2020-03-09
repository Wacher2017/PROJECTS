package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0008Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 8.斐波那契数列");
        ch0008 ch0008 = new ch0008();
        System.out.println(">>> 递归方式，    Input: 15, Output: " + ch0008.fibonacciRecursion(15));
        System.out.println(">>> 动态规划方式，Input: 15, Output: " + ch0008.fibonacciDynamicPlan(15));
        System.out.println(">>> 动态规划方式,只缓存前两个数，Input: 15, Output: " + ch0008.fibonacciDynamicPlanning(15));
        System.out.println(">>> 动态规划方式，预先计算前40项并缓存，Input: 15, Output: " + ch0008.fibonacciDynamicPlaned(15));
        System.out.println("====================================================");
    }

}
