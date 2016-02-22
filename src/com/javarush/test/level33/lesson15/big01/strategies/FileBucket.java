package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by paul on 2/10/16.
 */
public class FileBucket
{
   private Path path;
    public FileBucket()
    {
        try
        {
            path = Files.createTempFile("tmp", null);
            path.toFile().deleteOnExit();

        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }


    }

    public long getFileSize()
    {
        return path.toFile().length();
    }

    public void putEntry(Entry entry)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile())))
        {
            oos.writeObject(entry);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry()
    {
        if (getFileSize() == 0) return null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile())))
        {
            return (Entry) ois.readObject();

        }
        catch (ClassNotFoundException | IOException e)
        {
            ExceptionHandler.log(e);
        }
        return null;
    }

    public void remove()
    {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            ExceptionHandler.log(e);
        }
}}
