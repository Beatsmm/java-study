# Guava

## Guava-Map
Guava中封装了一些关于Map的骚操作

***1.*** Table双键Map

在Java里面只允许一个key和一个value存在,但是在guava中的Table允许一个value存在两个key,Table中的两个key分别被称为rowKey和columKey,也就是行和列Table<Key, Key, Value> 类似于 HashMap<String, HashMap<String, Integer>>  
```
// 获取第一个key的集合  
Set<String> stringRow = table.rowKeySet();
```  
```
// 获取第二个key的集合
Set<String> stringColumn = table.columnKeySet(); 
```
```
// value集合  
Collection<Integer> values = table.values();
```
```
// 计算key对应的所有value的和
for (String key : table.rowKeySet()) {
    Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
    int total = 0;
    for (Map.Entry<String, Integer> row : rows) {
    total += row.getValue();
    }
    System.out.println(key + ": " + total);
} 
```
```
// 转换rowKey和columnKey
        Table<String, String, Integer> transpose = Tables.transpose(table);
        Set<Table.Cell<String, String, Integer>> cells = transpose.cellSet();
        cells.forEach(cell ->
                System.out.println(cell.getRowKey()+","+cell.getColumnKey()+":"+cell.getValue())
        );
```
```
// 转换为嵌套Map
Map<String, Map<String, Integer>> rowMap = table.rowMap();
Map<String, Map<String, Integer>> columnMap = table.columnMap();
System.out.println(rowMap);
System.out.println(columnMap);
```

***2、*** BiMap -- 双向Map
在普通Map中,如果想要根据value查找对应的key,没什么简便的办法,无论是使用for循环还是迭代器,都需要遍历整个Map,以循环keySet的方式为例：
```
    Map<String, String> hashMap = new HashMap<>();
        hashMap.put("test1","test1Value");
        hashMap.put("test2","test2Value");
        hashMap.put("test3","test3Value");
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).equals("test2Value")){
                System.out.println("找到了value=test2Value对应的key="+key);
            }
        }
```
而guava中的BiMap提供了一种key和value双向关联的数据结构
```
HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("zhangsan","China");
        biMap.put("lisi","UK");
        biMap.put("wangwu","USA");
        System.out.println(biMap.get("zhangsan"));
        BiMap<String, String> inverse = biMap.inverse();
        // 使用value获取key
        System.out.println(inverse.get("USA"));
```
注意！！！经过inverse的BiMap,如果对转化后的key在次进行put会覆盖掉原先的biMap  
value不可重复,BiMap的底层继承了Map,我们知道在Map中的key是不允许重复的,而双向的BiMap中的key和value可以认为处于等价地位,因此在这个基础之上增加了限制,value也是不可重复的
```
        HashBiMap<String, String> repeatKeyOrValue = HashBiMap.create();
        repeatKeyOrValue.put("key1", "value1");
        //repeatKeyOrValue.put("key2", "value1");
        // 如果非要把新的key映射到已有的value上,那么也可以使用forcePut方法强制替换掉原有的key
        repeatKeyOrValue.forcePut("key2", "value1");
        System.out.println(repeatKeyOrValue);
```
***3、*** Multimap-多值Map  
java中的Map维护的是键值一对一的关系,如果要将一个映射到多个值上,那么就只能把值的内容设为集合形式,简单实现
```
        Map<String, List<Integer>> javaMapping = new HashMap<>();
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        javaMapping.put("day", list);
        System.out.println(javaMapping);
```
guava中的Multimap提供了讲一个键映射到多个值的形式,使用起来无需定义复杂的内层集合,可以像使用普通的Map一样使用它
```
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 3);
        System.out.println(multimap);
```
```
//1、获取值的集合 可以创建ListMultimap、TreeMultimap、 HashMultimap
List<Integer> listInt = multimap.get("day");
// get方法会返回一个非null的集合,但是这个集合的内容可能是空的,看一下下面的例子
List<Integer> testNullKey = multimap.get("nullKey");
System.out.println(testNullKey); // []
```
操作get后的集合：和BiMap的使用类似,使用get方法返回的集合也不是一个独立的对象,可以理解为集合视图的关联,对这个新集合的操作仍然会作用于原始的Multimap上
```
        List<Integer> listDay = multimap.get("day");
        listDay.remove(0);
        List<Integer> listMonth = multimap.get("month");
        listMonth.add(12);
        System.out.println(multimap); // {month=[3, 12], day=[2, 3]}
```
转换为Map：使用asMap方法可以将Multimap转换为Map<K, Collection>的形式,同样这个Map也可以看做一个关联的视图,在这个Map上的操作会作用域原始的Multimap
```
        Map<String, Collection<Integer>> reverseMap = multimap.asMap();
        for (String key : reverseMap.keySet()) {
            System.out.println(key+" : "+reverseMap.get(key));
        }
        reverseMap.get("day").add(20);
        System.out.println(reverseMap);
```
数量问题：Multimap中的数量在使用中也有些容易混淆的地方,因为size方法返回的是所有key到value的映射
```
        System.out.println(multimap.size()); //5
        System.out.println(multimap.entries().size());//5
        for (Map.Entry<String, Integer> entry : multimap.entries()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
```
***4、*** RangeMap - 范围Map  
假设我们现在要对用户的年龄进行一个分类
```
closedOpen-左闭右开[) closed-左闭右闭[] openClosed-左开右闭 (]
TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0, 18),"未成年");
        rangeMap.put(Range.closed(18, 30),"青年");
        rangeMap.put(Range.openClosed(30, 60),"壮年");
        rangeMap.put(Range.atLeast(60),"老年");
        System.out.println(rangeMap.get(15)); // 未成年
        System.out.println(rangeMap.get(18)); // 青年
        System.out.println(rangeMap.get(25)); // 青年
        System.out.println(rangeMap.get(30)); // 青年
        System.out.println(rangeMap.get(35)); // 壮年
        System.out.println(rangeMap.get(60)); // 老年
        System.out.println(rangeMap.get(65)); // 老年
```
***5、*** ClassToInstanceMap-实例Map  
ClassToInstanceMap是一个比较特殊的Map,它的键是Class,而这个值是这个Class对应的实例对象
```
    MutableClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        User user = new User("张三",18);
        Goods goods = new Goods("鼠标", new BigDecimal("100"));
        instanceMap.put(User.class, user);
        instanceMap.put(Goods.class, goods);
        User instanceUser = instanceMap.getInstance(User.class);
        if (instanceUser == user) System.out.println(true);
```
其实这跟Map<Class, Object>的区别就是在取出对象的时候省去了复杂的强制类型转换,避免了手动进行类型转换的错误  
同样可以对类型起到约束的作用,value要符合key所对应的类型,HashMap和TreeMap都集成了Map父类,但是如果想放入其他类型就会报错,如果想缓存对象又不想做复杂的类型校验,可以使用ClassToInstanceMap
```
    MutableClassToInstanceMap<Map> classToInstanceMap = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();
        classToInstanceMap.put(HashMap.class, hashMap);
        classToInstanceMap.put(TreeMap.class, treeMap);
//        classToInstanceMap.put(ArrayList.class, list); // 编译不通过
```
