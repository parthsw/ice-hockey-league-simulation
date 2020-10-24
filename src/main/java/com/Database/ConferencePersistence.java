package com.Database;

import com.IceHockeyLeague.LeagueManager.Conference.IConference;
import com.IceHockeyLeague.LeagueManager.Conference.IConferencePersistence;

import java.sql.*;
import java.util.List;

public class ConferencePersistence implements IConferencePersistence {
    @Override
    public boolean saveConference(IConference conference) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String conferenceID = null;
        CallableStatement myCall;

        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoConference(?,?,?)}");
            myCall.setInt(1, conference.getLeagueID());
            myCall.setString(2, conference.getConferenceName());
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                conferenceID = result.getString("lastID");
            }
            myCall.close();
            conference.setConferenceID(Integer.parseInt(conferenceID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Conference");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
        return false;
    }

    @Override
    public boolean loadConferences(int leagueId, List<IConference> conferences) {
        return false;
    }
}
