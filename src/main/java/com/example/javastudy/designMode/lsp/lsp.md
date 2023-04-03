# 里氏替换原则

## 原理
里氏替换原则主要阐述了有关继承的一些原则，也就是什么时候应该使用继承，什么时候不应该使用继承，里氏替换原是继承复用的基础，它反映里基类与子类之间的关系，是对开闭原则的补充，是对实现抽象化的具体实现

## 目的
里氏替换原则通俗来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能，也就是说，子类继承父类的时候，除了加新的方法完成新增功能外，尽量不要重写父类的方法

**1、** 子类可以实现父类的抽象方法，但不能覆盖父类的非抽象的方法
**2、** 子类中可以增加自己特有的方法
**3、** 当子类的方法重载父类的方法时，方法的前置条件(即方法的输入参数)要比父类的方法更加宽松，就是不能在子类重写父类的方法里面做一些增强的功能
**4、** 当子类的方法实现父类的方法的时候(重写/重载或实现抽象方法),方法的后置条件(即方法的输出/返回值)要比父类方法更严格或相等

通过重写父类的方法来完成新的功能写起来虽然简单，但是整个继承体系的可复用性会比较差，特别是运用多态比较频繁的时候，程序出错的几率会非常大，
如果违背了里氏替换原则，则继承类的对象在基类出现的地方会出现运行错误，这个时候修改的方法是取消原来的继承关系，重新设计他们之间的关系，关于里氏
替换原则的例子，最有名的就是正方形不是长方形，当然我们日常生活中也会有很多类似的例子，比如，企鹅、鸵鸟从生物学的角度来划分，他们属于鸟类，但从
类的继承关系来看，由于他们不能继承鸟会飞的功能，所以它们不能被定义成鸟的子类，同样，由于气球鱼不会游泳，所以不能定义成鱼的子类，呲水枪不能对敌人
给予致命打击，所以不能定义成枪的子类，对于这些比如正方形和长方形最好的做法是在添加一个父类，他们同时继承这个类
### 总结
采用里氏替换原则就是为了减少继承带来的缺点，增强程序的健壮性，版本升级时也可以保持良好的兼容性，即使增加子类，原有的子类也可以继续运行

## 例子
现在有一个A类里面有一个方法，完成两个数相减的功能，现在有一个B类继承A类，新增类一个功能，完成两个数的相加，改变了父类A的相见方法，可能是无意的，但是违背了
里氏替换的原则，我们可以把原来的父类和子类都继承一个更通俗的基类，原有的继承关系去掉，才有依赖，聚合，组合等关系的替代，定义一个更加基础的类Base，A类继承Base
B类也继承Base,但是如果B需要使用A类的方法，可以使用组合的关系，可以把A类注入到B类中，封装一个方法调用A的方法
