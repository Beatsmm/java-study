package com.example.javastudy.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 是一种同步手段，允许固定数量的线程在某一个点相互等待，还可以重复使用
 * 注意点：
 * 1、每个参与线程需要处理因CyclicBarrier失效后导致的内存数据后者数据库数据的回滚，因为任何一个线程抛出
 * Exception会同时导致其他线程也会抛出Exception
 * 2、barrierAction种需要对Exception进行处理避免影响整个业务逻辑
 */

/**
 *  CyclicBarrier和CountDownLatch的区别
 *  CountDownLatch是计数器只能使用一次，而CyclicBarrier的计数器有reset功能乐意使用多次
 *  对CountDownLatch来说重点是一个线程（多个线程）等待，而其他的N个线程在完成某件时间后，可以终止也可以等待，
 *  而对于CyclicBarrier重点是多个线程，在任何一个线程没有完成所有的线程都必须等待
 *  CountDownLatch是计数器，线程完成一个记录减一个，CyclicBarrier有点类似于阀门，所有线程都要打到阀门才能打开，然后继续执行
 */
public class CyclicBarrierTest {

    /**
     * 例：加入一个公司有各个部门，现在老板想知道公司男女人数，老板只需要开会通知部门老大去统计最后汇总
     */
    private static List<Statistics> list = new ArrayList<>();
    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5,()->{
            // 最终汇总
            int totalMale = 0;
            int totalFemale = 0;
            for (Statistics statistics : list) {
                totalMale += statistics.getMale();
                totalFemale += statistics.getFemale();
            }
            System.out.println("This Campany has male : "+totalMale+",female : "+totalFemale);
        });

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                Statistics statistics = new Statistics();
                statistics.setMale((int) (Math.random()*100));
                statistics.setFemale((int) (Math.random()*100));
                list.add(statistics);
                System.out.println(Thread.currentThread().getName()+"male:"+statistics.getMale()+",female:"+statistics.getFemale());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }




    }




}

class Statistics{

    private int male;

    private int female;

    public int getMale() {
        return male;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getFemale() {
        return female;
    }

    public void setFemale(int female) {
        this.female = female;
    }
}