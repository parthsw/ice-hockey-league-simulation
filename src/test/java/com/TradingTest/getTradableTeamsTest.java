package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.GetTradableTeams;
import com.IceHockeyLeague.Trading.ITradingFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class getTradableTeamsTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ITradingFactory tradingFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        tradingFactory = appFactory.createTradingFactory();
    }

    @Test
    public void getTradableTeamsTest(){
        ITeam team1 = leagueManagerFactory.createTeam();
        team1.setTeamName("team1");
        ITeam team2 = leagueManagerFactory.createTeam();
        team2.setTeamName("team2");
        ITeam team3 = leagueManagerFactory.createTeam();
        team3.setTeamName("team3");
        ITeam team4 = leagueManagerFactory.createTeam();
        team4.setTeamName("team4");
        ITeam team5 = leagueManagerFactory.createTeam();
        team5.setTeamName("team5");
        ITeam team6 = leagueManagerFactory.createTeam();
        team6.setTeamName("team6");
        ITeam team7 = leagueManagerFactory.createTeam();
        team7.setTeamName("team7");
        ITeam team8 = leagueManagerFactory.createTeam();
        team8.setTeamName("team8");

        List<ITeam> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(team8);

        GetTradableTeams testObj = tradingFactory.createGetTradableTeams(teams, 0);
        List<ITeam> result = testObj.getTeams();
        Assert.assertEquals(result.get(0).getTeamName(),"team1");
        Assert.assertEquals(result.get(1).getTeamName(),"team2");
        Assert.assertEquals(result.get(2).getTeamName(),"team3");
        Assert.assertEquals(result.get(3).getTeamName(),"team4");
        Assert.assertEquals(result.get(4).getTeamName(),"team5");
        Assert.assertEquals(result.get(5).getTeamName(),"team6");
        Assert.assertEquals(result.get(6).getTeamName(),"team7");
        Assert.assertEquals(result.get(7).getTeamName(),"team8");
    }

}
