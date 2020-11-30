package com.IceHockeyLeague.LeagueManager;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import org.json.JSONException;
import org.json.JSONObject;

public interface ILeagueCreator {
    ILeague createLeague(JSONObject jsonObject) throws JSONException;
}
