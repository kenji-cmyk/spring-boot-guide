package com.kna.cachingdata;

public interface BookRepo {
    Book getByIsbn(String isbn);
}
