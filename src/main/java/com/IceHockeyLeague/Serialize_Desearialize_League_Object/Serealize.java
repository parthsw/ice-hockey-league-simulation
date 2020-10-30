package com.IceHockeyLeague.Serialize_Desearialize_League_Object;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.google.gson.*;

import java.io.*;

public class Serealize implements ISerealize {
    @Override
    public String serealizeLeagueObject(ILeague league) {
        Gson gson = new Gson();

        String jsonOutput = gson.toJson(league);
        File obj = null;
        FileWriter myWriter = null;
        try{
            myWriter = new FileWriter("jsonOutput.json");
            myWriter.write(jsonOutput);
            obj = new File("jsonOutput");
            String path = obj.getAbsolutePath();
            Deserealize objD = new Deserealize();
            objD.desrealizeLeagueObject(path);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
