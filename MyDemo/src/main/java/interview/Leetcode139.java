package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-12
 */
public class Leetcode139 {
    /**
     * 动态规划
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        char[] chars = s.toCharArray();
        // 代表（0-i）的字符串是否可以单词拆分
        boolean[] dp = new boolean[s.length()];
        // dp[0]就是看看第一个字母是否在字典集里面
        dp[0] = wordDict.contains(String.valueOf(chars[0]));
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
        }
        // 从序号1开始规划
        for (int i = 1; i <chars.length; i++) {
            // 对于dp[i]，看看以字典里面的任何一个字符串为结尾时，是否满足
            for (int j = 0; j < wordDict.size(); j++) {
                String s1 = wordDict.get(j);
                // 如果总长度<字典集字符串的长度，肯定不符合
                if(s1.length()>(i+1)){
                    continue;
                }
                // 匹配下是否可以用该字典集的字符串结尾
                int k = doit(i,s1,s);
                // 刚好就是这个字符串，直接为true
                if(k==0){
                    dp[i] = true;
                    break;
                }
                // 不能，直接看下一个
                if(k==-1){
                    continue;
                }
                // 如果可以，并且去掉这个字符串剩下的还可以单词拆分；则只为true
                if(dp[k-1]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()-1];
    }

    private int doit(int i, String s1, String source) {
        String substring = source.substring(i - s1.length() + 1, i+1);
        if(s1.equals(substring)){
            return i - s1.length() + 1;
        }
        return -1;
    }
}
