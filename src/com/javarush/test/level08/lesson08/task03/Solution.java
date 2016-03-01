package com.javarush.test.level08.lesson08.task03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* Одинаковые имя и фамилия
Создать словарь (Map<String, String>) занести в него десять записей по принципу «Фамилия» - «Имя».
Проверить сколько людей имеют совпадающие с заданным имя или фамилию.
*/

public class Solution
{
    public static HashMap<String, String> createMap()
    {
        //напишите тут ваш код
        HashMap<String, String> list = new HashMap<String,String>();
        list.put("yana","byana");
        list.put("yan","byana");
        list.put("ya","byana2");
        list.put("y","byana3");
        list.put("janna","byana3");
        list.put("jann","byana4");
        list.put("jan","byana5");
        list.put("ja","byana6");
        list.put("j","byana7");
        list.put("jana","byana");
        return list;

    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name)
    {
        //напикодшите тут ваш
        int count = 0;
        for(Map.Entry<String,String> e : map.entrySet())
        {
            if (e.getValue().equals(name)) count++;
        }
        return count;

    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName)
    {
        //напишите тут ваш код
        int count = 0;
        for(Map.Entry<String,String> e : map.entrySet())
        {
            if (e.getKey().equals(lastName)) count++;
        }
        return count;

    }
}
