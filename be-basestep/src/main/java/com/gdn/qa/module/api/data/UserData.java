package com.gdn.qa.module.api.data;

import lombok.Data;
import org.springframework.context.annotation.Scope;
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
@Scope("cucumber-glue")
public class UserData {
    
    // User credentials
    private String username;
    private String password;
    
    // User identification
    private String userId;  // UUID from DemoQA
    
    // Authentication
    private String token;
    private String expires;
    private Boolean isActive;
    
    // Response data storage
    private Map<String, Object> createUserResponse;
    private Map<String, Object> getUserResponse;
    private Map<String, Object> generateTokenResponse;
    private Map<String, Object> authorizedResponse;
    
    /**
     * Reset all user data - useful for cleanup between scenarios
     */
    public void resetUserData() {
        this.username = null;
        this.password = null;
        this.userId = null;
        this.token = null;
        this.expires = null;
        this.isActive = null;
        this.createUserResponse = null;
        this.getUserResponse = null;
        this.generateTokenResponse = null;
        this.authorizedResponse = null;
    }
}
