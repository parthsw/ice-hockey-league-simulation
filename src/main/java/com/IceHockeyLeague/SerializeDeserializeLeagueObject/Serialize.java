package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.google.gson.*;

import java.io.*;

public class Serialize implements ISerialize {

    private String path;
    private String jsonOutput;

    @Override
    public String serializeLeagueObject(ILeague league, String fileName) {
        Gson gson = new Gson();
        jsonOutput = gson.toJson(league);
        String file = fileName+".json";
        FileWriter myWriter = null;
        try{
            myWriter = new FileWriter(file);
            File obj = new File(file);
            path = obj.getAbsolutePath();
            myWriter.write(jsonOutput);
            myWriter.flush();
            myWriter.close();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return path;
    }
}
