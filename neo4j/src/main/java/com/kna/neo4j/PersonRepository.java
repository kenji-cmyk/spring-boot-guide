package com.kna.neo4j;

import jakarta.annotation.Nullable;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,Long> {

    @Nullable
    Person findByName(String name);

    List<Person> findByRefferalsName (String name);
}
