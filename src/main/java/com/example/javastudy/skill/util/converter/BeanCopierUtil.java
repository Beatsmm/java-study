package com.example.javastudy.skill.util.converter;


import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
public class BeanCopierUtil {

    public static void copy(Object from, Object to) {
        BeanCopier copier = BeanCopier.create(from.getClass(), to.getClass(), false);
        copier.copy(from, to, null);
    }

    public static <T> T copy(Object from, Supplier<T> to,Class<T> clazz) {
        BeanCopier copier = BeanCopier.create(from.getClass(), clazz, false);
        T t = to.get();
        copier.copy(from, t, null);
        return t;
    }

    /**
     * 集合数据的拷贝
     * @param sources: 数据源类
     * @param target: 目标类::new(eg: UserVO::new)
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }


    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, BeanCopierUtilCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copy(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }
}
