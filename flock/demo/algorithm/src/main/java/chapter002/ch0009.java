package chapter002;

/**
 * 矩形覆盖
 * @author Chunming_Wang
 */
public class ch0009 {

    public int rectCover(int n) {
        if(n <= 2) {
            return n;
        }
        int pre2 = 1, pre1 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre2 + pre1;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

}
