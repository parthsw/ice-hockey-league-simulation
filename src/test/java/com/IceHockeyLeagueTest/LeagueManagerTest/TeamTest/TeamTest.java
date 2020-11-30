package com.IceHockeyLeagueTest.LeagueManagerTest.TeamTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
//import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
//import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
//import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
//import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;
import com.PersistenceTest.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeamTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void ConstructorTest() {
        ITeam team = leagueManagerFactory.createTeam();
        Assert.assertEquals(-1, team.getTeamID());
        Assert.assertEquals(-1, team.getDivisionID());
        Assert.assertEquals(0f, team.getTeamStrength(), 0.0);
        Assert.assertFalse(team.getIsUserCreated());
    }

    @Test
    public void getTeamIDTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamID(9);
        Assert.assertEquals(9, team.getTeamID());
    }

    @Test
    public void setTeamIDTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamID(5);
        Assert.assertEquals(5, team.getTeamID());
    }

    @Test
    public void getTeamNameTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamName("Halifax");
        Assert.assertEquals("Halifax", team.getTeamName());
    }

    @Test
    public void setTeamNameTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamName("Montreal");
        Assert.assertEquals("Montreal", team.getTeamName());
    }

    @Test
    public void getIsUserCreatedTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setIsUserCreated(false);
        Assert.assertFalse(team.getIsUserCreated());
    }

    @Test
    public void setIsUserCreatedTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setIsUserCreated(true);
        Assert.assertTrue(team.getIsUserCreated());
    }

    @Test
    public void setTeamStrengthTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamStrength(1.2f);
        Assert.assertEquals(1.2f, team.getTeamStrength(), 0.0);
    }

    @Test
    public void getTeamStrengthTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setTeamStrength(56.7f);
        Assert.assertEquals(56.7f, team.getTeamStrength(), 0.0);
    }

    @Test
    public void getDivisionIDTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setDivisionID(3);
        Assert.assertEquals(3, team.getDivisionID());
    }

    @Test
    public void setDivisionIDTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setDivisionID(7);
        Assert.assertEquals(7, team.getDivisionID());
    }

    @Test
    public void getLossPointValueTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setLossPointValue(3);
        Assert.assertEquals(3, team.getLossPointValue());
    }

    @Test
    public void setLossPointValueTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setLossPointValue(5);
        Assert.assertEquals(5, team.getLossPointValue());
    }

    @Test
    public void incrementLossPointValueTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setLossPointValue(5);
        team.incrementLossPointValue();
        Assert.assertEquals(6, team.getLossPointValue());
    }

    @Test
    public void decrementLossPointValueWhenLossPointIsZeroTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setLossPointValue(0);
        team.decrementLossPointValue();
        Assert.assertEquals(0, team.getLossPointValue());
    }

    @Test
    public void decrementLossPointValueWhenLossPointIsGreaterThanZeroTest() {
        ITeam team = leagueManagerFactory.createTeam();
        team.setLossPointValue(5);
        team.decrementLossPointValue();
        Assert.assertEquals(4, team.getLossPointValue());
    }

    @Test
    public void getPlayerByIdTest() {
        ITeam team = leagueManagerFactory.createTeam();
        Assert.assertNull(team.getPlayerById(1));
    }

    @Test
    public void addPlayerTest() {
        ITeam team = leagueManagerFactory.createTeam();
        ITeamPlayer teamPlayer = leagueManagerFactory.createTeamPlayer();
        teamPlayer.setTeamPlayerID(1);

        team.addPlayer(teamPlayer);
        Assert.assertEquals(1, team.getPlayers().size());
    }

    @Test
    public void removePlayerTest() {
        ITeam team = leagueManagerFactory.createTeam();
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerPersistenceMock.loadTeamPlayers(1,teamPlayers);
        team.setPlayers(teamPlayers);

        team.removePlayer(teamPlayers.get(0));
        List<ITeamPlayer> players = team.getPlayers();
        Assert.assertEquals(1, players.size());

        List<ITeamPlayer> emptyTeamPlayers = new ArrayList<>();
        team.setPlayers(emptyTeamPlayers);
        ITeamPlayer playerThatNotExist = leagueManagerFactory.createTeamPlayer();
        Assert.assertFalse(team.removePlayer(playerThatNotExist));
    }

    @Test
    public void getPlayersTest() {
        ITeam team = leagueManagerFactory.createTeam();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        teamPlayerPersistenceMock.loadTeamPlayers(1,teamPlayers);
        team.setPlayers(teamPlayers);
        Assert.assertEquals(2, team.getPlayers().size());
    }

    @Test
    public void setPlayersTest() {
        ITeam team = leagueManagerFactory.createTeam();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        teamPlayerPersistenceMock.loadTeamPlayers(1,teamPlayers);
        team.setPlayers(teamPlayers);
        Assert.assertEquals(2, team.getPlayers().size());
    }

    @Test
    public void getCoachTest() {
        ITeam team = leagueManagerFactory.createTeam();
        ICoach coach = leagueManagerFactory.createCoach();
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        coachPersistenceMock.loadTeamCoach(1,coach);
        Assert.assertNull(team.getCoach());
        team.setCoach(coach);
        Assert.assertNotNull(team.getCoach());
    }

    @Test
    public void setCoachTest() {
        ITeam team = leagueManagerFactory.createTeam();
        ICoach coach = leagueManagerFactory.createCoach();
        CoachPersistenceMock coachPersistenceMock = persistenceFactory.createCoachPersistence();
        coachPersistenceMock.loadTeamCoach(1,coach);
        Assert.assertNull(team.getCoach());
        team.setCoach(coach);
        Assert.assertNotNull(team.getCoach());
    }

    @Test
    public void getManagerTest() {
        ITeam team = leagueManagerFactory.createTeam();
        IManager manager = leagueManagerFactory.createManager();
        ManagerPersistenceMock managerPersistenceMock = persistenceFactory.createManagerPersistence();
        managerPersistenceMock.loadTeamManager(1,manager);
        Assert.assertNull(team.getManager());
        team.setManager(manager);
        Assert.assertNotNull(team.getManager());
    }

    @Test
    public void setManagerTest() {
        ITeam team = leagueManagerFactory.createTeam();
        IManager manager = leagueManagerFactory.createManager();
        ManagerPersistenceMock managerPersistenceMock = persistenceFactory.createManagerPersistence();
        managerPersistenceMock.loadTeamManager(1,manager);
        Assert.assertNull(team.getManager());
        team.setManager(manager);
        Assert.assertNotNull(team.getManager());
    }

    @Test
    public void checkTeamPlayersTest() {
        ITeam team = leagueManagerFactory.createTeam();
        String[] positions = new String[]{"forward", "defence"};
        List<ITeamPlayer> players = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 18; i++) {
            String temp = positions[random.nextInt(positions.length)];
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            players.add(player);
        }
        for (int i = 0; i < 2; i++) {
            String temp = "goalie";
            ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
            IPlayerStats stats = leagueManagerFactory.createPlayerStats();
            stats.setPosition(temp);
            stats.setStrength(random.nextInt(100));
            player.setPlayerStats(stats);
            players.add(player);
        }
        team.setPlayers(players);
        Assert.assertTrue(team.checkTeamPlayers());
    }

    @Test
    public void checkIfTeamNameExistsTest() {
        TeamPersistenceMock teamPersistenceMock = persistenceFactory.createTeamPersistence();
        List<ILeague> leagues = new ArrayList<>();
        ITeam team = leagueManagerFactory.createTeam();
        teamPersistenceMock.checkIfTeamNameExists("Halifax",leagues);
        Assert.assertEquals(1, leagues.size());
    }

    @Test
    public void isNullOrEmptyTest() {
        ITeam team = leagueManagerFactory.createTeam();
        Assert.assertTrue(team.isNullOrEmpty(""));
        Assert.assertFalse(team.isNullOrEmpty("Halifax"));
    }

    @Test
    public void isTeamNameExistTest() {
        TeamPersistenceMock teamPersistenceMock = persistenceFactory.createTeamPersistence();
        List<ITeam> teams = new ArrayList<>();
        teamPersistenceMock.loadTeams(1,teams);
        ITeam team = leagueManagerFactory.createTeam();
        Assert.assertTrue(team.isTeamNameExist(teams,"Boston"));
        Assert.assertFalse(team.isTeamNameExist(teams,"Halifax"));
    }

    @Test
    public void calculateTeamStrengthTest() {
        ITeamStrengthCalculator teamStrength = leagueManagerFactory.createTeamStrengthCalculator();
        ITeam team = leagueManagerFactory.createTeam();
        TeamPlayerPersistenceMock teamPlayerPersistenceMock = persistenceFactory.createTeamPlayerPersistence();
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayerPersistenceMock.loadTeamPlayers(1,teamPlayers);
        team.setPlayers(teamPlayers);
        Assert.assertEquals(46.5f, team.calculateTeamStrength(teamStrength), 0.0);
    }

}
