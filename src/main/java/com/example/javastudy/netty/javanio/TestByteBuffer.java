package com.example.javastudy.netty.javanio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer正确使用
 * 1、向buffer写入数据，例如调用channel.read(一开始buffer是空的，从channel里面读写入buffer
 * 2、调用flip切换到读模式(如果不切换到读模式是读不出来的)
 * 3、从buffer读取数据，buffer.get
 * 4、调用clear或compact切换到写模式
 */

/**
 * 问题：为什么要用这种方式使用ByteBuffer，并且切换读写模式
 * byteBuffer内部有三个结构
 * 1、position
 *  读：读数据的时候可以从一个特定的地方读，当buffer从写模式切换到读模式的时候，position会重置为0
 *  写：写数据到buffer，position表示当前的位置，初始的position值为0，每次插入的时候position会移动到下一个可以插入的
 *  位置，最大可以到capacity
 * 2、capacity 容量
 * 3、limit
 *  写：buffer的limit表示你最多能往buffer里面写多少数据，写模式下limit等于capacity
 *  读：limit表示你最多能读到多少数据，当切换buffer到读模式的时候，limit会被设置成写模式下的position
 */

public class TestByteBuffer {

    public static void main(String[] args) {
        //FileChannel
        // 1、输入输出流 2、randomAccessFile
        try(FileChannel channel = new FileInputStream("test.txt").getChannel()){
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true){
                // 从channel读取数据，向buffer写入
                int len = channel.read(buffer);
                if (len == -1){ // 没有内容了
                    break;
                }
                buffer.flip();// 切换到读模式
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.print((char)b);
                }
                buffer.clear();
            }
        }catch (IOException e){

        }
    }
}
