package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.Database.AbstractDatabaseFactory;
import com.IO.AbstractIOFactory;
import com.IO.IOFactory;
import com.IOTest.IOMock;
import com.IceHockeyLeague.LeagueManager.Coach.Coach;
import com.IceHockeyLeague.LeagueManager.Conference.Conference;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Manager.Manager;
import com.IceHockeyLeague.LeagueManager.Team.Team;
import com.IceHockeyLeague.StateMachine.AbstractStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import java.util.*;
import org.junit.rules.TemporaryFolder;

public class CreateStateTest {

    private static IOMock ioMockInstance = null;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setUp() {
        ioMockInstance = IOMock.instance();
    }

    @Test
    public void welcomeMessageTest() {
        AbstractState createTeamState = AbstractStateMachineFactory.getFactory().getCreateTeamState();
        createTeamState.welcomeMessage();
        Assert.assertTrue(ioMockInstance.getOutput().contains("************Welcome to team creation************"));
    }

    @Test
    public void onRunTest() {
        AbstractState createTeamState = AbstractStateMachineFactory.getFactory().getCreateTeamState();
        ioMockInstance.commandLineInput("");
    }

    @Test
    public void isNullOrEmptyTest() {
        Division division = new Division();
        Conference conference = new Conference();
        Team team = new Team();
        Manager manager = new Manager();
        Coach coach = new Coach();
        boolean flag = division.isNullOrEmpty("");
        Assert.assertTrue("true", flag);
        flag = conference.isNullOrEmpty("");
        Assert.assertTrue("true", flag);
        flag = team.isNullOrEmpty("");
        Assert.assertTrue("true", flag);
        flag = manager.isNullOrEmpty("");
        Assert.assertTrue("true", flag);
        flag = coach.isNullOrEmpty("");
        Assert.assertTrue("true", flag);
    }

    @Test
    public void isConferenceNameExistTest() {
        List<IConference> conferences = new ArrayList<>();
        boolean flag = false;
        Conference conference1 = new Conference();
        conference1.setConferenceName("Eastern Conference");
        Conference conference2 = new Conference();
        conference2.setConferenceName("Eastern Conference 1");
        Conference conference3 = new Conference();
        conference3.setConferenceName("Eastern Conference 2");
        conferences.add(conference1);
        conferences.add(conference2);
        conferences.add(conference3);
        Conference conference = new Conference();
        flag = conference.isConferenceNameExist(conferences,"Eastern Conference 2");
        Assert.assertTrue("true",flag);
    }
}

