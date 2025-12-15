package com.gdn.qa.module.api.automation.test.api.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.gdn.qa.automation.core.json.JsonApi;
import com.gdn.qa.automation.core.restassured.ResponseApi;
import com.gdn.qa.automation.core.restassured.ServiceApi;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import com.gdn.qa.module.api.automation.test.models.request.LoginRequest;
import com.gdn.qa.module.api.automation.test.models.responses.GenerateTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller("com.gdn.qa.module.api.automation.test.api.controllers.AccountController")
public class AccountController extends ServiceApi {
    @Autowired
    private JsonApi jsonApi;

    // Write code here for creating user API - Demo at Session

    public ResponseApi<GenerateTokenResponse> generateToken(LoginRequest request) {
        Response response = service("demoqa")
                .body(request)
                .post("Account/v1/GenerateToken");
        log.info("Response is {}", response.getBody().asPrettyString());
        return jsonApi.fromJson(response, new TypeReference<>() {
        });
    }

    public ResponseApi<Boolean> authorizedAccount(LoginRequest request) {
        Response response = service("demoqa")
                .body(request)
                .post("Account/v1/Authorized");
        log.info("Response is {}", response.getBody().asPrettyString());
        return jsonApi.fromJson(response, new TypeReference<>() {
        });
    }
}
