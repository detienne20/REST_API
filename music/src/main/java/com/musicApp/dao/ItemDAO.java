package com.musicApp.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.musicApp.model.*;
import java.util.Collection;
import java.util.ArrayList;


public class ItemDAO {
    private JdbcTemplate jdbcTemplate;
    private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_example";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
    
    
    
    public ItemDAO() {
        this.jdbcTemplate = new JdbcTemplate(this.getDataSource()); 
    }

    //@Autowired    
    public ItemDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }
    
   public Collection<Item> getAllItems(){
        Collection<Item> items = new ArrayList<Item>();
        this.jdbcTemplate.query(
                "SELECT * FROM products", new Object[] { },
                (rs, rowNum) -> new Item(rs.getInt("itemId"),rs.getString("name"),rs.getDouble("msrp"),rs.getDouble("salePrice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(item -> items.add(item));

        return items; 
    }


   public Collection<Item> getItemsByKeyword(String keyword){
     Collection<Item> items = new ArrayList<Item>();
        this.jdbcTemplate.query(
                "SELECT * FROM products WHERE name LIKE ? OR shortDescription LIKE ? OR brandName LIKE ? OR color LIKE ? OR size LIKE ? OR gender LIKE ? ", new Object[] {keyword+"%",keyword+"%",keyword+"%",keyword+"%",keyword+"%",keyword+"%"},
                (rs, rowNum) -> new Item(rs.getInt("itemId"),rs.getString("name"),rs.getDouble("msrp"),rs.getDouble("salePrice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(item -> items.add(item));

        return items; 
   }


  public Collection<Item> getItemByID(int itemId){
    Collection<Item> items = new ArrayList<Item>();
        this.jdbcTemplate.query(
                "SELECT * FROM products where itemId= ?",  new Object[] {itemId},
                (rs, rowNum) -> new Item(rs.getInt("itemId"),rs.getString("name"),rs.getDouble("msrp"),rs.getDouble("salePrice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"))
        ).forEach(item -> items.add(item));

       return items;
  }


   public Collection<Item> getItemByCartId(int cartId){
        Collection<Item> items = new ArrayList<Item>();

        this.jdbcTemplate.query(
                "SELECT * FROM purchase WHERE cartId = ?", new Object[] { cartId },
                    (rs, rowNum) -> new Item(rs.getInt("itemId"),rs.getString("name"),rs.getDouble("msrp"),rs.getDouble("salePrice"),rs.getInt("upc"),rs.getString("shortDescription"),rs.getString("brandName"),rs.getString("size"),rs.getString("color"),rs.getString("gender"),cartId)
        ).forEach(item -> items.add(item));

        return items;
    }


  public DriverManagerDataSource getDataSource() {
          DriverManagerDataSource dataSource = new DriverManagerDataSource();
          dataSource.setDriverClassName(driverClassName);
          dataSource.setUrl(url);
          dataSource.setUsername(dbUsername);
          dataSource.setPassword(dbPassword);
          return dataSource;

    }
    



 
}