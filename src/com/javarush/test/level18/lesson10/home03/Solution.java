package com.javarush.test.level18.lesson10.home03;

/* Два в одном
Считать с консоли 3 имени файла
Записать в первый файл содержимого второго файла, а потом дописать в первый файл содержимое третьего файла
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String f1 = r.readLine();
        String f2 = r.readLine();
        String f3 = r.readLine();

        FileInputStream f21 = new FileInputStream(f2);
        FileOutputStream f12 = new FileOutputStream(f1);
        FileInputStream f31 = new FileInputStream(f3);
        while (f21.available() > 0){
            int data = f21.read();
            f12.write(data);
        }
        while (f31.available() > 0 ){
            int data = f31.read();
            f12.write(data);
        }
        f12.close();
        f21.close();
        f31.close();
        r.close();


    }
}
