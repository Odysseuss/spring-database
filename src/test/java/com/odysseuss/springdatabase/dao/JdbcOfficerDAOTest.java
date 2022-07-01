package com.odysseuss.springdatabase.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.odysseuss.springdatabase.entities.Officer;
import com.odysseuss.springdatabase.entities.Rank;


@SpringBootTest
public class JdbcOfficerDAOTest {

    @Autowired
    JdbcOfficerDAO dao;

    @Test
    void testCount() {

    }

    @Test
    void testDelete() {

    }

    @Test
    void testExistsById() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindByIdThatExists() {

        Optional<Officer> officer = dao.findById(1);
        assertTrue(officer.isPresent());
        assertEquals(1, officer.get().getId());
    }

    @Test
    void testFindByIdThatDoesNotExist() {
        Optional<Officer> officer = dao.findById(999);
        assertTrue(officer.isEmpty());
    }

    @Test
    void testSave() {

        Officer officer = new Officer(Rank.ADMIRAL, "Akbar", "Fishman");
        officer = dao.save(officer);
        assertNotNull(officer.getId());

    }
}
