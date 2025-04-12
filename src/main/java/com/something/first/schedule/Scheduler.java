package com.something.first.schedule;


import com.something.first.controller.DictionaryController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler {

    private final DictionaryController dc;

    public Scheduler(DictionaryController dc) {
        this.dc = dc;
    }

    // Method
    // To trigger the scheduler every 3 seconds with
    // an initial delay of 5 seconds.
//    @Scheduled(fixedDelay = 2000, initialDelay = 1000)

    public void scheduleTask()
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss.SSS");

        String strDate = dateFormat.format(new Date());

        System.out.println(
                "Fixed Delay Scheduler: Task running at - "
                        + strDate);

        dc.printFirst50Entries();
    }
}