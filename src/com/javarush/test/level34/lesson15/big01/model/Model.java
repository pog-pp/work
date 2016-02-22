package com.javarush.test.level34.lesson15.big01.model;

import com.javarush.test.level34.lesson15.big01.controller.EventListener;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by paul on 2/16/16.
 */
public class Model
{
    private EventListener eventListener;
    public static final int FIELD_SELL_SIZE = 20;
    private GameObjects gameObjects;
    private int currentLevel=1;
    LevelLoader levelLoader = new LevelLoader(Paths.get("/home/paul/Downloads/JavaRushHomeWork(1)/JavaRushHomeWork/src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));


    public GameObjects getGameObjects(){
        return gameObjects;
    }
    public void restartLevel(int level) throws IOException
    {
        gameObjects = levelLoader.getLevel(level);
    }
    public void restart() throws IOException
    {
        restartLevel(currentLevel);
    }
    public void startNextLevel() throws IOException
    {
        restartLevel(++currentLevel);
    }
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public void move(Direction direction) throws IOException
    {
        Player player = gameObjects.getPlayer();
        if(checkWallCollision(player, direction))
            return;
        if(checkBoxCollision(direction))
            return;

        switch (direction)
        {
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for(Wall wall: gameObjects.getWalls())
        {
            if(gameObject.isCollision(wall,direction))
                return true;
        }
        return false;
    }

    public boolean checkBoxCollision(Direction direction){
        Player player = gameObjects.getPlayer();

        for(Box box: gameObjects.getBoxes())
        {
            if (player.isCollision(box, direction))
            {
                if (checkWallCollision(box, direction))
                    return true;
                for(Box box1: gameObjects.getBoxes())
                {
                    if(box.isCollision(box1,direction))
                        return true;
                }
                switch (direction)
                {
                    case RIGHT:
                        box.move(FIELD_SELL_SIZE, 0);
                        break;
                    case LEFT:
                        box.move(-FIELD_SELL_SIZE, 0);
                        break;
                    case DOWN:
                        box.move(0, FIELD_SELL_SIZE);
                        break;
                    case UP:
                        box.move(0, -FIELD_SELL_SIZE);
                }
            }
        }
        return false;
    }
    public void checkCompletion() throws IOException
    {
        int count = 0;
        for(Box box: gameObjects.getBoxes())
        {
            for(Home home: gameObjects.getHomes())
            {
                if(home.getX() == box.getX() && home.getY() == box.getY())
                    count++;
            }
        }
        if(count == gameObjects.getBoxes().size())
            eventListener.levelCompleted(currentLevel);
    }

}
