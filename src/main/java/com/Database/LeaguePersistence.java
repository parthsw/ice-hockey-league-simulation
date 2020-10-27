package com.Database;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.*;

public class LeaguePersistence implements ILeaguePersistence {
    @Override
    public boolean saveLeague(ILeague league) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String leagueID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoLeague(?,?)}");

            myCall.setString(1, league.getLeagueName());
            myCall.registerOutParameter(2, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                leagueID = result.getString("leagueID");
            }
            myCall.close();
            league.setLeagueID(Integer.parseInt(leagueID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert League");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadLeague(int leagueId, ILeague league) {
        return false;
    }

    @Override
    public boolean checkIfLeagueNameExists(String leagueName) {
        return false;
    }
}
