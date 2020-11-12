package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.util.List;
import java.sql.*;

public class TeamPlayerPersistence implements ITeamPlayerPersistence {
    private final IDatabaseFactory databaseFactory;

    public TeamPlayerPersistence() {
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        IDateConversion sqlDateConversion = databaseFactory.createSQLDateConversion();
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String playerID = null;
        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("insertIntoPlayer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            IPlayerStats teamPlayerStats = teamPlayer.getPlayerStats();

            myCall.setInt(1, teamPlayer.getTeamID());
            myCall.setString(2, teamPlayer.getPlayerName());
            myCall.setBoolean(3, teamPlayer.isCaptain());
            myCall.setInt(4, teamPlayer.getPlayerAge());
            myCall.setInt(5, teamPlayer.getElapsedDaysFromLastBDay());
            myCall.setBoolean(6, teamPlayer.getInjuredStatus());
            myCall.setInt(7, teamPlayer.getDaysInjured());

            Date sqlInjuryDate = sqlDateConversion.convertLocalDateToSQLDate(teamPlayer.getInjuryDate());
            if (sqlInjuryDate == null) {
                myCall.setNull(8, Types.DATE);
            } else {
                myCall.setDate(8, sqlInjuryDate);
            }

            myCall.setBoolean(9, teamPlayer.getRetiredStatus());

            Date sqlRetirementDate = sqlDateConversion.convertLocalDateToSQLDate(teamPlayer.getRetirementDate());
            if (sqlRetirementDate == null) {
                myCall.setNull(10, Types.DATE);
            } else {
                myCall.setDate(10, sqlRetirementDate);
            }

            myCall.setString(11, teamPlayerStats.getPosition());
            myCall.setInt(12, teamPlayerStats.getSkating());
            myCall.setInt(13, teamPlayerStats.getShooting());
            myCall.setInt(14, teamPlayerStats.getChecking());
            myCall.setInt(15, teamPlayerStats.getSaving());
            myCall.setFloat(16, teamPlayerStats.getStrength());
            myCall.registerOutParameter(17, Types.INTEGER);

            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("playerID");
            }
            teamPlayer.setTeamPlayerID(Integer.parseInt(playerID));
            return true;
        } catch (SQLException e) {
            System.out.println("error in insert player");
            e.printStackTrace();
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadTeamPlayers(int teamId, List<ITeamPlayer> teamPlayers) {
        IDateConversion sqlDateConversion = databaseFactory.createSQLDateConversion();
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("loadTeamPlayers(?)");

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
                player.setIsCaptain(result.getBoolean("captain"));
                player.setPlayerAge(result.getInt("age"));
                player.setElapsedDaysFromLastBDay(result.getInt("elapsedDaysFromLastBDay"));
                player.setInjuredStatus(result.getBoolean("isInjured"));
                player.setDaysInjured(result.getInt("daysInjured"));
                player.setInjuryDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("injuryDate")));
                player.setRetiredStatus(result.getBoolean("isRetired"));
                player.setRetirementDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("retirementDate")));
                player.setPlayerStats(stats);

                teamPlayers.add(player);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("error in load player");
            e.printStackTrace();
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}
