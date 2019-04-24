package study.test1.thread;

import java.util.Observable;
import java.util.Observer;

/**
 * @author chao
 * @since 2019-04-24
 */
public class MyObserver implements Observer {

    private Runnable runnable;

    public MyObserver(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("重启thread");
        new Thread(runnable).start();
    }
}
