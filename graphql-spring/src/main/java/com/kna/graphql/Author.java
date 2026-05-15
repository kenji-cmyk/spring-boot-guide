package com.kna.graphql;

import java.util.Arrays;
import java.util.List;

public record Author(String id, String firstName, String lastName)  {

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Kimmich"),
            new Author("author-2", "Lamine", "Yamal"),
            new Author("author-3", "Leniol", "Messi")
    );

    public static Author getById(String id){
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
