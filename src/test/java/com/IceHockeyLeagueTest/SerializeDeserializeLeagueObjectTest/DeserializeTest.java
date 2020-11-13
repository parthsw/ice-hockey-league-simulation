package com.IceHockeyLeagueTest.SerializeDeserializeLeagueObjectTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.IDeserialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerializeDeserializeLeagueObjectFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeserializeTest {
    private static ISerializeDeserializeLeagueObjectFactory leagueSerializationFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        leagueSerializationFactory = appFactory.createSerializeDeserializeLeagueObjectFactory();
    }

    @Test
    public void deserializeLeagueObjectTest(){
        IDeserialize deserialize = leagueSerializationFactory.createDeserialize();
        ILeague league;
        league = deserialize.deserializeLeagueObject("jsonInput");
        String leagueName = league.getLeagueName();
        Assert.assertEquals(leagueName, "Dalhousie Hockey League");
    }

}
