package com.Database;

import com.IceHockeyLeague.LeagueManager.Coach.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.*;
public class CoachPersistence implements ICoachPersistence {

    @Override
    public boolean saveCoach(ICoach coach) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String coachID = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call insertIntoCoach(?,?,?,?)}");
            myCall.setInt(1, coach.getTeamID());
            myCall.setInt(2, coach.getLeagueID());
            myCall.setString(3, coach.getCoachName());
            myCall.setDouble(4,coach.getCoachStats().getSkating());
            myCall.setDouble(5,coach.getCoachStats().getShooting());
            myCall.setDouble(6,coach.getCoachStats().getChecking());
            myCall.setDouble(7,coach.getCoachStats().getSaving());
            myCall.registerOutParameter(8, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                coachID = result.getString("lastID");
            }
            myCall.close();
            coach.setCoachID(Integer.parseInt(coachID));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Coach");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
        return false;
    }

    @Override
    public boolean loadTeamCoach(int teamId, ICoach coach) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String coachID = null;
        String teamID = null;
        String leagueID = null;
        String name = null;
        String skating = null;
        String shooting = null;
        String checking = null;
        String saving = null;

        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadTeamCoach(?)}");
            myCall.setInt(1, teamId);

            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                ICoachStats stats = new CoachStats();
                coachID = result.getString("coachID");
                teamID = result.getString("teamID");
                leagueID = result.getString("leagueID");
                name = result.getString("name");
                skating = result.getString("skating");
                shooting = result.getString("shooting");
                checking = result.getString("checking");
                saving = result.getString("saving");
                coach.setCoachID(Integer.parseInt(coachID));
                coach.setTeamID(Integer.parseInt(teamID));
                coach.setLeagueID(Integer.parseInt(leagueID));
                coach.setCoachName(name);
                stats.setSkating(Double.parseDouble(skating));
                stats.setShooting(Double.parseDouble(shooting));
                stats.setChecking(Double.parseDouble(checking));
                stats.setSaving(Double.parseDouble(saving));
                coach.setCoachStats(stats);
            }
            myCall.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Coach");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches) {
        DBConnection connectionManager = null;
        Connection connection = null;
        String leagueID;
        String coachID;
        String teamID;
        String name;
        String skating;
        String shooting;
        String checking;
        String saving;

        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadLeagueCoaches(?)}");
            myCall.setInt(1, leagueId);

            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                ICoach coach = new Coach();
                ICoachStats stats = new CoachStats();
                coachID = result.getString("coachID");
                teamID = result.getString("teamID");
                leagueID = result.getString("leagueID");
                name = result.getString("name");
                skating = result.getString("skating");
                shooting = result.getString("shooting");
                checking = result.getString("checking");
                saving = result.getString("saving");
                coach.setCoachID(Integer.parseInt(coachID));
                coach.setTeamID(Integer.parseInt(teamID));
                coach.setLeagueID(Integer.parseInt(leagueID));
                coach.setCoachName(name);
                stats.setSkating(Float.parseFloat(skating));
                stats.setShooting(Float.parseFloat(shooting));
                stats.setChecking(Float.parseFloat(checking));
                stats.setSaving(Float.parseFloat(saving));
                coach.setCoachStats(stats);
                coaches.add(coach);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Coach");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }
}
