package com.Trading;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.Trading.GetAllTeamsFromLeague;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class getAllTeamsFromLeagueTest {
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }
    @Test
    public void getAllTeamsFromLeagueTest(){

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

        IDivision div1 = leagueManagerFactory.createDivision();
        div1.setDivisionName("div1");
        div1.addTeam(team1);
        div1.addTeam(team2);
        IDivision div2 = leagueManagerFactory.createDivision();
        div2.setDivisionName("div2");
        div2.addTeam(team3);
        div2.addTeam(team4);
        IDivision div3 = leagueManagerFactory.createDivision();
        div3.setDivisionName("div3");
        div3.addTeam(team5);
        div3.addTeam(team6);
        IDivision div4 = leagueManagerFactory.createDivision();
        div4.setDivisionName("div4");
        div4.addTeam(team7);
        div4.addTeam(team8);

        IConference cov1 = leagueManagerFactory.createConference();
        cov1.setConferenceName("Eastern Conference");
        cov1.addDivision(div1);
        cov1.addDivision(div2);
        IConference cov2 = leagueManagerFactory.createConference();
        cov2.setConferenceName("Western Conference");
        cov2.addDivision(div3);
        cov2.addDivision(div4);

        ILeague league = leagueManagerFactory.createLeague();
        league.addConference(cov1);
        league.addConference(cov2);

        List<ITeam> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(team8);

        GetAllTeamsFromLeague testObj = new GetAllTeamsFromLeague(league);
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
