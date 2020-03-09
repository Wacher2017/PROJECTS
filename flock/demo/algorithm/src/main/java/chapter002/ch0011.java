package chapter002;

import java.util.Arrays;

/**
 * 变态跳台阶
 *
 * @author Chunming_Wang
 */
public class ch0011 {

    public int jumpFloorI(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp, 1);
        for (int i = 1; i < target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target - 1];
    }

    public int jumpFloorII(int target) {
        return (int) Math.pow(2, target - 1);
    }

}
