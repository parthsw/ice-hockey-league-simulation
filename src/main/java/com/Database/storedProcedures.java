/*package com.Database;

import java.sql.*;

public class storedProcedures {
    private IDBConnection dbConnectionUtil = new DBConnection();
    private Connection connection;

    public String insertIntoLeague(String leagueName) {
        String leagueID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();
            myCall = connection.prepareCall("{call insertIntoLeague(?,?)}");

            myCall.setString(1, leagueName);
            myCall.registerOutParameter(2, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                leagueID = result.getString("lastID");
            }
            myCall.close();
            return leagueID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert League");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoConference(int leagueID, String conferenceName) {
        String conferenceID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoConference(?,?,?)}");
            myCall.setInt(1, leagueID);
            myCall.setString(2, conferenceName);
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                conferenceID = result.getString("lastID");
            }
            myCall.close();
            return conferenceID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Conference");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoDivision(int conferenceID, String divisionName) {
        String divisionID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoDivision(?,?,?)}");
            myCall.setInt(1, conferenceID);
            myCall.setString(2, divisionName);
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                divisionID = result.getString("lastID");
            }
            myCall.close();
            return divisionID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Division");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoTeam(int divisionID, String teamName, int strength) {
        String teamID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoTeam(?,?,?,?)}");
            myCall.setInt(1, divisionID);
            myCall.setString(2, teamName);
            myCall.setInt(3, strength);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                teamID = result.getString("lastID");
            }
            myCall.close();
            return teamID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Team");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoCoach(int teamID, int leagueID ,String coachName, float skating, float shooting, float checking, float saving) {
        String coachID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoCoach(?,?,?,?)}");
            myCall.setInt(1, teamID);
            myCall.setInt(2, leagueID);
            myCall.setString(3, coachName);
            myCall.setFloat(4,skating);
            myCall.setFloat(5,shooting);
            myCall.setFloat(6,checking);
            myCall.setFloat(7,saving);
            myCall.registerOutParameter(8, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                coachID = result.getString("lastID");
            }
            myCall.close();
            return coachID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Coach");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoManager( int teamID,int leagueID, String managerName) {
        String managerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoManager(?,?,?,?)}");
            myCall.setInt(1, teamID);
            myCall.setInt(2, leagueID);
            myCall.setString(3, managerName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                managerID = result.getString("lastID");
            }
            myCall.close();
            return managerID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert Manager");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoPlayer(int teamID, String playerName, String playerPosition, int age, int skating,int shooting, int checking, int saving, boolean captain, float strength, boolean isInjured) {
        String playerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoPlayer(?,?,?,?,?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, teamID);
            myCall.setString(2, playerName);
            myCall.setString(3, playerPosition);
            myCall.setInt(4, age);
            myCall.setInt(5, skating);
            myCall.setInt(6, shooting);
            myCall.setInt(7, checking);
            myCall.setInt(8, saving);
            myCall.setBoolean(9, captain);
            myCall.setFloat(10, strength);
            myCall.setBoolean(11, isInjured);

            myCall.registerOutParameter(12, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("lastID");
            }
            myCall.close();
            return playerID;
        } catch (SQLException e) {
            System.out.println("error in insert player");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoFreeAgent(int leagueID, String playerName, String playerPosition, int age, int skating,int shooting, int checking, int saving, float strength, boolean isInjured) {
        String playerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoFreeAgent(?,?,?,?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, leagueID);
            myCall.setString(2, playerName);
            myCall.setString(3, playerPosition);
            myCall.setInt(4, age);
            myCall.setInt(5, skating);
            myCall.setInt(6, shooting);
            myCall.setInt(7, checking);
            myCall.setInt(8, saving);
            myCall.setFloat(9, strength);
            myCall.setBoolean(10, isInjured);
            myCall.registerOutParameter(11, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("lastID");
            }
            myCall.close();
            //    System.out.println(playerID);
            return playerID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in insert player");
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public void interTeamTrade(int toTeamID, int playerID){
        CallableStatement myCall;
        String status="interTeamTrade failed";
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();
            myCall = connection.prepareCall("{call interTeamTrade(?,?,?)}");
            myCall.setInt(1, toTeamID);
            myCall.setInt(2, playerID);
            myCall.registerOutParameter(3, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                status = result.getString("status");
            }
            System.out.println(status);
            myCall.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in interTeamTrade");
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }

    }

    public String freeToTeamTrade(int freeID, int teamID, boolean isCaptain) {
        String playerID = null;
        String status = "Trade failed";
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();
            myCall = connection.prepareCall("{call freeToTeamTrade(?,?,?,?,?)}");

            myCall.setInt(1, freeID);
            myCall.setInt(2, teamID);
            myCall.setBoolean(3, isCaptain);
            myCall.registerOutParameter(4, Types.VARCHAR);
            myCall.registerOutParameter(5, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("lastID");
                status = result.getString("status");
            }
            myCall.close();
            System.out.println(status);
            return playerID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in freeToTeamTrade");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String teamToFreeTrade(int playerID, int leagueID) {
        String freeID = null;
        String status = "Trade failed";
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DBConnection();
            connection = dbConnectionUtil.getConnection();
            myCall = connection.prepareCall("{call teamToFreeTrade(?,?,?,?)}");

            myCall.setInt(1, playerID);
            myCall.setInt(2, leagueID);
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                freeID = result.getString("lastID");
                status = result.getString("status");
            }
            myCall.close();
            System.out.println(status);
            return freeID;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error in freeToTeamTrade");
            return null;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }
}
*/