package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.IceHockeyLeague.LeagueManager.League.ILeague;

public interface IDeserialize {
    public ILeague deserializeLeagueObject(String path);
}
