package study.leetcode;

/**
 * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/">Remove Duplicates from Sorted Array
 * II</a>
 *
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the
 * new length.
 * <p>
 * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra
 * memory.
 * </p>
 *
 * @author xiachao
 * @date 2021/02/01
 */
public class Solution80 {

    /**
     * most twice duplicated
     *
     * @param nums sorted array
     * @return length
     */
    public int removeDuplicates(int[] nums) {
        boolean repeated = false;
        int index = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != num) {
                num = nums[i];
                nums[index++] = num;
                repeated = false;
            } else if (!repeated) {
                repeated = true;
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    public static void main(String[] args) {
        //int[] nums = new int[] {1,1,1,2,2,3};
        int[] nums = new int[] {0,0,1,1,1,1,2,3,3};
        int length = new Solution80().removeDuplicates(nums);

        for (int i = 0; i < length; i++) {
            System.out.print(nums[i]);
        }
        System.out.println();
    }

}
