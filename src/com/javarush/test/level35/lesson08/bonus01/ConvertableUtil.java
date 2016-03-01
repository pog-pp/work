package com.javarush.test.level35.lesson08.bonus01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <T,Key,K extends Convertable<Key>> Map<T, K> convert(List<K> list) {
        Map<T, K> result = new HashMap<T, K>();
        for (int i = 0; i < list.size(); i++) {
            result.put((T) list.get(i).getKey(), list.get(i));
        }
        return result;
    }
}
