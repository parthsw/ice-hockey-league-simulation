package com.Trading;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;

import java.util.ArrayList;
import java.util.List;

public class getTradableTeams {
    private List<ITeam> allTeams;
    private List<ITeam> reducedTeams;
    int lossPointValue;

    public getTradableTeams(List<ITeam> teams, int lossPoint){
        setDefaults(teams,lossPoint);
    }

    private void setDefaults(List<ITeam> teams, int lossPoint) {
        this.allTeams = teams;
        this.lossPointValue = lossPoint;
        this.reducedTeams = new ArrayList<ITeam>();
    }

    public List<ITeam> getTeams() {
        for (ITeam team : this.allTeams) {
            if (team.getLossPointValue() >= this.lossPointValue) {
                this.reducedTeams.add(team);
            }
        }
        return reducedTeams;
    }
}
