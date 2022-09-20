package com.example.service;

import com.example.model.Persons;

import java.util.List;

public interface PersonService {
    List<Persons> getAllPersons();
    Persons getPersonById(int getPersonById);
    void savePerson(Persons person);
    List<Persons> getPersonsByColor(String color);
}
