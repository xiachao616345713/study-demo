package study.test1.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chao
 * @since 2018-12-11
 */
public class DeadLockSample extends Thread {

    private String name;
    private String stringA;
    private String stringB;

    public DeadLockSample(String name, String stringA, String stringB) {
        this.name = name;
        this.stringA = stringA;
        this.stringB = stringB;
    }

    @Override
    public void run() {
        synchronized (stringA) {
            System.out.println(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (stringB) {
                System.out.println("out");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String stra = "123";
        String strb = "456";
        DeadLockSample threadA = new DeadLockSample("threadA", stra, strb);
        DeadLockSample threadB = new DeadLockSample("threadB", strb, stra);

        threadA.start();
        threadB.start();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
        scheduled.scheduleAtFixedRate(() -> {
            long[] threadIds = threadMXBean.findDeadlockedThreads();
            if (threadIds != null) {
                System.out.println("find deadLocked thread");
                for (ThreadInfo threadInfo : threadMXBean.getThreadInfo(threadIds)) {
                    System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
                }
            }
        }, 5L, 10L, TimeUnit.SECONDS);

        threadA.join();
        threadB.join();
    }
}

