package com.IceHockeyLeague.Serialize_Desearialize_League_Object;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Deserealize implements IDeserealize {
    public ILeague desrealizeLeagueObject(String path){
        Gson gson = new Gson();
        String jsonData = "";
        ILeague league = null;
        Scanner myReader = null;
        try {
            File obj = new File("path/jsonOutput.json");
            myReader = new Scanner(obj);
            while(myReader.hasNextLine()) {
                jsonData = myReader.nextLine();
            }
             league = gson.fromJson(jsonData, ILeague.class);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        finally{
            myReader.close();
        }
        return league;
    }
}

