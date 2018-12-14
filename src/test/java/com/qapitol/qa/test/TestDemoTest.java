package com.qapitol.qa.test;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.qapitol.qa.api.Employees;
import com.qapitol.qa.pojos.Employee;


public class TestDemoTest {

	//WebClient client = WebClient.create("http://localhost:8081");
	//Posts posts  = client.get(Posts.class);
	//Posts posts = RestProxyFactory.createProxy(Posts.class, "http://localhost:3000");
	//Posts posts = JAXRSClientFactory.create("http://localhost:8081", Posts.class);
	Employees employees;
	
	@BeforeClass
    public void config() {
    	List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());
        //providers.add(new AllureHttpInterceptor());
        
        employees = JAXRSClientFactory.create("http://localhost:3000", Employees.class, providers);
        Client client = WebClient.client(employees);
    }
	@Test
	public void testGetAll() {
		assertEquals(3,employees.getAllEmployees().size());	
	}

	@Test
	public void testSavePost() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setFirst_name("Bharat");
		employee.setLast_name("Malviya");
		employee.setEmail("bharat.k@qapitol.com");
		assertEquals(employee,employees.saveEmployee(employee));	
	}

	
	@Test
	public void testGetEmployee() {
		assertEquals("Bharat",employees.getEmployee(10).getFirst_name());	
	}
	
	@Test
	public void testUpdatePost() {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setFirst_name("Bharat");
		employee.setLast_name("Malviya");
		employee.setEmail("bkm@qapitol.com");
		assertEquals(employee,employees.updateEmployee(10,employee));
	}
	
	@Test
	public void testDeletePost() {
		assertEquals("Employee(id=null, first_name=null, last_name=null, email=null)",employees.deleteEmployee(10).toString());	
	}
}
