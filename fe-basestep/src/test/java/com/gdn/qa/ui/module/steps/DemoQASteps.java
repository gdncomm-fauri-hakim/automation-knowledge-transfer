package com.gdn.qa.ui.module.steps;

import com.gdn.qa.automation.core.context.StepDefinition;
import com.gdn.qa.automation.core.ui.actor.ActorManager;
import com.gdn.qa.automation.core.utils.helper.commands.CommandExecutor;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Step definitions for DemoQA automation
 */
@StepDefinition
public class DemoQASteps {

    @Autowired
    private ActorManager actor;

    @Given("^(\\S+) using (\\S+) in (desktop|mobile) and logged (in|out) \\\"(\\S+)\\\" page$")
    public void userInBrowserAndLoggedInPage(
            String actorName,
            String application,
            String platform,
            String state,
            String page) throws Throwable {

        page = CommandExecutor.executeCommand(page);
        page = page.trim();

        if (this.actor.getCurrentApplication() == null ||
                !this.actor.getCurrentApplication().equalsIgnoreCase(application)) {
            this.actor.named(actorName).using(application, platform);
        }

        this.actor.open(page);
        
        System.out.println("DEBUG: state received is '" + state + "'");

        if ("in".equalsIgnoreCase(state)) {
            java.util.Map<String, Object> credential = new java.util.HashMap<>();
            credential.put("username",
                    CommandExecutor.executeCommand("properties(default.username)"));
            credential.put("password",
                    CommandExecutor.executeCommand("properties(default.password)"));

            this.actor.doAction("log in to demoqa", credential);
        } else {
            this.actor.doAction("log out from demoqa");
        }
    }
}
