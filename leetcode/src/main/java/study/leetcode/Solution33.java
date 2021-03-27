package study.leetcode;

/**
 * @author xiachao
 * @date 2021/03/27
 */
public class Solution33 {

    /**
     * 3,4,5,6,7,0,1,2
     */
    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (high >= low) {
            int middle = low + (high - low) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            // middle-high无序
            if (nums[middle] > nums[high]) {
                if (target > nums[middle] || target <= nums[high]) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            } else {
                // middle-high有序
                if (target > nums[middle] && target <= nums[high]) {
                    low = middle + 1;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3,1};
        int target = 1;
        System.out.println(search(nums, target));
    }
}
