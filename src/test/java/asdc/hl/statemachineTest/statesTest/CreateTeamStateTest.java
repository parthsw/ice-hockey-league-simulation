package asdc.hl.statemachineTest.statesTest;

import asdc.hl.statemachine.StateMachine;
import asdc.hl.statemachine.states.CreateTeamState;
import asdc.hl.statemachine.states.PlayerChoiceState;
import asdc.hl.statemachine.states.State;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class CreateTeamStateTest {
    private static StateMachine stateMachine;
    private static State createTeamState;

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

    @BeforeClass
    public static void setup() {
        stateMachine = new StateMachine();
        createTeamState = new CreateTeamState(stateMachine);
    }

    @Test
    public void onRunTest() {
        inputMock.provideLines("teamName", "conferenceName", "divisionName", "generalManager", "headCoach");
        assertTrue(createTeamState.onRun() instanceof PlayerChoiceState);
    }

}
