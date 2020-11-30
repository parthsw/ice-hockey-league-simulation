package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.Persistence.ILeaguePersistence;
import com.PersistenceTest.PersistenceFactoryTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DraftingStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static PersistenceFactoryTest persistenceFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        AbstractAppFactory.setStateMachineFactory(appFactory.createStateMachineFactory());
        AbstractAppFactory.setTrophySystemFactory(appFactory.createTrophySystemFactory());
        stateMachineFactory = AbstractAppFactory.getStateMachineFactory();
        persistenceFactory = AppFactoryTest.createPersistenceFactoryTest();
    }

    @Test
    public void onRunTest() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();
        ILeague league = leaguePersistence.loadLeague("");

        draftingState.setLeague(league);
        Assert.assertNull(draftingState.onRun());
    }

    @Test
    public void onRunDraftPicksTest() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();
        ILeague league = leaguePersistence.loadLeague("");

        draftingState.setLeague(league);
        draftingState.onRun();
        Assert.assertNull(league.getDraftPicks());
    }

    @Test
    public void onRunTeamPlayersTest() {
        ILeaguePersistence leaguePersistence = persistenceFactory.createLeaguePersistence();
        AbstractState draftingState = stateMachineFactory.createDraftingState();
        ILeague league = leaguePersistence.loadLeague("");
        ITeam firstTeam;

        draftingState.setLeague(league);
        draftingState.onRun();

        firstTeam = league.getConferences().get(0).getDivisions().get(0).getTeams().get(0);
        Assert.assertEquals(30, firstTeam.getPlayers().size());
    }

}
