package com.example.javastudy.designMode.facadeMode;

public class Test {

    /**
     * 外观模式：类似于我们分布式系统中的网关，对外提供简单的交互接口，隐藏内部的复杂性
     * 例：当我们当程序有很多模块，我们希望给用户提供一个统一操作的洁面类，而不是让用户分别于这些模块交互
     * 生成模块报表，报表里面可能需要支付模块的数据，订单模块的数据，物流模块的数据，我们不应该写很多API给
     * 前端去调用，应该使用一个统一的接口去封装这些数据
     * 优点：降低了客户端的复杂性
     * 缺点：增加了一层，所以当子系统改变的时候，外观类相应的需要维护
     */
    public static void main(String[] args) {
        new Facade().getReport();
    }
}
