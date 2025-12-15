package com.gdn.qa.module.api.automation.test.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component("com.gdn.qa.module.api.automation.test.models.responses.GenerateTokenResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateTokenResponse implements Serializable {
	private String token;
	private String expires;
	private String status;
	private String result;
}