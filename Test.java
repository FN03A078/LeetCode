package com.LeetCode;

import com.LeetCode.com.shan.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 10:22 2022/6/24
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(3, "Spring Framework");
        map.put(4, "MyBatis framework");
        map.put(5, "Java中文社群");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + "+" + entry.getValue());
        }

        System.out.println("-------------------------------");
        Iterator<Integer> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()){
            Integer key = iterator1.next();
            System.out.println(key + "+" + map.get(key));
        }

        System.out.println("-------------------------------");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "+" + entry.getValue());
        }

        System.out.println("-------------------------------");

        for (Integer integer : map.keySet()) {
            System.out.println(integer + "+" + map.get(integer));
        }

        System.out.println("-------------------------------");

        map.forEach((key,value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        System.out.println("-------------------------------");

        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

    }


}
