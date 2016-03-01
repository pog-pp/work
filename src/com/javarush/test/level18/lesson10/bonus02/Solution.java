package com.javarush.test.level18.lesson10.bonus02;

/* Прайсы
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается со следующим набором параметров:
-c id productName price quantity
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-c  - добавляет товар с заданными параметрами в конец файла, генерирует id самостоятельно, инкрементируя максимальный id, найденный в файле

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution
{
        static ArrayList<String> list = new ArrayList<String>();

        public static void main(String[] args) throws Exception
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();
            reader.close();
            BufferedReader file = new BufferedReader(new FileReader(name));
            int max = 0;
            if (args[0].equals("-c"))
            {
                while (file.ready())
                {
                    String str = file.readLine();
                    list.add(str);
                    int y = Integer.parseInt(str.substring(0,8).replaceAll(" ",""));
                    if (y > max) max = y;

                }
                max++;
                file.close();
                BufferedWriter fileWR = new BufferedWriter(new FileWriter(name));
                for (int i = 0; i < list.size();i++)
                {
                    if (list.get(i).equals("")) continue;
                    fileWR.write(String.format("%s%n", list.get(i)));
                }
                String productname = args[1];
                for (int l = 2; l < args.length-2; l++)
                    productname +=" " + args[l];
                fileWR.write(String.format("%-8.8s%-30.30s%-8.8s%-4.4s%n",String.valueOf(max),productname,args[args.length-2],args[args.length-1]));
                fileWR.close();
            }
        }
    }