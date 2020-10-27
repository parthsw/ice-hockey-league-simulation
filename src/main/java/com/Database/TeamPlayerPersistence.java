package com.Database;

import com.IceHockeyLeague.LeagueManager.Player.*;

import java.util.List;
import java.sql.*;

public class TeamPlayerPersistence implements ITeamPlayerPersistence {
    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String playerID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoPlayer(?,?,?,?,?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, teamPlayer.getTeamID());
            myCall.setString(2, teamPlayer.getPlayerName());
            myCall.setString(3, teamPlayer.getPlayerStats().getPosition());
            myCall.setInt(4, teamPlayer.getPlayerAge());
            myCall.setInt(5, teamPlayer.getPlayerStats().getSkating());
            myCall.setInt(6, teamPlayer.getPlayerStats().getShooting());
            myCall.setInt(7, teamPlayer.getPlayerStats().getChecking());
            myCall.setInt(8, teamPlayer.getPlayerStats().getSaving());
            myCall.setBoolean(9, teamPlayer.isCaptain());
            myCall.setDouble(10, teamPlayer.getPlayerStats().getStrength());
            myCall.setBoolean(11, teamPlayer.getIsInjured());

            myCall.registerOutParameter(12, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("playerID");
            }
            myCall.close();
            teamPlayer.setTeamPlayerID(Integer.parseInt(playerID));
            return true;
        } catch (SQLException e) {
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
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadTeamPlayers(?)}");
            myCall.setInt(1, teamId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                ITeamPlayer player = new TeamPlayer();
                IPlayerStats stats = new PlayerStats();
                stats.setSkating(result.getInt("skating"));
                stats.setSkating(result.getInt("shooting"));
                stats.setSkating(result.getInt("checking"));
                stats.setSkating(result.getInt("saving"));
                stats.setStrength(result.getInt("strength"));

                player.setTeamPlayerID(result.getInt("playerID"));
                player.setTeamID(result.getInt("teamID"));
                player.setPlayerName(result.getString("name"));
                player.setPlayerAge(result.getInt("age"));
                player.setPlayerStats(stats);
                player.setIsInjured(result.getBoolean("isInjured"));
                player.setIsCaptain(result.getBoolean("captain"));
                teamPlayers.add(player);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in load player");
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}
