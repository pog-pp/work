package com.javarush.test.level18.lesson10.home01;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв)
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        String name = args[0];
        int count = 0;
        FileInputStream fis = new FileInputStream(name);
        while(fis.available() > 0)
        {
            int data = fis.read();
            if ((data >= Integer.valueOf('A') && data <= Integer.valueOf('Z'))
                    || (data >= Integer.valueOf('a') && data <= Integer.valueOf('z')))
            {
                count++;
            }

        }
        fis.close();
        System.out.println(Integer.valueOf(count));

    }
}
