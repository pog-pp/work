package com.javarush.test.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by paul on 2/9/16.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File file)
    {
        if (file != null) {
            String nameForCheck = file.getName().toLowerCase();
            if (nameForCheck.endsWith(".html") || nameForCheck.endsWith(".htm") || file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
