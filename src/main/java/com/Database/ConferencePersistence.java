package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;

import java.sql.*;
import java.util.List;

public class ConferencePersistence implements IConferencePersistence {
    private final IDatabaseFactory databaseFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public ConferencePersistence() {
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public boolean saveConference(IConference conference) {
        String conferenceID = null;
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;

        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("insertIntoConference(?,?,?)");
            myCall.setInt(1, conference.getLeagueID());
            myCall.setString(2, conference.getConferenceName());
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                conferenceID = result.getString("conferenceID");
            }
            conference.setConferenceID(Integer.parseInt(conferenceID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Conference");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;

        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("loadConferences(?)");
            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                IConference conference = leagueManagerFactory.createConference();
                conference.setConferenceID(result.getInt("conferenceID"));
                conference.setLeagueID(result.getInt("leagueID"));
                conference.setConferenceName(result.getString("name"));
                conferences.add(conference);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading Conference");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}
