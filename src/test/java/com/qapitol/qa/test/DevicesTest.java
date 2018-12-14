package com.qapitol.qa.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.Client;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.qapitol.qa.TestBase.TestBase;
import com.qapitol.qa.api.DeviceService;
import com.qapitol.qa.pojos.DeviceResponse;
;
public class DevicesTest extends TestBase{
	DeviceService login;
	Client client;
	String accessToken;
	DeviceResponse response;
	String deviceID;
	
	@BeforeClass
	public void getToken() {
		List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());
        
        login = JAXRSClientFactory.create(prop.getProperty("URLGenerateToken"), DeviceService.class, providers);
        client = WebClient.client(login);
        DeviceResponse responce = login.doLogin(prop.getProperty("email"),prop.getProperty("password"),prop.getProperty("clientID"),prop.getProperty("grantType"));
		accessToken =responce.getAccess_token();
	//	System.out.println("---"+accessToken);
	}
	

	@BeforeMethod
	public void header() {
		
		List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());
        
        final MultivaluedMap<String, String> obj = new MultivaluedHashMap<String, String>();

        obj.add("Authorization", "Bearer "+accessToken);
        obj.add("x-realm-name", "demo_account");

        login = JAXRSClientFactory.create(prop.getProperty("URLApplication"), DeviceService.class, providers);
        client = WebClient.client(login).headers(obj);
      		
	}
	
	@Test(priority=1)
	public void createDevice() {
		response=new DeviceResponse();
		response.setBroker(prop.getProperty("CreateDeviceBroker"));
		response.setDeviceFamily(prop.getProperty("CreateDeviceFamily"));
		response.setSerialNumber(prop.getProperty("CreateDeviceSerialNumber"));
		response.setName(prop.getProperty("CreateDeviceName"));
		
		Response res=login.create(response);
		assertEquals(res.getStatus(), RESPONSE_STATUS_CODE_200, " status code is not 200");
		
		JSONObject jsonObj = new JSONObject(res.readEntity(String.class));
		deviceID = (String) jsonObj.get("deviceId");
		assertNotNull(deviceID, prop.getProperty("DeviceUserMessage"));
		
	}
	
	@Test(priority=2)
	public void getDevice() {
		Response res=login.getdevice(deviceID);
		assertEquals(res.getStatus(), RESPONSE_STATUS_CODE_200, " status code is not 200");
		
		JSONObject obj=new JSONObject(res.readEntity(String.class));
		assertNotNull(obj.get("deviceId"), prop.getProperty("DeviceUserMessage"));
	}
	
	@Test(priority=3)
	public void updateDevice() {
		
		response=new DeviceResponse();
		response.setBroker(prop.getProperty("CreateDeviceBroker"));
		response.setDeviceFamily(prop.getProperty("CreateDeviceFamily"));
		response.setSerialNumber(prop.getProperty("UpdateSerialNumber"));
		response.setName(prop.getProperty("CreateDeviceName"));
		
		Response res=login.updateDevice(deviceID, response);
		assertEquals(res.getStatus(), RESPONSE_STATUS_CODE_200, " status code is not 200");
		
		JSONObject jsonObj = new JSONObject(res.readEntity(String.class));
		String updatedSerialNumber = (String) jsonObj.get("serialNumber");
		assertEquals(prop.getProperty("UpdateSerialNumber"), updatedSerialNumber, " Serial number is not updated ");
	}
	 
	@Test(priority=4)
	public void deleteDevice() {
		Response res=login.delete(deviceID);
		assertEquals(res.getStatus(), RESPONSE_STATUS_CODE_204, " status code is not 204");
		
		Response res1=login.getdevice(deviceID);
		System.out.println("8888 "+res1.getStatus());
		JSONObject jsonObj = new JSONObject(res1.readEntity(String.class));
		System.out.println(jsonObj+"9999999");
		assertEquals(res1.getStatus(), RESPONSE_STATUS_CODE_404, " status code is not 404");
	}

}
