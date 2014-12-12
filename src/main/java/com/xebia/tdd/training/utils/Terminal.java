package com.xebia.tdd.training.utils;

import java.util.logging.Logger;

/**
 * Terminal can be used for logging and send output to any console available
 * 
 * @author Anand Swarup V
 *
 */
public class Terminal {

    private final String name;
    private final Logger logger;

    public Terminal(String name) {
        this.name = name;
        logger = Logger.getLogger(name);
    }

    public static Terminal getInstance(String name) {
        return new Terminal(name);
    }

    public void out(String message) {
        logger.info(message);
        System.out.println(name + " : " + message);
    }

    public void debug(String message) {
        logger.finest(message);
    }
}
