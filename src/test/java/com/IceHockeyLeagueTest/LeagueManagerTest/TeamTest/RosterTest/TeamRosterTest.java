package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest.RosterTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.Roster.ITeamRoster;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TeamRosterTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Test
    public void validateRosterTest() {
        IGenerateMockObjectForRosterTesting mockObject = new GenerateMockObjectForRosterTesting();
        List<ITeamPlayer> players = mockObject.getPlayerList(50);
        List<IFreeAgent> agents = mockObject.getAgentList(100);

        ITeamRoster roster = leagueManagerFactory.createTeamRoster();
        roster.setPlayers(players);
        roster.setAgents(agents);
        roster.validateRoster(agents);
        List<ITeamPlayer> activeRoster = roster.getActiveRoster();
        List<ITeamPlayer> inactiveRoster = roster.getInactiveRoster();
        Assert.assertEquals(activeRoster.size(), 20);
        Assert.assertEquals(inactiveRoster.size(), 10);
    }

}
