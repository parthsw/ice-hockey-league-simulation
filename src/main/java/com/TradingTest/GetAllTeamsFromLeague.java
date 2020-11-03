package com.TradingTest;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class GetAllTeamsFromLeague {

    public GetAllTeamsFromLeague(ILeague league) {
       this.gatherTeams(league);
    }

    private List<ITeam> allTeams;

    private void gatherTeams(ILeague league){
        List<ITeam> teams = new ArrayList<ITeam>();
        List<IConference> conferences = this.getAllConferencesFromLeague(league);
        for(IConference conference : conferences){
            for(IDivision division : this.getAllDivisionsFromConference(conference)){
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


