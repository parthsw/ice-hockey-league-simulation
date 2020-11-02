package com.Database;

import com.IceHockeyLeague.LeagueManager.Division.Division;
import com.IceHockeyLeague.LeagueManager.Division.IDivision;
import com.IceHockeyLeague.LeagueManager.Division.IDivisionPersistence;

import java.sql.*;
import java.util.List;

public class DivisionPersistence implements IDivisionPersistence {
    @Override
    public boolean saveDivision(IDivision division) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String divisionID = null;

        try {
            storedProcedure = AbstractDatabaseFactory.getFactory().getStoredProcedure();
            myCall = storedProcedure.setup("insertIntoDivision(?,?,?)");
            myCall.setInt(1, division.getConferenceID());
            myCall.setString(2, division.getDivisionName());
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                divisionID = result.getString("divisionID");
            }
            division.setDivisionID(Integer.parseInt(divisionID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Division");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadDivisions(int conferenceId, List<IDivision> divisions) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;

        try {
            storedProcedure = AbstractDatabaseFactory.getFactory().getStoredProcedure();
            myCall = storedProcedure.setup("loadDivisions(?)");
            myCall.setInt(1, conferenceId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                IDivision division = new Division();
                division.setDivisionID(result.getInt("divisionID"));
                division.setConferenceID(result.getInt("ConferenceID"));
                division.setDivisionName(result.getString("name"));
                divisions.add(division);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading Division");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}
