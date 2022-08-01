package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-07-07
 */
public class Leetcode136 {

    /**
     * 利用异或运算：如果a、b两个值不相同，则异或结果为1。如果a、b两个值相同，异或结果为0。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res=nums[0];
        for (int i = 1; i < nums.length; i++) {
            res=res^nums[i];
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(3^4^4^8^3^8^9);
    }
}
