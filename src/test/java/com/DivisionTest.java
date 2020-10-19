package com;

import com.leaguemodel.models.Division;
import com.leaguemodel.models.ITeam;
import com.leaguemodel.models.Team;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DivisionTest {

    @Test
    public void getDivisionNameTest(){
        Division division = new Division();
        division.setDivisionName("Division 1");
        Assert.assertEquals("Division 1", division.getDivisionName());
    }

    @Test
    public void getDivisionIDTest(){
        Division division = new Division();
        division.setDivisionID(1);
        Assert.assertEquals(1, division.getDivisionID());
    }

    @Test
    public void getTeamByNameTest(){
        Division division = new Division();
        ITeam team = new Team();
        team.setTeamName("Team 1");
        division.addTeam(team);
        Assert.assertEquals(team, division.getTeamByName("Team 1"));
    }

    @Test
    public void getTeamsTest(){
        Division division = new Division();
        List<ITeam> teams = new ArrayList<>();

        ITeam team1 = new Team();
        team1.setTeamName("Team 1");
        ITeam team2 = new Team();
        team2.setTeamName("Team 2");

        teams.add(team1);
        teams.add(team2);

        division.setTeams(teams);
        Assert.assertEquals(teams, division.getTeams());
    }

}
