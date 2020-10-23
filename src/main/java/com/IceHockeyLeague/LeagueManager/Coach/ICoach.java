package com.IceHockeyLeague.LeagueManager.Coach;

public interface ICoach {
    int getCoachID();
    void setCoachID(int id);

    String getCoachName();
    void setCoachName(String name);

    int getTeamID();
    void setTeamID(int id);

    int getLeagueID();
    void setLeagueID(int id);

    ICoachStats getCoachStats();
    void setCoachStats(ICoachStats coachStats);

    boolean isValid();

    boolean saveCoach(ICoachPersistence coachDB);
    boolean loadTeamCoach(ICoachPersistence coachDB, ICoach coach);
}
