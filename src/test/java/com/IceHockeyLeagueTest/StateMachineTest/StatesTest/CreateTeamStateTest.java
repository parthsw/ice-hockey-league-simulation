package com.IceHockeyLeagueTest.StateMachineTest.StatesTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IOTest.IOMock;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.StateMachine.IStateMachineFactory;
import com.IceHockeyLeague.StateMachine.States.AbstractState;
import com.IceHockeyLeague.StateMachine.States.CreateTeamState;
import org.junit.*;

import java.util.*;
import org.junit.rules.TemporaryFolder;

public class CreateTeamStateTest {
    private static IStateMachineFactory stateMachineFactory;
    private static ILeagueManagerFactory leagueManagerFactory;
    private static IOMock ioMockInstance = null;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        stateMachineFactory = appFactory.createStateMachineFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
        ioMockInstance = IOMock.instance();
    }

    @Before
    public void setupSystemOutput() {
        ioMockInstance.setupSystemOutput();
    }

    @After
    public void resetSystemOutput() {
        ioMockInstance.resetSystemOutput();
        ioMockInstance.resetSystemInput();
    }

    @Test
    public void welcomeMessageTest() {
        CreateTeamState createTeamState = (CreateTeamState) stateMachineFactory.createCreateTeamState();
        createTeamState.welcomeMessage();
        Assert.assertTrue(ioMockInstance.getOutput().contains("************Welcome to team creation************"));
    }

    @Test
    public void onRunTest() {
        AbstractState createTeamState = stateMachineFactory.createCreateTeamState();
        ioMockInstance.commandLineInput("");
        Assert.assertNull(createTeamState.onRun());
    }

    @Test
    public void isNullOrEmptyTest() {
        IDivision division = leagueManagerFactory.createDivision();
        IConference conference = leagueManagerFactory.createConference();
        ITeam team = leagueManagerFactory.createTeam();
        IManager manager = leagueManagerFactory.createManager();
        ICoach coach = leagueManagerFactory.createCoach();
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
        boolean flag;
        IConference conference1 = leagueManagerFactory.createConference();
        conference1.setConferenceName("Eastern Conference");
        IConference conference2 = leagueManagerFactory.createConference();
        conference2.setConferenceName("Eastern Conference 1");
        IConference conference3 = leagueManagerFactory.createConference();
        conference3.setConferenceName("Eastern Conference 2");
        conferences.add(conference1);
        conferences.add(conference2);
        conferences.add(conference3);
        IConference conference = leagueManagerFactory.createConference();
        flag = conference.isConferenceNameExist(conferences,"Eastern Conference 2");
        Assert.assertTrue("true",flag);
    }

    @Test
    public void isDivisionNameExistTest(){
        List<IDivision> divisions = new ArrayList<>();
        boolean flag;
        IDivision division1 = leagueManagerFactory.createDivision();
        division1.setDivisionName("Atlantic");
        IDivision division2 = leagueManagerFactory.createDivision();
        division2.setDivisionName("Northern");
        IDivision division3 = leagueManagerFactory.createDivision();
        division3.setDivisionName("Southern");
        divisions.add(division1);
        divisions.add(division2);
        divisions.add(division3);
        IDivision division = leagueManagerFactory.createDivision();
        flag = division.isDivisionNameExist(divisions, "Atlantic");
        Assert.assertTrue("true",flag);
    }

    @Test
    public void isTeamNameExist(){
        List<ITeam> teams = new ArrayList<>();
        boolean flag;
        ITeam team1 = leagueManagerFactory.createTeam();
        team1.setTeamName("Team 1");
        ITeam team2 = leagueManagerFactory.createTeam();
        team2.setTeamName("Team 2");
        ITeam team3 = leagueManagerFactory.createTeam();
        team3.setTeamName("Team 3");
        ITeam team = leagueManagerFactory.createTeam();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        flag = team.isTeamNameExist(teams, "Team 2");
        Assert.assertTrue("true",flag);
    }

    @Test
    public void isManagerNameExistTest(){
        List<IManager> managers = new ArrayList<>();
        boolean flag;
        IManager manager1 = leagueManagerFactory.createManager();
        manager1.setManagerName("Parth Parmar");
        IManager manager2 = leagueManagerFactory.createManager();
        manager2.setManagerName("Tejasvi Vig");
        IManager manager = leagueManagerFactory.createManager();
        managers.add(manager1);
        managers.add(manager2);
        flag = manager.isManagerNameExist(managers,"Tejasvi Vig");
        Assert.assertTrue("true",flag);
    }

    @Test
    public void isCoachNameExist(){
        List<ICoach> coaches = new ArrayList<>();
        boolean flag;
        ICoach coach1 = leagueManagerFactory.createCoach();
        coach1.setCoachName("Rajveen Singh Chandok");
        ICoach coach2 = leagueManagerFactory.createCoach();
        coach2.setCoachName("Sagar Moghe");
        ICoach coach = leagueManagerFactory.createCoach();
        coaches.add(coach1);
        coaches.add(coach2);
        flag = coach.isCoachNameExist(coaches,"Sagar Moghe");
        Assert.assertTrue("true",flag);
    }
}

