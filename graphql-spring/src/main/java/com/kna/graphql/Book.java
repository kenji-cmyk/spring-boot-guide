package com.kna.graphql;


import java.util.Arrays;
import java.util.List;

public record Book  (String id, String name, int pageCnt, String authorId) {

    private static List<Book> books = Arrays.asList(
            new Book("book-1","Java", 416, "author-1"),
            new Book("book-2","Go",206,"author-2"),
            new Book("book-3","C#",437,"author-3")
    );

    public static Book getById(String id){
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
    }

}
