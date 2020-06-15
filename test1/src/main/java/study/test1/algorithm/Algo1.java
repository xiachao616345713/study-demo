package study.test1.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * algorithm
 *
 * @author chao
 * @since 2019-04-14
 */
public class Algo1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int[] a = new int[]{1,2,3,4};
//        new Algo1().twentyFourPoint(list, a, 1,a[0], new StringBuilder().append(a[0]));
        new Algo1().dfs(list, a, null, null, null, 0);
        list.forEach(System.out::println);
    }

    /**
     * 给出4个整数，要求用加减乘除4个运算使其运算结果变成24，4个数字要不重复的用到计算中。
     * 数字之间的除法中不得出现小数(例如原本我们可以1/4=0.25)
     */
//    public void twentyFourPoint(int[] a, int index, Integer tempValue, StringBuilder tempRet) {
//        if (index >= 4) return;
//        int temp = a[index];
//        if (index == 3) {
//            if (tempValue + temp == 24) {
//                tempRet.append("+").append(temp);
//                ret.add(tempRet.toString());
//            } else if (tempValue - temp == 24) {
//                tempRet.append("-").append(temp);
//                ret.add(tempRet.toString());
//            } else if (tempValue * temp == 24) {
//                tempRet.insert(0,"(");
//                tempRet.append(")*").append(temp);
//                ret.add(tempRet.toString());
//            } else if (tempValue / temp == 24) {
//                tempRet.insert(0,"(");
//                tempRet.append(")/").append(temp);
//                ret.add(tempRet.toString());
//            }
//        } else {
//            twentyFourPoint(ret, a, index + 1, tempValue + temp, new StringBuilder(tempRet).append("+").append(temp));
//            twentyFourPoint(ret, a, index + 1, tempValue - temp, new StringBuilder(tempRet).append("-").append(temp));
//            twentyFourPoint(ret, a, index + 1, tempValue * temp, new StringBuilder("(").append(tempRet).append(")*").append(temp));
//            if (tempValue % temp == 0) {
//                twentyFourPoint(ret, a, index + 1, tempValue / temp, new StringBuilder("(").append(tempRet).append(")/").append(temp));
//            }
//        }
//    }

    public void dfs(List<String> ret, int[] a, List<Integer> used, Integer tempValue, StringBuilder tempRet, int deep) {
        if (deep > 4) return;
        if (deep == 4) {
            if (tempValue == 24) {
                String str = tempRet.toString();
                if (!ret.contains(str)) {
                    ret.add(str);
                }
                return;
            }
        }
        for (int i = 0; i < a.length; i++) {
            int valuei = a[i];
            List<Integer> temp = new ArrayList<>();
            StringBuilder tempStr;
            if (used == null) {
                temp.add(valuei);
                deep = 1;
                tempValue = valuei;
                tempStr = new StringBuilder();
                tempStr.append(valuei);
            } else if (used.contains(valuei)) {
                continue;
            } else {
                temp.addAll(used);
                tempStr = new StringBuilder(tempRet);
            }
            for (int j = 0; j < a.length; j++) {
                int valuej = a[j];
                if (temp.contains(valuej)) {
                    continue;
                }
                temp.add(valuej);
                dfs(ret, a, temp, tempValue + valuej, new StringBuilder(tempStr).append("+").append(valuej), deep + 1);
                dfs(ret, a, temp, tempValue - valuej, new StringBuilder(tempStr).append("-").append(valuej), deep + 1);
                dfs(ret, a, temp, tempValue * valuej, new StringBuilder("(").append(tempStr).append(")*").append(valuej), deep + 1);
                if (tempValue % valuej == 0) {
                    dfs(ret, a, temp, tempValue / valuej, new StringBuilder("(").append(tempStr).append(")/").append(valuej), deep + 1);
                }
            }
        }
    }

}
