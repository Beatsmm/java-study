package com.example.javastudy.skill.util.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) {
        Test  test = new Test();
        test.testPerformance();
    }


    public void testPerformance() {
        int count = 1000000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            BeanCopier copier = BeanCopier.create(MA.class, MA.class, false);
            MA source = new MA();
            source.setIntP(42);
            MA target = new MA();
            copier.copy(source, target, null);
        }
        long end = System.currentTimeMillis();
        System.out.println("BeanCopier cost " + (end - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            MA source = new MA();
            source.setIntP(42);
            MA target = new MA();
            target.setIntP(source.getIntP());
        }
        end = System.currentTimeMillis();
        System.out.println("set cost " + (end - start) + " ms");



        start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            MA source = new MA();
            source.setIntP(42);
            MA target = new MA();
            BeanUtils.copyProperties(target, source);
        }
        end = System.currentTimeMillis();
        System.out.println("BeanUtils cost " + (end - start) + " ms");
    }
}
