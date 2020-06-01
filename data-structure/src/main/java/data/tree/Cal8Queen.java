package data.tree;

/**
 * @author xiachao
 * @date 2020/05/29
 */
public class Cal8Queen {

    // 每一行放1个元素，result表示放在哪一列
    private int[] result = new int[8];

    private void cal8Queen(int row) {
        if (row == 8) {
            printQueens(result);
            return;
        }
        for (int i = 0; i < 8; i++) {
            // 满足条件到下一层，存在一条都不满足都直接结束
            if (isOk(row, i)) {
                result[row] = i;
                cal8Queen(row + 1);
            }
        }
    }

    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        // 后面的行没有放棋子，只放看左上和右上，从row-1开始
        int temp;
        for (int i = row - 1; i >= 0; i--) {
            temp = result[i];
            // 当前列
            if (temp == column) {
                return false;
            }
            // 左上
            if (temp == leftUp) {
                return false;
            }
            // 右上
            if (temp == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }

        return true;
    }



//    int[] result = new int[8];//全局或成员变量,下标表示行,值表示queen存储在哪一列
//    public void cal8queens(int row) { // 调用方式：cal8queens(0);
//        if (row == 8) { // 8个棋子都放置好了，打印结果
//            printQueens(result);
//            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
//        }
//        for (int column = 0; column < 8; ++column) { // 每一行都有8中放法
//            if (isOk(row, column)) { // 有些放法不满足要求
//                result[row] = column; // 第row行的棋子放到了column列
//                cal8queens(row+1); // 考察下一行
//            }
//        }
//    }
//
//    private boolean isOk(int row, int column) {//判断row行column列放置是否合适
//        int leftup = column - 1, rightup = column + 1;
//        for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
//            if (result[i] == column) return false; // 第i行的column列有棋子吗？
//            if (leftup >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
//                if (result[i] == leftup) return false;
//            }
//            if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
//                if (result[i] == rightup) return false;
//            }
//            --leftup; ++rightup;
//        }
//        return true;
//    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Cal8Queen cal8Queen = new Cal8Queen();
        cal8Queen.cal8Queen(0);
    }

}
