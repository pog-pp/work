package com.javarush.test.level18.lesson10.home04;

/* Объединение файлов
Считать с консоли 2 имени файла
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String f1 = r.readLine();
        String f2 = r.readLine();
        r.close();
        FileInputStream file1 = new FileInputStream(f1);
        FileInputStream file2 = new FileInputStream(f2);

        byte[] sec = new byte[file2.available()];
        file2.read(sec);
        file2.close();

        byte[] fst = new byte[file1.available()];
        file1.read(fst);
        file1.close();

        FileOutputStream f11 = new FileOutputStream(f1);
        f11.write(sec);
        f11.write(fst);
        f11.close();



        ;

    }
}
