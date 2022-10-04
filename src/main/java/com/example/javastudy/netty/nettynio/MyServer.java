package com.example.javastudy.netty.nettynio;

import com.example.javastudy.netty.javanio.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * 分工，accept事件由Boss处理，worker负责读写事件，如果有的任务需要执行的事件很长，可以使用非NioEventLoopGroup
 * 避免同一个NioEventLoop中其他Channel在比较长的事件内都无法得到处理
 * 结论：一个EventLoop可以负责多个channel，且EventLoop一旦与Channel绑定，则一直负责处理该Channel中的事件
 */
public class MyServer {

    public static void main(String[] args) {
        // 增加自定义的非NioEventLoopGroup
        EventLoopGroup group = new DefaultEventLoop();


        new ServerBootstrap()
            // 两个Group，分别为Boss负责accept事件，worker负责读写事件
            .group(new NioEventLoopGroup(1),new NioEventLoopGroup(2))
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf buf = (ByteBuf)msg;
                            System.out.println(Thread.currentThread().getName() + " " + buf.toString(StandardCharsets.UTF_8));
                            // 调用下一个handler
                            ctx.fireChannelRead(msg);
                        }
                        // 该handler绑定自定义的Group
                    }).addLast(group, "myHandler", new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            ByteBuf buf = (ByteBuf) msg;
                            System.out.println(Thread.currentThread().getName() + " " + buf.toString(StandardCharsets.UTF_8));
                        }
                    });
                }
            }).bind(8080);

    }
}
