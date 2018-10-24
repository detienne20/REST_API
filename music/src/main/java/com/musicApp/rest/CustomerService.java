package com.musicApp.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.musicApp.dao.*;
import com.musicApp.model.*;

@Service
public class CustomerService {
	
	//@Autowired
	private CustomerDAO customerDAO = new CustomerDAO();
	
 
	public String getMsg( String msg) { 
		return "Hello : " + msg;
	}

	public String getAllCustomers() {
		String retString = "";
		Collection<Customer> customers = customerDAO.getAllCustomers();
		for (Customer customer : customers) {
			retString += customer.toString();
		}

		return retString;
	}

	public String getCustomer(String username) {
		String retString = "";
		Collection<Customer> customers = customerDAO.getCustomer(username);
		for (Customer customer : customers) {
			retString += customer.toString();
		}
		return retString;
	}

	public int createCustomer(String fname,String lname,String username,String email) {
		int result=customerDAO.createCustomer(fname,lname,username,email); 
		return result; 
	}

	public void updateCustomer(String fname,String lname,String username,String email) {
		customerDAO.updateCustomer(fname,lname,username,email); 
	}

	public void deleteCustomer(String username){
		customerDAO.deleteCustomer(username); 

	}

	


}