package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GlobalHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = LogManager.getLogger(GlobalHandler.class);
        logger.fatal("Uncaught exception", e);
    }
}
