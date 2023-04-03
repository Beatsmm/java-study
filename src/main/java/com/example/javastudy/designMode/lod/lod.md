# 迪米特法则

## 原理
迪米特法则又叫做最少知识原则，也就是说一个对象应当对其他对象尽可能少的了解，就像不和陌生人说话，如果两个类不需要彼此直接通信，那么这两个类就不应当发生直接的相互作用，如果其中的一个类需要调用另外一个类的某一个方法，可以通过第三者转发这个调用

## 目的
迪米特法则的目的在于降低类之间的耦合，由于每个类尽量减少对其他类的依赖，因此，很容易使得系统的功能模块能独立，相互之间不存在或者很少有依赖关系，迪米特法则不希望类之间建立直接的关系，如果真的有需要建立联系，也希望能通过它的抽象来转达，应用迪米特法则有可能造成一个后果就是系统中存在大量的中介类，这些类之所以存在完全是为了传递类之间的相互调用关系，无意中增加了系统的复杂性

## 总结
在面向对象编程中有一些众所周知的抽象概念，比如封装、内聚和耦合，理论上可以用来生成清晰的设计和良好的代码，虽然这些都是非常重要的概念，但他们不够实用，不能直接用于开发环境，这些概念是比较主观的，非常依赖于使用人的经验和知识，对于其他概念，比如单一职责、开闭原则等，迪米特法则的独特之处在于它简洁而准确的定义，它允许在编写代码时直接应用，几乎自动的应用了适当的封装、低内聚和松耦合

朋友圈的确定朋友条件

**1、** 当前对象本身(this)

**2、** 以参数的形式传入到当前对象方法中的对象，方法入参是一个对象这是这个对象和当前类是朋友

**3、** 当前对象的实例变量直接引用的对象：定义一个一个类，里面的属性引用了其他对象，那么这个实例和当前实例是朋友

**4、** 当前对象的实例变量如果是一个聚集，那么聚集中的元素也都是朋友：如果一个属性是对象，那么属性和对象里的元素都是朋友

**5、** 当前对象所创建的对象：任何一个对象，如果满足上面条件之一，就是当前对象的朋友，否则就是陌生人


## 例子
### 反例
***1、*** 只和直接的朋友交流


比如现在有一个功能是老板要通过arthas看所有接口的，这个例子中可以有三个类，老板Boss,Arthas,Interface

    public void command(Arthas arthas){
        List<Interface> list = new ArrayList<>();
        list.add(new Interface());
        list.add(new Interface());
        list.add(new Interface());
        list.add(new Interface());
        list.add(new Interface());
        arthas.count(list);
    }

在这个例子中，Boss的朋友有Arthas和Interface，Arthas是Boss里面的command方法的入参，另外一个是Interface，因为Boss在command方法中使用了Interface，现在Boss的直接朋友只有Arthas，因为直接朋友出现在成员变量，方法的输入输出参数中的类就是直接的朋友，Boss在command方法中创建了Interface数组，
和非直接的朋友Interface发生类交流，所以上述例子违反了迪米特法则，方法是类的一个行为，类竟然不知道自己的行为与其他类产生类依赖关系，这是不允许的，严重违反了迪米特法则

    public void command(Arthas arthas){
    arthas.count();
    }

把Boss类中的方法这样改造，然后再把List<Interface>依赖注入到Arthas里面，这样每个类都直接和朋友交流，有效减少了类之间的耦合

***2、***
减少对朋友的了解，也就是在一个类中尽量减少一个类对外报漏的方法，现在我需要找中介租房子，我的核心诉求就是租房子这个方法，但是中介需要寻找房源，联系房东，签合同这三个方法，有了中介以后我们只需要签订合同就好，别的都交给中介

    private Medium medium;
    public I(Medium medium){
        this.medium = medium;
    }
    private void rentHouse(){
        medium.findApartment();
        medium.contact();
        medium.sign();
    }
在上述例子中中介是我的直接好友，但是我对中介了解的太多了，不需要知道中介怎么去找的这个房子，怎么联系的房东，我们只需要关注签约动作就行，所以我们可以把联系房东和找房子的方法private，在用签约这个方法把这两个方法包起来,从而减少对
中介的了解

    private void rentHouse(){
        medium.sign();
    }

      private void findApartment(){
        System.out.println("寻找房源");
    }

    private void contact(){
        System.out.println("联系房东");
    }

    public void sign(){
        findApartment();
        contact();
        System.out.println("签订合同");
    }