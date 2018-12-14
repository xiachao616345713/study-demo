package study.test1.concurrent;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import study.test1.concurrent.PhaserTest.Person.ArriveAction;

/**
 * @author chao
 * @since 2018-12-12
 */
public class PhaserTest {

    //    static class TestTask implements Runnable {
//
//        private String name;
//        private Phaser phaser;
//
//        public TestTask(Phaser phaser, String name) {
//            this.phaser = phaser;
//            this.name = name;
//            phaser.register();
//        }
//
//        @Override
//        public void run() {
//            phaser.arriveAndAwaitAdvance();
//            try {
//                Thread.sleep(5000);
//                System.out.println("run:" + name);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            phaser.arriveAndDeregister();
//        }
//    }
//
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Phaser phaser = new Phaser(1);
//
//        int i = 1;
//        for (; i <= 3; i++) {
//            executor.submit(new TestTask(phaser, "task-" + i));
//        }
//        Thread.sleep(10000);
//        // arriveAndAwaitAdvance等待者达到指定数量开始下面运行,构造函数初始化数量和register数量
//        // arriveAndDeregister取消register，减少数量
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("this is phase " + phaser.getPhase());
//
//        for (; i <= 5; i++) {
//            executor.submit(new TestTask(phaser, "task-" + i));
//        }
//        phaser.arriveAndAwaitAdvance();
//        System.out.println("this is phase " + phaser.getPhase());
//
//        phaser.arriveAndDeregister();

        ArriveAction action = new ArriveAction();

        Random random = new Random();

        int num = 5;
        Phaser phaser = new Phaser(num);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= num; i++) {
            executor.submit(new Person(phaser, "person-" + i, random, action));
        }

        executor.shutdown();
    }

    // 5个人一起出去玩，3个阶段。全部到站点1才能出发；下车后自己随便完，约定到景点2才能一起吃饭；最后晚上到出口3集合才能回去
    static class Person implements Runnable {

        private String name;
        private Phaser phaser;
        private Random random;
        private final ArriveAction action;

        public Person(Phaser phaser, String name, Random random, ArriveAction action) {
            this.name = name;
            this.phaser = phaser;
            this.random = random;
            this.action = action;
        }

        @Override
        public void run() {
            long time = 1000L * random.nextInt(5);
            System.out.println(name + "到达站点1，等time：" + time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            allArriveAction();

            time = 1000L * random.nextInt(5);
            System.out.println(name + "到达景点2，等time：" + time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            allArriveAction();

            time = 1000L * random.nextInt(5);
            System.out.println(name + "到达出口3，等time：" + time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            allArriveAction();
        }

        private void allArriveAction() {
            synchronized (action) {
                if (action.getPhase() == phaser.getPhase()) {
                    action.run();
                    action.increment();
                }
            }
        }

        static class ArriveAction {

            int phase = 1;

            public void run() {
                System.out.println("this is phase:" + phase);
            }

            public int getPhase() {
                return phase;
            }

            public void increment(){
                phase++;
            }
        }
    }
}
