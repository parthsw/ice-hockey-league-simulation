package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.LeagueScheduler.Schedule;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceTimeState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PersistStateTest {
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory appFactory = AppFactoryTest.createAppFactoryTest();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = new League();
        List<ISchedule> playoffScheduleList = new ArrayList<>();
        ISchedule schedule = new Schedule();
        schedule.setIsGamePlayed(true);
        playoffScheduleList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffScheduleList);

        AbstractState persistState = stateMachineFactory.createPersistState();
        persistState.setLeague(league);

        Assert.assertNull(persistState.onRun());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = new League();

        AbstractState persistState = stateMachineFactory.createPersistState();
        persistState.setLeague(league);

        Assert.assertTrue(persistState.onRun() instanceof AdvanceTimeState);
    }
}
