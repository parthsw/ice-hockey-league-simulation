package com.Database;

import com.AbstractAppFactory;
import com.IceHockeyLeague.LeagueManager.ILeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Player.*;

import java.sql.*;
import java.util.List;

public class FreeAgentPersistence implements IFreeAgentPersistence {
    private final IDatabaseFactory databaseFactory;
    private final ILeagueManagerFactory leagueManagerFactory;

    public FreeAgentPersistence() {
        databaseFactory = AbstractAppFactory.getDatabaseFactory();
        leagueManagerFactory = AbstractAppFactory.getLeagueManagerFactory();
    }

    @Override
    public boolean saveFreeAgent(IFreeAgent freeAgent) {
        IDateConversion sqlDateConversion = databaseFactory.createSQLDateConversion();
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        String playerID = null;

        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("insertIntoFreeAgent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            IPlayerStats freeAgentStats = freeAgent.getPlayerStats();

            myCall.setInt(1, freeAgent.getLeagueID());
            myCall.setString(2, freeAgent.getPlayerName());
            myCall.setInt(3, freeAgent.getPlayerAge());
            myCall.setInt(4, freeAgent.getElapsedDaysFromLastBDay());
            myCall.setBoolean(5, freeAgent.getInjuredStatus());
            myCall.setInt(6, freeAgent.getDaysInjured());

            Date sqlInjuryDate = sqlDateConversion.convertLocalDateToSQLDate(freeAgent.getInjuryDate());
            if (sqlInjuryDate == null) {
                myCall.setNull(7, Types.DATE);
            } else {
                myCall.setDate(7, sqlInjuryDate);
            }

            myCall.setBoolean(8, freeAgent.getRetiredStatus());

            Date sqlRetirementDate = sqlDateConversion.convertLocalDateToSQLDate(freeAgent.getRetirementDate());
            if (sqlRetirementDate == null) {
                myCall.setNull(9, Types.DATE);
            } else {
                myCall.setDate(9, sqlRetirementDate);
            }

            myCall.setString(10, freeAgentStats.getPosition());
            myCall.setInt(11, freeAgentStats.getSkating());
            myCall.setInt(12, freeAgentStats.getShooting());
            myCall.setInt(13, freeAgentStats.getChecking());
            myCall.setInt(14, freeAgentStats.getSaving());
            myCall.setFloat(15, freeAgentStats.getStrength());
            myCall.registerOutParameter(16, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("freeAgentID");
            }
            freeAgent.setFreeAgentID(Integer.parseInt(playerID));
            return true;
        } catch (SQLException e) {
            System.out.println("error in insert free agent");
            e.printStackTrace();
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }

    @Override
    public boolean loadFreeAgents(int leagueId, List<IFreeAgent> freeAgents) {
        IDateConversion sqlDateConversion = databaseFactory.createSQLDateConversion();
        IStoredProcedure storedProcedure = null;
        CallableStatement myCall;
        try {
            storedProcedure = databaseFactory.createStoredProcedure();
            myCall = storedProcedure.setup("loadFreeAgents(?)");
            myCall.setInt(1, leagueId);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                IPlayerStats freeAgentStats = leagueManagerFactory.createPlayerStats();
                freeAgentStats.setPosition(result.getString("position"));
                freeAgentStats.setSkating(result.getInt("skating"));
                freeAgentStats.setShooting(result.getInt("shooting"));
                freeAgentStats.setChecking(result.getInt("checking"));
                freeAgentStats.setSaving(result.getInt("saving"));
                freeAgentStats.setStrength(result.getFloat("strength"));

                IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
                freeAgent.setLeagueID(result.getInt("leagueID"));
                freeAgent.setFreeAgentID(result.getInt("freeAgentID"));
                freeAgent.setPlayerName(result.getString("name"));
                freeAgent.setPlayerAge(result.getInt("age"));
                freeAgent.setElapsedDaysFromLastBDay(result.getInt("elapsedDaysFromLastBDay"));
                freeAgent.setInjuredStatus(result.getBoolean("isInjured"));
                freeAgent.setDaysInjured(result.getInt("daysInjured"));
                freeAgent.setInjuryDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("injuryDate")));
                freeAgent.setRetiredStatus(result.getBoolean("isRetired"));
                freeAgent.setRetirementDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("retirementDate")));

                freeAgent.setPlayerStats(freeAgentStats);

                freeAgents.add(freeAgent);
            }
            return true;
        } catch (SQLException e) {
            System.out.println("error in loading freeAgent");
            e.printStackTrace();
            return false;
        } finally {
            storedProcedure.cleanup();
        }
    }
}

