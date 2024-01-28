package com.codevibe.codevibe.repositories;


import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.codevibe.codevibe.domainModels.CarAdvert;


import jakarta.annotation.PostConstruct;

@Repository
public class CarAdvertRepository
 {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarAdvertRepository(JdbcTemplate _jdbcTemplate) {
        this.jdbcTemplate = _jdbcTemplate;
    }

    @PostConstruct
    private void initializeDatabase() {
        checkAndCreateTable(); 
    }
    
    private void checkAndCreateTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS car_advert " +
            "(id SERIAL PRIMARY KEY, " +
            "title VARCHAR(255), " +
            "fuel_type VARCHAR(50), " + 
            "price INTEGER, " +
            "is_new BOOLEAN, " +
            "mileage INTEGER, " +
            "first_registration DATE)"; 
    
        jdbcTemplate.execute(sqlCreate);
    }
    
    public List<CarAdvert> getAllCarAdverts(String sortBy) 
    {
        String sql = "SELECT * FROM car_advert";

        if (sortBy != null && !sortBy.isEmpty()) {
            sql += " ORDER BY " + sortBy;
        }

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarAdvert.class));
    }

    public CarAdvert getCarAdvertById(Long id) {
        String sql = "SELECT * FROM car_advert WHERE id = ?";
        try 
        {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CarAdvert.class), id);
        } 
        catch (EmptyResultDataAccessException e) 
        {
            return null;
        }
    }

    public CarAdvert createCarAdvert(CarAdvert newAdvert) 
    {
        String sql = "INSERT INTO car_advert (title, fuel_type, price, is_new, mileage, first_registration) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
         KeyHolder keyHolder = new GeneratedKeyHolder();            

                     jdbcTemplate.update(
        connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, newAdvert.getTitle());
            ps.setString(2, newAdvert.getFuelType().toString());
            ps.setInt(3, newAdvert.getPrice());
            ps.setBoolean(4, newAdvert.getIsNew());
            ps.setInt(5, newAdvert.getMileage());
            ps.setTimestamp(6, new java.sql.Timestamp(newAdvert.getFirstRegistration().getTime())); 
            return ps;
        },
        keyHolder);
       
        Number key = keyHolder.getKey();
        if (key != null) {
            return getCarAdvertById(key.longValue());
        } 
        return null;  
    }

    public CarAdvert updateCarAdvert(Long id, CarAdvert updatedAdvert)
    {
        String sql = "UPDATE car_advert SET title = ?, fuel_type = ?, price = ?, is_new = ?, mileage = ?, first_registration = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                updatedAdvert.getTitle(),
                updatedAdvert.getFuelType().toString(),
                updatedAdvert.getPrice(),
                updatedAdvert.getIsNew(),
                updatedAdvert.getMileage(),
                updatedAdvert.getFirstRegistration(),
                id);
         return  getCarAdvertById(id);       
    }
    

    public void deleteCarAdvertById(Long id) 
    {
        String sql = "DELETE FROM car_advert WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
