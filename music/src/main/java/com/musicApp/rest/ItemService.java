package com.musicApp.rest;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.ArrayList;


import com.musicApp.dao.*;
import com.musicApp.model.*;

@Service
public class ItemService {
	
	//@Autowired
	private ItemDAO itemDAO = new ItemDAO();
	
 
	public String getMsg( String msg) { 
		return "Hello : " + msg;
	}

	public String getAllItems(){
		String retString = "";
		Collection<Item> items = itemDAO.getAllItems();
		for (Item item : items) {
			retString += item.toString();
		}

		return retString;
	}

	public String getItemsByKeyword(String keyword){
		String retString = "";
		Collection<Item> items = itemDAO.getItemsByKeyword(keyword);
		for (Item item : items) {
			retString += item.toString();
		}
		return retString;
	}

	public String getItemsByID(int id){
		String retString = "";
		Collection<Item> items = itemDAO.getItemByID(id);
		for (Item item : items) {
			retString += item.toString();
		}

		return retString;

	}


}