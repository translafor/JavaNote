package interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-12
 */
public class Leetcode17 {
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        Map<Integer, String[]> source = new HashMap<>();
        source.put(2, new String[]{"a", "b", "c"});
        source.put(3, new String[]{"d", "e", "f"});
        source.put(4, new String[]{"g", "h", "i"});
        source.put(5, new String[]{"j", "k", "l"});
        source.put(6, new String[]{"m", "n", "o"});
        source.put(7, new String[]{"p", "q", "r", "s"});
        source.put(8, new String[]{"t", "u", "v"});
        source.put(9, new String[]{"w", "x", "y", "z"});

        List<String> result = new ArrayList<>();
        addResult(0, "", source, digits, result);
        return result;
    }

    private void addResult(int i, String item, Map<Integer, String[]> source, String digits, List<String> result) {
        // 递归完最后一个元素
        if (i == digits.length()) {
            // 这道题没有 22 即重复数字这样的case，有的话要考虑去重
            result.add(item);
            return;
        }
        String[] sourceItem = source.get(digits.charAt(i) - '0');
        for (int j = 0; j < sourceItem.length; j++) {
            // 对当前索引的每一种可能 进行递归
            addResult(i + 1, item + sourceItem[j], source, digits, result);
        }
    }
}
