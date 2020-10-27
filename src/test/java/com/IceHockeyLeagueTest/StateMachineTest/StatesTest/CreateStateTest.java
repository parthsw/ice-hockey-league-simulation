package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.Database.AbstractDatabaseFactory;
import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IOTest.IOMock;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CreateStateTest {

    private static IOMock ioMockInstance = null;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUp(){
        ioMockInstance = IOMock.instance();
    }

    @Test
    public void welcomeMessageTest(){
        AbstractState createTeamState = AbstractStateMachineFactory.getFactory().getCreateTeamState();
        createTeamState.welcomeMessage();
        Assert.assertTrue(ioMockInstance.getOutput().contains("************Welcome to team creation************"));
    }

    @Test
    public void onRunTest(){
        AbstractState createTeamState = AbstractStateMachineFactory.getFactory().getCreateTeamState();
        ioMockInstance.commandLineInput("");
    }


}
