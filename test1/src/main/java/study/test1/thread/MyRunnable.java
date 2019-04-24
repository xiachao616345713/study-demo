package study.test1.thread;

import java.util.Observable;

/**
 * @author chao
 * @since 2019-04-24
 */
public class MyRunnable extends Observable implements Runnable {

    @Override
    public void run() {
        int i = 1;
        try {
            while (true) {
                doSomething(i);
                i++;
            }
        } catch (Exception ignore) {
        } finally {
            notifyOnEx();
        }
    }

    private void notifyOnEx() {
        super.setChanged();
        super.notifyObservers();
    }

    private void doSomething(int i) throws Exception {
        if(i == 10) throw new Exception("111");
        System.out.println("xxxx=" + i);
        Thread.sleep(1000);
    }

}

