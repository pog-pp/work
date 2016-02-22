package com.javarush.test.level19.lesson05.task02;

/* Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    static int counter = 0;
    static char currentChar = ' ';
    public static void main(String[] args) throws IOException
    {


        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        FileReader reader = new FileReader(r.readLine());
        //FileWriter writer = new FileWriter(br.readLine());

        String text = "";
        while (reader.ready())
        {
            currentChar = (char)reader.read();
            //readWorld(reader);
            text = text+currentChar;

        }

        String replacedString = text.replaceAll("\\p{P}"," ").toLowerCase();
        replacedString=replacedString.replaceAll("\\s"," ");

        String splitString[] = replacedString.split(" ");
        for (String x : splitString) {
            if (x.equals("world")) {
                counter++;
            }
        }

        System.out.println(counter);
        reader.close();
        r.close();

    }
}
