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

/**
 * reactor
 *
 * @author xiachao
 * @date 2021/03/15
 */
public class Reactor implements Runnable {

    private Selector selector;
    private ServerSocketChannel channel;

    public Reactor(int port) throws Exception {
        selector = Selector.open();
        channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT).attach(new Acceptor());
    }

    @Override
    public void run() {
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
                    new Handler(selector, client);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static class Handler implements Runnable {

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

        }
    }
}
