package com.javarush.test.level16.lesson03.task03;

import java.util.ArrayList;
import java.util.List;

/* Список и нити
В методе main добавить в статический объект list 5 нитей SpecialThread - различных объектов.
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {

        SpecialThread obj1 = new SpecialThread();
        SpecialThread obj2 = new SpecialThread();
        SpecialThread obj3 = new SpecialThread();
        SpecialThread obj4 = new SpecialThread();
        SpecialThread obj5 = new SpecialThread();

        list.add(new Thread(obj1));
        list.add(new Thread(obj2));
        list.add(new Thread(obj3));
        list.add(new Thread(obj4));
        list.add(new Thread(obj5));

    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's run method inside SpecialThread");
        }
    }
}
