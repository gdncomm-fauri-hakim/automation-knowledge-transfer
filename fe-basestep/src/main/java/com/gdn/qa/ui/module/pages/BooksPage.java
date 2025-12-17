package com.gdn.qa.ui.module.pages;

import com.gdn.qa.automation.core.models.other.DesktopWebPage;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Page object for DemoQA books Page
 * URL: https://demoqa.com/books
 */
@DesktopWebPage(page = "books")
public class BooksPage extends PageObject {
    // Book Store Elements
    public static final Target SEARCH_BOX = Target.the("search box").located(By.cssSelector("input#searchBox"));

    public static final Target TABLE_BOOKS = Target.the("books table").located(By.cssSelector(".rt-table"));

    public static final Target ROWS_BOOKS = Target.the("book rows").located(By.cssSelector(".rt-tbody .rt-tr-group"));

}
