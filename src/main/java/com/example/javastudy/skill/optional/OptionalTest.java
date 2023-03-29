package com.example.javastudy.skill.optional;


import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        //1、empty()
        System.out.println(Optional.empty()); // Optional.empty
        //2、 of()
        String testOf = "test";
        System.out.println(Optional.of(testOf)); //Optional[test] 如果of(null)会导致NPE
        //3、 ofNullable()
        String testOfNullable = null;
        System.out.println(Optional.ofNullable(testOfNullable)); // Optional.empty
        String testNotNullOfNullable = "test";
        System.out.println(Optional.ofNullable(testNotNullOfNullable));// Optional[test]
        //4、get()
        String testNullGet = null;
        Optional<String> testNullGet1 = Optional.ofNullable(testNullGet);
        //System.out.println(testNullGet1.get()); // NoSuchElementException No value present
        String testNotNullGet = "not null";
        System.out.println(Optional.ofNullable(testNotNullGet).get()); // not null
        // 5、isPresent()
        String testNullIsPresent = null;
        System.out.println(Optional.ofNullable(testNullIsPresent).isPresent()); // false
        String testNotNullIsPresent = "present";
        System.out.println(Optional.ofNullable(testNotNullIsPresent).isPresent()); // true
        // 6、ifPresent(Consumer<? super T> consumer)
        String testNullIfPresent = null;
        Optional.ofNullable(testNullIfPresent).ifPresent( item -> System.out.println(item)); // 不打印任何东西
        String testNotNullIfPresent = "testNotNullIfPresent";
        Optional.ofNullable(testNotNullIfPresent).ifPresent( item -> System.out.println(item)); // testNotNullIfPresent
        // 7、filter(Predicate<? super T> predicate)
        User user7 = new User();
        user7.setName("monster");
        Optional<User> optionalUser7 = Optional.ofNullable(user7);
        Optional<User> monster = optionalUser7.filter(v -> v.getName().equals("monster"));//Optional[User(name=monster, age=null)]
        System.out.println(monster);
        Optional<User> shit = optionalUser7.filter(v -> v.getName().equals("shit")); //Optional.empty
        System.out.println(shit);
        // 8、map(Function<? super T,? extends U> mapper)
        User userEightNull = null;
        Optional<String> optionalEightNull = Optional.ofNullable(userEightNull).map(OptionalTest::getMap);
        System.out.println(optionalEightNull); // Optional.empty
        User userEightNotNull = new User("demos",12);
        Optional<String> optionalEightNotNull = Optional.ofNullable(userEightNotNull).map(OptionalTest::getMap);
        System.out.println(optionalEightNotNull.get()); // demos
        // 9、flatMap(Function<? super T, Optional> mapper)
        User userNineNull = null;
        Optional<String> userNineOptional = Optional.ofNullable(userNineNull).flatMap(OptionalTest::getFlatMap);
        System.out.println(userNineOptional);// Optional.empty
        User userNineNotNull = new User("demos",25);
        Optional<String> userNineNotOptional = Optional.ofNullable(userNineNotNull).flatMap(OptionalTest::getFlatMap);
        System.out.println(userNineNotOptional);//Optional[demos]
        // 10、orElse(T other)
        String value1 = "2";
        String orElse1 = Optional.ofNullable(value1).orElse("1");
        System.out.println(orElse1);  //2
        String value2 = null;
        String orElse2 = Optional.ofNullable(value2).orElse("1");
        System.out.println(orElse2);  //1
        // 11、orElseGet(Supplier<? extends T> other)
        String valueOrElseGet = null;
        String yes = Optional.ofNullable(valueOrElseGet).orElseGet(OptionalTest::get);
        System.out.println(yes); // 123

        // 12、orELSEThrow
        String orElseThrow = null;
        String orElse = Optional.ofNullable(orElseThrow).orElseThrow(() -> new RuntimeException("不存在值"));
        System.out.println(orElse);
    }




    public static String getMap(User user){
        return user.getName();
    }

    public static Optional<String> getFlatMap(User user){
        return Optional.ofNullable(user).map(User::getName);
    }

    public static String get(){
        return "123";
    }

}
