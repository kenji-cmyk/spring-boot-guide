package com.kna.msgredis;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger();


    public void receiveMessage (String message){
        LOGGER.info("Receive <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount(){
        return counter.get();
    }
}
