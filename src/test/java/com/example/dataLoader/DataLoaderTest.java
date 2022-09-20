package com.example.dataLoader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.model.Persons;
import com.example.repository.PersonsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DataLoader.class})
@ExtendWith(SpringExtension.class)
class DataLoaderTest {
    @Autowired
    private DataLoader dataLoader;

    @MockBean
    private PersonsRepository personsRepository;

    /**
     * Method under test: {@link DataLoader#run(ApplicationArguments)}
     */
    @Test
    void testRun() throws IOException {
        when(personsRepository.saveAll((Iterable<Persons>) any())).thenReturn(new ArrayList<>());
        dataLoader.run(new DefaultApplicationArguments("Args"));
        verify(personsRepository).saveAll((Iterable<Persons>) any());
    }

    /**
     * Method under test: {@link DataLoader#getColorCodeMap()}
     */
    @Test
    void testGetColorCodeMap() {
        Map<String, String> actualColorCodeMap = dataLoader.getColorCodeMap();
        assertEquals(7, actualColorCodeMap.size());
        assertEquals("blau", actualColorCodeMap.get("1"));
        assertEquals("grün", actualColorCodeMap.get("2"));
        assertEquals("violett", actualColorCodeMap.get("3"));
        assertEquals("rot", actualColorCodeMap.get("4"));
        assertEquals("gelb", actualColorCodeMap.get("5"));
        assertEquals("türkis", actualColorCodeMap.get("6"));
        assertEquals("weiß", actualColorCodeMap.get("7"));
    }
}

