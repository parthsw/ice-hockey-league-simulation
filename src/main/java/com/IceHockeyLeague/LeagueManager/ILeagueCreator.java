package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import org.json.JSONObject;

public interface ILeagueCreator {
    ILeague createLeague(JSONObject jsonObject);
}
