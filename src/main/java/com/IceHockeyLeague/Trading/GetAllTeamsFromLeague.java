package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetAllTeamsFromLeague implements IGetAllTeamsFromLeague {
    private final Logger LOGGER = LogManager.getLogger(GetAllTeamsFromLeague.class);

    public GetAllTeamsFromLeague(ILeague league) {
        this.gatherTeams(league);
    }

    private List<ITeam> allTeams;

    public void gatherTeams(ILeague league) {
        if (league == null) {
            LOGGER.error("Null league provided");
        }
        List<ITeam> teams = new ArrayList<>();
        List<IConference> conferences = this.getAllConferencesFromLeague(league);
        for (IConference conference : conferences) {
            for (IDivision division : this.getAllDivisionsFromConference(conference)) {
                teams.addAll(this.getAllTeamsFromDivision(division));
            }
        }
        allTeams = teams;
    }

    private List<IConference> getAllConferencesFromLeague(ILeague league){
        return league.getConferences();
    }

    private List<IDivision> getAllDivisionsFromConference(IConference conference){
        return conference.getDivisions();
    }

    private List<ITeam> getAllTeamsFromDivision(IDivision division){
        return division.getTeams();
    }

    public List<ITeam> getTeams(){
        return allTeams;
    }
}


