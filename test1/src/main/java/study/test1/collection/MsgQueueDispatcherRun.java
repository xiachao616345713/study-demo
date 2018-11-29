package study.test1.collection;

/**
 * @author chao
 * @since 2018-11-28
 */
public class MsgQueueDispatcherRun {

    public static void main(String[] args) {

        PriorityBlockingQueueTest msgQueueDispatcher = PriorityBlockingQueueTest.getInstance();

        for (int i = 0; i < 20; i++) {
            PriorityBlockingQueueTest.MsgRunnable msgRunnable = new PriorityBlockingQueueTest.
                    MsgRunnable(String.format("send msg %d", i), i);
            msgQueueDispatcher.offer(msgRunnable);
        }

        new Thread(()->{
            try {
                Thread.sleep(30000);
                msgQueueDispatcher.stopDispatcher();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        msgQueueDispatcher.startDispatcher();
        //msgQueueDispatcher.stopDispatcher();
    }
}
