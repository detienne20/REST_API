package com.musicApp.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.musicApp.model.*;
import java.util.Collection;
import java.util.ArrayList;


@Repository
public class CartDAO {

    private JdbcTemplate jdbcTemplate;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/db_example";
    private static final String dbUsername = "springuser";
    private static final String dbPassword = "ThePassword";
	
	
    public CartDAO() {
		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
    }

    //@Autowired	
    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }
 
    public Collection<Cart> getAllCarts(){
        Collection<Cart> carts = new ArrayList<Cart>();
        this.jdbcTemplate.query(
                "SELECT * FROM carts", new Object[] { },
                (rs, rowNum) -> new Cart(rs.getInt("cartId"),rs.getString("username"))
                    ).forEach(cart -> carts.add(cart));

        return carts;
    }

    public void addItemToCart(int itemId, String username) {
        int cartId=-1; 
        Collection<Cart> carts = new ArrayList<Cart>();
        this.jdbcTemplate.query(
                "SELECT * FROM carts WHERE username=? ", new Object[] {username},
                (rs, rowNum) -> new Cart(rs.getInt("cartId"))
                ).forEach(cart -> carts.add(cart));

        for (Cart curr : carts) {
            cartId=curr.getCartId(); 
        }


       if (cartId!=-1){
         int count= this.jdbcTemplate.queryForObject("SELECT COUNT(cartId) FROM carts",int.class);
         count=(count+1); 
         this.jdbcTemplate.update("INSERT into purchase (cartId, itemId,binaryNum) values (count,itemId,0)"); 
        }
       else{ 
         this.jdbcTemplate.update("INSERT into carts (cartId,username,binaryNum) values (cartId,username,0)"); 
         this.jdbcTemplate.update("INSERT into purchase (cartId, itemId,binaryNum) values (cartId,itemId,0)"); 
       }
       
    }
    

    public void removeItem(int cartId, int productId){
         this.jdbcTemplate.update("DELETE FROM purchase WHERE cardId=? AND itemId=?", cartId,productId);  

    }

    public void buyItem(int id){   
       this.jdbcTemplate.update("UPDATE purchase SET binaryNum=1 WHERE cartId=?", id); 
       this.jdbcTemplate.update("UPDATE carts SET binaryNum=1 WHERE cartId=?", id); 
    }


    public int getCartID(String username){
       int cId=-1; 
        Collection<Cart> carts = new ArrayList<Cart>();
        this.jdbcTemplate.query(
                "SELECT cartId FROM carts WHERE username=? ", new Object[] {username},
                (rs, rowNum) -> new Cart(rs.getInt("cartId"))
                ).forEach(cart -> carts.add(cart));
        
       for (Cart curr : carts) {
            cId=curr.getCartId(); 
        }  

        return cId; 
    }
    

    public Collection<Item> getCartItems(int cartId, String username){
        Collection<Item> items = new ArrayList<Item>();
        this.jdbcTemplate.query(
                "SELECT * FROM products INNER JOIN purchase ON purchase.itemId=products.itemId INNER JOIN carts ON carts.cartId=purchase.cartId WHERE carts.cartId=? AND username= ?", new Object[] {cartId, username},
                (rs, rowNum) -> new Item(rs.getInt("itemId"),rs.getString("name"),rs.getDouble("msrp"),rs.getDouble("salePrice"))
                    ).forEach(item -> items.add(item));

        return items; 
    }


    public Collection<Customer> getBuyers(int productId){
        Collection<Cart> carts = new ArrayList<Cart>();
        Collection<Customer> customers = new ArrayList<Customer>();
        
        this.jdbcTemplate.query(
                "SELECT * FROM carts INNER JOIN purchase ON carts.cartId=purchase.cartId WHERE purchase.binaryNum=1", new Object[] { },
                (rs, rowNum) -> new Cart(rs.getInt("username"))
                ).forEach(cart -> carts.add(cart));



        for (Cart curr : carts) {
            String username=curr.getUsername(); 
            this.jdbcTemplate.query(
                   "SELECT * FROM customers WHERE username=? ", new Object[] {username },
                     (rs, rowNum) -> new Customer(rs.getString("fname"),rs.getString("lname"),rs.getString("username"),rs.getString("email"))
                    ).forEach(customer -> customers.add(customer)); 

         } 

         return customers; 
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
