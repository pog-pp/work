package com.javarush.test.level36.lesson04.home01;

/**
 * Created by paul on 2/17/16.
 */
public class View
{
    private Controller controller = new Controller();
    public void fireEventShowData() {
        System.out.println(controller.onDataListShow());
    }

}
