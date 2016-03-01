package com.javarush.test.level26.lesson15.big01.command;


import java.io.IOException;

/**
 * Created by paul on 12/21/15.
 */
interface Command
{
    void execute() throws IOException;
}
