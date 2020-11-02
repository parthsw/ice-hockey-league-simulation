package com.Database;

import com.IceHockeyLeague.LeagueManager.League.ILeague;
import com.IceHockeyLeague.LeagueManager.League.ILeaguePersistence;

import java.sql.*;

public class LeaguePersistence implements ILeaguePersistence {
    @Override
    public boolean saveLeague(ILeague league) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String leagueID = null;
        try {
            storedProcedure = AbstractDatabaseFactory.getFactory().getStoredProcedure();
            myCall = storedProcedure.setup("insertIntoLeague(?,?,?)");

            myCall.setString(1, league.getLeagueName());
            myCall.setDate(2, Date.valueOf(league.getLeagueDate()));
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                leagueID = result.getString("leagueID");
            }
            league.setLeagueID(Integer.parseInt(leagueID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert League");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadLeague(int leagueId, ILeague league) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        try {
            storedProcedure = AbstractDatabaseFactory.getFactory().getStoredProcedure();
            myCall = storedProcedure.setup("loadLeague(?)");

            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                league.setLeagueID(result.getInt("leagueID"));
                league.setLeagueName(result.getString("name"));
                league.setLeagueDate(result.getDate("date").toLocalDate());
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading League");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean checkIfLeagueNameExists(String leagueName) {
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String leagueID = null;
        try {
            storedProcedure = AbstractDatabaseFactory.getFactory().getStoredProcedure();
            myCall = storedProcedure.setup("checkIfLeagueNameExists(?)");
            myCall.setString(1, leagueName);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                leagueID = result.getString("leagueID");
            }
            if(leagueID == null){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in checking League");
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}
