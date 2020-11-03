package com.Trading;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class getAllTeamsFromLeagueTest {

    @BeforeClass
    public static void setup() {
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }
    @Test
    public void getAllTeamsFromLeagueTest(){

        ITeam team1 = AbstractLeagueManagerFactory.getFactory().getTeam();
        team1.setTeamName("team1");
        ITeam team2 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team2.setTeamName("team2");
        ITeam team3 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team3.setTeamName("team3");
        ITeam team4 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team4.setTeamName("team4");
        ITeam team5 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team5.setTeamName("team5");
        ITeam team6 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team6.setTeamName("team6");
        ITeam team7 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team7.setTeamName("team7");
        ITeam team8 =AbstractLeagueManagerFactory.getFactory().getTeam();
        team8.setTeamName("team8");

        IDivision div1 = AbstractLeagueManagerFactory.getFactory().getDivision();
        div1.setDivisionName("div1");
        div1.addTeam(team1);
        div1.addTeam(team2);
        IDivision div2 = AbstractLeagueManagerFactory.getFactory().getDivision();
        div2.setDivisionName("div2");
        div2.addTeam(team3);
        div2.addTeam(team4);
        IDivision div3 = AbstractLeagueManagerFactory.getFactory().getDivision();
        div3.setDivisionName("div3");
        div3.addTeam(team5);
        div3.addTeam(team6);
        IDivision div4 = AbstractLeagueManagerFactory.getFactory().getDivision();
        div4.setDivisionName("div4");
        div4.addTeam(team7);
        div4.addTeam(team8);

        IConference cov1 = AbstractLeagueManagerFactory.getFactory().getConference();
        cov1.setConferenceName("Eastern Conference");
        cov1.addDivision(div1);
        cov1.addDivision(div2);
        IConference cov2 = AbstractLeagueManagerFactory.getFactory().getConference();
        cov2.setConferenceName("Western Conference");
        cov2.addDivision(div3);
        cov2.addDivision(div4);

        ILeague league = AbstractLeagueManagerFactory.getFactory().getLeague();
        league.addConference(cov1);
        league.addConference(cov2);

        List<ITeam> teams = new ArrayList<ITeam>();
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
