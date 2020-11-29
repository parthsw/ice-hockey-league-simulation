package com.TradingTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerAgeInfo;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.Trading.ITradingFactory;
import com.IceHockeyLeague.Trading.SimulateTrade;
import com.IceHockeyLeague.Trading.TeamValidator;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulateTradeTest {
    private static IOMock ioMockInstance = null;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ITradingFactory tradingFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setTradingFactory(appFactory.createTradingFactory());
        tradingFactory = AbstractAppFactory.getTradingFactory();
        AbstractAppFactory.setIOFactory(appFactory.createIOFactory());
        ioMockInstance = IOMock.instance();
    }

    @Test
    public void simulateTradeTest() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(1);
        IConference conference = leagueManagerFactory.createConference();
        league.addConference(conference);
        IDivision division = leagueManagerFactory.createDivision();
        conference.addDivision(division);
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 200; j++) {
            league.addFreeAgent(generateAgent(positions[random.nextInt(positions.length)]));
        }
        for (int i = 0; i < 1; i++) {
            List<ITeamPlayer> players = generatePlayers();
            ITeam team = generateTeam(players, random.nextInt(5));
            division.addTeam(team);
        }

        List<ITeamPlayer> players1 = generatePlayers();
        for (ITeamPlayer player : players1) {
            player.getPlayerStats().setStrength(200);
            player.setPlayerName("StrongTeamPlayer");
        }
        List<ITeamPlayer> players2 = generatePlayers();
        for (ITeamPlayer player : players2) {
            player.getPlayerStats().setStrength(150);
            player.setPlayerName("WeakTeamPlayer");
        }
        ITeam team1 = generateTeam(players1, 0);
        ITeam team2 = generateTeam(players2, 6);
        team1.setTeamName("teamStrong");
        team2.setTeamName("teamWeak");
        division.addTeam(team1);
        division.addTeam(team2);

        SimulateTrade simulation = tradingFactory.createSimulateTrade();
        simulation.simulateTrade(league, 6, 5, 1);
        simulation.simulate();

        boolean flag1 = false;
        boolean flag2 = false;

        for (ITeamPlayer player : team1.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag1 = true;
            break;
        }
        Assert.assertTrue(flag1);
        for (ITeamPlayer player : team2.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag2 = true;
            break;
        }
        Assert.assertTrue(flag2);
    }

    private ITeamPlayer generatePlayer(String position) {
        Random random = new Random();
        ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo ageInfo = leagueManagerFactory.createPlayerAgeInfo();
        stats.setPosition(position);
        stats.setStrength(random.nextInt(100));
        player.setPlayerStats(stats);
        player.setPlayerAgeInfo(ageInfo);
        return player;
    }

    private ITeam generateTeam(List<ITeamPlayer> players, int lossPointValue) {
        ITeam team = leagueManagerFactory.createTeam();
        IManager manager1 = leagueManagerFactory.createManager();
        team.setManager(manager1);
        team.setPlayers(players);
        team.setLossPointValue(lossPointValue);
        return team;
    }

    private List<ITeamPlayer> generatePlayers() {
        List<ITeamPlayer> players = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ITeamPlayer player = generatePlayer("Forward");
            players.add(player);
        }
        for (int i = 0; i < 9; i++) {
            ITeamPlayer player = generatePlayer("Defence");
            players.add(player);
        }
        for (int i = 0; i < 2; i++) {
            ITeamPlayer player = generatePlayer("Goalie");
            players.add(player);
        }
        return players;
    }

    private IFreeAgent generateAgent(String position) {
        Random random = new Random();
        IFreeAgent player = leagueManagerFactory.createFreeAgent();
        IPlayerStats stats = leagueManagerFactory.createPlayerStats();
        IPlayerAgeInfo ageInfo = leagueManagerFactory.createPlayerAgeInfo();
        player.setPlayerName("AgentPlayer");
        stats.setStrength(random.nextInt(1000));
        stats.setPosition(position);
        player.setPlayerStats(stats);
        player.setPlayerAgeInfo(ageInfo);
        player.setLeagueID(1);

        return player;
    }

    @Test
    public void simulateTradeTest2() {
        ioMockInstance.commandLineInput("YES");
        ILeague league = leagueManagerFactory.createLeague();
        league.setLeagueID(1);
        IConference conference = leagueManagerFactory.createConference();
        league.addConference(conference);
        IDivision division = leagueManagerFactory.createDivision();
        conference.addDivision(division);
        Random random = new Random();
        String[] positions = new String[]{"Forward", "Defence", "Goalie"};
        for (int j = 0; j < 200; j++) {
            league.addFreeAgent(generateAgent(positions[random.nextInt(positions.length)]));
        }
        for (int i = 0; i < 1; i++) {
            List<ITeamPlayer> players = generatePlayers();
            ITeam team = generateTeam(players, random.nextInt(5));
            division.addTeam(team);
        }

        List<ITeamPlayer> players1 = generatePlayers();
        for (ITeamPlayer player : players1) {
            player.getPlayerStats().setStrength(200);
            player.setPlayerName("StrongTeamPlayer");
        }
        List<ITeamPlayer> players2 = generatePlayers();
        for (ITeamPlayer player : players2) {
            player.getPlayerStats().setStrength(150);
            player.setPlayerName("WeakTeamPlayer");
        }
        ITeam team1 = generateTeam(players1, 0);
        ITeam team2 = generateTeam(players2, 6);
        team1.setTeamName("teamStrong");
        team2.setTeamName("teamWeak");
        team1.setIsUserCreated(true);
        division.addTeam(team1);
        division.addTeam(team2);

        SimulateTrade simulation = tradingFactory.createSimulateTrade();
        simulation.simulateTrade(league, 6, 5, 1);
        simulation.simulate();


        boolean flag1 = false;
        boolean flag2 = false;

        for (ITeamPlayer player : team1.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag1 = true;
            break;
        }
        Assert.assertTrue(flag1);
        for (ITeamPlayer player : team2.getPlayers()) {
            if (player.getPlayerName().equals("fromStrongTeam")) ;
            flag2 = true;
            break;
        }
        Assert.assertTrue(flag2);
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
            org.junit.Assert.assertTrue(true);
        } else {
            org.junit.Assert.fail();
        }
    }
}
