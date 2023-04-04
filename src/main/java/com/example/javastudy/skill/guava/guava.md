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
***2、*** value不可重复,BiMap的底层继承了Map,我们知道在Map中的key是不允许重复的,而双向的BiMap中的key和value可以认为处于等价地位,因此在这个基础之上增加了限制,value也是不可重复的
```
        HashBiMap<String, String> repeatKeyOrValue = HashBiMap.create();
        repeatKeyOrValue.put("key1", "value1");
        //repeatKeyOrValue.put("key2", "value1");
        // 如果非要把新的key映射到已有的value上,那么也可以使用forcePut方法强制替换掉原有的key
        repeatKeyOrValue.forcePut("key2", "value1");
        System.out.println(repeatKeyOrValue);
```
