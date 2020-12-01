package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetTradableTeams implements IGetTradableTeams {
    private List<ITeam> allTeams;
    private List<ITeam> reducedTeams;
    int lossPointValue;
    private final Logger LOGGER = LogManager.getLogger(GetTradableTeams.class);

    public GetTradableTeams(List<ITeam> teams, int lossPoint) {
        setDefaults(teams, lossPoint);
    }

    private void setDefaults(List<ITeam> teams, int lossPoint) {
        this.allTeams = teams;
        this.lossPointValue = lossPoint;
        this.reducedTeams = new ArrayList<>();
        LOGGER.info("GetTradableTeams initiated");
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
