package study.test1.xiecheng;

import java.util.ArrayList;
import java.util.List;

/**
 * 正常的使用thread和协程对比,{@link Coroutine}
 *
 * @author xiachao
 * @date 2020/04/28
 */
public class ThreadTest {

    private static Integer count = 0;//
    private static final Integer FULL = 10; // 最大生产数量
    private static final Object LOCK = new Object(); // 资源锁

    public static void main(String[] args) {
        ThreadTest test1 = new ThreadTest();

        long start = System.currentTimeMillis();

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {// 创建五个生产者线程
            Thread thread = new Thread(test1.new Producer());
            thread.start();
            list.add(thread);
        }

        for (int i = 0; i < 1000; i++) {// 创建五个消费者线程
            Thread thread = new Thread(test1.new Consumer());
            thread.start();
            list.add(thread);
        }

        try {
            for (Thread thread : list) {
                thread.join();// 等待所有线程执行完
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(" 子线程执行时长：" + (end - start));
    }

    // 生产者
    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count.equals(FULL)) {// 当数量满了时
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + " 生产者生产，目前总共有 " + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    // 消费者
    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (LOCK) {
                    while (count == 0) {// 当数量为零时
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + " 消费者消费，目前总共有 " + count);
                    LOCK.notifyAll();
                }
            }
        }
    }
}
