package com.gdn.qa.module.api.data;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Data holder for User Account operations in DemoQA API
 * This component stores user-related data across test steps
 * 
 * @author QA Automation Team
 */
@Data
@Component("com.gdn.qa.module.api.data.UserData")
public class AccountData {


    // User identification
    private String userId;  // UUID from DemoQA

    // Authentication
    private String token;
}
