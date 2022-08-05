package com.cq.studyprocess.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 复制工具类
 *
 * @author 程崎
 * @since 2022/08/05
 */
public class CopyUtil {

    /**
     * 单体复制
     *
     * @param source 源
     * @param clazz  clazz
     * @return {@link T}
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /**
     * 列表复制
     *
     * @param source 源
     * @param clazz  clazz
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> copyList(List<?> source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)) {
            for (Object c : source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }
}