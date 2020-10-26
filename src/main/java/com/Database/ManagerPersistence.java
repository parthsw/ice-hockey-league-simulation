package com.Database;

import com.IceHockeyLeague.LeagueManager.Manager.IManager;
import com.IceHockeyLeague.LeagueManager.Manager.IManagerPersistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.*;

public class ManagerPersistence implements IManagerPersistence {
    @Override
    public boolean saveManager(IManager manager) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String managerID = null;
        CallableStatement myCall;
        try {

            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoManager(?,?,?,?)}");
            myCall.setInt(1, manager.getTeamID());
            myCall.setInt(2, manager.getLeagueID());
            myCall.setString(3, manager.getManagerName());
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                managerID = result.getString("lastID");
            }
            myCall.close();
            manager.setManagerID(Integer.parseInt(managerID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Manager");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
        return false;
    }

    @Override
    public boolean loadTeamManager(int leagueId, int teamId, IManager manager) {
        return false;
    }

    @Override
    public boolean loadLeagueManagers(int leagueId, List<IManager> manager) {
        return false;
    }
}
