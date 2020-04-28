package study.test1.lock;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author xiachao
 * @date 2020/04/28
 */
public class LockTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10, 20,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10000),
            new ThreadFactoryBuilder().setNameFormat("test-th-pool-%s")
                .setUncaughtExceptionHandler((t, e) -> e.printStackTrace()).build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

        final LockTest lockTest = new LockTest();
        lockTest.num = 0L;

        // 全写
        Stopwatch sw = Stopwatch.createStarted();

        List<CompletableFuture<Void>> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
        }

        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();

        sw.stop();

        System.out.println(lockTest.num);
        lockTest.num = 0L;

        // 读写一半
        sw.start();

        list = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
        }

        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();

        sw.stop();

        System.out.println(lockTest.num);
        lockTest.num = 0L;

        // 全读
        sw.start();

        list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
        }
        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();
        sw.stop();

        System.out.println(lockTest.num);
        lockTest.num = 0L;

        // 读多写少
        sw.start();

        list = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
        }

        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();

        sw.stop();

        System.out.println(lockTest.num);
        lockTest.num = 0L;

        // 读少写多
        sw.start();

        list = new ArrayList<>();
        for (int i = 0; i < 2500; i++) {
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test1, threadPoolExecutor));
            list.add(CompletableFuture.runAsync(lockTest::test11, threadPoolExecutor));
        }

        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).join();

        sw.stop();

        System.out.println(lockTest.num);
        lockTest.num = 0L;

        System.out.println(sw.toString());
    }

    private final Object mutex = new Object();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final StampedLock stampedLock = new StampedLock();
    private final LongAdder longAdder = new LongAdder();

    private long num;

    // synchronized
    private void test1() {
        synchronized (mutex) {
            try {
                Thread.sleep(200L);
                num++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long test11() {
        synchronized (mutex) {
            return num;
        }
    }

    // reentrantReadWritedLock
    private void test2() {
        WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            num++;
        } finally {
            writeLock.unlock();
        }
    }

    private long test21() {
        ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            return num;
        } finally {
            readLock.unlock();
        }
    }

    // stampedLock
    private void test3() {
        long stamp = stampedLock.writeLock();
        try {
            num++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    private long test31() {
        long stamp = stampedLock.tryOptimisticRead();
        long ret = num;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                ret = num;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return ret;
    }

    // longAdder
    private void test4() {
        longAdder.add(1L);
    }

    private long test41() {
        return longAdder.sum();
    }


}
