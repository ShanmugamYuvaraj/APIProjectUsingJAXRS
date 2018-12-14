package com.qapitol.qa.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qapitol.qa.pojos.Employee;


@Path("employees/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface Employees {
	
	@GET
	List<Employee> getAllEmployees();
	
	@GET
	@Path("{id}")
	Employee getEmployee(@PathParam ("id") Integer id);
	
	@POST
	Employee saveEmployee(Employee employee);
	
	@PUT
	@Path("{id}")
	Employee updateEmployee(@PathParam ("id") Integer id,Employee employee);
	
	@DELETE
	@Path("{id}")
	Employee deleteEmployee(@PathParam ("id") Integer id);
}
