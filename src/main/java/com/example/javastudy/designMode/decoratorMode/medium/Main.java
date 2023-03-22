package com.example.javastudy.designMode.decoratorMode.medium;



public class Main {


    /**
     * 使用场景：动态的方式替代继承，有些类不支持被继承比如用final修饰的类
     * 例子：我们在项目中需要对一些敏感数据进行加密，又不想改变原有对象的时候可以使用装饰器模式
     * 另外：装饰者模式与代理模式很像，区别在于装饰者模式不会改变原有的对象，只是在原有的对象上套了一层，而代理模式可能会返回给你
     * 一个代理对象
     */
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("out/OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("out/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}



