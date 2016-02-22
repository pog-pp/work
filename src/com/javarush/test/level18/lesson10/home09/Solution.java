package com.javarush.test.level18.lesson10.home09;

/* Файлы и исключения
Читайте с консоли имена файлов
Если файла не существует (передано неправильное имя файла), то
перехватить исключение FileNotFoundException, вывести в консоль переданное неправильное имя файла и завершить работу программы.
Закрыть потоки. Не использовать try-with-resources
Не используйте System.exit();
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException, FileNotFoundException
    {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        while (true){
            s = r.readLine();
            File f = new File(s);
            try{
                if (!f.exists()){
                    throw new FileNotFoundException();
                }

            }catch(FileNotFoundException e)
            {
                System.out.println(s);
                r.close();
                break;
            }
        }
    }
}
