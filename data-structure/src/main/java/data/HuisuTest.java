package data;


/**
 * 回溯
 *
 * @author xiachao
 * @date 2020/06/01
 */
public class HuisuTest {

    // 8皇后棋子问题

//    // 每一行放1个元素，result表示放在哪一列
//    private int[] result = new int[8];
//
//    private void cal8Queen(int row) {
//        if (row == 8) {
//            printQueens(result);
//            return;
//        }
//        for (int i = 0; i < 8; i++) {
//            // 满足条件到下一层，存在一条都不满足都直接结束
//            if (isOk(row, i)) {
//                result[row] = i;
//                cal8Queen(row + 1);
//            }
//        }
//    }
//
//    private boolean isOk(int row, int column) {
//        int leftUp = column - 1;
//        int rightUp = column + 1;
//        // 后面的行没有放棋子，只放看左上和右上，从row-1开始
//        int temp;
//        for (int i = row - 1; i >= 0; i--) {
//            temp = result[i];
//            // 当前列
//            if (temp == column) {
//                return false;
//            }
//            // 左上
//            if (temp == leftUp) {
//                return false;
//            }
//            // 右上
//            if (temp == rightUp) {
//                return false;
//            }
//            leftUp--;
//            rightUp++;
//        }
//
//        return true;
//    }
//
//    private void printQueens(int[] result) { // 打印出一个二维矩阵
//        for (int row = 0; row < 8; ++row) {
//            for (int column = 0; column < 8; ++column) {
//                if (result[row] == column) {
//                    System.out.print("Q ");
//                } else {
//                    System.out.print("* ");
//                }
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
//
//
//    public static void main(String[] args) {
//        HuisuTest cal8Queen = new HuisuTest();
//        cal8Queen.cal8Queen(0);
//    }


    // 0-1背包问题，物品重量和价值

    // 允许最大重量
    private int canw;

    // 最大价值
    private int maxv;

    // 0-1背包问题，物品重量和价值
    public void f(int[] w, int[] v, int cw, int cv, int index) {
        // 遍历完
        if (index >= w.length) {
            if (cv > maxv) {
                maxv = cv;
            }
            return;
        }

        // 不放
        f(w, v, cw, cv, index + 1);

        if (cw + w[index] <= canw) {
            // 放
            f(w, v, cw + w[index], cv + v[index], index + 1);
        }
    }

    public static void main(String[] args) {
        HuisuTest huisuTest = new HuisuTest();
        huisuTest.canw = 100;

        int[] w = {11, 10, 25, 38, 51, 33, 20, 12, 22, 31};
        int[] v = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        huisuTest.f(w, v, 0, 0, 0);

        System.out.println(huisuTest.maxv);
    }

}
