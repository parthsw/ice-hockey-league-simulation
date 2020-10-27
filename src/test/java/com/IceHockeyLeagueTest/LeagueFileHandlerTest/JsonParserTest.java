package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.IJsonParser;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

public class JsonParserTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
    }

    @Test
    public void parseTest() {
        InputStream inputStream = new ByteArrayInputStream("{\"leagueName\": \"DHL\"}".getBytes());
        IJsonParser jsonParser = AbstractLeagueFileHandlerFactory.getFactory().getJsonParser();

        Assert.assertEquals(jsonParser.parse(inputStream).getString("leagueName"), "DHL");
    }
}
