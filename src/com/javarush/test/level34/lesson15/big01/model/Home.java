package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by paul on 2/16/16.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setHeight(2);
        setWidth(2);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLUE);
        int leftUpperCornerX = getX() - getWidth()/2;
        int leftUpperCornerY = getY() - getHeight()/2;

        graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}
