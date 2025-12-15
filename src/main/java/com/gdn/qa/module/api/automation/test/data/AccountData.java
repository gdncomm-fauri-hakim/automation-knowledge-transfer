package com.gdn.qa.module.api.automation.test.data;

import com.gdn.qa.automation.core.restassured.ResponseApi;
import lombok.Data;
import com.gdn.qa.module.api.automation.test.models.request.LoginRequest;
import com.gdn.qa.module.api.automation.test.models.responses.GenerateTokenResponse;
import org.springframework.stereotype.Component;

@Data
@Component("com.gdn.qa.module.api.automation.test.data.AccountData")
public class AccountData {
    private String userId;
    private Boolean authorizedResponse;
    private LoginRequest loginRequest;
    private ResponseApi<GenerateTokenResponse> generateTokenResponse;
    private ResponseApi<Boolean> authorizedAccount;
    // Later add ResponseApi for create account API - Demo at Session
}
