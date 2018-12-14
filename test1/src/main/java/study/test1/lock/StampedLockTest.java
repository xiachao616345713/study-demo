package study.test1.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.StampedLock;

/**
 * @author chao
 * @since 2018-12-10
 */
public class StampedLockTest {

    private final StampedLock sl = new StampedLock();

    void mutate() {
        long stamp = sl.writeLock();
        try {
            write();
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    private void write() {
        System.out.println("write start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("write end");
    }

    String access() {
        long stamp = sl.tryOptimisticRead();
        String data = read();
        // 判断这个stamp是否在读过程发生期间被修改过,如果stamp没有被修改过,认为这次读取时有效的,
        // 因此就可以直接return了,反之,如果stamp是不可用的,则意味着在读取的过程中,
        // 可能被其他线程改写了数据,因此,有可能出现脏读,如果如果出现这种情况,
        // 我们可以像CAS操作那样在一个死循环中一直使用乐观锁,知道成功为止
        if (!sl.validate(stamp)) {
            // 如果没有进入，就成功避免了开销；如果进入，则尝试获取读锁
            // 自旋锁的方式获取锁
            stamp = sl.readLock();
            try {
                data = ttRead();
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return data;
    }

    private String read() {
        return "123";
    }

    private String ttRead() {
        return "222";
    }

    public static void main(String[] args) throws InterruptedException {
        StampedLockTest test = new StampedLockTest();
        new Thread(test::mutate).start();
        Thread.sleep(1000);
        new Thread(() ->
                System.out.println(test.access())).start();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        for (ThreadInfo threadInfo : threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds())) {
            System.out.println(threadInfo.getThreadId() + ":" + threadInfo.getThreadName());
        }
    }

}
