package data;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiachao
 * @date 2021/03/30
 */
public class ThreadPoolTest {

    private static final Logger log = LoggerFactory.getLogger(TanxinTest.class);

    public static void main(String[] args) throws InterruptedException {

        //BasicConfigurator.configure();

        ThreadPoolTest test= new ThreadPoolTest();
        new Thread(()->{
            test.test();
        }).start();

        TimeUnit.HOURS.sleep(1);
    }


    ThreadPoolExecutor executor1 = new ThreadPoolExecutor(
        10, 10,
        60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(),
        new ThreadFactoryBuilder().setNameFormat("executor1-%s").setUncaughtExceptionHandler((t,e)->{
            e.printStackTrace();
        }).build(),
        new AbortPolicy());

    ThreadPoolExecutor executor2 = new ThreadPoolExecutor(
        10, 10,
        60, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(10000),
        new ThreadFactoryBuilder().setNameFormat("executor2-%s").setUncaughtExceptionHandler((t,e)->{
            e.printStackTrace();
        }).build(),
        new AbortPolicy());

    private void test() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor1.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    //for (int j = 0; j < 100; j++) {
                    executor2.submit(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //log.info("child");
                        //log.info("executor2-thread-name, {}", Thread.currentThread().getName());
                    });
                    //}

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //log.info("parent");
                //log.info("executor1-thread-name, {}", Thread.currentThread().getName());
            });
        }
    }


}
