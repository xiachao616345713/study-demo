package study.test1.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author chao
 * @since 2019-04-24
 */
public class MyThreadRun {

//    public static void main(String[] args) {
//        MyRunnable runnable = new MyRunnable();
//        MyObserver observer = new MyObserver(runnable);
//        runnable.addObserver(observer);
//        new Thread(runnable).start();
//    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        Thread threadA = new Thread(new MyRunnable1("threadA", a, b));
        Thread threadB = new Thread(new MyRunnable1("threadB", b, c));
        Thread threadC = new Thread(new MyRunnable1("threadC", c, a));

        threadA.start();
        TimeUnit.SECONDS.sleep(1);
        threadB.start();
        TimeUnit.SECONDS.sleep(1);
        threadC.start();

    }

    static class MyRunnable1 implements Runnable {

        private String print;
        private Object waitObj;
        private Object notifyObj;

        MyRunnable1(String print, Object waitObj, Object notifyObj) {
            this.print = print;
            this.waitObj = waitObj;
            this.notifyObj = notifyObj;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(print);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (notifyObj) {
                    notifyObj.notifyAll();
                }
                synchronized (waitObj) {
                    try {
                        waitObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
