package com.qapitol.qa.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeviceResponse {
	private String access_token;
	private String expires_in;
	private Integer refresh_expires_in;
	private String refresh_token;
	private String token_type;
	@JsonProperty("not-before-policy")
	private Integer not_before_policy;
	private String session_state;
	private String broker;
	private String deviceFamily;
	private String name;
	private String serialNumber;
	
}
