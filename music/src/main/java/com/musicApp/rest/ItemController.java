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
@Path("/items")
public class ItemController extends HttpServlet  {

	
	//@Autowired
	private ItemService itemService = new ItemService();

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
		String output = itemService.getMsg(msg);

		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces("text/plain")
	public String getAllItems() {
		return itemService.getAllItems();
	}


    @GET
    @Path("/search/{keyword}")
    @Produces("text/plain")
    public Response getItemsByKeyword(@PathParam("keyword") String word) {
        String output = itemService.getItemsByKeyword(word);
        return Response.status(200).entity(output).build();
    }


    @GET
    @Path("/{id}")
    public Response getItemByID(@PathParam("id") int id) {
        String output = itemService.getItemsByID(id);
        return Response.status(200).entity(output).build();
    }


}