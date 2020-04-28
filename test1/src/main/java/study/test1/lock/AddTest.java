package study.test1.lock;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chao
 * @since 2018-12-13
 */
public class AddTest {

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
        ThreadPoolExecutor execute = new ThreadPoolExecutor(
            10, 50,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1000),
            new ThreadFactoryBuilder().setNameFormat("test-th-pool-%s")
                .setUncaughtExceptionHandler((t, e) -> e.printStackTrace()).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            execute.submit(AddTest::addSynchronized);
        }
        while (num != 10000000) {

        }
        System.out.println(System.currentTimeMillis() - start);
        Thread.sleep(1000);
        System.out.println(num);
        execute.shutdown();
    }

    private static final ReentrantLock lock = new ReentrantLock();

    private static void addSynchronized() {
        lock.lock();
        try {
            num++;
        } finally {
            lock.unlock();
        }
    }
}
