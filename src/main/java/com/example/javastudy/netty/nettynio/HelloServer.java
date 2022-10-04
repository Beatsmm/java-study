package com.example.javastudy.netty.nettynio;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * channel：数据的通道，类比加工就是我们的加工厂的流水线
 * msg:流动的数据，就是我们工厂流水线待加工的东西
 * byteBuf：msg的载体
 * handler：数据处理的工序，就是我们加工的时候可能会打磨，抛光等
 * pipeline：数据处理的工序有很多，加在一起就是pipeline，可以理解为处理待加工所需要的步骤
 * eventLoop:可以理解为处理数据的工人，就是我们处理代加工的负责人
 *      1、工人可以管理多个channel的IO操作，并且一旦工人负责了某个channel，就要负责到底
 *      2、工人既可以执行IO操作，也可以进行任务处理，每个工人有任务队列，队列里可以堆放多个channel待处理任务，任务分为
 *      普通任务和地ing是任务
 *      3、工人按照pipeline顺序，一次按照handler规划处理数据，可以为每到工序指定不同的工人
*/
public class HelloServer {

    public static void main(String[] args) {
        // 1.启动服务器
        new ServerBootstrap()
                //2.创建NioEventLoopGroup，可以理解为线程池+Selector
            .group(new NioEventLoopGroup())
                // 3.选择服务器的ServerSocketChannel实现
            .channel(NioServerSocketChannel.class)
                //4.child负责处理读写，该方法决定了child执行哪些操作
                // ChannelInitializer处理器仅执行一次
                // 他的作用就是待客户端socketChannel建立连接后，执行initChannel添加更多的处理器
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    // 5、SocketChannel的处理器，使用StringDecoder解码，ByteBuf=>String
                    nioSocketChannel.pipeline().addLast(new StringDecoder());
                    // 6、SocketChannel的业务处理，使用上一个处理器的处理结果
                    nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                        @Override
                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                            System.out.println(s);
                        }
                    });
                }
            }).bind(8080);


    }
}
