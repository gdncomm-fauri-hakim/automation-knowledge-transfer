package com.gdn.qa.module.api.automation.test.models.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component("com.gdn.qa.module.api.automation.test.models.request.LoginRequest")
public class LoginRequest implements Serializable {
	private String userName;
	private String password;
}