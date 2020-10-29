package com.Database;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.sql.*;
import java.util.List;

public class FreeAgentPersistence implements IFreeAgentPersistence {

    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String playerID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoFreeAgent(?,?,?,?,?,?,?,?,?,?,?)}");

            IPlayerStats freeAgentStats = freeAgent.getPlayerStats();

            myCall.setInt(1, freeAgent.getLeagueID());
            myCall.setString(2, freeAgent.getPlayerName());
            myCall.setString(3, freeAgentStats.getPosition());
            myCall.setInt(4, freeAgent.getPlayerAge());
            myCall.setInt(5, freeAgentStats.getSkating());
            myCall.setInt(6, freeAgentStats.getShooting());
            myCall.setInt(7, freeAgentStats.getChecking());
            myCall.setInt(8, freeAgentStats.getSaving());
            myCall.setFloat(9, freeAgentStats.getStrength());
            myCall.setBoolean(10, freeAgent.getInjuredStatus());
            myCall.registerOutParameter(11, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("freeAgentID");
            }
            myCall.close();
            freeAgent.setFreeAgentID(Integer.parseInt(playerID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert free agent");
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadFreeAgents(?)}");
            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                IPlayerStats freeAgentStats = AbstractLeagueManagerFactory.getFactory().getPlayerStats();
                freeAgentStats.setPosition(result.getString("position"));
                freeAgentStats.setSkating(result.getInt("skating"));
                freeAgentStats.setShooting(result.getInt("shooting"));
                freeAgentStats.setChecking(result.getInt("checking"));
                freeAgentStats.setSaving(result.getInt("saving"));
                freeAgentStats.setStrength(result.getFloat("strength"));

                IFreeAgent freeAgent = AbstractLeagueManagerFactory.getFactory().getFreeAgent();
                freeAgent.setLeagueID(result.getInt("leagueID"));
                freeAgent.setFreeAgentID(result.getInt("freeAgentID"));
                freeAgent.setPlayerName(result.getString("name"));
                freeAgent.setPlayerAge(result.getInt("age"));
                freeAgent.setPlayerStats(freeAgentStats);
                freeAgent.setInjuredStatus(result.getBoolean("isInjured"));
                freeAgents.add(freeAgent);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in loading freeAgent");
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}

