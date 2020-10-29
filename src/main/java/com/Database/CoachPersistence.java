package com.Database;

import com.IceHockeyLeague.LeagueManager.AbstractLeagueManagerFactory;
import com.IceHockeyLeague.LeagueManager.Coach.*;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.sql.*;

public class CoachPersistence implements ICoachPersistence {

    private boolean saveBaseCoach(ICoach coach, CallableStatement myCall) throws SQLException {
        String coachID = null;
        myCall.setInt(2, coach.getLeagueID());
        myCall.setString(3, coach.getCoachName());
        myCall.setFloat(4, coach.getCoachStats().getSkating());
        myCall.setFloat(5, coach.getCoachStats().getShooting());
        myCall.setFloat(6, coach.getCoachStats().getChecking());
        myCall.setFloat(7, coach.getCoachStats().getSaving());
        myCall.registerOutParameter(8, Types.INTEGER);
        ResultSet result = myCall.executeQuery();
        while (result.next()) {
            coachID = result.getString("coachID");
        }
        myCall.close();
        coach.setCoachID(Integer.parseInt(coachID));
        return true;
    }

    @Override
    public boolean saveTeamCoach(ICoach coach) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoCoach(?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, coach.getTeamID());
            return saveBaseCoach(coach, myCall);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert team Coach");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean saveLeagueCoach(ICoach coach) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();
            myCall = connection.prepareCall("{call insertIntoCoach(?,?,?,?,?,?,?,?)}");
            myCall.setNull(1, Types.INTEGER);
            return saveBaseCoach(coach, myCall);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert league Coach");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    @Override
    public boolean loadTeamCoach(int teamId, ICoach coach) {
        DBConnection connectionManager = null;
        Connection connection = null;
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadTeamCoach(?)}");
            myCall.setInt(1, teamId);

            ResultSet result = myCall.executeQuery();
            while (result.next()) {
                loadBaseCoach(result, coach);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading team Coach");
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
        CallableStatement myCall;
        try {
            connectionManager = AbstractDatabaseFactory.getFactory().getDBConnection();
            connection = connectionManager.getConnection();

            myCall = connection.prepareCall("{call loadLeagueCoaches(?)}");
            myCall.setInt(1, leagueId);

            ResultSet result = myCall.executeQuery();
            while (result.next()) {
                ICoach coach = AbstractLeagueManagerFactory.getFactory().getCoach();
                loadBaseCoach(result, coach);
                coaches.add(coach);
            }
            myCall.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in loading league Coaches");
            return false;
        } finally {
            if (connection != null) {
                connectionManager.terminateConnection();
            }
        }
    }

    private void loadBaseCoach(ResultSet result, ICoach coach) throws SQLException {
        coach.setCoachID(result.getInt("coachID"));
        coach.setTeamID(result.getInt("teamID"));
        coach.setLeagueID(result.getInt("leagueID"));
        coach.setCoachName(result.getString("name"));

        ICoachStats stats = AbstractLeagueManagerFactory.getFactory().getCoachStats();
        stats.setSkating(result.getFloat("skating"));
        stats.setShooting(result.getFloat("shooting"));
        stats.setChecking(result.getFloat("checking"));
        stats.setSaving(result.getFloat("saving"));
        coach.setCoachStats(stats);
    }

}
