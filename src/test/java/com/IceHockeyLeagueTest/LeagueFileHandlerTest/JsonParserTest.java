package com.IceHockeyLeagueTest.LeagueFileHandlerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueFileHandler.IJsonParser;
import com.IceHockeyLeague.LeagueFileHandler.ILeagueFileHandlerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;

public class JsonParserTest {
    private static ILeagueFileHandlerFactory leagueFileHandlerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueFileHandlerFactory(appFactory.createLeagueFileHandlerFactory());
        leagueFileHandlerFactory = AbstractAppFactory.getLeagueFileHandlerFactory();
    }

    @Test
    public void parseTest() {
        InputStream inputStream = new ByteArrayInputStream("{\"leagueName\": \"DHL\"}".getBytes());
        IJsonParser jsonParser = leagueFileHandlerFactory.createJsonParser();
        Assert.assertEquals(jsonParser.parse(inputStream).getString("leagueName"), "DHL");
    }

}
