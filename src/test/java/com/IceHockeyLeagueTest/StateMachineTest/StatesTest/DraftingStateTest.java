package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.Database.IDatabaseFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DraftingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IDatabaseFactory databaseFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        databaseFactory = appFactory.createDatabaseFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();

        leaguePersistence.loadLeague(1, league);
        draftingState.setLeague(league);
        Assert.assertNull(draftingState.onRun());
    }

    @Test
    public void onRunDraftPicksTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();

        leaguePersistence.loadLeague(1, league);
        draftingState.setLeague(league);
        draftingState.onRun();
        Assert.assertNull(league.getDraftPicks());
    }

    @Test
    public void onRunTeamPlayersTest() {
        ILeague league = leagueManagerFactory.createLeague();
        ILeaguePersistence leaguePersistence = databaseFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();
        ITeam firstTeam;

        leaguePersistence.loadLeague(1, league);
        draftingState.setLeague(league);
        draftingState.onRun();

        firstTeam = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
        Assert.assertEquals(30, firstTeam.getPlayers().size());
    }

}
