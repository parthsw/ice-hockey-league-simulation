package com.IceHockeyLeague.SerializeDeserializeLeagueObject;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Draft.DraftManager;
import com.IceHockeyLeague.LeagueManager.ILeagueCreator;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.League;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Scanner;

public class Deserialize implements IDeserialize {
    private final ILeagueManagerFactory leagueManagerFactory;
    private final Logger LOGGER = LogManager.getLogger(Deserialize.class);

    public Deserialize() {
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    public ILeague deserializeLeagueObject(String path) {
        ILeagueCreator leagueCreator = leagueManagerFactory.createLeagueCreator();
        Gson gson = new Gson();
        String jsonData = "";
        ILeague league = leagueManagerFactory.createLeague();
        Scanner myReader = null;
        try {
            File obj = new File(path);
            myReader = new Scanner(obj);
            while (myReader.hasNextLine()) {
                jsonData = myReader.nextLine();
            }
            league = gson.fromJson(jsonData, League.class);
            LOGGER.info("The League object has been de serialized with file path and name as " + path);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            myReader.close();
        }
        return league;
    }
}

