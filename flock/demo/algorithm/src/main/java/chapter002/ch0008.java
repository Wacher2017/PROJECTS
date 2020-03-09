package chapter002;

/**
 * 斐波那契数列
 * @author Chunming_Wang
 */
public class ch0008 {

    //递归方式：会重复计算一些子问题
    public int fibonacciRecursion (int n) {
        if(n <= 1) {
            return n;
        }
        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    //动态规划：把子问题的解缓存起来，避免重复求解问题
    public int fibonacciDynamicPlan(int n) {
        if(n <= 1) {
            return n;
        }
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    //考虑到第 i 项只与第 i-1 和第 i-2 项有关，因此只需要存储前两项的值就能求解第 i 项
    //从而将空间复杂度由 O(N) 降低为 O(1)。
    public int fibonacciDynamicPlanning(int n) {
        if(n <= 1) {
            return n;
        }
        int pre2 = 0, pre1 = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = pre2 + pre1;
            pre2 = pre1;
            pre1 = fib;
        }
        return fib;
    }

    //由于待求解的 n 小于 40，因此可以将前 40 项的结果先进行计算，之后就能以 O(1) 时间复杂度得到第 n 项的值
    private int[] result = new int[40];

    public ch0008() {
        result[1] = 1;
        for (int i = 2; i < result.length; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
    }

    public int fibonacciDynamicPlaned(int n) {
        return result[n];
    }

}
