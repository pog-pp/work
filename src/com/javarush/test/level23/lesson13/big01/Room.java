package com.javarush.test.level23.lesson13.big01;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Основной класс программы.
 */
public class Room
{
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake)
    {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public Mouse getMouse()
    {
        return mouse;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setSnake(Snake snake)
    {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse)
    {
        this.mouse = mouse;
    }

    /**
     *  Основной цикл программы.
     *  Тут происходят все важные действия
     */
    public void run()
    {
        //Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //пока змея жива
        while (snake.isAlive())
        {
            //"наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //Если равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;

                //Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                //Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                //Если "стрелка вверх" - сдвинуть фигурку вверх
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                //Если "стрелка вниз" - сдвинуть фигурку вниз
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   //двигаем змею
            print();        //отображаем текущее состояние игры
            sleep();        //пауза между ходами
        }

        //Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }

    /**
     * а) вывести на экран прямоугольник из точек размером width x height.
     б) тело змеи отметить символом "x"-английское
     в) голову змеи нарисовать символом X-английское.
     Подсказка:
     а) удобно сначала создать матрицу типа int[][] с размером (height x width)
     б) затем пройтись по всем объектам и отрисовать их в матрицу.
     Например, тело змеи - 1, голова змеи - 2, мышь - 3.
     */
    public void print()
    {
        int[][] screen = new int[height][width];
        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections)
        {
            screen[snakeSection.getY()][snakeSection.getX()] = 1;
        }


        screen[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

        screen[mouse.getY()][mouse.getX()] = 3;

        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                System.out.print(symbols[screen[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    /**
     * Метод вызывается, когда мышь съели
     */
    public void eatMouse()
    {
        createMouse();
    }

    /**
     * Создает новую мышь
     */
    public void createMouse()
    {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }


    public static Room game;

    public static void main(String[] args)
    {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }



    private static int[] levelDelay = {1000, 600, 550, 500, 480, 460, 440, 420, 400, 380, 360, 340, 320, 300, 285, 270};


    public void sleep()
    {
        try
        {
            int level = snake.getSections().size();
            int delay = level < 15 ? levelDelay[level] : 250;
            Thread.sleep(delay);
        }
        catch (InterruptedException e)
        {
        }
    }
}
