package study.leetcode;


/**
 * <a href="https://leetcode.com/problems/two-city-scheduling/">1029. Two City Scheduling</a>
 *
 * @author xiachao
 * @date 2021/03/15
 */
public class Solution1029 {

    public int twoCitySchedCost(int[][] costs) {
        int length = costs.length / 2;

        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = 1100;
        }

        int temp;
        for (int i = 0; i < costs.length; i++) {
            temp = costs[i][0] - costs[i][1];
            insert(array, temp, i);
        }

        // top length
        int middleNum = array[length - 1];
        int middleCount = 1;
        for (int i = length - 2; i >= 0; i--) {
            if (array[i] == middleNum) {
                middleCount++;
            } else {
                break;
            }
        }

        int ret = 0;
        for (int[] cost : costs) {
            temp = cost[0] - cost[1];
            if (temp < middleNum) {
                ret += cost[0];
            } else if (temp == middleNum) {
                if (middleCount > 0) {
                    ret += cost[0];
                    middleCount--;
                } else {
                    ret += cost[1];
                }
            } else {
                ret += cost[1];
            }
        }

        return ret;
    }


    private void insert(int[] array, int value, int num) {
        int i = 0;
        for (; i < array.length; i++) {
            if (value <= array[i]) {
                break;
            }
        }
        if (i == array.length) {
            return;
        }
        for (int j = Math.min(num, array.length - 1); j > i; j--) {
            array[j] = array[j - 1];
        }
        array[i] = value;
    }

    public static void main(String[] args) {
        //int[][] costs = new int[][]{{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
        //int[][] costs = new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        int[][] costs = new int[][]{{33, 135}, {849, 791}, {422, 469}, {310, 92}, {253, 489}, {995, 760}, {852, 197},
            {658, 216}, {679, 945}, {197, 341}, {362, 648}, {22, 324}, {408, 25}, {505, 734}, {463, 279}, {885, 512},
            {922, 850}, {784, 500}, {557, 860}, {528, 367}, {877, 741}, {554, 545}, {598, 888}, {558, 104}, {426, 427},
            {449, 189}, {113, 51}, {201, 221}, {251, 62}, {981, 897}, {392, 519}, {115, 70}, {961, 109}, {512, 678},
            {476, 708}, {28, 902}, {763, 282}, {787, 774}, {925, 475}, {253, 532}, {100, 502}, {110, 857}, {822, 942},
            {231, 186}, {869, 491}, {651, 344}, {239, 806}, {651, 193}, {830, 360}, {427, 69}, {776, 875}, {466, 81},
            {520, 959}, {798, 775}, {875, 199}, {110, 396}};
        System.out.println(new Solution1029().twoCitySchedCost(costs));
    }

}
