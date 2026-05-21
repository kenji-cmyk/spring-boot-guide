package com.kna.cachingdata;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookRepoImpl implements  BookRepo{


    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn,"Some book");
    }

    private void simulateSlowService (){
        try {
            long time = 3000L;
            Thread.sleep(1000);
        } catch (InterruptedException e){
            throw new IllegalStateException(e);
        }
    }
}
