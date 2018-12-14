package study.test1.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chao
 * @since 2018-12-12
 */
public class CyclicBarrierTest {

    static class TestTask implements Runnable {

        private String name;
        private CyclicBarrier cyclicBarrier;

        public TestTask(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000L * Integer.valueOf(name.split("-")[1]));
                System.out.println("run:" + name);
                cyclicBarrier.await();
                System.out.println("run over:" + name);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // public CyclicBarrier(int parties, Runnable barrierAction) {}
        // 参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容
        // await(),用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
        // await(long timeout, TimeUnit unit) 让线程等待一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("all over!"));
        for (int i = 0; i < 5; i++) {
            new Thread(new TestTask(cyclicBarrier, "task-" + i)).start();
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // CyclicBarrier相比于CountDownLatch是可以重用的
        for (int i = 0; i < 5; i++) {
            new Thread(new TestTask(cyclicBarrier, "task-" + i)).start();
        }
    }
}
