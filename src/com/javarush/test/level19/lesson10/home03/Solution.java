package com.javarush.test.level19.lesson10.home03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* Хуан Хуанович
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя день месяц год
где [имя] - может состоять из нескольких слов, разделенных пробелами, и имеет тип String
[день] - int, [месяц] - int, [год] - int
данные разделены пробелами

Заполнить список PEOPLE импользуя данные из файла
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Иванов Иван Иванович 31 12 1987
Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException
    {
        String file1 = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(file1));
        String line = null;
        List<String> lines = new ArrayList<String>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        for (String str : lines){
            String[] stringArray = str.split(" ");
            String name = "";
            for (int i = 0; i < stringArray.length-3; i++) {
                if (i == stringArray.length-4)
                    name = name+stringArray[i];
                else
                    name = name+stringArray[i]+" ";
            }
            int year = Integer.parseInt(stringArray[stringArray.length-1]);
            int month = Integer.parseInt(stringArray[stringArray.length-2])-1;
            int day = Integer.parseInt(stringArray[stringArray.length-3]);
            Date birthDay = new GregorianCalendar(year, month, day).getTime();
            PEOPLE.add(new Person(name, birthDay));
        }
    }

}
