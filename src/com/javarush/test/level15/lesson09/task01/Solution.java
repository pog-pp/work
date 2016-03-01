package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();
    static  {
        labels.put(5.5,"string");
        labels.put(6.28,"cat");
        labels.put(1.2,"tom");
        labels.put(1.4,"dog");
        labels.put(4.8,"Jerry");}

    public static void main(String[] args) {

        System.out.println(labels);
    }
}
