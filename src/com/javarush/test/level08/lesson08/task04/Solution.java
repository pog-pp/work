package com.javarush.test.level08.lesson08.task04;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Удалить всех людей, родившихся летом
Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: «фамилия» - «дата рождения».
Удалить из словаря всех людей, родившихся летом.
*/

public class Solution
{
    public static HashMap<String, Date> createMap()
    {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Сталин", new Date("JUNE 1 1980"));
        map.put("Батистута", new Date("JUNE 1 1980"));
        map.put("Матиз", new Date("SEPTEMBER 1 1980"));
        map.put("Борис", new Date("FEBRUARY 1 1980"));
        map.put("Новел", new Date("MARCH 1 1980"));
        map.put("Виолла", new Date("MAY 1 1980"));
        map.put("Шокин", new Date("MAY 1 1980"));
        map.put("Феликс", new Date("JUNE 1 1980"));
        map.put("Эдмунд", new Date("JUNE 1 1980"));

        return map;

        //напишите тут ваш код

    }

    public static void removeAllSummerPeople(HashMap<String, Date> map)
    {
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();

        while (iterator.hasNext())
        {
            int month = iterator.next().getValue().getMonth() + 1;
            if (6 <= month && month <= 8)
            {
                iterator.remove();
            }
        }//напишите тут ваш код

    }
}
