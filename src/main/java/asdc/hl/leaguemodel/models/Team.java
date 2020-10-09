package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class Team implements ITeam {
    private int teamID;
    private int divisionID;
    private String teamName;
    private List<IPlayer> players;
    private List<IManager> managers;
    private List<ICoach> coaches;

    private IPersistence db;

    public Team(IPersistence p, int divisionId) {
        this();
        db = p;
        divisionID = divisionId;
    }

    public Team() {
        teamID = -1;
        teamName = null;
        players = new ArrayList<IPlayer>();
        managers = new ArrayList<IManager>();
        coaches = new ArrayList<ICoach>();
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public void setTeamID(int id) {
        teamID = id;
    }

    @Override
    public int getDivisionID() {
        return divisionID;
    }

    @Override
    public void setDivisionID(int id) {
        divisionID = id;
    }

    @Override
    public String getTeamName() {
        return this.teamName;
    }

    @Override
    public void setTeamName(String name) {
        teamName = name;
    }

    @Override
    public IPlayer getPlayerByName(String name) {
        IPlayer player = null;
        for (IPlayer p: players) {
            if (p.getPlayerName().equalsIgnoreCase(name)) {
                player = p;
                break;
            }
        }
        return player;
    }

    @Override
    public void addPlayer(IPlayer player) {
        players.add(player);
    }

    @Override
    public List<IPlayer> getPlayers() {
        return players;
    }

    @Override
    public void setPlayers(List<IPlayer> players) {
        this.players = players;
    }

    @Override
    public ICoach getCoachByName(String name) {
        ICoach coach = null;
        for (ICoach c: coaches) {
            if (c.getCoachName().equalsIgnoreCase(name)) {
                coach = c;
                break;
            }
        }
        return coach;
    }

    @Override
    public void addCoach(ICoach coach) {
        coaches.add(coach);
    }

    @Override
    public IManager getManagerByName(String name) {
        IManager manager = null;
        for (IManager m: managers) {
            if (m.getManagerName().equalsIgnoreCase(name)) {
                manager = m;
                break;
            }
        }
        return manager;
    }

    @Override
    public void addManager(IManager manager) {
        managers.add(manager);
    }

    @Override
    public List<IManager> getManagers() {
        return managers;
    }

    @Override
    public void setManagers(List<IManager> managers) {
        this.managers = managers;
    }

    @Override
    public List<ICoach> getCoaches() {
        return coaches;
    }

    @Override
    public void setCoaches(List<ICoach> coaches) {
        this.coaches = coaches;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    @Override
    public boolean checkIfTeamNameExists() {
        db.loadTeamWithName(teamName,this);
        if (teamID == -1) return false;
        else return true;
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(teamName)) {
            validity = "Team name is blank";
        }
        return validity;
    }

    private boolean isTeamNameDuplicate(List<ITeam> teamList) {
        boolean duplicate = false;
        for (ITeam t: teamList) {
            if (t.getTeamName().equalsIgnoreCase(teamName)) {
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }

    @Override
    public String validateNameDuringCreate(List<ITeam> teamList) {
        String validity = "Valid";
        if (isNullOrEmpty(teamName)) {
            validity = "Team name is blank";
        }
        else if (isTeamNameDuplicate(teamList)) {
            validity = teamName + " - The given team name already exists, please enter a valid team name";
        }
        return validity;
    }

    @Override
    public String validateNameDuringLoad() {
        String validity = "Valid";
        if (isNullOrEmpty(teamName)) {
            validity = "Team name is blank";
        }
        else if (!checkIfTeamNameExists()) {
            validity = teamName + " - The given team name already exists, please enter a valid team name";
        }
        return validity;
    }

    private boolean isNumberOfPlayerNot20() {
        return players.size() != 20;
    }

    private boolean isCaptainNot1() {
        int captain = 0;
        for (IPlayer p: players) {
            if (p.getIsCaptain()) {
                captain++;
            }
        }
        return captain != 1;
    }

    @Override
    public String validateBusinessRules() {
        String validity = "Valid";
        if (isNumberOfPlayerNot20()) {
            validity = teamName + " - The number of Players in this team are not equal to 20";
        }
        else if(isCaptainNot1()) {
            validity = teamName + " - There can be only a single captain in the team";
        }
        return validity;
    }

    @Override
    public void save() {
        db.saveTeam(this);
    }

    @Override
    public void load() {
        db.loadTeamWithName(teamName, this);
    }
}
