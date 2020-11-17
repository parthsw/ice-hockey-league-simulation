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
            myCall = storedProcedure.setup("insertIntoFreeAgent(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            IPlayerStats freeAgentStats = freeAgent.getPlayerStats();
            IPlayerAgeInfo freeAgentAgeInfo = freeAgent.getPlayerAgeInfo();

            myCall.setInt(1, freeAgent.getLeagueID());
            myCall.setString(2, freeAgent.getPlayerName());
            myCall.setDate(3, sqlDateConversion.convertLocalDateToSQLDate(freeAgentAgeInfo.getBirthDate()));
            myCall.setInt(4, freeAgentAgeInfo.getAgeInYears());
            myCall.setInt(5, freeAgentAgeInfo.getElapsedDaysFromLastBDay());
            myCall.setBoolean(6, freeAgent.getInjuredStatus());
            myCall.setInt(7, freeAgent.getDaysInjured());

            Date sqlInjuryDate = sqlDateConversion.convertLocalDateToSQLDate(freeAgent.getInjuryDate());
            if (sqlInjuryDate == null) {
                myCall.setNull(8, Types.DATE);
            } else {
                myCall.setDate(8, sqlInjuryDate);
            }

            myCall.setBoolean(9, freeAgent.getRetiredStatus());

            Date sqlRetirementDate = sqlDateConversion.convertLocalDateToSQLDate(freeAgent.getRetirementDate());
            if (sqlRetirementDate == null) {
                myCall.setNull(10, Types.DATE);
            } else {
                myCall.setDate(10, sqlRetirementDate);
            }

            myCall.setString(11, freeAgentStats.getPosition());
            myCall.setInt(12, freeAgentStats.getSkating());
            myCall.setInt(13, freeAgentStats.getShooting());
            myCall.setInt(14, freeAgentStats.getChecking());
            myCall.setInt(15, freeAgentStats.getSaving());
            myCall.setFloat(16, freeAgentStats.getStrength());
            myCall.registerOutParameter(17, Types.INTEGER);
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

                IPlayerAgeInfo freeAgentAgeInfo = leagueManagerFactory.createPlayerAgeInfo();
                freeAgentAgeInfo.setAgeInYears(result.getInt("age"));
                freeAgentAgeInfo.setElapsedDaysFromLastBDay(result.getInt("elapsedDaysFromLastBDay"));
                freeAgentAgeInfo.setBirthDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("birthDate")));

                IFreeAgent freeAgent = leagueManagerFactory.createFreeAgent();
                freeAgent.setLeagueID(result.getInt("leagueID"));
                freeAgent.setFreeAgentID(result.getInt("freeAgentID"));
                freeAgent.setPlayerName(result.getString("name"));
                freeAgent.setInjuredStatus(result.getBoolean("isInjured"));
                freeAgent.setDaysInjured(result.getInt("daysInjured"));
                freeAgent.setInjuryDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("injuryDate")));
                freeAgent.setRetiredStatus(result.getBoolean("isRetired"));
                freeAgent.setRetirementDate(sqlDateConversion.convertSQLDateToLocalDate(result.getDate("retirementDate")));
                freeAgent.setPlayerAgeInfo(freeAgentAgeInfo);
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

