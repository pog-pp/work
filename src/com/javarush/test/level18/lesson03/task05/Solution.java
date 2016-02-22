package com.javarush.test.level18.lesson03.task05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;




/* Сортировка байт
Ввести с консоли имя файла
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран
Закрыть поток ввода-вывода

Пример байт входного файла
44 83 44

Пример вывода
44 83
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        FileInputStream inputStream = new FileInputStream(file);
        int com = 0;
        byte b = 0;
        if (inputStream.available() > 0)
        {
            //читаем весь файл одним куском
            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);
            for (Byte bb : buffer)
            {
                if (bb == 44) com++;
            }
            System.out.println(count);
        }


        r.close();
        inputStream.close();
    }
}
