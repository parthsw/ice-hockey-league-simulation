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
import com.IceHockeyLeague.StateMachine.States.TrainingState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class AdvanceTimeStateTest {

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
        league.setLeagueDate(LocalDate.now());
        league.getScheduleSystem().setRegularSeasonEndDate(LocalDate.now().plusDays(2));

        AbstractState advanceTimeState = AbstractStateMachineFactory.getFactory().getAdvanceTimeState();
        advanceTimeState.setLeague(league);

        Assert.assertTrue(advanceTimeState.onRun() instanceof TrainingState);
    }
}
