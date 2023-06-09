# 合成复用原则

## 原理

尽量使用合成、聚合的方式，而不是使用继承。如果非要使用继承的关系必须满足里氏替换原则，合成复用原则同里氏替换原则相辅相成，两者都是开闭原则的具体实现规范

## 目的

通常一个类的复用分为继承复用和合成复用两种，继承复用虽然有简单和易实现的优点，但是也存在一下缺点

**1、** 继承复用破坏了类的封装性，因为继承会将父类的实现细节暴露给子类，父类对子类是透明的，这种复用又叫白箱复用
**2、** 子类与父类的耦合度高，父类的实现的任何改变都会导致子类的实现发生改变，这不利于类的扩展与维护
**3、** 它限制了复用的灵活性，从父类继承而来的实现是静态的，在编译时已经定义类，所以在运行的时候不可能发生改变

采用组合或者聚合复用的时候，可以将已有对象纳入新对象中，使之为新对象的一部分，新对象可以调用已有对象的功能，它有以下优点：

**1、** 维持了类的封装性，因为成分对象的内部细节是新对象看不见的，所以这种复用又成为黑箱复用
**2、** 新旧类之间的耦合度低，这种复用所需的依赖较少，新对象存取成分对象的唯一方法是通过成分对象的接口
**3、** 复用的灵活性高，这种复用可以在运行时动态进行，新对象可以动态的引用与成分对象类型相同的对象

## 总结

**1、** 找出应用里面可能需要变化的地方，把它们独立出来，不要和那些不需要变化的代码混在一起
**2、** 针对接口编程，而不是针对实现编程
**3、** 为了交互对象之间的松耦合设计而努力

## 例子
汽车按照能源分类有汽油汽车和电动汽车，按照颜色分类又可以分为白色、黑色、红色等等，如果需要同时考虑这两种分类，那么组合就有很多，如果我们用继承的方式可能就会出现6中子类，父类的任何改变都会导致子类发生变化不利于类的扩展与维护我们在用组合关系来解决这种问题
把颜色注入到我们的汽车类中完成聚合的方式
