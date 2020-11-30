package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import org.junit.BeforeClass;

public class SimulateStateTest {
    private static IStateMachineFactory stateMachineFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
    }

   /* @Test
    public void onRunNoSeasonsSimulatedTest() {
        int noOfSeasons = 0;
        AbstractState simulateState = stateMachineFactory.createSimulateState(noOfSeasons);
        Assert.assertNull(simulateState.onRun());
    }*/
}
