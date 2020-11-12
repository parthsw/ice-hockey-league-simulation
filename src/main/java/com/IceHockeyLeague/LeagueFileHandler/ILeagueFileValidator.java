package com.IceHockeyLeague.LeagueFileHandler;

import org.json.JSONObject;
import java.util.List;

public interface ILeagueFileValidator {
    List<String> validateJsonSchema(JSONObject json, JSONObject schema);
}
