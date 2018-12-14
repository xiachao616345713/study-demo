package study.test1.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * @author chao
 * @since 2018-12-11
 */
public class SemaphoreTest {

    static class SemaphoreWorker implements Runnable {

        private String name;
        private Semaphore semaphore;

        public SemaphoreWorker(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("acquire success " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //semaphore.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Semaphore(int permits) 参数permits表示许可数目，即同时可以允许多少线程进行访问
        // Semaphore(int permits, boolean fair) 这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
        // 阻塞方法 acquire()获取一个许可，release()释放一个许可
        // 非阻塞方法 tryAcquire()
        //final Semaphore semaphore = new Semaphore(5);
        final Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 20; i++) {
            new Thread(new SemaphoreWorker(semaphore, "thread"+i)).start();
        }

        Thread.sleep(1000L);

        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release(1);
            }
        }).start();

//        Thread thread = new Thread(()->{
//            LockSupport.park();
//            System.out.println("test");
//        });
//        thread.start();
//
//        new Thread(()->{
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            LockSupport.unpark(thread);
//        }).start();
    }

}
