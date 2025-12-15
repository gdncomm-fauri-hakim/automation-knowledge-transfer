package com.gdn.qa.module.api.automation.test.pages;

import com.gdn.qa.module.api.automation.test.constants.UIConstant;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("com.gdn.qa.module.api.automation.test.pages.BooksPage")
public class BooksPage extends PageObject {

    private static final String XPATH_WITH_ID = "//*[@id='%s']";
    private static final String FOLLOWING_SIBLING_LABEL = "/following-sibling::label";

    private String generatePathWithId(String idIdentifier) {
        return String.format(XPATH_WITH_ID, UIConstant.getid.get(idIdentifier));

    }
    public void clickWithId(String idIdentifier) {
        WebElementFacade element = find(By.xpath(this.generatePathWithId(idIdentifier)));
        element.waitUntilPresent().click();
    }

    public Boolean validateUserLoggedIn(String userName) {
        WebElementFacade element = find(By.xpath(this.generatePathWithId("Username Label") + FOLLOWING_SIBLING_LABEL));
        log.info("Element text is {}", element.getText());
        return element.waitUntilPresent().getText().equals(userName);
    }
}
