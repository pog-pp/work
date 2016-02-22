package com.javarush.test.level22.lesson09.task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* Обращенные слова
В методе main с консоли считать имя файла, который содержит слова, разделенные пробелами.
Найти в тексте все пары слов, которые являются обращением друг друга. Добавить их в result.
Порядок слов first/second не влияет на тестирование.
Использовать StringBuilder.
Пример содержимого файла
рот тор торт о
о тот тот тот
Вывод:
рот тор
о о
тот тот
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();
    public static List<String> yalla = new ArrayList<>();
    public static Pair pr;


    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String fileName = r.readLine();
        r.close();
        BufferedReader fr = new BufferedReader(new FileReader(fileName));
        while (fr.ready()){
            String[] arr = fr.readLine().split(" ");
            for (String s : arr)
            {
                yalla.add(s);
            }
        }
        fr.close();
        List<String> copy = yalla;

        for (String str : yalla){

            for (String cop : copy){
                StringBuilder s2 = new StringBuilder(cop);
                System.out.println(s2.reverse());
                if (s2.reverse().equals(str)) {
                    pr.first = cop;
                    pr.second = str;
                    result.add(pr);
                }
            }

        }
        for (Pair p : result) System.out.println(p.toString());
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
