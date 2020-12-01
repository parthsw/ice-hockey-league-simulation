package com;

import com.IOTest.IOMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HockeyLeagueSimulationAppTest {
    private static final String EXPECTED_FACTORY = "AppFactory";
    private static IOMock ioMockInstance = null;

    @BeforeClass
    public static void setup() {
        ioMockInstance = IOMock.instance();
    }

    @Test
    public void mainFactoriesTest() {
        ioMockInstance.commandLineInput("file.json");
        HockeyLeagueSimulationApp.main(null);
        String actualFactory = AbstractAppFactory.getAppFactory().getClass().getSimpleName();
        Assert.assertEquals(EXPECTED_FACTORY, actualFactory);
    }

}
