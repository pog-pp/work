package com.javarush.test.level36.lesson08.task01;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/* Использование TreeSet
Первым параметром приходит имя файла: файл1.
файл1 содержит только буквы латинского алфавита, пробелы, знаки препинания, тире, символы перевода каретки.
Отсортировать буквы по алфавиту и вывести на экран первые 5 различных букв в одну строку без разделителей.
Если файл1 содержит менее 5 различных букв, то вывести их все.
Буквы различного регистра считаются одинаковыми.
Регистр выводимых букв не влияет на результат.
Закрыть потоки.

Пример 1 данных входного файла:
zBk yaz b-kN
Пример 1 вывода:
abkny

Пример 2 данных входного файла:
caAC
A, aB? bB
Пример 2 вывода:
abc

Подсказка: использовать TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName =  args[0];

        Set<Character> set = new TreeSet<>();

        FileReader read = new FileReader(fileName);
        char[] array = new char[(int) new File(fileName).length()];
        char[] mas = new char[array.length];
        read.read(array);
        read.close();
        //System.out.println(Arrays.toString(array));

        for (int i = 0; i < array.length; i++) {
            mas[i] = Character.toLowerCase(array[i]);

        }
        //System.out.println(Arrays.toString(mas));

        for (int i = 0; i < mas.length; i++) {
            if (Character.isLetter(mas[i]))
                set.add(mas[i]);

        }
        //System.out.println(set);
        if (set.size()>=5)
        {
            int i =1;
            for (Character it: set) {
                if (i<=5)
                {
                    System.out.print(it.charValue());
                }
                else
                    break;
                i++;

            }
        }
        else
        {
            for (Character it: set) {

                System.out.print(it.charValue());

            }
        }
    }
}
