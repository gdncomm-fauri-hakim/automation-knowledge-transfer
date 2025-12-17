package com.gdn.qa.module.api.data;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data holder for BookStore operations in DemoQA API
 * This component stores book-related data across test steps
 * 
 * @author QA Automation Team
 */
@Data
@Component("com.gdn.qa.module.api.data.BookStoreData")
public class BookStoreData {
    
    // Book identifiers
    private String isbn;
}
