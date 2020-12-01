package com.IceHockeyLeague.Trading;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DivisionUpdatorAfterTrading implements IDivisionUpdatorAfterTrading {
    private final Logger LOGGER = LogManager.getLogger(DivisionUpdatorAfterTrading.class);

    public void divisionUpdatorAfterTradingFunction(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldReceivingTeam, ITeam newReceivingTeam) {
        if (league == null) {
            LOGGER.error("Null league provided");
        }
        for (IConference conference : league.getConferences()) {
            for (IDivision division : conference.getDivisions()) {
                if (division.getTeams().contains(oldSendingTeam)) {
                    division.getTeams().remove(oldSendingTeam);
                    division.getTeams().add(newSendingTeam);
                }
                if (division.getTeams().contains(oldReceivingTeam)) {
                    division.getTeams().remove(oldReceivingTeam);
                    division.getTeams().add(newReceivingTeam);
                }
            }
        }
    }
}
