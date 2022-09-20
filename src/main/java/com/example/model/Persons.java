package com.example.model;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persons")
public class Persons {

    @Id
    @NonNull
    private int id;
    private String name;
    private String lastName;
    private String zipcode;
    private String city;
    @NonNull
    private String color;

}

