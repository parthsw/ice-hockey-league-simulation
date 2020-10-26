package com.Database;

import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.sql.*;
import java.util.List;

public class DivisionPersistence implements IDivisionPersistence {
    @Override
    public boolean saveDivision(IDivision division) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String divisionID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoDivision(?,?,?)}");
            myCall.setInt(1, division.getConferenceID());
            myCall.setString(2, division.getDivisionName());
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                divisionID = result.getString("lastID");
            }
            myCall.close();
            division.setDivisionID(Integer.parseInt(divisionID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Division");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
        return false;
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        return false;
    }
}
