package study.test1.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * guarded suspention
 *
 * @author xiachao
 * @date 2021/03/19
 */
public class GuardedSuspension {

    private static Map<String, GuardedSuspensionInstance> map = new ConcurrentHashMap<>();

    static class GuardedSuspensionInstance {

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private volatile boolean isDown = false;
        private Message message;
        private Result result;

        GuardedSuspensionInstance(Message argMessage) {
            message = argMessage;
        }

        private Result get() {
            lock.lock();
            try {
                while (!Thread.currentThread().isInterrupted() && !isDown) {
                    try {
                        condition.await(10, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                lock.unlock();
            }

            return result;
        }

        private void done(Result argResult) {
            lock.lock();
            try {
                result = argResult;
                isDown = true;
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

    }

    public Result sendData(Message message) {
        GuardedSuspensionInstance instance = new GuardedSuspensionInstance(message);
        map.put(message.getKey(), instance);

        System.out.println("message:" + message.toString());

        Result result;
        try {
            result = instance.get();
        } finally {
            map.remove(message.getKey());
        }

        return result;
    }

    private void receiveData(Result result) {
        map.get(result.key).done(result);
    }

    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    static class Message {

        public Message(String key, Object content) {
            this.key = key;
            this.content = content;
        }

        private String key;
        private Object content;
    }

    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    static class Result {

        public Result(String key, Object content) {
            this.key = key;
            this.content = content;
        }

        private String key;
        private Object content;
    }

    public static void main(String[] args) {
        GuardedSuspension o = new GuardedSuspension();
        Thread thread;

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("key-" + i, "xiachao-" + i);
            thread = new Thread(() -> System.out.println("result:" + o.sendData(message)));

            list.add(thread);
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Result result = new Result("key-" + i, "result-xiachao-" + i);

            thread = new Thread(() -> {
                long random = (long) (1 + Math.random() * 10);
                try {
                    TimeUnit.SECONDS.sleep(random);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                o.receiveData(result);
            });

            list.add(thread);
            thread.start();
        }

        list.forEach(item -> {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("over");
    }


}
