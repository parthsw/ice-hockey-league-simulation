package com.IceHockeyLeagueTest.SerializeDeserializeLeagueObjectTest;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueManager.Player.FreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.ISerialize;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.Serialize;
import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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