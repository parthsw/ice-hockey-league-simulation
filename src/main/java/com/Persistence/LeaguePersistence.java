package com.Persistence;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Draft.DraftManager;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LeaguePersistence implements ILeaguePersistence {
    private final Logger LOGGER = LogManager.getLogger(LeaguePersistence.class);
    public boolean saveLeague(ILeague league){
        ISerializeDeserializeLeagueObjectFactory serializeDeserializeLeagueObjectFactory = AbstractAppFactory.getSerializeDeserializeLeagueObjectFactory();
        ISerialize s = serializeDeserializeLeagueObjectFactory.createSerialize();
        String teamName = "";
        List<IConference> conferences;
        List<IDivision> divisions;
        List<ITeam> teams;
        conferences = league.getConferences();
        for(IConference c : conferences){
            divisions = c.getDivisions();
            for(IDivision d : divisions){
                teams = d.getTeams();
                for(ITeam t : teams){
                    if(t.getIsUserCreated()){
                        teamName = t.getTeamName();
                        break;
                    }
                }
            }
        }
        LOGGER.info("The new team name that has been added as a serialized json file is "+teamName);
        s.serializeLeagueObject(league,teamName);
        return true;
    }
    public ILeague loadLeague(String filePath){
        ISerializeDeserializeLeagueObjectFactory serializeDeserializeLeagueObjectFactory = AbstractAppFactory.getSerializeDeserializeLeagueObjectFactory();
        IDeserialize d = serializeDeserializeLeagueObjectFactory.createDeserialize();
        ILeague league = d.deserializeLeagueObject(filePath);
        LOGGER.info("The league has been loaded with filepath "+filePath);
        return league;
    }
}
