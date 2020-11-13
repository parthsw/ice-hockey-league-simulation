package com.Trading;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public class DivisionUpdatorAfterTrading {

    public DivisionUpdatorAfterTrading(ILeague league, ITeam oldSendingTeam, ITeam newSendingTeam, ITeam oldReceivingTeam, ITeam newReceivingTeam) {
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
