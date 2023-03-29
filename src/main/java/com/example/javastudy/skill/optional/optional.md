# Optional
## 简介
optional是jdk8提供的新特性，它可以让我们的代码变得更加优雅，在我们
日常开发中空指针问题一直是一个头疼的问题，特别在调第三方接口的时候，如果
不知道对象的规约的时候，我们在取值的时候无法知道哪些值能为空，哪些不能为空，
如果我们谨慎一点，可能会对每一个值进行判空处理，但是会充斥着大量的if语句很不
雅观

## 方法
1. ***empty()***:返回一个空的***Optional***对象  ***Optional.empty***

2. ***of(T value)***:参数传入一个对象，返回一个***Option***对象，***value***不能为空，如果为空就抛出空指针异常

3. ***ofNullable(T value)***,参数传入一个对象，可以为空，如果为空，将返回一个空的***Optional***对象,如果不是空就返回一个不为空的Optional对象

4. ***get()*** 获取***Optional***的值，这个值也就是我们的值，***Optional***相当于一个外壳

5. ***isPresent()*** 判断***Optional***对象中是否有值，如果有值，返回***true***，没有返回***false*** 

6. ***ifPresent(Consumer<? super T> consumer)***，参数是一个函数式接口，无返回值，会将Optional中的参数传递到***ifPresent***中

7. ***filter(Predicate<? super T> predicate)***是一个***Predicate***函数接口，会把***Optional***中的值作为参数传入，如果符合规则，那么返回一个***Optional***对象，否则返回一个空的***Optional***对象

8. ***map(Function<? super T, ? extends U> mapper)***参数是一个***Function***函数式接口，会把***Optional***中的值作为参数传递到***map***中，如果传入的值是空的，则返回空的***Optional***对象，相当于***Optional.empty()***，如果不是空的我们可以返回一个可以描述结果的返回值

9. ***flatMap(Function<? super T, Optional> mapper)*** 如果***Optional***中的值存在，那么返回一个基于***Optional***的值，如果***Optional***中的值不存在，则返回一个空对象***Optional***对象，相当于***Optional.empty()***，与***map***不同，***map***返回的是一个值，而***flatMap***返回一个基于***Optional***的值

10. ***orElse(T other)*** 如果***Optional***中的值不为空，则返回Optional中的值，如果为空，则返回***other***值，

11. ***orElseGet(Supplier<? extends T> other)*** 如果***Optional***中存在值，则返回值，否则返回***other***调用的结果

12. ***orElseThrow(Supplier<? extends X> exceptionSupplier)*** 如果***Optional***中的值存在，则返回值，值不存在，抛出异常函数***Supplier***中的异常





