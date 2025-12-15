package com.gdn.qa.module.api.automation.test.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("properties.com.gdn.qa.module.api.automation.test.DefaultProperties")
@ConfigurationProperties(prefix = "param")
public class DefaultProperties {
    private String storeId;
    private String requestId;
    private String channelId;
    private String clientId;
    private String requestParams;
}
