package com.IceHockeyLeagueTest.LeagueSchedulerTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueScheduler.*;
import com.IceHockeyLeague.LeagueStandings.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSystemTest {
    private static ILeagueManagerFactory leagueManagerFactory;
    private static ILeagueSchedulerFactory leagueSchedulerFactory;
    private static ILeagueStandingsFactory leagueStandingsFactory;

    @BeforeClass
    public static void setup() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        AbstractAppFactory.setLeagueManagerFactory(appFactory.createLeagueManagerFactory());
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
        AbstractAppFactory.setLeagueSchedulerFactory(appFactory.createLeagueSchedulerFactory());
        leagueSchedulerFactory = AbstractAppFactory.getLeagueSchedulerFactory();
        AbstractAppFactory.setLeagueStandingsFactory(appFactory.createLeagueStandingsFactory());
        leagueStandingsFactory = AbstractAppFactory.getLeagueStandingsFactory();
    }

    private ILeague createDummyLeague() {
        ILeague league = leagueManagerFactory.createLeague();
        league.setConferences(new ArrayList<>());

        IConference conference1 = leagueManagerFactory.createConference();
        conference1.setDivisions(new ArrayList<>());
        IConference conference2 = leagueManagerFactory.createConference();
        conference2.setDivisions(new ArrayList<>());

        IDivision division1 = leagueManagerFactory.createDivision();
        division1.setTeams(new ArrayList<>());
        IDivision division2 = leagueManagerFactory.createDivision();
        division2.setTeams(new ArrayList<>());
        IDivision division3 = leagueManagerFactory.createDivision();
        division3.setTeams(new ArrayList<>());
        IDivision division4 = leagueManagerFactory.createDivision();
        division4.setTeams(new ArrayList<>());

        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        ITeam team3 = leagueManagerFactory.createTeam();
        ITeam team4 = leagueManagerFactory.createTeam();
        ITeam team5 = leagueManagerFactory.createTeam();
        ITeam team6 = leagueManagerFactory.createTeam();
        ITeam team7 = leagueManagerFactory.createTeam();
        ITeam team8 = leagueManagerFactory.createTeam();
        ITeam team9 = leagueManagerFactory.createTeam();
        ITeam team10 = leagueManagerFactory.createTeam();
        ITeam team11 = leagueManagerFactory.createTeam();
        ITeam team12 = leagueManagerFactory.createTeam();
        ITeam team13 = leagueManagerFactory.createTeam();
        ITeam team14 = leagueManagerFactory.createTeam();
        ITeam team15 = leagueManagerFactory.createTeam();
        ITeam team16 = leagueManagerFactory.createTeam();
        ITeam team17 = leagueManagerFactory.createTeam();
        ITeam team18 = leagueManagerFactory.createTeam();
        ITeam team19 = leagueManagerFactory.createTeam();
        ITeam team20 = leagueManagerFactory.createTeam();

        league.addConference(conference1);
        league.addConference(conference2);
        conference1.addDivision(division1);
        conference1.addDivision(division2);
        conference2.addDivision(division3);
        conference2.addDivision(division4);
        division1.addTeam(team1);
        division1.addTeam(team2);
        division1.addTeam(team3);
        division1.addTeam(team4);
        division1.addTeam(team5);
        division2.addTeam(team6);
        division2.addTeam(team7);
        division2.addTeam(team8);
        division2.addTeam(team9);
        division2.addTeam(team10);
        division3.addTeam(team11);
        division3.addTeam(team12);
        division3.addTeam(team13);
        division3.addTeam(team14);
        division3.addTeam(team15);
        division4.addTeam(team16);
        division4.addTeam(team17);
        division4.addTeam(team18);
        division4.addTeam(team19);
        division4.addTeam(team20);

        return league;
    }

    private List<IStanding> createDummyStandings(ILeague league) {
        IConference conference1 = league.getConferences().get(0);
        IConference conference2 = league.getConferences().get(1);
        IDivision division1 = conference1.getDivisions().get(0);
        IDivision division2 = conference1.getDivisions().get(1);
        IDivision division3 = conference2.getDivisions().get(0);
        IDivision division4 = conference2.getDivisions().get(1);
        ITeam team1 = division1.getTeams().get(0);
        ITeam team2 = division1.getTeams().get(1);
        ITeam team3 = division1.getTeams().get(2);
        ITeam team4 = division1.getTeams().get(3);
        ITeam team5 = division1.getTeams().get(4);
        ITeam team6 = division2.getTeams().get(0);
        ITeam team7 = division2.getTeams().get(1);
        ITeam team8 = division2.getTeams().get(2);
        ITeam team9 = division2.getTeams().get(3);
        ITeam team10 = division2.getTeams().get(4);
        ITeam team11 = division3.getTeams().get(0);
        ITeam team12 = division3.getTeams().get(1);
        ITeam team13 = division3.getTeams().get(2);
        ITeam team14 = division3.getTeams().get(3);
        ITeam team15 = division3.getTeams().get(4);
        ITeam team16 = division4.getTeams().get(0);
        ITeam team17 = division4.getTeams().get(1);
        ITeam team18 = division4.getTeams().get(2);
        ITeam team19 = division4.getTeams().get(3);
        ITeam team20 = division4.getTeams().get(4);

        IStanding standing1 = leagueStandingsFactory.createStanding();
        standing1.setConference(conference1);
        standing1.setDivision(division1);
        standing1.setTeam(team1);
        standing1.setGamesPlayed(7);
        standing1.setGamesWon(2);
        standing1.setPoints(4);

        IStanding standing2 = leagueStandingsFactory.createStanding();
        standing2.setConference(conference1);
        standing2.setDivision(division1);
        standing2.setTeam(team2);
        standing2.setGamesPlayed(7);
        standing2.setGamesWon(6);
        standing2.setPoints(12);

        IStanding standing3 = leagueStandingsFactory.createStanding();
        standing3.setConference(conference1);
        standing3.setDivision(division1);
        standing3.setTeam(team3);
        standing3.setGamesPlayed(8);
        standing3.setGamesWon(4);
        standing3.setPoints(8);

        IStanding standing4 = leagueStandingsFactory.createStanding();
        standing4.setConference(conference1);
        standing4.setDivision(division1);
        standing4.setTeam(team4);
        standing4.setGamesPlayed(7);
        standing4.setGamesWon(5);
        standing4.setPoints(11);

        IStanding standing5 = leagueStandingsFactory.createStanding();
        standing5.setConference(conference1);
        standing5.setDivision(division1);
        standing5.setTeam(team5);
        standing5.setGamesPlayed(8);
        standing5.setGamesWon(4);
        standing5.setPoints(9);

        IStanding standing6 = leagueStandingsFactory.createStanding();
        standing6.setConference(conference1);
        standing6.setDivision(division2);
        standing6.setTeam(team6);
        standing6.setGamesPlayed(7);
        standing6.setGamesWon(6);
        standing6.setPoints(12);

        IStanding standing7 = leagueStandingsFactory.createStanding();
        standing7.setConference(conference1);
        standing7.setDivision(division2);
        standing7.setTeam(team7);
        standing7.setGamesPlayed(8);
        standing7.setGamesWon(5);
        standing7.setPoints(10);

        IStanding standing8 = leagueStandingsFactory.createStanding();
        standing8.setConference(conference1);
        standing8.setDivision(division2);
        standing8.setTeam(team8);
        standing8.setGamesPlayed(7);
        standing8.setGamesWon(4);
        standing8.setPoints(9);

        IStanding standing9 = leagueStandingsFactory.createStanding();
        standing9.setConference(conference1);
        standing9.setDivision(division2);
        standing9.setTeam(team9);
        standing9.setGamesPlayed(8);
        standing9.setGamesWon(2);
        standing9.setPoints(4);

        IStanding standing10 = leagueStandingsFactory.createStanding();
        standing10.setConference(conference1);
        standing10.setDivision(division2);
        standing10.setTeam(team10);
        standing10.setGamesPlayed(8);
        standing10.setGamesWon(4);
        standing10.setPoints(8);

        IStanding standing11 = leagueStandingsFactory.createStanding();
        standing11.setConference(conference2);
        standing11.setDivision(division3);
        standing11.setTeam(team11);
        standing11.setGamesPlayed(8);
        standing11.setGamesWon(3);
        standing11.setPoints(7);

        IStanding standing12 = leagueStandingsFactory.createStanding();
        standing12.setConference(conference2);
        standing12.setDivision(division3);
        standing12.setTeam(team12);
        standing12.setGamesPlayed(8);
        standing12.setGamesWon(3);
        standing12.setPoints(6);

        IStanding standing13 = leagueStandingsFactory.createStanding();
        standing13.setConference(conference2);
        standing13.setDivision(division3);
        standing13.setTeam(team13);
        standing13.setGamesPlayed(7);
        standing13.setGamesWon(5);
        standing13.setPoints(10);

        IStanding standing14 = leagueStandingsFactory.createStanding();
        standing14.setConference(conference2);
        standing14.setDivision(division3);
        standing14.setTeam(team14);
        standing14.setGamesPlayed(7);
        standing14.setGamesWon(4);
        standing14.setPoints(8);

        IStanding standing15 = leagueStandingsFactory.createStanding();
        standing15.setConference(conference2);
        standing15.setDivision(division3);
        standing15.setTeam(team15);
        standing15.setGamesPlayed(7);
        standing15.setGamesWon(6);
        standing15.setPoints(12);

        IStanding standing16 = leagueStandingsFactory.createStanding();
        standing16.setConference(conference2);
        standing16.setDivision(division4);
        standing16.setTeam(team16);
        standing16.setGamesPlayed(7);
        standing16.setGamesWon(1);
        standing16.setPoints(2);

        IStanding standing17 = leagueStandingsFactory.createStanding();
        standing17.setConference(conference2);
        standing17.setDivision(division4);
        standing17.setTeam(team17);
        standing17.setGamesPlayed(6);
        standing17.setGamesWon(4);
        standing17.setPoints(9);

        IStanding standing18 = leagueStandingsFactory.createStanding();
        standing18.setConference(conference2);
        standing18.setDivision(division4);
        standing18.setTeam(team18);
        standing18.setGamesPlayed(8);
        standing18.setGamesWon(0);
        standing18.setPoints(1);

        IStanding standing19 = leagueStandingsFactory.createStanding();
        standing19.setConference(conference2);
        standing19.setDivision(division4);
        standing19.setTeam(team19);
        standing19.setGamesPlayed(7);
        standing19.setGamesWon(3);
        standing19.setPoints(6);

        IStanding standing20 = leagueStandingsFactory.createStanding();
        standing20.setConference(conference2);
        standing20.setDivision(division4);
        standing20.setTeam(team20);
        standing20.setGamesPlayed(7);
        standing20.setGamesWon(4);
        standing20.setPoints(8);

        List<IStanding> standings = new ArrayList<>();
        standings.add(standing1);
        standings.add(standing2);
        standings.add(standing3);
        standings.add(standing4);
        standings.add(standing5);
        standings.add(standing6);
        standings.add(standing7);
        standings.add(standing8);
        standings.add(standing9);
        standings.add(standing10);
        standings.add(standing11);
        standings.add(standing12);
        standings.add(standing13);
        standings.add(standing14);
        standings.add(standing15);
        standings.add(standing16);
        standings.add(standing17);
        standings.add(standing18);
        standings.add(standing19);
        standings.add(standing20);

        return standings;
    }

    @Test
    public void setRegularScheduleTest() {
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        List<ISchedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);

        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSchedule(scheduleList);
        Assert.assertEquals(scheduleList, scheduleSystem.getRegularSchedule());
        Assert.assertEquals(2, scheduleSystem.getRegularSchedule().size());
    }

    @Test
    public void setPlayoffScheduleTest() {
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        List<ISchedule> scheduleList = new ArrayList<>();
        scheduleList.add(schedule1);
        scheduleList.add(schedule2);

        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setPlayoffSchedule(scheduleList);
        Assert.assertEquals(scheduleList, scheduleSystem.getPlayoffSchedule());
        Assert.assertEquals(2, scheduleSystem.getPlayoffSchedule().size());
    }

    @Test
    public void setRegularSeasonStartDateTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        LocalDate date = LocalDate.now();
        scheduleSystem.setRegularSeasonStartDate(date);
        Assert.assertEquals(date, scheduleSystem.getRegularSeasonStartDate());
    }

    @Test
    public void setRegularSeasonEndDateTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        LocalDate date = LocalDate.now();
        scheduleSystem.setRegularSeasonEndDate(date);
        Assert.assertEquals(date, scheduleSystem.getRegularSeasonEndDate());
    }

    @Test
    public void setPlayoffStartDateTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        LocalDate date = LocalDate.now();
        scheduleSystem.setPlayoffStartDate(date);
        Assert.assertEquals(date, scheduleSystem.getPlayoffStartDate());
    }

    @Test
    public void setPlayoffEndDateTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        LocalDate date = LocalDate.now();
        scheduleSystem.setPlayoffEndDate(date);
        Assert.assertEquals(date, scheduleSystem.getPlayoffEndDate());
    }

    @Test
    public void setTradeDeadline() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        LocalDate date = LocalDate.now();
        scheduleSystem.setTradeDeadline(date);
        Assert.assertEquals(date, scheduleSystem.getTradeDeadline());
    }

    @Test
    public void generateRegularSeasonScheduleTest() {
        ILeague league = leagueManagerFactory.createLeague();
        IConference conference = leagueManagerFactory.createConference();
        List<IConference> conferences = new ArrayList<>();
        IDivision division = leagueManagerFactory.createDivision();
        List<IDivision> divisions = new ArrayList<>();
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        List<ITeam> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);

        division.setTeams(teams);
        divisions.add(division);
        conference.setDivisions(divisions);
        conferences.add(conference);
        league.setConferences(conferences);

        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(2));
        scheduleSystem.generateRegularSeasonSchedule(league);
        Assert.assertEquals(team1, scheduleSystem.getRegularSchedule().get(0).getFirstTeam());
        Assert.assertEquals(team2, scheduleSystem.getRegularSchedule().get(0).getSecondTeam());
        Assert.assertNotNull(scheduleSystem.getRegularSchedule().get(0).getDate());
    }

    @Test
    public void generatePlayoffScheduleTest() {
        ILeague league = createDummyLeague();
        List<IStanding> standingsList = createDummyStandings(league);

        IStandingSystem standingSystem = leagueStandingsFactory.createStandingSystem();
        standingSystem.setStandings(standingsList);

        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setPlayoffStartDate(LocalDate.now());
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.generatePlayoffSchedule(league, standingSystem);
        List<ISchedule> playoffSchedule = scheduleSystem.getPlayoffSchedule();

        Assert.assertEquals(standingsList.get(1).getConference(), playoffSchedule.get(0).getFirstConference());
        Assert.assertEquals(standingsList.get(1).getDivision(), playoffSchedule.get(0).getFirstDivision());
        Assert.assertEquals(standingsList.get(1).getTeam(), playoffSchedule.get(0).getFirstTeam());
        Assert.assertEquals(standingsList.get(9).getConference(), playoffSchedule.get(0).getSecondConference());
        Assert.assertEquals(standingsList.get(9).getDivision(), playoffSchedule.get(0).getSecondDivision());
        Assert.assertEquals(standingsList.get(9).getTeam(), playoffSchedule.get(0).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(0).getDate());

        Assert.assertEquals(standingsList.get(5).getConference(), playoffSchedule.get(1).getFirstConference());
        Assert.assertEquals(standingsList.get(5).getDivision(), playoffSchedule.get(1).getFirstDivision());
        Assert.assertEquals(standingsList.get(5).getTeam(), playoffSchedule.get(1).getFirstTeam());
        Assert.assertEquals(standingsList.get(2).getConference(), playoffSchedule.get(1).getSecondConference());
        Assert.assertEquals(standingsList.get(2).getDivision(), playoffSchedule.get(1).getSecondDivision());
        Assert.assertEquals(standingsList.get(2).getTeam(), playoffSchedule.get(1).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(1).getDate());

        Assert.assertEquals(standingsList.get(3).getConference(), playoffSchedule.get(2).getFirstConference());
        Assert.assertEquals(standingsList.get(3).getDivision(), playoffSchedule.get(2).getFirstDivision());
        Assert.assertEquals(standingsList.get(3).getTeam(), playoffSchedule.get(2).getFirstTeam());
        Assert.assertEquals(standingsList.get(4).getConference(), playoffSchedule.get(2).getSecondConference());
        Assert.assertEquals(standingsList.get(4).getDivision(), playoffSchedule.get(2).getSecondDivision());
        Assert.assertEquals(standingsList.get(4).getTeam(), playoffSchedule.get(2).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(2).getDate());

        Assert.assertEquals(standingsList.get(6).getConference(), playoffSchedule.get(3).getFirstConference());
        Assert.assertEquals(standingsList.get(6).getDivision(), playoffSchedule.get(3).getFirstDivision());
        Assert.assertEquals(standingsList.get(6).getTeam(), playoffSchedule.get(3).getFirstTeam());
        Assert.assertEquals(standingsList.get(7).getConference(), playoffSchedule.get(3).getSecondConference());
        Assert.assertEquals(standingsList.get(7).getDivision(), playoffSchedule.get(3).getSecondDivision());
        Assert.assertEquals(standingsList.get(7).getTeam(), playoffSchedule.get(3).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(3).getDate());

        Assert.assertEquals(standingsList.get(14).getConference(), playoffSchedule.get(4).getFirstConference());
        Assert.assertEquals(standingsList.get(14).getDivision(), playoffSchedule.get(4).getFirstDivision());
        Assert.assertEquals(standingsList.get(14).getTeam(), playoffSchedule.get(4).getFirstTeam());
        Assert.assertEquals(standingsList.get(11).getConference(), playoffSchedule.get(4).getSecondConference());
        Assert.assertEquals(standingsList.get(11).getDivision(), playoffSchedule.get(4).getSecondDivision());
        Assert.assertEquals(standingsList.get(11).getTeam(), playoffSchedule.get(4).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(4).getDate());

        Assert.assertEquals(standingsList.get(16).getConference(), playoffSchedule.get(5).getFirstConference());
        Assert.assertEquals(standingsList.get(16).getDivision(), playoffSchedule.get(5).getFirstDivision());
        Assert.assertEquals(standingsList.get(16).getTeam(), playoffSchedule.get(5).getFirstTeam());
        Assert.assertEquals(standingsList.get(10).getConference(), playoffSchedule.get(5).getSecondConference());
        Assert.assertEquals(standingsList.get(10).getDivision(), playoffSchedule.get(5).getSecondDivision());
        Assert.assertEquals(standingsList.get(10).getTeam(), playoffSchedule.get(5).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(5).getDate());

        Assert.assertEquals(standingsList.get(12).getConference(), playoffSchedule.get(6).getFirstConference());
        Assert.assertEquals(standingsList.get(12).getDivision(), playoffSchedule.get(6).getFirstDivision());
        Assert.assertEquals(standingsList.get(12).getTeam(), playoffSchedule.get(6).getFirstTeam());
        Assert.assertEquals(standingsList.get(13).getConference(), playoffSchedule.get(6).getSecondConference());
        Assert.assertEquals(standingsList.get(13).getDivision(), playoffSchedule.get(6).getSecondDivision());
        Assert.assertEquals(standingsList.get(13).getTeam(), playoffSchedule.get(6).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(6).getDate());

        Assert.assertEquals(standingsList.get(19).getConference(), playoffSchedule.get(7).getFirstConference());
        Assert.assertEquals(standingsList.get(19).getDivision(), playoffSchedule.get(7).getFirstDivision());
        Assert.assertEquals(standingsList.get(19).getTeam(), playoffSchedule.get(7).getFirstTeam());
        Assert.assertEquals(standingsList.get(18).getConference(), playoffSchedule.get(7).getSecondConference());
        Assert.assertEquals(standingsList.get(18).getDivision(), playoffSchedule.get(7).getSecondDivision());
        Assert.assertEquals(standingsList.get(18).getTeam(), playoffSchedule.get(7).getSecondTeam());
        Assert.assertNotNull(playoffSchedule.get(7).getDate());
    }

    @Test
    public void unplayedGameOnThisDateInRegularSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setIsGamePlayed(true);
        schedule1.setDate(LocalDate.now().plusDays(5));
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setDate(LocalDate.now().plusDays(7));
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setIsGamePlayed(true);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        Assert.assertTrue(scheduleSystem.anyUnplayedGamesOnThisDate(LocalDate.now().plusDays(7)));
    }

    @Test
    public void noUnplayedGameOnThisDateInRegularSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setIsGamePlayed(true);
        schedule1.setDate(LocalDate.now().plusDays(5));
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setDate(LocalDate.now().plusDays(7));
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setIsGamePlayed(true);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        Assert.assertFalse(scheduleSystem.anyUnplayedGamesOnThisDate(LocalDate.now().plusDays(6)));
    }

    @Test
    public void unplayedGameOnThisDateInPlayoffSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setDate(LocalDate.now().plusDays(82));
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setDate(LocalDate.now().plusDays(87));
        schedule4.setIsGamePlayed(false);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        Assert.assertTrue(scheduleSystem.anyUnplayedGamesOnThisDate(LocalDate.now().plusDays(87)));
    }

    @Test
    public void noUnplayedGameOnThisDateInPlayoffSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setDate(LocalDate.now().plusDays(82));
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setDate(LocalDate.now().plusDays(87));
        schedule4.setIsGamePlayed(false);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        Assert.assertFalse(scheduleSystem.anyUnplayedGamesOnThisDate(LocalDate.now().plusDays(86)));
    }

    @Test
    public void getScheduledMatchOnThisDateInRegularSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setDate(LocalDate.now().plusDays(5));
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setDate(LocalDate.now().plusDays(7));
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setDate(LocalDate.now().plusDays(52));
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setDate(LocalDate.now().plusDays(57));
        schedule4.setIsGamePlayed(false);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        ISchedule todaySchedule = scheduleSystem.getScheduledMatchOnThisDate(LocalDate.now().plusDays(7));
        Assert.assertEquals(LocalDate.now().plusDays(7), todaySchedule.getDate());
        Assert.assertFalse(todaySchedule.getIsGamePlayed());
    }

    @Test
    public void getScheduledMatchOnThisDateInPlayoffSeasonTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setRegularSeasonStartDate(LocalDate.now());
        scheduleSystem.setRegularSeasonEndDate(LocalDate.now().plusDays(50));
        scheduleSystem.setPlayoffStartDate(LocalDate.now().plusDays(75));
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(100));

        List<ISchedule> regularSeasonSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setDate(LocalDate.now().plusDays(5));
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setDate(LocalDate.now().plusDays(7));
        schedule2.setIsGamePlayed(false);
        regularSeasonSchedule.add(schedule1);
        regularSeasonSchedule.add(schedule2);
        scheduleSystem.setRegularSchedule(regularSeasonSchedule);

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setDate(LocalDate.now().plusDays(52));
        schedule3.setIsGamePlayed(true);
        ISchedule schedule4 = leagueSchedulerFactory.createSchedule();
        schedule4.setDate(LocalDate.now().plusDays(57));
        schedule4.setIsGamePlayed(false);
        playoffSchedule.add(schedule3);
        playoffSchedule.add(schedule4);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        ISchedule todaySchedule = scheduleSystem.getScheduledMatchOnThisDate(LocalDate.now().plusDays(57));
        Assert.assertEquals(LocalDate.now().plusDays(57), todaySchedule.getDate());
        Assert.assertFalse(todaySchedule.getIsGamePlayed());
    }

    @Test
    public void isStanleyCupWinnerDeterminedTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setIsGamePlayed(true);
        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setIsGamePlayed(false);
        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        Assert.assertFalse(scheduleSystem.isStanleyCupWinnerDetermined());
    }

    @Test
    public void isStanleyCupWinnerDeterminedNoPlayoffGeneratedTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        Assert.assertFalse(scheduleSystem.isStanleyCupWinnerDetermined());
    }

    @Test
    public void updateScheduleAfterFirstPlayoffGamePlayedTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setPlayoffStartDate(LocalDate.now());
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(10));

        IConference conference1 = leagueManagerFactory.createConference();
        IConference conference2 = leagueManagerFactory.createConference();
        IDivision division1 = leagueManagerFactory.createDivision();
        IDivision division2 = leagueManagerFactory.createDivision();
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setDate(LocalDate.now());
        schedule1.setIsGamePlayed(false);

        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setDate(LocalDate.now().plusDays(1));

        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        schedule1.setWinningTeam(team2);

        scheduleSystem.updateScheduleAfterGamePlayed(schedule1);
        List<ISchedule> playoffScheduleList = scheduleSystem.getPlayoffSchedule();

        Assert.assertEquals(3, playoffScheduleList.size());
        Assert.assertTrue(playoffScheduleList.get(0).getIsGamePlayed());
        Assert.assertEquals(conference2, playoffScheduleList.get(2).getFirstConference());
        Assert.assertEquals(division2, playoffScheduleList.get(2).getFirstDivision());
        Assert.assertEquals(team2, playoffScheduleList.get(2).getFirstTeam());
        Assert.assertNull(playoffScheduleList.get(2).getSecondConference());
        Assert.assertNull(playoffScheduleList.get(2).getSecondDivision());
        Assert.assertNull(playoffScheduleList.get(2).getSecondTeam());
        Assert.assertNull(playoffScheduleList.get(2).getWinningTeam());
        Assert.assertEquals(LocalDate.now().plusDays(2), playoffScheduleList.get(2).getDate());
        Assert.assertFalse(playoffScheduleList.get(2).getIsGamePlayed());
    }

    @Test
    public void updateScheduleAfterSecondPlayoffGamePlayedTest() {
        IScheduleSystem scheduleSystem = leagueSchedulerFactory.createScheduleSystem();
        scheduleSystem.setPlayoffStartDate(LocalDate.now());
        scheduleSystem.setPlayoffEndDate(LocalDate.now().plusDays(10));

        IConference conference1 = leagueManagerFactory.createConference();
        IConference conference2 = leagueManagerFactory.createConference();
        IConference conference3 = leagueManagerFactory.createConference();
        IConference conference4 = leagueManagerFactory.createConference();
        IDivision division1 = leagueManagerFactory.createDivision();
        IDivision division2 = leagueManagerFactory.createDivision();
        IDivision division3 = leagueManagerFactory.createDivision();
        IDivision division4 = leagueManagerFactory.createDivision();
        ITeam team1 = leagueManagerFactory.createTeam();
        ITeam team2 = leagueManagerFactory.createTeam();
        ITeam team3 = leagueManagerFactory.createTeam();
        ITeam team4 = leagueManagerFactory.createTeam();

        List<ISchedule> playoffSchedule = new ArrayList<>();
        ISchedule schedule1 = leagueSchedulerFactory.createSchedule();
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(division1);
        schedule1.setFirstTeam(team1);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(division2);
        schedule1.setSecondTeam(team2);
        schedule1.setDate(LocalDate.now());
        schedule1.setIsGamePlayed(true);
        schedule1.setWinningTeam(team1);

        ISchedule schedule2 = leagueSchedulerFactory.createSchedule();
        schedule2.setFirstConference(conference3);
        schedule2.setFirstDivision(division3);
        schedule2.setFirstTeam(team3);
        schedule2.setSecondConference(conference4);
        schedule2.setSecondDivision(division4);
        schedule2.setSecondTeam(team4);
        schedule2.setDate(LocalDate.now().plusDays(1));
        schedule2.setIsGamePlayed(false);

        ISchedule schedule3 = leagueSchedulerFactory.createSchedule();
        schedule3.setFirstConference(conference1);
        schedule3.setFirstDivision(division1);
        schedule3.setFirstTeam(team1);
        schedule3.setDate(LocalDate.now().plusDays(2));

        playoffSchedule.add(schedule1);
        playoffSchedule.add(schedule2);
        playoffSchedule.add(schedule3);
        scheduleSystem.setPlayoffSchedule(playoffSchedule);

        schedule2.setWinningTeam(team4);

        scheduleSystem.updateScheduleAfterGamePlayed(schedule2);
        List<ISchedule> playoffScheduleList = scheduleSystem.getPlayoffSchedule();

        Assert.assertEquals(3, playoffScheduleList.size());
        Assert.assertTrue(playoffScheduleList.get(1).getIsGamePlayed());
        Assert.assertEquals(conference1, playoffScheduleList.get(2).getFirstConference());
        Assert.assertEquals(division1, playoffScheduleList.get(2).getFirstDivision());
        Assert.assertEquals(team1, playoffScheduleList.get(2).getFirstTeam());
        Assert.assertEquals(conference4, playoffScheduleList.get(2).getSecondConference());
        Assert.assertEquals(division4, playoffScheduleList.get(2).getSecondDivision());
        Assert.assertEquals(team4, playoffScheduleList.get(2).getSecondTeam());
        Assert.assertNull(playoffScheduleList.get(2).getWinningTeam());
        Assert.assertEquals(LocalDate.now().plusDays(2), playoffScheduleList.get(2).getDate());
        Assert.assertFalse(playoffScheduleList.get(2).getIsGamePlayed());
    }
}