package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.google.gson.Gson;
import org.junit.Assert;

import java.io.*;
import java.util.Scanner;

public class Deserialize implements IDeserialize {

    public ILeague deserializeLeagueObject(String path){
        Gson gson = new Gson();
        String jsonData = "";
        ILeague league = new League();
        String leagueName = "";
        Scanner myReader = null;
        try {
            File obj = new File("jsonInput");
            myReader = new Scanner(obj);
            while(myReader.hasNextLine()) {
                jsonData = myReader.nextLine();
            }
             league = gson.fromJson(jsonData, League.class);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        finally {
            myReader.close();
        }
        return league;
    }
}

