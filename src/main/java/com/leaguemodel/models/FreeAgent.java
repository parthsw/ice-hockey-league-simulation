package com.leaguemodel.models;

import com.leaguemodel.IPersistence;

public class FreeAgent implements IFreeAgent {
    private int playerID;
    private String playerName;
    private boolean isCaptain;
    private String playerPosition;
    private int teamID;

    private IPersistence db;

    public FreeAgent(IPersistence p) {
        this();
        db = p;
    }

    public FreeAgent() {
        playerID = -1;
        playerName = null;
        isCaptain = false;
        teamID = -1;
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
    public boolean getIsCaptain() {
        return isCaptain;
    }

    @Override
    public void setIsCaptain(boolean isCaptain) {
        this.isCaptain = isCaptain;
    }

    @Override
    public String getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public void setPlayerPosition(String name) {
        playerPosition = name;
    }


    private boolean isNullOrEmpty(String s) {
        return !(s == null || s.equals(""));
    }

    @Override
    public String validateNameDuringImport() {
        String validity = "Valid";
        if (isNullOrEmpty(playerName)) {
            validity = "Free agent name is blank";
        }
        return validity;
    }

    @Override
    public String validateNameDuringCreate() {
        String validity = "Valid";
        if (isNullOrEmpty(playerName)) {
            validity = "Free agent name is blank";
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
        return validity;
    }

    public String validateName() {
        String validity = "Valid";
        if (isNullOrEmpty(playerName)) {
            validity = "Free agent is blank";
        }
        return validity;
    }

//    @Override
//    public void save() {
//        db.savePlayer(this);
//    }
//
//    @Override
//    public void load() {
//        db.loadPlayerWithName(playerName, this);
//    }
}
