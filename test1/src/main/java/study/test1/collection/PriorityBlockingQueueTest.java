package study.test1.collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author chao
 * @since 2018-11-28
 */
public class PriorityBlockingQueueTest {

    private PriorityBlockingQueue<Runnable> queue;

    private static volatile PriorityBlockingQueueTest instance;

    private ExecutorService execute;

    private AtomicIntegerFieldUpdater updater;

    private volatile int exitFlag;

    private PriorityBlockingQueueTest() {
        updater = AtomicIntegerFieldUpdater.newUpdater(PriorityBlockingQueueTest.class, "exitFlag");
        queue = new PriorityBlockingQueue<>();
        execute = Executors.newFixedThreadPool(5);
    }

    private static class PriorityBlockingQueueTestHolder {
        private static final PriorityBlockingQueueTest holder = new PriorityBlockingQueueTest();
    }

    public static PriorityBlockingQueueTest getInstance() {
        if (instance == null) {
            instance = PriorityBlockingQueueTestHolder.holder;
        }
        return instance;
    }

    public void startDispatcher() {
        while (exitFlag != 1) {
            if (exitFlag == 1) {
                break;
            }
            try {
//                if (mPriorityBlockingQueue.size() == 0) {
//                    System.out.println("over!!!!!!!");
//                    mMsgTaskExecutor.shutdown();
//                    break;
//                }
                System.out.println("waiting for msg......");
                Runnable runnable = queue.poll(5, TimeUnit.SECONDS);
                if (runnable != null) {
                    System.out.println(String.format("------take msg %s  level %d------",
                            ((MsgRunnable) runnable).getContent(), ((MsgRunnable) runnable).getLevel()));
                    if (execute != null) {
                        execute.execute(runnable);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopDispatcher() {
        updater.set(this, 1);
        if (execute != null) {
            execute.shutdown();
        }
    }


    public void offer(MsgRunnable runnable) {
        if (queue != null) {
            queue.offer(runnable);
        }
    }

    static class MsgRunnable implements Runnable, Comparable<MsgRunnable> {

        private int level;
        private String content;

        public MsgRunnable(String content, int level) {
            this.content = content;
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public String getContent() {
            return content;
        }

        @Override
        public int compareTo(MsgRunnable o) {
            if (this.level > o.level) {
                return -1;
            } else if (this.level < o.level) {
                return 1;
            }
            return 0;
        }

        @Override
        public void run() {
            try {
                System.out.println(String.format("sending msg %s,current level %d", content, level));
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
