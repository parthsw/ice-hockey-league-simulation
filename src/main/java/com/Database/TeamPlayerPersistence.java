package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.util.List;
import java.sql.*;

public class TeamPlayerPersistence implements ITeamPlayerPersistence {
    private final IDatabaseFactory databaseFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public TeamPlayerPersistence() {
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public boolean saveTeamPlayer(ITeamPlayer teamPlayer) {
        IDateConversion sqlDateConversion = databaseFactory.createSQLDateConversion();
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String playerID = null;
        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("insertIntoPlayer(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            IPlayerStats teamPlayerStats = teamPlayer.getPlayerStats();
            IPlayerAgeInfo teamPlayerAgeInfo = teamPlayer.getPlayerAgeInfo();

            myCall.setInt(1, teamPlayer.getTeamID());
            myCall.setString(2, teamPlayer.getPlayerName());
            myCall.setBoolean(3, teamPlayer.isCaptain());
            myCall.setDate(4, sqlDateConversion.convertLocalDateToSQLDate(teamPlayerAgeInfo.getBirthDate()));
            myCall.setInt(5, teamPlayerAgeInfo.getAgeInYears());
            myCall.setInt(6, teamPlayerAgeInfo.getElapsedDaysFromLastBDay());
            myCall.setBoolean(7, teamPlayer.getInjuredStatus());
            myCall.setInt(8, teamPlayer.getDaysInjured());

            Date sqlInjuryDate = sqlDateConversion.convertLocalDateToSQLDate(teamPlayer.getInjuryDate());
            if (sqlInjuryDate == null) {
                myCall.setNull(9, Types.DATE);
            } else {
                myCall.setDate(9, sqlInjuryDate);
            }

            myCall.setBoolean(10, teamPlayer.getRetiredStatus());

            Date sqlRetirementDate = sqlDateConversion.convertLocalDateToSQLDate(teamPlayer.getRetirementDate());
            if (sqlRetirementDate == null) {
                myCall.setNull(11, Types.DATE);
            } else {
                myCall.setDate(11, sqlRetirementDate);
            }

            myCall.setString(12, teamPlayerStats.getPosition());
            myCall.setInt(13, teamPlayerStats.getSkating());
            myCall.setInt(14, teamPlayerStats.getShooting());
            myCall.setInt(15, teamPlayerStats.getChecking());
            myCall.setInt(16, teamPlayerStats.getSaving());
            myCall.setFloat(17, teamPlayerStats.getStrength());
            myCall.registerOutParameter(18, Types.INTEGER);

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
                ITeamPlayer player = leagueManagerFactory.createTeamPlayer();
                IPlayerStats stats = leagueManagerFactory.createPlayerStats();
                stats.setPosition(result.getString("position"));
                stats.setSkating(result.getInt("skating"));
                stats.setShooting(result.getInt("shooting"));
                stats.setChecking(result.getInt("checking"));
                stats.setSaving(result.getInt("saving"));
                stats.setStrength(result.getInt("strength"));

                IPlayerAgeInfo teamPlayerAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
                teamPlayerAgeInfo.setAgeInYears(result.getInt("age"));
                teamPlayerAgeInfo.setElapsedDaysFromLastBDay(result.getInt("elapsedDaysFromLastBDay"));
                teamPlayerAgeInfo.setBirthDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("birthDate")));

                player.setTeamPlayerID(result.getInt("playerID"));
                player.setTeamID(result.getInt("teamID"));
                player.setPlayerName(result.getString("name"));
                player.setIsCaptain(result.getBoolean("captain"));
                player.setInjuredStatus(result.getBoolean("isInjured"));
                player.setDaysInjured(result.getInt("daysInjured"));
                player.setInjuryDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("injuryDate")));
                player.setRetiredStatus(result.getBoolean("isRetired"));
                player.setRetirementDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("retirementDate")));
                player.setPlayerStats(stats);
                player.setPlayerAgeInfo(teamPlayerAgeInfo);

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
