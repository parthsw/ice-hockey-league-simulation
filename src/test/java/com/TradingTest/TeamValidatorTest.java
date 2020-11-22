package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.TeamValidator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamValidatorTest {
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
    public void validateTeamNumberTest() {
        ITeam team = leagueManagerFactory.createTeam();
        List<IFreeAgent> agents = new ArrayList<>();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();
        int skater = 0;
        int goalie = 0;
        String keeper = "Goalie";
        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }
        for (int i = 0; i < 100; i++) {
            String temp = positions[random.nextInt(positions.length)];
            IFreeAgent agent = leagueManagerFactory.createFreeAgent();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            agent.setPlayerStats(stats);
            agents.add(agent);
        }
        TeamValidator object = tradingFactory.createTeamValidator(team, 1, agents);
        ITeam validatedTeam = object.validateTeam();

        for (ITeamPlayer player : validatedTeam.getPlayers()) {
            if (player.getPlayerStats().getPosition().equals(keeper)) {
                goalie++;
            } else {
                skater++;
            }
        }
        if (goalie == 2 && skater == 18) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

}
