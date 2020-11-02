package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IceHockeyLeague.LeagueFileHandler.AbstractLeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueFileHandler.LeagueFileHandlerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.StateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.AdvanceTimeState;
import com.IceHockeyLeague.StateMachine.States.TrainingState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InitializeSeasonStateTest {

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
    }

    @Test
    public void onRunTest() {
        ILeague league = new League();

        AbstractState initializeSeasonState = AbstractStateMachineFactory.getFactory().getInitializeSeasonState();
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
