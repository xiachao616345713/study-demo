package study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/permutations-ii/">47. Permutations II</a>
 * <p>
 *     Given a collection of numbers that might contain duplicates,
 *     return all possible unique permutations.
 * </p>
 *
 * 相关题
 * <a href="https://leetcode.com/problems/permutations/">Permutations</a>
 * <p>
 *     Given a collection of distinct integers, return all possible permutations.
 * </p>
 *
 * @author chao
 * @since 2019-05-07
 */
public class Solution47 {

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        List<List<Integer>> list = new Solution47().permuteUnique(nums);
        list.forEach(item-> System.out.println(item.toString()));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        // 排序保证相同元素连续，nums元素唯一则不需要排序
        Arrays.sort(nums);
        recurse(new ArrayList<>(), nums, used ,result);
        return result;
    }

    public void recurse(List<Integer> list, int[] nums, boolean[] used, List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
        }
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            temp = nums[i];
            // nums元素唯一则不需要判断
            // 重复判断，i>0保证从第二个元素开始；
            // temp=nums[i - 1]保证连续2个相等，其中nums已经排序了；
            // !used[i - 1]保证上1个元素未用，如果使用了就是其中1个需要的结果（如，1、1、2，到第二个1时，第1个1已经使用是需要的结果）
            if (i > 0 && temp == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            list.add(temp);
            recurse(list, nums, used, result);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
