package com.javarush.test.level34.lesson15.big01.controller;

import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;
import com.javarush.test.level34.lesson15.big01.model.Model;
import com.javarush.test.level34.lesson15.big01.view.View;

import java.io.IOException;

/**
 * Created by paul on 2/16/16.
 */
public class Controller implements EventListener
{
    private View view;
    private Model model;

    public static void main(String[] args) throws IOException
    {
        Controller controller = new Controller();
    }

    public Controller() throws IOException
    {
        view = new View(this);
        model = new Model();
        view.init();
        model.restart();
        model.setEventListener(this);
        view.setEventListener(this);
    }
    public GameObjects getGameObjects(){
        return model.getGameObjects();
    }
    @Override
    public void move(Direction direction) throws IOException
    {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart() throws IOException
    {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel() throws IOException
    {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level) throws IOException
    {
        view.completed(level);
    }
}
