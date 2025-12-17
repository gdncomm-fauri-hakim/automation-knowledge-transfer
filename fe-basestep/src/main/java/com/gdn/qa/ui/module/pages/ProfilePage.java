package com.gdn.qa.ui.module.pages;

import com.gdn.qa.automation.core.models.other.DesktopWebPage;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Page object for DemoQA Profile Page
 * URL: https://demoqa.com/profile
 */
@DesktopWebPage(page = "profile")
public class ProfilePage extends PageObject {

    // Header Elements
    public static final Target LABEL_USERNAME = Target.the("logged in username label")
            .located(By.cssSelector("#userName-value"));

    public static final Target BUTTON_LOGOUT = Target.the("button logout").located(By.cssSelector("button#submit"));

    // Buttons
    public static final Target BUTTON_GO_TO_BOOK_STORE = Target.the("go to book store button")
            .located(By.cssSelector("#gotoStore"));

    // Profile Menu
    public static final Target MENU_PROFILE = Target.the("profile menu").located(By.cssSelector("#item-3"));

    public static final Target MENU_BOOK_STORE = Target.the("book store menu").located(By.cssSelector("#item-2"));
}
