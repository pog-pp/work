package com.javarush.test.level38.lesson04.home01;

import com.javarush.test.level38.lesson06.home01.ExceptionApplicationMessage;
import com.javarush.test.level38.lesson06.home01.ExceptionDBMessage;
import com.javarush.test.level38.lesson06.home01.ExceptionUserMessage;

/**
 * Created by paul on 2/22/16.
 */
public class ExceptionFactory
{ public static Throwable getException(Enum message)
{
    if (message != null)
    {
        String msg = message.name().substring(0, 1).toUpperCase() + message.name().substring(1).toLowerCase().replaceAll("_", " ");

        if (message instanceof ExceptionApplicationMessage)
        {
            return (new Exception(msg));
        }
        if (message instanceof ExceptionDBMessage)
        {
            return (new RuntimeException(msg));
        }
        if (message instanceof ExceptionUserMessage)
        {
            return (new Error(msg));
        }

    }
    return new IllegalArgumentException();
    }
}
