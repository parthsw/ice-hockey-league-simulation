package com.PersistenceTest;

import com.AbstractAppFactory;
import com.AppFactoryTest;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Draft.DraftPick.IDraftPick;
import com.IceHockeyLeague.LeagueManager.FreeAgent.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Scheduler.ISchedule;
import com.IceHockeyLeague.LeagueManager.Standings.IStanding;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeagueTest.LeagueFileHandlerTest.LeagueJsonMock;
import com.Persistence.ILeaguePersistence;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class LeaguePersistenceMock implements ILeaguePersistence {
    private final ILeagueManagerFactory leagueManagerFactory;

    public LeaguePersistenceMock() {
        AbstractAppFactory.setAppFactory(AppFactoryTest.createAppFactory());
        AbstractAppFactory appFactory = AbstractAppFactory.getAppFactory();
        leagueManagerFactory = appFactory.createLeagueManagerFactory();
    }

    public boolean loadLeague(int leagueId, ILeague league) {
        LeagueJsonMock leagueJsonMock = LeagueJsonMock.instance();
        ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();
        ITeamPlayer retiredTeamPlayer = leagueManagerFactory.createTeamPlayer();
        ILeague createdLeague = leagueCreator.createLeague(leagueJsonMock.validLeagueJson());

        league.setLeagueName(createdLeague.getLeagueName());
        league.setLeagueID(leagueId);
        league.setLeagueDate(LocalDate.of(2020, Month.SEPTEMBER, 30));
        league.setGamePlayConfig(createdLeague.getGamePlayConfig());
        league.setConferences(createdLeague.getConferences());
        league.setFreeAgents(createdLeague.getFreeAgents());
        league.setManagers(createdLeague.getManagers());
        league.setCoaches(createdLeague.getCoaches());
        league.getStandingSystem().setStandings(mockStandings(league));
        league.getScheduleSystem().setPlayoffSchedule(mockSchedules(league));
        league.getScheduleSystem().setRegularSeasonStartDate(LocalDate.of(2020, Month.SEPTEMBER, 30));
        league.setDraftPicks(mockDrafting(league.getStandingSystem().getStandings()));
        IFreeAgent retiredAgent = createdLeague.getFreeAgents().get(0);
        retiredAgent.setRetiredStatus(true);
        retiredAgent.setRetirementDate(league.getLeagueDate());

        retiredAgent.convertToTeamPlayer(retiredTeamPlayer);
        retiredTeamPlayer.setRetiredStatus(true);
        retiredTeamPlayer.setRetirementDate(league.getLeagueDate());

        league.addRetiredFreeAgent(retiredAgent);
        league.addRetiredTeamPlayer(retiredTeamPlayer);
        return true;
    }

    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }

    @Override
    public boolean saveLeague(ILeague league) {
        return false;
    }

    @Override
    public ILeague loadLeague(String filePath) {
        ILeague league = leagueManagerFactory.createLeague();
        loadLeague(1, league);
        return league;
    }

    private List<IStanding> mockStandings(ILeague league) {
        List<IConference> leagueConferences = league.getConferences();
        IConference conference1 = leagueConferences.get(0);
        IConference conference2 = leagueConferences.get(1);

        List<IDivision> conference1Divisions = conference1.getDivisions();
        IDivision conference1Division1 = conference1Divisions.get(0);
        IDivision conference1Division2 = conference1Divisions.get(1);

        List<IDivision> conference2Divisions = conference2.getDivisions();
        IDivision conference2Division1 = conference2Divisions.get(0);
        IDivision conference2Division2 = conference2Divisions.get(1);

        List<ITeam> conference1Division1Teams = conference1Division1.getTeams();
        ITeam conference1Division1Team1 = conference1Division1Teams.get(0);
        ITeam conference1Division1Team2 = conference1Division1Teams.get(1);

        List<ITeam> conference1Division2Teams = conference1Division2.getTeams();
        ITeam conference1Division2Team1 = conference1Division2Teams.get(0);
        ITeam conference1Division2Team2 = conference1Division2Teams.get(1);

        List<ITeam> conference2Division1Teams = conference2Division1.getTeams();
        ITeam conference2Division1Team1 = conference2Division1Teams.get(0);
        ITeam conference2Division1Team2 = conference2Division1Teams.get(1);

        List<ITeam> conference2Division2Teams = conference2Division2.getTeams();
        ITeam conference2Division2Team1 = conference2Division2Teams.get(0);
        ITeam conference2Division2Team2 = conference2Division2Teams.get(1);

        IStanding standing1 = leagueManagerFactory.createStanding();
        standing1.setConference(conference1);
        standing1.setDivision(conference1Division1);
        standing1.setTeam(conference1Division1Team1);
        standing1.setGamesPlayed(7);
        standing1.setGamesWon(4);
        standing1.setPoints(8);

        IStanding standing2 = leagueManagerFactory.createStanding();
        standing2.setConference(conference1);
        standing2.setDivision(conference1Division1);
        standing2.setTeam(conference1Division1Team2);
        standing2.setGamesPlayed(7);
        standing2.setGamesWon(3);
        standing2.setPoints(6);

        IStanding standing3 = leagueManagerFactory.createStanding();
        standing3.setConference(conference1);
        standing3.setDivision(conference1Division2);
        standing3.setTeam(conference1Division2Team1);
        standing3.setGamesPlayed(7);
        standing3.setGamesWon(0);
        standing3.setPoints(0);

        IStanding standing4 = leagueManagerFactory.createStanding();
        standing4.setConference(conference1);
        standing4.setDivision(conference1Division2);
        standing4.setTeam(conference1Division2Team2);
        standing4.setGamesPlayed(6);
        standing4.setGamesWon(1);
        standing4.setPoints(2);

        IStanding standing5 = leagueManagerFactory.createStanding();
        standing5.setConference(conference2);
        standing5.setDivision(conference2Division1);
        standing5.setTeam(conference2Division1Team1);
        standing5.setGamesPlayed(7);
        standing5.setGamesWon(5);
        standing5.setPoints(12);

        IStanding standing6 = leagueManagerFactory.createStanding();
        standing6.setConference(conference2);
        standing6.setDivision(conference2Division1);
        standing6.setTeam(conference2Division1Team2);
        standing6.setGamesPlayed(7);
        standing6.setGamesWon(4);
        standing6.setPoints(8);

        IStanding standing7 = leagueManagerFactory.createStanding();
        standing7.setConference(conference2);
        standing7.setDivision(conference2Division2);
        standing7.setTeam(conference2Division2Team1);
        standing7.setGamesPlayed(7);
        standing7.setGamesWon(0);
        standing7.setPoints(0);

        IStanding standing8 = leagueManagerFactory.createStanding();
        standing8.setConference(conference2);
        standing8.setDivision(conference2Division2);
        standing8.setTeam(conference2Division2Team2);
        standing8.setGamesPlayed(7);
        standing8.setGamesWon(2);
        standing8.setPoints(4);

        List<IStanding> standings = new ArrayList<>();
        standings.add(standing1);
        standings.add(standing2);
        standings.add(standing3);
        standings.add(standing4);
        standings.add(standing5);
        standings.add(standing6);
        standings.add(standing7);
        standings.add(standing8);

        return standings;
    }

    private List<ISchedule> mockSchedules(ILeague league) {
        List<IConference> leagueConferences = league.getConferences();
        IConference conference1 = leagueConferences.get(0);
        IConference conference2 = leagueConferences.get(1);

        List<IDivision> conference1Divisions = conference1.getDivisions();
        IDivision conference1Division1 = conference1Divisions.get(0);

        List<IDivision> conference2Divisions = conference2.getDivisions();
        IDivision conference2Division1 = conference2Divisions.get(0);

        List<ITeam> conference1Division1Teams = conference1Division1.getTeams();
        ITeam conference1Division1Team1 = conference1Division1Teams.get(0);
        ITeam conference1Division1Team2 = conference1Division1Teams.get(1);

        List<ITeam> conference2Division1Teams = conference2Division1.getTeams();
        ITeam conference2Division1Team1 = conference2Division1Teams.get(0);
        ITeam conference2Division1Team2 = conference2Division1Teams.get(1);

        List<ISchedule> mockSchedules = new ArrayList<>();

        ISchedule schedule = leagueManagerFactory.createSchedule();
        schedule.setFirstConference(conference1);
        schedule.setFirstDivision(conference1Division1);
        schedule.setFirstTeam(conference1Division1Team1);
        schedule.setSecondConference(conference2);
        schedule.setSecondDivision(conference2Division1);
        schedule.setSecondTeam(conference2Division1Team1);
        schedule.setIsGamePlayed(true);
        schedule.setDate(LocalDate.of(2020, Month.APRIL, 13));
        schedule.setWinningTeam(conference2Division1Team1);

        ISchedule schedule1 = leagueManagerFactory.createSchedule();
        schedule1.setFirstConference(conference1);
        schedule1.setFirstDivision(conference1Division1);
        schedule1.setFirstTeam(conference1Division1Team2);
        schedule1.setSecondConference(conference2);
        schedule1.setSecondDivision(conference2Division1);
        schedule1.setSecondTeam(conference2Division1Team2);
        schedule1.setIsGamePlayed(true);
        schedule1.setDate(LocalDate.of(2020, Month.APRIL, 14));
        schedule1.setWinningTeam(conference1Division1Team2);

        ISchedule schedule2 = leagueManagerFactory.createSchedule();
        schedule2.setFirstConference(conference1);
        schedule2.setFirstDivision(conference1Division1);
        schedule2.setFirstTeam(conference1Division1Team2);
        schedule2.setSecondConference(conference2);
        schedule2.setSecondDivision(conference2Division1);
        schedule2.setSecondTeam(conference2Division1Team1);
        schedule2.setIsGamePlayed(true);
        schedule2.setDate(LocalDate.of(2020, Month.APRIL, 15));
        schedule2.setWinningTeam(conference1Division1Team2);

        mockSchedules.add(schedule);
        mockSchedules.add(schedule1);
        mockSchedules.add(schedule2);

        return mockSchedules;
    }

    private List<IDraftPick> mockDrafting(List<IStanding> standings) {
        List<IDraftPick> draftPicks = new ArrayList<>();

        ITeam teamTradingAway = standings.get(0).getTeam();
        ITeam teamReceiving = standings.get(1).getTeam();
        IDraftPick simplePick = leagueManagerFactory.createDraftPick(teamTradingAway, teamReceiving, 1, leagueManagerFactory.createTeamPlayer());
        draftPicks.add(simplePick);

        return draftPicks;
    }

}
