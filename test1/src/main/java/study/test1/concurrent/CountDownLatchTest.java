package study.test1.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author chao
 * @since 2018-12-12
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
        // await(long timeout, TimeUnit unit)和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
        // countDown()将count值减1
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new TestTask(countDownLatch, "task-" + i)).start();
        }

        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println("over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class TestTask implements Runnable {

        private String name;
        private CountDownLatch countDownLatch;

        public TestTask(CountDownLatch countDownLatch, String name) {
            this.countDownLatch = countDownLatch;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(Integer.valueOf(name.split("-")[1]) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run:" + name);
            countDownLatch.countDown();
        }
    }
}
