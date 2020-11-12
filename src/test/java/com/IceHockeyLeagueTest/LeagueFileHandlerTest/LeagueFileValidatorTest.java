package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.AbstractAppFactory;
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
        AbstractAppFactory appFactory = AbstractAppFactory.createAppFactory();
        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
    }

    @Test
    public void validateJsonSchemaTest() {
        ILeagueFileValidator leagueFileValidator = leagueFileHandlerFactory.createLeagueFileValidator();
        JSONObject leagueSchema = LeagueJsonMock.getInstance().leagueSchema();

        JSONObject leagueJson = LeagueJsonMock.getInstance().validLeagueJson();
        Assert.assertNull(leagueFileValidator.validateJsonSchema(leagueJson, leagueSchema));

        JSONObject invalidLeagueJson = LeagueJsonMock.getInstance().invalidLeagueJson();
        List<String> expectedError = new ArrayList<>();
        expectedError.add("#/leagueName: expected minLength: 1, actual: 0");

        Assert.assertEquals(expectedError, leagueFileValidator.validateJsonSchema(invalidLeagueJson, leagueSchema));
    }
}
