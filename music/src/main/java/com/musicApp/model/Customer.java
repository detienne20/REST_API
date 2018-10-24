package com.musicApp.model;

import java.util.Collection;
import org.json.*;

public class Customer {
    private int customerId;
    private String fname;
    private String lname; 
    private String username; 
    private String email; 
    private int cartId;  
 
    public Customer(String fname, String lname,String username,String email){
        this.fname = fname;
        this.lname= lname;
        this.username= username; 
        this.email= email; 
    }

    public Customer(int customerId, String fname, String lname,String username,String email){
        this.customerId = customerId;
        this.fname = fname;
        this.lname= lname;
        this.username= username; 
        this.email= email; 
    }

   /* public Customer(int customerId, String fname, String lname,String username,String email, int cartId){
        this.customerId = customerId;
        this.fname = fname;
        this.lname= lname;
        this.username= username; 
        this.email= email; 
        this.cartId=cartId; 
    }
    */


    public Customer(String username){
        this.username= username; 
    }

    public int getCustomerId(){
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return fname;
    }

    public void setFirstname(String fname) {
        this.fname = fname;
    }

    public String getLastname() {
        return lname;
    }

    public void setLastname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
    }
    
    /*
    public int getCartId() {
        return cartId; 
    }

    public void setCartId(int cartId) {
        this.cartId= cartId;
    }
    */


    @Override
    public String toString() {

        return String.format(
                "Customer[customerId='%d', fname='%s', lname='%s',username='%s',email='%s']",
                customerId, fname, lname, username,email);
    }

}
