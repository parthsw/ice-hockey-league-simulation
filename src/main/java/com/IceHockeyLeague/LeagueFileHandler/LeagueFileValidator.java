package com.IceHockeyLeague.LeagueFileHandler;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.List;

public class LeagueFileValidator implements ILeagueFileValidator {

    @Override
    public List<String> validateJsonSchema(JSONObject json, JSONObject schemaToValidate) {
        try {
            Schema schema = SchemaLoader.load(schemaToValidate);
            schema.validate(json);
        }
        catch (ValidationException validationException) {
            return validationException.getAllMessages();
        }
        return null;
    }

}
