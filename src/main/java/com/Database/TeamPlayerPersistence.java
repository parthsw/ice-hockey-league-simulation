package com.Database;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
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

            IPlayerStats teamPlayerStats = AbstractLeagueManagerFactory.getFactory().getPlayerStats();

            myCall.setInt(1, teamPlayer.getTeamID());
            myCall.setString(2, teamPlayer.getPlayerName());
            myCall.setString(3, teamPlayerStats.getPosition());
            myCall.setInt(4, teamPlayer.getPlayerAge());
            myCall.setInt(5, teamPlayerStats.getSkating());
            myCall.setInt(6, teamPlayerStats.getShooting());
            myCall.setInt(7, teamPlayerStats.getChecking());
            myCall.setInt(8, teamPlayerStats.getSaving());
            myCall.setBoolean(9, teamPlayer.isCaptain());
            myCall.setFloat(10, teamPlayerStats.getStrength());
            myCall.setBoolean(11, teamPlayer.getInjuredStatus());

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
                stats.setPosition(result.getString("position"));
                stats.setSkating(result.getInt("skating"));
                stats.setShooting(result.getInt("shooting"));
                stats.setChecking(result.getInt("checking"));
                stats.setSaving(result.getInt("saving"));
                stats.setStrength(result.getInt("strength"));

                player.setTeamPlayerID(result.getInt("playerID"));
                player.setTeamID(result.getInt("teamID"));
                player.setPlayerName(result.getString("name"));
                player.setPlayerAge(result.getInt("age"));
                player.setPlayerStats(stats);
                player.setInjuredStatus(result.getBoolean("isInjured"));
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
