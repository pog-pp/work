package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String file1 = r.readLine();
        String file2 = r.readLine();
        r.close();
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        String str;
        String[] strMass;
        double tmp;

        while ((str = fileReader.readLine()) != null) {
            strMass = str.split(" ");
            for (String strMas : strMass)
            {
                tmp = Math.round(Double.parseDouble(strMas));
                numbers.add((int)tmp);
            }
        }
        fileReader.close();

        BufferedWriter bw = new BufferedWriter(new FileWriter(file2));
        String num = "";
        for (int a : numbers)
            num = num+a+" ";
        bw.write(num);
        bw.close();

    }
}
