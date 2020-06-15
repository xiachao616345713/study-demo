package study.test1.str;

import java.lang.reflect.Field;

/**
 * <href a="https://vanilla-java.github.io/2016/04/21/Bad-String.html"/>
 *
 * @author chao
 * @since 2019-03-30
 */
public class PingPongMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("bb".hashCode());
        System.out.println("cC".hashCode());

//        new Thread(() -> {
//            try {
//                synchronized ("bb") {
//                    while (true) {
//                        "bb".wait();
//                        "bb".notifyAll();
//                        System.out.println("b");
//                    }
//                }
//            } catch (InterruptedException e) {
//                throw new AssertionError(e);
//            }
//        }).start();
//        Thread.sleep(100);
//        setString("bb", 'c', 'C');
//        new Thread(() -> {
//            try {
//                // change "cC" to "cc" and this program prints nothing.
//                synchronized ("cC") {
//                    while (true) {
//                        "cC".notifyAll();
//                        "cC".wait();
//                        System.out.println("c");
//                    }
//                }
//            } catch (InterruptedException e) {
//                throw new AssertionError(e);
//            }
//        }).start();

    }

    public static void setString(String s, char... chars) {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            value.set(s, chars);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
