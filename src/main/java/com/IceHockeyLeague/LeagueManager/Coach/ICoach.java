package com.IceHockeyLeague.LeagueManager.Coach;

import java.util.List;

public interface ICoach {
    int getCoachId();

    void setCoachId(int id);

    String getCoachName();

    void setCoachName(String name);

    int getTeamId();

    void setTeamId(int id);

    int getLeagueId();

    void setLeagueId(int id);

    ICoachStats getCoachStats();

    void setCoachStats(ICoachStats coachStats);

    boolean isValid();

    boolean saveTeamCoach(ICoachPersistence coachDB);

    boolean saveLeagueCoach(ICoachPersistence coachDB);

    boolean loadTeamCoach(ICoachPersistence coachDB, ICoach coach);

    boolean isNullOrEmpty(String coachName);

    boolean isCoachNameExist(List<ICoach> coaches, String coachName);
}
