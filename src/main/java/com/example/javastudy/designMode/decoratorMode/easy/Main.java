package com.example.javastudy.designMode.decoratorMode.easy;

public class Main {

    /**
     * 例子:假如现在我有一个iPhone4s手机姑且看它是一代苹果手机，假如4s只能拍照片和打视频，现在我需要投屏和NFC功能
     * 过了很多年我出现了iPhone 14pro Max可以投屏，NFC功能，但是兜里没钱，买不起14pro Max，所以决定自己动手给
     * 4s做一次升级让它有14proMax一样的功能
     * 装饰模式通过关联机制把一个类的对象嵌入到另外一个类中，类似于套娃，套就相当于装饰器，继承模式相当于是静态的
     * 因为一定要写一个新的子类，对层级进行扩展，而关联机制是动态的我们拿到一个类，不需要改变原有的类，就可以进行扩展
     */
    public static void main(String[] args) {
        new PhoneDecorator(new Iphone4s()).takePhotos();
        new PhoneDecorator(new Iphone4s()).call();



    }
}
