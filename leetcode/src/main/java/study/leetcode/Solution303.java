package study.leetcode;

/**
 * @author xiachao
 * @date 2020/04/18
 */
public class Solution303 {

    private int[] nums;

    public Solution303(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int ret = 0;
        for (; i <= j; i++) {
            ret += nums[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        System.out.println(new Solution303(nums).sumRange(0, 2));
    }
}
