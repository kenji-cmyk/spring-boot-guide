package com.kna.neo4j;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Neo4jApplication {

	private final Logger log = LoggerFactory.getLogger(Neo4jApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);

		System.exit(0);
	}

	@Bean
	CommandLineRunner demo (PersonRepository personRepository){
		return  args -> {
			personRepository.deleteAll();
			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person mixi = new Person("Mixi");

			List<Person> team = Arrays.asList(greg, roy, mixi);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(mixi);

			greg = personRepository.findByName(greg.getName());
			greg.referred(roy);
			greg.referred(mixi);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			roy.referred(mixi);
			personRepository.save(roy);

			log.info("Lookup each person by name....");

			team.stream().forEach(person -> log.info(
					"\t" + personRepository.findByName(person.getName()).toString()));

					List<Person> refferers = personRepository.findByRefferalsName(mixi.getName());

					log.info("The following referred Mixi ....");

					refferers.stream().forEach(person -> log.info("\t" + person.getName()));
		};
	}

}


