package com.javarush.test.level34.lesson15.big01.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by paul on 2/17/16.
 */
public class LevelLoader
{
    private Path levels;
    public LevelLoader(Path levels)
    {
        this.levels = levels;
    }



    public GameObjects getLevel(int level) throws IOException
    {
        while (level>60)
        {
            level -= 60;
        }
        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        try
        {   FileReader fileReader = new FileReader(levels.toString());
            BufferedReader reader = new BufferedReader(fileReader);
            while (true)
            {
                String input = reader.readLine();
                if (("Maze: " + level).equals(input))
                    break;
            }

            reader.readLine();
            int width = Integer.parseInt(reader.readLine().split(" ")[2]);
            int height = Integer.parseInt(reader.readLine().split(" ")[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();

            for(int y = 0; y < height; y++)
            {
                int yCoord = y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2;
                String in = reader.readLine();
                char[] array = in.toCharArray();
                for (int x = 0; x < width; x++)
                {
                    int xCoord = x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2;
                    char character = array[x];
                    switch (character)
                    {
                        case '&':
                            boxes.add(new Box(xCoord, yCoord));
                            homes.add(new Home(xCoord, yCoord));
                            break;
                        case '@':
                            player = new Player(xCoord, yCoord);
                            break;
                        case 'X':
                            walls.add(new Wall(xCoord, yCoord));
                            break;
                        case '.':
                            homes.add(new Home(xCoord, yCoord));
                            break;
                        case '*':
                            boxes.add(new Box(xCoord, yCoord));
                    }
                }
            }
            reader.close();
            fileReader.close();
        }
        catch (IOException e)
        {
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
