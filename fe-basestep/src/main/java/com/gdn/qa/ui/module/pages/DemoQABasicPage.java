package com.gdn.qa.ui.module.pages;

import com.gdn.qa.automation.core.ui.basic.BasicPage;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Base page for DemoQA pages
 * Contains common loading elements
 */
public class DemoQABasicPage extends BasicPage {
    public static final By LOADING_WRAPPER = By.cssSelector(".rt-loading");
}
