/*package com.qapitol.qa.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.qapitol.qa.api.LoginService;
import com.qapitol.qa.pojos.LoginResponse;

public class FernlinkTest {

	LoginService login;
	Client client;
	String AccessToken;
	@BeforeClass
	public void config() {
    	List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());
        //providers.add(new AllureHttpInterceptor());
        
        
        login = JAXRSClientFactory.create("http://stage.auth.fernlink.com", LoginService.class, providers);
        client = WebClient.client(login);
    }
	@Test
	public void testLogin(){
		LoginResponse loginResponse = login.doLogin("bala.krishnan@qapitol.com","12345","console","password");
		AccessToken =loginResponse.getAccess_token();
		System.out.println("---"+AccessToken);
	}
}
*/