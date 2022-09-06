package com.example.javastudy.juc;

public class VolatileTest {

    /**
     * 1、原本我们非volatile修饰的变量基于性能考虑，每个线程都会在主存拷贝一份到CPU缓存里面，因为CPU缓存效率比主存高，多核
     * CPU每个线程是允许在不同的CPU上的，这就导致我们在多线程情况下JVM对该变量的读写操作能够正常，但是经过Volatile修饰以后
     * 会让它在CPU缓存失效去主存读取数据更新到CPU缓存读取到最新的值保证可见性
     * 2、保证有序性
     * 3、但是volatile不能保证原子性
     * 疑问？
     * volatile修饰的变量对于其他线程之间应该是可见的，加入A，B两个线程都同时读到i的值是1，但是如果A线程执行完i的操作以后
     * 应该会把B线程读到的i的值置为无效并且强制B线程重新读入i的新值2才会进行自增操作
     * 答：加入1、线程读取i，2、temp=i+1 3、i = temp
     * 当i=1的时候A，B两个线程同时读取了i的值，然后A线程进行了temp=i+1的操作，这个时候i的值没有变化，然后B线程也进行了temp=i+1
     * 这个时候A，B两个线程保存的值还是1，temp是2，然后A线程进行了i=temp的操作，这个时候会把i的值2刷到主存，并且使B线程的i失效，此时
     * B重新读取i的值为2，但是temp的值还是2，所有导致i=temp还是等于2，比预期的结果少1
     */
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 100; i++) {
            new Thread(myRunnable).start();
        }
    }
}

class MyRunnable implements Runnable{

    private volatile int number;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            number++;
            System.out.println("number============>"+ number);
        }

    }
}
