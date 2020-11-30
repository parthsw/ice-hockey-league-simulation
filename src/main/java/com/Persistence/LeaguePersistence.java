package com.Persistence;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.SerializeDeserializeLeagueObject.*;

import java.util.List;

public class LeaguePersistence implements ILeaguePersistence {
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
        s.serializeLeagueObject(league,teamName);
        return true;
    }
    public ILeague loadLeague(String filePath){
        ISerializeDeserializeLeagueObjectFactory serializeDeserializeLeagueObjectFactory = AbstractAppFactory.getSerializeDeserializeLeagueObjectFactory();
        IDeserialize d = serializeDeserializeLeagueObjectFactory.createDeserialize();
        ILeague league = d.deserializeLeagueObject(filePath);
        return league;
    }
}
