package com.Trading;

import com.IceHockeyLeague.LeagueManager.Player.*;
import com.Trading.GetBestAgent;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GetBestAgentTest {

    @Test
    public void getBestAgentWithPositionTest() {
        IPlayerStats stats1 = new PlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Goalie");
        IFreeAgent agent1 = new FreeAgent();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = new PlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Goalie");
        IFreeAgent agent2 = new FreeAgent();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<IFreeAgent> agentList = new ArrayList<>();
        agentList.add(agent1);
        agentList.add(agent2);

        GetBestAgent object = new GetBestAgent();
        IFreeAgent result = object.getBestAgentWithPosition(agentList, "Goalie");
        Assert.assertEquals("test player 2", result.getPlayerName());

    }

    @Test
    public void getWorsePlayerInTeamWithPositionTest() {
        IPlayerStats stats1 = new PlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Goalie");
        ITeamPlayer agent1 = new TeamPlayer();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = new PlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Goalie");
        ITeamPlayer agent2 = new TeamPlayer();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<ITeamPlayer> players = new ArrayList<>();
        players.add(agent1);
        players.add(agent2);

        GetBestAgent object = new GetBestAgent();
        ITeamPlayer result = object.getWorsePlayerInTeamWithPosition(players, "Goalie");
        Assert.assertEquals("test player 1", result.getPlayerName());


    }

    @Test
    public void getRandomBestAgentSkaterTest() {
        IPlayerStats stats1 = new PlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Forward");
        IFreeAgent agent1 = new FreeAgent();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = new PlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Defence");
        IFreeAgent agent2 = new FreeAgent();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<IFreeAgent> agentList = new ArrayList<>();
        agentList.add(agent1);
        agentList.add(agent2);

        GetBestAgent object = new GetBestAgent();
        IFreeAgent result = object.getRandomBestAgentSkater(agentList);
        Assert.assertEquals("test player 2", result.getPlayerName());

    }

    @Test
    public void getWorseSkaterInTeamTest() {
        IPlayerStats stats1 = new PlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Forward");
        ITeamPlayer agent1 = new TeamPlayer();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = new PlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Defence");
        ITeamPlayer agent2 = new TeamPlayer();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<ITeamPlayer> players = new ArrayList<>();
        players.add(agent1);
        players.add(agent2);

        GetBestAgent object = new GetBestAgent();
        ITeamPlayer result = object.getWorseSkaterInTeam(players);
        Assert.assertEquals("test player 1", result.getPlayerName());
    }
}
