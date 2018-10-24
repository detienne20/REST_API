package com.musicApp.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.musicApp.model.*;
import java.util.Collection;
import java.util.ArrayList;

@Repository
public class CustomerDAO {

    private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_example";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
	
	
    public CustomerDAO() {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }
 
    public Collection<Customer> getAllCustomers(){
        Collection<Customer> customers = new ArrayList<Customer>();
        this.jdbcTemplate.query(
                "SELECT * FROM customers", new Object[] { },
                (rs, rowNum) -> new Customer(rs.getInt("id"),rs.getString("fname"),rs.getString("lname"),rs.getString("username"),rs.getString("email"))
                    ).forEach(customer -> customers.add(customer));

        return customers;
    }
	
    public Collection<Customer> getCustomer(String username){
        Collection<Customer> customers = new ArrayList<Customer>();
        this.jdbcTemplate.query(
                "SELECT fname,lname,username, email FROM customers where username= ?", new Object[] { username},
                (rs, rowNum) -> new Customer(rs.getString("fname"),rs.getString("lname"),username,rs.getString("email")) 
                    ).forEach(customer -> customers.add(customer));

        return customers;
    }

    public String createCustomer(String fname,String lname,String username,String email) {
        String curr_username=""; 
        curr_username= this.jdbcTemplate.queryForObject("SELECT username FROM customers WHERE username=? ",new Object[] {username},String.class);
        if (curr_username!=""){
             this.jdbcTemplate.update("INSERT into customers (fname, lname,username, email) values (?,?)", 
            new Object[] {fname, lname, email,username});

        }
        return curr_username; 
    }

    public void updateCustomer(String fname,String lname,String username,String email) {
        this.jdbcTemplate.update("UPDATE customers SET fname= ?,lname=? ,email= ? WHERE username = ?",
            new Object[] {fname, lname, email,username});
    }

    public void deleteCustomer(String username){
         this.jdbcTemplate.update("DELETE FROM customers where username=? ",
            new Object[]{username});
      
    }


	/***NOTE: For simplicity, other CRUD operations have been removed from this example.***/

	public DriverManagerDataSource getDataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		  dataSource.setDriverClassName(driverClassName);
		  dataSource.setUrl(url);
		  dataSource.setUsername(dbUsername);
		  dataSource.setPassword(dbPassword);
		  return dataSource;

    }
	
	

}
