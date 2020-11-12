package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.Trading.TeamValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamValidatorTest {

    @Test
    public void validateTeamNumberTest() {
        ITeam team = new Team();
        List<IFreeAgent> agents = new ArrayList<IFreeAgent>();
        String[] positions = new String[]{"Goalie", "Forward", "Defence"};
        Random random = new Random();
        int skater = 0;
        int goalie = 0;
        String keeper = "Goalie";
        for (int i = 0; i < 20; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = new TeamPlayer();
            PlayerStats stats = new PlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            team.addPlayer(player);
        }
        for (int i = 0; i < 100; i++) {
            String temp = positions[random.nextInt(positions.length)];
            IFreeAgent agent = new FreeAgent();
            PlayerStats stats = new PlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            agent.setPlayerStats(stats);
            agents.add(agent);
        }
        TeamValidator object = new TeamValidator(team, 1, agents);
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
