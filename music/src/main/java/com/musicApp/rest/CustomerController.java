package com.musicApp.rest;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import org.springframework.web.context.support.SpringBeanAutowiringSupport; 
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;


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
@Path("/customers")
public class CustomerController extends HttpServlet  {

	
	//@Autowired
	private CustomerService customerService = new CustomerService();

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
		String output = customerService.getMsg(msg);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Produces("text/plain")
	public String getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GET
	@Path("/{username}")
	@Produces("text/plain")
	public Response getCustomer(@PathParam("username") String username) {
		String output = customerService.getCustomer(username);
		return Response.status(200).entity(output).build();
	}

	@POST 
	@Path("/{fname}/{lname}/{username}/{email}")
	public Response createCustomer(@QueryParam("fname") String fname, @QueryParam("lname")  String lname,@QueryParam("username") String username,@QueryParam("email") String email) {
		int stat=customerService.createCustomer(fname,lname,username, email); 
		if (stat!=0){
			return Response.status(409).build(); 
		}
		return Response.status(200).build(); 
	}	

	@PUT 
	@Path("/{fname}/{lname}/{username}/{email}")
	public Response updateCustomer(@QueryParam("fname") String fname, @QueryParam("lname")  String lname,@QueryParam("username") String username,@QueryParam("email") String email) {
			customerService.updateCustomer(fname,lname,username, email); 
			return Response.status(200).build();
	}	

	@DELETE 
	@Path("/{username}")
	public Response deleteCustomer(@PathParam("username") String username) {
			customerService.deleteCustomer(username); 
			return Response.status(200).build();
	}
	

}