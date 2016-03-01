package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;

import java.io.IOException;

/**
 * Created by paul on 2/16/16.
 */
public interface EventListener
{
    void move(Direction direction) throws IOException;// – передвинуть объект в определенном направлении.
    void 	restart() throws IOException;// – начать заново текущий уровень.
    void    startNextLevel() throws IOException;// – начать следующий уровень.
   	void levelCompleted(int level) throws IOException;// – уровень с номером level завершён.
}
