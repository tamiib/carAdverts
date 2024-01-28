package com.codevibe.codevibe.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

        jdbcTemplate.update(sql, 
                newAdvert.getTitle(),
                newAdvert.getFuelType().toString(), 
                newAdvert.getPrice(),
                newAdvert.getIsNew(),
                newAdvert.getMileage(),
                newAdvert.getFirstRegistration());

         return  getCarAdvertById(newAdvert.getId());      
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
