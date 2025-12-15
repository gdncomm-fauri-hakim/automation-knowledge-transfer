package com.gdn.qa.module.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("com.gdn.qa.module.api.properties.DefaultProperties")
@ConfigurationProperties(prefix = "default")
public class DefaultProperties {
    // DemoQA default test credentials
    private String username;
    private String password;
    
    // DemoQA API base URL
    private String baseUrl;
    
    // Common test data
    private String requestId;
    private String channelId;
}
