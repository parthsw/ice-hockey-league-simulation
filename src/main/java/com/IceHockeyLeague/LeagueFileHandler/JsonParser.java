package com.IceHockeyLeague.LeagueFileHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.InputStream;

public class JsonParser implements IJsonParser {

    public JSONObject parse(InputStream inputStream) throws JSONException {
        return new JSONObject(new JSONTokener(inputStream));
    }

}
