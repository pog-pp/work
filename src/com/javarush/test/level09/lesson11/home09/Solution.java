package com.javarush.test.level09.lesson11.home09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

public class Solution
{
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String,Cat> map = new HashMap<String, Cat>();
        Cat cat1= new Cat("Vasilyy1"); map.put("Vasilyy1",cat1);
        Cat cat2= new Cat("Vasilyy2"); map.put("Vasilyy2",cat2);
        Cat cat3= new Cat("Vasilyy3"); map.put("Vasilyy3",cat3);
        Cat cat4= new Cat("Vasilyy4"); map.put("Vasilyy4",cat4);
        Cat cat5= new Cat("Vasilyy5"); map.put("Vasilyy5",cat5);
        Cat cat6= new Cat("Vasilyy6"); map.put("Vasilyy6",cat6);
        Cat cat7= new Cat("Vasilyy7"); map.put("Vasilyy7",cat7);
        Cat cat8= new Cat("Vasilyy8"); map.put("Vasilyy8",cat8);
        Cat cat9= new Cat("Vasilyy9"); map.put("Vasilyy9",cat9);
        Cat cat0= new Cat("Vasilyy0"); map.put("Vasilyy0",cat0);
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map)
    {
        Set<Cat> cats = new HashSet<Cat>();
        for (Map.Entry<String,Cat> pair: map.entrySet() ) {
            Cat a = pair.getValue();
            cats.add(a);
        }
        return cats;
    }

    public static void printCatSet(Set<Cat> set)
    {
        for (Cat cat:set)
        {
            System.out.println(cat);
        }
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }


}
