package study.test1.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chao
 * @since 2018-11-30
 */
public class NIOServer extends Thread {

    public void run() {
        try (Selector selector = Selector.open();
                ServerSocketChannel serverSocket = ServerSocketChannel.open()) {// 创建 Selector 和 Channel
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            // 设置通道非阻塞
            serverSocket.configureBlocking(false);
            // 注册到 Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT).attach("test");
            while (true) {
                System.out.println("=====in");
                //select()方法返回的int值表示有多少通道已经就绪。亦即，自上次调用select()方法后有多少通道变成就绪状态。
                // 如果调用select()方法，因为有一个通道变成就绪状态，返回了1，若再次调用select()方法，
                // 如果另一个通道就绪了，它会再次返回1。如果对第一个就绪的channel没有做任何操作，
                // 现在就有两个就绪的通道，但在每次select()方法调用之间，只有一个通道就绪了
                selector.select();// 阻塞等待就绪的 Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    try (SocketChannel client = ((ServerSocketChannel) key.channel()).accept()) {
                        client.write(Charset.defaultCharset().encode("Hello world!" + key.attachment()));
                    }
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//        try (AsynchronousServerSocketChannel serverSock = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888))){
//            serverSock.accept(serverSock, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
//
//                @Override
//                public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
//                    exc.printStackTrace();
//                }
//
//                // 为异步操作指定 CompletionHandler 回调函数
//                @Override
//                public void completed(AsynchronousSocketChannel sockChannel, AsynchronousServerSocketChannel serverSock) {
//                    //serverSock.accept(serverSock, this);
//                    // 另外一个 write（sock，CompletionHandler{}）
//                    sockChannel.write(Charset.defaultCharset().encode("Hello World!"));
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), 8888)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(System.out::println);
        }
    }
}
