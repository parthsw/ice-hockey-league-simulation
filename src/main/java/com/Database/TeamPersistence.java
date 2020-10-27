package com.Database;

import com.IceHockeyLeague.LeagueManager.Player.ITeamPlayer;
import com.IceHockeyLeague.LeagueManager.Team.ITeam;
import com.IceHockeyLeague.LeagueManager.Team.ITeamPersistence;
import com.IceHockeyLeague.LeagueManager.Team.Team;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.*;
import java.util.List;

public class TeamPersistence implements ITeamPersistence {
    @Override
    public boolean saveTeam(ITeam team) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String teamID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoTeam(?,?,?,?)}");
            myCall.setInt(1, team.getDivisionID());
            myCall.setString(2, team.getTeamName());
            myCall.setBoolean(3,team.getIsUserCreated());
            myCall.setFloat(4, team.getTeamStrength());
            myCall.registerOutParameter(5, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                teamID = result.getString("teamID");
            }
            myCall.close();
            team.setTeamID(Integer.parseInt(teamID));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Team");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadTeams(int divisionId, List<ITeam> teams) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String teamID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoTeam(?)}");
            myCall.setInt(1, divisionId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                ITeam team = new Team();
                team.setTeamID(result.getInt("teamID"));
                team.setDivisionID(result.getInt("divisionID"));
                team.setTeamName(result.getString("name"));
                team.setTeamStrength(result.getInt("strength"));
                teams.add(team);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in load Team");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}
