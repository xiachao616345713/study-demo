package study.test1.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author chao
 * @since 2018-11-30
 */
public class DemoServer extends Thread {

    private ServerSocket serverSocket;

    private Executor executor;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(0);
            executor = Executors.newFixedThreadPool(8);
            while (true) {
                System.out.println("=====in");
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                executor.execute(requestHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DemoServer server = new DemoServer();
        server.start();
        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedReader.lines().forEach(System.out::println);
        }
    }
}

// 简化实现，不做读取，直接发送字符串
class RequestHandler extends Thread {

    private Socket socket;

    RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            while (true) {
                Thread.sleep(1000);
                out.println("Hello world!");
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

