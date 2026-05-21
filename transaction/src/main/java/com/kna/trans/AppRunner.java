package com.kna.trans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AppRunner implements CommandLineRunner {

    private final static Logger log = LoggerFactory.getLogger(AppRunner.class);

    private final BookingService bookingService;

    public AppRunner(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) throws Exception{
        bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBooking().size() == 3,
                "First booking should work with no problem");
        log.info("Alice, Bob and Carol have been booked");

        try {
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e){
            log.info("v-- The following exception is expect because 'Samuel' is too big for the DB --v");
            log.error(e.getMessage());
        }

        logCurrentBookings();

        log.info("You shouldn't see Buddy or null. null violated DB constraints, and \" +\n" +
                "\t\t\t\t\"Buddy was rolled back in the same TX");

        Assert.isTrue(bookingService.findAllBooking().size()==3, "'null' should have triggered a roll back");

    }

    private void logCurrentBookings(){
        for (String person : bookingService.findAllBooking()){
            log.info("So far, {} is booked", person);
        }
    }
}
