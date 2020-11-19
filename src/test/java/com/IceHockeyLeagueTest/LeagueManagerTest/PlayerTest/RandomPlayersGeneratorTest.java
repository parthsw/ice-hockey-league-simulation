package com.IceHockeyLeagueTest.LeagueManagerTest.PlayerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class RandomPlayersGeneratorTest {
    private static final LocalDate CURRENT_DATE = LocalDate.of(2020, Month.NOVEMBER, 16);
    private static IRandomChance randomChanceMock;
    private static IRandomPlayersGenerator randomPlayerGenerator;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        ILeagueManagerFactory leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        randomChanceMock = Mockito.mock(RandomChance.class);
        randomPlayerGenerator = leagueManagerFactory.createRandomPlayersGenerator(randomChanceMock);
    }

    @Before
    public void setupBeforeTest() {
        when(randomChanceMock.getRandomIntegerNumber(12, 20)).thenReturn(14);
        when(randomChanceMock.getRandomIntegerNumber(9, 18)).thenReturn(13);
        when(randomChanceMock.getRandomIntegerNumber(1, 7)).thenReturn(5);
        when(randomChanceMock.getRandomIntegerNumber(10, 19)).thenReturn(11);
        when(randomChanceMock.getRandomIntegerNumber(1, 12)).thenReturn(9);
        when(randomChanceMock.getRandomIntegerNumber(8, 15)).thenReturn(13);
        when(randomChanceMock.getRandomIntegerNumber(1, 10)).thenReturn(4);
    }

    @Test
    public void generateRandomPlayersTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 100);
        Assert.assertEquals(100, generatedPlayers.size());
    }

    @Test
    public void generateRandomPlayersEmptyTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 0);
        Assert.assertEquals(0, generatedPlayers.size());
    }

    @Test
    public void generateRandomPlayersForwardsCountTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 100);
        Assert.assertEquals(50, findPlayersForGivenPosition(generatedPlayers, PlayerPosition.FORWARD.toString()).size());
    }

    @Test
    public void generateRandomPlayersDefenseCountTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 100);
        Assert.assertEquals(40, findPlayersForGivenPosition(generatedPlayers, PlayerPosition.DEFENSE.toString()).size());
    }

    @Test
    public void generateRandomPlayersGoalieCountTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 100);
        Assert.assertEquals(10, findPlayersForGivenPosition(generatedPlayers, PlayerPosition.GOALIE.toString()).size());
    }

    @Test
    public void generateRandomPlayersAgeTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 10);
        generatedPlayers.forEach(player -> {
            int playerAge = player.getPlayerAgeInfo().getAgeInYears();
            Assert.assertTrue(playerAge >= 18 && playerAge <= 21);
        });
    }

    @Test
    public void generateRandomPlayersNameTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 1);
        IPlayer player = generatedPlayers.get(0);
        Assert.assertTrue(player.getPlayerName().length() > 0);
    }

    @Test
    public void generateRandomPlayersForwardStatsTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 10);
        List<IPlayer> forwardPlayers = findPlayersForGivenPosition(generatedPlayers, PlayerPosition.FORWARD.toString());

        forwardPlayers.forEach(forwardPlayer -> {
            IPlayerStats stats = forwardPlayer.getPlayerStats();
            Assert.assertEquals(14, stats.getSkating());
            Assert.assertEquals(14, stats.getShooting());
            Assert.assertEquals(13, stats.getChecking());
            Assert.assertEquals(5, stats.getSaving());
        });
    }

    @Test
    public void generateRandomPlayersDefenseStatsTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 10);
        List<IPlayer> defensePlayers = findPlayersForGivenPosition(generatedPlayers, PlayerPosition.DEFENSE.toString());

        defensePlayers.forEach(defensePlayer -> {
            IPlayerStats stats = defensePlayer.getPlayerStats();
            Assert.assertEquals(11, stats.getSkating());
            Assert.assertEquals(13, stats.getShooting());
            Assert.assertEquals(14, stats.getChecking());
            Assert.assertEquals(9, stats.getSaving());
        });
    }

    @Test
    public void generateRandomPlayersGoalieStatsTest() {
        List<IPlayer> generatedPlayers = randomPlayerGenerator.generateRandomPlayers(CURRENT_DATE, 10);
        List<IPlayer> goaliePlayers = findPlayersForGivenPosition(generatedPlayers, PlayerPosition.GOALIE.toString());

        goaliePlayers.forEach(goaliePlayer -> {
            IPlayerStats stats = goaliePlayer.getPlayerStats();
            Assert.assertEquals(13, stats.getSkating());
            Assert.assertEquals(4, stats.getShooting());
            Assert.assertEquals(9, stats.getChecking());
            Assert.assertEquals(14, stats.getSaving());
        });
    }

    private List<IPlayer> findPlayersForGivenPosition(List<IPlayer> players, String position) {
        if (players.size() <= 0) {
            return new ArrayList<>();
        }

        List<IPlayer> positionSpecificPlayers = new ArrayList<>();
        players.forEach(player -> {
            if (player.getPlayerStats().getPosition().equalsIgnoreCase(position)) {
                positionSpecificPlayers.add(player);
            }
        });
        return positionSpecificPlayers;
    }
}
