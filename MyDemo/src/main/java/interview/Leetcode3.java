package interview;

import java.util.HashMap;
import java.util.Stack;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-06
 */
public class Leetcode3 {
    public int lengthOfLongestSubstring(String s) {
        if(null==s||s.length()==0){
            return 0;
        }
        // 记录最大值
        int max = 0;
        // 记录滑动窗口的左指针，代表移动到了那个位置，后面计算最大值时，需要剪掉移动的距离
        int left = 0;
        // key用来判断是否有重复字串，value用来记录当前字符的最新位置
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                // 更新left的值，为对应重复的值下标+1（代表滑动窗口移动到重复的那个字母后面）
                left = Math.max(left,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            // 计算最大值，即减去滑动窗口移动的距离
            max = Math.max(max,i-left+1);
        }
        return max;
    }
}
