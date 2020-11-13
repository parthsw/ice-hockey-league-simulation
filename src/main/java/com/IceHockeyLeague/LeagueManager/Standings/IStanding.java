package com.IceHockeyLeague.LeagueManager.Standings;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;

public interface IStanding {
    IConference getConference();
    void setConference(IConference conference);

    IDivision getDivision();
    void setDivision(IDivision division);

    ITeam getTeam();
    void setTeam(ITeam team);

    int getGamesPlayed();
    void setGamesPlayed(int gamesPlayed);
    void incrementGamesPlayed();

    int getGamesWon();
    void setGamesWon(int gamesWon);
    void incrementGamesWon();

    int getPoints();
    void setPoints(int points);
    void incrementPoints();
}
