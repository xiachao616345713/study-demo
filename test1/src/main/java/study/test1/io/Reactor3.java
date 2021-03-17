package study.test1.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * reactor主从,handle使用多线程
 *
 * @author xiachao
 * @date 2021/03/17
 */
public class Reactor3 implements Runnable {

    private Selector selector;
    private ServerSocketChannel channel;

    private SubReactor3 subReactor;

    private LinkedBlockingQueue<ServerSocketChannel> channels = new LinkedBlockingQueue<>();

    public Reactor3(int port) throws Exception {
        selector = Selector.open();
        channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT).attach(new Acceptor());

        subReactor = new SubReactor3();
    }

    @Override
    public void run() {
        new Thread(subReactor).start();

        /**
         * 处理请求
         */
        while (!Thread.interrupted()) {
            try {
                selector.select();

                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                while (iterator.hasNext()) {
                    dispatch(iterator.next());
                    iterator.remove();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable runnable = (Runnable) selectionKey.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel client = channel.accept();

                if (client != null) {
                    new Handler(subReactor.getSubSelector(), client);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 专门处理任务
     */
    class SubReactor3 implements Runnable {

        private Selector subSelector;

        public SubReactor3() throws IOException {
            subSelector = Selector.open();
        }

        public Selector getSubSelector() {
            return subSelector;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    subSelector.select();

                    Set<SelectionKey> selectedKeys = subSelector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectedKeys.iterator();
                    while (iterator.hasNext()) {
                        dispatch(iterator.next());
                        iterator.remove();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static class Handler implements Runnable {

        private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

        final SocketChannel socketChannel;
        final SelectionKey selectionKey;

        public Handler(Selector selector, SocketChannel client) throws IOException {
            socketChannel = client;
            socketChannel.configureBlocking(false);
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this);
            selectionKey.interestOps(SelectionKey.OP_READ);
            // wakeup唤醒selector.select阻塞的方法，此时Handler#socketChannel注册了OP_READ
            // Reactor中dispatch方法中
            // 如果是OP_ACCEPT，selectionKey.attachment()会返回Acceptor
            // 如果是OP_READ，selectionKey.attachment()会返回Handler
            // run方法执行的结果结果就不同了，最后结果是所有已经OP_ACCEPT的都变成了client对OP_READ监听的Handler
            selector.wakeup();
        }

        @Override
        public void run() {
            executorService.submit(()->{
                // todo something
            });
        }
    }

}
