package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileValidator;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeagueFileValidatorTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
    }

    @Test
    public void validateJsonSchemaTest() {
        ILeagueFileValidator leagueFileValidator = AbstractLeagueFileHandlerFactory.getFactory().getLeagueFileValidator();
        JSONObject leagueSchema = LeagueJsonMock.getInstance().leagueSchema();

        JSONObject leagueJson = LeagueJsonMock.getInstance().validLeagueJson();
        Assert.assertNull(leagueFileValidator.validateJsonSchema(leagueJson, leagueSchema));

        JSONObject invalidLeagueJson = LeagueJsonMock.getInstance().invalidLeagueJson();
        List<String> expectedError = new ArrayList<>();
        expectedError.add("#/leagueName: expected minLength: 1, actual: 0");

        Assert.assertEquals(expectedError, leagueFileValidator.validateJsonSchema(invalidLeagueJson, leagueSchema));
    }
}
