package com.javarush.test.level07.lesson12.home03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Максимальное и минимальное числа в массиве
Создать массив на 20 чисел. Заполнить его числами с клавиатуры. Найти максимальное и минимальное числа в массиве.
Вывести на экран максимальное и минимальное числа через пробел.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[20];
        int  maximum;
        int  minimum;
        for (int i = 0; i < 20; i++){
            arr[i] = Integer.parseInt(reader.readLine());
        }
        maximum = arr[0];
        minimum = arr[0];
        for (int i = 0; i < 20; i++){
            if (arr[i]>maximum) maximum=arr[i];
            if (arr[i]<minimum) minimum=arr[i];
        }
        //напишите тут ваш код


        System.out.println(maximum);
        System.out.println(minimum);
    }
}
