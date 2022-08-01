package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-16
 */
public class Leetcode22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
//        allParenthesis(new char[2 * n], res, 0, 0, 0);
        back(n*2,res,new StringBuilder(),0,0);
        return res;
    }

    private void back(int length, List<String> res, StringBuilder str, int leftNum, int rightNum) {
        if (str.length() == length) {
            res.add(str.toString());
            return;
        }
        // 只有在左括号大于右括号的情况下 才能添加右括号
        if(leftNum>rightNum){
            back(length,res,str.append(')'),leftNum,rightNum+1);
            // 完成上面的递归后，回溯删除一个节点，给其他选择机会；比如刚刚添加了一个(,组成了一个组合，然后删掉这个节点，走下面的添加(逻辑
            str.deleteCharAt(str.length()-1);
        }
        // 如果没超过一半
        if(leftNum<length/2){
            back(length,res,str.append('('),leftNum+1,rightNum);
            // 逻辑同上，即便这里是(,因为可能是中间过程的回溯
            str.deleteCharAt(str.length()-1);
        }
    }

    private void allParenthesis(char[] chars, List<String> res, int index, int leftNum, int rightNum) {
        int length = chars.length;
        if (leftNum + rightNum == length) {
            if (isValid(chars)) {
                res.add(new String(chars));
            }
        }
        if (leftNum < length / 2) {
            chars[index] = '(';
            allParenthesis(chars, res, index + 1, leftNum + 1, rightNum);
        }
        if (rightNum < length / 2) {
            chars[index] = ')';
            allParenthesis(chars, res, index + 1, leftNum, rightNum + 1);
        }
    }

    private boolean isValid(char[] chars) {
        int valid = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                valid++;
            } else {
                valid--;
            }
            if (valid < 0) {
                return false;
            }
        }
        return true;
    }
}
