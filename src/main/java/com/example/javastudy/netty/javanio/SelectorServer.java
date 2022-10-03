package com.example.javastudy.netty.javanio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorServer {

    public static void main(String[] args) throws IOException {
        //1、创建selector
        Selector selector = Selector.open();
        // 1、创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // 2、建立selector和channel联系
        // SelectionKey事件发生后，通过他可以知道事件和那个channel的事件关联
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        ssc.bind(new InetSocketAddress(8080));
        while(true){
            // 3、select方法，没有时间发生，线程阻塞，有事件，线程才会恢复运行
            // select事件未处理的时候不会阻塞
            selector.select();
            // 4、处理事件，selectKeys内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                // 处理key时候，要从selectedKeys集合中删除，否则下次处理就会有问题
                iterator.remove();
                // 5、区分事件类型
                if (key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                }else if (key.isReadable()){
                    try {
                        SocketChannel channel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        int read = channel.read(buffer);// 正常断开，read方法返回值是-1
                        if (read == -1){
                            key.cancel();
                        }else {
                            buffer.flip();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }


                }

            }
        }
    }
}
