# 模版方法模式
## 原理：
主要思想是定一个操作的一系列步骤，对于某些暂时确定不下来的步骤就留给子类去实现
## 场景：
假如现在有一个需求，需要根据前端传过来的类型来进行一个排序，类型可能是int，可能
是String、可能是BigDecimal，每种类型的排序方式都不相同，先判断是什么类型的，然后
再去传入对应的方法，如果每次都需要进行判断类型和排序就可以把这两个方法抽取出来，然后
子类还可以去扩展一些功能
## 实现方式
1、抽取公共的方法或者步骤在上诉例子就是抽取判断类型和排序的共有方法
2、然后根据不同的类型由子类去实现自己类型的排序规则

## 优点
可以将重复代码提取到一个父类中

## 缺点
模版方法中步骤越多，维护工作可能就会越困难

## easy例子
假设我们开发了一个从数据库读取设置的类，由于数据库读取数据比较慢，我们可以把从数据库读取出来的
数据缓存一下，这样下一次读取同样的数据就不需要访问数据库了，这块的缓存采用本地缓存+redis缓存

## medium例子
我们公司需要对接国外各个社交平台的登陆、发消息、退出三个步骤，首先我们创建一个父类来定义整个流程
在抽取出登陆、发消息、退出三个抽象方法由子类去实现