package com.kna.trans;


import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingService {

    private final static Logger log = LoggerFactory.getLogger(BookingService.class);

    private final JdbcTemplate jdbcTemplate;

    public BookingService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void book(String... persons){
        for (String person : persons){
            log.info("Booking {} is a seat ...", person);
            jdbcTemplate.update("insert into BOOKING(FIRST_NAME) values (?)", person);
        }
    }

    public List<String> findAllBooking(){
        return jdbcTemplate.query("select FIRST_NAME from BOOKING", (rs, rowNum) ->
                rs.getString("FIRST_NAME"));
    }
}
