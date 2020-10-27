package com.Database;

import com.IceHockeyLeague.LeagueManager.Player.IFreeAgent;
import com.IceHockeyLeague.LeagueManager.Player.IFreeAgentPersistence;
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
            myCall.setInt(1, freeAgent.getLeagueID());
            myCall.setString(2, freeAgent.getPlayerName());
            myCall.setString(3, freeAgent.getPlayerStats().getPosition());
            myCall.setInt(4, freeAgent.getPlayerAge());
            myCall.setInt(5, freeAgent.getPlayerStats().getSkating());
            myCall.setInt(6, freeAgent.getPlayerStats().getShooting());
            myCall.setInt(7, freeAgent.getPlayerStats().getChecking());
            myCall.setInt(8, freeAgent.getPlayerStats().getSaving());
            myCall.setDouble(9, freeAgent.getPlayerStats().getStrength());
            myCall.setBoolean(10, freeAgent.getIsInjured());
            myCall.registerOutParameter(11, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("playerID");
            }
            myCall.close();
            freeAgent.setFreeAgentID(Integer.parseInt(playerID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert player");
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
        return false;
    }
}
