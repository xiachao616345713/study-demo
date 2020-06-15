package study.leetcode;

/**
 * 找到最小缺失正数
 *
 * @author xiachao
 * @date 2020/06/08
 */
public class Solution41 {

    public static void main(String[] args) {
//        System.out.println(0x7fffffff);
//        // 28 + 3 = 31位
        //System.out.println(Integer.MAX_VALUE / 63);

        //int[] nums = {10,4,16,54,17,-7,21,15,25,31,61,1,6,12,21,46,16,56,54,12,23,20,38,63,2,27,35,11,13,47,13,11,61,39,0,14,42,8,16,54,50,12,-10,43,11,-1,24,38,-10,13,60,0,44,11,50,33,48,20,31,-4,2,54,-6,51,6};

        int[] nums = {99,94,96,11,92,5,91,89,57,85,66,63,84,81,79,61,74,78,77,30,64,13,58,18,70,69,51,12,32,34,9,43,39,8,1,38,49,27,21,45,47,44,53,52,48,19,50,59,3,40,31,82,23,56,37,41,16,28,22,33,65,42,54,20,29,25,10,26,4,60,67,83,62,71,24,35,72,55,75,0,2,46,15,80,6,36,14,73,76,86,88,7,17,87,68,90,95,93,97,98};

        System.out.println(new Solution41().firstMissingPositive(nums));
    }

    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        int[] bitmap = new int[length / 31 + 1];
        // init
        bitmap[0] = 1;

        for (int num : nums) {
            if (num <= 0 || num > length) {
                continue;
            }
            int index = num / 31;
            int mod = num - index * 31;
            bitmap[index] |= (1 << mod);
        }

        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < 31; j++) {
                if ((bitmap[i] & (1 << j)) == 0) {
                    return i * 31 + j;
                }
            }
        }
        return -1;
    }

}
