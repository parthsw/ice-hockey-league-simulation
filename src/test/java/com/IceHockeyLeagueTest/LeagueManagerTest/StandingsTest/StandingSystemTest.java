package com.IceHockeyLeagueTest.LeagueManagerTest.StandingsTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Standings.IStandingSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class StandingSystemTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory persistenceFactory;
    private ILeague league;
    private IStandingSystem leagueStandingSystem;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setDatabaseFactory(appFactory.createDatabaseFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        persistenceFactory = AbstractAppFactory.getDatabaseFactory();
    }

    @Before
    public void setupBeforeTest() {
        league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        leaguePersistence.loadLeague(1, league);
        leagueStandingSystem = league.getStandingSystem();
    }

    @Test
    public void setStandingsTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        standingSystem.setStandings(leagueStandingSystem.getStandings());
        Assert.assertEquals(8, standingSystem.getStandings().size());
    }

    @Test
    public void initializeStandingsTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        standingSystem.initializeStandings(league);
        List<IStanding> standings = standingSystem.getStandings();
        Assert.assertEquals(8, standings.size());
    }

    @Test
    public void updateStatsForWinningTeamTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        standingSystem.updateStatsForWinningTeam(standing.getConference(), standing.getDivision(), standing.getTeam());

        Assert.assertEquals(8, standing.getGamesPlayed());
        Assert.assertEquals(5, standing.getGamesWon());
        Assert.assertEquals(10, standing.getPoints());
    }

    @Test
    public void updateStatsForLosingTeamTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        standingSystem.updateStatsForLosingTeam(standing.getConference(), standing.getDivision(), standing.getTeam());

        Assert.assertEquals(8, standing.getGamesPlayed());
        Assert.assertEquals(4, standing.getGamesWon());
        Assert.assertEquals(8, standing.getPoints());
    }

    @Test
    public void getStandingsInDivisionTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> divisionStandings = standingSystem.getStandingsInDivision(standing.getDivision());

        Assert.assertEquals(2, divisionStandings.size());
        Assert.assertEquals(standings.get(1), divisionStandings.get(1));
        Assert.assertEquals(standings.get(0), divisionStandings.get(0));
    }

    @Test
    public void getStandingsInConferenceTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(0);
        List<IStanding> conferenceStandings = standingSystem.getStandingsInConference(standing.getConference());

        Assert.assertEquals(4, conferenceStandings.size());
        Assert.assertEquals(standings.get(0), conferenceStandings.get(0));
        Assert.assertEquals(standings.get(1), conferenceStandings.get(1));
        Assert.assertEquals(standings.get(2), conferenceStandings.get(3));
        Assert.assertEquals(standings.get(3), conferenceStandings.get(2));
    }

    @Test
    public void getTopStandingInConferenceTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);

        IStanding standing = standings.get(4);
        IStanding topStandingInConference = standingSystem.getTopStandingInConference(standing.getConference());

        Assert.assertEquals(standing, topStandingInConference);
    }

    @Test
    public void getRegularSeasonStandingsInReverseTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> standings = leagueStandingSystem.getStandings();
        standingSystem.setStandings(standings);
        standingSystem.getRegularSeasonStandingsInReverse();
        Assert.assertEquals(0, standings.get(0).getPoints());
    }

    @Test
    public void getPlayOffSeasonStandingsInReverseTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<IStanding> playOffStandingsInReverse = standingSystem.getPlayOffSeasonStandingsInReverse(league.getScheduleSystem().getPlayoffSchedule());
        Assert.assertEquals(4, playOffStandingsInReverse.size());
    }

    @Test
    public void getPlayOffSeasonStandingsInReverseSecondTeamTest() {
        IStandingSystem standingSystem = leagueManagerFactory.createStandingSystem();
        List<ISchedule> playOffSchedule = league.getScheduleSystem().getPlayoffSchedule();
        ISchedule lastSchedule = playOffSchedule.get(2);
        lastSchedule.setWinningTeam(lastSchedule.getSecondTeam());
        List<IStanding> playOffStandingsInReverse = standingSystem.getPlayOffSeasonStandingsInReverse(league.getScheduleSystem().getPlayoffSchedule());
        Assert.assertEquals(4, playOffStandingsInReverse.size());
    }

}
