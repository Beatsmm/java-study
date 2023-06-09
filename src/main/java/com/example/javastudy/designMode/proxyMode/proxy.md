# 代理模式
一种结构型的设计模式，代理控制着对于原对象的访问，并允许在请求提交给对象前后进行一些处理
  
## 原理：
 假如我们有一个消耗大量系统资源的很大的对象，而我们只是偶然需要使用它，并非频繁的使用，我们可以
  实现延迟初始化，就是在实际有需要的时候在创建这个对象，对象的所有客户端都要执行延迟初始化的代码，着可能会
  带来很多重复代码，在理想的情况下，我们可以把代码放在对象的类中，但这并非总是能实现，比如这个类是第三方封闭
  库的一部分，代理模式建议新建一个与原来服务对象接口相同的代理类，然后更新应用以将代理对象传递给所有原始对象的客户端，
  代理类收到客户端请求以后会创建实际的服务对象，并且把所有工作委派给它。

## 场景：
  1、延迟初始化：如果有一个偶尔使用的重量级服务对象，一直保持该对象运行会消耗系统资源，可以使用代理模式
  2、访问控制：如果只希望特定客户端使用服务对象，这个的对象可以是操作系统里面最重要的部分，而客户端则是启动的
  程序，如果只有管理员可以操作创建菜单，一般普通用户无法操作
  3、记录日志：比如为我们每一个接口的参数进行一个打印
  4、智能引用：可在没有客户端使用某一个重量级对象的时候立即销毁该对象

## 实现方式:
  1、一般创建出一个接口来实现代理和被代理对象的可交换性，从服务类中抽取接口并非总能成功，因为你需要对服务
  的所有客户端进行修改，让他们使用接口，备选计划就是将我们代理作为服务类的子类，这样代理就能继承服务的所有接口了
  2、创建代理类，代理对象负责创建服务对象，并且对这个服务对象整个生命周期进行管理
  3、代理在完成增强的工作后，再把工作委派给被代理的服务对象
  4、可以考虑建一个构造方法来判断用户（客户端）获取的是代理还是实际服务，我们可以在代理类中创建一个简单的静态方法，
  也可以创建一个完整的工厂方法
  
## 优点：
  1、可以在用户没有察觉的时候控制被代理对象
  2、如果请求客户端对被代理对象的生命周期没有特殊的要求，我们可以对生命周期进行管理
  3、开闭原则，可以在不对服务或客户端做出修改的情况下创建新代理
  
## 缺点：
  1、代码会变的复杂，需要新建许多类
  2、服务响应可能会延迟
  