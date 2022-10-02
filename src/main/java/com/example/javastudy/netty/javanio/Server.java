package com.example.javastudy.netty.javanio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {
        // 使用nio来理解阻塞模式
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1、创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 非阻塞模式ssc.configureBlocking(false);影响accept非阻塞虽然事件没有发现，线程可以继续运行，sc返回null
        // 2、绑定监听端口
        ssc.bind(new InetSocketAddress(8080));
        // 3、连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            // 4、accept建立与客户端连接，SocketChannel用来与客户端之间通信
            SocketChannel sc = ssc.accept(); // 阻塞，等待新的连接建立，如果没有建立就会阻塞
            //sc.configureBlocking(false); channel.read()也不会阻塞没有数据返回0
            channels.add(sc);
            for (SocketChannel channel : channels) {
                channel.read(buffer); // 没有东西读会阻塞，线程停止运行，等待客户端发送数据
                buffer.flip();
                buffer.clear();
            }
        }
    }
}
