package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.junit.BeforeClass;

public class PersistStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    /*@Test
    public void onRunTest() {
        ILeague league = leagueManagerFactory.createLeague();
        List<ISchedule> playoffScheduleList = new ArrayList<>();
        ISchedule schedule = leagueManagerFactory.createSchedule();
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
    }*/
}
