package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

public class validateJsonSchemaTest {
    private JSONObject validJson, invalidJson;

    @BeforeClass
    public static void setup() {
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
    }

    @Test
    public void validateJsonSchemaTest() {

    }
}
