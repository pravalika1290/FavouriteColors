package com.example.repository;

import com.example.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

    @Query(value = "SELECT c FROM Persons c WHERE c.color = :color")
    List<Persons> getPersonsListByColor(String color);
}
