package study.test1.thread;

/**
 * @author chao
 * @since 2019-04-24
 */
public class MyThreadRun {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        MyObserver observer = new MyObserver(runnable);
        runnable.addObserver(observer);
        new Thread(runnable).start();
    }

}
