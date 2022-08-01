package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-07
 */
public class Leetcode5 {
    public String longestPalindrome(String s) {
        if (null == s || s.length() < 2) {
            return s;
        }
        int left = 0;
        // 长度至少为1
        int max = 1;
        int len = s.length();
        boolean dp[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            // 长度为1时都是回文串
            dp[i][i] = true;
        }
        // 遍历长度：从长度短的往长的演进
        for (int i = 2; i <= len; i++) {
            //遍历对应长度下的左指针
            for (int j = 0; j < len; j++) {
                int right = j + i - 1;
                if (right >= len) {
                    // 越界
                    continue;
                }
                //保证至少是(2，4)的距离
                boolean temp = s.charAt(j) == s.charAt(right);
                if (right - j < 2) {
                    dp[j][right] = temp;
                } else {
                    dp[j][right] = dp[j + 1][right - 1] && temp;
                }
                if (dp[j][right] && right - j + 1 > max) {
                    max = right - j + 1;
                    left = j;
                }
            }
        }
        return s.substring(left, left + max);
    }
}
