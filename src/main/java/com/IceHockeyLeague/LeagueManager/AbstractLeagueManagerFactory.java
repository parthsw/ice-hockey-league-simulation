package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.Coach.ICoachStats;
import com.IceHockeyLeague.LeagueManager.GamePlayConfig.*;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;
import com.IceHockeyLeague.LeagueManager.Player.*;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;
import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;
import com.IceHockeyLeague.LeagueManager.Team.ITeamStrengthCalculator;


public abstract class AbstractLeagueManagerFactory {
    private static AbstractLeagueManagerFactory abstractLeagueManagerFactory;

    public static AbstractLeagueManagerFactory getFactory() {
        return abstractLeagueManagerFactory;
    }

    public static void setFactory(AbstractLeagueManagerFactory leagueFactory) {
        abstractLeagueManagerFactory = leagueFactory;
    }

    public abstract ILeagueCreator getLeagueCreator();
    public abstract ILeague getLeague();
    public abstract ILeaguePersistence getLeagueDB();

    public abstract IConference getConference();
    public abstract IConferencePersistence getConferenceDB();

    public abstract IDivision getDivision();
    public abstract IDivisionPersistence getDivisionDB();

    public abstract ITeam getTeam();
    public abstract ITeamStrengthCalculator getTeamStrengthCalculator();
    public abstract ITeamPersistence getTeamDB();

    public abstract IPlayer getPlayer();
    public abstract IPlayerStats getPlayerStats();
    public abstract IPlayerCareerProgression getPlayerCareerProgression();
    public abstract ITeamPlayer getTeamPlayer();
    public abstract ITeamPlayerPersistence getTeamPlayerDB();
    public abstract IFreeAgent getFreeAgent();
    public abstract IFreeAgentPersistence getFreeAgentDB();

    public abstract ICoach getCoach();
    public abstract ICoachPersistence getCoachDB();
    public abstract ICoachStats getCoachStats();

    public abstract IManager getManager();
    public abstract IManagerPersistence getManagerDB();

    public abstract IGamePlayConfig getGamePlayConfig();
    public abstract IGamePlayConfigPersistence getGamePlayConfigDB();

    public abstract IAgingConfig getAgingConfig();
    public abstract IGameResolverConfig getGameResolverConfig();
    public abstract IInjuryConfig getInjuryConfig();
    public abstract ITrainingConfig getTrainingConfig();
    public abstract ITradingConfig getTradingConfig();

    public abstract IRandomChance getRandomChance();
}
