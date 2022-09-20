package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.model.Persons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {PersonsRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.model"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class PersonsRepositoryTest {
    @Autowired
    private PersonsRepository personsRepository;

    /**
     * Method under test: {@link PersonsRepository#getPersonsListByColor(String)}
     */
    @Test
    void testGetPersonsListByColor() {
        Persons persons = new Persons();
        persons.setCity("Oxford");
        persons.setColor("Color");
        persons.setId(1);
        persons.setLastName("Doe");
        persons.setName("Name");
        persons.setZipcode("21654");

        Persons persons1 = new Persons();
        persons1.setCity("Oxford");
        persons1.setColor("Color");
        persons1.setId(1);
        persons1.setLastName("Doe");
        persons1.setName("Name");
        persons1.setZipcode("21654");
        personsRepository.save(persons);
        personsRepository.save(persons1);
        assertTrue(personsRepository.getPersonsListByColor("foo").isEmpty());
    }
}

