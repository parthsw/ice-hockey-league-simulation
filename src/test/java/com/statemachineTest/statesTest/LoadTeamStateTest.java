package com.statemachineTest.statesTest;

import com.statemachine.StateMachine;
import com.statemachine.states.State;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class LoadTeamStateTest {
    private static StateMachine stateMachine;
    private static State loadTeamState;

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

//    @BeforeClass
//    public static void setup() {
//        stateMachine = new StateMachine();
//        loadTeamState = new LoadTeamState(stateMachine);
//    }

}
