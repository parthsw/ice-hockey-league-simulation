package com.IceHockeyLeagueTest.SerializeDeserializeLeagueObjectTest;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.Serialize;
import org.junit.Assert;
import org.junit.Test;
import java.io.*;

public class SerializeTest {

    @Test
    public void SerializeLeagueObjectTest() {
        ILeague league = new League();
        league.setLeagueID(13);
        league.setLeagueName("Dalhousie Hockey League");
        ISerialize serialize = new Serialize();
        String path = serialize.serializeLeagueObject(league);
        File file = new File(path);
        Assert.assertTrue(file.exists());
    }
}