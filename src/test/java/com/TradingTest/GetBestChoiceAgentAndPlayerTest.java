package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.Trading.GetBestChoiceAgentAndPlayer;
import com.Trading.GetBestAgent;
import com.Trading.ITradingFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GetBestChoiceAgentAndPlayerTest {
public class GetBestAgentTest {
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
    public void getBestAgentWithPositionTest() {
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Goalie");
        IFreeAgent agent1 = leagueManagerFactory.createFreeAgent();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Goalie");
        IFreeAgent agent2 = leagueManagerFactory.createFreeAgent();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<IFreeAgent> agentList = new ArrayList<>();
        agentList.add(agent1);
        agentList.add(agent2);

        GetBestAgent object = tradingFactory.createGetBestAgent();
        IFreeAgent result = object.getBestAgentWithPosition(agentList, "Goalie");
        Assert.assertEquals("test player 2", result.getPlayerName());

    }

    @Test
    public void getWorsePlayerInTeamWithPositionTest() {
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Goalie");
        ITeamPlayer agent1 = leagueManagerFactory.createTeamPlayer();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Goalie");
        ITeamPlayer agent2 = leagueManagerFactory.createTeamPlayer();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<ITeamPlayer> players = new ArrayList<>();
        players.add(agent1);
        players.add(agent2);

        GetBestAgent object = tradingFactory.createGetBestAgent();
        ITeamPlayer result = object.getWorsePlayerInTeamWithPosition(players, "Goalie");
        Assert.assertEquals("test player 1", result.getPlayerName());


    }

    @Test
    public void getRandomBestAgentSkaterTest() {
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Forward");
        IFreeAgent agent1 = leagueManagerFactory.createFreeAgent();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Defence");
        IFreeAgent agent2 = leagueManagerFactory.createFreeAgent();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<IFreeAgent> agentList = new ArrayList<>();
        agentList.add(agent1);
        agentList.add(agent2);

        GetBestAgent object = tradingFactory.createGetBestAgent();
        IFreeAgent result = object.getRandomBestAgentSkater(agentList);
        Assert.assertEquals("test player 2", result.getPlayerName());

    }

    @Test
    public void getWorseSkaterInTeamTest() {
        IPlayerStats stats1 = leagueManagerFactory.createPlayerStats();
        stats1.setStrength(10);
        stats1.setPosition("Forward");
        ITeamPlayer agent1 = leagueManagerFactory.createTeamPlayer();
        agent1.setPlayerName("test player 1");
        agent1.setPlayerStats(stats1);

        IPlayerStats stats2 = leagueManagerFactory.createPlayerStats();
        stats2.setStrength(12);
        stats2.setPosition("Defence");
        ITeamPlayer agent2 = leagueManagerFactory.createTeamPlayer();
        agent2.setPlayerName("test player 2");
        agent2.setPlayerStats(stats2);

        List<ITeamPlayer> players = new ArrayList<>();
        players.add(agent1);
        players.add(agent2);

        GetBestAgent object = tradingFactory.createGetBestAgent();
        ITeamPlayer result = object.getWorseSkaterInTeam(players);
        Assert.assertEquals("test player 1", result.getPlayerName());
    }
}
