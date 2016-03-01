package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by paul on 12/25/15.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

 /*   public void processVideos() throws NoVideoAvailableException
    {
        List<Advertisement> advertisements = new ArrayList<>(storage.list());

        if (advertisements.isEmpty()) throw new NoVideoAvailableException();

        Collections.sort(advertisements, new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement a1, Advertisement a2)
            {
                if (a1.getAmountPerOneDisplaying() != a2.getAmountPerOneDisplaying())
                {
                    return (-1) * Long.compare(a1.getAmountPerOneDisplaying(), a2.getAmountPerOneDisplaying());
                } else
                {
                    Long l1, l2;
                    l1 = a1.getAmountPerOneDisplaying() * 1000 / a1.getDuration();
                    l2 = a1.getAmountPerOneDisplaying() * 1000 / a2.getDuration();
                    if (l1 - l2 == 0) return 0;
                    else if (l1 - l2 < 0) return -1;
                    else return 1;
                }
            }
        });

        for (Advertisement advertisement : advertisements){
            ConsoleHelper.writeMessage(String.format("%s is displaying... %d, %d",
                    advertisement.getName(),
                    advertisement.getAmountPerOneDisplaying(),
                    advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration()));

                advertisement.revalidate();

        }*/

  //  }
  public void processVideos() {
      Collections.sort(storage.list(), new Comparator<Advertisement>() {
          @Override
          public int compare(Advertisement o1, Advertisement o2) {
              int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
              if (result != 0)
                  return -result;

              long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
              long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

              return Long.compare(oneSecondCost1, oneSecondCost2);
          }
      });

      int timeLeft = timeSeconds;
      for (Advertisement advertisement : storage.list()) {
          if (timeLeft < advertisement.getDuration()) {
              continue;
          }

          ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                  + advertisement.getAmountPerOneDisplaying() + ", "
                  + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

          timeLeft -= advertisement.getDuration();
          try
          {
              advertisement.revalidate();
          }

          catch (UnsupportedOperationException e){

          }
      }

      if (timeLeft == timeSeconds) {
          throw new NoVideoAvailableException();
      }
  }

}
