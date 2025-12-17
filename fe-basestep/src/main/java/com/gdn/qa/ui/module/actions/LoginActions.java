package com.gdn.qa.ui.module.actions;

import com.gdn.qa.automation.core.models.other.Action;
import com.gdn.qa.automation.core.models.other.OnPage;
import com.gdn.qa.automation.core.ui.tasks.executor.UserAction;
import com.gdn.qa.ui.module.pages.BooksPage;
import com.gdn.qa.ui.module.pages.ProfilePage;
import com.gdn.qa.ui.module.pages.LoginPage;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.type.Type;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Map;

/**
 * Actions for Login functionality
 */
@Log4j2
@OnPage({ LoginPage.class, ProfilePage.class, BooksPage.class})
public class LoginActions extends UserAction {

    @Action(value = "^log in to demoqa$", allowAnonymousCall = true)
    public void doLogin(Map<String, Object> credentials) throws Throwable {
        if (!isLoggedIn()) {
            Object username = credentials.getOrDefault("username", "");
            username = username == null ? "" : username;
            Object password = credentials.getOrDefault("password", "");
            password = password == null ? "" : password;

            log.info("Performing login with username: {}", username);

            // Wait for page to be ready and username field to be visible
            user().attemptsTo(
                    WaitUntil.the(LoginPage.FIELD_INPUT_USERNAME,
                            WebElementStateMatchers.isCurrentlyVisible()));
            // Fill in credentials
            user().attemptsTo(
                    Type.theValue(username.toString()).into(LoginPage.FIELD_INPUT_USERNAME),
                    Type.theValue(password.toString()).into(LoginPage.FIELD_INPUT_PASSWORD));
            
            // Handle login button click with JavaScript fallback to avoid iframe interception
            try {
                // First try: Wait and scroll element into view
                user().attemptsTo(
                        WaitUntil.the(LoginPage.BUTTON_LOGIN,
                                WebElementStateMatchers.isCurrentlyEnabled()));
                
                // Scroll button into view to avoid any overlay issues
                ((org.openqa.selenium.JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});", 
                        LoginPage.BUTTON_LOGIN.resolveFor(user()));
                
                Thread.sleep(500); // Brief wait after scroll
                
                // Try normal click first
                user().attemptsTo(Click.on(LoginPage.BUTTON_LOGIN));
                
            } catch (Exception e) {
                // Fallback: Use JavaScript click if normal click fails due to iframe
                log.warn("Normal click failed, using JavaScript click. Error: {}", e.getMessage());
                ((org.openqa.selenium.JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].click();", 
                        LoginPage.BUTTON_LOGIN.resolveFor(user()));
            }
        } else {
            log.info("User is already logged in, skipping login");
        }
    }

    @Action(value = "^log out from demoqa$", allowAnonymousCall = true)
    public void doLogout() throws Throwable {
        if (isLoggedIn()) {
            log.info("Performing logout");
            try {
                user().attemptsTo(
                        WaitUntil.the(LoginPage.BUTTON_LOGOUT,
                                WebElementStateMatchers.isCurrentlyVisible()),
                        Click.on(LoginPage.BUTTON_LOGOUT));
            } catch (Exception e) {
                // Fallback: Use JavaScript click if normal click fails due to iframe
                log.warn("Normal click failed, using JavaScript click. Error: {}", e.getMessage());
                ((org.openqa.selenium.JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].click();",
                        LoginPage.BUTTON_LOGOUT.resolveFor(user()));
            }
        } else {
            log.info("User is already logged out, skipping logout");
        }
    }

    public boolean isLoggedIn() {
        try {
            // Ensure we have a driver and it's active  
            if (getDriver() == null) {
                log.warn("Driver is null, cannot check login status");
                return false;
            }
            
            // Check if userName cookie exists
            return getDriver().manage().getCookieNamed("userName") != null;
            
        } catch (Exception e) {
            log.warn("Error checking login status: {}", e.getMessage());
            return false;
        }
    }

    @Action(value = "^logged (in|out) demoqa$", allowAnonymousCall = true)
    public void isLogged(String state) throws Throwable {
        boolean expected = state.equalsIgnoreCase("in");
        boolean actual = isLoggedIn();
        
        log.info("Checking if user is logged {}: Expected={}, Actual={}", state, expected, actual);
        
        if (expected != actual) {
            throw new Exception(user().getName() + " is not logged " + state + " demoqa");
        }
    }

    @Action(value = "^search for book (.*)$")
    public void searchForBook(String bookTitle) throws Throwable {
        log.info("Searching for book: {}", bookTitle);
        user().attemptsTo(
                WaitUntil.the(BooksPage.SEARCH_BOX,
                        WebElementStateMatchers.isVisible()),
                Type.theValue(bookTitle).into(BooksPage.SEARCH_BOX));
    }
}
