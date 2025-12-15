package com.gdn.qa.module.api.hooks;


import com.gdn.qa.module.api.utils.DatabaseHelper;
import io.cucumber.java.After;
import lombok.extern.log4j.Log4j2;
import com.gdn.qa.automation.core.context.StepDefinition;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
@StepDefinition
public class CucumberHooks {

    @Autowired
    DatabaseHelper databaseHelper;

    @After("@ClearTenantData")
    public void clearTenantData() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("tenants");
        databaseHelper.deleteAllGeneratedDataUsing(true, "createTenantRequest", "tenantId", false);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }

    @After("@ClearPlatformData")
    public void clearPlatformData() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("platforms");
        databaseHelper.deleteAllGeneratedDataUsing(true, "createPlatformRequest", "platformId", false);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }

    @After("@ClearClientData")
    public void clearClientData() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("clients");
        databaseHelper.deleteAllGeneratedDataUsing(false, "createClientResponse", "clientId", true);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }

    @After("@ClearRefreshTokenData")
    public void clearRefreshTokenData() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("refresh_tokens");
        databaseHelper.deleteAllGeneratedDataUsing(true, "generateAccessTokenRequest", "clientId", false);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }

    @After("@ClearSecretData")
    public void clearSecretData() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("secrets");
        databaseHelper.deleteAllGeneratedDataUsing(false, "createSecretResponse", "secretId", true);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }

    @After("@ClearSecretDataByClientId")
    public void clearSecretDataByClientId() throws Throwable {
        databaseHelper.connectToMongoWithName("tenant");
        databaseHelper.selectMongoCollectionWithName("secrets");
        databaseHelper.deleteAllGeneratedDataUsing(false, "createClientResponse", "clientId", true);
        databaseHelper.generatedDataIsDeletedFromDb();
        databaseHelper.closeDbConnection();
    }
}
