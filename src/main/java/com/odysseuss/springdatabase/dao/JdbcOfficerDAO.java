package com.odysseuss.springdatabase.dao;

import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.odysseuss.springdatabase.entities.Officer;
import com.odysseuss.springdatabase.entities.Rank;



@Repository
public class JdbcOfficerDAO implements OfficerDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertOfficer;

    private RowMapper<Officer> officerMapper = (resultSet, rowNum) -> {
        return new Officer(resultSet.getInt("id"),
                        Rank.valueOf(resultSet.getString("rank")),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
        };

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertOfficer = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("officers")
                .usingGeneratedKeyColumns("id");
    }


    @Override
    public long count() {
        return jdbcTemplate.queryForObject("SELECT COUNT (*) from officers", Long.class);
    }

    @Override
    public void delete(Officer officer) {
        
        jdbcTemplate.update("DELETE FROM officers WHERE id=?", officer.getId());
        
    }

    @Override
    public boolean existsById(Integer id) {
        
        return jdbcTemplate.queryForObject("SELECT EXISTS( SELECT 1 FROM officers WHERE id=?)", Boolean.class, id);
    
    }

    @Override
    public List<Officer> findAll() {
        
        return jdbcTemplate.query("SELECT * FROM officers", officerMapper);
                
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        
        if (!existsById(id)) {
            return Optional.empty();
        }

        return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM officers WHERE id=?",
                officerMapper, id));

    }

    @Override
    public Officer save(Officer officer) {
        
        Map<String, Object> parameters = Map.ofEntries(
            entry("rank", officer.getRank()),
            entry("first_name", officer.getFirstName()),
            entry("last_name", officer.getLastName()));

        Integer newId = (Integer) insertOfficer.executeAndReturnKey(parameters);
        officer.setId(newId);
        
        return officer;
    }


    
}
