package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-24
 */
public class Leetcode48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] temp = new int[n][n];
        // 对于矩阵中的第一行而言，在旋转后，它出现在倒数第一列的位置：
        // 并且，第一行的第 x 个元素在旋转后恰好是倒数第一列的第 x 个元素。
        // 文字无法理解，建议直接看官方答案的图
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }
}
