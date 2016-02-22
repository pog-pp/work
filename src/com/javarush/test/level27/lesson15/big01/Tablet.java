package com.javarush.test.level27.lesson15.big01;

import com.javarush.test.level27.lesson15.big01.ad.AdvertisementManager;
import com.javarush.test.level27.lesson15.big01.ad.NoVideoAvailableException;
import com.javarush.test.level27.lesson15.big01.kitchen.Order;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by paul on 12/24/15.
 */
public class Tablet extends Observable
{
    private final int number;
    public static Logger log =  Logger.getLogger(Tablet.class.getName());

    public Tablet(int number)
    {
        this.number = number;
    }

    public void createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            if (!order.isEmpty()) {
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
            }

            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
        }
        catch (IOException e) {
            log.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            log.log(Level.INFO, "No video is available for the order " + order);
        }
    }
/*    public void createOrder() throws IOException
    {
        try
        {
            Order order = new Order(this);
            if (!order.isEmpty()){
                ConsoleHelper.writeMessage(order.toString());
                setChanged();
                notifyObservers(order);
                try
                {
                    AdvertisementManager advert = new AdvertisementManager(order.getTotalCookingTime() * 60);
                    advert.processVideos();
                }

                catch (NoVideoAvailableException e)
                {
                    log.log(Level.INFO, "No video is available for the order " + order);
                }
                catch (UnsupportedOperationException e){

                }

            }
        }
        catch (IOException e)
        {
            log.log(Level.SEVERE, "Console is unavailable.");
        }
    }*/

    @Override
    public String toString()
    {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
