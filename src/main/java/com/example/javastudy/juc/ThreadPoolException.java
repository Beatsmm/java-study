package com.example.javastudy.juc;

import java.util.concurrent.*;

public class ThreadPoolException {
    // 线程池抛出异常如何处理
    // 1、我们可以通过加try catch来捕获我们的异常
    // 2、使用Thread.setDefaultUncaughtExceptionHandler方法来捕获异常
    // 3、重写afterExecute进行异常处理


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//      1、  // 创建一个线程池
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//
//        // 当线程池抛出异常后submit无提示，其他线程继续执行
//        Future<?> submit = executorService.submit(new Task());
//
//
//        // 当线程池抛出异常后 execute抛出异常，其他线程继续执行新任务
//        executorService.execute(new Task());
        // 2、
        // 实现一个自己的线程工厂
//        ThreadFactory factory = (Runnable r)->{
//            // 创建一个线程
//            Thread t = new Thread(r);
//            // 给创建的线程设置UncaughtExceptionHandler对象里面实现异常的默认逻辑
//            t.setDefaultUncaughtExceptionHandler((Thread thread1,Throwable e) -> {
//                System.out.println("线程工厂设置的exceptionHandler" + e.getMessage());
//            });
//            return t;
//        };
//        // 创建一个自己定义的线程池，使用自己定义的线程工厂
//        ExecutorService executorService = new ThreadPoolExecutor(
//                1,
//                1,
//                0,
//                TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<>(10),
//                factory);
//
//        // submit无提示
//        executorService.submit(new Task());
//
//        Thread.sleep(1000);
//        System.out.println("==================为检验打印结果，1秒后执行execute方法");
//        // execute 方法被线程工厂factory 的UncaughtExceptionHandler捕捉到异常
//        executorService.execute(new Task());
//      3、
        //1.创建一个自己定义的线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                3,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(10)
        ) {
            //重写afterExecute方法
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                //这个是execute提交的时候
                if (t != null) {
                    System.out.println("afterExecute里面获取到execute提交的异常信息，处理异常" + t.getMessage());
                }
                //如果r的实际类型是FutureTask 那么是submit提交的，所以可以在里面get到异常
                if (r instanceof FutureTask) {
                    try {
                        Future<?> future = (Future<?>) r;
                        //get获取异常
                        future.get();

                    } catch (Exception e) {
                        System.out.println("afterExecute里面获取到submit提交的异常信息，处理异常" + e);
                    }
                }
            }
        };
        //当线程池抛出异常后 execute
        executorService.execute(new Task());

        //当线程池抛出异常后 submit
        executorService.submit(new Task());
    }


}
class Task implements Runnable{


//   1、 @Override
//    public void run() {
//        try{
//            System.out.println(Thread.currentThread().getName() + "进入了task方法！！！");
//            int i = 1/0;
//        }catch(Exception e){
//            System.out.println("使用了try - catch 捕获异常" + e);
//        }
//
//    }
    //2、
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "进入了task方法！！！");
        int i = 1/0;
    }
}