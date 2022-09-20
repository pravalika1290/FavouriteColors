package com.example.dataLoader;

import com.example.model.Persons;
import com.example.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DataLoader implements ApplicationRunner {

    @Value("classpath:sample-input.csv")
    private Resource personsCsv;

    private static final String DELIMITER = ",";
    private final PersonsRepository personRepository;

    public DataLoader(PersonsRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void run(ApplicationArguments args) throws IOException {
        personsTable(getColorCodeMap());
    }

    public void personsTable(Map<String, String> colorCodeMap) throws IOException {

        List<Persons> personsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(personsCsv.getInputStream()))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {

                String[] columns = line.split(DELIMITER);
                if (columns.length == 4) {

                    String[] address = columns[2].trim().split(" ");

                    Persons person = Persons.builder()
                            .id(lineNumber)
                            .name(columns[0].trim()) //
                            .lastName(columns[1].trim()) //
                            .zipcode(address[0].trim()) //
                            .city(address[1].trim()) //
                            .color(colorCodeMap.get(columns[3].trim())) //
                            .build();

                    personsList.add(person);
                }
                lineNumber++;
            }
        }

        if (!personsList.isEmpty()) {
            personRepository.saveAll(personsList);
        }

    }

    public Map<String, String> getColorCodeMap() {
        Map<String, String> colorsList = new HashMap<>();

        colorsList.put("1", "blau");
        colorsList.put("2", "grün");
        colorsList.put("3", "violett");
        colorsList.put("4", "rot");
        colorsList.put("5", "gelb");
        colorsList.put("6", "türkis");
        colorsList.put("7", "weiß");

        return colorsList;
    }
}


