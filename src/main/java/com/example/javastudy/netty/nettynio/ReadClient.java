package com.example.javastudy.netty.nettynio;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 线程服务端和客户端断开连接以后需要关闭channel
 * 当我们关闭channel的时候，可以调用channel.close()方法，但是这个方法是异步的，真正的关闭操作不是在调用该方法
 * 的线程中执行的，而是在NIO线程中执行真正的关闭操作
 * 方法一：通过channel.closeFuture()方法获得对应的ChannelFuture对象，然后调用sync方法阻塞执行操作的线程，
 * 等待channel真正关闭后，在执行其他操作
 * 获得closeFuture对象
 *         ChannelFuture closeFuture = channel.closeFuture();
 *         同步等待NIO线程执行完close操作
 *         closeFuture.sync();
 * 方法二：调用closeFuture.addListener方法，添加close的后续操作
 * closeFuture.addListener(new ChannelFutureListener() {
 *     @Override
 *     public void operationComplete(ChannelFuture channelFuture) throws Exception {
 *         // 等待channel关闭后才执行的操作
 *         System.out.println("关闭之后执行一些额外操作...");
 *         // 关闭EventLoopGroup
 *         group.shutdownGracefully();
 *     }
 * });
 *
 */
public class ReadClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建EventLoopGroup，使用完毕后关闭
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080));
        channelFuture.addListener(new ChannelFutureListener() {
             @Override
              public void operationComplete(ChannelFuture channelFuture) throws Exception {
                  Channel channel = channelFuture.channel();
                  channel.writeAndFlush("hello world");
              }
          });

        Channel channel = channelFuture.channel();
        Scanner scanner = new Scanner(System.in);
        // 创建一个线程用于输入并且香服务器发送
        new Thread(()->{
            while(true){
                String msg = scanner.next();
                if ("q".equals(msg)) {
                    // 关闭操作是异步的，在NIO线程中执行
                    channel.close();
                    break;
                }
                channel.writeAndFlush(msg);
            }
        },"inputThread").start();
        // 获得closeFuture对象
        ChannelFuture closeFuture = channel.closeFuture();
        // 同步等待NIO线程执行完close操作
        closeFuture.sync();

        // 关闭之后执行一些操作，可以保证执行的操作一定是在channel关闭以后执行的
        System.out.println("关闭之后执行一些额外操作...");

        // 关闭EventLoopGroup
        group.shutdownGracefully();

    }
}
