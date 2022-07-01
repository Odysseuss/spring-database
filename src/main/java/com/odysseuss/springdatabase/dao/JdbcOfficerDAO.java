package com.odysseuss.springdatabase.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.odysseuss.springdatabase.entities.Officer;



@Repository
public class JdbcOfficerDAO implements OfficerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOfficerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(Officer officer) {
        
        
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Officer> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Officer> findById(Integer id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Officer save(Officer officer) {
        // TODO Auto-generated method stub
        return null;
    }


    
}
