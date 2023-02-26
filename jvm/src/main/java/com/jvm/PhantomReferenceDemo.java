package com.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 虚引用：
 * 虚引用顾名思义，就是形同虚设，它与其他几种引用都不同，虚引用并不会决定对象的生命周期，如果一个对象仅持有虚引用，
 * 那么它就跟没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和
 * 引用队列联合使用，虚引用的主要作用就是跟踪对象被垃圾回收的状态，仅仅提供了一种确保对象被finalize以后，某些事情的
 * 机制，PhantomReference的get方法总是返回null,因此无法访问对应的对象，它的意义就是说明一个对象已经进入finalization
 * 阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作，换句话说，设置虚引用关联的唯一目的就是在这个对象被收集器
 * 回收的时候收到一个系统通知或者后续添加进一步处理，Java技术允许使用finalize方法在垃圾收集器将对象从内存中清楚出去之前做
 * 必要的清理工作，比如张三通知被裁员，hr把张三叫到办公室协商赔偿通知张三被裁，然后给张三半天时间收拾个人物品，对象相当于张三
 * hr相当于gc，然后虚拟引用是绑定张三个人物品与张三的关系，forkjoinpool有具体的实现，当线程池里面的任务出错，在出错的任务的
 * 异常可能需要异常传递，这就用到了虚引用，处理这个异常任务被gc后的一些后事情
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) {
        // 演示ReferenceQueue
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println("*************GC回收前****************");
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("*************启动GC*************");
        o1 = null;
        System.gc();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        Object o2 = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o2,objectReferenceQueue);
        System.out.println("**************GC回收前***************");
        System.out.println(o2);
        System.out.println(phantomReference.get());
        System.out.println(objectReferenceQueue.poll());

        System.out.println("*************启动GC*************");
        o2 = null;
        System.gc();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(o2);
        System.out.println(phantomReference.get());
        System.out.println(objectReferenceQueue.poll());

        if(null == new Short((short) 12)) System.out.println(true);
    }

    /**
     * java提供了4种引用类型，在垃圾回收的时候，都各自有各自的特点，ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样
     * 可以运行，创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，如果程序发现某个虚引用已经被加入到引用
     * 队列，那么就可以在所引用的对象内存被回收之前采取必要的行动这相当于是一种机制，当关联的引用队列中有数据的时候，意味着引用指向的
     * 堆内存中的对象被回收，通过这种方式，JVM允许我们在对象被销毁后，做一些我们想做的事情。
     */

}
