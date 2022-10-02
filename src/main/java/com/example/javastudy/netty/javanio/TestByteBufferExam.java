package com.example.javastudy.netty.javanio;


import java.nio.ByteBuffer;

/**
 * 粘包半包分析
 * 网络传输中当我们的数据是以数据包传输的，一开始的时候如果数据包过小他会等待数据包达到一定数量的情况下才会发送（粘包），
 * 这样做的好处类似于mysql中的WAL机制，这样数据就会被拆分，导致比如一句完整的话被切分（半包问题）
 * 例：比如我们传输一句话用\n切分发送
 * hello,world\nI'm xuhaoyu\nice to meet you
 */
public class TestByteBufferExam {

    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(64);
        source.put("hello,world\nI'm xuhaoyu\nnice to meet you".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        // 切换到读模式
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整信息
            if (source.get(i) == '\n'){
                // 换行符索引+1-position
                int length = i + 1 - source.position();
                // 吧这条完整信息存入心的ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从source读，像target写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                target.flip();
                System.out.println(target.get());
            }

        }
        source.compact();
    }
}
