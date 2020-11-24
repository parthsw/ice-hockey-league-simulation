package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileValidator;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeagueFileValidatorTest {
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
    }

    @Test
    public void validateJsonSchemaValidTest() {
        ILeagueFileValidator leagueFileValidator = leagueFileHandlerFactory.createLeagueFileValidator();
        JSONObject leagueSchema = LeagueJsonMock.instance().leagueSchema();
        JSONObject leagueJson = LeagueJsonMock.instance().validLeagueJson();
        Assert.assertNull(leagueFileValidator.validateJsonSchema(leagueJson, leagueSchema));
    }

    @Test
    public void validateJsonSchemaInvalidTest() {
        ILeagueFileValidator leagueFileValidator = leagueFileHandlerFactory.createLeagueFileValidator();
        JSONObject leagueSchema = LeagueJsonMock.instance().leagueSchema();
        JSONObject invalidLeagueJson = LeagueJsonMock.instance().invalidLeagueJson();
        List<String> expectedError = new ArrayList<>();
        expectedError.add("#/leagueName: expected minLength: 1, actual: 0");
        Assert.assertEquals(expectedError, leagueFileValidator.validateJsonSchema(invalidLeagueJson, leagueSchema));
    }

}
