package com.example.javastudy.juc;

/**
 * 优先阻塞队列
 * 1、对队列中对元素排序，如果没有指定比较器，插入队列的元素必须实现Comparable接口
 * 2、内部是基于数组实现的最小二叉堆算法
 * 3、长度可以扩展
 */

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 例：假设在排队买火车票，大家都在按照顺序排队，这个时候来了一位军人，我们应该优先让军人挂号
 * 一开始路人1，2，3先排队，他们的优先级以他们的排队时间waitTime为准，身份是军人的有优先权
 * 原理：PriorityBlockingQueue中offer内部主要调用了三个方法
 * 1、tryGrow:内部数组长度不够即进行数组扩展，PriorityBlockingQueue内部元素是存储在一个内部
 * 数组里面，数组长度不够的时候和ArrayList类型扩展到原来的0.5倍
 * 2、siftUpComparable：对新增的元素进行最小二叉推排序，未指定比较器时调用该方法使用元素自身实现
 * 的Comparator.compareTo()方法进行比较排序。
 * 3、siftUpUsingComparator：对新增的元素进行最小二叉推排序，指定了比较器时调用该方法使用指定的比较器进行比较排序。
 *
 * poll内部主要调用了dequeue重的两个方法
 * 1、siftDownComparable：当从队列首部取出元素时，通过向下遍历的方式将合适的元素填充队列首部queue[0]，
 * 未指定比较器时调用，此时通过元素自身实现的Comparator.compareTo()方法进行比较排序。
 * 2、siftDownUsingComparator：当从队列首部取出元素时，通过向下遍历的方式将合适的元素填充队列首部
 * queue[0]，指定比较器时调用，此时通过比较器进行比较排序。
 *
 */
public class PriorityBlockingQueueTest {

    static class User implements Comparable<User>{
        private String name;
        private String identify;
        private long waitTime;

        public User(String name, String identify) {
            this.name = name;
            this.identify = identify;
            this.waitTime = System.nanoTime();
        }

        @Override
        public int compareTo(User o) {
            if ("soldier".equals(o.identify)){
                return 1;
            }else if ("soldier".equals(identify)){
                return -1;
            }
            return waitTime < o.waitTime ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        PriorityBlockingQueue<User> priorityBlockingQueue = new PriorityBlockingQueue<User>(10);
        for (int i = 0; i < 3; i++) {
            User user = new User("Passer-by"+i,"coder"+i);
            priorityBlockingQueue.offer(user);
        }
        User soldier = new User("李云龙","soldier");
        priorityBlockingQueue.offer(soldier);
        User user = null;
        do {
            user = priorityBlockingQueue.poll();
            if (user != null){
                System.out.println(user.name + "成功购票");
            }
        } while(user != null);
    }
}
