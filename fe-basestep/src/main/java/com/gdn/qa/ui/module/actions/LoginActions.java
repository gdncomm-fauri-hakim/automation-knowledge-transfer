package com.gdn.qa.ui.module.actions;

import com.gdn.qa.automation.core.models.other.Action;
import com.gdn.qa.automation.core.models.other.OnPage;
import com.gdn.qa.automation.core.ui.tasks.executor.UserAction;
import com.gdn.qa.automation.core.utils.helper.GlobalHelper;
import com.gdn.qa.ui.module.pages.BooksPage;
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
@OnPage({ LoginPage.class, BooksPage.class })
public class LoginActions extends UserAction {

    public boolean isLoggedIn() {
        return getDriver() != null && !GlobalHelper.isBlank(getDriver().manage()
                .getCookieNamed("userName"));
    }

    @Action(value = "^log in to demoqa$", allowAnonymousCall = true)
    public void doLogin(Map<String, Object> credentials) throws Throwable {
        Object username = credentials.getOrDefault("username", "");
        username = username == null ? "" : username;
        Object password = credentials.getOrDefault("password", "");
        password = password == null ? "" : password;

        log.info("Performing login with username: {}", username);

        user().attemptsTo(
                WaitUntil.the(LoginPage.FIELD_INPUT_USERNAME,
                        WebElementStateMatchers.isCurrentlyVisible()),
                Type.theValue(username.toString()).into(LoginPage.FIELD_INPUT_USERNAME),
                Type.theValue(password.toString()).into(LoginPage.FIELD_INPUT_PASSWORD),
                Click.on(LoginPage.BUTTON_LOGIN));
    }

    @Action(value = "^log out from demoqa$", allowAnonymousCall = true)
    public void doLogout() throws Throwable {
        log.info("Performing logout");
        user().attemptsTo(
                WaitUntil.the(BooksPage.BUTTON_LOGOUT,
                        WebElementStateMatchers.isCurrentlyVisible()),
                Click.on(BooksPage.BUTTON_LOGOUT));
    }

    @Action(value = "^logged (in|out) demoqa$", allowAnonymousCall = true)
    public void isLogged(String state) throws Throwable {
        boolean expected = state.equalsIgnoreCase("in");
        if (expected != isLoggedIn()) {
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
