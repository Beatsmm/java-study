package com.http.forest;

import com.dtflys.forest.config.ForestConfiguration;

public class ForRestDemo {

    public static void main(String[] args) {
        // 实例化Forset配置对象
        ForestConfiguration configuration = ForestConfiguration.configuration();
        configuration.setBackendName("httpclient");
        // 通过Forest配置对象实例化Forest请求接口
        ForRestClient myClient = configuration.createInstance(ForRestClient.class);
        // 调用Forest请求接口，并获取响应返回结果
        String result = myClient.simplePost("test");
        System.out.println(result);
    }
}


