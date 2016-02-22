package com.javarush.test.level18.lesson05.task04;

/* Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file = r.readLine();
        String outf = r.readLine();
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(outf);
        int com = 0;

        byte[] buffer = new byte[inputStream.available()];
        for(int i = inputStream.read(buffer); i > 0; i--) outputStream.write(buffer[i-1]);

        inputStream.close();
        outputStream.close();
        r.close();
    }
}
