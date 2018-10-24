package com.musicApp.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.musicApp.dao.*;
import com.musicApp.model.*;

@Service
public class CartService {
	
	//@Autowired
	private CartDAO cartDAO = new CartDAO();
	
 
	public String getMsg( String msg) { 
		return "Hello : " + msg;
	}

	public String getAllCarts(){
		String retString = "";
		Collection<Cart> carts = cartDAO.getAllCarts();
		for (Cart cart : carts) {
			retString += cart.toString();
		}

		return retString;
	}

	public String addItemToCart(int productId, String username){
		cartDAO.addItemToCart(productId, username); 
		return ""; 
	}


	public String removeItem(int cartId, int productId){
		cartDAO.removeItem(cartId,productId); 
		return ""; 
	}

	public String buyItem(int cartId){
		cartDAO.buyItem(cartId); 
		return ""; 
	}

    public String getUserShoppingCart(String username){
		String retString = "";
		int cartId= cartDAO.getCartID(username);
		retString +=cartId; 
		retString+=" ";
		Collection<Item> items=cartDAO.getCartItems(cartId,username); 
		for (Item item : items) {
			retString += item.toString();
		}

		return retString;
	}

	public String getBuyers(int productId){
		String retString = "";
		Collection<Customer> customers=cartDAO.getBuyers(productId); 
		for (Customer customer : customers) {
			retString += customer.toString();
		}

		return retString; 
		 
	}
	


}