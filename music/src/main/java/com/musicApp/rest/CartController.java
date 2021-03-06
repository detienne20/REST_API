package com.musicApp.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport; 
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import java.util.Collection;
import java.util.ArrayList;


import com.musicApp.dao.*;
import com.musicApp.model.*;

@Controller
@Path("/carts")
public class CartController extends HttpServlet  {

	
	//@Autowired
	private CartService cartService = new CartService();

	 public void init(ServletConfig config) {
		 try{
			super.init(config);
			SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
			  config.getServletContext());
		  }catch(ServletException e){
		  }
	 }
		  
	@GET
	@Path("/hello/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = cartService.getMsg(msg);
		return Response.status(200).entity(output).build();
	}


	@GET
	@Produces("text/plain")
	public String getAllCarts() {
		return cartService.getAllCarts();
	}
 

	@POST
	@Path("/{productId}/{username}")
	public Response AddItem(@QueryParam("productId") int id, @QueryParam("username") String user){
		cartService.addItemToCart(id, user); 
		return Response.status(200).build();

	}

	@DELETE 
	@Path("/{cartId}/{productId}")
	public Response removeItem(@QueryParam("cartId") int cId, @QueryParam("productId") int pId) {
			cartService.removeItem(cId,pId); 
			return Response.status(200).build();
	}	

	@PUT 
	@Path("/purchase/{cartId}")
	public Response BuyItem(@QueryParam("cartId") int id) {
		cartService.buyItem (id);
		return Response.status(200).build();
	}
   
	@GET
	@Path("/{key}")
	public Response Both(@PathParam("key") String key){
		String output=""; 
		boolean id=isInteger(key); 
		if (id==true){
			int num=Integer.parseInt(key);
			output=cartService.getBuyers(num);
		}
		else {
			output=cartService.getUserShoppingCart(key);
		}
		return Response.status(200).entity(output).build();

	}

	public boolean isInteger( String input ) {
   	 try {
       	Integer.parseInt( input );
       	 return true;
   	 }
    	catch( Exception e ) {
       	 return false;
    	}
	}

}