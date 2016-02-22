package com.javarush.test.level34.lesson15.big01.view;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;
import com.javarush.test.level34.lesson15.big01.model.Direction;
import com.javarush.test.level34.lesson15.big01.model.GameObject;
import com.javarush.test.level34.lesson15.big01.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

/**
 * Created by paul on 2/16/16.
 */
public class Field extends JPanel
{
    private EventListener eventListener;
    private View view;

    public Field(View view)
    {
        this.view = view;
        KeyHandler handler = new KeyHandler();
        view.addKeyListener(handler);
        view.setFocusable(true);
    }

    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());
        GameObjects gameObjects = view.getGameObjects();
        Set<GameObject> set = gameObjects.getAll();
        for(GameObject gameObject: set)
        {
            gameObject.draw(g);
        }
    }
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    try
                    {
                        eventListener.move(Direction.LEFT);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    try
                    {
                        eventListener.move(Direction.RIGHT);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_UP:
                    try
                    {
                        eventListener.move(Direction.UP);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    try
                    {
                        eventListener.move(Direction.DOWN);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case KeyEvent.VK_R:
                    try
                    {
                        eventListener.restart();
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
            }
        }
    }
}
