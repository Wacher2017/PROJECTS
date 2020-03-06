package chapter002;

/**
 * @author Chunming_Wang
 */
public class ch0002Test {

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(">>> 2.二维数组中的查找");
        ch0002 ch0002 = new ch0002();
        int matrix[][] = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(">>> [[1, 4, 7, 11, 15]\n     [2, 5, 8, 12, 19]\n     [3, 6, 9, 16, 22]\n" +
                "     [10, 13, 14, 17, 24]\n     [18, 21, 23, 26, 30]]");
        System.out.println(">>> Target: 5, Output: " + ch0002.find(5, matrix));
        System.out.println(">>> Target: 20, Output: " + ch0002.find(20, matrix));
        System.out.println("====================================================");
    }

}
