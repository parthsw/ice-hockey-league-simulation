package com.IceHockeyLeague.LeagueFileHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public interface IJsonParser {
    JSONObject parse(InputStream inputStream) throws JSONException;
}
