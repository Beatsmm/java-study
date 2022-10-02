package com.example.javastudy.netty.javanio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try(
                FileChannel form = new FileInputStream("test.txt").getChannel();
                FileChannel to = new FileOutputStream("data.txt").getChannel();

           ){
            // 效率高，transferTo底层会采用零拷贝技术
            long size = form.size();
            // left 变量还剩余多少字节
            // 因为一次只会读取2g大小的文件，所以需要分段处理
            for(long left = size;left>0;){
                left -= form.transferTo((size-left),left,to);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
