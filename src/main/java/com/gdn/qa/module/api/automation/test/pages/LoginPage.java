package com.gdn.qa.module.api.automation.test.pages;

import com.gdn.qa.module.api.automation.test.constants.UIConstant;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component("com.gdn.qa.module.api.automation.test.pages.LoginPage")
public class LoginPage extends PageObject {
    private static final String XPATH_WITH_ID = "//*[@id='%s']";

    private String generatePathWithId(String idIdentifier) {
        return String.format(XPATH_WITH_ID, UIConstant.getid.get(idIdentifier));

    }
    public void typeAtInputField(String input, String idIdentifier) {
        WebElementFacade element = find(By.xpath(this.generatePathWithId(idIdentifier)));
        element.waitUntilPresent().sendKeys(input);
    }
    public Boolean elementExistChecker(String idIdentifier) {
        WebElementFacade element = find(By.xpath(this.generatePathWithId(idIdentifier)));
        return element.waitUntilPresent().isPresent();
    }

}
