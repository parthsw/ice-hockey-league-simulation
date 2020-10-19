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

    float getSkating();
    void setSkating(float value);

    float getShooting();
    void setShooting(float value);

    float getChecking();
    void setChecking(float value);

    float getSaving();
    void setSaving(float value);

    boolean isValid();
}
