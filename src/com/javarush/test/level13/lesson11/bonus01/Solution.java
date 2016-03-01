package com.javarush.test.level13.lesson11.bonus01;

/* Сортировка четных чисел из файла
1. Ввести имя файла с консоли.
2. Прочитать из него набор чисел.
3. Вывести на консоль только четные, отсортированные по возрастанию.
Пример ввода:
5
8
11
3
2
10
Пример вывода:
2
8
10
*/

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        // напишите тут ваш код
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String file = read.readLine();
        BufferedReader r = new BufferedReader(new BufferedReader(new FileReader(file)));
        int n = 0;

        String s;
        ArrayList<Integer> l = new ArrayList<Integer>();
        ArrayList<Integer> chet = new ArrayList<Integer>();

        while ((s = r.readLine()) != null) {
            l.add(Integer.parseInt(s));

        }
        n = 0;
        for (int to : l){
            if (to % 2 == 0) {
                chet.add(to);
            }
        }
        n = 0;
        int[] x = new int[chet.size()];
        for (int ch : chet){
            x[n] = ch;
            n++;
        }

        for (int i = 0; i < x.length; i++){
            for (int j = 0; j < x.length - i - 1; j++){
                if (x[j] > x[j+1]){
                    int t = x[j + 1];
                    x[j + 1]  = x[j];
                    x[j] = t;
                }
            }
        }
        for (int i = 0; i < x.length; i++ ){
            System.out.println(x[i]);
        }

    }
}
