package asdc.hl.leaguemodel.models;

import asdc.hl.leaguemodel.IPersistence;

public class Player implements IPlayer {
    private int playerID;
    private String playerName;
    private boolean isCaptain;
    private String playerPosition;
    private int teamID;

    private IPersistence db;

    public Player(IPersistence p, int teamId) {
        this();
        db = p;
        teamID = teamId;
    }

    public Player() {
        playerID = -1;
        playerName = null;
        isCaptain = false;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    @Override
    public void setPlayerID(int id) {
        playerID = id;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String name) {
        playerName = name;
    }

    @Override
    public String getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public void setPlayerPosition(String name) {
        playerPosition = name;
    }

    @Override
    public boolean getIsCaptain() {
        return isCaptain;
    }

    @Override
    public void setIsCaptain(boolean isCaptain) {
        this.isCaptain = isCaptain;
    }

    @Override
    public int getTeamID() {
        return teamID;
    }

    @Override
    public void setTeamID(int id) {
        teamID = id;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || s.equals("");
    }

    private boolean isPositionNotValid() {
        return !(playerPosition.equalsIgnoreCase("goalie") ||
                playerPosition.equalsIgnoreCase("forward") ||
                playerPosition.equalsIgnoreCase("defense"));
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(playerName)) {
            validity = "Player name is blank";
        }
        return validity;
    }

    @Override
    public String validateNameDuringCreate() {
        String validity = "Valid";
        if (isNullOrEmpty(playerName)) {
            validity = "Player name is blank";
        }
        return validity;
    }

    @Override
    public String validateNameDuringLoad() {
        return validateNameDuringCreate();
    }

    @Override
    public String validateBusinessRules() {
        String validity = "Valid";
        if (isPositionNotValid()) {
            validity = playerName + " has an invalid position.";
        }
        return validity;
    }

    @Override
    public void save() {
        db.savePlayer(this);
    }

    @Override
    public void load() {
        db.loadPlayerWithName(playerName, this);
    }
}
