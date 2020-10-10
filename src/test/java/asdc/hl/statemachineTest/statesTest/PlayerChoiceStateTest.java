package asdc.hl.statemachineTest.statesTest;

import asdc.hl.statemachine.StateMachine;
import asdc.hl.statemachine.states.PlayerChoiceState;
import asdc.hl.statemachine.states.SimulateState;
import asdc.hl.statemachine.states.State;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class PlayerChoiceStateTest {
    private static State playerChoiceState;
    private static StateMachine stateMachine;

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

    @Before
    public void setup() {
        stateMachine = new StateMachine();
        playerChoiceState = new PlayerChoiceState(stateMachine);
    }

    @Test
    public void onRunTest() {
        inputMock.provideLines("20");
        State state = playerChoiceState.onRun();
        assertTrue(state instanceof SimulateState);
    }

    @Test()
    public void promptForNumberOfSeasonsTest() {
        inputMock.provideLines("45");
        playerChoiceState.onRun();
        assertEquals(45, stateMachine.getNoOfSeasons());
    }

    @Test()
    public void promptForNumberOfSeasonsExceptionTest() {
        inputMock.provideLines("abc", "30");
        playerChoiceState.onRun();
        assertEquals(30, stateMachine.getNoOfSeasons());
    }

}
