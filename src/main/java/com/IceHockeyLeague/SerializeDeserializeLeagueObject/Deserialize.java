package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.google.gson.Gson;

import java.io.File;
import java.util.Scanner;

public class Deserialize implements IDeserialize {
    private final ILeagueManagerFactory leagueManagerFactory;

    public Deserialize() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    public ILeague deserializeLeagueObject(String path){
        Gson gson = new Gson();
        String jsonData = "";
        ILeague league = leagueManagerFactory.createLeague();
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

