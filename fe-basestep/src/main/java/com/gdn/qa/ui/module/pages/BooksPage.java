package com.gdn.qa.ui.module.pages;

import com.gdn.qa.automation.core.models.other.DesktopWebPage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Page object for DemoQA Books/Profile Page
 * URL: https://demoqa.com/books or https://demoqa.com/profile
 */
@DesktopWebPage(page = "books")
public class BooksPage extends DemoQABasicPage {

    // Header Elements
    public static final Target LABEL_USERNAME = Target.the("logged in username label")
            .located(By.cssSelector("#userName-value"));

    public static final Target BUTTON_LOGOUT = Target.the("button logout").located(By.cssSelector("button#submit"));

    // Book Store Elements
    public static final Target SEARCH_BOX = Target.the("search box").located(By.cssSelector("input#searchBox"));

    public static final Target TABLE_BOOKS = Target.the("books table").located(By.cssSelector(".rt-table"));

    public static final Target ROWS_BOOKS = Target.the("book rows").located(By.cssSelector(".rt-tbody .rt-tr-group"));

    // Buttons
    public static final Target BUTTON_GO_TO_BOOK_STORE = Target.the("go to book store button")
            .located(By.cssSelector("#gotoStore"));

    // Profile Menu
    public static final Target MENU_PROFILE = Target.the("profile menu").located(By.cssSelector("#item-3"));

    public static final Target MENU_BOOK_STORE = Target.the("book store menu").located(By.cssSelector("#item-2"));
}
