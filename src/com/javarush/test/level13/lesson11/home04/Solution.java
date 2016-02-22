package com.javarush.test.level13.lesson11.home04;

/* Запись в файл
1. Прочесть с консоли имя файла.
2. Считывать строки с консоли, пока пользователь не введет строку "exit".
3. Вывести абсолютно все введенные строки в файл, каждую строчку с новой стороки.
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file1 = r.readLine();
        FileOutputStream w = new FileOutputStream(file1);
        while(true){
            String str = r.readLine();

            w.write((str+"\r\n").getBytes());
            if ("exit".equals(str)) break;
        }
        w.close();
        r.close();


    }
}
