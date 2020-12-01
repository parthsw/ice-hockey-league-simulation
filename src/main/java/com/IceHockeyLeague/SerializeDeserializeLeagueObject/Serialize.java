package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


public class Serialize implements ISerialize {

    private String path;
    private String jsonOutput;

    private final Logger LOGGER = LogManager.getLogger(Serialize.class);

    @Override
    public String serializeLeagueObject(ILeague league, String fileName) {
        Gson gson = new Gson();
        jsonOutput = gson.toJson(league);
        String file = fileName + ".json";
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(file);
            File obj = new File(file);
            path = obj.getAbsolutePath();
            myWriter.write("serialization_input_output\\" + jsonOutput);
            LOGGER.info("The file that has been serialized after creating a new team is "+file);
            myWriter.flush();
            myWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return path;
    }
}
