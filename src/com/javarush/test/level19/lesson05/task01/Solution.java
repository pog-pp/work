package com.javarush.test.level19.lesson05.task01;

/* Четные байты
Считать с консоли 2 имени файла.
Вывести во второй файл все байты с четным индексом.
Пример: второй байт, четвертый байт, шестой байт и т.д.
Закрыть потоки ввода-вывода.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        FileInputStream fis1 = new FileInputStream(file1);
   //     FileInputStream fis2 = new FileInputStream(file2);

        byte[] buffer1 = new byte[fis1.available()];
      //  byte[] buffer2 = new byte[fis2.available()];

        fis1.read(buffer1);
  //      fis2.read(buffer2);

        FileOutputStream fos2 = new FileOutputStream(file2);
        for (int i = 1; i < buffer1.length; i = i + 2){
            int k = ((int) buffer1[i]);
            fos2.write(k);
        }
        fos2.close();
        reader.close();
        fis1.close();
    }
}
