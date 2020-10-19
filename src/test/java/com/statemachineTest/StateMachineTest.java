package com.statemachineTest;

import com.statemachine.StateMachine;
import com.statemachine.states.State;
import org.junit.*;

import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class StateMachineTest {
    private static StateMachine stateMachine;
    private static State createTeamState;
    private static State importState;

    @Rule
    public final TextFromStandardInputStream inputMock = emptyStandardInputStream();

//    @BeforeClass
//    public static void setup() {
//        stateMachine = new StateMachine();
//        importState = new ImportState(stateMachine);
//        createTeamState = new CreateTeamState(stateMachine);
//    }
//
//    @Before
//    public void beforeEachTest() {
//        stateMachine.setState(importState);
//    }
//
//    @Test
//    public void runTest() {
//        inputMock.provideLines("inValidPath", "teamName", "3");
//        stateMachine.run();
//        assertNull(stateMachine.getState());
//    }
//
//    @Test
//    public void setStateTest() {
//        stateMachine.setState(createTeamState);
//        assertEquals(stateMachine.getState(), createTeamState);
//    }
//
//    @Test
//    public void getStateTest() {
//        stateMachine.setState(createTeamState);
//        assertEquals(stateMachine.getState(), createTeamState);
//    }
//
//    @Test
//    public void setNoOfSeasonsTest() {
//        stateMachine.setNoOfSeasons(100);
//        assertEquals(stateMachine.getNoOfSeasons(), 100);
//    }
//
//    @Test
//    public void getNoOfSeasonsTest() {
//        stateMachine.setNoOfSeasons(1124);
//        assertEquals(stateMachine.getNoOfSeasons(), 1124);
//    }
}
