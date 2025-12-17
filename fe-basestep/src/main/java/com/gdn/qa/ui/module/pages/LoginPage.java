package com.gdn.qa.ui.module.pages;

import com.gdn.qa.automation.core.models.other.DesktopWebPage;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Page object for DemoQA Login Page
 * URL: https://demoqa.com/login
 */
@DesktopWebPage(page = "login")
public class LoginPage extends PageObject {

    // Login Form Elements
    public static final Target FIELD_INPUT_USERNAME = Target.the("field input username")
            .located(By.cssSelector("input#userName"));

    public static final Target FIELD_INPUT_PASSWORD = Target.the("field input password")
            .located(By.cssSelector("input#password"));

    public static final Target BUTTON_LOGIN = Target.the("button login").located(By.cssSelector("button#login"));

    public static final Target BUTTON_NEW_USER = Target.the("button new user")
            .located(By.cssSelector("button#newUser"));

    public static final Target LOADING_LABEL = Target.the("loading label")
            .located(By.cssSelector("#loading-label"));

    public static final Target BUTTON_LOGOUT = Target.the("button logout").located(By.cssSelector("button#submit"));

    // Error/Success Messages
    public static final Target LABEL_ERROR_MESSAGE = Target.the("error message").located(By.cssSelector("p#name"));

    public static final Target LABEL_INVALID_CREDENTIALS = Target.the("invalid credentials message")
            .located(By.cssSelector("#name"));

    // Page Header
    public static final Target HEADER_LOGIN_FORM = Target.the("login form header")
            .located(By.cssSelector(".login-wrapper h5"));
}
