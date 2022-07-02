package com.odysseuss.springdatabase.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.odysseuss.springdatabase.entities.Officer;
import com.odysseuss.springdatabase.entities.Rank;


@SpringBootTest
@Transactional
public class JdbcOfficerDAOTest {

    @Autowired
    JdbcOfficerDAO dao;

    @Test
    void testCount() {
        assertEquals(5, dao.count());
    }

    @Test
    void testDelete() {

        IntStream.rangeClosed(1, 5).forEach(id -> {
            Optional<Officer> officer = dao.findById(id);
            assertTrue(officer.isPresent());
            dao.delete(officer.get());

        });

        assertEquals(0, dao.count());
    }

    @Test
    void testExistsById() {

        IntStream.rangeClosed(1, 5).forEach(id -> {
            assertTrue(dao.existsById(id));
        });
    }

    @Test
    void testFindAll() {
        List<String> lastNames = dao.findAll().stream()
                                    .map(Officer::getLastName)
                                    .collect(Collectors.toList());

        assertThat(lastNames,
            containsInAnyOrder("Archer", "Janeway", "Kirk", "Picard", "Sisko"));

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

        Officer officer = new Officer(Rank.ADMIRAL, "Gial", "Ackbar");
        officer = dao.save(officer);
        assertNotNull(officer.getId());

    }
}
