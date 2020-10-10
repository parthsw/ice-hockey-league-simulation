package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

import java.util.ArrayList;
import java.util.List;

public class Division implements IDivision{
    private int divisionID;
    private String divisionName;
    private int conferenceID;
    private List<ITeam> teams;

    private IPersistence db;

    public Division(IPersistence p, int conferenceId) {
        this();
        db = p;
        conferenceID = conferenceId;
    }

    public Division() {
        divisionID = -1;
        divisionName = null;
        conferenceID = -1;
        teams = new ArrayList<ITeam>();
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
    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public void setDivisionName(String name) {
        divisionName = name;
    }

    @Override
    public int getConferenceID() {
        return conferenceID;
    }

    @Override
    public void setConferenceID(int id) {
        conferenceID = id;
    }

    @Override
    public ITeam getTeamByName(String name) {
        ITeam team = null;
        for (ITeam t: teams) {
            if (t.getTeamName().equalsIgnoreCase(name)) {
                team = t;
                break;
            }
        }
        return team;
    }

    @Override
    public void addTeam(ITeam team) {
        teams.add(team);
    }

    @Override
    public List<ITeam> getTeams() {
        return teams;
    }

    @Override
    public void setTeams(List<ITeam> teams) {
        this.teams = teams;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    @Override
    public boolean checkIfDivisionNameExists() {
        db.loadDivisionWithName(divisionName,this);
        if (divisionID == -1) return false;
        else return true;
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(divisionName)) {
            validity = "Division name is blank";
        }
        return validity;
    }

    private boolean isDivisionNameExist(List<IDivision> divList) {
        boolean isExist = false;
        for (IDivision d: divList) {
            if (d.getDivisionName().equalsIgnoreCase(divisionName)) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    @Override
    public String validateNameDuringCreate(List<IDivision> divList) {
        String validity = "Valid";
        if (isNullOrEmpty(divisionName)) {
            validity = "Division name is blank";
        }
        else if (!isDivisionNameExist(divList)) {
            validity = divisionName + " - The given division does not exist, please enter a valid division name";
        }
        return validity;
    }

    @Override
    public String validateNameDuringLoad() {
        String validity = "Valid";
        if (isNullOrEmpty(divisionName)) {
            validity = "Division name is blank";
        }
        else if (!checkIfDivisionNameExists()) {
            validity = divisionName + " - The given division does not exist, please enter a valid division name";
        }
        return validity;
    }

    @Override
    public String validateBusinessRules() {
        String validity = "Valid";

        return validity;
    }

    @Override
    public void save() {
        db.saveDivision(this);
    }

    @Override
    public void load() {
        db.loadDivisionWithName(divisionName, this);
    }
}
