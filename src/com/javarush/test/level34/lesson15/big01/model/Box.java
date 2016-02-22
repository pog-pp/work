package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by paul on 2/16/16.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.ORANGE);

        int leftUpperCornerX = getX() - getWidth()/2;
        int leftUpperCornerY = getY() - getHeight()/2;

        graphics.drawRect(leftUpperCornerX,leftUpperCornerY,getWidth(),getHeight());
        graphics.fillRect(leftUpperCornerX,leftUpperCornerY,getWidth(),getHeight());
    }

    @Override
    public void move(int x, int y)
    {
        setX(getX()+x);
        setY(getY()+y);
    }
}
