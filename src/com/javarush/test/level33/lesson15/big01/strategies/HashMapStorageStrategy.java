package com.javarush.test.level33.lesson15.big01.strategies;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paul on 2/10/16.
 */
public class HashMapStorageStrategy implements StorageStrategy
{
    private HashMap<Long, String> data = new HashMap<>();
    @Override
    public boolean containsKey(Long key)
    {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value)
    {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value)
    {
        data.put(key,value);
    }

    @Override
    public Long getKey(String value)
    {
        for (Map.Entry<Long,String> pair : data.entrySet()){
            if (pair.getValue().equals(value)){
                return pair.getKey();
            }
        }

        return null;
    }

    @Override
    public String getValue(Long key)
    {
        return data.get(key);
    }
}
