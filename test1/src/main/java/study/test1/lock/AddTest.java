package study.test1.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chao
 * @since 2018-12-13
 */
public class AddTest {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static int num = 0;

//    public static void main(String[] args) throws InterruptedException {
//        long start = System.currentTimeMillis();
//
//        for (int i = 0; i < 10000000; i++) {
//            executorService.submit(AddTest::addSynchronized);
//        }
//        while (num != 10000000) {
//
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        Thread.sleep(1000);
//        System.out.println(num);
//        executorService.shutdown();
//    }
//
//    private static synchronized void addSynchronized() {
//        num++;
//    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            executorService.submit(AddTest::addSynchronized);
        }
        while (num != 10000000) {

        }
        System.out.println(System.currentTimeMillis() - start);
        Thread.sleep(1000);
        System.out.println(num);
        executorService.shutdown();
    }

    private static final ReentrantLock lock = new ReentrantLock();

    private static void addSynchronized() {
        try {
            lock.lock();
            num++;
        } finally {
            lock.unlock();
        }
    }
}
