package data;

import java.util.stream.IntStream;

/**
 * @author xiachao
 * @date 2020/07/05
 */
public class StreamDemo {

    public int workaroundSingleThread() {
        int[] holder = new int[] { 2 };
        IntStream sums = IntStream
            .of(1, 2, 3)
            .map(val -> val + holder[0]);

        /**
         * 先后顺序
         */
        holder[0] = 0;

        return sums.sum();
    }

    public static void main(String[] args) {
        System.out.println(new StreamDemo().workaroundSingleThread());
    }

}
