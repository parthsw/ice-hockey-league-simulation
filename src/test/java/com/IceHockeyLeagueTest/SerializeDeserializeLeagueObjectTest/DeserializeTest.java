package com.IceHockeyLeagueTest.SerializeDeserializeLeagueObjectTest;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.Deserialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.IDeserialize;
import org.junit.Assert;
import org.junit.Test;

public class DeserializeTest {

    @Test
    public void deserializeLeagueObjectTest(){
        IDeserialize deserialize = new Deserialize();
        ILeague league = new League();
        league = deserialize.deserializeLeagueObject("jsonInput");
        String leagueName = league.getLeagueName();
        Assert.assertEquals(leagueName, "Dalhousie Hockey League");
    }

}
