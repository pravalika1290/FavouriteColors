package com.example.service.impl;

import com.example.model.Persons;
import com.example.repository.PersonsRepository;
import com.example.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonsRepository personsRepository;


    public PersonServiceImpl(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }
    @Override
    public List<Persons> getAllPersons() {
        return personsRepository.findAll();
    }

    @Override
    public Persons getPersonById(int id) {
        Optional<Persons> response = personsRepository.findById(id);
        return response.orElse(null);
    }

    @Override
    public void savePerson(Persons person) {
        personsRepository.save(person);
    }

    @Override
    public List<Persons> getPersonsByColor(String color) {
        return personsRepository.getPersonsListByColor(color);
    }
}
