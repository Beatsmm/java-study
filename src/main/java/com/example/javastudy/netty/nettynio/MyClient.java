package com.example.javastudy.netty.nettynio;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                // 该方法为异步非阻塞方法，主线程调用后不会被阻塞，真正去执行连接操作的是NIO线程
                // NIO线程：NioEventLoop中的线程
                .connect(new InetSocketAddress("localhost", 8080));
        /**
         * 该方法用于等待连接真正的建立，如果去掉这行代码，服务器会无法收到hello world，这是因为建立
         * 的过程是异步阻塞的，如果不使用sync方法阻塞主线程，等待连接真正建立，这时通过channelFuture.channel
         * 拿到的Channel对象，并不是真正与服务器建立好的连接的channel，也就没法将正确的传输给服务器端，所以需要sync阻塞
         * 主线程同步处理结果
         * 下面还有一种方法，用于异步获取建立连接后的 Channel 和发送数据，使得执行这些操作的线程是NIO线程
         * （去执行connect操作的线程）
         *  channelFuture.addListener(new ChannelFutureListener() {
         *             @Override
         *             public void operationComplete(ChannelFuture channelFuture) throws Exception {
         *                 Channel channel = channelFuture.channel();
         *                 channel.writeAndFlush("hello world");
         *             }
         *         });
         * System.in.read();
         */

        channelFuture.sync();//
        // 获取客户端-服务器之间的Channel对象
        Channel channel = channelFuture.channel();
        channel.writeAndFlush("hello world");
        System.in.read();
    }


}
