package com.gdn.qa.module.api.automation.test.api.interceptors;

import com.gdn.qa.automation.core.restassured.ServiceInterceptor;
import io.restassured.specification.RequestSpecification;
import com.gdn.qa.module.api.automation.test.properties.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com.gdn.qa.module.api.automation.test.api.interceptors.DemoQaInterceptor")
public class DemoQaInterceptor implements ServiceInterceptor {

    @Autowired
    private DefaultProperties properties;

    @Override
    public void prepare(RequestSpecification requestSpecification) {
        requestSpecification.log()
                .all()
                .header("content-type", "application/json")
                .queryParam("storeId", properties.getStoreId())
                .queryParam("channelId", properties.getChannelId())
                .queryParam("clientId", properties.getClientId())
                .queryParam("requestId", properties.getRequestId());
    }

    @Override
    public boolean isSupport(String serviceName) {
        return serviceName.equalsIgnoreCase("demoqa");
    }
}
