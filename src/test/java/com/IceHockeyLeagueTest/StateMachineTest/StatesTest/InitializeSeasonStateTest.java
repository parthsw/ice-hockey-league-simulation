package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceTimeState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InitializeSeasonStateTest {
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactoryTest();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = new League();

        AbstractState initializeSeasonState = stateMachineFactory.createInitializeSeasonState();
        initializeSeasonState.setLeague(league);

        Assert.assertTrue(initializeSeasonState.onRun() instanceof AdvanceTimeState);
        Assert.assertNotNull(league.getLeagueDate());
        Assert.assertNotNull(league.getScheduleSystem().getRegularSeasonStartDate());
        Assert.assertNotNull(league.getScheduleSystem().getRegularSeasonEndDate());
        Assert.assertNotNull(league.getScheduleSystem().getPlayoffStartDate());
        Assert.assertNotNull(league.getScheduleSystem().getPlayoffEndDate());
        Assert.assertNotNull(league.getScheduleSystem().getTradeDeadline());
        Assert.assertNotNull(league.getScheduleSystem().getRegularSchedule());
        Assert.assertNotNull(league.getStandingSystem().getStandings());
    }
}
