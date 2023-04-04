package com.example.javastudy.skill.guava;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class GuavaTable {

    public static void main(String[] args) {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("12", "23", 1);
        table.put("11", "24", 2);

        Integer value = table.get("12", "23");
        System.out.println(value);
        // 获取第一个key的集合
        Set<String> stringRow = table.rowKeySet();
        System.out.println(stringRow);
        // 获取第二个key的集合
        Set<String> stringColumn = table.columnKeySet();
        System.out.println(stringColumn);
        // value集合
        Collection<Integer> values = table.values();
        System.out.println(values);

        // 计算key对应的所有value的和
        for (String key : table.rowKeySet()) {
            Set<Map.Entry<String, Integer>> rows = table.row(key).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> row : rows) {
                total += row.getValue();
            }
            System.out.println(key + ": " + total);
        }

        // 转换rowKey和columnKey
        Table<String, String, Integer> transpose = Tables.transpose(table);
        Set<Table.Cell<String, String, Integer>> cells = transpose.cellSet();
        cells.forEach(cell ->
                System.out.println(cell.getRowKey()+","+cell.getColumnKey()+":"+cell.getValue())
        );

        // 转换为嵌套Map
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        System.out.println(rowMap);
        System.out.println(columnMap);
    }

}
