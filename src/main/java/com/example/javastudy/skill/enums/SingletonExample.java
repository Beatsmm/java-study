package com.example.javastudy.skill.enums;



public class SingletonExample {

    /**
     * 构造函数私有化，避免外部创建实例
     */
    private SingletonExample(){}

    private static SingletonExample getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonExample instance;

        // JVM 保证这个方法绝对只调用一次
        Singleton() {
            instance = new SingletonExample();
        }

        public SingletonExample getInstance() {
            return instance;
        }
    }

}
