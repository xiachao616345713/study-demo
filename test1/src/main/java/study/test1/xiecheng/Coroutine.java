package study.test1.xiecheng;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import kilim.Mailbox;
import kilim.Task;

/**
 * 协程
 *
 * @author xiachao
 * @date 2020/04/28
 */
public class Coroutine {

    // 为每个协程创建一个信箱，由于协程中不能多个消费者共用一个信箱，需要为每个消费者提供一个信箱，这也是协程通过通信来保证共享变量的线程安全的一种方式
    static Map<Integer, Mailbox<Integer>> mailMap = new HashMap<>();

    public static void main(String[] args) {

        if (kilim.tools.Kilim.trampoline(false,args)) {
            return;
        }
        Properties propes = new Properties();
        propes.setProperty("kilim.Scheduler.numThreads", "1");// 设置一个线程
        System.setProperties(propes);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {// 创建一千生产者
            Mailbox<Integer> mb = new Mailbox<>(1, 10);
            new Producer(i, mb).start();
            mailMap.put(i, mb);
        }

        for (int i = 0; i < 1000; i++) {// 创建一千个消费者
            new Consumer(mailMap.get(i)).start();
        }

        Task.idledown();// 开始运行

        long endTime = System.currentTimeMillis();

        System.out.println( Thread.currentThread().getName()  + " 总计花费时长：" + (endTime- startTime));
    }
}
