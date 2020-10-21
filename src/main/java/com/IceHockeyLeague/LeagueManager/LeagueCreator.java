package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeagueCreator implements ILeagueCreator {

    private static final String LEAGUE_NAME = "leagueName";
    private static final String CONFERENCES = "conferences";
    private static final String FREE_AGENTS = "freeAgents";
    private static final String COACHES = "coaches";
    private static final String GENERAL_MANAGERS = "generalManagers";
    private static final String DIVISIONS = "divisions";
    private static final String TEAMS = "teams";
    private static final String PLAYERS = "players";


    private static final String CONFERENCE_NAME = "conferenceName";
    private static final String DIVISION_NAME = "divisionName";

    private static final String TEAM_NAME = "teamName";
    private static final String GENERAL_MANAGER = "generalManager";
    private static final String HEAD_COACH = "headCoach";

    private static final String COACH_NAME = "name";

    private static final String PLAYER_NAME = "playerName";
    private static final String POSITION = "position";
    private static final String AGE = "age";
    private static final String SKATING = "skating";
    private static final String SHOOTING = "shooting";
    private static final String CHECKING = "checking";
    private static final String SAVING = "saving";

    private static final String CAPTAIN = "captain";

    public LeagueCreator() {
    }

    @Override
    public ILeague createLeague(JSONObject leagueJson) {
        ILeague league = AbstractLeagueManagerFactory.getFactory().getLeague();
        league.setLeagueName(leagueJson.getString(LEAGUE_NAME));

        league.setConferences(createConferenceList(leagueJson.getJSONArray(CONFERENCES)));
        league.setFreeAgents(null);
        // TODO: set gamePlayConfig
        league.setCoaches(createCoachList(leagueJson.getJSONArray(COACHES)));
        league.setManagers(createManagerList(leagueJson.getJSONArray(GENERAL_MANAGERS)));

        return league;
    }

    private IConference createConference(Object conferenceJson) {
        IConference conference = AbstractLeagueManagerFactory.getFactory().getConference();

        conference.setConferenceName(((JSONObject) conferenceJson).getString(CONFERENCE_NAME));
        conference.setDivisions(createDivisionList(((JSONObject) conferenceJson).getJSONArray(DIVISIONS)));

        return conference;
    }

    private List<IConference> createConferenceList(JSONArray conferencesJson) {
        List<IConference> conferences = new ArrayList<>();
        for (Object conference : conferencesJson) {
            conferences.add(createConference(conference));
        }
        return conferences;
    }

    private IDivision createDivision(Object divisionJson) {
        IDivision division = AbstractLeagueManagerFactory.getFactory().getDivision();

        division.setDivisionName(((JSONObject) divisionJson).getString(DIVISION_NAME));
        division.setTeams(createTeamList(((JSONObject) divisionJson).getJSONArray(TEAMS)));

        return division;
    }

    private List<IDivision> createDivisionList(JSONArray divisionsJson) {
        List<IDivision> divisions = new ArrayList<>();
        for (Object division : divisionsJson) {
            divisions.add(createDivision(division));
        }
        return divisions;
    }

    private ITeam createTeam(Object teamJson) {
        ITeam team = AbstractLeagueManagerFactory.getFactory().getTeam();

        team.setTeamName(((JSONObject) teamJson).getString(TEAM_NAME));
        team.setManager(createManager(((JSONObject) teamJson).getString(GENERAL_MANAGER)));
        team.setCoach(createCoach(((JSONObject) teamJson).getJSONObject(HEAD_COACH)));
        team.setPlayers(null); // TODO;

        return team;
    }

    private List<ITeam> createTeamList(JSONArray teamsJson) {
        List<ITeam> teams = new ArrayList<>();
        for (Object team : teamsJson) {
            teams.add(createTeam(team));
        }
        return teams;
    }

    private IManager createManager(String generalManager) {
        IManager manager = AbstractLeagueManagerFactory.getFactory().getManager();
        manager.setManagerName(generalManager);
        return manager;
    }

    private List<IManager> createManagerList(JSONArray managersJson) {
        List<IManager> managers = new ArrayList<>();
        for (Object manager : managersJson) {
            managers.add(createManager(manager.toString()));
        }
        return managers;
    }

    private ICoach createCoach(Object coachJson) {
        ICoach coach = AbstractLeagueManagerFactory.getFactory().getCoach();

        coach.setCoachName(((JSONObject) coachJson).getString(COACH_NAME));
        coach.setSkating(((JSONObject) coach).getFloat(SKATING));
        coach.setShooting(((JSONObject) coach).getFloat(SHOOTING));
        coach.setChecking(((JSONObject) coach).getFloat(CHECKING));
        coach.setSaving(((JSONObject) coach).getFloat(SAVING));

        return coach;
    }

    private List<ICoach> createCoachList(JSONArray coachesJson) {
        List<ICoach> coaches = new ArrayList<>();
        for (Object coach : coachesJson) {
            coaches.add(createCoach(coach));
        }
        return coaches;
    }

// TODO:

//    private List<IFreeAgent> createFreeAgents(JSONArray freeAgentsJson) {
//        List<IFreeAgent> freeAgents = new ArrayList<IFreeAgent>();
//
//        for(Object playerJson: freeAgentsJson) {
//            IFreeAgent freeAgent = AbstractLeagueFactory.getFactory().getFreeAgent();
//            freeAgent.setName(((JSONObject) playerJson).getString(PLAYER_NAME));
//            freeAgent.setPosition(((JSONObject) playerJson).getString(POSITION));
//            freeAgent.setAge(((JSONObject) playerJson).getInt(AGE));
//            freeAgent.setSkating(((JSONObject) playerJson).getInt(SKATING));
//            freeAgent.setShooting(((JSONObject) playerJson).getInt(SHOOTING));
//            freeAgent.setChecking(((JSONObject) playerJson).getInt(CHECKING));
//            freeAgent.setSaving(((JSONObject) playerJson).getInt(SAVING));
//            freeAgents.add(freeAgent); // No need to set teamID or playerID
//        }
//        return freeAgents;
//    }
//
//    private List<IPlayer> createPlayers(JSONArray playersJson) {
//        List<IPlayer> players = new ArrayList<IPlayer>();
//
//        for(Object playerJson: playersJson) {
//            IPlayer player = AbstractLeagueFactory.getFactory().getPlayer();
//            player.setPlayerName(((JSONObject) playerJson).getString(PLAYER_NAME));
//            player.setPosition(((JSONObject) playerJson).getString(POSITION));
//            player.setIsCaptain((JSONOBject));
//            player.setAge(((JSONObject) playerJson).getInt(AGE));
//            player.setSkating(((JSONObject) playerJson).getInt(SKATING));
//            player.setShooting(((JSONObject) playerJson).getInt(SHOOTING));
//            player.setChecking(((JSONObject) playerJson).getInt(CHECKING));
//            player.setSaving(((JSONObject) playerJson).getInt(SAVING));
//            players.add(player);
//        }
//        return players;
//    }
}
