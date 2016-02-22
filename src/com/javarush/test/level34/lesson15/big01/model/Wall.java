package com.javarush.test.level34.lesson15.big01.model;

import java.awt.*;

/**
 * Created by paul on 2/16/16.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.DARK_GRAY);

        int leftUpperCornerX = getX() - getWidth()/2;
        int leftUpperCornerY = getY() - getHeight()/2;

        graphics.drawOval(leftUpperCornerX,leftUpperCornerY,getWidth(),getHeight());
        graphics.fillOval(leftUpperCornerX,leftUpperCornerY,getWidth(),getHeight());
    }
}
