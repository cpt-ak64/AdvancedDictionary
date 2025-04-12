package com.something.first.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    // One syntax to implement a
    // GET method
    @GetMapping("/")
    public String home() {
        logger.info("/ endpoint called");
        String str
                = "<html><body><font color=\"green\">"
                + "<h1>WELCOME To GeeksForGeeks</h1>"
                + "</font></body></html>";
        return str;
    }

    // Another syntax to implement a
    // GET method
    @RequestMapping(
            method = { RequestMethod.GET },
            value = { "/gfg" })

    public String info() {
        logger.info("/gfg called");
        String str2
                = "<html><body><font color=\"green\">"
                + "<h2>GeeksForGeeks is a Computer"
                + " Science portal for Geeks. "
                + "This portal has been "
                + "created to provide well written, "
                + "well thought and well explained "
                + "solutions for selected questions."
                + "</h2></font></body></html>";
        return str2;
    }
}
