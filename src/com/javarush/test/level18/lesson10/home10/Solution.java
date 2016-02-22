package com.javarush.test.level18.lesson10.home10;

/* Собираем файл
Собираем файл из кусочков
Считывать с консоли имена файлов
Каждый файл имеет имя: [someName].partN. Например, Lion.avi.part1, Lion.avi.part2, ..., Lion.avi.part37.
Имена файлов подаются в произвольном порядке. Ввод заканчивается словом "end"
В папке, где находятся все прочтенные файлы, создать файл без приставки [.partN]. Например, Lion.avi
В него переписать все байты из файлов-частей используя буфер.
Файлы переписывать в строгой последовательности, сначала первую часть, потом вторую, ..., в конце - последнюю.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        List<String> files= new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = "";
        String simlpeFileName = "";
        String simlpeFileExt = "";
        while(true){
            file = reader.readLine();
            if(!file.equals("end")) {
                files.add(file);
            }
            else{
                break;
            }
        }
        Collections.sort(files);
        String[] nameFile = files.get(0).split("\\.");
        simlpeFileName = nameFile[0];
        simlpeFileExt = nameFile[1];
        FileOutputStream out = new FileOutputStream(simlpeFileName + "." + simlpeFileExt);
        for (String s:files) {
            FileInputStream in = new FileInputStream(new File(s));
            if (in.available() > 0) {

                byte[] buffer = new byte[in.available()];
                int count = in.read(buffer);
                out.write(buffer, 0, count);
            }
            in.close();
        }
        out.close();
        reader.close();

    }
}