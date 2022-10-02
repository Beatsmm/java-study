package com.example.javastudy.netty.javanio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 拷贝多级目录
 */
public class TestFilesCopy {

    public static void main(String[] args) throws IOException {
        String source = "D:\\test-1.11.2=x64";
        String target = "D:\\test-1.11.2=x64aaa";

        Files.walk(Paths.get(source)).forEach(item -> {
            try {
                String targetName = item.toString().replace(source, target);
                // 是目录就创建
                if (Files.isDirectory(item)){
                    Files.createDirectories(Paths.get(targetName));
                }
                // 是普通文件就拷贝
                else if (Files.isRegularFile(item)){
                    Files.copy(item,Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
