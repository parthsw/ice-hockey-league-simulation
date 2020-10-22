package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Player.IPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IPlayerStats;
import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
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
        league.setFreeAgents(createFreeAgentList(leagueJson.getJSONArray(FREE_AGENTS)));
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
        team.setPlayers(createTeamPlayerList(((JSONObject) teamJson).getJSONArray(PLAYERS)));

        return team;
    }

    private List<ITeam> createTeamList(JSONArray teamsJson) {
        List<ITeam> teams = new ArrayList<>();
        for (Object team : teamsJson) {
            teams.add(createTeam(team));
        }
        return teams;
    }

    private IPlayer createPlayer(Object playerJson) {
        IPlayer player = AbstractLeagueManagerFactory.getFactory().getPlayer();

        player.setPlayerName(((JSONObject) playerJson).getString(PLAYER_NAME));
        player.setPlayerAge(((JSONObject) playerJson).getInt(AGE));

        IPlayerStats playerStats = AbstractLeagueManagerFactory.getFactory().getPlayerStats();
        playerStats.setPosition(((JSONObject) playerJson).getString(POSITION));
        playerStats.setSkating(((JSONObject) playerJson).getInt(SKATING));
        playerStats.setShooting(((JSONObject) playerJson).getInt(SHOOTING));
        playerStats.setChecking(((JSONObject) playerJson).getInt(CHECKING));
        playerStats.setSaving(((JSONObject) playerJson).getInt(SAVING));
        playerStats.setStrength(playerStats.calculateStrength());

        player.setPlayerStats(playerStats);

        return player;
    }

    private ITeamPlayer createTeamPlayer(Object playerJson) {
        ITeamPlayer teamPlayer = AbstractLeagueManagerFactory.getFactory().getTeamPlayer();
        teamPlayer.setIsCaptain(((JSONObject) playerJson).getBoolean(CAPTAIN));

        IPlayer player = createPlayer(playerJson);
        teamPlayer.setPlayerAge(player.getPlayerAge());
        teamPlayer.setPlayerName(player.getPlayerName());
        teamPlayer.setPlayerStats(player.getPlayerStats());

        return teamPlayer;
    }

    private List<ITeamPlayer> createTeamPlayerList(JSONArray teamPlayerJson) {
        List<ITeamPlayer> teamPlayers = new ArrayList<>();
        for (Object teamPlayer : teamPlayerJson) {
            teamPlayers.add(createTeamPlayer(teamPlayer));
        }
        return teamPlayers;
    }

    private IFreeAgent createFreeAgent(Object playerJson) {
        IFreeAgent freeAgent = AbstractLeagueManagerFactory.getFactory().getFreeAgent();

        IPlayer player = createPlayer(playerJson);
        freeAgent.setPlayerAge(player.getPlayerAge());
        freeAgent.setPlayerName(player.getPlayerName());
        freeAgent.setPlayerStats(player.getPlayerStats());

        return freeAgent;
    }

    private List<IFreeAgent> createFreeAgentList(JSONArray freeAgentJson) {
        List<IFreeAgent> freeAgents = new ArrayList<>();
        for (Object freeAgent : freeAgentJson) {
            freeAgents.add(createFreeAgent(freeAgent));
        }
        return freeAgents;
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
        coach.setCoachStats(createCoachStats(coachJson));

        return coach;
    }

    private ICoachStats createCoachStats(Object coachJson) {
        ICoachStats coachStats = AbstractLeagueManagerFactory.getFactory().getCoachStats();

        coachStats.setSkating(((JSONObject) coachJson).getFloat(SKATING));
        coachStats.setShooting(((JSONObject) coachJson).getFloat(SHOOTING));
        coachStats.setChecking(((JSONObject) coachJson).getFloat(CHECKING));
        coachStats.setSaving(((JSONObject) coachJson).getFloat(SAVING));

        return coachStats;
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
