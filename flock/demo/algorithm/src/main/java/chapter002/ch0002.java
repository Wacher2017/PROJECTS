package chapter002;

/**
 * 二维数组中的查找
 * @author Chunming_Wang
 */
public class ch0002 {

    public boolean find(int target, int [][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1; //从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if(target == matrix[r][c]) {
                return true;
            } else if(target > matrix[r][c]) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }

}
