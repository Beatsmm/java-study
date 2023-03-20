package com.example.javastudy.designMode.adapterMode;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {

    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        long a = 0;
        for (int i = 0; i < num; i++) {
            a += num;
        }
        System.out.println("Result:" + a);

        return a;
    }


    /**
     * 适配器模式就是将一个类的接口转化成客户希望的另外的一个接口，使得原本由于接口不兼容而不能一起
     * 工作的那些类可以一起工作，假如我们拿着充电器去美国旅游，但是国内的充电器插座不符合美国的插座，
     * 所以需要一个适配器插在美国的插座上然后在插上我们的充电器，类似与Mac m1 m2系列用HDMI线连接
     * 显示器需要扩展坞
     */
    public static void main(String[] args) {
        // 现在我们需要通过一个线程去执行Task
//        Callable<Long> callable = new Task(1000L);
        // 编译出错，因为Thread接受Runnable接口，但不接受Callable接口，所以我们创建一个Runnable的适配器在里面把
        // Callable在RunnableAdapter构造器中依赖进去，start方法里面调用call方法，就可以正常编译
//        Thread t = new Thread(callable);
        Task task = new Task(1l);
        Thread thread = new Thread(new RunnableAdapter(task));
        thread.start();
    }

}
