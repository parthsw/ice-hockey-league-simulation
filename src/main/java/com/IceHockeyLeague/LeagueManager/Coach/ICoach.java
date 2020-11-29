package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public interface ICoach {
    int getCoachId();

    void setCoachId(int coachId);

    String getCoachName();

    void setCoachName(String name);

    int getTeamId();

    void setTeamId(int teamId);

    int getLeagueId();

    void setLeagueId(int leagueId);

    ICoachStats getCoachStats();

    void setCoachStats(ICoachStats coachStats);

    boolean isValid();

    boolean saveTeamCoach(ICoachPersistence coachDb);

    boolean saveLeagueCoach(ICoachPersistence coachDb);

    boolean loadTeamCoach(ICoachPersistence coachDb, ICoach coach);

    boolean isNullOrEmpty(String coachName);

    boolean isCoachNameExist(List<ICoach> coaches, String coachName);
}
