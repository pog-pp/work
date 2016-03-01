package com.javarush.test.level34.lesson15.big01.model;

/**
 * Created by paul on 2/16/16.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }
    public boolean isCollision(GameObject gameObject, Direction direction){
        boolean result = false;
        switch (direction)
        {
            case RIGHT:
                if(getX() + Model.FIELD_SELL_SIZE == gameObject.getX() && getY() == gameObject.getY())
                    result = true;
                break;
            case LEFT:
                if(getX() - Model.FIELD_SELL_SIZE == gameObject.getX() && getY() == gameObject.getY())
                    result = true;
                break;
            case UP:
                if(getX() == gameObject.getX() && getY() - Model.FIELD_SELL_SIZE == gameObject.getY())
                    result = true;
                break;
            case DOWN:
                if(getX() == gameObject.getX() && getY() + Model.FIELD_SELL_SIZE == gameObject.getY())
                    result = true;
        }
        return result;
    }
}
