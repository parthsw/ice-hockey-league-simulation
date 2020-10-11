package asdc.hl.database;

import java.sql.*;

public class test {

    private DBConnection dbConnectionUtil = new DatabaseInitialize();
    private Connection connection;

    public boolean insertIntoSaveState() {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoSaveState()}");
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in insert SaveState");
            return false;
        } finally {
            // dbConnectionUtil.terminateStatement(myCall);
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public String insertIntoLeague(int saveID, String leagueName) {
        String leagueID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();
            myCall = connection.prepareCall("{call insertIntoLeague(?,?,?)}");

            myCall.setInt(1, saveID);
            myCall.setString(2, leagueName);
            myCall.registerOutParameter(3, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                leagueID = result.getString("leagueID");
            }
            myCall.close();
            //System.out.println(leagueID);
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

    public String insertIntoConference(int saveID, int leagueID, String conferenceName) {
        String conferenceID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoConference(?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, leagueID);
            myCall.setString(3, conferenceName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                conferenceID = result.getString("conferenceID");
            }
            myCall.close();
           // System.out.println(conferenceID);

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

    public String insertIntoDivision(int saveID, int conferenceID, String divisionName) {
        String divisionID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoDivision(?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, conferenceID);
            myCall.setString(3, divisionName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                divisionID = result.getString("divisionID");
            }
            myCall.close();
           // System.out.println(divisionID);
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

    public String insertIntoTeam(int saveID, int divisionID, String teamName) {
        String teamID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoTeam(?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, divisionID);
            myCall.setString(3, teamName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                teamID = result.getString("teamID");
            }
            myCall.close();
            //System.out.println(teamID);
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

    public String insertIntoCoach(int saveID, int teamID, String coachName) {
        String coachID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoCoach(?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, teamID);
            myCall.setString(3, coachName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                coachID = result.getString("coachID");
            }
            myCall.close();
          //  System.out.println(coachID);
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

    public String insertIntoManager(int saveID, int teamID, String managerName) {
        String managerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoManager(?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, teamID);
            myCall.setString(3, managerName);
            myCall.registerOutParameter(4, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                managerID = result.getString("managerID");
            }
            myCall.close();
        //    System.out.println(managerID);
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

    public String insertIntoPlayer(int saveID, int teamID, String playerName, boolean captain, String playerPosition) {
        String playerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoPlayer(?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, teamID);
            myCall.setString(3, playerName);
            myCall.setBoolean(4, captain);
            myCall.setString(5, playerPosition);
            myCall.registerOutParameter(6, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("playerID");
            }
            myCall.close();
        //    System.out.println(playerID);
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

    public String insertIntoFreeAgent(int saveID, String playerName, boolean captain, String playerPosition) {
        String playerID = null;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call insertIntoFreeAgent(?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setString(2, playerName);
            myCall.setBoolean(3, captain);
            myCall.setString(4, playerPosition);
            myCall.registerOutParameter(5, Types.INTEGER);
            ResultSet result = myCall.executeQuery();
            while(result.next()) {
                playerID = result.getString("freeAgentID");
            }
            myCall.close();
       //     System.out.println(playerID);
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

    public boolean removeFromCoach(int coachID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromCoach(?,?)}");
            myCall.setInt(1, coachID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Coach");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromConference(int conferenceID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromConference(?,?)}");
            myCall.setInt(1, conferenceID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove conference");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromDivision(int divisionID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromDivision(?,?)}");
            myCall.setInt(1, divisionID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Division");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromLeague(int leagueID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromLeague(?,?)}");

            myCall.setInt(2, saveID);
            myCall.setInt(1, leagueID);

            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove League");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromManager(int managerID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromManager(?,?)}");
            myCall.setInt(1, managerID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Manager");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromPlayer(int playerID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromPlayer(?,?)}");
            myCall.setInt(1, playerID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Player");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromSaveState(int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromSaveState(?)}");

            myCall.setInt(1, saveID);


            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove SaveState");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromTeam(int teamID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromTeam(?,?)}");
            myCall.setInt(1, teamID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Team");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public boolean removeFromFreeAgent(int freeAgentID, int saveID) {
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call removeFromFreeAgent(?,?)}");
            myCall.setInt(1, freeAgentID);
            myCall.setInt(2, saveID);
            myCall.execute();
            myCall.close();
            return true;
        } catch (SQLException e) {
            System.out.println("error in remove Free Agent");
            return false;
        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }



    public Object[] readCoach(int saveID, int coachID) {
        CallableStatement myCall;
        Object[] obj = new Object[4];
        try {
            myCall = connection.prepareCall("{call readCoach(?,?,?,?,?,?)}");
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall.setInt(1, saveID);
            myCall.setInt(2, coachID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("coachName");
                obj[1]=result.getString("coachID");
                obj[2]=result.getString("saveID");
                obj[3]=result.getString("teamID");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readCoach");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readConference(int saveID, int conferenceID) {
        Object[] obj = new Object[4];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readConference(?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, conferenceID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("conferenceID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("leagueID");
                obj[3]=result.getString("conferenceName");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readConference");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readDivision(int saveID, int divisionID) {
        Object[] obj = new Object[4];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readDivision(?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, divisionID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("divisionID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("conferenceID");
                obj[3]=result.getString("divisionName");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readDivision");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readLeague(int saveID, int leagueID) {
        Object[] obj = new Object[3];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readLeague(?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, leagueID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("leagueID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("leagueName");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readLeague");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readManager(int saveID, int managerID) {
        Object[] obj = new Object[4];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readManager(?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, managerID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("ManagerID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("teamID");
                obj[3]=result.getString("managerName");
            }
            myCall.close();
            return obj;

        } catch (SQLException e) {
            System.out.println("error in readManager");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readPlayer(int saveID, int playerID) {
        Object[] obj = new Object[6];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readPlayer(?,?,?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, playerID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            myCall.registerOutParameter(7, Types.TINYINT);
            myCall.registerOutParameter(8, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("playerID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("teamID");
                obj[3]=result.getString("playerName");
                obj[4]=result.getString("captain");
                obj[5]=result.getString("playerPosition");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readPlayer");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readTeam(int saveID, int teamID) {
        Object[] obj = new Object[4];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readTeam(?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, teamID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);
            myCall.registerOutParameter(5, Types.INTEGER);
            myCall.registerOutParameter(6, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("teamID");
                obj[1]=result.getString("saveID");
                obj[2]=result.getString("divisionID");
                obj[3]=result.getString("teamName");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readTeam");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public Object[] readFreeAgent(int saveID, int freeAgentID) {
        Object[] obj = new Object[5];
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readPlayer(?,?,?,?,?,?,?)}");
            myCall.setInt(1, saveID);
            myCall.setInt(2, freeAgentID);
            myCall.registerOutParameter(3, Types.INTEGER);
            myCall.registerOutParameter(4, Types.INTEGER);

            myCall.registerOutParameter(5, Types.VARCHAR);
            myCall.registerOutParameter(6, Types.TINYINT);
            myCall.registerOutParameter(7, Types.VARCHAR);
            ResultSet result = myCall.executeQuery();
            while(result.next()){
                obj[0]=result.getString("freeAgentID");
                obj[1]=result.getString("saveID");

                obj[2]=result.getString("playerName");
                obj[3]=result.getString("captain");
                obj[4]=result.getString("playerPosition");
            }
            myCall.close();
            return obj;


        } catch (SQLException e) {
            System.out.println("error in readFreeAgent");
            return null;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }

    public int readSaveState() {
        int count = 0;
        CallableStatement myCall;
        try {
            dbConnectionUtil = new DatabaseInitialize();
            connection = dbConnectionUtil.getConnection();

            myCall = connection.prepareCall("{call readCoach()}");
            ResultSet result = myCall.executeQuery();
            while(result.next()){
               count++;
            }
            myCall.close();
            return count;


        } catch (SQLException e) {
            System.out.println("error in readCoach");
            return 0;

        } finally {
            if (connection != null) {
                dbConnectionUtil.terminateConnection();
            }
        }
    }
}
