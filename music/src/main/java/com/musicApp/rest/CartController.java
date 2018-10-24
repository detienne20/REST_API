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
	public Response AddItem(@QueryParam("productId") int productId, @QueryParam("username") String username){
		cartService.addItemToCart(productId, username); 
		return Response.status(200).build();

	}

	@DELETE 
	@Path("/{cartId}/{productId}")
	public Response removeItem(@PathParam("cartId") int cartId, @PathParam("productId") int productId) {
			cartService.removeItem(cartId,productId); 
			return Response.status(200).build();
	}	

	@PUT 
	@Path("/purchase/{cartId}")
	public Response BuyItem(@PathParam("param") int cartId) {
		cartService.buyItem (cartId);
		return Response.status(200).build();
	}

	@GET
	@Path("/username")
	public Response getUserShoppingCart(@PathParam("param") String username) {
		String output = cartService.getUserShoppingCart(username);
		return Response.status(200).entity(output).build();
	}

	@GET 
	@Path("/{productID}")
	public Response getBuyers(@PathParam("param") int productId) {
		String output = cartService.getBuyers(productId);
		return Response.status(200).entity(output).build();
	}


}