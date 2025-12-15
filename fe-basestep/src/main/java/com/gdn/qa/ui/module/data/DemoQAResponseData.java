package com.gdn.qa.ui.module.data;

import com.gdn.qa.automation.core.models.DefaultData;
import com.gdn.qa.automation.core.models.ResponseData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * Data holder for DemoQA test responses
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ResponseData
@Component("com.gdn.qa.ui.module.data.DemoQAResponseData")
public class DemoQAResponseData extends DefaultData {
    private String loggedInUsername;
    private boolean loginSuccessful;
}
