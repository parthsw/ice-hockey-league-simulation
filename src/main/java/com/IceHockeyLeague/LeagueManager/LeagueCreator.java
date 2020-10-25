package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
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

    private static final String GAME_PLAY_CONFIG = "gameplayConfig";
    private static final String AGING = "aging";
    private static final String AVERAGE_RETIREMENT_AGE = "averageRetirementAge";
    private static final String MAXIMUM_AGE = "maximumAge";
    private static final String GAME_RESOLVER = "gameResolver";
    private static final String RANDOM_WIN_CHANCE = "randomWinChance";
    private static final String INJURIES = "injuries";
    private static final String RANDOM_INJURY_CHANCE = "randomInjuryChance";
    private static final String INJURY_DAYS_LOW = "injuryDaysLow";
    private static final String INJURY_DAYS_HIGH = "injuryDaysHigh";
    private static final String TRAINING = "training";
    private static final String DAYS_STAT_INCREASE_CHECK = "daysUntilStatIncreaseCheck";
    private static final String TRADING = "trading";
    private static final String LOSS_POINT = "lossPoint";
    private static final String RANDOM_TRADE_OFFER_CHANCE = "randomTradeOfferChance";
    private static final String MAX_PLAYERS_PER_TRADE = "maxPlayersPerTrade";
    private static final String RANDOM_ACCEPTANCE_CHANCE = "randomAcceptanceChance";

    public LeagueCreator() {
    }

    @Override
    public ILeague createLeague(JSONObject leagueJson) {
        ILeague league = AbstractLeagueManagerFactory.getFactory().getLeague();
        league.setLeagueName(leagueJson.getString(LEAGUE_NAME));

        league.setGamePlayConfig(createGamePlayConfig(leagueJson.getJSONObject(GAME_PLAY_CONFIG)));
        league.setConferences(createConferenceList(leagueJson.getJSONArray(CONFERENCES)));
        league.setFreeAgents(createFreeAgentList(leagueJson.getJSONArray(FREE_AGENTS)));
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

    private IGamePlayConfig createGamePlayConfig(JSONObject gamePlayConfigJson) {
        IGamePlayConfig gamePlayConfig = AbstractLeagueManagerFactory.getFactory().getGamePlayConfig();

        gamePlayConfig.setAgingConfig(createAgingConfig(gamePlayConfigJson.getJSONObject(AGING)));
        gamePlayConfig.setGameResolverConfig(createGameResolverConfig(gamePlayConfigJson.getJSONObject(GAME_RESOLVER)));
        gamePlayConfig.setInjuryConfig(createInjuryConfig(gamePlayConfigJson.getJSONObject(INJURIES)));
        gamePlayConfig.setTrainingConfig(createTrainingConfig(gamePlayConfigJson.getJSONObject(TRAINING)));
        gamePlayConfig.setTradingConfig(createTradingConfig(gamePlayConfigJson.getJSONObject(TRADING)));

        return gamePlayConfig;
    }

    private IAgingConfig createAgingConfig(JSONObject agingConfigJson) {
        IAgingConfig agingConfig = AbstractLeagueManagerFactory.getFactory().getAgingConfig();
        agingConfig.setAverageRetirementAge(agingConfigJson.getInt(AVERAGE_RETIREMENT_AGE));
        agingConfig.setMaximumAge(agingConfigJson.getInt(MAXIMUM_AGE));
        return agingConfig;
    }

    private IGameResolverConfig createGameResolverConfig(JSONObject gameResolverConfigJson) {
        IGameResolverConfig gameResolverConfig = AbstractLeagueManagerFactory.getFactory().getGameResolverConfig();
        gameResolverConfig.setRandomWinChance(gameResolverConfigJson.getDouble(RANDOM_WIN_CHANCE));
        return gameResolverConfig;
    }

    private IInjuryConfig createInjuryConfig(JSONObject injuryConfigJson) {
        IInjuryConfig injuryConfig = AbstractLeagueManagerFactory.getFactory().getInjuryConfig();
        injuryConfig.setRandomInjuryChance(injuryConfigJson.getDouble(RANDOM_INJURY_CHANCE));
        injuryConfig.setInjuryDaysLow(injuryConfigJson.getInt(INJURY_DAYS_LOW));
        injuryConfig.setInjuryDaysHigh(injuryConfigJson.getInt(INJURY_DAYS_HIGH));
        return injuryConfig;
    }

    private ITrainingConfig createTrainingConfig(JSONObject trainingConfigJson) {
        ITrainingConfig trainingConfig = AbstractLeagueManagerFactory.getFactory().getTrainingConfig();
        trainingConfig.setDaysUntilStatIncreaseCheck(trainingConfigJson.getInt(DAYS_STAT_INCREASE_CHECK));
        return trainingConfig;
    }

    private ITradingConfig createTradingConfig(JSONObject tradingConfigJson) {
        ITradingConfig tradingConfig = AbstractLeagueManagerFactory.getFactory().getTradingConfig();
        tradingConfig.setLossPoint(tradingConfigJson.getInt(LOSS_POINT));
        tradingConfig.setMaxPlayersPerTrade(tradingConfigJson.getInt(MAX_PLAYERS_PER_TRADE));
        tradingConfig.setRandomAcceptanceChance(tradingConfigJson.getDouble(RANDOM_ACCEPTANCE_CHANCE));
        tradingConfig.setRandomTradeOfferChance(tradingConfigJson.getDouble(RANDOM_TRADE_OFFER_CHANCE));
        return tradingConfig;
    }
}
