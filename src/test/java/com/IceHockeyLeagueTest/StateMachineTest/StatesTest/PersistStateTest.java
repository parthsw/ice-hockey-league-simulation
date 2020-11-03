package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.LeagueScheduler.ISchedule;
import com.IceHockeyLeague.LeagueScheduler.Schedule;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceTimeState;
import com.IceHockeyLeague.StateMachine.States.AdvanceToNextSeasonState;
import com.IceHockeyLeagueTest.LeagueManagerTest.TestLeagueManagerFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PersistStateTest {

    @BeforeClass
    public static void setup() {
        AbstractIOFactory.setFactory(new IOFactory());
        AbstractLeagueFileHandlerFactory.setFactory(new LeagueFileHandlerFactory());
        AbstractStateMachineFactory.setFactory(
                new StateMachineFactory(
                        AbstractIOFactory.getFactory().getCommandLineInput(),
                        AbstractIOFactory.getFactory().getCommandLineOutput(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileReader(),
                        LeagueFileHandlerFactory.getFactory().getJsonParser(),
                        LeagueFileHandlerFactory.getFactory().getLeagueFileValidator()
                )
        );
        AbstractLeagueManagerFactory.setFactory(new TestLeagueManagerFactory());
    }

    @Test
    public void onRunTest() {
        ILeague league = new League();
        List<ISchedule> playoffScheduleList = new ArrayList<>();
        ISchedule schedule = new Schedule();
        schedule.setIsGamePlayed(true);
        playoffScheduleList.add(schedule);
        league.getScheduleSystem().setPlayoffSchedule(playoffScheduleList);

        AbstractState persistState = AbstractStateMachineFactory.getFactory().getPersistState();
        persistState.setLeague(league);

        Assert.assertNull(persistState.onRun());
    }

    @Test
    public void onRunAlternateTest() {
        ILeague league = new League();

        AbstractState persistState = AbstractStateMachineFactory.getFactory().getPersistState();
        persistState.setLeague(league);

        Assert.assertTrue(persistState.onRun() instanceof AdvanceTimeState);
    }
}
