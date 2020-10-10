package asdc.hl.database;

import asdc.hl.leaguemodel.models.*;
import asdc.hl.leaguemodel.IPersistence;


import java.sql.SQLException;

public class Persistence implements IPersistence {
    test obj = null;
    public Persistence(){
        this.obj = new test();

    }

//    @Override
//    public void saveFreeAgent(IFreeAgent player) {
//        String result = this.obj.insertIntoFreeAgent(1, player.getPlayerName(), player.getIsCaptain(), player.getPlayerPosition());
//    }

    @Override
    public void saveLeague(ILeague league) {
        String result = this.obj.insertIntoLeague(1, league.getLeagueName());
        league.setLeagueID(Integer.parseInt(result));
    }

    @Override
    public void loadLeagueWithName(String name, ILeague league) {

    }

    @Override
    public void saveConference(IConference conference) {
        String result = this.obj.insertIntoConference(1,conference.getLeagueID(), conference.getConferenceName());
        conference.setConferenceID(Integer.parseInt(result));
    }

    @Override
    public void loadConferenceWithName(String conferenceName, IConference conference) {
//
    }

    @Override
    public void saveDivision(IDivision division) {
        String result = this.obj.insertIntoDivision(1, division.getConferenceID(), division.getDivisionName());
        division.setDivisionID(Integer.parseInt(result));
    }

    @Override
    public void loadDivisionWithName(String name, IDivision division) {

    }

    @Override
    public void saveTeam(ITeam team) {
        String result = this.obj.insertIntoTeam(1, team.getDivisionID(), team.getTeamName());
        team.setTeamID(Integer.parseInt(result));
    }

    @Override
    public void loadTeamWithName(String name, ITeam team) {

    }

    @Override
    public void savePlayer(IPlayer player) {
        String result = this.obj.insertIntoPlayer(1, player.getTeamID(), player.getPlayerName(), player.getIsCaptain(), player.getPlayerPosition());
        player.setTeamID(Integer.parseInt(result));
    }

    @Override
    public void loadPlayerWithName(String name, IPlayer player) {

    }

    @Override
    public void saveManager(IManager manager) {
        String result = this.obj.insertIntoManager(1, manager.getTeamID(), manager.getManagerName());
        manager.setManagerID(Integer.parseInt(result));
    }

    @Override
    public void loadManagerWithName(String name, IManager manager) {

    }

    @Override
    public void saveCoach(ICoach coach) {
        String result = this.obj.insertIntoCoach(1, coach.getTeamID(), coach.getCoachName());
        coach.setCoachID(Integer.parseInt(result));
   }

    @Override
    public void loadCoachWithName(String name, ICoach coach) {

    }
}
