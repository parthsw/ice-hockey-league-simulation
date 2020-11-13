package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueScheduler.ILeagueSchedulerFactory;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
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
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueSchedulerFactory leagueSchedulerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        leagueSchedulerFactory = AbstractAppFactory.getLeagueSchedulerFactory();
    }

    @Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        List<ISchedule> playoffScheduleList = new ArrayList<>();
        ISchedule schedule = leagueSchedulerFactory.createSchedule();
        schedule.setIsGamePlayed(true);
        playoffScheduleList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffScheduleList);

        AbstractState persistState = stateMachineFactory.createPersistState();
        persistState.setLeague(league);

        Assert.assertNull(persistState.onRun());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = leagueManagerFactory.createLeague();

        AbstractState persistState = stateMachineFactory.createPersistState();
        persistState.setLeague(league);

        Assert.assertTrue(persistState.onRun() instanceof AdvanceTimeState);
    }
}
