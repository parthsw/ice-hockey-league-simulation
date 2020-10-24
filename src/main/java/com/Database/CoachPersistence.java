package com.Database;

import com.IceHockeyLeague.LeagueManager.Coach.ICoach;
import com.IceHockeyLeague.LeagueManager.Coach.ICoachPersistence;

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
            myCall.setFloat(4,coach.getCoachStats().getSkating());
            myCall.setFloat(5,coach.getCoachStats().getShooting());
            myCall.setFloat(6,coach.getCoachStats().getChecking());
            myCall.setFloat(7,coach.getCoachStats().getSaving());
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
    public boolean loadTeamCoach(int leagueId, int teamId, ICoach coach) {
        return false;
    }

    @Override
    public boolean loadLeagueCoaches(int leagueId, List<ICoach> coaches) {
        return false;
    }
}
