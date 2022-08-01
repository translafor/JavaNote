package interview;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-09
 */
public class Leetcode2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 上一步计算后的进位
        int carry = 0;
        ListNode a = null;
        ListNode b = null;
        while (null != l1 || null != l2) {
            int value1 = (null == l1) ? 0 : l1.val;
            int value2 = (null == l2) ? 0 : l2.val;
            // 本次计算的值
            int temp = value1 + value2 + carry;
            // 如果是第一次，让b保留初始指针
            if (null == a) {
                a = new ListNode(temp % 10, null);
                b = a;
            } else {
                // 不是第一次，就让a一直往后递归
                a.next = new ListNode(temp % 10, null);
                a = a.next;
            }
            carry = temp / 10;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }
        // 不要忘了最后的进位
        if (carry != 0) {
            a.next = new ListNode(carry, null);
        }
        return b;
    }
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}



