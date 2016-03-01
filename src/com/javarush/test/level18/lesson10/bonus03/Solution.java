package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        LinkedList<String> list = new LinkedList<String>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String command = args[0];

        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNext())
        {
            String s = scanner.nextLine() + "\n";

            if (isUpdatedLine(args[1], s))
            {
                if (command.equals("-u"))
                    s = createLine(args);
                else if (command.equals("-d"))
                    s = "";
            }
            list.add(s);
        }
        scanner.close();

        FileOutputStream fos = new FileOutputStream(fileName);
        for (String s : list)
            fos.write(s.getBytes());

        fos.close();
        reader.close();
    }

    private static boolean isUpdatedLine(String arg, String s) {
        int idUpd = Integer.parseInt(arg);
        int id = Integer.parseInt(s.substring(0, 8).replaceAll("[^\\d]",""));

        return id == idUpd;
    }

    private static String createLine(String[] args) {
        String id = args[1];                            // 8 symbols
        String productName = "";                        // 60 bytes (30 chars, 30 symbols)
        for (int i = 2; i < args.length - 2; i++)
            productName += args[i] + " ";
        String price = args[args.length - 2];           // 8 symbols
        String quantity = args[args.length - 1];        // 4 symbols

        // prepare data
        id          = addSpaces(id, 8);
        productName = addSpaces(productName, 30);
        price       = addSpaces(price, 8);
        quantity    = addSpaces(quantity, 4);

        return (id + productName + price + quantity + "\n");
    }

    private static String addSpaces(String field, int maxNum) {
        if (field.length() < maxNum)
            while (field.length() < maxNum)
                field += " ";
        else
            field = field.substring(0, maxNum);
        return field;
    }
}